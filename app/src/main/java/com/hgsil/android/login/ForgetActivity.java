package com.hgsil.android.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class ForgetActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        TextView forgetting = (TextView) findViewById(R.id.forgetting);
        forgetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameInforget = (EditText)findViewById(R.id.usernameInforget);
                SharedPreferences pref = ForgetActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                pref.getString(usernameInforget.getText().toString(),"");
                Toast.makeText(ForgetActivity.this,
                        pref.getString(usernameInforget.getText().toString(),""),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
