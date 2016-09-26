package tomerbu.edu.servicesdemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Date;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TomerBu", "Created intent serivce");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i("TomerBu", "onStartCommand");
        return START_REDELIVER_INTENT;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        int counter = 0;
        boolean finished = false;

        while (!finished) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("TomerBu", counter + " : " + new Date());
            counter++;
            if (counter == 100) {
                Log.i("TomerBu", "Done with the entire job");
                LocalBroadcastManager.getInstance(MyIntentService.this).
                        sendBroadcast(new Intent("MyCustomAction"));

                //No need to call stop self. this is a common mistake,
                // as we don't want to stop it if there are more tasks at hand.
                finished = true;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
