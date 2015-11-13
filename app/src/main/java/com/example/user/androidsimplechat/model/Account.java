package com.example.user.androidsimplechat.model;

/**
 * Created by user on 06.11.15.
 */
public class Account
{
    private String name;
    private String status;


    public Account(String name, String status)
    {
        this.name = name;
        this.status = status;
    }

    public void print()
    {
        System.out.println("__");
        System.out.println("name: " + name);
        System.out.println("status: " + status);
        System.out.println("==");
    }

    public String getName()
    {
        return name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
