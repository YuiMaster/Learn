package cn.yui.learn;

import android.app.Activity;

/**
 *
 */
public class ViewBindingUtil {
    /**
     * 绑定视图
     *
     * @param activity
     */
    public static void bindView(Activity activity) {
        String name = activity.getClass().getName() + "ViewBinding";
        try {
            Class<?> clazz = Class.forName(name);
            IBinder binder = (IBinder) clazz.newInstance();
            binder.bind(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
