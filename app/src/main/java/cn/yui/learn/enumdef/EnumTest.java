package cn.yui.learn.enumdef;

/**
 * @Description: 自定义枚举，出于内存优化考虑
 * @Author: Yui Master
 * @CreateDate: 9/11/21 5:44 PM
 */
public class EnumTest {
    @EnTest
    private int mValue = EnTest.TRUE;

    public void initEnum(@EnTest int value) {
        mValue = value;
    }
}
