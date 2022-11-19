package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.DanhMucSanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.DanhMucSanPham;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;

public class DanhMucSanPhamActivity extends AppCompatActivity {
    private Spinner layoutDMSP_spnSanPham;
    private SearchView layoutDMSP_swTimKiem;
    private TextView layoutDMSP_btnTroVe;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private DanhMucSanPhamRecyclerViewAdapter adapter;
    private ArrayList<DanhMucSanPham> danhMucSanPhams = new ArrayList<>();
    private ArrayList<DanhMucSanPham> tatCa = new ArrayList<>();
    private ArrayList<DanhMucSanPham> sachs = new ArrayList<>();
    private ArrayList<DanhMucSanPham> vanPhongPhams = new ArrayList<>();

    private String maNhanVien;

    ArrayList<String> dataSanPham = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmucsanpham_layout);

        maNhanVien = sharePreferences.layMa(this);

        layoutDMSP_swTimKiem = findViewById(R.id.layoutDMSP_swTimKiem);
        layoutDMSP_spnSanPham = findViewById(R.id.layoutDMSP_spnSanPham);
        layoutDMSP_btnTroVe = findViewById(R.id.layoutDMSP_btnTroVe);

        timKiem();

        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.phanhoiykienkhachhang_spinner, dataSanPham);
        layoutDMSP_spnSanPham.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutDMSP_rvSanPham);
        adapter = new DanhMucSanPhamRecyclerViewAdapter(this, R.layout.danhmucsanpham_item, danhMucSanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutDMSP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void filterList(String newText) {
        ArrayList<DanhMucSanPham> fiIteredList = new ArrayList<>();
        for (DanhMucSanPham danhMucSanPham : danhMucSanPhams) {
            if (danhMucSanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(danhMucSanPham);
            }
        }
        if (!fiIteredList.isEmpty()) {
            adapter.setFilteredList(fiIteredList);
        }
    }

    public void timKiem() {
        layoutDMSP_swTimKiem.clearFocus();
        layoutDMSP_swTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiManHinhDanhMucSanPham(danhMucSanPhams, adapter, this);
    }

    public void suKien() {
        tatCa.clear();
        tatCa.addAll(danhMucSanPhams);
        sachs.clear();
        vanPhongPhams.clear();
        for (DanhMucSanPham sanPham : danhMucSanPhams) {
            if (sanPham.getMaSanPham().contains("s")) {
                sachs.add(sanPham);
            } else if (sanPham.getMaSanPham().contains("vpp")) {
                vanPhongPhams.add(sanPham);
            }
        }
        layoutDMSP_spnSanPham.setSelection(0);

        layoutDMSP_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        danhMucSanPhams.clear();
                        danhMucSanPhams.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        danhMucSanPhams.clear();
                        danhMucSanPhams.addAll(sachs);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        danhMucSanPhams.clear();
                        danhMucSanPhams.addAll(vanPhongPhams);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
