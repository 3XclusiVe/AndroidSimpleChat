package com.example.user.androidsimplechat.model;

/**
 * Created by user on 06.11.15.
 */
public class Account
{
    private String name;
    private Status status;


    public Account(String name, Status status)
    {
        this.name = name;
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }


}
