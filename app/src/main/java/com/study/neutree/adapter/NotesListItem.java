package com.study.neutree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.neutree.android_study.R;
import com.study.neutree.model.NoteList;

import java.util.List;

/**
 * Created by Neutree on 2015-10-30.
 */
public class NotesListItem extends BaseAdapter{

    private List<NoteList> mNoteList;
    private Context mContext;
    private LayoutInflater inflater;

    public NotesListItem( Context mContext,List<NoteList> mNoteList) {
        this.mNoteList = mNoteList;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mNoteList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mNoteList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataWraper dataWraper;

        TextView title,user,loveNum,lastEditTime;
        ImageView itemPicture;

        if(convertView==null){
            convertView=inflater.inflate(R.layout.note_list_item,null);
            itemPicture= (ImageView) convertView.findViewById(R.id.noteListItemImage);
            title= (TextView) convertView.findViewById(R.id.noteListItemTitle);
            user= (TextView) convertView.findViewById(R.id.noteListItemUser);
            loveNum= (TextView) convertView.findViewById(R.id.noteListItemLoveNum);
            lastEditTime= (TextView) convertView.findViewById(R.id.noteListItemLastEditTime);

            dataWraper=new DataWraper(title,user,loveNum,lastEditTime,itemPicture);
            convertView.setTag(dataWraper);
        }
        else{
            dataWraper= (DataWraper) convertView.getTag();
            itemPicture=dataWraper.mItemPicture;
            user=dataWraper.mUserName;
            title=dataWraper.mTitle;
            loveNum=dataWraper.mLoveNum;
            lastEditTime=dataWraper.mLastEditTime;
        }
        itemPicture.setImageDrawable(mNoteList.get(position).getItemImage());
        title.setText(mNoteList.get(position).getTitle());
        user.setText(mNoteList.get(position).getUserName());
        loveNum.setText(mNoteList.get(position).getLoveNum());
        lastEditTime.setText(mNoteList.get(position).getLastEditTime().toString());
        return convertView;
    }

    public final class DataWraper{
        public TextView mTitle;
        public TextView mUserName;
        public TextView mLoveNum;
        public TextView mLastEditTime;
        public ImageView mItemPicture;

        public DataWraper(TextView mTitle, TextView mUserName, TextView mLoveNum, TextView mLastEditTime, ImageView mItemPicture) {
            this.mTitle = mTitle;
            this.mUserName = mUserName;
            this.mLoveNum = mLoveNum;
            this.mLastEditTime = mLastEditTime;
            this.mItemPicture = mItemPicture;
        }
    }
}
