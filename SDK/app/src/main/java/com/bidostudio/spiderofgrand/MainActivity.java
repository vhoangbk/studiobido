package com.bidostudio.spiderofgrand;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;

import hoangnv.sdk.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String APP_KEY_APPODEAL = "6536df740ef85e163641298f66b1ae5c067ff6fc49cda055";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, APP_KEY_APPODEAL, Appodeal.BANNER | Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO );

//        Appodeal.setBannerCallbacks(new BannerCallbacks() {
//            @Override
//            public void onBannerLoaded(int height, boolean isPrecache) {
//                Log.d("Appodeal", "onBannerLoaded");
//            }
//            @Override
//            public void onBannerFailedToLoad() {
//                Log.d("Appodeal", "onBannerFailedToLoad");
//            }
//            @Override
//            public void onBannerShown() {
//                Log.d("Appodeal", "onBannerShown");
//            }
//            @Override
//            public void onBannerClicked() {
//                Log.d("Appodeal", "onBannerClicked");
//            }
//        });

//        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
//            @Override
//            public void onInterstitialLoaded(boolean isPrecache) {
//                Log.d("Appodeal", "onInterstitialLoaded");
//            }
//            @Override
//            public void onInterstitialFailedToLoad() {
//                Log.d("Appodeal", "onInterstitialFailedToLoad");
//            }
//            @Override
//            public void onInterstitialShown() {
//                Log.d("Appodeal", "onInterstitialShown");
//            }
//            @Override
//            public void onInterstitialClicked() {
//                Log.d("Appodeal", "onInterstitialClicked");
//            }
//            @Override
//            public void onInterstitialClosed() {
//                Log.d("Appodeal", "onInterstitialClosed");
//            }
//        });

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
