package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.DanhGiaSanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.data_model.PhanHoi;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.DanhGiaSanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DanhGiaSanPhamActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private ArrayList<DanhGiaSanPham> danhGiaSanPhams = new ArrayList<>();
    private DanhGiaSanPhamRecyclerViewAdapter adapter;

    private String maKhachHang;
    private String maDonHang;
    private String maSanPham = "";
    private String binhLuan = "Like!";
    private int danhGia = 0;
    private int viTri = -1;

    private ImageButton itemDGSP_btn1Sao, itemDGSP_btn2Sao, itemDGSP_btn3Sao, itemDGSP_btn4Sao, itemDGSP_btn5Sao;
    private Button itemDGSP_btnDanhGia;
    private TextView layoutDGSP_btnTroVe;
    private EditText layoutDGSP_edtBinhLuan;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.danhgiasanpham_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutDGSP_rvDanhGiaSanPham);

        maKhachHang = sharePreferences.layMa(this);
        maDonHang = getIntent().getStringExtra("maDonHang");

        itemDGSP_btn1Sao = findViewById(R.id.itemDGSP_btn1Sao);
        itemDGSP_btn2Sao = findViewById(R.id.itemDGSP_btn2Sao);
        itemDGSP_btn3Sao = findViewById(R.id.itemDGSP_btn3Sao);
        itemDGSP_btn4Sao = findViewById(R.id.itemDGSP_btn4Sao);
        itemDGSP_btn5Sao = findViewById(R.id.itemDGSP_btn5Sao);
        itemDGSP_btnDanhGia = findViewById(R.id.itemDGSP_btnDanhGia);
        layoutDGSP_btnTroVe = findViewById(R.id.layoutDGSP_btnTroVe);
        layoutDGSP_edtBinhLuan = findViewById(R.id.layoutDGSP_edtBinhLuan);

        adapter = new DanhGiaSanPhamRecyclerViewAdapter(this, R.layout.danhgiasanpham_item, danhGiaSanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutDGSP_edtBinhLuan.setEnabled(false);
        itemDGSP_btn1Sao.setEnabled(false);
        itemDGSP_btn2Sao.setEnabled(false);
        itemDGSP_btn3Sao.setEnabled(false);
        itemDGSP_btn4Sao.setEnabled(false);
        itemDGSP_btn5Sao.setEnabled(false);
        itemDGSP_btnDanhGia.setEnabled(false);
        itemDGSP_btn1Sao.setVisibility(View.GONE);
        itemDGSP_btn2Sao.setVisibility(View.GONE);
        itemDGSP_btn3Sao.setVisibility(View.GONE);
        itemDGSP_btn4Sao.setVisibility(View.GONE);
        itemDGSP_btn5Sao.setVisibility(View.GONE);
        itemDGSP_btnDanhGia.setVisibility(View.GONE);

        adapter.setOnItemClickListener(new DanhGiaSanPhamRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                CardView itemDGSP = view.findViewById(R.id.itemDGSP);
                itemDGSP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (danhGiaSanPhams.get(position).getCheck()) {
                            viTri = -1;
                            danhGiaSanPhams.get(position).setCheck(false);
                            adapter.notifyDataSetChanged();
                            maSanPham = "";
                            danhGiaSanPham(0);
                            layoutDGSP_edtBinhLuan.getText().clear();
                            layoutDGSP_edtBinhLuan.setHint("Vui lòng chọn vào sản phẩm để bình luận");
                            binhLuan = "Like!";
                            layoutDGSP_edtBinhLuan.setEnabled(false);
                            itemDGSP_btn1Sao.setEnabled(false);
                            itemDGSP_btn2Sao.setEnabled(false);
                            itemDGSP_btn3Sao.setEnabled(false);
                            itemDGSP_btn4Sao.setEnabled(false);
                            itemDGSP_btn5Sao.setEnabled(false);
                            itemDGSP_btnDanhGia.setEnabled(false);
                            itemDGSP_btn1Sao.setVisibility(View.GONE);
                            itemDGSP_btn2Sao.setVisibility(View.GONE);
                            itemDGSP_btn3Sao.setVisibility(View.GONE);
                            itemDGSP_btn4Sao.setVisibility(View.GONE);
                            itemDGSP_btn5Sao.setVisibility(View.GONE);
                            itemDGSP_btnDanhGia.setVisibility(View.GONE);
                        } else {
                            if (viTri != -1) {
                                danhGiaSanPhams.get(viTri).setCheck(false);
                            }
                            viTri = position;
                            danhGiaSanPhams.get(position).setCheck(true);
                            adapter.notifyDataSetChanged();
                            if (danhGiaSanPhams.get(position).getDanhGia() != 0 || !danhGiaSanPhams.get(position).getBinhLuan().equalsIgnoreCase("")) {
                                danhGiaSanPham(danhGiaSanPhams.get(position).getDanhGia());
                                layoutDGSP_edtBinhLuan.setText(danhGiaSanPhams.get(position).getBinhLuan());
                                layoutDGSP_edtBinhLuan.setEnabled(false);
                                layoutDGSP_edtBinhLuan.setTextColor(Color.BLACK);
                                itemDGSP_btn1Sao.setEnabled(false);
                                itemDGSP_btn2Sao.setEnabled(false);
                                itemDGSP_btn3Sao.setEnabled(false);
                                itemDGSP_btn4Sao.setEnabled(false);
                                itemDGSP_btn5Sao.setEnabled(false);
                                itemDGSP_btnDanhGia.setEnabled(false);
                                itemDGSP_btnDanhGia.setText("Sản phẩm đã được bình luận, đánh giá");
                                binhLuan = "Like!";
                                itemDGSP_btn1Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn2Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn3Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn4Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn5Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btnDanhGia.setVisibility(View.VISIBLE);
                            } else {
                                danhGiaSanPham(0);
                                maSanPham = danhGiaSanPhams.get(position).getMaSanPham();
                                layoutDGSP_edtBinhLuan.getText().clear();
                                layoutDGSP_edtBinhLuan.setHint("Nhập bình luận tại đây!");
                                binhLuan = "Like!";
                                layoutDGSP_edtBinhLuan.setEnabled(true);
                                itemDGSP_btn1Sao.setEnabled(true);
                                itemDGSP_btn2Sao.setEnabled(true);
                                itemDGSP_btn3Sao.setEnabled(true);
                                itemDGSP_btn4Sao.setEnabled(true);
                                itemDGSP_btn5Sao.setEnabled(true);
                                itemDGSP_btnDanhGia.setEnabled(true);
                                itemDGSP_btnDanhGia.setText("Đánh giá");
                                itemDGSP_btn1Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn2Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn3Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn4Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btn5Sao.setVisibility(View.VISIBLE);
                                itemDGSP_btnDanhGia.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });

        itemDGSP_btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 0) {
                    AlertDialog.Builder b = new AlertDialog.Builder(DanhGiaSanPhamActivity.this);
                    b.setTitle("CẢNH BÁO!");
                    b.setMessage("Bạn cần phải đánh giá sản phẩm!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else {
                    DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String ngay = LocalDate.now().format(fm);
                    PhanHoi phanHoi = new PhanHoi(
                            binhLuan,
                            String.valueOf(danhGia),
                            maDonHang,
                            maKhachHang,
                            "",
                            maSanPham,
                            ngay,
                            "");
                    fireBaseNhaSachOnline.phanHoiCuaKhachHang(phanHoi, DanhGiaSanPhamActivity.this);
                }
            }
        });

        layoutDGSP_edtBinhLuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binhLuan = String.valueOf(s);
            }
        });

        itemDGSP_btn1Sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 1) {
                    danhGia = 0;
                } else {
                    danhGia = 1;
                }
                danhGiaSanPham(danhGia);
            }
        });

        itemDGSP_btn2Sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 2) {
                    danhGia = 0;
                } else {
                    danhGia = 2;
                }
                danhGiaSanPham(danhGia);
            }
        });

        itemDGSP_btn3Sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 3) {
                    danhGia = 0;
                } else {
                    danhGia = 3;
                }
                danhGiaSanPham(danhGia);
            }
        });

        itemDGSP_btn4Sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 4) {
                    danhGia = 0;
                } else {
                    danhGia = 4;
                }
                danhGiaSanPham(danhGia);
            }
        });

        itemDGSP_btn5Sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (danhGia == 5) {
                    danhGia = 0;
                } else {
                    danhGia = 5;
                }
                danhGiaSanPham(danhGia);
            }
        });

        layoutDGSP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiItemDanhGiaSanPham(maDonHang, danhGiaSanPhams, adapter, this);
    }

    public void capNhat() {
        danhGiaSanPhams.get(viTri).setCheck(false);
        viTri = -1;
        adapter.notifyDataSetChanged();
        maSanPham = "";
        danhGiaSanPham(0);
        layoutDGSP_edtBinhLuan.getText().clear();
        layoutDGSP_edtBinhLuan.setHint("Vui lòng chọn vào sản phẩm để bình luận");
        binhLuan = "Like!";
        layoutDGSP_edtBinhLuan.setEnabled(false);
        itemDGSP_btn1Sao.setEnabled(false);
        itemDGSP_btn2Sao.setEnabled(false);
        itemDGSP_btn3Sao.setEnabled(false);
        itemDGSP_btn4Sao.setEnabled(false);
        itemDGSP_btn5Sao.setEnabled(false);
        itemDGSP_btnDanhGia.setEnabled(false);
        itemDGSP_btn1Sao.setVisibility(View.GONE);
        itemDGSP_btn2Sao.setVisibility(View.GONE);
        itemDGSP_btn3Sao.setVisibility(View.GONE);
        itemDGSP_btn4Sao.setVisibility(View.GONE);
        itemDGSP_btn5Sao.setVisibility(View.GONE);
        itemDGSP_btnDanhGia.setVisibility(View.GONE);
        AlertDialog.Builder b = new AlertDialog.Builder(DanhGiaSanPhamActivity.this);
        b.setTitle("THÔNG BÁO!");
        b.setMessage("Bình luận, đánh giá thành công! Chọn sản phẩm khác để tiếp tục");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    private void danhGiaSanPham(int dg) {
        switch (dg) {
            case 0:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 1:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 2:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 3:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 4:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 5:
                itemDGSP_btn1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                itemDGSP_btn5Sao.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }
    }
}
