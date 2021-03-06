package com.example.user.androidsimplechat.presentation.main_activity.frames.chat_list_frame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.model.ChatRoom;

import java.util.*;

/**
 * Created by user on 07.11.15.
 */
public class ChatListAdapter extends BaseAdapter
{

    private Context mContext;
    private List<ChatRoom> mData;
    private int mResource;


    public ChatListAdapter(Context context, int resource, List<ChatRoom> objects)
    {
        mContext = context;
        mData = objects;
        mResource = resource;
    }


    static class ViewHolder
    {
        protected TextView chatName;
        protected TextView onlineUserCount;
        protected TextView chatDescription;
        public String chatId;
    }

    @Override
    public ChatRoom getItem(int i)
    {
        return mData.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.chatName = (TextView) convertView.findViewById(R.id.chat_name);
            viewHolder.onlineUserCount = (TextView) convertView.findViewById(R.id.chat_online_user_count);
            viewHolder.chatDescription = (TextView) convertView.findViewById(R.id.chat_description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChatRoom chatRoom = getItem(position);
        viewHolder.chatName.setText(chatRoom.getName());
        viewHolder.onlineUserCount.setText(representOnlineUserCount(chatRoom.getOnlineUserCount()));
        viewHolder.chatDescription.setText(chatRoom.getDescription());
        viewHolder.chatId = chatRoom.getChatId();

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
        return mData.size();
    }

    private String representOnlineUserCount(Integer OnlineUserCount)
    {
        return "( " + OnlineUserCount.toString() + " )";
    }

}
