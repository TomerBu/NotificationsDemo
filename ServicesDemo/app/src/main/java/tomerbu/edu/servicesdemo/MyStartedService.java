package tomerbu.edu.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class MyStartedService extends Service {

    private boolean stopAllJOBS = false;
    AsyncTask asyncTask;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (asyncTask != null) {
            asyncTask.cancel(true);
        }

        asyncTask = new AsyncTask() {
            private boolean finished = false;

            @Override
            protected Object doInBackground(Object[] objects) {

                int counter = 0;
                while (!finished && !stopAllJOBS && !this.isCancelled()) {
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("TomerBu", counter + " : " + new Date());
                    counter++;
                    if (counter == 100) {
                        Log.i("TomerBu", "Done with the entire job");
                        stopSelf();
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
            }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //asyncTask.execute();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopAllJOBS = true;
        Log.i("TomerBu", "Destroyed\nFinished: ");
    }

    //onStart Deprecated
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
