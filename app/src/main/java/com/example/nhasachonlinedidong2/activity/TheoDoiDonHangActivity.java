package com.example.nhasachonlinedidong2.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.data_model.DonHang;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.TheoDoiDonHang;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {
    private String maKhachHang;
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private int selectedRow = -1;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private ArrayList<DonHang> donHangsModel = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        maKhachHang = sharePreferences.getKhachHang(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);

      /*  theoDoiDonHangs.add(new TheoDoiDonHang("DH01", "Nguyen Van Tho", "30/09/2022", "11/10/2022", 130000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH02", "Tran Bao Tin", "27/09/2022", "14/10/2022", 120000, "Đang chờ xác nhận"));
        theoDoiDonHangs.add(new TheoDoiDonHang("DH03", "Nguyen Van Tho", "29/09/2022", "15/10/2022", 150000, "Đã xác nhận"));
      */
        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //fireBase.hienThiTheoDoiDonHang(maKhachHang,theoDoiDonHangs, adapter, this);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemTDDH_btnXemChiTiet = view.findViewById(R.id.itemTDDH_btnXemChiTiet);

                itemTDDH_btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Chuyển sang màn hình Xem Chi Tiết!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
//        fireBase.hienThiTheoDoiDonHang(maKhachHang, theoDoiDonHangs, adapter, this);
    }
}
