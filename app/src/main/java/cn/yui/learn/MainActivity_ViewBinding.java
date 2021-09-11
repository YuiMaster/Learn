package cn.yui.learn;

/**
 * @Description: java类作用描述
 * @Author: Yui Master
 * @CreateDate: 9/11/21 7:32 PM
 */
class MainActivity_ViewBinding implements IBinder<MainActivity> {
    public MainActivity_ViewBinding() {

    }

    @Override
    public void bind(MainActivity target) {
        target.textView = target.findViewById(R.id.text_view);
    }
}
