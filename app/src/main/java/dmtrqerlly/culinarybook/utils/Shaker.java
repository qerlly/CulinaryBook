package dmtrqerlly.culinarybook.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Shaker implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener mListener;
    private long mShakeTimestamp;
    private int mShakeCount;

    public void setOnShakeListener(final OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {

        if (mListener != null) {
            final float x = event.values[0];
            final float y = event.values[1];
            final float z = event.values[2];

            final float gX = x / SensorManager.GRAVITY_EARTH;
            final float gY = y / SensorManager.GRAVITY_EARTH;
            final float gZ = z / SensorManager.GRAVITY_EARTH;

            final float gForce = (float)Math.sqrt( gX * gX + gY * gY + gZ * gZ );

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                mListener.onShake(mShakeCount);
            }
        }
    }
}