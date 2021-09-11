package cn.yui.learn.enumdef;


import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cn.yui.learn.enumdef.EnTest.TRUE;

@IntDef(value = {TRUE, EnTest.FALSE})
@Target(value = {ElementType.PARAMETER,ElementType.FIELD})
@Retention(value = RetentionPolicy.SOURCE)
public @interface EnTest {
    int TRUE = 0;
    int FALSE = 1;
}
