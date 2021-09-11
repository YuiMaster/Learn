package cn.yui.learn;

/**
 * @Description: 绑定视图
 * @Author: Yui Master
 * @CreateDate: 9/11/21 7:34 PM
 */
public interface IBinder<T> {
    void bind(T target);
}
