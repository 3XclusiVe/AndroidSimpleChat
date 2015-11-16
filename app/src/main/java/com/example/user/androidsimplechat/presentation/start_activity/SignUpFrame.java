package com.example.user.androidsimplechat.presentation.start_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.androidsimplechat.R;

/**
 * Created by user on 07.11.15.
 */
public class SignUpFrame extends FragmentAtachedToSplashScreen
{

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        changePerspectiveButton.setText(R.string.go_authorization_button_text);

        return v;
    }

    @Override
    protected boolean isRegistrationFrame()
    {
        return true;
    }


}
