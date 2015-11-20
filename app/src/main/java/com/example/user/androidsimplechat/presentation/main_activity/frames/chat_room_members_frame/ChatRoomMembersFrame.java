package com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_members_frame;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.*;


import com.example.user.androidsimplechat.model.ChatMember;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.model.Message;
import com.example.user.androidsimplechat.presentation.main_activity.frames.base.FrameAttachedToMainActivity;
import com.example.user.androidsimplechat.presentation.main_activity.frames.UserInfoFrame;
import com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_frame.ChatRoomAdapter;

/**
 * Created by user on 07.11.15.
 */
public class ChatRoomMembersFrame extends FrameAttachedToMainActivity
{
    private static ListView listView;
    private static List<ChatMember> chatMembers;
    private static ChatRoomMembersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.chat_room_members, null);


        // находим список
        listView = (ListView) v.findViewById(R.id.chat_room_messages);

        chatMembers = new ArrayList<ChatMember>();


        // создаем адаптер
        adapter = new ChatRoomMembersAdapter(getActivity(), chatMembers);


        // присваиваем адаптер списку
        listView.setAdapter(adapter);


        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        responceMembers();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ChatRoomMembersAdapter.ViewHolder viewHolder = (ChatRoomMembersAdapter.ViewHolder) view.getTag();

                String argKey = "userId";
                String arg = viewHolder.getUserId();
                mainActivity.loadFrame(newInstance(new UserInfoFrame(), argKey, arg));
            }
        });

    }


    private void responceMembers()
    {
        startLoad();
        onRecieveChatMembers();

    }

    private void onRecieveChatMembers()
    {
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {
                chatMembers = ServerClient.instance.getChatMembersList();
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
                finishLoading();
            }
        });
    }

    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_room_members);
    }

    @Override
    protected void onReceiveArgument(Bundle args)
    {

    }

    @Override
    protected int getActionBar()
    {
        return R.menu.fragment_menu;
    }

}
