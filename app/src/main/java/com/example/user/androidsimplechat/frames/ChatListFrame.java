package com.example.user.androidsimplechat.frames;

import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.model.IChatServerResponcesObserver;
import com.example.user.androidsimplechat.model.Message;

import java.io.IOException;
import java.util.*;

/**
 * Created by user on 07.11.15.
 */
public class ChatListFrame extends FrameAttachedToMainActivity implements IChatServerResponcesObserver
{

    private ChatListAdapter adapter;
    private List<ChatRoom> chatRooms;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.chat_list_frame, null);

        // находим список
        listView = (ListView) v.findViewById(R.id.chat_list);

        chatRooms = new ArrayList<ChatRoom>();

        for (int i = 0; i < 10; i++) {
            chatRooms.add(new ChatRoom("name", 10, "chat", "c"));
        }

        // создаем адаптер
        adapter = new ChatListAdapter(getActivity(), R.layout.chat_list_element, chatRooms);

        // присваиваем адаптер списку
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mainActivity.loadFrame(new ChatRoomFrame());
            }
        });

        return v;
    }

    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_list);
    }

    protected void responceChatList()
    {
        try {
            mainActivity.getClient().getChatList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRegister(String status)
    {

    }

    @Override
    public void onAuthorization(String status)
    {
        mainActivity.getClient().subscribe(this);
        responceChatList();
    }

    @Override
    public void onChannelList(final List<ChatRoom> rooms)
    {
        Log.d("111", "1111");
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {
                chatRooms.clear();
                Log.d("111", rooms.get(0).getDescription());
                chatRooms.add(rooms.get(0));
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });

    }


    @Override
    public void onEnterToChannel(List<Message> messages)
    {

    }

    @Override
    public void onUserInfo(Account user)
    {

    }

    @Override
    public void onUserLeaveChannel(Message message)
    {

    }

    @Override
    public void onUserEnterToChannel(Message message)
    {

    }

    @Override
    public void onMessage(Message message)
    {

    }
}
