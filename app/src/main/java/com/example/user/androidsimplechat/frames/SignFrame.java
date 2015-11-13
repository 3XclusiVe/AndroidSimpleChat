package com.example.user.androidsimplechat.frames;

import android.widget.Button;
import android.widget.EditText;
import com.example.user.androidsimplechat.MainActivity;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.androidsimplechat.model.Client;

import java.io.IOException;

/**
 * Created by user on 07.11.15.
 */
public class SignFrame extends FrameAttachedToMainActivity
{
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.sign_frame, null);

        final Button button = (Button) v.findViewById(R.id.sign_button);
        final EditText loginEditText = (EditText) v.findViewById(R.id.login);
        final EditText passwordEditText = (EditText) v.findViewById(R.id.password);

        loginEditText.setText("mmmalkin007@mail.r");
        passwordEditText.setText("12345");

        button.setOnClickListener(new View.OnClickListener()

                                  {
                                      public void onClick(View v)
                                      {

                                          String login = loginEditText.getText().toString();
                                          String password = loginEditText.getText().toString();
                                          String nick = "QW12";

                                          RetrieveFeedTask task = new RetrieveFeedTask(mainActivity);
                                          task.execute();

                                          mainActivity.loadFrame(new ChatListFrame());
                                      }
                                  }

        );

        return v;
    }

    @Override
    protected String getActionBarTitle()
    {
        return getString(R.string.title_sign);
    }

    @Override
    protected int getActionBar()
    {
        return R.menu.menu_splash_screen;
    }
}
