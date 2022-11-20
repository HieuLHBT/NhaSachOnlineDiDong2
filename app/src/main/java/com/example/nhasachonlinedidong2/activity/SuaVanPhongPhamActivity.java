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
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
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

public class SuaVanPhongPhamActivity extends AppCompatActivity {
    private TextView layoutSVPP_btnTroVe, layoutSVPP_tvMaVanPhongPham, layoutSVPP_tvSoLuongKho;
    private EditText layoutSVPP_tvTenVanPhongPham,
            layoutSVPP_tvXuatXu,
            layoutSVPP_tvNhaPhanPhoi,
            layoutSVPP_tvDonVi,
            layoutSVPP_tvGiaTien,
            layoutSVPP_tvKhuyenMai;
    private ImageView layoutSVPP_imgHinhVanPhongPham;
    private Button layoutSVPP_btnSua, layoutSVPP_btnNhapMoi;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private QuanLySanPham_SanPham sanPham = new QuanLySanPham_SanPham();
    private VanPhongPham vanPhongPham = new VanPhongPham();
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";
    private Boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suavanphongpham_layout);

        sanPham = (QuanLySanPham_SanPham) getIntent().getSerializableExtra("sanPham");
        vanPhongPham.setHinhVanPhongPham(sanPham.getHinhSanPham());
        vanPhongPham.setMaVanPhongPham(sanPham.getMaSanPham());
        vanPhongPham.setTenVanPhongPham(sanPham.getTenSanPham());
        vanPhongPham.setXuatXu(sanPham.getXuatXu());
        vanPhongPham.setNhaPhanPhoi(sanPham.getNhaPhanPhoi());
        vanPhongPham.setDonVi(sanPham.getDonVi());
        vanPhongPham.setGiaTien(String.valueOf(sanPham.getGiaSanPham()));
        vanPhongPham.setKhuyenMai(String.valueOf(sanPham.getKhuyenMai()));
        vanPhongPham.setSoLuongKho(String.valueOf(sanPham.getSoLuongKho()));

        layoutSVPP_btnTroVe = findViewById(R.id.layoutSVPP_btnTroVe);
        layoutSVPP_imgHinhVanPhongPham = findViewById(R.id.layoutSVPP_imgHinhVanPhongPham);
        layoutSVPP_tvMaVanPhongPham = findViewById(R.id.layoutSVPP_tvMaVanPhongPham);
        layoutSVPP_tvTenVanPhongPham = findViewById(R.id.layoutSVPP_tvTenVanPhongPham);
        layoutSVPP_tvXuatXu = findViewById(R.id.layoutSVPP_tvXuatXu);
        layoutSVPP_tvNhaPhanPhoi = findViewById(R.id.layoutSVPP_tvNhaPhanPhoi);
        layoutSVPP_tvDonVi = findViewById(R.id.layoutSVPP_tvDonVi);
        layoutSVPP_tvGiaTien = findViewById(R.id.layoutSVPP_tvGiaTien);
        layoutSVPP_tvKhuyenMai = findViewById(R.id.layoutSVPP_tvKhuyenMai);
        layoutSVPP_tvSoLuongKho = findViewById(R.id.layoutSVPP_tvSoLuongKho);
        layoutSVPP_btnSua = findViewById(R.id.layoutSVPP_btnSua);
        layoutSVPP_btnNhapMoi = findViewById(R.id.layoutSVPP_btnNhapMoi);

        hienThiThongTinVanPhongPham();

        layoutSVPP_tvTenVanPhongPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setTenVanPhongPham(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_tvXuatXu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setXuatXu(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_tvNhaPhanPhoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setNhaPhanPhoi(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_tvDonVi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setDonVi(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_tvGiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setGiaTien(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_tvKhuyenMai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vanPhongPham.setKhuyenMai(String.valueOf(s));
                check = true;
            }
        });

        layoutSVPP_btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đồng ý xác nhận sửa sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            if (check) {
                                fireBaseNhaSachOnline.suaVanPhongPham(vanPhongPham, SuaVanPhongPhamActivity.this);
                                // Tai anh len storage
                                ghiAnh(uri, vanPhongPham.getHinhVanPhongPham());
                            } else {
                                AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
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

        layoutSVPP_imgHinhVanPhongPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
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

        layoutSVPP_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiThongTinVanPhongPham();
                check = false;
            }
        });

        layoutSVPP_btnTroVe.setOnClickListener(new View.OnClickListener() {
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

    public void hienThiThongTinVanPhongPham() {
        layoutSVPP_tvMaVanPhongPham.setText(sanPham.getMaSanPham());
        layoutSVPP_tvTenVanPhongPham.setText(sanPham.getTenSanPham());
        layoutSVPP_tvXuatXu.setText(sanPham.getXuatXu());
        layoutSVPP_tvNhaPhanPhoi.setText(sanPham.getNhaPhanPhoi());
        layoutSVPP_tvDonVi.setText(sanPham.getDonVi());
        layoutSVPP_tvGiaTien.setText(String.valueOf(sanPham.getGiaSanPham()));
        layoutSVPP_tvKhuyenMai.setText(String.valueOf(sanPham.getKhuyenMai()));
        layoutSVPP_tvSoLuongKho.setText(String.valueOf(sanPham.getSoLuongKho()));
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
                    layoutSVPP_imgHinhVanPhongPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
            layoutSVPP_imgHinhVanPhongPham.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(SuaVanPhongPhamActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutSVPP_imgHinhVanPhongPham.setImageURI(uri);
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
        boolean boolTenVanPhongPham = !layoutSVPP_tvTenVanPhongPham.getText().toString().isEmpty();
        boolean boolXuatXu = !layoutSVPP_tvXuatXu.getText().toString().isEmpty();
        boolean boolNhaPhanPhoi = !layoutSVPP_tvNhaPhanPhoi.getText().toString().isEmpty();
        boolean boolDonVi = !layoutSVPP_tvDonVi.getText().toString().isEmpty();
        boolean boolGiaTien = !layoutSVPP_tvGiaTien.getText().toString().isEmpty();
        boolean boolKhuyenMai = !layoutSVPP_tvKhuyenMai.getText().toString().isEmpty();
        boolean boolHinhSach = layoutSVPP_imgHinhVanPhongPham.getDrawable() != null;

        if (!boolTenVanPhongPham) {
            layoutSVPP_tvTenVanPhongPham.setError("Không được bỏ trống");
        }
        if (!boolXuatXu) {
            layoutSVPP_tvXuatXu.setError("Không được bỏ trống");
        }
        if (!boolNhaPhanPhoi) {
            layoutSVPP_tvNhaPhanPhoi.setError("Không được bỏ trống");
        }
        if (!boolDonVi) {
            layoutSVPP_tvDonVi.setError("Không được bỏ trống");
        }
        if (!boolGiaTien) {
            layoutSVPP_tvGiaTien.setError("Không được bỏ trống");
        }
        if (!boolKhuyenMai) {
            layoutSVPP_tvKhuyenMai.setError("Không được bỏ trống ");
        }
        if (!boolHinhSach) {
            AlertDialog.Builder b = new AlertDialog.Builder(SuaVanPhongPhamActivity.this);
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
        return boolTenVanPhongPham && boolXuatXu && boolNhaPhanPhoi && boolDonVi && boolGiaTien && boolGiaTien && boolKhuyenMai && boolHinhSach;
    }
}
