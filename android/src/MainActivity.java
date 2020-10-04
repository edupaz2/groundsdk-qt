package com.edupaz2.groundsdkqt;

import org.qtproject.qt5.android.bindings.QtActivity;

import com.parrot.drone.groundsdk.ManagedGroundSdk;
import com.parrot.drone.groundsdk.facility.AutoConnection;
import com.parrot.drone.groundsdk.device.Drone;
import com.parrot.drone.groundsdk.Ref;
import com.parrot.drone.sdkcore.ulog.ULog;
import com.parrot.drone.sdkcore.ulog.ULogTag;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends QtActivity {

    private static final ULogTag TAG = new ULogTag("GroundSDKQt");
    private ManagedGroundSdk mGroundSdk;
    private Ref<AutoConnection> mAutoConnection;
    private Drone mDrone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ULog.i(TAG, "--> STARTING APP");
        Toast.makeText(this, "START APP", Toast.LENGTH_LONG).show();
        mGroundSdk = ManagedGroundSdk.obtainSession(this);
        mAutoConnection = mGroundSdk.getFacility(AutoConnection.class, autoconnection -> {
            assert autoconnection != null;
            ULog.i(TAG, "AUTO CONNECTION STATUS " + autoconnection.getStatus().toString());
            if(autoconnection.getStatus() != AutoConnection.Status.STARTED) {
                Toast.makeText(this, "START AUTOCONN", Toast.LENGTH_LONG).show();
                autoconnection.start();
            }

            mDrone = autoconnection.getDrone();
            if(mDrone != null) {
                Toast.makeText(this, "YES DRONE", Toast.LENGTH_LONG).show();
                ULog.w(TAG, "YES DRONE");
                mDrone.getState(state -> {
                    assert state != null;
                    ULog.i(TAG, "DRONE CONNECTED");
                    Toast.makeText(this, "Drone Status: " + state.toString(), Toast.LENGTH_LONG).show();
            });
            } else {
                Toast.makeText(this, "NO DRONE", Toast.LENGTH_LONG).show();
                ULog.w(TAG, "NO DRONE");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
