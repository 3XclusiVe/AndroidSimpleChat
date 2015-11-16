package com.example.user.androidsimplechat;

import android.app.Fragment;
import android.app.ProgressDialog;
import com.example.user.androidsimplechat.model.Client;

/**
 * Created by user on 14.11.15.
 */
public interface ILoadable
{

    void finishLoading();

    void startLoad();

    void loadFrame(Fragment fragment);

}
