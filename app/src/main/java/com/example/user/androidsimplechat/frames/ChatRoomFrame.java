package com.example.user.androidsimplechat.frames;

import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 07.11.15.
 */
public class ChatRoomFrame extends FrameAttachedToMainActivity
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.chat_room_frame, null);
        return v;
    }

    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_chat_room);
    }
}
