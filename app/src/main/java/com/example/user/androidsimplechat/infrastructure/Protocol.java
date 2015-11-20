package com.example.user.androidsimplechat.infrastructure;

import org.json.*;

public class Protocol
{
    public static String action = "action";
    public static String data = "data";

    public static class Actions
    {
        public static final String Authorization = "auth";
        public static final String Registration = "register";
        public static final String Channellist = "channellist";
        public static final String OnCreateChannel = "createchannel";
        public static final String EnterChannel = "enter";
        public static final String UserInformation = "userinfo";
        public static final String LeaveChannel = "leave";
        public static final String OnEnter = "ev_enter";
        public static final String OnLeave = "ev_leave";
        public static final String OnMessage = "ev_message";
        public static final String OnChangeUserInfo = "setuserinfo";
    }

    ;


    public static JSONObject registration(String login, String password, String nickname)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "register";


            dataJsonObject.put("login", login);
            dataJsonObject.put("pass", password);
            dataJsonObject.put("nick", nickname);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject authorization(String login, String password)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "auth";

            dataJsonObject.put("login", login);
            dataJsonObject.put("pass", password);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject chatList(String userId, String sessionId)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "channellist";


            dataJsonObject.put("cid", userId);
            dataJsonObject.put("sid", sessionId);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject userInfo(String userId, String selfUserId, String selfSessionId)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "userinfo";

            dataJsonObject.put("user", userId);
            dataJsonObject.put("cid", selfUserId);
            dataJsonObject.put("sid", selfSessionId);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject enterChatRoom(String cid, String sid, String channelId)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "enter";

            dataJsonObject.put("cid", cid);
            dataJsonObject.put("sid", sid);
            dataJsonObject.put("channel", channelId);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject createChatRoom(String cid, String sid, String channelName, String channelDescription)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "createchannel";

            dataJsonObject.put("cid", cid);
            dataJsonObject.put("sid", sid);
            dataJsonObject.put("name", channelName);
            dataJsonObject.put("descr", channelDescription);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject changeUserInfo(String cid, String sid, String status)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "setuserinfo";

            dataJsonObject.put("cid", cid);
            dataJsonObject.put("sid", sid);
            dataJsonObject.put("user_status", status);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject leaveChatRoom(String cid, String sid, String channelId)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "leave";

            dataJsonObject.put("cid", cid);
            dataJsonObject.put("sid", sid);
            dataJsonObject.put("channel", channelId);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

    public static JSONObject sendMessage(String cid, String sid, String channelId, String message)
    {
        JSONObject actionJsonObject = new JSONObject();

        JSONObject dataJsonObject = new JSONObject();
        try {
            String currentAction = "message";

            dataJsonObject.put("cid", cid);
            dataJsonObject.put("sid", sid);
            dataJsonObject.put("channel", channelId);
            dataJsonObject.put("body", message);


            actionJsonObject.put(action, currentAction);
            actionJsonObject.put(data, dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actionJsonObject;
    }

}
