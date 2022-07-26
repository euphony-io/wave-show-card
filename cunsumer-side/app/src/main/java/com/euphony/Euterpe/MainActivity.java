package com.euphony.Euterpe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;
import euphony.lib.receiver.AcousticSensor;
import euphony.lib.receiver.EuRxManager;




public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 17389;
    private boolean mode = false;
    private Context mContext;
    private View MainView;
    private TextView listenView;
    private TextView listenView2;

    EuRxManager mRxManager = new EuRxManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainView = findViewById(R.id.view_main);
        listenView = findViewById(R.id.listenView);
        listenView2 = findViewById(R.id.listenView2);
        mContext = this;


        int permission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO);

        if (permission == PackageManager.PERMISSION_DENIED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.RECORD_AUDIO}, 1000);
            }
        }
        mRxManager.setAcousticSensor(new AcousticSensor() {
            @Override
            public void notify(String letters) {
                listenView.setText(letters);

                mode = false;
            }
        });

        MainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "Touch", Toast.LENGTH_SHORT).show();
                        if (ContextCompat.checkSelfPermission(mContext,
                                Manifest.permission.RECORD_AUDIO)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestRecorderPermission();
                        }
                        else {
                            if(mode) {
                                mRxManager.finish();
                                listenView2.setText("멈춤");
                                mode = false;
                            } else {
                                mRxManager.listen();  //Listening Start
                                listenView2.setText("음파신호를 듣는 중입니다.. ");
                                mode = true;
                            }
                        }

                        break;

                }
                return true;
            }
        });
        requestRecorderPermission();
    }
    private void requestRecorderPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                Snackbar.make(MainView, R.string.recorder_access_required,
                        Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Request the permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.RECORD_AUDIO},
                                MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                    }
                }).show();
            } else {
                Snackbar.make(MainView, R.string.recorder_unavailable, Snackbar.LENGTH_SHORT).show();
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        } else {
            // Permission has already been granted
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(MainView, R.string.recorder_permission_granted,
                                    Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Snackbar.make(MainView, R.string.recorder_permission_rejected,
                                    Snackbar.LENGTH_SHORT)
                            .show();
                }
        }
    }

}








