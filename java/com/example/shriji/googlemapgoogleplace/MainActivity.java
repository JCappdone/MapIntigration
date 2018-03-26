package com.example.shriji.googlemapgoogleplace;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.places.GeoDataClient;
import com.jaeger.library.StatusBarUtil;

import ColorFullTextView.RainbowTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    protected GeoDataClient mGeoDataClient;

    @BindView(R.id.txtLogo1)
    RainbowTextView txtLogo1;
    @BindView(R.id.txtLogo2)
    RainbowTextView txtLogo2;
    @BindView(R.id.btnMap)
    ActionProcessButton btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/LeckerliOne-Regular.ttf");
        txtLogo1.setTypeface(face);
        txtLogo2.setTypeface(face);
        btnMap.setTypeface(face);
        btnMap.setMode(ActionProcessButton.Mode.ENDLESS);
        StatusBarUtil.setTransparent(this);


        if (isServicesOK()) {
            init();
        }


    }

    private void init() {
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMap.setProgress(1);
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google service version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services Working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: An error occur but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "you can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}
