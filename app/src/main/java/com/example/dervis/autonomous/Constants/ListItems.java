package com.example.dervis.autonomous.Constants;

import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObj;
import com.example.dervis.autonomous.Objects.ListObjControl;
import com.example.dervis.autonomous.Objects.ListObjDiagnostics;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;

import java.util.Arrays;
import java.util.List;

public class ListItems {
    //Car info activity list
    public static final ListObj LICENSE_NR = new ListObj(220, "License Number", "");
    public static final ListObj VEHICLE_ID = new ListObj(221, "Vehicle ID", "");
    public static final ListObj WEIGHT = new ListObj(222, "Weight", "2kg");
    public static final ListObj FUEL = new ListObj(223, "Fuel", "Electricity");
    public static final ListObj FUEL_CAPACITY = new ListObj(224, "Fuel Capacity", "");
    public static final ListObj YEAR_OF_MANUFACTURE = new ListObj(225, "Year of Manufacture", "2017");

    public static final List<ListObj> objListCarInfo = Arrays.asList(LICENSE_NR, VEHICLE_ID, WEIGHT, FUEL, FUEL_CAPACITY, YEAR_OF_MANUFACTURE);

    //Main activity list
    public static final ListObjIcon CAR_INFO = new ListObjIcon(ListIDs.CAR_INFO_ID, "Car Information", "Information & Specification",ResourceGetter.getDrawable(R.drawable.icon_car), R.drawable.icon_car);
    public static final ListObjIcon CLIMATE = new ListObjIcon(ListIDs.CLIMATE_ID, "Climate", "AC & Heating", ResourceGetter.getDrawable(R.drawable.icon_climate), R.drawable.icon_climate);
    public static final ListObjIcon BATTERY = new ListObjIcon(ListIDs.BATTERY_ID, "Battery", "Information & Statistics", ResourceGetter.getDrawable(R.drawable.icon_battery), R.drawable.icon_battery);
    public static final ListObjIcon LOCATION = new ListObjIcon(ListIDs.LOCATION_ID, "Location", "GPS", ResourceGetter.getDrawable(R.drawable.icon_location), R.drawable.icon_location);
    public static final ListObjIcon CONTROLS = new ListObjIcon(ListIDs.CONTROLS_ID, "Controls", "Lights & Doors", ResourceGetter.getDrawable(R.drawable.icon_control), R.drawable.icon_control);
    public static final ListObjIcon DIAGNOSTICS = new ListObjIcon(ListIDs.DIAGNOSTIC_ID, "Diagnostics", "Information & Statistics", ResourceGetter.getDrawable(R.drawable.icon_diagnostics), R.drawable.icon_diagnostics);
    public static final ListObjIcon ENGINEERING = new ListObjIcon(ListIDs.ENGINEERING_ID, "Engineering", "Direct Control", ResourceGetter.getDrawable(R.drawable.icon_engineering), R.drawable.icon_engineering);

    public static final List<ListObjIcon> objListMain = Arrays.asList(CAR_INFO, CLIMATE, BATTERY, LOCATION, CONTROLS, DIAGNOSTICS, ENGINEERING);

    //Engineering activity list
    public static final ListObjIcon REMOTE = new ListObjIcon(ListIDs.REMOTE_ID, "Remote", "Direct Control", ResourceGetter.getDrawable(R.drawable.icon_remote), R.drawable.icon_remote);
    public static final ListObjIcon DASHBOARD = new ListObjIcon(ListIDs.DASHBOARD_ID, "Dashboard", "Dashboard input", ResourceGetter.getDrawable(R.drawable.icon_display), R.drawable.icon_display);
    public static final ListObjIcon DEVELOPER = new ListObjIcon(ListIDs.DEVELOPER_ID, "Developer", "Settings & Information", ResourceGetter.getDrawable(R.drawable.icon_settings), R.drawable.icon_settings);

    public static final List<ListObjIcon> objListEngineering = Arrays.asList(REMOTE, DASHBOARD, DEVELOPER);

    //Battery activity list
    public static final ListObj CAPACITY = new ListObj(420, "Capacity", "--mA");
    public static final ListObj AVERAGE_CONSUMPTION = new ListObj(421, "Average Consumption", "-- mA/h");
    public static final ListObj DISTANCE_TO_EMPTY = new ListObj(422, "Distance to empty", "--m");
    public static final ListObj TIME_TO_EMPTY = new ListObj(423, "Time to empty", "--h");
    public static final ListObj BATTERY_LEFT = new ListObj(424, "Battery left", "100%");

    public static final List<ListObj> objListBattery = Arrays.asList(CAPACITY, AVERAGE_CONSUMPTION, DISTANCE_TO_EMPTY, TIME_TO_EMPTY, BATTERY_LEFT);

    //Control activity list
    public static final ListObjControl POWER = new ListObjControl(520, "Power", "On", "Off", ResourceGetter.getDrawable(R.drawable.icon_power), R.drawable.icon_power);
    public static final ListObjControl DOORLOCK = new ListObjControl(521, "Door lock", "Unlocked", "Locked", ResourceGetter.getDrawable(R.drawable.locked), R.drawable.locked);
    public static final ListObjControl INDICATORS = new ListObjControl(522, "Hazard lights", "On", "Off", ResourceGetter.getDrawable(R.drawable.icon_indicators), R.drawable.icon_indicators);
    public static final ListObjControl HORN = new ListObjControl(523, "Horn", "Press to honk", "Press to honk", ResourceGetter.getDrawable(R.drawable.icon_horn), R.drawable.icon_horn);
    public static final ListObjControl HEAD_LIGHTS = new ListObjControl(524, "Lights", "On", "Off", ResourceGetter.getDrawable(R.drawable.icon_headlights), R.drawable.icon_headlights);

    public static final List<ListObjControl> objListControl = Arrays.asList(POWER, DOORLOCK, HEAD_LIGHTS, INDICATORS, HORN);

    //Diagnostics activity list
    public static final ListObjDiagnostics BRAKE_FLUID = new ListObjDiagnostics("Brake fluid", ResourceGetter.getDrawable(R.drawable.icon_brake_fluid), R.drawable.icon_brake_fluid, null);
    public static final ListObjDiagnostics TIRE = new ListObjDiagnostics("Tire", ResourceGetter.getDrawable(R.drawable.icon_tire_warning), R.drawable.icon_tire_warning, null);
    public static final ListObjDiagnostics COOLANT = new ListObjDiagnostics("Coolant", ResourceGetter.getDrawable(R.drawable.icon_coolant), R.drawable.icon_coolant, null);
    public static final ListObjDiagnostics LIGHTS = new ListObjDiagnostics("Lights", ResourceGetter.getDrawable(R.drawable.icon_headlights), R.drawable.icon_headlights, null);
    public static final ListObjDiagnostics OIL = new ListObjDiagnostics("Oil", ResourceGetter.getDrawable(R.drawable.icon_oil), R.drawable.icon_oil, null);
    public static final ListObjDiagnostics SERVICE = new ListObjDiagnostics( "Service", ResourceGetter.getDrawable(R.drawable.settings), R.drawable.settings, null);
    public static final ListObjDiagnostics WASHER_FLUID = new ListObjDiagnostics( "Washer fluid", ResourceGetter.getDrawable(R.drawable.icon_windshield_washer), R.drawable.icon_windshield_washer, null);

    public static final List<ListObjDiagnostics> objListDiagnostic = Arrays.asList(BRAKE_FLUID, TIRE, COOLANT, LIGHTS, OIL, SERVICE, WASHER_FLUID);


    //Engineering diagnostics activity list
    public static final ListObjDiagnostics COMPASS = new ListObjDiagnostics("Compass", ResourceGetter.getDrawable(R.drawable.icon_compass), R.drawable.icon_compass, Sockets.COMPASS_SOCKET_STRING);
    public static final ListObjDiagnostics LIDAR = new ListObjDiagnostics("Lidar", ResourceGetter.getDrawable(R.drawable.icon_lidar), R.drawable.icon_lidar, Sockets.LIDAR_SOCKET_STRING);
    public static final ListObjDiagnostics SONAR = new ListObjDiagnostics("Sonar", ResourceGetter.getDrawable(R.drawable.icon_sonar), R.drawable.icon_sonar, Sockets.SONAR_SOCKET_STRING);
    public static final ListObjDiagnostics CAMERA = new ListObjDiagnostics("Camera", ResourceGetter.getDrawable(R.drawable.icon_camera), R.drawable.icon_camera, Sockets.IMAGE_SOCKET_STRING);
    public static final ListObjDiagnostics LIGHTS_ENG = new ListObjDiagnostics("Lights", ResourceGetter.getDrawable(R.drawable.icon_light), R.drawable.icon_light, Sockets.LIGHT_SOCKET_STRING);
    public static final ListObjDiagnostics BATTERY_ENG = new ListObjDiagnostics("Battery", ResourceGetter.getDrawable(R.drawable.icon_battery), R.drawable.icon_battery, Sockets.BATTERY_SOCKET_STRING);

    public static final List<ListObjDiagnostics> objListEngineeringDiagnostic = Arrays.asList(COMPASS, LIDAR, SONAR, CAMERA, LIGHTS_ENG, BATTERY_ENG);


}
