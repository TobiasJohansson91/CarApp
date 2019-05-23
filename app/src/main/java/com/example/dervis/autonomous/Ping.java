package com.example.dervis.autonomous;

import android.os.AsyncTask;

import java.io.IOException;

public class Ping extends AsyncTask<String, Void, Boolean> {

    private final String ip;
    private final ICommandCallback callback;

    private Ping(String ip, ICommandCallback callback) {
        this.ip = ip;
        this.callback = callback;
    }

    public static void ping(String ip, ICommandCallback callback) {
        new Ping(ip, callback).execute();
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        String command = "/system/bin/ping -c 1 " + ip;
        try {
            Process mIpAddrProcess = runtime.exec(command);
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue " + mExitValue);
            if (mExitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        callback.callbackPing(aBoolean, ip);
    }
}
