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
import org.w3c.dom.Text;

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


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (position % 2 == 0) {
                convertView = inflater.inflate(R.layout.chatroom_self_message, null);
            } else {
                convertView = inflater.inflate(R.layout.chatroom_message, null);
            }

            viewHolder = new ViewHolder();
            viewHolder.messageContent = (TextView) convertView.findViewById(R.id.txtMsg);
            viewHolder.senderNickname = (TextView) convertView.findViewById(R.id.lblMsgFrom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = messages.get(position);

        viewHolder.senderNickname.setText(message.getSenderNickname());
        viewHolder.messageContent.setText(message.getContent());
        viewHolder.userId = message.getSender();

        return convertView;
    }
}
