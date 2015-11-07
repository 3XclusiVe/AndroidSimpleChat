package com.example.user.androidsimplechat.frames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by user on 07.11.15.
 */
public class ChatListAdapter extends BaseAdapter
{

    private Context mContext;
    private String[] mData;
    private int mResource;
    private int mTextViewResourceId;


    public ChatListAdapter(Context context, int resource, int textViewResourceId, String[] objects)
    {
        mContext = context;
        mData = objects;
        mResource = resource;
        mTextViewResourceId = textViewResourceId;
    }


    static class ViewHolder
    {
        TextView txtItem;
    }

    @Override
    public String getItem(int i)
    {
        return mData[i];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.txtItem = (TextView) convertView.findViewById(mTextViewResourceId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtItem.setText(getItem(position));

        return convertView;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public int getCount()
    {
        return mData.length;
    }

}
