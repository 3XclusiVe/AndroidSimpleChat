package com.example.user.androidsimplechat.model;

import android.accounts.Account;

/**
 * Created by user on 06.11.15.
 */
public class Message
{

    private String content;
    private Account sender;

    public Message(String content, Account sender)
    {
        this.sender = sender;
        this.content = content;
    }


    public Account getSender()
    {
        return sender;
    }

    public String getContent()
    {
        return content;
    }
}
