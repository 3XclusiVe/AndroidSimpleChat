package com.example.user.androidsimplechat.infrastructure;

import android.util.Log;
import com.example.user.androidsimplechat.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

import java.io.IOException;
import java.util.List;

public class Client implements ICallbackable, Serializable
{
    private String selfSessionId = new String();
    private String selfUserId = new String();

    private String selfLogin;
    private String selfPassword;
    private String selfNickname;
    private String currentChatId;

    private final String IP = "188.166.49.215";
    private final int Port = 7777;

    private SocketClient socketClient;
    private List<ChatMember> chatMembers;
    private static List<IChatServerResponcesObserver> observers;

    private Object lock = new Object();

    public Client(String login, String password, String nickname) throws IOException, IllegalArgumentException
    {
        socketClient = new SocketClient(IP, Port, this);

        this.selfLogin = login;
        this.selfPassword = password;
        this.selfNickname = nickname;

        socketClient.connect();
        socketClient.sendHello();

        observers = new ArrayList<IChatServerResponcesObserver>();

        socketClient.sendRequest(Protocol.registration(login, password, nickname));
    }

    public Client(String login, String password, String nickname, IChatServerResponcesObserver observer) throws IOException
    {

        socketClient = new SocketClient(IP, Port, this);

        this.selfLogin = login;
        this.selfPassword = password;
        this.selfNickname = nickname;

        socketClient.connect();
        socketClient.sendHello();

        observers = new ArrayList<IChatServerResponcesObserver>();

        subscribe(observer);

        socketClient.sendRequest(Protocol.registration(login, password, nickname));

    }

    public Client(String login, String password) throws IOException
    {
        socketClient = new SocketClient(IP, Port, this);

        this.selfLogin = login;
        this.selfPassword = password;

        socketClient.connect();
        socketClient.sendHello();

        observers = new ArrayList<IChatServerResponcesObserver>();

        socketClient.sendRequest(Protocol.authorization(login, password));
    }

    public Client(String login, String password, IChatServerResponcesObserver observer) throws IOException
    {

        socketClient = new SocketClient(IP, Port, this);

        this.selfLogin = login;
        this.selfPassword = password;

        socketClient.connect();
        socketClient.sendHello();

        observers = new ArrayList<IChatServerResponcesObserver>();

        subscribe(observer);

        socketClient.sendRequest(Protocol.authorization(login, password));

    }

    public static void subscribe(IChatServerResponcesObserver observer)
    {
        observers.add(observer);
    }

    public void unsubscribe(IChatServerResponcesObserver observer)
    {
        observers.remove(observer);
    }

    public void getChatList() throws IOException
    {
        socketClient.sendRequest(Protocol.chatList(selfUserId, selfSessionId));
    }

    public void getUserInfo(String userId) throws IOException
    {
        socketClient.sendRequest(Protocol.userInfo(userId, selfUserId, selfSessionId));
    }

    public void getSelfUserInfo() throws IOException
    {
        socketClient.sendRequest(Protocol.userInfo(selfUserId, selfUserId, selfSessionId));
    }

    public void enterRoom(String chatId) throws IOException
    {
        currentChatId = chatId;
        socketClient.sendRequest(Protocol.enterChatRoom(selfUserId, selfSessionId, chatId));
    }

    public void sendMessage(String message) throws IOException
    {
        if (currentChatId != null) {
            socketClient.sendRequest(Protocol.sendMessage(selfUserId, selfSessionId, currentChatId, message));
        }
    }

    public void leaveChatRoom() throws IOException
    {
        if (currentChatId != null) {
            socketClient.sendRequest(Protocol.leaveChatRoom(selfUserId, selfSessionId, currentChatId));
            currentChatId = null;
        }
    }

    public void disconnect() throws IOException
    {
        socketClient.disconnect();
    }

    private String getUserId(JSONObject responceData)
    {
        String userId = null;
        try {
            userId = responceData.getString("cid");
        } catch (JSONException e) {
            print(responceData.toString());
            print("unexpected response from the server");
        }
        return userId;
    }

    private String getSessionId(JSONObject responceData)
    {
        String SessionId = null;
        try {
            SessionId = responceData.getString("sid");
        } catch (JSONException e) {
            print(responceData.toString());
            print("unexpected response from the server");
        }
        return SessionId;
    }

    private int getReport(JSONObject responce)
    {
        int error = 0;

        try {
            error = responce.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }

    private String getErrorDescrition(JSONObject responce)
    {

        String error = new String();

        try {
            error = responce.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return error;
    }

    @Override
    public void onRegister(JSONObject responce)
    {
        int error = getReport(responce);

        switch (error) {

            case Status.OK:
                for (IChatServerResponcesObserver observer : observers) {
                    observer.onRegister("OK");
                }
                break;

            default:
                String errorDescription = getErrorDescrition(responce);
                for (IChatServerResponcesObserver observer : observers) {
                    observer.onRegister(errorDescription);
                }
                break;
        }
    }

    @Override
    public void onAuthorization(JSONObject responce)
    {

        int error = getReport(responce);

        switch (error) {

            case Status.OK:
                selfSessionId = getSessionId(responce);
                selfUserId = getUserId(responce);

                for (IChatServerResponcesObserver observer : observers) {
                    observer.onAuthorization("OK");
                }
                break;

            default:
                String errorDecription = getErrorDescrition(responce);
                for (IChatServerResponcesObserver observer : observers) {
                    observer.onAuthorization(errorDecription);
                }
                break;
        }
    }

    @Override
    public void onChannelList(JSONObject responce)
    {
        int error = getReport(responce);

        switch (error) {
            case Status.OK:
                final String channels = "channels";
                List<ChatRoom> chatList = new ArrayList<ChatRoom>();

                JSONArray rooms = null;
                try {
                    rooms = responce.getJSONArray(channels);


                    int length = rooms.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject room = rooms.getJSONObject(i);
                        ChatRoom chatRoom = new ChatRoom(room.getString("name"), room.getInt("online"), room.getString("descr"), room.getString("chid"));

                        chatList.add(chatRoom);

                    }

                    for (IChatServerResponcesObserver observer : observers) {
                        observer.onChannelList(chatList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                print("error");
                break;

        }

    }

    @Override
    public void onEnterToChannel(JSONObject responce)
    {
        int error = getReport(responce);
        switch (error) {
            case Status.OK:
                try {
                    JSONArray users = responce.getJSONArray("users");
                    JSONArray messages = responce.getJSONArray("last_msg");

                    print("users: " + users.toString());
                    print("messages: " + messages.toString());

                    List<Message> lastMessages = new ArrayList<Message>();

                    int lastMessagesCount = messages.length();
                    for (int i = 0; i < lastMessagesCount; i++) {
                        JSONObject jsMessage = messages.getJSONObject(i);

                        String userId = jsMessage.getString("uid");
                        String nick = jsMessage.getString("nick");
                        String content = jsMessage.getString("body");

                        Message message = new Message(content, userId, nick);

                        lastMessages.add(message);

                    }

                    List<ChatMember> chatMembers = new ArrayList<ChatMember>();

                    int chatMembersCount = users.length();
                    for (int i = 0; i < chatMembersCount; i++) {
                        JSONObject jsMember = users.getJSONObject(i);

                        String userId = jsMember.getString("uid");
                        String nick = jsMember.getString("nick");

                        Message chatMemberMessage = new Message("присутствует" + " на канале", userId, nick);
                        lastMessages.add(chatMemberMessage);

                        ChatMember member = new ChatMember(nick, null, userId);

                        chatMembers.add(member);

                    }

                    this.chatMembers = chatMembers;


                    for (IChatServerResponcesObserver observer : observers) {
                        observer.onEnterToChannel(lastMessages);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                print("error");
                break;
        }
    }

    public List<ChatMember> getChatMembersList()
    {
        return chatMembers;
    }

    @Override
    public void onUserInfo(JSONObject responce)
    {
        int error = getReport(responce);
        switch (error) {
            case Status.OK:
                try {
                    String nickName = responce.getString("nick");

                    String status = responce.getString("user_status");

                    Account user = new Account(nickName, status);

                    for (IChatServerResponcesObserver observer : observers) {
                        observer.onUserInfo(user);
                    }

                    user.print();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                print("error");
                break;

        }
    }

    @Override
    public void onLeaveChannel(JSONObject responce)
    {
        int error = getReport(responce);
        switch (error) {
            case Status.OK:
                break;
            default:
                print("error");
                break;
        }
    }

    @Override
    public void OnUserLeaveFromChannel(JSONObject responce)
    {

        try {
            String channelId = responce.getString("chid");

            String userId = responce.getString("uid");
            String nick = responce.getString("nick");

            Message message = new Message("покинул канал", userId, nick);
            message.print();

            for (IChatServerResponcesObserver observer : observers) {
                observer.onUserLeaveChannel(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnUserEnterToChannel(JSONObject responce)
    {

        try {
            String channelId = responce.getString("chid");

            String userId = responce.getString("uid");
            String nick = responce.getString("nick");

            Message message = new Message("зашел на канал", userId, nick);
            message.print();

            for (IChatServerResponcesObserver observer : observers) {
                observer.onUserEnterToChannel(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnMessage(JSONObject responce)
    {
        try {
            String channelId = responce.getString("chid");

            String userId = responce.getString("from");
            String nick = responce.getString("nick");
            String content = responce.getString("body");

            Message message = new Message(content, userId, nick);
            message.print();

            for (IChatServerResponcesObserver observer : observers) {
                observer.onMessage(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static class Status
    {
        public static final int OK = 0;
        public static final int NickAlreadyWasUsed = 1;
        public static final int InvalidLoginOrPassword = 2;
        public static final int InvalidRequest = 3;
        public static final int four = 4;
        public static final int five = 5;
        public static final int six = 6;
        public static final int NeedToRegister = 7;
    }

    private void print(String string)
    {
        Log.d("logs:", string);
    }

}
