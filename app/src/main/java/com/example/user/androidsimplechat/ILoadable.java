package com.example.user.androidsimplechat;

import com.example.user.androidsimplechat.model.Client;

/**
 * Created by user on 14.11.15.
 */
public interface ILoadable
{
    void loadNextActivity();

    void onSuccessToConnect();

    void onFailToCoonnect();

}
