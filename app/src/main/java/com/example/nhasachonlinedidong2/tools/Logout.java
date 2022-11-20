package com.example.nhasachonlinedidong2.tools;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import androidx.appcompat.app.AlertDialog;

import com.example.nhasachonlinedidong2.activity.DangNhapActivity;


public class Logout {
    public void LogOut(Activity activity){

        //show dialog
        AlertDialog alertDialogW = new AlertDialog.Builder(activity).create();
        alertDialogW.setTitle("CẢNH BÁO");
        alertDialogW.setMessage("Bạn có thật sự muốn đăng xuất?");
        alertDialogW.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogW.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //--Clear SharePreferences--
                SharedPreferences settings = activity.getSharedPreferences("dulieu", Context.MODE_PRIVATE);
                settings.edit().clear().commit();

                //--Start new task--
                Intent intent = new Intent(activity, DangNhapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(activity,intent,null);
                dialog.dismiss();
            }
        });
        alertDialogW.show();

    }


}
