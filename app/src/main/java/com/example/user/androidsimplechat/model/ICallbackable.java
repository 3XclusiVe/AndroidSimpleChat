package com.example.user.androidsimplechat.model;

import org.json.JSONObject;

public interface ICallbackable
{
    void onRegister(JSONObject responce);

    void onAuthorization(JSONObject responce);

    void onChannelList(JSONObject responce);

    void onEnterToChannel(JSONObject responce);

    void onUserInfo(JSONObject responce);

    void onLeaveChannel(JSONObject responce);

    void OnUserLeaveFromChannel(JSONObject responce);

    void OnUserEnterToChannel(JSONObject responce);

    void OnMessage(JSONObject responce);
}
