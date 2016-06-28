package com.example.admin.learnretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link();
            }
        });
        ButterKnife.bind(this);
    }

    /**
     * 测试，是否在主线程，进行网络请求
     */
    @OnClick(R.id.link) public void link(){
        new RxRetrofitTest().linkNet();
    }
}
