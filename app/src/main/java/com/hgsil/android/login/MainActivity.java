package com.hgsil.android.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {
    private static String isChecked ="false";


    //判断输入的账户密码是否正确 并作出反应
    public boolean isRight(String username,String password){
        SharedPreferences pref = MainActivity.this.getSharedPreferences("data",MODE_PRIVATE);
        HashMap<String,String> user = new HashMap<>();
        user.put(username,pref.getString(username,""));

        if (!user.get(username).equals("")){
                if (user.get(username).equals(password)){
                    Toast.makeText(MainActivity.this,"登陆成功!",Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    Toast.makeText(MainActivity.this,"错误的密码!",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(MainActivity.this,"账号不存在!",Toast.LENGTH_SHORT).show();
                return false;
            }

    }
    //忘记密码时给出密码
    public void forgetPassword(String username){
        SharedPreferences pref1 = MainActivity.this.getSharedPreferences("data",MODE_PRIVATE);
        String password;
        password =pref1.getString(username,"");
        Toast.makeText(MainActivity.this,password,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = MainActivity.this.getSharedPreferences("data",MODE_PRIVATE);
        if (pref.getString("isChecked","").equals("true")){
            AutoCompleteTextView usernameInlogin = (AutoCompleteTextView) findViewById(R.id.usernameInlogin);
            EditText passwoedInlogin = (EditText)findViewById(R.id.passwordInlogin);
            CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
            usernameInlogin.setThreshold(1);
            checkBox.setChecked(true);
            passwoedInlogin.setText(pref.getString(usernameInlogin.getText().toString(),""));
        }
        //监听返回键
        TextView textReturn = (TextView) findViewById(R.id.textReturn);
        textReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //监听页面跳转的  注册  按钮
        TextView createnow =(TextView)findViewById(R.id.creatnow);
        createnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
        //登陆功能的监视
        final AutoCompleteTextView usernameInlogin = (AutoCompleteTextView) findViewById(R.id.usernameInlogin);
        final EditText passwoedInlogin = (EditText)findViewById(R.id.passwordInlogin);
        TextView loginning = (TextView) findViewById(R.id.logining);
        loginning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(usernameInlogin.getText())) {
                    isRight(usernameInlogin.getText().toString(),
                            passwoedInlogin.getText().toString());
                }
                else {
                    Toast.makeText(MainActivity.this,"请输入正确的账户名!",Toast.LENGTH_SHORT).show();

                }
            }
        });
        //忘记密码键的监视   创建一个忘记密码的activity
        TextView forgetPasswoed = (TextView) findViewById(R.id.forgetPassword);
        forgetPasswoed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
        //记住密码的监听,最后这玩意只能保存点击的状态 不能记住密码。。。。
        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = MainActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor mEditor = pref.edit();
                if (checkBox.isChecked()){
                    checkBox.setChecked(true);
                    isChecked = "true";
                    mEditor.putString("isChecked",isChecked);
                    mEditor.commit();
                }else {
                    checkBox.setChecked(false);
                    isChecked = "false";
                    mEditor.putString("isChecked",isChecked);
                    mEditor.commit();
                }
            }
        });
    }
}
