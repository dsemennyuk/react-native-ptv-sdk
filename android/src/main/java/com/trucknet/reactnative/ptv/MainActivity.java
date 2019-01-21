package com.trucknet.reactnative.ptv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ptvag.navigation.sdk.MapView;
import com.ptvag.navigation.sdk.Position;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Application.hadStartupException()) {
            showStartupError();
            return;
        }

        setContentView(R.layout.activity_main);

        mapView = (MapView)findViewById(R.id.map);
        mapView.setCenter(new Position(934177, 6268747));
    }

    private void showStartupError() {
        setContentView(R.layout.activity_startup_error);
        TextView errorMessage = (TextView) findViewById(R.id.errorMessageTextView);
        Throwable e = Application.getStartupException();

        errorMessage.setText(e.getMessage());
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (Application.hadStartupException())
            return;

        mapView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Application.hadStartupException())
            return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Application.hadStartupException())
            return;
        mapView.finish();

    }
}