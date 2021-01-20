package com.example.mohandesipezeshki.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohandesipezeshki.R;
import com.example.mohandesipezeshki.view.dialog.dialogmsg;

import io.github.yuweiguocn.lib.squareloading.SquareLoading;

public class Internet_Error_Connecting extends AppCompatActivity {
    SquareLoading loading;
    Button enter;
    ConnectionCheck connectionCheck;
    private boolean doubleBackToExitPressedOnce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet__error__connecting);
        cast();
    }
    void cast(){
        enter=findViewById(R.id.enter);
        loading=findViewById(R.id.loading);

    }
    @Override
    protected void onStart() {
        connectionCheck=new ConnectionCheck();
        registerReceiver(connectionCheck,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(connectionCheck,new IntentFilter("android.net.android.net.wifi.WIFI_STATE_CHANGE"));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(connectionCheck);
        super.onStop();
    }

    void MethoidCheckIntent(){
        loading.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(0, "http://pixelli.ir", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                dialogmsg da= new  dialogmsg(Internet_Error_Connecting.this,"خطا در ارتباط با سرور");
                da.show();

            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

    class ConnectionCheck extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            boolean checkinfo=networkInfo!=null && networkInfo.isConnectedOrConnecting();
            if(checkinfo){
                MethoidCheckIntent();
            }else
            {
                startActivity(new Intent(getApplicationContext(),Internet_Error_Connecting.class));
                finish();
            }

        }
    }
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای بازگشت بکبار دیگر کلید برگشت را بفشارید", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {@Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
        }, 2000);
    }
}