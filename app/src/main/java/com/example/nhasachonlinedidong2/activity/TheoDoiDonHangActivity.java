package com.example.nhasachonlinedidong2.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.TheoDoiDonHang;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;

public class TheoDoiDonHangActivity extends AppCompatActivity {
    private Spinner layoutTDDH_spnTrangThai;
    private TextView layoutTDDH_btnTroVe;

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private String maKhachHang;

    private ArrayList<TheoDoiDonHang> theoDoiDonHangs = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> donCanXacNhan = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> donHangDangXuLy = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> donHangDaHuy = new ArrayList<>();
    private ArrayList<TheoDoiDonHang> tatCa = new ArrayList<>();
    private TheoDoiDonHangRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        maKhachHang = sharePreferences.layMa(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTDDH_rvTheoDoiDonHang);
        layoutTDDH_spnTrangThai = findViewById(R.id.layoutTDDH_spnTrangThai);
        layoutTDDH_btnTroVe = findViewById(R.id.layoutTDDH_btnTroVe);

        ArrayList<String> trangThai = new ArrayList<>();
        trangThai.add("Tất cả đơn hàng");
        trangThai.add("Đơn hàng cần xác nhận");
        trangThai.add("Đơn hàng đang xử lý");
        trangThai.add("Đơn hàng đã hủy");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.theodoidonhang_spinner, trangThai);
        layoutTDDH_spnTrangThai.setAdapter(arrayAdapter);

        adapter = new TheoDoiDonHangRecyclerViewAdapter(this, R.layout.theodoidonhang_item, theoDoiDonHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TheoDoiDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemTDDH_btnChiTiet = view.findViewById(R.id.itemTDDH_btnChiTiet);
                itemTDDH_btnChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TheoDoiDonHangActivity.this, ChiTietTheoDoiDonHangActivity.class);
                        intent.putExtra("maDonHang", theoDoiDonHangs.get(position).getMaDonHang());
                        startActivity(intent);
                    }
                });
            }
        });

        layoutTDDH_spnTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(donCanXacNhan);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(donHangDangXuLy);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        theoDoiDonHangs.clear();
                        theoDoiDonHangs.addAll(donHangDaHuy);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        layoutTDDH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiTheoDoiDonHang(maKhachHang, theoDoiDonHangs, adapter, this);
    }

    public void donHangCanXacNhan() {
        tatCa.clear();
        tatCa.addAll(theoDoiDonHangs);
        donCanXacNhan.clear();
        donHangDangXuLy.clear();
        donHangDaHuy.clear();
        for (TheoDoiDonHang theoDoiDonHang : theoDoiDonHangs) {
            if (theoDoiDonHang.getTrangThaiDon().equalsIgnoreCase("Đang xử lý") && theoDoiDonHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("Đã xác nhận")) {
                donCanXacNhan.add(theoDoiDonHang);
            } else if (theoDoiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                donHangDaHuy.add(theoDoiDonHang);
            } else {
                donHangDangXuLy.add(theoDoiDonHang);
            }
        }
        if (donCanXacNhan.size() != 0) {
            layoutTDDH_spnTrangThai.setSelection(1);
            theoDoiDonHangs.clear();
            theoDoiDonHangs.addAll(donCanXacNhan);
            adapter.notifyDataSetChanged();
        } else {
            layoutTDDH_spnTrangThai.setSelection(0);
            theoDoiDonHangs.clear();
            theoDoiDonHangs.addAll(tatCa);
            adapter.notifyDataSetChanged();
        }
    }
}
