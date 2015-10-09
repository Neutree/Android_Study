package com.study.neutree.android_study;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        System.out.println("OnCreate");
        Log.d("debug_info", "onCreate ");

        Button button_login=(Button)findViewById(R.id.button_login);
        Button button_quit=(Button)findViewById(R.id.button_quit);
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
                    AlertDialog.Builder builder  = new  AlertDialog.Builder(sign_up.this);
                    builder.setTitle(getResources().getString(R.string.SignUp_Error_Input)) ;
                    builder.setMessage(getResources().getString(R.string.NotComplete)) ;
                    builder.setPositiveButton(getResources().getString(R.string.YES), null);
                    builder.show();
               //     Toast.makeText(getApplicationContext(), getResources().getString(R.string.NotComplete), Toast.LENGTH_SHORT).show();
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
        button_quit.setOnClickListener(new quit());//添加点击退出按钮事件
    }
    class quit implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder  = new  AlertDialog.Builder(sign_up.this);
            builder.setTitle(getResources().getString(R.string.Exit)) ;
            builder.setMessage(getResources().getString(R.string.Exit) + "?") ;
            builder.setPositiveButton(getResources().getString(R.string.YES), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.NO), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        System.out.println("OnCreateOptionMenu");
        Log.d("debug_info", "onCreateOptionsMenu ");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        System.out.println("OnOptionsItemSelected");
        Log.d("debug_info", "onOptionsItemSelected ");

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("OnStart");
        Log.d("debug_info", "onStart ");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
        Log.d("debug_info", "onRestart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        Log.d("debug_info", "onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
        Log.d("debug_info", "onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        Log.d("debug_info", "onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        Log.d("debug_info", "onDestroy ");
    }
}
