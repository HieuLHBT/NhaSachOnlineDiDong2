package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.QuanLyNhanVienRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;

import com.example.nhasachonlinedidong2.item.ItemNhanVien;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maNhanVien;

    private SearchView timkiemNV;
    private ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();
    private QuanLyNhanVienRecyclerViewAdapter adapter;
    private Spinner layout_spnNhanVien;
    private TextView item_tvTroVe;
    private TextView item_tvThemNhanVien;
    private CardView itemMHQLNV_SuaNhanVien;
    ItemTouchHelper itemTouchHelper;
    ItemTouchHelper itemTouchHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_quanly_nhanvien_layout);
        setControl();
        setEvent();
        //search
        timkiemNV = findViewById(R.id.layoutMHQLNV_swTimKiem);
        timKiem();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHQLNV_rvDanhSachNhanVien);
        adapter = new QuanLyNhanVienRecyclerViewAdapter(this, R.layout.manhinh_quanly_nhanvien_item, nhanViens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                itemTouchHelper.startDrag(viewHolder);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder b = new AlertDialog.Builder(QuanLyNhanVienActivity.this);
                b.setTitle("C???NH B??O");
                b.setMessage("B???n c?? mu???n x??a nh??n vi??n ra kh???i cty kh??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.xoaNhanVien(nhanViens.get(position).getMaNhanVien(), adapter);

                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.setCancelable(false);
                al.show();
            }
        });
        itemTouchHelper2 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                itemTouchHelper.startDrag(viewHolder);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder b = new AlertDialog.Builder(QuanLyNhanVienActivity.this);
                b.setTitle("C???NH B??O");
                b.setMessage("B???n c?? mu???n ??i ?????n s???a nh??n vi??n n??y kh??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(QuanLyNhanVienActivity.this, SuaNhanVienActivity.class);
                        intent.putExtra("maNhanVien", nhanViens.get(position).getMaNhanVien());
                        QuanLyNhanVienActivity.this.startActivity(intent);

                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.setCancelable(false);
                al.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        itemTouchHelper2.attachToRecyclerView(recyclerView);

        fireBase.hienThiManHinhChinhQuanLyNhanVien(nhanViens, adapter, this);

        adapter.setOnItemClickListener(new QuanLyNhanVienRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Intent intent = new Intent(QuanLyNhanVienActivity.this, SuaNhanVienActivity.class);
                intent.putExtra(maNhanVien, nhanViens.get(position).getMaNhanVien());
                QuanLyNhanVienActivity.this.startActivity(intent);
            }
        });
    }

    private void setEvent() {
        //BACK
        item_tvTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //ADD
        item_tvThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyNhanVienActivity.this, ThemNhanVienActivity.class);
                QuanLyNhanVienActivity.this.startActivity(intent);
            }
        });

    }

    private void setControl() {
        item_tvTroVe = findViewById(R.id.MHQLNV_tvTroVe);
        item_tvThemNhanVien = findViewById(R.id.layoutMHQLNV_tvThemNhanVien);

    }

    public void filterList(String newText) {
        ArrayList<ItemNhanVien> fiIteredList = new ArrayList<>();
        for (ItemNhanVien nhanVien : nhanViens) {
            if (nhanVien.getTenNhanVien().toLowerCase().contains(newText.toLowerCase()) || nhanVien.getMaNhanVien().toLowerCase().contains(newText.toLowerCase())) {
                fiIteredList.add(nhanVien);
            }
        }

        if (fiIteredList.isEmpty()) {
            Toast.makeText(this, "Kh??ng c?? d??? li???u", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList1(fiIteredList);
        }
    }

    public void timKiem() {
        timkiemNV.clearFocus();
        timkiemNV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        adapter.notifyDataSetChanged();
    }
}
