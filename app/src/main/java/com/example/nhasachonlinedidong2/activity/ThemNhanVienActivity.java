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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemNhanVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThemNhanVienActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemNhanVien> nhanViens = new ArrayList<>();

    EditText MHTNV_edtMaNhanVien, MHTNV_edtTenNhanVien, MHTNV_edtChucVu, MHTNV_edtTaiKhoan, MHTNV_edtMatKhau, MHTNV_edtEmail, MHTNV_edtDiaChi, MHTNV_edtSoDienThoai, MHTNV_edtCMND, MHTNV_edtLuongCoBan;
    ImageView MHTNV_imgHinhNhanVien;
    Button MHTNV_btnNhapMoi, MHTNV_btnThemNhanVien;
    TextView MHTNV_txtQuayLai;
    private Uri uri;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int CAMERA_PIC_REQUEST = 1337;
    private String chonAnh = "Thư viện";
    private String maNhanVien = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fireBase.getNhanVien(nhanViens, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_themnhanvien_layout);
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        setControl();
        setEvent();
        MHTNV_edtMaNhanVien.setEnabled(false);
        MHTNV_edtChucVu.setEnabled(false);

        MHTNV_edtChucVu.setText("Nhân viên");



    }

    private void setEvent() {
        //Add nhan vien
        MHTNV_btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemNhanVienActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn thêm nhân viên vào cty không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ValidateNhanVien()){
                            fireBase.themNhanVien(
                                     MHTNV_edtMaNhanVien.getText().toString(),
                                    "nhanvien" + maNhanVien + ".png",
                                    MHTNV_edtTenNhanVien.getText().toString(),
                                    MHTNV_edtCMND.getText().toString(),
                                    MHTNV_edtDiaChi.getText().toString(),
                                    MHTNV_edtEmail.getText().toString(),
                                    MHTNV_edtLuongCoBan.getText().toString(),
                                    MHTNV_edtMatKhau.getText().toString(),
                                    "nhanvien",
                                    MHTNV_edtSoDienThoai.getText().toString(),
                                    MHTNV_edtTaiKhoan.getText().toString()
                            );
                            // Tai anh len storage
                            ghiAnh(uri, "nhanvien" + maNhanVien);
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

        //reset
        MHTNV_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MHTNV_edtTenNhanVien.setText("");
                MHTNV_edtTaiKhoan.setText("");
                MHTNV_edtMatKhau.setText("");
                MHTNV_edtEmail.setText("");
                MHTNV_edtDiaChi.setText("");
                MHTNV_edtSoDienThoai.setText("");
                MHTNV_edtCMND.setText("");
                MHTNV_edtLuongCoBan.setText("");
            }
        });
        //Quay lai
        MHTNV_txtQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Doi hinh
        MHTNV_imgHinhNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ThemNhanVienActivity.this);
                b.setTitle("THÊM HÌNH NHÂN VIÊN");
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
    }

    private void setControl() {
        MHTNV_txtQuayLai=findViewById(R.id.MHTNV_txtQuayLai);
        MHTNV_edtMaNhanVien = findViewById(R.id.MHTNV_edtMaNhanVien);
        MHTNV_edtTenNhanVien = findViewById(R.id.MHTNV_edtTenNhanVien); //validated
        MHTNV_edtChucVu = findViewById(R.id.MHTNV_edtChucVu);
        MHTNV_edtTaiKhoan = findViewById(R.id.MHTNV_edtTaiKhoanNhanVien); //validated
        MHTNV_edtMatKhau = findViewById(R.id.MHTNV_edtMatKhauNhanVien); //validated
        MHTNV_edtEmail = findViewById(R.id.MHTNV_edtEmailNhanVien); //validated
        MHTNV_edtDiaChi = findViewById(R.id.MHTNV_edtDiaChiNhanVien); //validated
        MHTNV_edtSoDienThoai = findViewById(R.id.MHTNV_edtSoDienThoaiNhanVien); //validated
        MHTNV_edtCMND = findViewById(R.id.MHTNV_edtCMNDNhanVien); //validated
        MHTNV_edtLuongCoBan = findViewById(R.id.MHTNV_edtLuongNhanVien); //validated
        MHTNV_imgHinhNhanVien = findViewById(R.id.MHTNV_imgAnhNhanVien);
        MHTNV_btnNhapMoi = findViewById(R.id.MHTNV_btnNhapMoi);
        MHTNV_btnThemNhanVien = findViewById(R.id.MHTNV_btnThemNhanVien);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            uri  =  data.getData();
            MHTNV_imgHinhNhanVien.setImageURI(uri);
        }
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ThemNhanVienActivity.this.getContentResolver(), photo, "Title", null);
            uri = Uri.parse(path);
            MHTNV_imgHinhNhanVien.setImageURI(uri);
        }
    }

    private void ghiAnh(Uri filePath, String maNhanVien){
        if(filePath != null)
        {
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
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    //Lay ma nhan vien
    public void createMaNhanvien(){
        int location = nhanViens.size()-1;
        String[] strings = nhanViens.get(location).getMaNhanVien().split("nv");
        maNhanVien = ""+ (Integer.parseInt(strings[1]) + 1);
        MHTNV_edtMaNhanVien.setText("nv" + maNhanVien);
    }

    //Kiem tra nhan vien
    public boolean ValidateNhanVien(){
        //Tu 6 den 20 ky tu khong khoang trang va ky tu dac biet
        Pattern patternAccount = Pattern.compile("^[A-Za-z][A-Za-z0-9]{5,20}$");
        Matcher matcherAccount = patternAccount.matcher(MHTNV_edtTaiKhoan.getText().toString());

        //email regex
        Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(MHTNV_edtEmail.getText().toString());

        //More than 8 char, required at least: 1 char, 1 number, 1 special char.
        Pattern patternPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcherPassword = patternPassword.matcher(MHTNV_edtMatKhau.getText().toString());

        //Ten tieng viet hoac tieng anh, yeu cau viet hoa dau tu
        Pattern patternName = Pattern.compile("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$");
        Matcher matcherName = patternName.matcher(MHTNV_edtTenNhanVien.getText().toString());

        //So dien thoai Viet Nam
        Pattern patternPhone = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        Matcher matcherPhone = patternPhone.matcher(MHTNV_edtSoDienThoai.getText().toString());

        //So can cuong - CMND
        Pattern patternCitizenNum = Pattern.compile("/^[0-9]{8,13}$");
        Matcher matcherCitizenNum  = patternCitizenNum.matcher(MHTNV_edtCMND.getText().toString());

        //Luong
        Pattern patternSalary = Pattern.compile("/^[0-9]{6,}$");
        Matcher matcherSalary  = patternSalary.matcher(MHTNV_edtLuongCoBan.getText().toString());


        boolean boolAccount = matcherAccount.find();
        boolean boolEmail = matcherEmail.find();
        boolean boolPassword = matcherPassword.find();
        boolean boolName = matcherName.find();
        boolean boolPhone = matcherPhone.find();
        boolean boolAddress = !MHTNV_edtDiaChi.getText().toString().isEmpty();
        boolean boolCitizenNum = !MHTNV_edtCMND.getText().toString().isEmpty();
        boolean boolSalary = true;

        if (!boolAccount) {
            MHTNV_edtTaiKhoan.setError("Tên đăng nhập từ 6 đến 20 ký tự, không khoảng trắng và ký tự đặc biệt");
        }
        if (!boolEmail) {
            MHTNV_edtEmail.setError("Vui lòng nhập email hợp lệ");
        }
        if (!boolPassword) {
            MHTNV_edtMatKhau.setError("Mật khẩu cần ít nhất 8 ký tự, một chữ cái, một chữ số và một ký tự đặc biệt");
        }
        if (!boolName) {
            MHTNV_edtTenNhanVien.setError("Tên Tiếng Việt hoặc Tiếng Anh, viết hoa đầu từ");
        }
        if (!boolPhone) {
            MHTNV_edtSoDienThoai.setError("Số điện thoại bạn nhập không hợp lệ");
        }
        if (!boolAddress) {
            MHTNV_edtDiaChi.setError("Không được bỏ trống ");
        }
        if(!boolSalary){
            MHTNV_edtLuongCoBan.setError("lương phải lớn hơn 1 triệu");
        }
        if(!boolCitizenNum){
            MHTNV_edtCMND.setError("Căn cước hoặc chứng minh nhân dân chỉ có từ 9-12 ký tự ");
        }
        return boolAccount && boolEmail && boolPassword && boolName && boolPhone && boolAddress && boolSalary && boolCitizenNum;
    }


}
