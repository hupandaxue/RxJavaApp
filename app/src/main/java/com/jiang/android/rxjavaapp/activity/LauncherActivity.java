package com.jiang.android.rxjavaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jiang.android.rxjavaapp.R;
import com.jiang.android.rxjavaapp.common.CommonString;
import com.jiang.android.rxjavaapp.common.SPKey;
import com.jiang.android.rxjavaapp.utils.DataUtils;
import com.jiang.android.rxjavaapp.utils.SharePrefUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class LauncherActivity extends AppCompatActivity {


    ImageView mSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplash = (ImageView) findViewById(R.id.splash_index);
        ImageLoader.getInstance().displayImage(CommonString.SPLASH_INDEX_URL, mSplash);
        startAct();

    }

    private void startAct() {
        if (SharePrefUtil.getBoolean(this, SPKey.FIRST_ENTER, true)) {
            DataUtils.fillData(new DataUtils.callBack() {
                @Override
                public void onSuccess() {
                    SharePrefUtil.saveBoolean(LauncherActivity.this, SPKey.FIRST_ENTER, false);
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                    LauncherActivity.this.finish();

                }

                @Override
                public void onFail(Throwable e) {
                    Snackbar.make(mSplash, e.getMessage(), Snackbar.LENGTH_LONG).show();

                }
            });

        } else {
            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
            LauncherActivity.this.finish();
        }
    }
}
