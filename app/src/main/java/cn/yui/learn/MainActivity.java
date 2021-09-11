package cn.yui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import cn.yui.annotations.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(id = R.id.text_view)
    TextView textView;


    @BindView(id = R.id.text_view2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewBindingUtil.bindView(this);

        textView.setText("你好");
        textView2.setText("第二个管控阿金");
    }
}