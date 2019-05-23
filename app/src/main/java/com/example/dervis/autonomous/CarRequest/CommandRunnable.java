package com.example.dervis.autonomous.CarRequest;

import com.example.dervis.autonomous.Constants.IPAdresses;

import org.jeromq.ZMQ;

import java.util.ArrayList;

public class CommandRunnable implements Runnable {

    public boolean isGoing = true;
    private static ArrayList<byte[]> commandQueue = new ArrayList<>();

    public CommandRunnable() {
    }

    public synchronized void addCommand(byte[] command){
        if (command.length == 3){
            command = new byte[]{command[0], command[1], (byte) 9, (byte) 0, (byte) 0, command[2], (byte) 0x01 };
            commandQueue.add(command);
        }else {
            command = new byte[]{command[0], command[1], (byte) 9, (byte) 0};
            commandQueue.add(command);
        }
    }

    public synchronized byte[] getCommand(){
        if(commandQueue.size() > 0){
            byte[] command = commandQueue.get(0);
            commandQueue.remove(0);
            return command;
        }
        return null;
    }

    public void killThread(){
        isGoing = false;
    }

    @Override
    public void run() {
        while(isGoing){
            byte[] command = getCommand();
            if(command != null){
                ZMQ.Socket socket = getSocket();
                socket.send(command);
                byte[] response = socket.recv();
                socket.close();
            }
            sleep();
        }
    }

    private ZMQ.Socket getSocket(){
        ZMQ.Socket socket = ZMQSocketFactory.createRequestSocket();
        socket.connect("tcp://" + IPAdresses.connectedIp + ":5555");
        return socket;
    }

    private void sleep(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
