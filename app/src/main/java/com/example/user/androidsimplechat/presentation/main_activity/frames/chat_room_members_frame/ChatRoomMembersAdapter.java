package com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_members_frame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatMember;
import com.example.user.androidsimplechat.model.Message;

import java.util.List;

/**
 * Created by user on 17.11.15.
 */
public class ChatRoomMembersAdapter extends BaseAdapter
{

    private Context context;
    private List<ChatMember> members;

    public ChatRoomMembersAdapter(Context context, List<ChatMember> members)
    {
        this.context = context;
        this.members = members;
    }

    static class ViewHolder
    {
        protected TextView senderNickname;
        protected String userId;

        public String getUserId()
        {
            return userId;
        }
    }

    @Override
    public int getCount()
    {
        return members.size();
    }

    @Override
    public Object getItem(int position)
    {
        return members.get(position);
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

            convertView = inflater.inflate(R.layout.chat_room_members_element, null);


            viewHolder = new ViewHolder();
            viewHolder.senderNickname = (TextView) convertView.findViewById(R.id.nickname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChatMember member = members.get(position);

        viewHolder.senderNickname.setText(member.getName());
        viewHolder.userId = member.getUserId();

        return convertView;
    }
}