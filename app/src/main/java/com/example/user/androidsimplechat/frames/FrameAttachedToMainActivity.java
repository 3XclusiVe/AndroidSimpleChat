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
public abstract class FrameAttachedToMainActivity extends Fragment
{
    protected IFramable mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mainActivity == null) {
            mainActivity = (IFramable) getActivity();
        }

        return null;
    }
}
