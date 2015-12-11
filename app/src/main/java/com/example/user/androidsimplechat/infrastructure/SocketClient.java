package com.example.user.androidsimplechat.infrastructure;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A Simple Socket client that connects to our socket server
 */
public class SocketClient
{
    private String hostname;
    private int port;
    private Socket socketClient;
    public ICallbackable Client;
    private boolean connected;
    private boolean writeLogs = true;

    public SocketClient(String hostname, int port, ICallbackable Client)
    {
        this.hostname = hostname;
        this.port = port;
        this.Client = Client;
    }

    public void connect() throws UnknownHostException, IOException
    {
        log("Attempting to connect to " + hostname + ":" + port);

        //socketClient = new Socket(hostname, port);
        socketClient = new Socket();
        socketClient.connect(new InetSocketAddress(hostname, port), 5 * 1000);

        log("Connected!");

        connected = true;

        submit();
    }

    public void disconnect() throws IOException
    {
        connected = false;
        socketClient.close();
        log("Connection Closed");
    }

    private void submit()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                String incomingMessage = new String();


                InputStream inputStream = null;
                try {
                    inputStream = socketClient.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    while (connected) {
                        if (in.ready()) {
                            int symbol = in.read();
                            incomingMessage += Character.toString((char) symbol);

                            if (isJSONValid(incomingMessage)) {
                                log("SERVER: " + incomingMessage);
                                handleMessage(incomingMessage);
                                incomingMessage = new String();
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void handleMessage(String incomingMessage)
    {
        final String action = "action";
        try {
            JSONObject serverMessage = new JSONObject(incomingMessage);

            String currentAction = serverMessage.getString(action);

            if (currentAction.equals(Protocol.Actions.Registration)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onRegister(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.Authorization)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onAuthorization(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.Channellist)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onChannelList(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.EnterChannel)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onEnterToChannel(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.UserInformation)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onUserInfo(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.LeaveChannel)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onLeaveChannel(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.OnEnter)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.OnUserEnterToChannel(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.OnLeave)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.OnUserLeaveFromChannel(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.OnMessage)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.OnMessage(serverResponce);

            } else if (currentAction.equals(Protocol.Actions.OnCreateChannel)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onCreateChannel(serverResponce);
            } else if (currentAction.equals(Protocol.Actions.OnChangeUserInfo)) {
                JSONObject serverResponce = getResponceData(serverMessage);
                Client.onChangeUserInfo(serverResponce);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getResponceData(JSONObject responce)
    {
        final String data = "data";
        JSONObject responceData = null;

        try {
            responceData = responce.getJSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responceData;
    }

    public boolean isJSONValid(String test)
    {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }


    public void sendHello() throws IOException
    {
        log("CLIENT: " + "Hello");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        writer.flush();
    }

    public void sendRequest(JSONObject message) throws IOException
    {
        String request = message.toString();
        log("CLIENT: " + request);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        writer.write(request);
        writer.flush();
    }

    private void log(String message)
    {
        if (writeLogs) {
            Log.d("logs:", message);
        }
    }

}
