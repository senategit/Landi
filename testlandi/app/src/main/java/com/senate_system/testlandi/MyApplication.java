package com.senate_system.testlandi;

import android.app.Application;
import android.os.Build;

import com.usdk.apiservice.aidl.constants.RFDeviceName;
import com.usdk.apiservice.aidl.pinpad.DeviceName;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
//        LogUtil.d("-------------------onCreate-------------------");
        super.onCreate();

        System.out.println("test");

        initDefaultConfig();
        DeviceHelper.me().init(this);
        DeviceHelper.me().bindService();
        DeviceHelper.me().setServiceListener(new DeviceHelper.ServiceReadyListener() {
            @Override
            public void onReady(String version) {

            }
        });
    }

    private void initDefaultConfig() {
        if (Build.MODEL.startsWith("AECR")) {
            DemoConfig.PINPAD_DEVICE_NAME = DeviceName.COM_EPP;
            DemoConfig.RF_DEVICE_NAME = RFDeviceName.EXTERNAL;
        } else {
            DemoConfig.PINPAD_DEVICE_NAME = DeviceName.IPP;
            DemoConfig.RF_DEVICE_NAME = RFDeviceName.INNER;
        }
    }

    @Override
    public void onTerminate() {
//        LogUtil.d("-------------------onTerminate-------------------");
        super.onTerminate();
        DeviceHelper.me().unbindService();
    }
}
