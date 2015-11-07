package com.example.user.androidsimplechat.frames;

import com.example.user.androidsimplechat.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 07.11.15.
 */
public class UserInfoFrame extends FrameAttachedToMainActivity
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.user_info_frame, null);
        return v;
    }
}
