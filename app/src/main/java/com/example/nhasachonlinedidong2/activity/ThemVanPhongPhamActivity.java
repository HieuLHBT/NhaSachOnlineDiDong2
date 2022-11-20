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
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ThemVanPhongPhamActivity extends AppCompatActivity {
    private TextView layoutTVPP_btnTroVe, layoutTVPP_tvMaVanPhongPham;
    private EditText layoutTVPP_tvTenVanPhongPham,
            layoutTVPP_tvXuatXu,
            layoutTVPP_tvNhaPhanPhoi,
            layoutTVPP_tvDonVi,
            layoutTVPP_tvGiaTien,
            layoutTVPP_tvKhuyenMai,
            layoutTVPP_tvSoLuongKho;
    private ImageView layoutTVPP_imgHinhVanPhongPham;
    private Button layoutTVPP_btnThem, layoutTVPP_btnNhapMoi;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private VanPhongPham vanPhongPham = new VanPhongPham();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";
    private Boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themvanphongpham_layout);

        layoutTVPP_btnTroVe = findViewById(R.id.layoutTVPP_btnTroVe);
        layoutTVPP_imgHinhVanPhongPham = findViewById(R.id.layoutTVPP_imgHinhVanPhongPham);
        layoutTVPP_tvMaVanPhongPham = findViewById(R.id.layoutTVPP_tvMaVanPhongPham);
        layoutTVPP_tvTenVanPhongPham = findViewById(R.id.layoutTVPP_tvTenVanPhongPham);
        layoutTVPP_tvXuatXu = findViewById(R.id.layoutTVPP_tvXuatXu);
        layoutTVPP_tvNhaPhanPhoi = findViewById(R.id.layoutTVPP_tvNhaPhanPhoi);
        layoutTVPP_tvDonVi = findViewById(R.id.layoutTVPP_tvDonVi);
        layoutTVPP_tvGiaTien = findViewById(R.id.layoutTVPP_tvGiaTien);
        layoutTVPP_tvKhuyenMai = findViewById(R.id.layoutTVPP_tvKhuyenMai);
        layoutTVPP_tvSoLuongKho = findViewById(R.id.layoutTVPP_tvSoLuongKho);
        layoutTVPP_btnThem = findViewById(R.id.layoutTVPP_btnThem);
        layoutTVPP_btnNhapMoi = findViewById(R.id.layoutTVPP_btnNhapMoi);

        layoutTVPP_tvTenVanPhongPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setTenVanPhongPham(String.valueOf(s));
            }
        });

        layoutTVPP_tvXuatXu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setXuatXu(String.valueOf(s));
            }
        });

        layoutTVPP_tvNhaPhanPhoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setNhaPhanPhoi(String.valueOf(s));
            }
        });

        layoutTVPP_tvDonVi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setDonVi(String.valueOf(s));
            }
        });

        layoutTVPP_tvGiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setGiaTien(String.valueOf(s));
            }
        });

        layoutTVPP_tvKhuyenMai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setKhuyenMai(String.valueOf(s));
            }
        });

        layoutTVPP_tvSoLuongKho.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setSoLuongKho(String.valueOf(s));
            }
        });

        layoutTVPP_btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemVanPhongPhamActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đồng ý xác nhận thêm sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            fireBaseNhaSachOnline.themVanPhongPham(vanPhongPham, ThemVanPhongPhamActivity.this);
                            // Tai anh len storage
                            ghiAnh(uri, vanPhongPham.getHinhVanPhongPham());
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

        layoutTVPP_imgHinhVanPhongPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemVanPhongPhamActivity.this);
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

        layoutTVPP_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutTVPP_tvTenVanPhongPham.getText().clear();
                layoutTVPP_tvXuatXu.getText().clear();
                layoutTVPP_tvNhaPhanPhoi.getText().clear();
                layoutTVPP_tvDonVi.getText().clear();
                layoutTVPP_tvGiaTien.getText().clear();
                layoutTVPP_tvKhuyenMai.getText().clear();
                layoutTVPP_tvSoLuongKho.getText().clear();
                layoutTVPP_imgHinhVanPhongPham.setImageBitmap(null);
                check = false;
            }
        });

        layoutTVPP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienMaVanPhongPham(vanPhongPham, this);
    }

    public void hienThiMaVanPhongPham() {
        layoutTVPP_tvMaVanPhongPham.setText(vanPhongPham.getMaVanPhongPham());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            layoutTVPP_imgHinhVanPhongPham.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemVanPhongPhamActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutTVPP_imgHinhVanPhongPham.setImageURI(uri);
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
        boolean boolTenVanPhongPham = !layoutTVPP_tvTenVanPhongPham.getText().toString().isEmpty();
        boolean boolXuatXu = !layoutTVPP_tvXuatXu.getText().toString().isEmpty();
        boolean boolNhaPhanPhoi = !layoutTVPP_tvNhaPhanPhoi.getText().toString().isEmpty();
        boolean boolDonVi = !layoutTVPP_tvDonVi.getText().toString().isEmpty();
        boolean boolGiaTien = !layoutTVPP_tvGiaTien.getText().toString().isEmpty();
        boolean boolKhuyenMai = !layoutTVPP_tvKhuyenMai.getText().toString().isEmpty();
        boolean boolSoLuongKho = !layoutTVPP_tvSoLuongKho.getText().toString().isEmpty();
        boolean boolHinhSach = check;

        if (!boolTenVanPhongPham) {
            layoutTVPP_tvTenVanPhongPham.setError("Không được bỏ trống");
        }
        if (!boolXuatXu) {
            layoutTVPP_tvXuatXu.setError("Không được bỏ trống");
        }
        if (!boolNhaPhanPhoi) {
            layoutTVPP_tvNhaPhanPhoi.setError("Không được bỏ trống");
        }
        if (!boolDonVi) {
            layoutTVPP_tvDonVi.setError("Không được bỏ trống");
        }
        if (!boolGiaTien) {
            layoutTVPP_tvGiaTien.setError("Không được bỏ trống");
        }
        if (!boolKhuyenMai) {
            layoutTVPP_tvKhuyenMai.setError("Không được bỏ trống ");
        }
        if (!boolSoLuongKho) {
            layoutTVPP_tvSoLuongKho.setError("Không được bỏ trống ");
        }
        if (!boolHinhSach) {
            AlertDialog.Builder b = new AlertDialog.Builder(ThemVanPhongPhamActivity.this);
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
        return boolTenVanPhongPham && boolXuatXu && boolNhaPhanPhoi && boolDonVi && boolGiaTien && boolGiaTien && boolKhuyenMai && boolSoLuongKho && boolHinhSach;
    }
}
