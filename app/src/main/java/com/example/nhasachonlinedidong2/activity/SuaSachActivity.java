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
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
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

public class SuaSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;

    private Sach sach = new Sach();

    private EditText layoutSuaSach_edtMaSach;
    private EditText layoutSuaSach_edtTenSach;
    private EditText layoutSuaSach_edtTheLoai;
    private EditText layoutSuaSach_edtTacGia;
    private EditText layoutSuaSach_edtNhaXuatBan;
    private EditText layoutSuaSach_edtNgayXuatBan;
    private EditText layoutSuaSach_edtDonGia;
    private EditText layoutSuaSach_edtSoLuongTonKho;
    private ImageView layoutSuaSach_imgHinhSach;
    private Button layoutSuaSach_btnNhapMoi;
    private Button layoutSuaSach_btnSua;
    private Button layoutSuaSach_btnTroVe;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suasanpham_sach_layout);

        maSanPham = getIntent().getStringExtra("maSanPham");


        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        layoutSuaSach_edtMaSach = findViewById(R.id.layoutSuaSach_edtMaSach);
        layoutSuaSach_edtTenSach = findViewById(R.id.layoutSuaSach_edtTenSach);
        layoutSuaSach_edtTheLoai = findViewById(R.id.layoutSuaSach_edtTheLoai);
        layoutSuaSach_edtTacGia = findViewById(R.id.layoutSuaSach_edtTacGia);
        layoutSuaSach_edtNhaXuatBan = findViewById(R.id.layoutSuaSach_edtNhaXuatBan);
        layoutSuaSach_edtNgayXuatBan = findViewById(R.id.layoutSuaSach_edtNgayXuatBan);
        layoutSuaSach_edtDonGia = findViewById(R.id.layoutSuaSach_edtDonGia);
        layoutSuaSach_edtSoLuongTonKho = findViewById(R.id.layoutSuaSach_edtSoLuongTonKho);
        layoutSuaSach_imgHinhSach = findViewById(R.id.layoutSuaSach_imgHinhSach);
        layoutSuaSach_btnNhapMoi = findViewById(R.id.layoutSuaSach_btnNhapMoi);
        layoutSuaSach_btnSua = findViewById(R.id.layoutSuaSach_btnSua);
        layoutSuaSach_btnTroVe = findViewById(R.id.layoutSuaSach_btnTroVe);

        fireBase.hienThiSach(maSanPham, sach, this);


        layoutSuaSach_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn sửa thông tin của sách không?");

                b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Kiểm tra các trường bỏ trống
                        if (layoutSuaSach_edtMaSach.getTouchables().isEmpty() || layoutSuaSach_edtTenSach.getTouchables().isEmpty() || layoutSuaSach_edtTheLoai.getTouchables().isEmpty() ||
                                layoutSuaSach_edtTacGia.getTouchables().isEmpty() || layoutSuaSach_edtNhaXuatBan.getTouchables().isEmpty() || layoutSuaSach_edtNgayXuatBan.getTouchables().isEmpty() ||
                                layoutSuaSach_edtDonGia.getTouchables().isEmpty() || layoutSuaSach_edtSoLuongTonKho.getTouchables().isEmpty()) {
                            Toast.makeText(SuaSachActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }
                        // Tai anh len storage
                        ghiAnh(uri, layoutSuaSach_edtMaSach.getText().toString());
                        Toast.makeText(SuaSachActivity.this, "Sửa sách thành công", Toast.LENGTH_SHORT).show();
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

        layoutSuaSach_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSuaSach_edtTenSach.setText("");
                layoutSuaSach_edtTheLoai.setText("");
                layoutSuaSach_edtTacGia.setText("");
                layoutSuaSach_edtNhaXuatBan.setText("");
                layoutSuaSach_edtNgayXuatBan.setText("");
                layoutSuaSach_edtDonGia.setText("");
            }
        });

        layoutSuaSach_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutSuaSach_imgHinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
                b.setTitle("SỬA HÌNH SÁCH");
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
            layoutSuaSach_imgHinhSach.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(SuaSachActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutSuaSach_imgHinhSach.setImageURI(uri);
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

    public void thongTinSach(){

        layoutSuaSach_edtMaSach.setText(sach.getMaSach());
        layoutSuaSach_edtTenSach.setText(sach.getTenSach());
        layoutSuaSach_edtTheLoai.setText(sach.getTheLoai());
        layoutSuaSach_edtTacGia.setText(sach.getTacGia());
        layoutSuaSach_edtNhaXuatBan.setText(sach.getNhaXuatBan());
        layoutSuaSach_edtNgayXuatBan.setText(sach.getNgayXuatBan());
        layoutSuaSach_edtDonGia.setText(sach.getGiaTien());
        layoutSuaSach_edtSoLuongTonKho.setText(sach.getSoLuongKho());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sach.getHinhSach());
        try {
            File file = null;
            if (sach.getHinhSach().contains("png")) {
                file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()), "png");
            } else if (sach.getHinhSach().contains("jpg")) {
                file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutSuaSach_imgHinhSach.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
