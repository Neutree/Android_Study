package com.study.neutree.android_study;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.study.neutree.adapter.NotesListItem;
import com.study.neutree.model.NoteList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MyNotesList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes_list);

        ListView listView= (ListView) findViewById(R.id.MyNotesListView);
        List<NoteList> listForum = new ArrayList<NoteList>();
        listForum.add(new NoteList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.head_pic), "Android Listview 的使用总结",
                getResources().getString(R.string.User) + getResources().getString(R.string.colon) + "Neutree",
                getResources().getString(R.string.Love) + getResources().getString(R.string.colon) + "120",
                getResources().getString(R.string.LastEditDateTime) + getResources().getString(R.string.colon) + "2015-10-28 10:10"));
        listForum.add(new NoteList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.head_pic),"Android 自定义xml  的使用总结",
                getResources().getString(R.string.User)+getResources().getString(R.string.colon)+"Neutree",
                getResources().getString(R.string.Love)+getResources().getString(R.string.colon)+"150",
                getResources().getString(R.string.LastEditDateTime)+getResources().getString(R.string.colon)+"2015-10-28 10:10"));

        listForum.add(new NoteList(ContextCompat.getDrawable(getApplicationContext(), R.drawable.head_pic), "Android Adapter 的使用总结",
                getResources().getString(R.string.User) + getResources().getString(R.string.colon) + "Neutree",
                getResources().getString(R.string.Love) + getResources().getString(R.string.colon) + "885",
                getResources().getString(R.string.LastEditDateTime) + getResources().getString(R.string.colon) + "2015-10-28 10:10"));

        NotesListItem adapter=new NotesListItem(this,listForum);

        //simpleadapter的使用
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> data1=new HashMap<String,Object>();
        data1.put("image",R.drawable.head_pic);
        data1.put("title","啊哈哈哈哈哈");
        data1.put("user", "neucrack");
        data1.put("love", "100");
        data1.put("date", "2015-11-13");
        mapList.add(data1);

        Map<String,Object> data2=new HashMap<String,Object>();
        data2.put("image",R.drawable.head_pic);
        data2.put("title","啊啊啊啊啊啊啊啊");
        data2.put("user", "neucrack");
        data2.put("love", "50000");
        data2.put("date", "2015-11-13");
        mapList.add(data2);

        //simpleAdapter 的好处就是简单，缺点就是只能为整个item添加点击事件，自定义的item还可以为item里面的控件添加事件
        SimpleAdapter adapter2=new SimpleAdapter(this,
                mapList,R.layout.note_list_item,new String[]{"image","title","user","love","date"},
                new int[]{R.id.noteListItemImage,R.id.noteListItemUser,R.id.noteListItemLoveNum,
                        R.id.noteListItemLastEditTime});
        listView.setAdapter(adapter2);//应用 simpleadapter
       // listView.setAdapter(adapter);//应用自定义的adapter
        //添加点击监听时间
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"itemOnItemClick\n",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_notes_list, menu);
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
