package com.example.dervis.autonomous.Constants;

import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObj;
import com.example.dervis.autonomous.Objects.ListObjControl;
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
    public static final ListObjIcon CONTROLS = new ListObjIcon(ListIDs.CONTROLS_ID, "Controls", "lights & Doors", ResourceGetter.getDrawable(R.drawable.icon_control), R.drawable.icon_control);
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
    public static final ListObjControl INDICATORS = new ListObjControl(522, "Indicators", "On", "Off", ResourceGetter.getDrawable(R.drawable.icon_indicators), R.drawable.icon_indicators);
    public static final ListObjControl HORN = new ListObjControl(523, "Horn", "Press to honk", "Press to honk", ResourceGetter.getDrawable(R.drawable.icon_horn), R.drawable.icon_horn);

    public static final List<ListObjControl> objListControl = Arrays.asList(POWER, DOORLOCK, INDICATORS, HORN);

    //Diagnostics activity list
    public static final ListObjIcon BRAKE_FLUID = new ListObjIcon(620, "Brake fluid", "", ResourceGetter.getDrawable(R.drawable.icon_brake_fluid), R.drawable.icon_brake_fluid);
    public static final ListObjIcon TIRE = new ListObjIcon(621, "Tire", "", ResourceGetter.getDrawable(R.drawable.icon_tire_warning), R.drawable.icon_tire_warning);
    public static final ListObjIcon COOLANT = new ListObjIcon(622, "Coolant", "", ResourceGetter.getDrawable(R.drawable.icon_coolant), R.drawable.icon_coolant);
    public static final ListObjIcon LIGHTS = new ListObjIcon(623, "Lights", "", ResourceGetter.getDrawable(R.drawable.icon_light), R.drawable.icon_light);
    public static final ListObjIcon OIL = new ListObjIcon(624, "Oil", "", ResourceGetter.getDrawable(R.drawable.icon_oil), R.drawable.icon_oil);
    public static final ListObjIcon SERVICE = new ListObjIcon(625, "Service", "", ResourceGetter.getDrawable(R.drawable.settings), R.drawable.settings);
    public static final ListObjIcon WASHER_FLUID = new ListObjIcon(626, "Washer fluid", "", ResourceGetter.getDrawable(R.drawable.icon_windshield_washer), R.drawable.icon_windshield_washer);

    public static final List<ListObjIcon> objListDiagnostic = Arrays.asList(BRAKE_FLUID, TIRE, COOLANT, LIGHTS, OIL, SERVICE, WASHER_FLUID);


    //Engineering diagnostics activity list
    public static final ListObjIcon COMPASS = new ListObjIcon(720, "Compass", "", ResourceGetter.getDrawable(R.drawable.icon_compass), R.drawable.icon_compass);
    public static final ListObjIcon LIDAR = new ListObjIcon(721, "Lidar", "", ResourceGetter.getDrawable(R.drawable.icon_lidar), R.drawable.icon_lidar);
    public static final ListObjIcon SONAR = new ListObjIcon(722, "Sonar", "", ResourceGetter.getDrawable(R.drawable.icon_sonar), R.drawable.icon_sonar);
    public static final ListObjIcon CAMERA = new ListObjIcon(723, "Camera", "", ResourceGetter.getDrawable(R.drawable.icon_camera), R.drawable.icon_camera);
    public static final ListObjIcon LIGHTS_ENG = new ListObjIcon(724, "Lights", "", ResourceGetter.getDrawable(R.drawable.icon_light), R.drawable.icon_light);
    public static final ListObjIcon BATTERY_ENG = new ListObjIcon(725, "Battery", "", ResourceGetter.getDrawable(R.drawable.icon_battery), R.drawable.icon_battery);

    public static final List<ListObjIcon> objListEngineeringDiagnostic = Arrays.asList(COMPASS, LIDAR, SONAR, CAMERA, LIGHTS_ENG, BATTERY_ENG);


}
