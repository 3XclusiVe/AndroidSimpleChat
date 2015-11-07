package com.example.user.androidsimplechat.frames;

import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import com.example.user.androidsimplechat.model.ChatRoom;

/**
 * Created by user on 07.11.15.
 */
public class ChatListFrame extends FrameAttachedToMainActivity
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.chat_list_frame, null);

        // находим список
        ListView listView = (ListView) v.findViewById(R.id.chat_list);

        ChatRoom[] chatRooms = new ChatRoom[10];
        for (int i = 0; i < 10; i++) {
            chatRooms[i] = new ChatRoom("name", 10, "chat");
        }

        // создаем адаптер
        ChatListAdapter adapter = new ChatListAdapter(getActivity(), R.layout.chat_list_element, chatRooms);

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

}
