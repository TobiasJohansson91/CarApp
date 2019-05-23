package com.example.dervis.autonomous.Objects;

import java.util.ArrayList;

public class LidarObj {

    private ArrayList<LidarPositionObj> objectList = new ArrayList<>();

    public void addObject(short quality, short distance, short angle){
        if(distance != 0 || angle != 0)
            objectList.add(new LidarPositionObj(quality, distance, angle));
    }

    private class LidarPositionObj {

        public final double quality;
        public final double distance;
        public final double angle;

        private LidarPositionObj(short quality, short distance, short angle) {
            this.quality = (quality * 1);
            this.distance = (distance * 0.25);
            this.angle = (angle * (1.0/64.0*Math.PI/180.0));
        }
    }

}
