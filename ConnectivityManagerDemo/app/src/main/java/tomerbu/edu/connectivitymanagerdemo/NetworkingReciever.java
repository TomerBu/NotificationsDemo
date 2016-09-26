package tomerbu.edu.connectivitymanagerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkingReciever extends BroadcastReceiver {
    public NetworkingReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.e("Tomer" ,intent.toString());
    }
}
