package com.xyc.guviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xyc.guguviews.views.GuProgressBarView;

public class MainActivity extends AppCompatActivity {

    private GuProgressBarView guProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guProgress = ((GuProgressBarView) findViewById(R.id.guProgress));
        guProgress.setPercent(0.9);
        guProgress.setCurrentTextCenter(true);

    }
}
