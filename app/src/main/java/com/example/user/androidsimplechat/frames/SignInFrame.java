package com.example.user.androidsimplechat.frames;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.user.androidsimplechat.ILoadable;
import com.example.user.androidsimplechat.R;

/**
 * Created by user on 07.11.15.
 */
public class SignInFrame extends FragmentAtachedToSplashScreen
{


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        changePerspectiveButton.setText("Registration");
        nickNameEditText.setVisibility(View.INVISIBLE);

        return v;
    }

    @Override
    protected boolean isRegistrationFrame()
    {
        return false;
    }


}
