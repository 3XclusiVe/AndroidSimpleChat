package com.example.user.androidsimplechat.frames;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;


import com.example.user.androidsimplechat.model.Message;

/**
 * Created by user on 07.11.15.
 */
public class ChatRoomFrame extends FrameAttachedToMainActivity
{

    private Button sendButton;
    private EditText inputMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.chat_room_frame, null);

        sendButton = (Button) v.findViewById(R.id.btnSend);
        inputMessage = (EditText) v.findViewById(R.id.inputMsg);


        // находим список
        ListView listView = (ListView) v.findViewById(R.id.chat_room_messages);

        final List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message("hello can you see my message " +
                    "gsdjnsdjngslndgsdnglsdngksdngjksndglksndglksndglksndglksdngslkgn" +
                    " ", "aaa", "bot"));
        }

        // создаем адаптер
        final ChatRoomAdapter adapter = new ChatRoomAdapter(getActivity(), messages);

        // присваиваем адаптер списку
        listView.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Message newMessage = new Message(inputMessage.getText().toString(), "aaa", "me");
                messages.add(newMessage);

                adapter.notifyDataSetChanged();

                // Clearing the input filed once message was sent
                inputMessage.setText("");
            }
        });

        return v;
    }

    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_room);
    }

}
