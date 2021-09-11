package cn.yui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 当前代码插入点在java 变成 class的时候
// java -- [javac] -- class -- [dx] -- dex(smali)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface BindView {
    int id();
}
