package com.example.user.androidsimplechat.model;

import java.util.List;

public interface IChatServerResponcesObserver
{
    void onRegister(String status);

    void onAuthorization(String status);

    void onChannelList(List<ChatRoom> rooms);

    void onEnterToChannel(List<Message> messages);

    void onUserInfo(Account user);

    void onUserLeaveChannel(Message message);

    void onUserEnterToChannel(Message message);

    void onMessage(Message message);
}
