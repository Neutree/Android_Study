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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button button_signUp=(Button)findViewById(R.id.SignUP_Button);
        final EditText userName=(EditText)findViewById(R.id.SignUp_userName);
        final EditText password=(EditText)findViewById(R.id.SignUp_password);
        final EditText password2=(EditText)findViewById(R.id.SignUp_password2);
        Spinner spinner1= (Spinner) findViewById(R.id.SignUp_spinner);
        List<String> list_Proresson = new ArrayList<>();
        list_Proresson.add(getResources().getString(R.string.profession_hint));
        list_Proresson.add(getResources().getString(R.string.Profession_IT));
        list_Proresson.add(getResources().getString(R.string.Profession_Art));
        list_Proresson.add(getResources().getString(R.string.Profession_Business));
        list_Proresson.add(getResources().getString(R.string.Profession_Edu));

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,R.layout.spinner_white_item,list_Proresson);//set fold style
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);//set unfold style

        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new spinnerSelected());
        spinner1.setSelection(0);

        button_signUp.setOnClickListener(new View.OnClickListener() {
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
//                    builder.setPositiveButton(getResources().getString(R.string.YES), null);
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

    }

    class spinnerSelected implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position==0){//default value

            }
            else{           //valid item

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

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
