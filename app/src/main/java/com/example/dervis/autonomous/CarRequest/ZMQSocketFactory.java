package com.example.dervis.autonomous.CarRequest;

import com.example.dervis.autonomous.Objects.SocketObj;

import org.jeromq.ZMQ;

import java.util.HashMap;
import java.util.List;

public class ZMQSocketFactory {

    public static ZMQ.Context zmqContext = null;

    private static ZMQ.Context getZmqContext(){
        if (zmqContext == null)
            zmqContext = ZMQ.context(1);
        return zmqContext;
    }

    public static ZMQ.Socket createPublishSocket(){
        return null;
    }

    public static ZMQ.Socket createSubscribeSocket(byte[] filter){
        ZMQ.Socket socket = getZmqContext().socket(ZMQ.SUB);
        socket.subscribe(filter);
        socket.setRcvHWM(1);
        socket.setReceiveTimeOut(1000);
        return socket;
    }

    public static ZMQ.Socket createReplySocket(){
        return null;
    }

    public static ZMQ.Socket createRequestSocket(){
        ZMQ.Socket socket = getZmqContext().socket(ZMQ.REQ);
        socket.setRcvHWM(1);
        socket.setSndHWM(8);
        socket.setReceiveTimeOut(1000);
        return socket;
    }

    public static HashMap<String, ZMQ.Socket>  createPublishSockets(String[] sockets){
        return null;
    }

    public static HashMap<String, ZMQ.Socket> createSubscribeSockets(List<SocketObj> socketObjs){
        HashMap<String, ZMQ.Socket> socketHashMap = new HashMap<>();
        for (SocketObj socketObj : socketObjs){
            socketHashMap.put(socketObj.socketName, createSubscribeSocket(socketObj.filterBytes));
        }
        return socketHashMap;
    }

    public static HashMap<String, ZMQ.Socket> createReplySockets(int socketType, String[] sockets){
        return null;
    }

    public static HashMap<String, ZMQ.Socket> createRequestSockets(){
        return null;
    }

    //Todo: använd threadpool i main för att executa runnables i som kör socketsarna.
}

