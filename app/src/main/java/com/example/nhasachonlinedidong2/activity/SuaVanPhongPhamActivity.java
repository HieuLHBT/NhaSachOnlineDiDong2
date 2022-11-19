package com.example.nhasachonlinedidong2.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.Sach;
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaVanPhongPhamActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    private String maSanPham;

    private VanPhongPham vanPhongPham = new VanPhongPham();

    private EditText layoutSuaVanPhongPham_edtMaVPP;
    private EditText layoutSuaVanPhongPham_edtTenVPP;
    private EditText layoutSuaVanPhongPham_edtNhaPhanPhoi;
    private EditText layoutSuaVanPhongPham_edtXuatXu;
    private EditText layoutSuaVanPhongPham_edtDonVi;
    private EditText layoutSuaVanPhongPham_edtDonGia;
    private EditText layoutSuaVanPhongPham_edtSoLuongTonKho;
    private ImageView layoutSuaVanPhongPham_imgHinhVanPhongPham;
    private Button layoutSuaVanPhongPham_btnNhapMoi;
    private Button layoutSuaVanPhongPham_btnSua;
    private Button layoutSuaVanPhongPham_btnTroVe;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suasanpham_vanphongpham_layout);

        maSanPham = getIntent().getStringExtra("maSanPham");


        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        layoutSuaVanPhongPham_edtMaVPP = findViewById(R.id.layoutSuaVanPhongPham_edtMaVPP);
        layoutSuaVanPhongPham_edtTenVPP = findViewById(R.id.layoutSuaVanPhongPham_edtTenVPP);
        layoutSuaVanPhongPham_edtNhaPhanPhoi = findViewById(R.id.layoutSuaVanPhongPham_edtNhaPhanPhoi);
        layoutSuaVanPhongPham_edtXuatXu = findViewById(R.id.layoutSuaVanPhongPham_edtXuatXu);
        layoutSuaVanPhongPham_edtDonVi = findViewById(R.id.layoutSuaVanPhongPham_edtDonVi);
        layoutSuaVanPhongPham_edtDonGia = findViewById(R.id.layoutSuaVanPhongPham_edtDonGia);
        layoutSuaVanPhongPham_edtSoLuongTonKho = findViewById(R.id.layoutSuaVanPhongPham_edtSoLuongTonKho);
        layoutSuaVanPhongPham_imgHinhVanPhongPham = findViewById(R.id.layoutSuaVanPhongPham_imgHinhVanPhongPham);
        layoutSuaVanPhongPham_btnNhapMoi = findViewById(R.id.layoutSuaVanPhongPham_btnNhapMoi);
        layoutSuaVanPhongPham_btnSua = findViewById(R.id.layoutSuaVanPhongPham_btnSua);
        layoutSuaVanPhongPham_btnTroVe = findViewById(R.id.layoutSuaVanPhongPham_btnTroVe);

        fireBase.hienThiVanPhongPham(maSanPham, vanPhongPham, this);


        layoutSuaVanPhongPham_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn sửa thông tin của văn phòng phẩm không?");

                b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.suaSanPhamVPP(SuaVanPhongPhamActivity.this,
                                maSanPham,
                                layoutSuaVanPhongPham_imgHinhVanPhongPham.getResources().toString(),
                                layoutSuaVanPhongPham_edtTenVPP.getText().toString(),
                                layoutSuaVanPhongPham_edtNhaPhanPhoi.getText().toString(),
                                layoutSuaVanPhongPham_edtXuatXu.getText().toString(),
                                layoutSuaVanPhongPham_edtDonVi.getText().toString(),
                                layoutSuaVanPhongPham_edtDonGia.getText().toString(),
                                layoutSuaVanPhongPham_edtSoLuongTonKho.getText().toString()
                        );

                        //Kiểm tra các trường bỏ trống
                        if (layoutSuaVanPhongPham_imgHinhVanPhongPham.getTouchables().isEmpty() || layoutSuaVanPhongPham_edtXuatXu.getTouchables().isEmpty() || layoutSuaVanPhongPham_edtSoLuongTonKho.getTouchables().isEmpty() ||
                                layoutSuaVanPhongPham_edtTenVPP.getTouchables().isEmpty() || layoutSuaVanPhongPham_edtDonVi.getTouchables().isEmpty() ||
                                layoutSuaVanPhongPham_edtNhaPhanPhoi.getTouchables().isEmpty() || layoutSuaVanPhongPham_edtDonGia.getTouchables().isEmpty()) {
                            Toast.makeText(SuaVanPhongPhamActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }
                        // Tai anh len storage
                        ghiAnh(uri, layoutSuaVanPhongPham_edtMaVPP.getText().toString());
                        Toast.makeText(SuaVanPhongPhamActivity.this, "Sửa văn phòng phẩm thành công", Toast.LENGTH_SHORT).show();
                    }
                });


                b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });

        layoutSuaVanPhongPham_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSuaVanPhongPham_edtTenVPP.setText("");
                layoutSuaVanPhongPham_edtNhaPhanPhoi.setText("");
                layoutSuaVanPhongPham_edtXuatXu.setText("");
                layoutSuaVanPhongPham_edtDonVi.setText("");
                layoutSuaVanPhongPham_edtDonGia.setText("");
            }
        });

        layoutSuaVanPhongPham_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutSuaVanPhongPham_imgHinhVanPhongPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
                b.setTitle("SỬA HÌNH VĂN PHÒNG PHẨM");
                String[] ca = {"Chọn từ thư viện", "Chụp ảnh"};
                b.setSingleChoiceItems(ca, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chonAnh = "Thư viện";
                                break;
                            case 1:
                                chonAnh = "Camera";
                                break;
                        }
                    }
                });
                b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chonAnh.equalsIgnoreCase("Thư viện")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, PICK_IMAGE_REQUEST);
                        } else if (chonAnh.equalsIgnoreCase("Camera")) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                        }
                        chonAnh = "Thư viện";
                    }
                });
                b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            layoutSuaVanPhongPham_imgHinhVanPhongPham.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(SuaVanPhongPhamActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutSuaVanPhongPham_imgHinhVanPhongPham.setImageURI(uri);
        }
    }

    private void ghiAnh(Uri filePath, String maNhanVien) {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(maNhanVien + ".png");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }

    public void thongTinVanPhongPham(){
        layoutSuaVanPhongPham_edtMaVPP.setText(vanPhongPham.getMaVanPhongPham());
        layoutSuaVanPhongPham_edtTenVPP.setText(vanPhongPham.getTenVanPhongPham());
        layoutSuaVanPhongPham_edtNhaPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
        layoutSuaVanPhongPham_edtXuatXu.setText(vanPhongPham.getXuatXu());
        layoutSuaVanPhongPham_edtDonVi.setText(vanPhongPham.getDonVi());
        layoutSuaVanPhongPham_edtDonGia.setText(vanPhongPham.getGiaTien());
        layoutSuaVanPhongPham_edtSoLuongTonKho.setText(vanPhongPham.getSoLuongKho());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getHinhVanPhongPham());
        try {
            File file = null;
            if (vanPhongPham.getHinhVanPhongPham().contains("png")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()), "png");
            } else if (vanPhongPham.getHinhVanPhongPham().contains("jpg")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutSuaVanPhongPham_imgHinhVanPhongPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
