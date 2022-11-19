
package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ManHinhChinhKhachHangActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private String maKhachHang;

    private SearchView layoutMHCKH_swTimKiem;
    private TextView layoutMHCKH_tvTenKhachHang;
    private ImageButton layoutMHCKH_btnLogout;

    private ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    private ArrayList<ItemSanPham> tatCa = new ArrayList<>();
    private ArrayList<ItemSanPham> sachs = new ArrayList<>();
    private ArrayList<ItemSanPham> vanPhongPhams = new ArrayList<>();
    private ManHinhChinhKhachHangAdapter adapter;
    private Spinner layoutMHCKH_spnSanPham;
    private ArrayList<String> dataSanPham = new ArrayList<>();
    private KhachHang khachHang = new KhachHang();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_khachhang_layout);
        maKhachHang = sharePreferences.layMa(this);
        khachHang.setMaKhachHang(maKhachHang);

        //search
        layoutMHCKH_swTimKiem = findViewById(R.id.layoutMHCKH_swTimKiem);
        layoutMHCKH_tvTenKhachHang = findViewById(R.id.layoutMHCKH_tvTenKhachHang);
        layoutMHCKH_btnLogout = findViewById(R.id.layoutMHCKH_btnLogout);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.layoutMHCKH_botNAV);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        timKiem();

        //Get views from layout
        layoutMHCKH_spnSanPham = findViewById(R.id.layoutMHCKH_spnSanPham);
        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.phanhoiykienkhachhang_spinner, dataSanPham);
        layoutMHCKH_spnSanPham.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHCKH_rvDanhSach);
        adapter = new ManHinhChinhKhachHangAdapter(this, R.layout.manhinhchinh_khachhang_item, sanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutMHCKH_btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePreferences.dangXuat(ManHinhChinhKhachHangActivity.this);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiManHinhChinhKhachHang(khachHang, sanPhams, adapter, this);
    }

    public void hienThiTenKhachHang() {
        layoutMHCKH_tvTenKhachHang.setText(khachHang.getTenKhachHang());
    }

    public void suKien() {
        tatCa.clear();
        tatCa.addAll(sanPhams);
        sachs.clear();
        vanPhongPhams.clear();
        for (ItemSanPham sanPham : sanPhams) {
            if (sanPham.getMaSanPham().contains("s")) {
                sachs.add(sanPham);
            } else if (sanPham.getMaSanPham().contains("vpp")) {
                vanPhongPhams.add(sanPham);
            }
        }
        layoutMHCKH_spnSanPham.setSelection(0);

        layoutMHCKH_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sanPhams.clear();
                        sanPhams.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        sanPhams.clear();
                        sanPhams.addAll(sachs);
                        adapter.notifyDataSetChanged();

                        break;
                    case 2:
                        sanPhams.clear();
                        sanPhams.addAll(vanPhongPhams);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter.setOnItemClickListener(new ManHinhChinhKhachHangAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                ImageButton itemMHCKH_btnThemGioHang = view.findViewById(R.id.itemMHCKH_btnThemGioHang);
                EditText itemMHCKH_edtSoLuong = view.findViewById(R.id.itemMHCKH_edtSoLuong);
                itemMHCKH_btnThemGioHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireBaseNhaSachOnline.themVaoGioHang(maKhachHang, sanPhams.get(position).getMaSanPham(), itemMHCKH_edtSoLuong.getText().toString());
                        Intent intent = new Intent(ManHinhChinhKhachHangActivity.this, GioHangActivity.class);
                        intent.putExtra("maSanPham", sanPhams.get(position).getMaSanPham());
                        ManHinhChinhKhachHangActivity.this.startActivity(intent);
                    }
                });

                ImageView itemMHCKH_imgAnhSanPham = view.findViewById(R.id.itemMHCKH_imgAnhSanPham);
                itemMHCKH_imgAnhSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhChinhKhachHangActivity.this, ChiTietSanPhamActivity.class);
                        intent.putExtra("sanPham", sanPhams.get(position));
                        ManHinhChinhKhachHangActivity.this.startActivity(intent);
                    }
                });
            }
        });
    }

    public void filterList(String newText) {
        ArrayList<ItemSanPham> fiIteredList = new ArrayList<>();
        for (ItemSanPham sanPham : sanPhams) {
            if (sanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(sanPham);
            }
        }
        if (!fiIteredList.isEmpty()) {
            adapter.setFilteredList(fiIteredList);
        }
    }

    public void timKiem() {
        layoutMHCKH_swTimKiem.clearFocus();
        layoutMHCKH_swTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.menu_ManHinhChinhKH:
                    return true;
                case R.id.menu_TheoDoiDonHang:
                    Intent intentTDDH = new Intent(ManHinhChinhKhachHangActivity.this, TheoDoiDonHangActivity.class);
                    startActivity(intentTDDH);
                    break;
                case R.id.menu_GioHang:
                    Intent intentGH = new Intent(ManHinhChinhKhachHangActivity.this, GioHangActivity.class);
                    startActivity(intentGH);
                    break;
                case R.id.menu_LichSuMuaHang:
                    Intent intentLSMH = new Intent(ManHinhChinhKhachHangActivity.this, LichSuMuaHangActivity.class);
                    startActivity(intentLSMH);
                    break;
                case R.id.menu_ThongTin:
                    Intent intentTTKH = new Intent(ManHinhChinhKhachHangActivity.this, ThongTinKhachHangActivity.class);
                    startActivity(intentTTKH);
                    break;
            }
            return false;
        }
    };
}

