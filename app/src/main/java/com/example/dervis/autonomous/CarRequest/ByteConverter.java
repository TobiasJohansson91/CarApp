package com.example.dervis.autonomous.CarRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dervis.autonomous.Objects.BatteryObj;
import com.example.dervis.autonomous.Objects.LidarObj;
import com.example.dervis.autonomous.Objects.SonarObj;
import com.example.dervis.autonomous.Objects.WheelSpeedObj;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteConverter {

    public static LidarObj lidar(byte[] data){
        data = removeFilterBytes(data);
        LidarObj lidarObj = convertLidarChunk(data);
        return lidarObj;
    }

    public static SonarObj sonar(byte[] data){
        data = removeFilterBytes(data);
        byte[] sonarData = clipByteData(data, 0,2);
        byte[] sonarIdData = new byte[]{0, data[2]};
        int sonarValue = convertToShort(sonarData);
        int sonarId = convertToShort(sonarIdData);
        return new SonarObj(sonarValue,sonarId);
    }

    public static WheelSpeedObj speed(byte[] data){
        data = removeFilterBytes(data);
        int speedwheel1 = convertToShort(clipByteData(data, 0, 2));
        int speedwheel2 = convertToShort(clipByteData(data, 2, 4));
        int speedwheel3 = convertToShort(clipByteData(data, 4, 6));
        int speedwheel4 = convertToShort(clipByteData(data, 6, 8));
        return new WheelSpeedObj(speedwheel1, speedwheel2, speedwheel3, speedwheel4);
    }

    public static Bitmap image(byte[] data){
        data = removeFilterBytes(data);
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        return bmp;
    }

    public static int compass(byte[] data){
        data = removeFilterBytes(data);
        int compass = convertToShort(data);
        return compass;
    }

    public static BatteryObj battery(byte[] data){
        data = removeFilterBytes(data);
        byte[] voltageData = clipByteData(data, 0,2);
        byte[] currentData = clipByteData(data, 2,4);
        int voltage = convertToShort(voltageData);
        int current = convertToShort(currentData);
        return new BatteryObj(voltage, current);
    }

    private static byte[] removeFilterBytes(byte[] data){
        return Arrays.copyOfRange(data, 4, data.length );
    }

    private static byte[] clipByteData(byte[] data, int from, int to){
        return Arrays.copyOfRange(data, from, to );
    }

    private static int convertToInt(byte[] data){
        return ByteBuffer.wrap(data).getInt();
    }

    private static short convertToShort(byte[] data){
        return ByteBuffer.wrap(data).getShort();
    }

    private static LidarObj convertLidarChunk(byte[] data){
        LidarObj lidarObj = new LidarObj();
        for (int row = 0; row < data.length; row+=5) {
            lidarObj.addObject(
                    convertToShort(new byte[]{0, data[row]}),
                    convertToShort(clipByteData(data, row + 1, row + 3)),
                    convertToShort(clipByteData(data, row + 3, row + 5))
                    );
        }
        return lidarObj;
    }
}