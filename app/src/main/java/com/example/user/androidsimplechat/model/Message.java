package com.example.user.androidsimplechat.model;


/**
 * Created by user on 06.11.15.
 */
public class Message
{

    private String content;
    private String senderId;
    private String senderNickname;

    public Message(String content, String senderId, String senderNickname)
    {
        this.senderId = senderId;
        this.content = content;
        this.senderNickname = senderNickname;
    }


    public String getSender()
    {
        return senderId;
    }

    public String getSenderNickname()
    {
        return senderNickname;
    }

    public String getContent()
    {
        return content;
    }

    public void print()
    {
        System.out.println(senderNickname + ": " + content);
    }
}
