package com.example.user.androidsimplechat.model;

/**
 * Created by user on 17.11.15.
 */
public class ChatMember
{
    private String name;
    private String status;
    private String userId;


    public ChatMember(String name, String status, String userId)
    {
        this.name = name;
        this.status = status;
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }


    public String getName()
    {
        return name;
    }

    public String getStatus()
    {
        return status;
    }
}
