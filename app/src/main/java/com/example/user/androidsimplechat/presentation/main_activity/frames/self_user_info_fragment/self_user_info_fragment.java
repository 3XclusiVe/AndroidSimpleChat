package com.example.user.androidsimplechat.presentation.main_activity.frames.self_user_info_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.presentation.main_activity.frames.base.FrameAttachedToMainActivity;

import java.io.IOException;

/**
 * Created by user on 20.11.15.
 */
public class self_user_info_fragment extends FrameAttachedToMainActivity
{

    private Button saveButton;
    private EditText statusText;
    private TextView errorDescription;
    private TextView userNicknameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.self_user_info_frame, null);

        saveButton = (Button) v.findViewById(R.id.save_button);
        statusText = (EditText) v.findViewById(R.id.user_status);
        userNicknameTextView = (TextView) v.findViewById(R.id.user_nickname);
        errorDescription = (TextView) v.findViewById(R.id.error);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        responceUserInformation();

        saveButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                String status = statusText.getText().toString();

                try {
                    ServerClient.instance.setSelfUserInfo(status);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void responceUserInformation()
    {
        startLoad();
        try {

            ServerClient.instance.getSelfUserInfo();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUserInfo(final Account user)
    {
        finishLoading();
        userNicknameTextView.post(new Runnable()
        {
            @Override
            public void run()
            {
                userNicknameTextView.setText(user.getName());
            }
        });

        errorDescription.post(new Runnable()
        {
            @Override
            public void run()
            {
                errorDescription.setText(user.getStatus());
            }
        });

    }

    @Override
    public void onChangeUserInfo()
    {
        mainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                responceUserInformation();
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

    }
}
