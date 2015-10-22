package com.study.neutree.android_study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class sign_In extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);
        TextView SginInToSignUp = (TextView) findViewById(R.id.SignIn_SignUp);
        TextView SignInForgetPassword = (TextView) findViewById(R.id.SignIn_ForgetPassword);

        //add onclick listener
        SignInForgetPassword.setOnClickListener(new ToForgetPasswordPage());
        SginInToSignUp.setOnClickListener(new ToSignIn());
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
}
