package com.study.neutree.android_study;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.study.neutree.adapter.DBAdapterImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class load extends AppCompatActivity {
    private loadImage mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        //新的线程
//        String url="http://imglf1.nosdn.127.net/img/WFJva2M2cDd0RE9sYk4wYWovNyt2NzhYWWVBUWhpeEY.gif";
        String url="http://img.mukewang.com/user/559b375400016fb801000100-40-40.jpg";
        mTask=new loadImage((ImageView) findViewById(R.id.loadImage),(ProgressBar) findViewById(R.id.progressbar_));

        mTask.execute(url);




        //这里没有将数据库表进行对象化，直接使用语句，这样是不好的
//        DBAdapterImpl dbAdapter=new DBAdapterImpl(getApplicationContext(),"sharenote.db",1,
//                "create table user (id integer primary key autoincrement,name text,password text);");
//        dbAdapter.Open();//打开数据库
//        dbAdapter.Close();
    }
    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(mTask!=null&&mTask.getStatus()==AsyncTask.Status.RUNNING){
            mTask.cancel(true);//cancel方法只是将对应asyncTask标志为cancel状态，线程本身不会停止，在线程中使用isCanseled()函数进行判断，然后退出线程就行了
        }
    }

    class loadImage extends AsyncTask<String,Integer,Bitmap>{
        private ImageView loadImage=null;
        private ProgressBar loadHint=null;
        private ProgressBar loadHint2=null;
        public loadImage(ImageView loadImage, ProgressBar loadHint) {
            this.loadImage = loadImage;
            this.loadHint = loadHint;
            loadHint2= (ProgressBar) findViewById(R.id.progressBar_horizontal);
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        /**
         * <p>Applications should preferably override {@link #onCancelled(Object)}.
         * This method is invoked by the default implementation of
         * {@link #onCancelled(Object)}.</p>
         * <p>
         * <p>Runs on the UI thread after {@link #cancel(boolean)} is invoked and
         * {@link #doInBackground(Object[])} has finished.</p>
         *
         * @see #onCancelled(Object)
         * @see #cancel(boolean)
         * @see #isCancelled()
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("xys", "onCancelled: ");
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param bitmap The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            loadHint.setVisibility(View.GONE);
            loadImage.setImageBitmap(bitmap);
            Intent intent = new Intent(load.this, sign_In.class);
            startActivity(intent);
            finish();
        }

        /**
         * Runs on the UI thread after {@link #publishProgress} is invoked.
         * The specified values are the values passed to {@link #publishProgress}.
         *
         * @param values The values indicating progress.
         * @see #publishProgress
         * @see #doInBackground
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loadHint2.setProgress(values[0]);
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            publishProgress(1);
            String url=params[0];
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;
            publishProgress(2);
            try {
                connection=new URL(url).openConnection();
                publishProgress(10);
                is=connection.getInputStream();
                publishProgress(20);
                BufferedInputStream bis=new BufferedInputStream(is);
                publishProgress(60);
                if(isCancelled()) {
                    is.close();
                    bis.close();
                    return null;
                }
                bitmap= BitmapFactory.decodeStream(bis);
                publishProgress(90);
                if(isCancelled()) {
                    is.close();
                    bis.close();
                    return null;
                }
                is.close();
                bis.close();
                publishProgress(95);
            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress(100);
            return bitmap;
        }
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
