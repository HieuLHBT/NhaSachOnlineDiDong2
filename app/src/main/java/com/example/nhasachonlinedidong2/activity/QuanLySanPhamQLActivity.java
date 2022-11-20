package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.QuanLySanPhamQLRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemQuanLySanPhamQL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuanLySanPhamQLActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;
    private String maNhanVien;
    private QuanLySanPhamQLRecyclerViewAdapter adapter;
    private ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLS = new ArrayList<>();

    private Spinner layoutQLSP_QL_spnSanPham;
    private SearchView timKiemSP;
    private Button layoutQLSP_QL_btnTroVe;
    private Button layoutQLSP_QL_btnThemSach;
    private Button layoutQLSP_QL_btnThemVPP;

    ArrayList<String> dataSanPham = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlysanpham_ql_layout);

        // Search
        timKiemSP = findViewById(R.id.layoutQLSP_QL_swTimKiem);
        timKiem();

        layoutQLSP_QL_spnSanPham = findViewById(R.id.layoutQLSP_QL_spnSanPham);
        dataSanPham.add("Tất cả");
        dataSanPham.add("Sách");
        dataSanPham.add("Văn phòng phẩm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, dataSanPham);
        layoutQLSP_QL_spnSanPham.setAdapter(arrayAdapter);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutQLSP_QL_rvQuanLySanPhamQuanLy);
        adapter = new QuanLySanPhamQLRecyclerViewAdapter(this, R.layout.quanlysanpham_ql_item, itemQuanLySanPhamQLS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutQLSP_QL_btnTroVe = findViewById(R.id.layoutQLSP_QL_btnTroVe);
        layoutQLSP_QL_btnThemVPP = findViewById(R.id.layoutQLSP_QL_btnThemVPP);
        layoutQLSP_QL_btnThemSach = findViewById(R.id.layoutQLSP_QL_btnThemSach);

        layoutQLSP_QL_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

          layoutQLSP_QL_btnThemVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySanPhamQLActivity.this, ThemVanPhongPhamActivity.class);
                QuanLySanPhamQLActivity.this.startActivity(intent);
            }
        });
        layoutQLSP_QL_btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySanPhamQLActivity.this, ThemSachActivity.class);
                QuanLySanPhamQLActivity.this.startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new QuanLySanPhamQLRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button itemQLSP_QL_btnXoa = view.findViewById(R.id.itemQLSP_QL_btnXoa);
                Button itemQLSP_QL_btnSua = view.findViewById(R.id.itemQLSP_QL_btnSua);
                Button itemQLSP_QL_btnNhapKho = view.findViewById(R.id.itemQLSP_QL_btnNhapKho);
                if (itemQuanLySanPhamQLS.get(position).getMaSanPham().contains("s")) {
                    itemQLSP_QL_btnNhapKho.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(QuanLySanPhamQLActivity.this, NhapKhoSachActivity.class);
                            intent.putExtra("maSanPham", itemQuanLySanPhamQLS.get(position).getMaSanPham());
                            QuanLySanPhamQLActivity.this.startActivity(intent);
                        }
                    });
                }else if (itemQuanLySanPhamQLS.get(position).getMaSanPham().contains("vpp")) {
                    itemQLSP_QL_btnNhapKho.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(QuanLySanPhamQLActivity.this, NhapKhoVPPActivity.class);
                            intent.putExtra("maSanPham", itemQuanLySanPhamQLS.get(position).getMaSanPham());
                            QuanLySanPhamQLActivity.this.startActivity(intent);
                        }
                    });
                }

                itemQLSP_QL_btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fireBase.xoaSanPham(itemQuanLySanPhamQLS.get(position).getMaSanPham(), adapter);
                    }
                });
                if (itemQuanLySanPhamQLS.get(position).getMaSanPham().contains("s")) {
                    itemQLSP_QL_btnSua.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(QuanLySanPhamQLActivity.this, SuaSachActivity.class);
                            intent.putExtra("maSanPham", itemQuanLySanPhamQLS.get(position).getMaSanPham());
                            QuanLySanPhamQLActivity.this.startActivity(intent);
                        }
                    });
                } else if (itemQuanLySanPhamQLS.get(position).getMaSanPham().contains("vpp")) {
                    itemQLSP_QL_btnSua.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(QuanLySanPhamQLActivity.this, SuaVanPhongPhamActivity.class);
                            intent.putExtra("maSanPham", itemQuanLySanPhamQLS.get(position).getMaSanPham());
                            QuanLySanPhamQLActivity.this.startActivity(intent);
                        }
                    });
                }
            }
        });

        layoutQLSP_QL_spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        fireBase.hienThiItemQuanLySanPhamQL(itemQuanLySanPhamQLS, adapter, QuanLySanPhamQLActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        fireBase.hienThiItemSach(itemQuanLySanPhamQLS, adapter, QuanLySanPhamQLActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        fireBase.hienThiItemVanPhongPham(itemQuanLySanPhamQLS, adapter, QuanLySanPhamQLActivity.this);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void filterList(String newText) {
        ArrayList<ItemQuanLySanPhamQL> fiIteredList = new ArrayList<>();
        for (ItemQuanLySanPhamQL itemQuanLySanPhamQL : itemQuanLySanPhamQLS) {
            if (itemQuanLySanPhamQL.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(itemQuanLySanPhamQL);
            }
        }
        if (fiIteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(fiIteredList);
        }
    }

    public void timKiem() {
        timKiemSP.clearFocus();
        timKiemSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        fireBase.hienThiItemQuanLySanPhamQL(itemQuanLySanPhamQLS, adapter, this);
    }
}
