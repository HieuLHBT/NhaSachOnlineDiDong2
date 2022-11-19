package com.example.nhasachonlinedidong2.activity;

import android.annotation.SuppressLint;
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
import com.example.nhasachonlinedidong2.item.ItemVanPhongPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ThemVanPhongPhamActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemVanPhongPham> vanPhongPhams = new ArrayList<>();

    private EditText layoutThemVanPhongPham_edtMaVPP;
    private EditText layoutThemVanPhongPham_edtTenVPP;
    private EditText layoutThemVanPhongPham_edtNhaPhanPhoi;
    private EditText layoutThemVanPhongPham_edtXuatXu;
    private EditText layoutThemVanPhongPham_edtDonVi;
    private EditText layoutThemVanPhongPham_edtDonGia;
    private EditText layoutThemVanPhongPham_edtSoLuongKho;
    private ImageView layoutThemVanPhongPham_imgHinhVanPhongPham;
    private Button layoutThemVanPhongPham_btnNhapMoi;
    private Button layoutThemVanPhongPham_btnThemVPP;
    private Button layoutThemVanPhongPham_btnTroVe;

    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsanpham_vanphongpham_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        layoutThemVanPhongPham_edtMaVPP = findViewById(R.id.layoutThemVanPhongPham_edtMaVPP);
        layoutThemVanPhongPham_edtTenVPP = findViewById(R.id.layoutThemVanPhongPham_edtTenVPP);
        layoutThemVanPhongPham_edtNhaPhanPhoi = findViewById(R.id.layoutThemVanPhongPham_edtNhaPhanPhoi);
        layoutThemVanPhongPham_edtXuatXu = findViewById(R.id.layoutThemVanPhongPham_edtXuatXu);
        layoutThemVanPhongPham_edtDonVi = findViewById(R.id.layoutThemVanPhongPham_edtDonVi);
        layoutThemVanPhongPham_edtDonGia = findViewById(R.id.layoutThemVanPhongPham_edtDonGia);
        layoutThemVanPhongPham_edtSoLuongKho = findViewById(R.id.layoutThemVanPhongPham_edtSoLuongKho);
        layoutThemVanPhongPham_imgHinhVanPhongPham = findViewById(R.id.layoutThemVanPhongPham_imgHinhVanPhongPham);
        layoutThemVanPhongPham_btnNhapMoi = findViewById(R.id.layoutThemVanPhongPham_btnNhapMoi);
        layoutThemVanPhongPham_btnThemVPP = findViewById(R.id.layoutThemVanPhongPham_btnThemVPP);
        layoutThemVanPhongPham_btnTroVe = findViewById(R.id.layoutThemVanPhongPham_btnTroVe);

        layoutThemVanPhongPham_btnThemVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemVanPhongPhamActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn thêm văn phòng phẩm vào kho sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.themSanPhamVPP(
                                "vpp" + layoutThemVanPhongPham_edtMaVPP.getText().toString(),
                                "vpp" + layoutThemVanPhongPham_edtMaVPP.getText() + ".png",
                                layoutThemVanPhongPham_edtTenVPP.getText().toString(),
                                layoutThemVanPhongPham_edtNhaPhanPhoi.getText().toString(),
                                layoutThemVanPhongPham_edtXuatXu.getText().toString(),
                                layoutThemVanPhongPham_edtDonVi.getText().toString(),
                                layoutThemVanPhongPham_edtDonGia.getText().toString(),
                                layoutThemVanPhongPham_edtSoLuongKho.getText().toString()
                        );

                        //Kiểm tra các trường bỏ trống
                        if (layoutThemVanPhongPham_edtMaVPP.getTouchables().isEmpty() || layoutThemVanPhongPham_edtTenVPP.getTouchables().isEmpty() || layoutThemVanPhongPham_edtNhaPhanPhoi.getTouchables().isEmpty() ||
                                layoutThemVanPhongPham_edtXuatXu.getTouchables().isEmpty() || layoutThemVanPhongPham_edtDonVi.getTouchables().isEmpty() || layoutThemVanPhongPham_edtDonGia.getTouchables().isEmpty() ||
                                layoutThemVanPhongPham_edtSoLuongKho.getTouchables().isEmpty()) {
                            Toast.makeText(ThemVanPhongPhamActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }
                        // Tai anh len storage
                        ghiAnh(uri, layoutThemVanPhongPham_edtMaVPP.getText().toString());
                        Toast.makeText(ThemVanPhongPhamActivity.this, "Thêm văn phòng phẩm thành công", Toast.LENGTH_SHORT).show();
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

        layoutThemVanPhongPham_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutThemVanPhongPham_edtTenVPP.setText("");
                layoutThemVanPhongPham_edtNhaPhanPhoi.setText("");
                layoutThemVanPhongPham_edtXuatXu.setText("");
                layoutThemVanPhongPham_edtDonVi.setText("");
                layoutThemVanPhongPham_edtDonGia.setText("");
                layoutThemVanPhongPham_edtSoLuongKho.setText("");
            }
        });

        layoutThemVanPhongPham_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutThemVanPhongPham_imgHinhVanPhongPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemVanPhongPhamActivity.this);
                b.setTitle("THÊM HÌNH VĂN PHÒNG PHẨM");
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
            layoutThemVanPhongPham_imgHinhVanPhongPham.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemVanPhongPhamActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            layoutThemVanPhongPham_imgHinhVanPhongPham.setImageURI(uri);
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
