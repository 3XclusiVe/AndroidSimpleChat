package com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_frame;

/**
 * Created by user on 09.11.15.
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.model.Message;
import com.example.user.androidsimplechat.presentation.ServerClient;

public class ChatRoomAdapter extends BaseAdapter
{

    private Context context;
    private List<Message> messages;
    private int mResource;

    public ChatRoomAdapter(Context context, int resource, List<Message> messages)
    {
        this.mResource = resource;
        this.context = context;
        this.messages = messages;
    }

    static class ViewHolder
    {
        protected TextView senderNickname;
        protected TextView messageContent;
        protected String userId;

        public String getUserId()
        {
            return userId;
        }
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
        ViewHolder viewHolder;

        Message message = messages.get(position);

        String messageUserId = message.getSender();
        int messageStyle;
        if (messageUserId.equals(ServerClient.instance.getSelfUserId())) {
            messageStyle = R.layout.chatroom_self_message;
        } else {
            messageStyle = R.layout.chatroom_message;
        }


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(messageStyle, null);

        viewHolder = new ViewHolder();
        viewHolder.messageContent = (TextView) convertView.findViewById(R.id.txtMsg);
        viewHolder.senderNickname = (TextView) convertView.findViewById(R.id.lblMsgFrom);


        viewHolder.senderNickname.setText(message.getSenderNickname());
        viewHolder.messageContent.setText(message.getContent());
        viewHolder.userId = message.getSender();
        convertView.setTag(viewHolder);

        return convertView;
    }
}
