package com.study.neutree.android_study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sign_In extends AppCompatActivity {
    /****************************登录数据储存*****************************/
    /*定义访问模式*/
    public static int MODE = MODE_PRIVATE;
    /*定义一个SharedPreferences名。之后将以这个名字保存在Android文件系统中*/
    public static final String PREFERENCE_NAME = "SavedPassword";
    /*******************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

    //get view resources
        TextView SginInToSignUp = (TextView) findViewById(R.id.SignIn_SignUp);
        TextView SignInForgetPassword = (TextView) findViewById(R.id.SignIn_ForgetPassword);
        Button buttonSignIn=(Button)findViewById(R.id.button_signUp);
        EditText userName= (EditText) findViewById(R.id.signIn_UserName);
        EditText userPassword= (EditText) findViewById(R.id.signIn_UserPassword);

    //load resources
        /*获取SharedPreferences实例。如果不存在将新建一个  */
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
        /*读取SharedPreferences中保存的键值:如果文件或键值不在，则用缺省值 */
        String name = sharedPreferences.getString("Name","");
        String password = sharedPreferences.getString("Password","");
        userName.setText(name);
        userPassword.setText(password);

    //add onclick listener
        SignInForgetPassword.setOnClickListener(new ToForgetPasswordPage());
        SginInToSignUp.setOnClickListener(new ToSignIn());
        buttonSignIn.setOnClickListener(new Signin());

    }

    class ToForgetPasswordPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
    class ToSignIn implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(sign_In.this, sign_up.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign__in, menu);
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

    private class Signin implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            CheckBox rememberPassword=(CheckBox)findViewById(R.id.signIn_rememberPassword);
            EditText userNameEdit= (EditText) findViewById(R.id.signIn_UserName);
            EditText userPasswordEdit= (EditText) findViewById(R.id.signIn_UserPassword);
            String userName=userNameEdit.getText().toString().trim();
            String userPassword=userPasswordEdit.getText().toString().trim();
            if(userName.equals("")||userName==null||userPassword.equals("")||userPassword==null)
            {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.NotComplete), Toast.LENGTH_SHORT).show();
                return ;
            }

            //在这里验证登录正确性


            //如果选择了记住密码，则执行记住密码操作
            if(rememberPassword.isChecked())
            {
                /*获取SharedPreferences实例  */
                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
                 /*通过SharedPreferences.Editor类向SharedPreferences中写键值，调用commit()保存修改内容*/
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Name", userName);
                editor.putString("Password",userPassword);
                editor.commit();

            }

            //Jump to success page
            Intent intent = new Intent(sign_In.this, signUpSuccess.class);
            Bundle bundle = new Bundle();
            bundle.putCharSequence("userName", userName);
            bundle.putCharSequence("password", userPassword);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
