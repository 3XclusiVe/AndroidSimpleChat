package com.example.user.androidsimplechat.model;

/**
 * Created by user on 06.11.15.
 */
public class ChatRoom
{
    private String name;
    private int onlineUserCount;
    private String description;
    private String chatId;

    public ChatRoom(String name, int onlineUserCount, String description, String chatId)
    {
        this.name = name;
        this.onlineUserCount = onlineUserCount;
        this.description = description;
        this.chatId = chatId;
    }

    public void print()
    {
        System.out.print("_\n" + name + " " + onlineUserCount + "\n" +
                description + "\n");
    }

    public String getName()
    {
        return name;
    }

    public int getOnlineUserCount()
    {
        return onlineUserCount;
    }

    public String getDescription()
    {
        return description;
    }

    public String getChatId()
    {
        return chatId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOnlineUserCount(int onlineUserCount)
    {
        this.onlineUserCount = onlineUserCount;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
