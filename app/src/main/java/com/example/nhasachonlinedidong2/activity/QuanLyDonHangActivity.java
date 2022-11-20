package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.LichSuMuaHangDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLyDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_DonHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_DonHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_SanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;


public class QuanLyDonHangActivity extends AppCompatActivity {
    private TextView layoutQLDH_btnTroVe;
    private Spinner layoutQLDH_spnSanPham;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private QuanLyDonHangRecyclerViewAdapter adapter;
    private ArrayList<QuanLyDonHang_DonHang> quanLyDonHang_donHangs = new ArrayList<>();
    private ArrayList<QuanLyDonHang_DonHang> tatCa = new ArrayList<>();
    private ArrayList<QuanLyDonHang_DonHang> donCanDuyet = new ArrayList<>();
    private ArrayList<QuanLyDonHang_DonHang> donCanGiao = new ArrayList<>();

    private String maNhanVien;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.quanlydonhang_layout);

        maNhanVien = sharePreferences.layMa(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLDH_rvQuanLyDonHang);
        layoutQLDH_btnTroVe = findViewById(R.id.layoutQLDH_btnTroVe);
        layoutQLDH_spnSanPham = findViewById(R.id.layoutQLDH_spnSanPham);

        ArrayList<String> data = new ArrayList<>();
        data.add("Tất cả");
        data.add("Đơn duyệt");
        data.add("Đơn giao");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.phanhoiykienkhachhang_spinner, data);
        layoutQLDH_spnSanPham.setAdapter(arrayAdapter);

        adapter = new QuanLyDonHangRecyclerViewAdapter(this, R.layout.quanlydonhang_donhang_item, quanLyDonHang_donHangs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new QuanLyDonHangRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemQLDH_btnDuyetDon = view.findViewById(R.id.itemQLDH_btnDuyetDon);
                Button itemQLDH_btnHuyDon = view.findViewById(R.id.itemQLDH_btnHuyDon);
                Button itemQLDH_btnGiaoHang = view.findViewById(R.id.itemQLDH_btnGiaoHang);

                itemQLDH_btnDuyetDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder b = new AlertDialog.Builder(QuanLyDonHangActivity.this);
                        b.setTitle("THÔNG BÁO");
                        b.setMessage("Bạn xác nhận duyệt đơn hàng không?");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String danhSach = "";
                                for (QuanLyDonHang_SanPham sanPham : quanLyDonHang_donHangs.get(position).getQuanLyDonHang_sanPhams()) {
                                    if (sanPham.getSoLuongBan() > sanPham.getSoLuongKho()) {
                                        danhSach += "\nMã sản phẩm: " + sanPham.getMaSanPham() + " (sl kho: " + sanPham.getSoLuongKho() + ", sl bán: " + sanPham.getSoLuongBan() + ")";
                                    }
                                }
                                if (!danhSach.equalsIgnoreCase("")) {
                                    AlertDialog.Builder b = new AlertDialog.Builder(QuanLyDonHangActivity.this);
                                    b.setTitle("CẢNH BÁO");
                                    b.setMessage("Đơn hàng không đủ số lượng! Vui lòng kiểm tra danh sách và liên hệ với quản lý:" + danhSach);
                                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    AlertDialog al = b.create();
                                    al.show();
                                } else {
                                    fireBaseNhaSachOnline.duyetDonHang(maNhanVien, quanLyDonHang_donHangs.get(position).getMaDonHang(), quanLyDonHang_donHangs.get(position).getQuanLyDonHang_sanPhams(), QuanLyDonHangActivity.this);
                                }
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
                    }
                });

                itemQLDH_btnGiaoHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder b = new AlertDialog.Builder(QuanLyDonHangActivity.this);
                        b.setTitle("THÔNG BÁO");
                        b.setMessage("Bạn xác nhận đã giao đơn hàng cho khách hàng?");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fireBaseNhaSachOnline.giaoHang(quanLyDonHang_donHangs.get(position).getMaDonHang(), QuanLyDonHangActivity.this);
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
                    }
                });

                itemQLDH_btnHuyDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuanLyDonHangActivity.this, HuyDonHangActivity.class);
                        intent.putExtra("maDonHang", quanLyDonHang_donHangs.get(position).getMaDonHang());
                        startActivity(intent);
                    }
                });
            }
        });

        layoutQLDH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiQuanLyDonHang(quanLyDonHang_donHangs, adapter, this);
    }

    public void suKien() {
        tatCa.clear();
        tatCa.addAll(quanLyDonHang_donHangs);
        donCanDuyet.clear();
        donCanGiao.clear();
        for (QuanLyDonHang_DonHang quanLyDonHang_donHang : quanLyDonHang_donHangs) {
            if (!quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") && quanLyDonHang_donHang.getTrangThaiDuyetNV().equalsIgnoreCase("Đang xử lý")) {
                donCanDuyet.add(quanLyDonHang_donHang);
            } else if (!quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") && !quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Thành công") && !quanLyDonHang_donHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("")) {
                donCanGiao.add(quanLyDonHang_donHang);
            }
        }
        if (donCanDuyet.size() != 0) {
            layoutQLDH_spnSanPham.setSelection(1);
            quanLyDonHang_donHangs.clear();
            quanLyDonHang_donHangs.addAll(donCanDuyet);
            adapter.notifyDataSetChanged();
        } else if (donCanGiao.size() != 0) {
            layoutQLDH_spnSanPham.setSelection(2);
            quanLyDonHang_donHangs.clear();
            quanLyDonHang_donHangs.addAll(donCanGiao);
            adapter.notifyDataSetChanged();
        } else {
            layoutQLDH_spnSanPham.setSelection(0);
            quanLyDonHang_donHangs.clear();
            quanLyDonHang_donHangs.addAll(tatCa);
            adapter.notifyDataSetChanged();
        }

        layoutQLDH_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        quanLyDonHang_donHangs.clear();
                        quanLyDonHang_donHangs.addAll(tatCa);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        quanLyDonHang_donHangs.clear();
                        quanLyDonHang_donHangs.addAll(donCanDuyet);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        quanLyDonHang_donHangs.clear();
                        quanLyDonHang_donHangs.addAll(donCanGiao);
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
