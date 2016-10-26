package tomerbu.edu.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.GoogleApiClient;

public class DeepLinkReferActivity extends AppCompatActivity {

    private String TAG = "Tomerbu";
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_refer);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)

                .addApi(AppInvite.API)
                .build();

        boolean autoLaunchDeepLink = true;

        Intent intent = getIntent();
        if (AppInviteReferral.hasReferral(intent)) {
            processReferralIntent(intent);
        }

    }


    private void processReferralIntent(Intent intent) {
        boolean autoLaunchDeepLink = false;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink);

        // Extract referral information from the intent
        String invitationId = AppInviteReferral.getInvitationId(intent);
        String deepLink = AppInviteReferral.getDeepLink(intent);

        // Display referral information
        // [START_EXCLUDE]
        Log.e(TAG, "Found Referral: " + invitationId + ":" + deepLink);
        Log.e(TAG, "Found Referral: " + invitationId + ":" + invitationId);

    }
}
