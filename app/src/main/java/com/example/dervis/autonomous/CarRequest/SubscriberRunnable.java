package com.example.dervis.autonomous.CarRequest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.dervis.autonomous.Constants.IPAdresses;
import com.example.dervis.autonomous.Constants.Sockets;

import org.jeromq.ZMQ;

public class SubscriberRunnable implements Runnable {

    private ZMQ.Socket socket;
    public final String socketName;
    private SocketCallback socketCallback;
    private boolean isGoing = true;
    private Handler mainHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            socketCallback.callback(socketName, (byte[]) msg.obj);
        }
    };

    public SubscriberRunnable(ZMQ.Socket socket, String socketName, SocketCallback socketCallback){
        this.socket = socket;
        this.socketName = socketName;
        this.socketCallback = socketCallback;
    }

    public void killThread(){
        isGoing = false;
    }

    @Override
    public void run() {
        socket.connect("tcp://" + IPAdresses.connectedIp + ":5556");
        while (isGoing) {
            byte[] data = socket.recv();
            Log.d("Hello", "rununug in runnable: " + data + " " + socketName);
            if (data != null)
                mainHandler.obtainMessage(1, data).sendToTarget();
            sleep();
        }
    }

    private void sleep(){
        int sleepTime = socketName.equals(Sockets.IMAGE_SOCKET) ? 50 : 2000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
