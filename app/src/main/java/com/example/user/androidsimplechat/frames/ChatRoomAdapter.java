package com.example.user.androidsimplechat.frames;

/**
 * Created by user on 09.11.15.
 */

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.model.Message;

public class ChatRoomAdapter extends BaseAdapter
{

    private Context context;
    private List<Message> messages;

    public ChatRoomAdapter(Context context, List<Message> messages)
    {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount()
    {
        return messages.size();
    }

    @Override
    public Object getItem(int position)
    {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        Message message = messages.get(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (position % 2 == 0) {
            convertView = mInflater.inflate(R.layout.chatroom_self_message, null);
        } else {
            convertView = mInflater.inflate(R.layout.chatroom_message, null);
        }

        TextView messageSenderName = (TextView) convertView.findViewById(R.id.lblMsgFrom);
        TextView messageContent = (TextView) convertView.findViewById(R.id.txtMsg);

        messageContent.setText(message.getContent());
        messageSenderName.setText(message.getSenderNickname());

        return convertView;
    }
}
