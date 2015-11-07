package com.example.user.androidsimplechat.frames;

import android.widget.Button;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 07.11.15.
 */
public class SignFrame extends FrameAttachedToMainActivity
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.sign_frame, null);

        Button button = (Button) v.findViewById(R.id.sign_button);
        button.setOnClickListener(new View.OnClickListener()

                                  {
                                      public void onClick(View v)
                                      {
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
}
