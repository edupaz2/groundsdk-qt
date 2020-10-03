package com.edupaz2.groundsdkqt;

import org.qtproject.qt5.android.bindings.QtActivity;

import com.parrot.drone.groundsdk.ManagedGroundSdk;

import android.os.Bundle;

public class MainActivity extends QtActivity {

    private ManagedGroundSdk mGroundSdk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroundSdk = ManagedGroundSdk.obtainSession(this);
    }
}