package com.haokukeji.autoapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tvChannel = (TextView) findViewById(R.id.tv_channel);
        String channel = ChannelUtil.getChannel(this);
        if (!TextUtils.isEmpty(channel.trim())) {
            tvChannel.setText(String.format(getString(R.string.main_channel), channel));
        }
    }
}
