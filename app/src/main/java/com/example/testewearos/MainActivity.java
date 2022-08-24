package com.example.testewearos;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.google.android.gms.wearable.DataMap.TAG;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private SensorManager mSensorManager;
    private Sensor mHeartSensor;
    private TextView mTextView;


    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            TextView txt = (TextView) findViewById(R.id.text);
            txt.setText(String.valueOf(event.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mHeartSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorEventListener, mHeartSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
//
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
    }

}