package com.example.nhasachonlinedidong2.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.Sach;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.QuanLySanPham_SanPham;
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
    private TextView layoutSS_btnTroVe, layoutSS_tvMaSach, layoutSS_tvSoLuongKho;
    private EditText layoutSS_tvTenSach,
            layoutSS_tvTheLoai,
            layoutSS_tvTacGia,
            layoutSS_tvNhaXuatBan,
            layoutSS_tvNgayXuatBan,
            layoutSS_tvGiaTien,
            layoutSS_tvKhuyenMai;
    private ImageView layoutSS_imgHinhSach;
    private Button layoutSS_btnSua, layoutSS_btnNhapMoi;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private QuanLySanPham_SanPham sanPham = new QuanLySanPham_SanPham();
    private Sach sach = new Sach();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";
    private Boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suasach_layout);

        sanPham = (QuanLySanPham_SanPham) getIntent().getSerializableExtra("sanPham");
        sach.setHinhSach(sanPham.getHinhSanPham());
        sach.setMaSach(sanPham.getMaSanPham());
        sach.setTenSach(sanPham.getTenSanPham());
        sach.setTheLoai(sanPham.getTheLoai());
        sach.setTacGia(sanPham.getTacGia());
        sach.setNhaXuatBan(sanPham.getNhaXuatBan());
        sach.setNgayXuatBan(sanPham.getNgayXuatBan());
        sach.setGiaTien(String.valueOf(sanPham.getGiaSanPham()));
        sach.setKhuyenMai(String.valueOf(sanPham.getKhuyenMai()));
        sach.setSoLuongKho(String.valueOf(sanPham.getSoLuongKho()));

        layoutSS_btnTroVe = findViewById(R.id.layoutSS_btnTroVe);
        layoutSS_imgHinhSach = findViewById(R.id.layoutSS_imgHinhSach);
        layoutSS_tvMaSach = findViewById(R.id.layoutSS_tvMaSach);
        layoutSS_tvTenSach = findViewById(R.id.layoutSS_tvTenSach);
        layoutSS_tvTheLoai = findViewById(R.id.layoutSS_tvTheLoai);
        layoutSS_tvTacGia = findViewById(R.id.layoutSS_tvTacGia);
        layoutSS_tvNhaXuatBan = findViewById(R.id.layoutSS_tvNhaXuatBan);
        layoutSS_tvNgayXuatBan = findViewById(R.id.layoutSS_tvNgayXuatBan);
        layoutSS_tvGiaTien = findViewById(R.id.layoutSS_tvGiaTien);
        layoutSS_tvKhuyenMai = findViewById(R.id.layoutSS_tvKhuyenMai);
        layoutSS_tvSoLuongKho = findViewById(R.id.layoutSS_tvSoLuongKho);
        layoutSS_btnSua = findViewById(R.id.layoutSS_btnSua);
        layoutSS_btnNhapMoi = findViewById(R.id.layoutSS_btnNhapMoi);

        hienThiThongTinSach();

        layoutSS_tvTenSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTenSach(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvTheLoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTheLoai(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvTacGia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTacGia(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvNhaXuatBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setNhaXuatBan(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvNgayXuatBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setNgayXuatBan(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvGiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setGiaTien(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_tvKhuyenMai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setKhuyenMai(String.valueOf(s));
                check = true;
            }
        });

        layoutSS_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đồng ý xác nhận sửa sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            if (check) {
                                fireBaseNhaSachOnline.suaSach(sach, SuaSachActivity.this);
                                // Tai anh len storage
                                ghiAnh(uri, sach.getHinhSach());
                            } else {
                                AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
                                b.setTitle("CẢNH BÁO");
                                b.setMessage("Sản phẩm không có sự thay đổi! Sửa không thành công!");
                                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                AlertDialog al = b.create();
                                al.show();
                            }
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

        layoutSS_imgHinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
                b.setTitle("THÊM HÌNH SÁCH");
                String[] ca = {"Chọn từ thư viên", "Chụp ảnh"};
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
                        check = true;
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

        layoutSS_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiThongTinSach();
                check = false;
            }
        });

        layoutSS_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void hienThiThongTinSach() {
        layoutSS_tvMaSach.setText(sanPham.getMaSanPham());
        layoutSS_tvTenSach.setText(sanPham.getTenSanPham());
        layoutSS_tvTheLoai.setText(sanPham.getTheLoai());
        layoutSS_tvTacGia.setText(sanPham.getTacGia());
        layoutSS_tvNhaXuatBan.setText(sanPham.getNhaXuatBan());
        layoutSS_tvNgayXuatBan.setText(sanPham.getNgayXuatBan());
        layoutSS_tvGiaTien.setText(String.valueOf(sanPham.getGiaSanPham()));
        layoutSS_tvKhuyenMai.setText(String.valueOf(sanPham.getKhuyenMai()));
        layoutSS_tvSoLuongKho.setText(String.valueOf(sanPham.getSoLuongKho()));
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sanPham.getHinhSanPham());
        try {
            File file = null;
            if (sanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(sanPham.getHinhSanPham().substring(0, sanPham.getHinhSanPham().length() - 4), "png");
            } else if (sanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(sanPham.getHinhSanPham().substring(0, sanPham.getHinhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutSS_imgHinhSach.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            layoutSS_imgHinhSach.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(SuaSachActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutSS_imgHinhSach.setImageURI(uri);
        }
    }

    private void ghiAnh(Uri filePath, String hinhSach) {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(hinhSach);
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

    protected boolean kiemTra() {
        boolean boolTenSach = !layoutSS_tvTenSach.getText().toString().isEmpty();
        boolean boolTheLoai = !layoutSS_tvTheLoai.getText().toString().isEmpty();
        boolean boolTacGia = !layoutSS_tvTacGia.getText().toString().isEmpty();
        boolean boolNhaXuatBan = !layoutSS_tvNhaXuatBan.getText().toString().isEmpty();
        boolean boolNgayXuatBan = !layoutSS_tvNgayXuatBan.getText().toString().isEmpty();
        boolean boolGiaTien = !layoutSS_tvGiaTien.getText().toString().isEmpty();
        boolean boolKhuyenMai = !layoutSS_tvKhuyenMai.getText().toString().isEmpty();
        boolean boolHinhSach = layoutSS_imgHinhSach.getDrawable() != null;

        if (!boolTenSach) {
            layoutSS_tvTenSach.setError("Không được bỏ trống");
        }
        if (!boolTheLoai) {
            layoutSS_tvTheLoai.setError("Không được bỏ trống");
        }
        if (!boolTacGia) {
            layoutSS_tvTacGia.setError("Không được bỏ trống");
        }
        if (!boolNhaXuatBan) {
            layoutSS_tvNhaXuatBan.setError("Không được bỏ trống");
        }
        if (!boolNgayXuatBan) {
            layoutSS_tvNgayXuatBan.setError("Không được bỏ trống");
        }
        if (!boolGiaTien) {
            layoutSS_tvGiaTien.setError("Không được bỏ trống");
        }
        if (!boolKhuyenMai) {
            layoutSS_tvKhuyenMai.setError("Không được bỏ trống ");
        }
        if (!boolHinhSach) {
            AlertDialog.Builder b = new AlertDialog.Builder(SuaSachActivity.this);
            b.setTitle("CẢNH BÁO");
            b.setMessage("Bạn cần phải chọn hình ảnh cho sản phẩm!");
            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog al = b.create();
            al.show();
        }
        return boolTenSach && boolTheLoai && boolTacGia && boolNhaXuatBan && boolNgayXuatBan && boolGiaTien && boolKhuyenMai && boolHinhSach;
    }
}
