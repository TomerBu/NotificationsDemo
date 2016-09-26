package tomerbu.edu.demo;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MyService extends IntentService {


     Handler uiHandler;
    Target t = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Notification notification = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle("Hello")
                    .setContentText("Welcome")
                    .setSmallIcon(R.drawable.com_facebook_button_icon)
                    .setLargeIcon(bitmap).build();
            NotificationManagerCompat.from(getApplicationContext()).notify(10, notification);
        //    stopSelf();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Log.d("TomerBu", "Failed");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            Log.d("TomerBu", "onPrepareLoad");
        }
    };

    public MyService() {
        super("GOOG");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Looper mainLooper = getApplicationContext().getMainLooper();
        uiHandler = new Handler(mainLooper);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        uiHandler.post(new Runnable() {
            @Override
            public void run() {//https://images.google.com/images/branding/googleg/1x/googleg_standard_color_128dp.png
                Picasso.with(getApplicationContext()).load("http://agarioskins.com/submitted/useruploads/Mario-Mushroom.png").into(t);
            }


        });


    }
}
