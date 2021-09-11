package cn.yui.learn.apt;

import android.util.Log;

/**
 * @Description: java类作用描述
 * @Author: Yui Master
 * @CreateDate: 9/11/21 5:40 PM
 */
public class AptTest {
    private static final String TAG = "hd/" + AptTest.class.getSimpleName();


    @Login
    public void testLoginA() {
        Log.d(TAG, "testLoginA");
    }

    public void testLoginB() {
        Log.d(TAG, "testLoginB");
    }


}
