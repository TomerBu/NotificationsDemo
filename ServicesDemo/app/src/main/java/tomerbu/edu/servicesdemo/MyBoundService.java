package tomerbu.edu.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void doStuff(String data) {
        Log.d("Tomer", data);
    }

    public class MyBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }

        public void doStuff(String data) {
            Log.d("Tomer", data);
        }
    }
}
