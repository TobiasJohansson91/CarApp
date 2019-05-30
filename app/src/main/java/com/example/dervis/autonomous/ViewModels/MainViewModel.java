package com.example.dervis.autonomous.ViewModels;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.dervis.autonomous.CarRequest.ByteConverter;
import com.example.dervis.autonomous.CarRequest.CommandRunnable;
import com.example.dervis.autonomous.CarRequest.SocketCallback;
import com.example.dervis.autonomous.CarRequest.SubscriberRunnable;
import com.example.dervis.autonomous.CarRequest.ZMQSocketFactory;
import com.example.dervis.autonomous.Constants.ConnectedStatus;
import com.example.dervis.autonomous.Constants.Filters;
import com.example.dervis.autonomous.Constants.IPAdresses;
import com.example.dervis.autonomous.Constants.Sockets;
import com.example.dervis.autonomous.ICommandCallback;
import com.example.dervis.autonomous.Objects.BatteryObj;
import com.example.dervis.autonomous.Objects.ConnectedSocketObj;
import com.example.dervis.autonomous.Objects.LidarObj;
import com.example.dervis.autonomous.Objects.SocketObj;
import com.example.dervis.autonomous.Objects.SonarObj;
import com.example.dervis.autonomous.Objects.WheelSpeedObj;
import com.example.dervis.autonomous.Ping;

import org.jeromq.ZMQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends android.arch.lifecycle.AndroidViewModel implements SocketCallback, ICommandCallback {

    private MutableLiveData<WheelSpeedObj> speed = new MutableLiveData<>();
    private MutableLiveData<BatteryObj> battery  = new MutableLiveData<>();
    private MutableLiveData<Bitmap> image  = new MutableLiveData<>();
    private MutableLiveData<Integer> compass = new MutableLiveData<>();
    private MutableLiveData<LidarObj> lidar  = new MutableLiveData<>();
    private MutableLiveData<SonarObj> sonar  = new MutableLiveData<>();
    private MutableLiveData<ConnectedSocketObj> connectedSocket = new MutableLiveData<>();

    private ExecutorService threadPool = Executors.newFixedThreadPool(6, Executors.defaultThreadFactory());
    private HashMap<String, ZMQ.Socket> subSockets = new HashMap<>();
    private ArrayList<SubscriberRunnable> runningProcesses = new ArrayList<>();
    private CommandRunnable commandRunnable;
    private List<SocketObj> socketObjList = new ArrayList<>();
    private HashMap<String, Boolean> connectedSockets;

    public MainViewModel(@NonNull Application application) {
        super(application);
        commandRunnable = new CommandRunnable();
        connectedSockets = ConnectedStatus.connectedSockets;
    }

    public void startDataGathering(List<SocketObj> socketObjs){
        socketObjList = socketObjs;
        createSubSockets(socketObjs);
        startSubSockets();
    }

    public void createSubSockets(List<SocketObj> socketObjs){
        subSockets = ZMQSocketFactory.createSubscribeSockets(socketObjs);
    }

    private void startSubSockets(){
        for (Map.Entry<String, ZMQ.Socket> entry : subSockets.entrySet()) {
            threadExists(entry.getKey());
            SubscriberRunnable runnable = new SubscriberRunnable(entry.getValue(), entry.getKey(), this);
            runningProcesses.add(runnable);
            threadPool.execute(runnable);
        }
    }

    private void threadExists(String socketName){
        for (SubscriberRunnable runnable : runningProcesses) {
            if (runnable.socketName.equals(socketName)) {
                runnable.killThread();
                runningProcesses.remove(runnable);
            }
        }
    }

    public void reconnectSockets(){
        for (SubscriberRunnable runnable : runningProcesses) {
            runnable.killThread();
        }
        startDataGathering(socketObjList);
    }

    @Override
    public void callback(String socketType, byte[] data) {
        boolean connected = data != Filters.NO_CONNECTION;
        setConnectedSockets(socketType, connected);
        if (connected){
            switch (socketType) {
                case Sockets.IMAGE_SOCKET_STRING:
                    Bitmap bitmap = ByteConverter.image(data);
                    getImage().setValue(bitmap);
                    break;
                case Sockets.BATTERY_SOCKET_STRING:
                    BatteryObj batteryObj = ByteConverter.battery(data);
                    battery.setValue(batteryObj);
                    break;
                case Sockets.COMPASS_SOCKET_STRING:
                    int compa = ByteConverter.compass(data);
                    compass.setValue(compa);
                    break;
                case Sockets.LIDAR_SOCKET_STRING:
                    LidarObj lidarObj = ByteConverter.lidar(data);
                    lidar.setValue(lidarObj);
                    break;
                case Sockets.SONAR_SOCKET_STRING:
                    SonarObj sonarObj = ByteConverter.sonar(data);
                    sonar.setValue(sonarObj);
                    break;
                case Sockets.SPEED_SOCKET_STRING:
                    WheelSpeedObj speedObj = ByteConverter.speed(data);
                    speed.setValue(speedObj);
                    break;
                default:
                    break;
            }
        }
    }

    private void setConnectedSockets(String socketName, boolean connected) {
        ConnectedSocketObj connectedSocketObj = new ConnectedSocketObj(socketName, connected);
        boolean currentStatus = connectedSockets.get(socketName);
        if (currentStatus != connected){
            connectedSockets.put(socketName, connected);
            connectedSocket.setValue(connectedSocketObj);
        }
    }

    public MutableLiveData<WheelSpeedObj> getSpeed() {
        if (speed == null) {
            speed = new MutableLiveData<WheelSpeedObj>();
        }
        return speed;
    }

    public MutableLiveData<BatteryObj> getBattery() {
        if (battery == null)
            battery = new MutableLiveData<BatteryObj>();
        return battery;
    }

    public MutableLiveData<Bitmap> getImage() {
        if (image == null)
            image = new MutableLiveData<Bitmap>();
        return image;
    }

    public MutableLiveData<Integer> getCompass() {
        if (compass == null)
            compass = new MutableLiveData<>();
        return compass;
    }

    public MutableLiveData<LidarObj> getLidar() {
        if (lidar == null)
            lidar = new MutableLiveData<>();
        return lidar;
    }

    public MutableLiveData<SonarObj> getSonar() {
        if (sonar == null)
            lidar = new MutableLiveData<>();
        return sonar;
    }

    public MutableLiveData<ConnectedSocketObj> getConnectedSockets() {
        if (connectedSocket == null)
            connectedSocket = new MutableLiveData<>();
        return connectedSocket;
    }

    public void connectToIp(String ip){
        Ping.ping(ip, this);
    }

    @Override
    public void callbackPing(boolean connected, String ip) {
        String text = connected ? "Connected" : "Not Connected";
        Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
        if (connected) {
            IPAdresses.connectedIp = ip;
            reconnectSockets();
        }
    }

    public void startCommandThread(){
        threadPool.execute(commandRunnable);
    }

    public void addCommand(byte[] command){
        commandRunnable.addCommand(command);
    }

    public void killCommandThread(){
        commandRunnable.killThread();
    }

    public void killSubscriberThreads(){
        for (SubscriberRunnable subRunnable : runningProcesses) {
            subRunnable.killThread();
        }
    }
}