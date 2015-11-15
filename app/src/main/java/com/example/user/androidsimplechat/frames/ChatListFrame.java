package com.example.user.androidsimplechat.frames;

import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import com.example.user.androidsimplechat.ServerClient;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.model.IChatServerResponcesObserver;
import com.example.user.androidsimplechat.model.Message;

import java.io.IOException;
import java.util.*;

/**
 * Created by user on 07.11.15.
 */
public class ChatListFrame extends FrameAttachedToMainActivity
{

    private static ChatListAdapter adapter;
    private static List<ChatRoom> chatRooms;
    private static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.chat_list_frame, null);

        // находим список
        listView = (ListView) v.findViewById(R.id.chat_list);

        //if (chatRooms == null) {
        chatRooms = new ArrayList<ChatRoom>();
        //}
        // создаем адаптер
        adapter = new ChatListAdapter(getActivity(), R.layout.chat_list_element, chatRooms);

        // присваиваем адаптер списку
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ChatListAdapter.ViewHolder viewHolder = (ChatListAdapter.ViewHolder) view.getTag();
                ServerClient.currentChatId = viewHolder.chatId;
                mainActivity.loadFrame(new ChatRoomFrame());
            }
        });

        responceChatList();
    }

    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_list);
    }

    protected void responceChatList()
    {
        try {
            ServerClient.instance.getChatList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChannelList(final List<ChatRoom> rooms)
    {
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {
                chatRooms.clear();

                for (ChatRoom room : rooms) {
                    chatRooms.add(room);
                }
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });

    }


}
