package com.example.shubham.classtest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    int count = 0;
    int p = 50;
    int window = 10;
    int time = 0;
    int offset = 0;
    double[] w = new double[10];
    double g=0;
    double m=0;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int on = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(),"Accelerometer", Toast.LENGTH_LONG).show();
            System.exit(1);
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            count = count + 1;

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            w[count] = Math.sqrt(x * x + y * y + z * z);


            TextView d = (TextView) findViewById(R.id.accel);
            d.setText(String.format(Locale.getDefault(), "%.9f", w[count]));
        }

        if(sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            g = Math.sqrt(x * x + y * y + z * z);
            TextView netG = (TextView) findViewById(R.id.gyro);

            netG.setText(String.format(Locale.getDefault(), "%.9f", g));
        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            m = Math.sqrt(x * x + y * y + z * z);
            TextView netM = (TextView) findViewById(R.id.mag);

            netM.setText(String.format(Locale.getDefault(), "%.9f", m));
        }

        }

    @Override
    protected void onResume() {
        super.onResume();
        if(on==1) return;
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getApplicationContext(), "Accelerometer Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getApplicationContext(), "Gyroscope Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getApplicationContext(), "Magnetometer Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
    }

    @Override
    protected void onPause() {
        if(on==1) {
            super.onPause();
            return;
        }
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.unregisterListener(this, sensor);
        }
        else {
            Toast.makeText(getApplicationContext(), "Accelerometer Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.unregisterListener(this, sensor);
        }
        else {
            Toast.makeText(getApplicationContext(), "Gyroscope Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.unregisterListener(this, sensor);
        }
        else {
            Toast.makeText(getApplicationContext(), "Magnetometer Error", Toast.LENGTH_LONG).show();
            System.exit(1);
        }
        super.onPause();
    }

    }








