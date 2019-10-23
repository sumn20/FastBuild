package com.seeyou.fastbuild;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seeyou.toolkit.base.ListProcessFactory;
import com.seeyou.toolkit.network.NetworkUtils;
import com.seeyou.toolkit.view.Options;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageAdapter messageAdapter = new MessageAdapter(null);
        new ListProcessFactory<Message>()
                .bind(this.getWindow().getDecorView()).adapter(messageAdapter)
                .empty(new Options("无数据"))
                .loadCallback(messageListProcessFactory -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", "测试");
                    NetworkUtils.getInstance().doGet("getAllMessage", map, Message.class, messageListProcessFactory.callBack());
                }).autoLoad();
    }


}
