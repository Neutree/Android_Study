package com.study.neutree.android_study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button button_login=(Button)findViewById(R.id.button_login);
        final EditText userName=(EditText)findViewById(R.id.userName);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText password2=(EditText)findViewById(R.id.password2);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_userName = userName.getText().toString().trim();
                String str_password = password.getText().toString().trim();
                String str_password2 = password2.getText().toString().trim();
                if (str_userName.length() == 0 || str_password.length() == 0 || str_password2.length() == 0)//未输入用户名或者密码
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.NotComplete), Toast.LENGTH_SHORT).show();
                } else if (!str_password.equals(str_password2)) {//两次输入的密码不相同

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.PasswordDiffrent), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(sign_up.this, signUpSuccess.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("userName", str_userName);
                    bundle.putCharSequence("password", str_password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
