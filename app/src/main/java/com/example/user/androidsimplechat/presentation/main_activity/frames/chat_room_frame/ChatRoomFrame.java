package com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_frame;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.*;


import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.model.Message;
import com.example.user.androidsimplechat.presentation.main_activity.frames.base.FrameAttachedToMainActivity;
import com.example.user.androidsimplechat.presentation.main_activity.frames.UserInfoFrame;

/**
 * Created by user on 07.11.15.
 */
public class ChatRoomFrame extends FrameAttachedToMainActivity
{

    private static Button sendButton;
    private static EditText inputMessage;
    private static ListView listView;
    private static List<Message> messages;
    private static ChatRoomAdapter adapter;

    private static String currentChatId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.chat_room_frame, null);


        sendButton = (Button) v.findViewById(R.id.btnSend);
        inputMessage = (EditText) v.findViewById(R.id.inputMsg);


        // находим список
        listView = (ListView) v.findViewById(R.id.chat_room_messages);

        messages = new ArrayList<Message>();


        // создаем адаптер
        adapter = new ChatRoomAdapter(getActivity(), R.layout.chatroom_message, messages);


        // присваиваем адаптер списку
        listView.setAdapter(adapter);


        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        responceLastMessages();

        sendButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                try {
                    ServerClient.instance.sendMessage(inputMessage.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Clearing the input filed once message was sent
                inputMessage.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ChatRoomAdapter.ViewHolder viewHolder = (ChatRoomAdapter.ViewHolder) view.getTag();

                String argKey = "userId";
                String arg = viewHolder.userId;
                mainActivity.loadFrame(newInstance(new UserInfoFrame(), argKey, arg));
            }
        });

    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        try {
            ServerClient.instance.leaveChatRoom();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responceLastMessages()
    {
        startLoad();
        try {
            ServerClient.instance.enterRoom(currentChatId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_room);
    }

    @Override
    protected int getActionBar()
    {
        return R.menu.chat_room_menu;
    }

    @Override
    protected void onReceiveArgument(Bundle args)
    {
        currentChatId = args.getString("chatId");
    }

    @Override
    public void onEnterToChannel(final List<Message> inputMessages)
    {
        finishLoading();
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {
                messages.clear();

                for (Message message : inputMessages) {
                    messages.add(message);
                }

                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });
    }

    @Override
    public void onUserLeaveChannel(final Message inputMessage)
    {
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {


                messages.add(inputMessage);


                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });
    }

    @Override
    public void onUserEnterToChannel(final Message message)
    {
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {


                messages.add(message);


                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });
    }

    @Override
    public void onMessage(final Message message)
    {
        listView.post(new Runnable()
        {
            @Override
            public void run()
            {


                messages.add(message);


                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });
    }

}
