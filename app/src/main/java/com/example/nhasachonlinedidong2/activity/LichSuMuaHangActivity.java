package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.LichSuMuaHangDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_DonHang;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;


public class LichSuMuaHangActivity extends AppCompatActivity {
    private TextView layoutLSMH_btnTroVe;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private LichSuMuaHangDonHangRecyclerViewAdapter adapter;
    private ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs = new ArrayList<>();

    private String maKhachHang;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.lichsumuahang_layout);

        maKhachHang = sharePreferences.layMa(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutLSMH_rvLichSuMuahang);
        layoutLSMH_btnTroVe =  findViewById(R.id.layoutLSMH_btnTroVe);

        adapter = new LichSuMuaHangDonHangRecyclerViewAdapter(this, R.layout.lichsumuahang_donhang_item, lichSuMuaHang_donHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LichSuMuaHangDonHangRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemLSMH_btnDanhGia = view.findViewById(R.id.itemLSMH_btnDanhGia);
                Button itemLSMH_btnMuaLai = view.findViewById(R.id.itemLSMH_btnMuaLai);

                itemLSMH_btnDanhGia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LichSuMuaHangActivity.this, DanhGiaSanPhamActivity.class);
                        intent.putExtra("maDonHang", lichSuMuaHang_donHangs.get(position).getMaDonHang());
                        startActivity(intent);
                    }
                });

                itemLSMH_btnMuaLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireBaseNhaSachOnline.muaLaiDonHang(maKhachHang, lichSuMuaHang_donHangs.get(position).getLichSuMuaHang_sanPhams(), LichSuMuaHangActivity.this);
                    }
                });
            }
        });

        layoutLSMH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiLichSuMuaHang(maKhachHang, lichSuMuaHang_donHangs, adapter);
    }
}
