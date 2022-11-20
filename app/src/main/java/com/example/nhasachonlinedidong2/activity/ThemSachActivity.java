package com.example.nhasachonlinedidong2.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSach;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ThemSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemSach> sachs = new ArrayList<>();

    private EditText layoutThemSach_edtMaSach;
    private EditText layoutThemSach_edtTenSach;
    private EditText layoutThemSach_edtTheLoai;
    private EditText layoutThemSach_edtTacGia;
    private EditText layoutThemSach_edtNhaXuatBan;
    private EditText layoutThemSach_edtNgayXuatBan;
    private EditText layoutThemSach_edtDonGia;
    private EditText layoutThemSach_edtSoLuongTonKho;
    private ImageView layoutThemSach_imgHinhSach;
    private Button layoutThemSach_btnNhapMoi;
    private Button layoutThemSach_btnThemSach;
    private Button layoutThemSach_btnTroVe;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsanpham_sach_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        layoutThemSach_edtMaSach = findViewById(R.id.layoutThemSach_edtMaSach);
        layoutThemSach_edtTenSach = findViewById(R.id.layoutThemSach_edtTenSach);
        layoutThemSach_edtTheLoai = findViewById(R.id.layoutThemSach_edtTheLoai);
        layoutThemSach_edtTacGia = findViewById(R.id.layoutThemSach_edtTacGia);
        layoutThemSach_edtNhaXuatBan = findViewById(R.id.layoutThemSach_edtNhaXuatBan);
        layoutThemSach_edtNgayXuatBan = findViewById(R.id.layoutThemSach_edtNgayXuatBan);
        layoutThemSach_edtDonGia = findViewById(R.id.layoutThemSach_edtDonGia);
        layoutThemSach_edtSoLuongTonKho = findViewById(R.id.layoutThemSach_edtSoLuongTonKho);
        layoutThemSach_imgHinhSach = findViewById(R.id.layoutThemSach_imgHinhSach);
        layoutThemSach_btnNhapMoi = findViewById(R.id.layoutThemSach_btnNhapMoi);
        layoutThemSach_btnThemSach = findViewById(R.id.layoutThemSach_btnThemSach);
        layoutThemSach_btnTroVe = findViewById(R.id.layoutThemSach_btnTroVe);

        layoutThemSach_btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSachActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn thêm sách vào kho sản phẩm không?");

                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

        layoutThemSach_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutThemSach_edtTenSach.setText("");
                layoutThemSach_edtTheLoai.setText("");
                layoutThemSach_edtTacGia.setText("");
                layoutThemSach_edtNhaXuatBan.setText("");
                layoutThemSach_edtNgayXuatBan.setText("");
                layoutThemSach_edtDonGia.setText("");
                layoutThemSach_edtSoLuongTonKho.setText("");
            }
        });

        layoutThemSach_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutThemSach_imgHinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSachActivity.this);
                b.setTitle("THÊM HÌNH SÁCH");
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
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
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
            layoutThemSach_imgHinhSach.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemSachActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutThemSach_imgHinhSach.setImageURI(uri);
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
}
