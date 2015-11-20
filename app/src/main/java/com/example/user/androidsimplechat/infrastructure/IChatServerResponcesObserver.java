package com.example.user.androidsimplechat.infrastructure;

import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.model.Message;

import java.util.List;

public interface IChatServerResponcesObserver
{
    void onRegister(String status);

    void onAuthorization(String status);

    void onChannelList(List<ChatRoom> rooms);

    void onEnterToChannel(List<Message> messages);

    void onCreateChannel(String status);

    void onUserInfo(Account user);

    void onUserLeaveChannel(Message message);

    void onUserEnterToChannel(Message message);

    void onMessage(Message message);

    void onChangeUserInfo();
}
