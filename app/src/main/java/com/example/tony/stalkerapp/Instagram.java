package com.example.tony.stalkerapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.logging.Handler;

import javax.security.auth.callback.Callback;

public class Instagram extends Activity{

    private InstagramApp mApp;

    private Button btnConnect, btnViewInfo, btnGetAllImages;
    private LinearLayout llAfterLoginView;
    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
   /* private Handler handler = new Handler(new Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == InstagramApp.WHAT_FINALIZE) {
                userInfoHashmap = mApp.getUserInfo();
            } else if (msg.what == InstagramApp.WHAT_FINALIZE) {
                Toast.makeText(Instagram.this, "Check your network.",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram);
        mApp = new InstagramApp(this, ApplicationData.CLIENT_ID,
                ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
        mApp.setListener(new InstagramApp.OAuthAuthenticationListener() {
            @Override
            public void onSuccess() {
// tvSummary.setText("Connected as " + mApp.getUserName());
                btnConnect.setText("Disconnect");
                llAfterLoginView.setVisibility(View.VISIBLE);
// userInfoHashmap = mApp.
                mApp.fetchUserName(handler);
            }
            @Override
            public void onFail(String error) {
                Toast.makeText(Instagram.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        setWidgetReference();
        bindEventHandlers();
        if (mApp.hasAccessToken()) {
// tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");
            llAfterLoginView.setVisibility(View.VISIBLE);
            mApp.fetchUserName(handler);
        }
    }
    private void bindEventHandlers() {
        btnConnect.setOnClickListener(this);
        btnViewInfo.setOnClickListener(this);
        btnGetAllImages.setOnClickListener(this);
    }
    private void setWidgetReference() {
        llAfterLoginView = (LinearLayout) findViewById(R.id.llAfterLoginView);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnViewInfo = (Button) findViewById(R.id.btnViewInfo);
        btnGetAllImages = (Button) findViewById(R.id.btnGetAllImages);
    }
    // OAuthAuthenticationListener listener ;
    @Override
    public void onClick(View v) {
        if (v == btnConnect) {
            connectOrDisconnectUser();
        } else if (v == btnViewInfo) {
            displayInfoDialogView();
        } else if (v == btnGetAllImages) {
            startActivity(new Intent(Instagram.this, AllMediaFiles.class)
                    .putExtra("userInfo", userInfoHashmap));
        }
    }
    private void connectOrDisconnectUser() {
        if (mApp.hasAccessToken()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    mApp.resetAccessToken();
// btnConnect.setVisibility(View.VISIBLE);
                                    llAfterLoginView.setVisibility(View.GONE);
                                    btnConnect.setText("Connect");
// tvSummary.setText("Not connected");
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            mApp.authorize();
        }
    }
    private void displayInfoDialogView() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Instagram.this);
        alertDialog.setTitle("Profile Info");
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.profile_view, null);
        alertDialog.setView(view);
        ImageView ivProfile = (ImageView) view
                .findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView) view.findViewById(R.id.tvUserName);
        TextView tvNoOfFollwers = (TextView) view
                .findViewById(R.id.tvNoOfFollowers);
        TextView tvNoOfFollowing = (TextView) view
                .findViewById(R.id.tvNoOfFollowing);
        new ImageLoader(Instagram.this).DisplayImage(
                userInfoHashmap.get(InstagramApp.TAG_PROFILE_PICTURE),
                ivProfile);
        tvName.setText(userInfoHashmap.get(InstagramApp.TAG_USERNAME));
        tvNoOfFollowing.setText(userInfoHashmap.get(InstagramApp.TAG_FOLLOWS));
        tvNoOfFollwers.setText(userInfoHashmap
                .get(InstagramApp.TAG_FOLLOWED_BY));
        alertDialog.create().show();
    }
}







