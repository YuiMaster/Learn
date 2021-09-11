package cn.yui.annotation_compiler;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

import cn.yui.annotations.BindView;

/**
 * @Description: 注解处理程序
 * @Author: Yui Master
 * @CreateDate: 9/11/21 6:52 PM
 */
@AutoService(Processor.class)
public class AnnotationsCompiler extends AbstractProcessor {

    /**
     * 生成apt目录下面的文件的对象
     */
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        LOG.d(processingEnv, "init " + processingEnvironment.getMessager());
    }

    /**
     * 支持的版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 支持哪些注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // HashSet 去除重复值
        Set<String> types = new HashSet<>();
        types.add(BindView.class.getCanonicalName());
        LOG.d(processingEnv, "getSupportedAnnotationTypes");
        return types;
    }

    /**
     * @param set
     * @param roundEnvironment
     * @return
     * @see <e href = "https://doc.codingdict.com/java_api/javax/lang/model/element/Element.html">Element 文档地址</>
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        LOG.d(processingEnv, "开始生成模板代码" + roundEnvironment.toString());
        LOG.d(processingEnv, "开始生成模板代码" + set.toString());

        // 获取所有用到了BindView注解的对象
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        LOG.d(processingEnv, "开始生成模板代码 " + elements.size());

        Map<String, List<VariableElement>> map = new HashMap<>();
        // 遍历所有注解
        for (Element element : elements) {
            /**
             * getEnclosingElement():返回封装此元素（非严格意义上）的最里层元素
             * 当前的获取的即activity类
             */
            VariableElement variableElement = (VariableElement) element;
            Class clazz = variableElement.getEnclosingElement().getClass();
            String activityName = variableElement.getEnclosingElement().getSimpleName().toString();
            List<VariableElement> variableElements = map.get(activityName);
            if (variableElements == null) {
                variableElements = new ArrayList<>();
                map.put(activityName, variableElements);
            }
            variableElements.add(variableElement);
        }

        /**
         * 生成代码
         */
        if (map.size() > 0) {
            Writer writer = null;
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String activityName = iterator.next();
                List<VariableElement> variableElements = map.get(activityName);
                TypeElement typeElement = (TypeElement) variableElements.get(0).getEnclosingElement();
                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).toString();
                LOG.d(processingEnv, "取得 packageName： " + packageName);

                // 编织代码
                try {
                    JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + activityName + "ViewBinding");
                    writer = sourceFile.openWriter();
                    writer.write("package " + packageName + ";");
                    writer.write("\n");

                    // 相当于 import cn.yui.learn.viewBinding.IBinder) {;
                    writer.write("import " + packageName + ".IBinder;");
                    writer.write("\n");

                    // 相当于 class MainActivity_ViewBinding implements IBinder<MainActivity> {
                    writer.write("public class " + activityName + "ViewBinding implements IBinder<" + packageName + "." + activityName + "> { \n");

                    writer.write("@Override\n");
                    // 相当于 public void bind(MainActivity target) {;
                    writer.write("public void bind(" + activityName + " target) {\n");
                    // 取得所有的注解
                    Iterator var12 = variableElements.iterator();
                    while (var12.hasNext()) {
                        VariableElement variableElement = (VariableElement) var12.next();
                        int id = variableElement.getAnnotation(BindView.class).id();
                        String name = variableElement.getSimpleName().toString();

                        // 相当于 target.textView = target.findViewById(R.id.text_view);
                        TypeMirror typeMirror = variableElement.asType();
                        writer.write("target." + name + " =(" + typeMirror + ")target.findViewById(" + id + ");\n");
                    }

                    writer.write("} \n");
                    writer.write("} \n");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.flush();
                            writer.close();
                        } catch (Exception var24) {
                            var24.printStackTrace();
                        }
                    }
                }

            }

        }

        LOG.d(processingEnv, "模板代码已完成");
        return false;
    }
}
