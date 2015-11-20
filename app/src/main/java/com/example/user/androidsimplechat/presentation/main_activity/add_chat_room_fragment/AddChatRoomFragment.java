package com.example.user.androidsimplechat.presentation.main_activity.add_chat_room_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.infrastructure.Protocol;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.presentation.ILoadable;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.presentation.main_activity.frames.base.FrameAttachedToMainActivity;
import com.example.user.androidsimplechat.presentation.main_activity.frames.chat_list_frame.ChatListFrame;

import java.io.IOException;


/**
 * Created by user on 20.11.15.
 */
public class AddChatRoomFragment extends FrameAttachedToMainActivity
{

    private Button createButton;
    private EditText chatNameText;
    private EditText chatDescriptionText;
    private TextView errorDescription;
    private String error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.add_chat_fragment, null);

        createButton = (Button) v.findViewById(R.id.create_chat_button);
        chatNameText = (EditText) v.findViewById(R.id.chat_name);
        chatDescriptionText = (EditText) v.findViewById(R.id.chat_description);
        errorDescription = (TextView) v.findViewById(R.id.error);

        errorDescription.setText(error);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        createButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                String chatName = chatNameText.getText().toString();
                String chatDescription = chatDescriptionText.getText().toString();

                try {
                    startLoad();
                    ServerClient.instance.createChatRoom(chatName, chatDescription);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onCreateChannel(String status)
    {
        finishLoading();
        if (status.equals("OK")) {
            mainActivity.loadFrame(new ChatListFrame());
        } else {
            recreate(status);
        }
    }


    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.add_chat_room_title);
    }

    @Override
    protected void onReceiveArgument(Bundle args)
    {

    }

    private void recreate(String reason)
    {

        Fragment fragment = new AddChatRoomFragment();

        Bundle args = new Bundle();
        args.putString("error", reason);
        fragment.setArguments(args);

        mainActivity.loadFrame(fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.error = "";
        Bundle args = getArguments();
        if (args != null) {
            String error = args.getString("error");
            if (error != null) {
                this.error = error;
            }
        }
    }
}
