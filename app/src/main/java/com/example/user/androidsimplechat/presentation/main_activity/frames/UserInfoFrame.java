package com.example.user.androidsimplechat.presentation.main_activity.frames;

import android.widget.TextView;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.presentation.main_activity.frames.base.FrameAttachedToMainActivity;

import java.io.IOException;

/**
 * Created by user on 07.11.15.
 */
public class UserInfoFrame extends FrameAttachedToMainActivity
{
    private TextView userName;
    private TextView userStatus;
    private static String currentUserId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.user_info_frame, null);

        userName = (TextView) v.findViewById(R.id.user_name);
        userStatus = (TextView) v.findViewById(R.id.user_status);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        responceUserInformation();
    }

    private void responceUserInformation()
    {
        startLoad();
        try {
            ServerClient.instance.getUserInfo(currentUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUserInfo(final Account user)
    {
        finishLoading();
        userName.post(new Runnable()
        {
            @Override
            public void run()
            {
                userName.setText(user.getName());
            }
        });

        userStatus.post(new Runnable()
        {
            @Override
            public void run()
            {
                userStatus.setText(user.getStatus());
            }
        });

    }


    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_user_info);
    }

    @Override
    protected void onReceiveArgument(Bundle args)
    {
        currentUserId = args.getString("userId");
    }

    @Override
    protected int getActionBar()
    {
        return R.menu.user_info_menu;
    }
}
