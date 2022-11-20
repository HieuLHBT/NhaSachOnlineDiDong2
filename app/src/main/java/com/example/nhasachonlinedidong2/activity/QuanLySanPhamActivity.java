
package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLySanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.GioHang;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.item.QuanLySanPham_SanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class QuanLySanPhamActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private String maQuanLy;

    private SearchView layoutQLSP_swTimKiem;
    private TextView layoutQLSP_btnTroVe, layoutQLSP_tvMucLuc;
    private Spinner layoutQLSP_spnSanPham;

    private ArrayList<QuanLySanPham_SanPham> sanPhams = new ArrayList<>();
    private ArrayList<QuanLySanPham_SanPham> tatCa = new ArrayList<>();
    private ArrayList<QuanLySanPham_SanPham> sachs = new ArrayList<>();
    private ArrayList<QuanLySanPham_SanPham> vanPhongPhams = new ArrayList<>();
    private QuanLySanPhamRecyclerViewAdapter adapter;

    private KhachHang khachHang = new KhachHang();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlysanpham_layout);
        maQuanLy = sharePreferences.layMa(this);

        //search
        layoutQLSP_btnTroVe = findViewById(R.id.layoutQLSP_btnTroVe);
        layoutQLSP_tvMucLuc = findViewById(R.id.layoutQLSP_tvMucLuc);
        layoutQLSP_spnSanPham = findViewById(R.id.layoutQLSP_spnSanPham);
        layoutQLSP_swTimKiem = findViewById(R.id.layoutQLSP_swTimKiem);

        timKiem();

        ArrayList<String> data = new ArrayList<>();
        data.add("Tất cả");
        data.add("Sách");
        data.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.phanhoiykienkhachhang_spinner, data);
        layoutQLSP_spnSanPham.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLSP_rvSanPham);
        adapter = new QuanLySanPhamRecyclerViewAdapter(this, R.layout.quanlysanpham_item, sanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutQLSP_tvMucLuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), layoutQLSP_tvMucLuc);
                popup.inflate(R.menu.quanlysanpham_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuQLSP_itemThemSach:
                                Intent intentTS = new Intent(QuanLySanPhamActivity.this, ThemSachActivity.class);
                                startActivity(intentTS);
                                break;
                            case R.id.menuQLSP_itemThemVanPhongPham:
                                Intent intentTVPP = new Intent(QuanLySanPhamActivity.this, ThemVanPhongPhamActivity.class);
                                startActivity(intentTVPP);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        layoutQLSP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiQuanLySanPham(sanPhams, adapter, this);
    }

    public void suKien() {
        tatCa.clear();
        tatCa.addAll(sanPhams);
        sachs.clear();
        vanPhongPhams.clear();
        for (QuanLySanPham_SanPham sanPham : sanPhams) {
            if (sanPham.getMaSanPham().contains("s")) {
                sachs.add(sanPham);
            } else if (sanPham.getMaSanPham().contains("vpp")) {
                vanPhongPhams.add(sanPham);
            }
        }
        layoutQLSP_spnSanPham.setSelection(0);

        layoutQLSP_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        adapter.setOnItemClickListener(new QuanLySanPhamRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                TextView itemQLSP_tvMucLuc = view.findViewById(R.id.itemQLSP_tvMucLuc);
                PopupMenu popup = new PopupMenu(view.getContext(), itemQLSP_tvMucLuc);
                popup.inflate(R.menu.quanlysanpham_item_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuQLSP_itemSuaSanPham:
                                if (sanPhams.get(position).getMaSanPham().contains("s")) {
                                    Intent intentSSP = new Intent(QuanLySanPhamActivity.this, SuaSachActivity.class);
                                    intentSSP.putExtra("sanPham", sanPhams.get(position));
                                    startActivity(intentSSP);
                                } else if (sanPhams.get(position).getMaSanPham().contains("vpp")) {
                                    Intent intentSSP = new Intent(QuanLySanPhamActivity.this, SuaVanPhongPhamActivity.class);
                                    intentSSP.putExtra("sanPham", sanPhams.get(position));
                                    startActivity(intentSSP);
                                }
                                break;
                            case R.id.menuQLSP_itemNhapKhoSanPham:
                                if (sanPhams.get(position).getMaSanPham().contains("s")) {
                                    Intent intentNKSP = new Intent(QuanLySanPhamActivity.this, NhapKhoSachActivity.class);
                                    intentNKSP.putExtra("sanPham", sanPhams.get(position));
                                    startActivity(intentNKSP);
                                } else if (sanPhams.get(position).getMaSanPham().contains("vpp")) {
                                    Intent intentNKSP = new Intent(QuanLySanPhamActivity.this, NhapKhoVanPhongPhamActivity.class);
                                    intentNKSP.putExtra("sanPham", sanPhams.get(position));
                                    startActivity(intentNKSP);
                                }
                                break;
                            case R.id.menuQLSP_itemXoaSanPham:
                                AlertDialog.Builder b = new AlertDialog.Builder(QuanLySanPhamActivity.this);
                                b.setTitle("THÔNG BÁO");
                                b.setMessage("Bạn xác nhận muốn xóa sản phẩm không?");
                                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        fireBaseNhaSachOnline.xoaSanPham(sanPhams.get(position), QuanLySanPhamActivity.this);
                                    }
                                });
                                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                AlertDialog al = b.create();
                                al.show();
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    public void filterList(String newText) {
        ArrayList<QuanLySanPham_SanPham> fiIteredList = new ArrayList<>();
        for (QuanLySanPham_SanPham sanPham : sanPhams) {
            if (sanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(sanPham);
            }
        }
        if (!fiIteredList.isEmpty()) {
            adapter.setFilteredList(fiIteredList);
        }
    }

    public void timKiem() {
        layoutQLSP_swTimKiem.clearFocus();
        layoutQLSP_swTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
}

