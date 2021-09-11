package cn.yui.annotation_compiler;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

/**
 * @Description: java类作用描述
 * @Author: Yui Master
 * @CreateDate: 9/11/21 7:26 PM
 */
public class LOG {


    /**
     * 打印编译文本信息
     *
     * @param processingEnv
     * @param logInfo
     */
    public static void d(ProcessingEnvironment processingEnv, String logInfo) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, logInfo);
    }

    public static void e(ProcessingEnvironment processingEnv, String logInfo) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, logInfo);
    }

    public static void w(ProcessingEnvironment processingEnv, String logInfo) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, logInfo);
    }
}
