package com.study.neutree.android_study;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.study.neutree.adapter.NotesListItem;
import com.study.neutree.model.NoteList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyNotesList extends AppCompatActivity {

    private  String userName=null,userPassword=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes_list);

        Bundle bundle = getIntent().getExtras();
        userName=bundle.getString("userName");
        userPassword=bundle.getString("password");


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
       // listView.setAdapter(adapter2);//应用 simpleadapter
        listView.setAdapter(adapter);//应用自定义的adapter
        //添加点击监听时间
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "itemOnItemClick\n", Toast.LENGTH_SHORT).show();
            }
        });
        //将快捷菜单注册到控件listView上
        registerForContextMenu(listView);
    }


    /**
     * This hook is called whenever an item in a context menu is selected. The
     * default implementation simply returns false to have the normal processing
     * happen (calling the item's Runnable or sending a message to its Handler
     * as appropriate). You can use this method for any items for which you
     * would like to do processing without those other facilities.
     * <p/>
     * Use {@link MenuItem#getMenuInfo()} to get extra information set by the
     * View that added this menu item.
     * <p/>
     * Derived classes should call through to the base class for it to perform
     * the default menu handling.
     *
     * @param item The context menu item that was selected.
     * @return boolean Return false to allow normal context menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch(item.getItemId()) {
            case Menu.FIRST:
                Toast.makeText(getApplicationContext(), "第一个选项选中啦", Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 1:
                Toast.makeText(getApplicationContext(), "第二个选项选中啦", Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 2:
                Toast.makeText(getApplicationContext(), "第三个选项选中啦", Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 3:
                Toast.makeText(getApplicationContext(), "第四个选项选中啦", Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST + 4:
                //Toast.makeText(getApplicationContext(), ((TextView)menuInfo.targetView.findViewById(R.id.noteListItemUser)).getText().toString(), Toast.LENGTH_SHORT).show();
                //save it to system file

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("Articles", Context.MODE_PRIVATE);
                    NoteList noteList=new NoteList( ((com.study.neutree.components.CircleImageView)menuInfo.targetView.findViewById(R.id.noteListItemImage)).getDrawable(),
                            ((TextView)menuInfo.targetView.findViewById(R.id.noteListItemTitle)).getText().toString(),
                            ((TextView)menuInfo.targetView.findViewById(R.id.noteListItemUser)).getText().toString(),
                            ((TextView)menuInfo.targetView.findViewById(R.id.noteListItemLoveNum)).getText().toString(),
                            ((TextView)menuInfo.targetView.findViewById(R.id.noteListItemLastEditTime)).getText().toString()
                            );
                    fos.write((noteList.getTitle() + noteList.getUserName() + noteList.getLoveNum() + noteList.getLastEditTime()).getBytes());    //String类型变量text：是需要保存的内容
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally{
                    if (fos != null){
                        try {
                            fos.flush();
                            fos.close();
                            Toast.makeText(getApplicationContext(),"Save succesfully",Toast.LENGTH_SHORT).show();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
        }
        return false;
    }

    /**
     * Called when a context menu for the {@code view} is about to be shown.
     * Unlike {@link #onCreateOptionsMenu(Menu)}, this will be called every
     * time the context menu is about to be shown and should be populated for
     * the view (or item inside the view for {@link AdapterView} subclasses,
     * this can be found in the {@code menuInfo})).
     * <p/>
     * Use {@link #onContextItemSelected(MenuItem)} to know when an
     * item has been selected.
     * <p/>
     * It is not safe to hold onto the context menu after this method returns.
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("选择操作");
        menu.add(0, Menu.FIRST, 0, "喜欢这条");
        menu.add(0,Menu.FIRST+1,1,"喜欢所有选中的");
        menu.add(0,Menu.FIRST+2,2,"不喜欢这条");
        menu.add(0,Menu.FIRST+3,3,"不喜欢选中的");
        menu.add(0,Menu.FIRST+4,4,"保存这条到本地");

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
        switch (id){
            case R.id.action_NewArticle:
                Intent intent = new Intent(MyNotesList.this, edit.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("userName", userName);
                bundle.putCharSequence("password", userPassword);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.action_settings:
               // Toast.makeText(this, "点击了设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_userProfile:
                Toast.makeText(this, "点击了个人设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_home:
                Toast.makeText(this, "点击了首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Toast.makeText(this, "点击了搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_look_saved_article:
                //Toast.makeText(this, "点击了查看已保存的数据", Toast.LENGTH_SHORT).show();
                //读出数据
                String text = null;
                FileInputStream fis = null;
                try {
                    fis = openFileInput("Articles");
                    if (fis.available() == 0){
                        Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    byte[] readBytes = new byte[fis.available()];
                    while(fis.read(readBytes) != -1);
                    text = new String(readBytes);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }

                    //显示数据
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyNotesList.this);
                    builder.setTitle(getResources().getString(R.string.saved_article));
                    builder.setMessage(text);
//                    builder.setPositiveButton(getResources().getString(R.string.YES), null);
                    builder.show();
                    break;
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
