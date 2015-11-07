package com.example.user.androidsimplechat.frames;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.user.androidsimplechat.IFramable;
import com.example.user.androidsimplechat.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        String[] strings = new String[3];
        for (int i = 0; i < 3; i++) {
            strings[i] = new Integer(i).toString();
        }

        // создаем адаптер
        ChatListAdapter adapter = new ChatListAdapter(getContext(), R.layout.chat_list_element, R.id.chat_name, strings);

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

    public void OnActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

}
