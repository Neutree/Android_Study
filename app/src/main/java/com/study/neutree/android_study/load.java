package com.study.neutree.android_study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.study.neutree.adapter.DBAdapterImpl;

public class load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Intent intent = new Intent(load.this, sign_In.class);
        startActivity(intent);
        //这里没有将数据库表进行对象化，直接使用语句，这样是不好的
//        DBAdapterImpl dbAdapter=new DBAdapterImpl(getApplicationContext(),"sharenote.db",1,
//                "create table user (id integer primary key autoincrement,name text,password text);");
//        dbAdapter.Open();//打开数据库
//        dbAdapter.Close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load, menu);
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
