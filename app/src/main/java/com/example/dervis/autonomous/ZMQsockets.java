package com.example.dervis.autonomous;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.jeromq.ZMQ;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ZMQsockets extends AsyncTask<Void, Void, byte[]> {

    private int SENS = 0x30;
    private int SENS_LIDAR = 0x01;
    private int SENS_PIC = 0x06;
    private int SENS_P_BATT = 0x09;
    private int SENS_TURNLIGHTS = 0x0B;
    private int SENS_ODOMETER = 0x0C;
    private int SENS_TRIPMETER = 0x0D;

    public ZMQsockets(){
        Log.d("Hello", "creating ZMQSocket");
        //this.execute();
    }

    @Override
    protected byte[] doInBackground(Void... voids) {
        Log.d("Hello", "doInBackground: ");
        byte[] filter = new byte[] {(byte)SENS, (byte)SENS_PIC};
        ZMQ.Context zmqcontext = ZMQ.context(1);
        ZMQ.Socket socket = zmqcontext.socket(ZMQ.SUB);
        socket.subscribe(filter);
        socket.setRcvHWM(1);
        socket.setReceiveTimeOut(1000);
        socket.connect("tcp://192.168.150.133:5556");
        byte data[] = socket.recv();

        Log.d("Hello", "doInBackground: " + filter);
        socket.close();
        zmqcontext.term();
        return data;
    }

    @Override
    protected void onPostExecute(byte[] bytearray) {
        super.onPostExecute(bytearray);
        if (bytearray != null){
            String message = new String(bytearray);
            Log.d("got message", "ZMQsockets: " + message);

            byte[] bytearray2 = Arrays.copyOfRange(bytearray, 4, 8 );
            int meter = ByteBuffer.wrap(bytearray2).getInt(); // big-endian by default

            byte[] bytes2 = ByteBuffer.allocate(4).putInt(2323).array();
            int ii = ByteBuffer.wrap(bytes2).getInt();

            byte[] imagebytearray = Arrays.copyOfRange(bytearray, 4,  bytearray.length);
            Bitmap bmp = BitmapFactory.decodeByteArray(imagebytearray, 0, imagebytearray.length);

            Log.d("hello", "onPostExecute: " + meter);
        }
    }
}
