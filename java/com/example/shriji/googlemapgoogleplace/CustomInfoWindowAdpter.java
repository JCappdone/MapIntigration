package com.example.shriji.googlemapgoogleplace;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by shriji on 3/3/18.
 */

public class CustomInfoWindowAdpter implements GoogleMap.InfoWindowAdapter{

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdpter(Context mContext) {
        this.mContext = mContext;
        mWindow = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window,null);
    }

    private void rendomWindowText(Marker marker,View view){
        String title = marker.getTitle();
        TextView txtTitle =  view.findViewById(R.id.snippeTitle);
        if(!title.equals("")){
            txtTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView txtSnippet =  view.findViewById(R.id.snipptDetail);
        if(!snippet.equals("")){
            txtSnippet.setText(snippet);
        }

    }


    @Override
    public View getInfoWindow(Marker marker) {
        rendomWindowText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendomWindowText(marker,mWindow);
        return mWindow;
    }
}
