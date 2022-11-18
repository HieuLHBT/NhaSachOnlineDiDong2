package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.SanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.ArrayList;

public class ManHinhQuanLySanPhamActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maSanPHam;

    private SearchView timkiemSP;
    private ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    private SanPhamRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.manhinh_quanly_sanpham_layout);
        //search
        timkiemSP = findViewById(R.id.layoutMHQLSP_swTimKiem);
        timKiem();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutMHQLSP_rvDanhSachSanPham);
        adapter = new SanPhamRecyclerViewAdapter(this,R.layout.manhinh_quanly_sanpham_item, sanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
                //itemTouchHelper.startDrag(viewHolder);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder b = new AlertDialog.Builder(ManHinhQuanLySanPhamActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn xóa Sản Phẩm Này không");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.xoaSanPham(sanPhams.get(position).getMaSanPham(), adapter);
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        fireBase.hienThiManHinhChinhQuanLySanPham(sanPhams,adapter,this);

        adapter.setOnItemClickListener(new SanPhamRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Button item_btnTroVe = view.findViewById(R.id.layoutMHQLSP_btnBack);
                item_btnTroVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                Button itemMHQLSP_btnThem = view.findViewById(R.id.itemMHQLSP_btnThem);
                itemMHQLSP_btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(ManHinhQuanLySanPhamActivity.this, ThemSanPhamSachActivity.class);
                        startActivity(intent1);
                    }
                });
                CardView itemMHQLSP = view.findViewById(R.id.itemMHQLSP);
                itemMHQLSP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhQuanLySanPhamActivity.this, SuaSanPhamSachActivity.class);
                        ManHinhQuanLySanPhamActivity.this.startActivity(intent);
                    }
                });
            }
        });
    }
    public void filterList(String newText) {
        ArrayList<ItemSanPham> fiIteredList = new ArrayList<>();
        for(ItemSanPham sanPham : sanPhams){
            if(sanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase()) || sanPham.getMaSanPham().toLowerCase().contains(newText.toLowerCase())){
                fiIteredList.add(sanPham);
            }
        }

        if(fiIteredList.isEmpty()){
            Toast.makeText(this,"Không có dữ liệu",Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList1(fiIteredList);
        }
    }

    public void timKiem(){
        timkiemSP.clearFocus();
        timkiemSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
