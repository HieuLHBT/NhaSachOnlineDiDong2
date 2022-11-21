package com.example.nhasachonlinedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhasachonlinedidong2.activity.DangNhapActivity;

public class NhaSachOnlineActivity extends AppCompatActivity {
    private ImageView layoutLG_imgDangNhap, layoutLG_imgNenDangNhap, layoutLG_imgNhaSachOnline;
    private Handler handler = new Handler();
    private ProgressBar layoutLG_pbThanhTrangThai;
    private TextView layoutLG_tvThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_layout);

        layoutLG_imgDangNhap = (ImageView) findViewById(R.id.layoutLG_imgDangNhap);
        layoutLG_imgNenDangNhap = (ImageView) findViewById(R.id.layoutLG_imgNenDangNhap);
        layoutLG_imgNhaSachOnline = (ImageView) findViewById(R.id.layoutLG_imgNhaSachOnline);

        Glide.with(this).load(R.drawable.dangnhap).into(layoutLG_imgDangNhap);
        Glide.with(this).load(R.drawable.nendangnhap).into(layoutLG_imgNenDangNhap);
        Glide.with(this).load(R.drawable.nhasachonline).into(layoutLG_imgNhaSachOnline);

        layoutLG_tvThongTin = (TextView) this.findViewById(R.id.layoutLG_tvThongTin);
        layoutLG_pbThanhTrangThai = (ProgressBar) this.findViewById(R.id.layoutLG_pbThanhTrangThai);

        doStartProgressBar1();
//        progressBar.setIndeterminate(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            progressBar.setMin(20);
//        }
//        progressBar.setProgress(25);
//        progressBar.setMax(100);
        layoutLG_imgDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhaSachOnlineActivity.this, DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void doStartProgressBar1() {
        final int MAX = 1300;
        layoutLG_pbThanhTrangThai.setMax(MAX);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        // Process
                    }
                });
                for (int i = 0; i < MAX; i++) {
                    final int progress = i + 1;
                    // Do something (Download, Upload, Update database,..)
                    SystemClock.sleep(10); // Sleep 20 milliseconds.
                    // Update interface.
                    handler.post(new Runnable() {
                        public void run() {
                            layoutLG_pbThanhTrangThai.setProgress(progress);
                            int percent = (progress * 100) / MAX;

                            layoutLG_tvThongTin.setText("Đang tải: " + percent + " %");
                            if (progress == MAX) {
                                layoutLG_tvThongTin.setText("Hoàn thành!");
                                dangnhap:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(NhaSachOnlineActivity.this, DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 1000);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }
}