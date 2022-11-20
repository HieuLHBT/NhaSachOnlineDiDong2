package com.example.nhasachonlinedidong2.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ThemSachActivity extends AppCompatActivity {
    private TextView layoutTS_btnTroVe, layoutTS_tvMaSach;
    private EditText layoutTS_tvTenSach,
            layoutTS_tvTheLoai,
            layoutTS_tvTacGia,
            layoutTS_tvNhaXuatBan,
            layoutTS_tvNgayXuatBan,
            layoutTS_tvGiaTien,
            layoutTS_tvKhuyenMai,
            layoutTS_tvSoLuongKho;
    private ImageView layoutTS_imgHinhSach;
    private Button layoutTS_btnThem, layoutTS_btnNhapMoi;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private Sach sach = new Sach();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";
    private Boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsach_layout);

        layoutTS_btnTroVe = findViewById(R.id.layoutTS_btnTroVe);
        layoutTS_imgHinhSach = findViewById(R.id.layoutTS_imgHinhSach);
        layoutTS_tvMaSach = findViewById(R.id.layoutTS_tvMaSach);
        layoutTS_tvTenSach = findViewById(R.id.layoutTS_tvTenSach);
        layoutTS_tvTheLoai = findViewById(R.id.layoutTS_tvTheLoai);
        layoutTS_tvTacGia = findViewById(R.id.layoutTS_tvTacGia);
        layoutTS_tvNhaXuatBan = findViewById(R.id.layoutTS_tvNhaXuatBan);
        layoutTS_tvNgayXuatBan = findViewById(R.id.layoutTS_tvNgayXuatBan);
        layoutTS_tvGiaTien = findViewById(R.id.layoutTS_tvGiaTien);
        layoutTS_tvKhuyenMai = findViewById(R.id.layoutTS_tvKhuyenMai);
        layoutTS_tvSoLuongKho = findViewById(R.id.layoutTS_tvSoLuongKho);
        layoutTS_btnThem = findViewById(R.id.layoutTS_btnThem);
        layoutTS_btnNhapMoi = findViewById(R.id.layoutTS_btnNhapMoi);

        layoutTS_tvTenSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTenSach(String.valueOf(s));
            }
        });

        layoutTS_tvTheLoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTheLoai(String.valueOf(s));
            }
        });

        layoutTS_tvTacGia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setTacGia(String.valueOf(s));
            }
        });

        layoutTS_tvNhaXuatBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setNhaXuatBan(String.valueOf(s));
            }
        });

        layoutTS_tvNgayXuatBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setNgayXuatBan(String.valueOf(s));
            }
        });

        layoutTS_tvGiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setGiaTien(String.valueOf(s));
            }
        });

        layoutTS_tvKhuyenMai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setKhuyenMai(String.valueOf(s));
            }
        });

        layoutTS_tvSoLuongKho.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sach.setSoLuongKho(String.valueOf(s));
            }
        });

        layoutTS_btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSachActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đồng ý xác nhận thêm sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            fireBaseNhaSachOnline.themSach(sach, ThemSachActivity.this);
                            // Tai anh len storage
                            ghiAnh(uri, sach.getHinhSach());
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

        layoutTS_imgHinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemSachActivity.this);
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

        layoutTS_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutTS_tvTenSach.getText().clear();
                layoutTS_tvTheLoai.getText().clear();
                layoutTS_tvTacGia.getText().clear();
                layoutTS_tvNhaXuatBan.getText().clear();
                layoutTS_tvNgayXuatBan.getText().clear();
                layoutTS_tvGiaTien.getText().clear();
                layoutTS_tvKhuyenMai.getText().clear();
                layoutTS_tvSoLuongKho.getText().clear();
                layoutTS_imgHinhSach.setImageBitmap(null);
                check = false;
            }
        });

        layoutTS_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienMaSach(sach, this);
    }

    public void hienThiMaSach() {
        layoutTS_tvMaSach.setText(sach.getMaSach());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            layoutTS_imgHinhSach.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemSachActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutTS_imgHinhSach.setImageURI(uri);
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
        boolean boolTenSach = !layoutTS_tvTenSach.getText().toString().isEmpty();
        boolean boolTheLoai = !layoutTS_tvTheLoai.getText().toString().isEmpty();
        boolean boolTacGia = !layoutTS_tvTacGia.getText().toString().isEmpty();
        boolean boolNhaXuatBan = !layoutTS_tvNhaXuatBan.getText().toString().isEmpty();
        boolean boolNgayXuatBan = !layoutTS_tvNgayXuatBan.getText().toString().isEmpty();
        boolean boolGiaTien = !layoutTS_tvGiaTien.getText().toString().isEmpty();
        boolean boolKhuyenMai = !layoutTS_tvKhuyenMai.getText().toString().isEmpty();
        boolean boolSoLuongKho = !layoutTS_tvSoLuongKho.getText().toString().isEmpty();
        boolean boolHinhSach = check;

        if (!boolTenSach) {
            layoutTS_tvTenSach.setError("Không được bỏ trống");
        }
        if (!boolTheLoai) {
            layoutTS_tvTheLoai.setError("Không được bỏ trống");
        }
        if (!boolTacGia) {
            layoutTS_tvTacGia.setError("Không được bỏ trống");
        }
        if (!boolNhaXuatBan) {
            layoutTS_tvNhaXuatBan.setError("Không được bỏ trống");
        }
        if (!boolNgayXuatBan) {
            layoutTS_tvNgayXuatBan.setError("Không được bỏ trống");
        }
        if (!boolGiaTien) {
            layoutTS_tvGiaTien.setError("Không được bỏ trống");
        }
        if (!boolKhuyenMai) {
            layoutTS_tvKhuyenMai.setError("Không được bỏ trống ");
        }
        if (!boolSoLuongKho) {
            layoutTS_tvSoLuongKho.setError("Không được bỏ trống ");
        }
        if (!boolHinhSach) {
            AlertDialog.Builder b = new AlertDialog.Builder(ThemSachActivity.this);
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
        return boolTenSach && boolTheLoai && boolTacGia && boolNhaXuatBan && boolNgayXuatBan && boolGiaTien && boolKhuyenMai && boolSoLuongKho && boolHinhSach;
    }
}
