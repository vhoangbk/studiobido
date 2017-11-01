package com.bidostudio.spiderofgrand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String APP_KEY_APPODEAL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APP_KEY_APPODEAL = getResources().getString(R.string.appodeal_key);

        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, APP_KEY_APPODEAL, Appodeal.BANNER | Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO );
        if (BuildConfig.DEBUG){
            Appodeal.setTesting(false);
        }
        Appodeal.disableNetwork(this, "admob", Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.BANNER );
        Appodeal.disableNetwork(this, "facebook", Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.BANNER);

        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                Log.d("Appodeal", "onBannerLoaded");
            }
            @Override
            public void onBannerFailedToLoad() {
                Log.d("Appodeal", "onBannerFailedToLoad");
            }
            @Override
            public void onBannerShown() {
                Log.d("Appodeal", "onBannerShown");
            }
            @Override
            public void onBannerClicked() {
                Log.d("Appodeal", "onBannerClicked");
            }
        });


        Appodeal.show(this, Appodeal.BANNER_BOTTOM);

        findViewById(R.id.img_cup).setOnClickListener(this);
        findViewById(R.id.img_coin).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_cup:
                Appodeal.show(this, Appodeal.REWARDED_VIDEO);
                break;
            case R.id.img_coin:
                Appodeal.show(this, Appodeal.INTERSTITIAL);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Appodeal.destroy(Appodeal.BANNER);
    }
}
