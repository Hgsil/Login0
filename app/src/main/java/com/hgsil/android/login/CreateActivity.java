package com.hgsil.android.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class CreateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText passwoed = (EditText)findViewById(R.id.password);
        TextView creatbutton = (TextView) findViewById(R.id.createbutton);
        creatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = CreateActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor =pref.edit();
                if (!TextUtils.isEmpty(username.getText())&&!TextUtils.isEmpty(passwoed.getText())){
                    editor.putString(username.getText().toString(),passwoed.getText().toString());
                    Toast.makeText(CreateActivity.this,"用户注册成功！",Toast.LENGTH_SHORT).show();
                    editor.commit();
                }else {
                    Toast.makeText(CreateActivity.this,"请正确输入用户名或密码！",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
