package com.example.nhasachonlinedidong2.firebase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.nhasachonlinedidong2.activity.ChiTietTheoDoiDonHangActivity;
import com.example.nhasachonlinedidong2.activity.DangKyActivity;
import com.example.nhasachonlinedidong2.activity.DangNhapActivity;
import com.example.nhasachonlinedidong2.activity.DanhGiaSanPhamActivity;
import com.example.nhasachonlinedidong2.activity.DanhMucSanPhamActivity;
import com.example.nhasachonlinedidong2.activity.DoiMatKhauActivity;
import com.example.nhasachonlinedidong2.activity.GioHangActivity;
import com.example.nhasachonlinedidong2.activity.ManHinhChinhKhachHangActivity;
import com.example.nhasachonlinedidong2.activity.ManHinhChinhQuanLyActivity;
import com.example.nhasachonlinedidong2.activity.NhapKhoSachActivity;
import com.example.nhasachonlinedidong2.activity.NhapKhoVPPActivity;
import com.example.nhasachonlinedidong2.activity.PhanHoiYKienKhachHangActivity;
import com.example.nhasachonlinedidong2.activity.QuanLyDonHangActivity;
import com.example.nhasachonlinedidong2.activity.QuenMatKhauActivity;
import com.example.nhasachonlinedidong2.activity.QuenMatKhauCapLaiMatKhauActivity;
import com.example.nhasachonlinedidong2.activity.QuenMatKhauXacNhanOTPActivity;
import com.example.nhasachonlinedidong2.activity.SuaNhanVienActivity;
import com.example.nhasachonlinedidong2.activity.SuaSachActivity;
import com.example.nhasachonlinedidong2.activity.SuaSanPhamSachActivity;
import com.example.nhasachonlinedidong2.activity.SuaSanPhamVPPActivity;
import com.example.nhasachonlinedidong2.activity.SuaVanPhongPhamActivity;
import com.example.nhasachonlinedidong2.activity.ThayDoiThongTinKhachHangActivity;
import com.example.nhasachonlinedidong2.activity.ManHinhChinhNhanVienActivity;
import com.example.nhasachonlinedidong2.activity.ThanhToanActivity;
import com.example.nhasachonlinedidong2.activity.TheoDoiDonHangActivity;
import com.example.nhasachonlinedidong2.activity.HuyDonHangActivity;
import com.example.nhasachonlinedidong2.activity.ThongTinKhachHangActivity;
import com.example.nhasachonlinedidong2.activity.TraLoiBinhLuanActivity;
import com.example.nhasachonlinedidong2.adapters.ChiTietTheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.DanhGiaSanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.GioHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.LichSuMuaHangDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.MaGiamGiaRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLyDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLyNhanVienRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.PhanHoiYKienKhachHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.DanhMucSanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLySanPhamQLRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.QuanLySanPhamRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.ThanhToanRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.adapters.TheoDoiDonHangRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.data_model.GiamGia;
import com.example.nhasachonlinedidong2.data_model.DonHang;
import com.example.nhasachonlinedidong2.data_model.GioHang;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.data_model.NhanVien;
import com.example.nhasachonlinedidong2.data_model.PhanHoi;
import com.example.nhasachonlinedidong2.data_model.QuanLy;
import com.example.nhasachonlinedidong2.data_model.Sach;
import com.example.nhasachonlinedidong2.data_model.TrangThaiDonHang;
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.data_model.XuatKho;
import com.example.nhasachonlinedidong2.item.ChiTietTheoDoiDonHang;
import com.example.nhasachonlinedidong2.item.ChiTietTheoDoiDonHang_SanPham;
import com.example.nhasachonlinedidong2.item.ChiTietTheoDoiDonHang_ThongTin;
import com.example.nhasachonlinedidong2.item.DanhGiaSanPham;
import com.example.nhasachonlinedidong2.item.DanhGiaSanPham_SanPham;
import com.example.nhasachonlinedidong2.item.HeThong;
import com.example.nhasachonlinedidong2.item.ItemNhanVien;
import com.example.nhasachonlinedidong2.item.DanhMucSanPham;
import com.example.nhasachonlinedidong2.item.ItemQuanLySanPhamQL;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_DonHang;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_SanPham;
import com.example.nhasachonlinedidong2.item.ManHinhChinhQuanLy_ThongKeDoanhSo;
import com.example.nhasachonlinedidong2.item.ManHinhChinhQuanLy_ThongKeDon;
import com.example.nhasachonlinedidong2.item.PhanHoiYKienKhachHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_DonHang;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_SanPham;
import com.example.nhasachonlinedidong2.item.SanPham;
import com.example.nhasachonlinedidong2.item.ThanhToan;
import com.example.nhasachonlinedidong2.item.TheoDoiDonHang;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FireBaseNhaSachOnline {
    private SharePreferences sharePreferences = new SharePreferences();

    // Giỏ hàng
    public void hienThiGioHang(String maKhachHang, ArrayList<com.example.nhasachonlinedidong2.item.GioHang> gioHangItem, GioHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        gioHangDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<GioHang> gioHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                    gioHangs.add(gioHang);
                }
                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        gioHangItem.clear();
                        for (GioHang gioHang : gioHangs) {
                            if (gioHang.getMaSanPham().contains("s")) {
                                Sach sach = snapshot.child("SACH").child(gioHang.getMaSanPham()).getValue(Sach.class);
                                gioHangItem.add(new com.example.nhasachonlinedidong2.item.GioHang(
                                        sach.getMaSach(),
                                        sach.getTenSach(),
                                        Integer.valueOf(sach.getGiaTien()),
                                        Integer.valueOf(sach.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        sach.getHinhSach()));
                            } else if (gioHang.getMaSanPham().contains("vpp")) {
                                VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(gioHang.getMaSanPham()).getValue(VanPhongPham.class);
                                gioHangItem.add(new com.example.nhasachonlinedidong2.item.GioHang(
                                        vanPhongPham.getMaVanPhongPham(),
                                        vanPhongPham.getTenVanPhongPham(),
                                        Integer.valueOf(vanPhongPham.getGiaTien()),
                                        Integer.valueOf(vanPhongPham.getKhuyenMai()),
                                        Integer.valueOf(gioHang.getSoLuong()),
                                        vanPhongPham.getHinhVanPhongPham()));
                            }
                        }
                        adapter.notifyDataSetChanged();
                        ((GioHangActivity) context).TongTienThanhToan();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void xoaSanPhamGioHang(String maKhachHang, String maSanpham, GioHangRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanpham).removeValue();
    }

    public void congSoLuongGioHang(String maKhachHang, String maSanPham, int soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue((++soLuong) + "");
    }

    public void truSoLuongGioHang(String maKhachHang, String maSanPham, int soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.child(maKhachHang).child(maSanPham).child("soLuong").setValue((--soLuong) + "");
    }

    public void taoXuatKho(String maKhachHang, ArrayList<com.example.nhasachonlinedidong2.item.GioHang> gioHangs, Context context, int size, GioHangRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> dsXuatKho = new ArrayList<>();
                for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                    dsXuatKho.add(xuatKhoSnapshot.getKey());
                }
                String[] temp = dsXuatKho.get(dsXuatKho.size() - 1).split("dh");
                String maDonHang = "dh" + (Integer.valueOf(temp[1]) + 1);
                if (gioHangs.size() == size) {
                    for (com.example.nhasachonlinedidong2.item.GioHang gioHang : gioHangs) {
                        XuatKho xuatKho = new XuatKho(maDonHang, gioHang.getMaSanPham(), String.valueOf(gioHang.getSoLuongSanPham()));
                        xuatKhoDatabase.child(maDonHang).child(gioHang.getMaSanPham()).setValue(xuatKho);
                    }
                    gioHangDatabase.child(maKhachHang).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            adapter.notifyDataSetChanged();
                            ((GioHangActivity) context).TongTienThanhToan();
                        }
                    });
                } else {
                    for (com.example.nhasachonlinedidong2.item.GioHang gioHang : gioHangs) {
                        XuatKho xuatKho = new XuatKho(maDonHang, gioHang.getMaSanPham(), String.valueOf(gioHang.getSoLuongSanPham()));
                        xuatKhoDatabase.child(maDonHang).child(gioHang.getMaSanPham()).setValue(xuatKho);
                        gioHangDatabase.child(maKhachHang).child(gioHang.getMaSanPham()).removeValue();
                    }
                }
                sharePreferences.themMaDonHang(context, maDonHang);
                Intent intent = new Intent(context, ThanhToanActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Thanh toán
    public void hienThiItemThanhToan(String maDonHang, ArrayList<ThanhToan> thanhToans, ThanhToanRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        xuatKhoDatabase.child(maDonHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thanhToans.clear();
                for (DataSnapshot xuatKhoSnapshot : snapshot.getChildren()) {
                    XuatKho xuatKho = xuatKhoSnapshot.getValue(XuatKho.class);
                    if (xuatKho.getMaSanPham().contains("s")) {
                        sanPhamDatabase.child("SACH").child(xuatKho.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Sach sach = snapshot.getValue(Sach.class);
                                thanhToans.add(new ThanhToan(sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), sach.getHinhSach(), Integer.valueOf(sach.getKhuyenMai())));
                                adapter.notifyDataSetChanged();
                                ((ThanhToanActivity) context).tongTien();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    } else if (xuatKho.getMaSanPham().contains("vpp")) {
                        sanPhamDatabase.child("VANPHONGPHAM").child(xuatKho.getMaSanPham()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                                thanhToans.add(new ThanhToan(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), vanPhongPham.getHinhVanPhongPham(), Integer.valueOf(vanPhongPham.getKhuyenMai())));
                                adapter.notifyDataSetChanged();
                                ((ThanhToanActivity) context).tongTien();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("onCancelled", "Lỗi!" + error.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiKhachHang(String maKhachHang, KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        xuatKhoDatabase.child("khachhang").child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KhachHang kh = snapshot.getValue(KhachHang.class);
                khachHang.setDiaChi(kh.getDiaChi());
                khachHang.setEmail(kh.getEmail());
                khachHang.setMaKhachHang(kh.getMaKhachHang());
                khachHang.setMatKhau(kh.getMatKhau());
                khachHang.setNganHang(kh.getNganHang());
                khachHang.setNguoiDung(kh.getNguoiDung());
                khachHang.setSoDienThoai(kh.getSoDienThoai());
                khachHang.setSoTaiKhoan(kh.getSoTaiKhoan());
                khachHang.setTaiKhoan(kh.getTaiKhoan());
                khachHang.setTenKhachHang(kh.getTenKhachHang());
                ((ThanhToanActivity) context).hienThiKhachHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void hienThiGiamGia(String maKhachHang, GiamGia giamGia, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGiaDaTa = giamGiaDataSnapshot.getValue(GiamGia.class);
                    if (giamGiaDaTa.getChon().equalsIgnoreCase("1")) {
                        giamGia.setMaKhachHang(giamGiaDaTa.getMaKhachHang());
                        giamGia.setChon(giamGiaDaTa.getChon());
                        giamGia.setHinhGiamGia(giamGiaDaTa.getHinhGiamGia());
                        giamGia.setMaGiamGia(giamGiaDaTa.getMaGiamGia());
                        giamGia.setTienGiamGia(giamGiaDaTa.getTienGiamGia());
                        giamGia.setTieuDe(giamGiaDaTa.getTieuDe());
                        giamGia.setYeuCau(giamGiaDaTa.getYeuCau());
                        ((ThanhToanActivity) context).tongTien();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void xoaChonGiamGia(String maKhachHang, String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("0");
    }

    public void chonGiamGia(String maKhachHang, String maGiamGia) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("1");
    }

    public void datHang(String phuongThucThanhToan, DonHang donHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.child(donHang.getMaDonHang()).setValue(donHang, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (donHang.getMaGiamGia() != "") {
                    giamGiaDatabase.child(donHang.getMaKhachHang()).child(donHang.getMaGiamGia()).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sharePreferences.xoaMaDonHang(context);
                            TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                                    donHang.getMaDonHang(),
                                    phuongThucThanhToan,
                                    "",
                                    "Đang xử lý",
                                    "Đang xử lý",
                                    "",
                                    "");
                            trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                            AlertDialog.Builder b = new AlertDialog.Builder(context);
                            b.setTitle("THÔNG BÁO!");
                            b.setMessage("Bạn đã đặt hàng thành công! Đơn hàng sẽ được hiển thị ở theo dõi đơn hàng (Đơn hàng đang xử lý)");
                            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ((ThanhToanActivity) context).finish();
                                }
                            });
                            AlertDialog al = b.create();
                            al.show();
                        }
                    });
                } else {
                    sharePreferences.xoaMaDonHang(context);
                    TrangThaiDonHang trangThaiDonHang = new TrangThaiDonHang(
                            donHang.getMaDonHang(),
                            phuongThucThanhToan,
                            "",
                            "Đang xử lý",
                            "Đang xử lý",
                            "",
                            "");
                    trangThaiDonHangDatabase.child(donHang.getMaDonHang()).setValue(trangThaiDonHang);
                    AlertDialog.Builder b = new AlertDialog.Builder(context);
                    b.setTitle("THÔNG BÁO!");
                    b.setMessage("Bạn đã đặt hàng thành công! Đơn hàng sẽ được hiển thị ở theo dõi đơn hàng (Đơn hàng đang xử lý)");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((ThanhToanActivity) context).finish();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            }
        });
    }

    public void huyThanhToan(String maKhachHang, String maGiamGia, String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        xuatKhoDatabase.child(maDonHang).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (maGiamGia != null) {
                    giamGiaDatabase.child(maKhachHang).child(maGiamGia).child("chon").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sharePreferences.xoaMaDonHang(context);
                        }
                    });
                } else {
                    sharePreferences.xoaMaDonHang(context);
                }
            }
        });

    }

    // Giảm giá
    public void hienThiMaGiamGia(Integer tongTien, String maKhachHang, ArrayList<GiamGia> giamGias, MaGiamGiaRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference giamgiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        giamgiaDatabase.child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                giamGias.clear();
                for (DataSnapshot giamGiaDataSnapshot : snapshot.getChildren()) {
                    GiamGia giamGia = giamGiaDataSnapshot.getValue(GiamGia.class);
                    if (Integer.valueOf(giamGia.getYeuCau()) > tongTien) {
                        giamGia.setKiemTra(true);
                    }
                    giamGias.add(giamGia);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Phản hồi ý kiến khách hàng
    public void hienThiPhanHoiYKien(ArrayList<PhanHoiYKienKhachHang> phanHoiYKienKhachHangs, PhanHoiYKienKhachHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        phanHoiDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<PhanHoi> phanHois = new ArrayList<>();
                for (DataSnapshot sanPhamDataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot phanHoiDataSnapshot : sanPhamDataSnapshot.getChildren()) {
                        PhanHoi phanHoi = phanHoiDataSnapshot.getValue(PhanHoi.class);
                        phanHois.add(phanHoi);
                    }
                }
                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        phanHoiYKienKhachHangs.clear();
                        for (PhanHoi phanHoi : phanHois) {
                            PhanHoiYKienKhachHang phanHoiYKienKhachHang = new PhanHoiYKienKhachHang();
                            phanHoiYKienKhachHang.setMaDonHang(phanHoi.getMaDonHang());
                            phanHoiYKienKhachHang.setDanhGia(phanHoi.getDanhGia());
                            phanHoiYKienKhachHang.setThoiGianBinhLuan(phanHoi.getNgayBinhLuan());
                            phanHoiYKienKhachHang.setMaKhachHang(phanHoi.getMaKhachHang());
                            phanHoiYKienKhachHang.setNoiDungBinhLuan(phanHoi.getBinhLuan());
                            phanHoiYKienKhachHang.setMaNhanVien(phanHoi.getMaNhanVien());
                            phanHoiYKienKhachHang.setNoiDungTraLoi(phanHoi.getTraLoi());
                            if (phanHoi.getMaSanPham().contains("s")) {
                                Sach sach = snapshot.child("SACH").child(phanHoi.getMaSanPham()).getValue(Sach.class);
                                phanHoiYKienKhachHang.setMaSanPham(sach.getMaSach());
                                phanHoiYKienKhachHang.setTenSanPham(sach.getTenSach());
                            } else if (phanHoi.getMaSanPham().contains("vpp")) {
                                VanPhongPham vanPhongPham = snapshot.child("VANPHONGPHAM").child(phanHoi.getMaSanPham()).getValue(VanPhongPham.class);
                                phanHoiYKienKhachHang.setMaSanPham(vanPhongPham.getMaVanPhongPham());
                                phanHoiYKienKhachHang.setTenSanPham(vanPhongPham.getTenVanPhongPham());
                            }
                            nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    KhachHang khachHang = snapshot.child("khachhang").child(phanHoi.getMaKhachHang()).getValue(KhachHang.class);
                                    phanHoiYKienKhachHang.setTenKhachHang(khachHang.getTenKhachHang());
                                    NhanVien nhanVien = snapshot.child("nhanvien").child(phanHoi.getMaNhanVien()).getValue(NhanVien.class);
                                    phanHoiYKienKhachHang.setTenNhanVien(nhanVien.getTenNhanVien());
                                    phanHoiYKienKhachHangs.add(phanHoiYKienKhachHang);
                                    adapter.notifyDataSetChanged();
                                    ((PhanHoiYKienKhachHangActivity) context).duLieu();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Trả lời bình luận
    public void traLoiBinhLuan(String maNhanVien, String noiDungThayDoi, PhanHoiYKienKhachHang phanHoiYKienKhachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        phanHoiDatabase.child(phanHoiYKienKhachHang.getMaDonHang()).child(phanHoiYKienKhachHang.getMaSanPham()).child("maNhanVien").setValue(maNhanVien, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                phanHoiDatabase.child(phanHoiYKienKhachHang.getMaDonHang()).child(phanHoiYKienKhachHang.getMaSanPham()).child("traLoi").setValue(noiDungThayDoi, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        AlertDialog.Builder b = new AlertDialog.Builder(context);
                        b.setTitle("THÔNG BÁO");
                        b.setMessage("Trả lời bình luận thành công!");
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((TraLoiBinhLuanActivity) context).finish();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    }
                });
            }
        });

    }

    // Màn hình chính quản lý
    public void hienThiThongTinQuanLy(String maQuanLy, QuanLy quanLy, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("quanly").child(maQuanLy).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuanLy ql = snapshot.getValue(QuanLy.class);
                quanLy.setTenQuanLy(ql.getTenQuanLy());
                quanLy.setMaQuanLy(ql.getMaQuanLy());
                quanLy.setNguoiDung(ql.getNguoiDung());
                quanLy.setHinhQuanLy(ql.getHinhQuanLy());
                ((ManHinhChinhQuanLyActivity) context).hienThiQuanLy();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void hienThiManHinhChinh(String ngay, ManHinhChinhQuanLy_ThongKeDon thongKeDon, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DonHang> donHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    donHangs.add(donHang);
                }
                trangThaiDonHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        thongKeDon.setDonThanhCong(0);
                        thongKeDon.setDonDaHuy(0);
                        thongKeDon.setDonDangXyLy(0);
                        for (DonHang donHang : donHangs) {
                            TrangThaiDonHang trangThaiDonHang = snapshot.child(donHang.getMaDonHang()).getValue(TrangThaiDonHang.class);
                            if (donHang.getThoiGianLap().equalsIgnoreCase(ngay)) {
                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công") && !donHang.getThoiGianLap().equalsIgnoreCase(donHang.getThoiGianGiao())) {
                                    thongKeDon.setDonThanhCong(thongKeDon.getDonThanhCong() + 1);
                                } else if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Đang xử lý")) {
                                    thongKeDon.setDonDangXyLy(thongKeDon.getDonDangXyLy() + 1);
                                } else if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy") && !donHang.getMaNhanVien().equalsIgnoreCase(donHang.getMaNhanVien())) {
                                    thongKeDon.setDonDaHuy(thongKeDon.getDonDaHuy() + 1);
                                }
                            }
                            if (donHang.getThoiGianGiao().equalsIgnoreCase(ngay)) {
                                if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                    thongKeDon.setDonThanhCong(thongKeDon.getDonThanhCong() + 1);
                                } else if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Đang xử lý")) {
                                    thongKeDon.setDonDangXyLy(thongKeDon.getDonDangXyLy() + 1);
                                } else if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Hủy")) {
                                    thongKeDon.setDonDaHuy(thongKeDon.getDonDaHuy() + 1);
                                }
                            }
                        }
                        ((ManHinhChinhQuanLyActivity) context).thielapDuLieuPieChart();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void hienThiThongKeDoanhSo(ArrayList<String> tuanHienTai, ManHinhChinhQuanLy_ThongKeDoanhSo thongKeDoanhSo, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDonHangDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference xuatKhoDonHangDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDonHangDatabase = firebaseDatabase.getReference("SANPHAM");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DonHang> donHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    donHangs.add(donHang);
                }
                trangThaiDonHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        thongKeDoanhSo.setThuHai(0.0f);
                        thongKeDoanhSo.setThuBa(0.0f);
                        thongKeDoanhSo.setThuTu(0.0f);
                        thongKeDoanhSo.setThuNam(0.0f);
                        thongKeDoanhSo.setThuSau(0.0f);
                        thongKeDoanhSo.setThuBay(0.0f);
                        thongKeDoanhSo.setChuNhat(0.0f);
                        for (int i = 0; i < tuanHienTai.size(); i++) {
                            final Integer j = i;
                            ArrayList<DonHang> donHangThanhCongs = new ArrayList<>();
                            for (DonHang donHang : donHangs) {
                                TrangThaiDonHang trangThaiDonHang = snapshot.child(donHang.getMaDonHang()).getValue(TrangThaiDonHang.class);
                                if (donHang.getThoiGianGiao().equalsIgnoreCase(tuanHienTai.get(i)) && trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                    donHangThanhCongs.add(donHang);
                                }
                            }
                            sanPhamDonHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ArrayList<SanPham> sanPhams = new ArrayList<>();
                                    for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                                        Sach sach = dataSnapshot.getValue(Sach.class);
                                        sanPhams.add(new SanPham(sach.getMaSach(), sach.getGiaTien(), sach.getKhuyenMai()));
                                    }
                                    for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                                        VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                                        sanPhams.add(new SanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getGiaTien(), vanPhongPham.getKhuyenMai()));
                                    }
                                    xuatKhoDonHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            float tong = 0.0f;
                                            for (DonHang donHang : donHangThanhCongs) {
                                                ArrayList<XuatKho> danhSachXuatKhos = new ArrayList<>();
                                                for (DataSnapshot dataSnapshot : snapshot.child(donHang.getMaDonHang()).getChildren()) {
                                                    XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                    danhSachXuatKhos.add(xuatKho);
                                                }
                                                for (XuatKho xuatKho : danhSachXuatKhos) {
                                                    for (SanPham sanPham : sanPhams) {
                                                        if (sanPham.getMaSanPham().equalsIgnoreCase(xuatKho.getMaSanPham())) {
                                                            tong += ((Float.valueOf(sanPham.getGiaTien()) - (Float.valueOf(sanPham.getGiaTien()) * Float.valueOf(sanPham.getKhuyenMai()) / 100)) * Float.valueOf(xuatKho.getSoLuongXuat())) / 1000000;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            switch (j) {
                                                case 0:
                                                    thongKeDoanhSo.setThuHai(tong);
                                                    break;
                                                case 1:
                                                    thongKeDoanhSo.setThuBa(tong);
                                                    break;
                                                case 2:
                                                    thongKeDoanhSo.setThuTu(tong);
                                                    break;
                                                case 3:
                                                    thongKeDoanhSo.setThuNam(tong);
                                                    break;
                                                case 4:
                                                    thongKeDoanhSo.setThuSau(tong);
                                                    break;
                                                case 5:
                                                    thongKeDoanhSo.setThuBay(tong);
                                                    break;
                                                case 6:
                                                    thongKeDoanhSo.setChuNhat(tong);
                                                    break;
                                            }
                                            ((ManHinhChinhQuanLyActivity) context).thielapDuLieuBarChart();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Màn hình theo dõi đơn hàng
    public void hienThiTheoDoiDonHang(String maKhachHang, ArrayList<TheoDoiDonHang> theoDoiDonHangs, TheoDoiDonHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DonHang> donHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
                        donHangs.add(donHang);
                    }
                }

                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<SanPham> sanPhams = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                            Sach sach = dataSnapshot.getValue(Sach.class);
                            sanPhams.add(new SanPham(sach.getMaSach(), sach.getGiaTien(), sach.getKhuyenMai()));
                        }
                        for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                            VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                            sanPhams.add(new SanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getGiaTien(), vanPhongPham.getKhuyenMai()));
                        }

                        nguoiDungDatabase.child("nhanvien").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<NhanVien> nhanViens = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    NhanVien nhanVien = dataSnapshot.getValue(NhanVien.class);
                                    nhanViens.add(nhanVien);
                                }

                                trangThaiDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        ArrayList<TrangThaiDonHang> trangThaiDonHangs = new ArrayList<>();
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            TrangThaiDonHang trangThaiDonHang = dataSnapshot.getValue(TrangThaiDonHang.class);
                                            trangThaiDonHangs.add(trangThaiDonHang);
                                        }

                                        xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                theoDoiDonHangs.clear();
                                                for (DonHang donHang : donHangs) {
                                                    TheoDoiDonHang theoDoiDonHang = new TheoDoiDonHang();
                                                    for (TrangThaiDonHang trangThaiDonHang : trangThaiDonHangs) {
                                                        if (trangThaiDonHang.getMaDonHang().equalsIgnoreCase(donHang.getMaDonHang())) {
                                                            theoDoiDonHang.setTrangThaiDon(trangThaiDonHang.getTrangThaiDon());
                                                            theoDoiDonHang.setTrangThaiDuyetNV(trangThaiDonHang.getTrangThaiDuyetNV());
                                                            theoDoiDonHang.setTrangThaiGiaoHangNV(trangThaiDonHang.getTrangThaiGiaoHangNV());
                                                            theoDoiDonHang.setTrangThaiGiaoHangKH(trangThaiDonHang.getTrangThaiGiaoHangKH());
                                                            theoDoiDonHang.setHinhThucThanhToan(trangThaiDonHang.getKieuThanhToan());
                                                            break;
                                                        }
                                                    }
                                                    if (!theoDoiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                                        int tong = 0;
                                                        ArrayList<XuatKho> danhSachXuatKhos = new ArrayList<>();
                                                        for (DataSnapshot dataSnapshot : snapshot.child(donHang.getMaDonHang()).getChildren()) {
                                                            XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                            danhSachXuatKhos.add(xuatKho);
                                                        }
                                                        for (XuatKho xuatKho : danhSachXuatKhos) {
                                                            for (SanPham sanPham : sanPhams) {
                                                                if (sanPham.getMaSanPham().equalsIgnoreCase(xuatKho.getMaSanPham())) {
                                                                    tong += (Integer.valueOf(sanPham.getGiaTien()) - (Integer.valueOf(sanPham.getGiaTien()) * Integer.valueOf(sanPham.getKhuyenMai()) / 100)) * Integer.valueOf(xuatKho.getSoLuongXuat());
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        theoDoiDonHang.setMaDonHang(donHang.getMaDonHang());
                                                        theoDoiDonHang.setNgayGiao(donHang.getThoiGianGiao());
                                                        theoDoiDonHang.setNgayLap(donHang.getThoiGianLap());
                                                        theoDoiDonHang.setTongTien(tong);
                                                        if (!donHang.getMaNhanVien().equalsIgnoreCase("")) {
                                                            for (NhanVien nhanVien : nhanViens) {
                                                                if (nhanVien.getMaNhanVien().equalsIgnoreCase(donHang.getMaNhanVien())) {
                                                                    theoDoiDonHang.setTenNhanVien(nhanVien.getTenNhanVien());
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            theoDoiDonHang.setTenNhanVien("Đang xử lý");
                                                        }
                                                        theoDoiDonHangs.add(theoDoiDonHang);
                                                    }
                                                }
                                                adapter.notifyDataSetChanged();
                                                ((TheoDoiDonHangActivity) context).donHangCanXacNhan();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Màn hình chi tiết giao hàng
    public void hienThiThongTinChiTietTheoDoiDonDang(String maKhachHang, String maDonHang, ArrayList<ChiTietTheoDoiDonHang> chiTietTheoDoiDonHangs, ChiTietTheoDoiDonHang_ThongTin chiTietTheoDoiDonHang_thongTin, ChiTietTheoDoiDonHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DonHang donHang = snapshot.child(maDonHang).getValue(DonHang.class);
                sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<ChiTietTheoDoiDonHang_SanPham> sanPhams = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                            Sach sach = dataSnapshot.getValue(Sach.class);
                            sanPhams.add(new ChiTietTheoDoiDonHang_SanPham(sach.getMaSach(), sach.getTenSach(), sach.getGiaTien(), sach.getKhuyenMai(), sach.getHinhSach()));
                        }
                        for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                            VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                            sanPhams.add(new ChiTietTheoDoiDonHang_SanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), vanPhongPham.getGiaTien(), vanPhongPham.getKhuyenMai(), vanPhongPham.getHinhVanPhongPham()));
                        }
                        xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                chiTietTheoDoiDonHangs.clear();
                                int tong = 0;
                                ArrayList<XuatKho> danhSachXuatKhos = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.child(donHang.getMaDonHang()).getChildren()) {
                                    XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                    danhSachXuatKhos.add(xuatKho);
                                }
                                for (XuatKho xuatKho : danhSachXuatKhos) {
                                    for (ChiTietTheoDoiDonHang_SanPham sanPham : sanPhams) {
                                        if (sanPham.getMaSanPham().equalsIgnoreCase(xuatKho.getMaSanPham())) {
                                            ChiTietTheoDoiDonHang chiTietTheoDoiDonHang = new ChiTietTheoDoiDonHang(sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getHinhSanPham(), Integer.valueOf(sanPham.getGiaTien()), Integer.valueOf(xuatKho.getSoLuongXuat()), Integer.valueOf(sanPham.getKhuyenMai()));
                                            chiTietTheoDoiDonHangs.add(chiTietTheoDoiDonHang);
                                            tong += (Integer.valueOf(sanPham.getGiaTien()) - (Integer.valueOf(sanPham.getGiaTien()) * Integer.valueOf(sanPham.getKhuyenMai()) / 100)) * Integer.valueOf(xuatKho.getSoLuongXuat());
                                            break;
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                chiTietTheoDoiDonHang_thongTin.setThoiGianLap(donHang.getThoiGianLap());
                                chiTietTheoDoiDonHang_thongTin.setThoiGianGiao(donHang.getThoiGianGiao());
                                chiTietTheoDoiDonHang_thongTin.setPhiVanChuyen(Integer.valueOf(donHang.getPhiVanChuyen()));
                                if (donHang.getPhiVanChuyen().equalsIgnoreCase("15000")) {
                                    chiTietTheoDoiDonHang_thongTin.setHinhThucGiao("Giao hàng tiêu chuẩn");
                                } else if (donHang.getPhiVanChuyen().equalsIgnoreCase("30000")) {
                                    chiTietTheoDoiDonHang_thongTin.setHinhThucGiao("Giao hàng nhanh");
                                }
                                chiTietTheoDoiDonHang_thongTin.setTongTienHang(tong);
                                chiTietTheoDoiDonHang_thongTin.setPhiVanChuyen(Integer.valueOf(donHang.getPhiVanChuyen()));

                                nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (!donHang.getMaNhanVien().equalsIgnoreCase("")) {
                                            NhanVien nhanVien = snapshot.child("nhanvien").child(donHang.getMaNhanVien()).getValue(NhanVien.class);
                                            chiTietTheoDoiDonHang_thongTin.setTenNhanVienGiaoHang(nhanVien.getTenNhanVien());
                                        } else {
                                            chiTietTheoDoiDonHang_thongTin.setTenNhanVienGiaoHang("Đang xử lý");
                                        }

                                        trangThaiDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                TrangThaiDonHang trangThaiDonHang = snapshot.child(donHang.getMaDonHang()).getValue(TrangThaiDonHang.class);
                                                chiTietTheoDoiDonHang_thongTin.setPhuongThucThanhToan(trangThaiDonHang.getKieuThanhToan());
                                                chiTietTheoDoiDonHang_thongTin.setTrangThaiDon(trangThaiDonHang.getTrangThaiDon());
                                                chiTietTheoDoiDonHang_thongTin.setTrangThaiDuyetNV(trangThaiDonHang.getTrangThaiDuyetNV());
                                                chiTietTheoDoiDonHang_thongTin.setTrangThaiGiaoHangKH(trangThaiDonHang.getTrangThaiGiaoHangKH());
                                                chiTietTheoDoiDonHang_thongTin.setTrangThaiGiaoHangNV(trangThaiDonHang.getTrangThaiGiaoHangNV());
                                                if (donHang.getMaGiamGia().equalsIgnoreCase("")) {
                                                    chiTietTheoDoiDonHang_thongTin.setGiamGia(0);
                                                    chiTietTheoDoiDonHang_thongTin.setTongTienThanhToan(chiTietTheoDoiDonHang_thongTin.getTongTienHang() + chiTietTheoDoiDonHang_thongTin.getPhiVanChuyen());
                                                    ((ChiTietTheoDoiDonHangActivity) context).hienThi();
                                                } else {

                                                    giamGiaDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            GiamGia giamGia = snapshot.child(maKhachHang).child(donHang.getMaGiamGia()).getValue(GiamGia.class);
                                                            chiTietTheoDoiDonHang_thongTin.setGiamGia(Integer.valueOf(giamGia.getTienGiamGia()));
                                                            chiTietTheoDoiDonHang_thongTin.setTongTienThanhToan(chiTietTheoDoiDonHang_thongTin.getTongTienHang() + chiTietTheoDoiDonHang_thongTin.getPhiVanChuyen() - chiTietTheoDoiDonHang_thongTin.getGiamGia());
                                                            ((ChiTietTheoDoiDonHangActivity) context).hienThi();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                        }
                                                    });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void xacNhanDaThanhToanOnline(String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDatabase.child(maDonHang).child("trangThaiChuyenTienKH").setValue("Đã xác nhận", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                ((ChiTietTheoDoiDonHangActivity) context).finish();
            }
        });
    }

    public void xacNhanNhanHang(String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDatabase.child(maDonHang).child("trangThaiGiaoHangKH").setValue("Đã xác nhận");
        trangThaiDatabase.child(maDonHang).child("trangThaiDon").setValue("Thành công");
        ((ChiTietTheoDoiDonHangActivity) context).finish();
    }

    public void khachHangHuyDonHang(String maDonHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        trangThaiDatabase.child(maDonHang).child("trangThaiDon").setValue("Hủy");
        trangThaiDatabase.child(maDonHang).child("lyDoHuy").setValue("Khách hàng hủy đơn");
        ((ChiTietTheoDoiDonHangActivity) context).finish();
    }

    //Màn hình chính khách hàng
    public void hienThiManHinhChinhKhachHang(KhachHang khachHang, ArrayList<ItemSanPham> sanPhams, ManHinhChinhKhachHangAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanPhams.clear();
                for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                    Sach sach = dataSnapshot.getValue(Sach.class);
                    sanPhams.add(new ItemSanPham(
                            sach.getMaSach(),
                            sach.getTenSach(),
                            sach.getHinhSach(),
                            sach.getTacGia(),
                            "",
                            sach.getTheLoai(),
                            sach.getNgayXuatBan(),
                            sach.getNhaXuatBan(),
                            "",
                            "",
                            Integer.valueOf(sach.getGiaTien()),
                            1,
                            Integer.valueOf(sach.getKhuyenMai()),
                            0,
                            0));
                }
                for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                    VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                    sanPhams.add(new ItemSanPham(
                            vanPhongPham.getMaVanPhongPham(),
                            vanPhongPham.getTenVanPhongPham(),
                            vanPhongPham.getHinhVanPhongPham(),
                            "",
                            vanPhongPham.getXuatXu(),
                            "",
                            "",
                            "",
                            vanPhongPham.getNhaPhanPhoi(),
                            vanPhongPham.getDonVi(),
                            Integer.valueOf(vanPhongPham.getGiaTien()),
                            1,
                            Integer.valueOf(vanPhongPham.getKhuyenMai()),
                            0,
                            0));
                }
                phanHoiDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (ItemSanPham sanPham : sanPhams) {
                            Integer tongDanhGia = 0;
                            Integer soLuongDanhGia = 0;
                            Integer soLuongBinhLuan = 0;
                            for (DataSnapshot phanHoiDataSnapshot : snapshot.getChildren()) {
                                PhanHoi phanHoi = phanHoiDataSnapshot.child(sanPham.getMaSanPham()).getValue(PhanHoi.class);
                                if (phanHoi != null) {
                                    if (phanHoi.getDanhGia() != null) {
                                        tongDanhGia += Integer.valueOf(phanHoi.getDanhGia());
                                        soLuongDanhGia++;
                                    }
                                    if (phanHoi.getBinhLuan() != null) {
                                        soLuongBinhLuan++;
                                    }
                                }
                            }
                            sanPham.setSoLuongBinhLuan(soLuongBinhLuan);
                            if (tongDanhGia != 0 && soLuongDanhGia != 0) {
                                sanPham.setTrungBinhDanhGia(tongDanhGia / soLuongDanhGia);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        ((ManHinhChinhKhachHangActivity) context).suKien();
                        nguoiDungDatabase.child("khachhang").child(khachHang.getMaKhachHang()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                KhachHang kh = snapshot.getValue(KhachHang.class);
                                khachHang.setTenKhachHang(kh.getTenKhachHang());
                                ((ManHinhChinhKhachHangActivity) context).hienThiTenKhachHang();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    //Thêm số lượng vào giỏ hàng
    public void themVaoGioHang(String maKhachHang, String maSanPham, String soLuong) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        GioHang gioHang = new GioHang(maSanPham, maKhachHang, soLuong);
        gioHangDatabase.child(maKhachHang).child(maSanPham).setValue(gioHang);
    }

    // Màn hình hiển thị danh sách nhân viên
    public void hienThiManHinhChinhQuanLyNhanVien(ArrayList<ItemNhanVien> nhanViens, QuanLyNhanVienRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nhanViens.clear();
                nguoiDungDatabase.child("nhanvien").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            NhanVien nhanVien = sachDataSnapshot.getValue(NhanVien.class);
                            nhanViens.add(new ItemNhanVien(
                                    nhanVien.getNguoiDung(),
                                    nhanVien.getMaNhanVien(),
                                    nhanVien.getTenNhanVien(),
                                    nhanVien.getCmnd(),
                                    nhanVien.getDiaChi(),
                                    nhanVien.getEmail(),
                                    nhanVien.getHinhNhanVien(),
                                    nhanVien.getLuong(),
                                    nhanVien.getMatKhau(),
                                    nhanVien.getSoDienThoai(),
                                    nhanVien.getTaiKhoan()
                            ));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    public void hienThiManHinhChinhQuanLySanPham(ArrayList<ItemSanPham> sanPhams, QuanLySanPhamRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sanPhams.clear();
                nguoiDungDatabase.child("nhanvien").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            //////////////
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    // Thêm nhân viên
    public void themNhanVien(String maNhanVien, String hinhNhanVien, String tenNhanVien, String cmnd, String diaChi, String email, String luongCoBan, String matKhau, String nguoiDung, String soDienThoai, String taiKhoan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("maNhanVien").setValue(maNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("hinhNhanVien").setValue(hinhNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("tenNhanVien").setValue(tenNhanVien);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("cmnd").setValue(cmnd);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("diaChi").setValue(diaChi);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("email").setValue(email);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("luong").setValue(luongCoBan);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(matKhau);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("matKhau").setValue(nguoiDung);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("soDienThoai").setValue(soDienThoai);
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("taiKhoan").setValue(taiKhoan);
    }

    // Xóa nhân viên
    public void xoaNhanVien(String maNhanVien, QuanLyNhanVienRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).removeValue();
    }

    // Sửa Nhân Viên
    public void suaNhanVien(Context context, String maNhanVien, String hinhNhanVien, String tenNhanVien, String cmnd, String diaChi, String email, String luongCoBan, String matKhau, String nguoiDung, String soDienThoai, String taiKhoan) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.child("nhanvien").child(maNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                nguoiDungDatabase.child(nhanVien.getMaNhanVien()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaNhanVienActivity) context).thongTinNhanVien(nhanVien);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("hinhNhanVien").setValue(hinhNhanVien);
                        nguoiDungDatabase.child("nhanvien").child(maNhanVien).child("tenNhanVien").setValue(tenNhanVien);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    // Hủy đơn hàng
    public void huyDonHang(String maNhanVien, String maDonHang, String lyDoHuy, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        donHangDatabase.child(maDonHang).child("maNhanVien").setValue(maNhanVien, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                trangThaiDatabase.child(maDonHang).child("lyDoHuy").setValue(lyDoHuy, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        trangThaiDatabase.child(maDonHang).child("trangThaiDon").setValue("Hủy", new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                AlertDialog.Builder b = new AlertDialog.Builder(context);
                                b.setTitle("THÔNG BÁO");
                                b.setMessage("Hủy đơn hàng thành công!");
                                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ((HuyDonHangActivity) context).finish();
                                    }
                                });
                                AlertDialog al = b.create();
                                al.show();
                            }
                        });
                    }
                });
            }
        });

    }

    // Thông tin khách hàng
    public void hienThiThongTinKhachHang(String maKhachHang, KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoidungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoidungDatabase.child("khachhang").child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KhachHang kh = snapshot.getValue(KhachHang.class);
                khachHang.setMaKhachHang(kh.getMaKhachHang());
                khachHang.setNguoiDung(kh.getNguoiDung());
                khachHang.setTenKhachHang(kh.getTenKhachHang());
                khachHang.setEmail(kh.getEmail());
                khachHang.setSoDienThoai(kh.getSoDienThoai());
                khachHang.setNganHang(kh.getNganHang());
                khachHang.setSoTaiKhoan(kh.getSoTaiKhoan());
                khachHang.setDiaChi(kh.getDiaChi());
                khachHang.setTaiKhoan(kh.getTaiKhoan());
                khachHang.setMatKhau(kh.getMatKhau());
                ((ThongTinKhachHangActivity) context).thongTinCaNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    // Thay đổi thông tin khách hàng
    public void thayDoiThongTinKhachHang(String maKhachHang, KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoidungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoidungDatabase.child("khachhang").child(maKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KhachHang kh = snapshot.getValue(KhachHang.class);
                khachHang.setMaKhachHang(kh.getMaKhachHang());
                khachHang.setNguoiDung(kh.getNguoiDung());
                khachHang.setTenKhachHang(kh.getTenKhachHang());
                khachHang.setEmail(kh.getEmail());
                khachHang.setSoDienThoai(kh.getSoDienThoai());
                khachHang.setNganHang(kh.getNganHang());
                khachHang.setSoTaiKhoan(kh.getSoTaiKhoan());
                khachHang.setDiaChi(kh.getDiaChi());
                khachHang.setTaiKhoan(kh.getTaiKhoan());
                khachHang.setMatKhau(kh.getMatKhau());
                ((ThayDoiThongTinKhachHangActivity) context).thongTinKhachHang();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    public void suaThongTinKhachHang(KhachHang khachHang, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoidungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoidungDatabase.child("khachhang").child(khachHang.getMaKhachHang()).setValue(khachHang, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("THÔNG BÁO");
                builder1.setMessage("Cập nhật thông tin thành công!");
                builder1.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ((ThayDoiThongTinKhachHangActivity) context).finish();
                    }
                });
                builder1.show();
            }
        });
    }

    //xoa San Pham
    public void xoaSanPham(String maSanPham, QuanLySanPhamRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("SANPHAM");
        gioHangDatabase.child("SACH").child(maSanPham).removeValue();
        gioHangDatabase.child("VANPHONGPHAM").child(maSanPham).removeValue();
    }

    //Hien thi item quan ly san pham
    public void hienThiItemQuanLySanPhamQL(ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLS, QuanLySanPhamQLRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemQuanLySanPhamQLS.clear();
                sanPhamDatabase.child("SACH").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            itemQuanLySanPhamQLS.add(new ItemQuanLySanPhamQL(sach.getHinhSach(), sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getSoLuongKho())));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
                sanPhamDatabase.child("VANPHONGPHAM").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot vanPhongPhamDataSnapshot : snapshot.getChildren()) {
                            VanPhongPham vanPhongPham = vanPhongPhamDataSnapshot.getValue(VanPhongPham.class);
                            itemQuanLySanPhamQLS.add(new ItemQuanLySanPhamQL(vanPhongPham.getHinhVanPhongPham(), vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getSoLuongKho())));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Hien thi item sach
    public void hienThiItemSach(ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLS, QuanLySanPhamQLRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemQuanLySanPhamQLS.clear();
                sanPhamDatabase.child("SACH").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sachDataSnapshot : snapshot.getChildren()) {
                            Sach sach = sachDataSnapshot.getValue(Sach.class);
                            itemQuanLySanPhamQLS.add(new ItemQuanLySanPhamQL(sach.getHinhSach(), sach.getMaSach(), sach.getTenSach(), Integer.valueOf(sach.getGiaTien()), Integer.valueOf(sach.getSoLuongKho())));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Hien thi item van phong pham
    public void hienThiItemVanPhongPham(ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLS, QuanLySanPhamQLRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemQuanLySanPhamQLS.clear();
                sanPhamDatabase.child("VANPHONGPHAM").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot vanPhongPhamDataSnapshot : snapshot.getChildren()) {
                            VanPhongPham vanPhongPham = vanPhongPhamDataSnapshot.getValue(VanPhongPham.class);
                            itemQuanLySanPhamQLS.add(new ItemQuanLySanPhamQL(vanPhongPham.getHinhVanPhongPham(), vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), Integer.valueOf(vanPhongPham.getGiaTien()), Integer.valueOf(vanPhongPham.getSoLuongKho())));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Man hinh nhap kho sach
    public void hienThiTTSachNhapKho(String maSanPham, Sach sach, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SANPHAM");
        sachDatabase.child("SACH").child(maSanPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sach s = snapshot.getValue(Sach.class);
                sach.setMaSach(s.getMaSach());
                sach.setGiaTien(s.getGiaTien());
                sach.setHinhSach(s.getHinhSach());
                sach.setKhuyenMai(s.getKhuyenMai());
                sach.setNgayXuatBan(s.getNgayXuatBan());
                sach.setNhaXuatBan(s.getNhaXuatBan());
                sach.setSoLuongKho(s.getSoLuongKho());
                sach.setSoLuongDanhGia(s.getSoLuongDanhGia());
                sach.setTacGia(s.getTacGia());
                sach.setTenSach(s.getTenSach());
                sach.setTheLoai(s.getTheLoai());
                sach.setAnhDanhGia(s.getAnhDanhGia());
                ((NhapKhoSachActivity) context).thongTinSach();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Man hinh nhap kho van phong pham
    public void hienThiTTVPPNhapKho(String maSanPham, VanPhongPham vanPhongPham, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SANPHAM");
        sachDatabase.child("VANPHONGPHAM").child(maSanPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VanPhongPham vpp = snapshot.getValue(VanPhongPham.class);
                vanPhongPham.setTenVanPhongPham(vpp.getTenVanPhongPham());
                vanPhongPham.setNhaPhanPhoi(vpp.getNhaPhanPhoi());
                vanPhongPham.setMaVanPhongPham(vpp.getMaVanPhongPham());
                vanPhongPham.setHinhVanPhongPham(vpp.getHinhVanPhongPham());
                vanPhongPham.setXuatXu(vpp.getXuatXu());
                vanPhongPham.setDonVi(vpp.getDonVi());
                vanPhongPham.setGiaTien(vpp.getGiaTien());
                vanPhongPham.setKhuyenMai(vpp.getKhuyenMai());
                vanPhongPham.setSoLuongKho(vpp.getSoLuongKho());
                ((NhapKhoVPPActivity) context).thongTinVPP();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Hien thi thong tin sach
    public void hienThiSach(String maSanPham, Sach sach, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SANPHAM");
        sachDatabase.child("SACH").child(maSanPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("test", snapshot.getValue() + " ");
                Sach s = snapshot.getValue(Sach.class);
                sach.setMaSach(s.getMaSach());
                sach.setGiaTien(s.getGiaTien());
                sach.setHinhSach(s.getHinhSach());
                sach.setKhuyenMai(s.getKhuyenMai());
                sach.setNgayXuatBan(s.getNgayXuatBan());
                sach.setNhaXuatBan(s.getNhaXuatBan());
                sach.setSoLuongKho(s.getSoLuongKho());
                sach.setSoLuongDanhGia(s.getSoLuongDanhGia());
                sach.setTacGia(s.getTacGia());
                sach.setTenSach(s.getTenSach());
                sach.setTheLoai(s.getTheLoai());
                sach.setAnhDanhGia(s.getAnhDanhGia());
                ((SuaSachActivity) context).thongTinSach();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }

    //Xoa san pham
    public void xoaSanPham(String maSanPham, QuanLySanPhamQLRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.child("SACH").child(maSanPham).removeValue();
        sanPhamDatabase.child("VANPHONGPHAM").child(maSanPham).removeValue();
    }

    //THEM SACH
    public void themSanPham_Sach(String maSach, String hinhSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan
            , String ngayXuatBan, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("SACH").child(maSach).child("maSach").setValue(maSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("hinhSach").setValue(hinhSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("tenSach").setValue(tenSach);
        nguoiDungDatabase.child("SACH").child(maSach).child("theLoao").setValue(theLoai);
        nguoiDungDatabase.child("SACH").child(maSach).child("tacGia").setValue(tacGia);
        nguoiDungDatabase.child("SACH").child(maSach).child("nhaXuatBan").setValue(nhaXuatBan);
        nguoiDungDatabase.child("SACH").child(maSach).child("ngayXuatBan").setValue(ngayXuatBan);
        nguoiDungDatabase.child("SACH").child(maSach).child("giaTien").setValue(giaTien);
        nguoiDungDatabase.child("SACH").child(maSach).child("soLongKho").setValue(soLuongKho);
    }

    // THÊM VPPP
    public void themSanPham_vpp(String maVanPhongPHam, String hinhVanPhongPham, String tenVanPhongPham
            , String nhaPhanPhoi, String xuatXu, String donVi
            , String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("maVanPhongPHam").setValue(maVanPhongPHam);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("hinhSach").setValue(hinhVanPhongPham);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("tenSach").setValue(tenVanPhongPham);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("theLoao").setValue(nhaPhanPhoi);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("tacGia").setValue(xuatXu);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("nhaXuatBan").setValue(donVi);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("giaTien").setValue(giaTien);
        nguoiDungDatabase.child("VANPHONGPHAM").child(maVanPhongPHam).child("soLongKho").setValue(soLuongKho);
    }

    public void suaSach(Context context, String maSach, String hinhSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan, String ngayXuatBan, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("SACH").child(maSach).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sach sach = snapshot.getValue(Sach.class);
                nguoiDungDatabase.child(sach.getMaSach()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaSanPhamSachActivity) context).thongTinSanPham(sach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("hinhSach").setValue(hinhSach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("tenSach").setValue(tenSach);
                        nguoiDungDatabase.child("SACH").child(maSach).child("theLoai").setValue(theLoai);
                        nguoiDungDatabase.child("SACH").child(maSach).child("tacGia").setValue(tacGia);
                        nguoiDungDatabase.child("SACH").child(maSach).child("nhaXuatBan").setValue(nhaXuatBan);
                        nguoiDungDatabase.child("SACH").child(maSach).child("ngayXuatBan").setValue(ngayXuatBan);
                        nguoiDungDatabase.child("SACH").child(maSach).child("giaTien").setValue(giaTien);
                        nguoiDungDatabase.child("SACH").child(maSach).child("soLuongKho").setValue(soLuongKho);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    public void suaVPP(Context context, String maVanPhongPham, String hinhVanPhongPham, String tenVanPhongPham, String nhaPhanPhoi, String xuatXu, String donVi, String giaTien, String soLuongKho) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("SANPHAM");
        nguoiDungDatabase.child("VPP").child(maVanPhongPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VanPhongPham vanPhongPham = snapshot.getValue(VanPhongPham.class);
                nguoiDungDatabase.child(vanPhongPham.getMaVanPhongPham()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ((SuaSanPhamVPPActivity) context).thongTinSanPhamVPP(vanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("hinhVanPhongPham").setValue(hinhVanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("tenVanPhongPham").setValue(tenVanPhongPham);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("nhaPhanPhoi").setValue(nhaPhanPhoi);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("xuatXu").setValue(xuatXu);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("donVi").setValue(donVi);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("giaTien").setValue(giaTien);
                        nguoiDungDatabase.child("VPP").child(maVanPhongPham).child("soLuongKho").setValue(soLuongKho);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("onCancelled", "Lỗi!" + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });

    }

    //DangNhap
    public void dangNhap(Context context, String type, String _taiKhoan, String _matKhau, boolean checkBox) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean check = false;
                if (type.equalsIgnoreCase("khachhang")) {
                    for (DataSnapshot dataSnapshot : snapshot.child(type).getChildren()) {
                        KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                        if (khachHang.getTaiKhoan().equals(_taiKhoan) && khachHang.getMatKhau().equals(_matKhau)) {
                            check = true;
                            //Khop mat khau va tai khoan, chuen snag man hinh chinh
                            sharePreferences.dangNhap(context, khachHang.getMaKhachHang());
                            Intent intent = new Intent(context, ManHinhChinhKhachHangActivity.class);
                            context.startActivity(intent);
                            if (checkBox) {
                                //goi ham luu thong tin dang nhap
                                sharePreferences.saveLoginInfo(context, _taiKhoan, _matKhau, checkBox);
                            } else {
                                sharePreferences.deleteLoginInfo(context);
                            }
                            return;
                        }
                    }
                } else if (type.equalsIgnoreCase("nhanvien")) {
                    for (DataSnapshot dataSnapshot : snapshot.child(type).getChildren()) {
                        NhanVien nhanVien = dataSnapshot.getValue(NhanVien.class);
                        if (nhanVien.getTaiKhoan().equals(_taiKhoan) && nhanVien.getMatKhau().equals(_matKhau)) {
                            check = true;
                            //Khop mat khau va tai khoan, chuen snag man hinh chinh
                            sharePreferences.dangNhap(context, nhanVien.getMaNhanVien());
                            Intent intent = new Intent(context, ManHinhChinhNhanVienActivity.class);
                            context.startActivity(intent);
                            if (checkBox == true) {
                                //goi ham luu thong tin dang nhap
                                sharePreferences.saveLoginInfo(context, _taiKhoan, _matKhau, checkBox);
                            } else {
                                sharePreferences.deleteLoginInfo(context);
                            }
                            return;
                        }
                    }
                } else if (type.equalsIgnoreCase("quanly")) {
                    for (DataSnapshot dataSnapshot : snapshot.child(type).getChildren()) {
                        QuanLy quanLy = dataSnapshot.getValue(QuanLy.class);
                        if (quanLy.getTaiKhoan().equals(_taiKhoan) && quanLy.getMatKhau().equals(_matKhau)) {
                            check = true;
                            //Khop mat khau va tai khoan, chuen snag man hinh chinh
                            sharePreferences.dangNhap(context, quanLy.getMaQuanLy());
                            Intent intent = new Intent(context, ManHinhChinhQuanLyActivity.class);
                            context.startActivity(intent);
                            if (checkBox == true) {
                                //goi ham luu thong tin dang nhap
                                sharePreferences.saveLoginInfo(context, _taiKhoan, _matKhau, checkBox);
                            } else {
                                sharePreferences.deleteLoginInfo(context);
                            }
                            return;
                        }
                    }
                }
                if (!check) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("LỖI ĐĂNG NHẬP");
                    alertDialog.setMessage("Không tìm thấy tài khoản! Hoặc sai mật khẩu");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //Dang ky
    public void dangKy(Context context, KhachHang khachHang) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean check = false;
                ArrayList<String> dsTaiKhoan = new ArrayList<>();
                for (DataSnapshot taiKhoanSnapshot : snapshot.child("khachhang").getChildren()) {
                    dsTaiKhoan.add(taiKhoanSnapshot.getKey());
                    KhachHang taiKhoan = taiKhoanSnapshot.getValue(KhachHang.class);
                    if (taiKhoan.getTaiKhoan().equals(khachHang.getTaiKhoan()) || taiKhoan.getEmail().equals(khachHang.getEmail())) {
                        check = true;
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("CẢNH BÁO");
                        alertDialog.setMessage("Tài khoản hoặc email đã tồn tại!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }
                if (check) {
                    return;
                } else {
                    String[] temp = dsTaiKhoan.get(dsTaiKhoan.size() - 1).split("kh");
                    String maKhachHang = "kh" + (Integer.valueOf(temp[1]) + 1);
                    khachHang.setMaKhachHang(maKhachHang);
                    nguoiDungDatabase.child("khachhang").child(maKhachHang).setValue(khachHang, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Chào mừng" + khachHang.getTenKhachHang());
                            alertDialog.setMessage("Bạn đã đăng ký thành công");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    ((DangKyActivity) context).finish();
                                }
                            });
                            alertDialog.show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Màn hình quên mật khẩu
    public void guiYeuCauQuaSoDienThoai(String taiKhoan, String soDienThoai, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean check = false;
                for (DataSnapshot dataSnapshot : snapshot.child("khachhang").getChildren()) {
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    if (khachHang.getTaiKhoan().equalsIgnoreCase(taiKhoan) && khachHang.getSoDienThoai().equalsIgnoreCase(soDienThoai)) {
                        check = true;
                        String sdt = "+84" + soDienThoai.substring(1);
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        PhoneAuthOptions options =
                                PhoneAuthOptions.newBuilder(firebaseAuth)
                                        .setPhoneNumber(sdt)
                                        .setTimeout(60L, TimeUnit.SECONDS)
                                        .setActivity((QuenMatKhauActivity) context)
                                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((QuenMatKhauXacNhanOTPActivity) context, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            FirebaseUser user = task.getResult().getUser();
                                                            Intent intent = new Intent(context, QuenMatKhauCapLaiMatKhauActivity.class);
                                                            intent.putExtra("taiKhoan", taiKhoan);
                                                            intent.putExtra("soDienThoai", user.getPhoneNumber());
                                                            context.startActivity(intent);
                                                        }
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String vertificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                super.onCodeSent(vertificationID, forceResendingToken);
                                                Intent intent = new Intent(context, QuenMatKhauXacNhanOTPActivity.class);
                                                intent.putExtra("taiKhoan", taiKhoan);
                                                intent.putExtra("soDienThoai", sdt);
                                                intent.putExtra("id", vertificationID);
                                                context.startActivity(intent);
                                            }
                                        })
                                        .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);
                        break;
                    }
                }
                if (!check) {
                    AlertDialog.Builder b = new AlertDialog.Builder(context);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Sai tài khoản hoặc số điện thoại! Vui lòng nhập lại!");
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void thayDoiMatKhau(String taiKhoan, String matKhau, String soDienThoai, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean check = false;
                for (DataSnapshot dataSnapshot : snapshot.child("khachhang").getChildren()) {
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    String sdt = "0" + soDienThoai.substring(3);
                    if (khachHang.getTaiKhoan().equalsIgnoreCase(taiKhoan) && khachHang.getSoDienThoai().equalsIgnoreCase(sdt)) {
                        nguoiDungDatabase.child("khachhang").child(khachHang.getMaKhachHang()).child("matKhau").setValue(matKhau);
                        check = true;
                        break;
                    }
                }
                if (check) {
                    AlertDialog.Builder b = new AlertDialog.Builder(context);
                    b.setTitle("THÔNG BÁO");
                    b.setMessage("Cập nhật mật khẩu thành công!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Intent intent = new Intent(context, DangNhapActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void xacNhanOTP(String verificationID, String maOTP, String taiKhoan, Context context) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, maOTP);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((QuenMatKhauXacNhanOTPActivity) context, (OnCompleteListener<AuthResult>) new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            Intent intent = new Intent(context, QuenMatKhauCapLaiMatKhauActivity.class);
                            intent.putExtra("taiKhoan", taiKhoan);
                            intent.putExtra("soDienThoai", user.getPhoneNumber());
                            context.startActivity(intent);
                        } else {
                            AlertDialog.Builder b = new AlertDialog.Builder(context);
                            b.setTitle("CẢNH BÁO");
                            b.setMessage("Mã OTP không chính xác! Vui lòng kiểm tra lại!");
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
                });

    }

    public void guiLaiMaOTP(HeThong hethong, String taiKhoan, String soDienThoai, Context context) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(soDienThoai)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity((QuenMatKhauXacNhanOTPActivity) context)
                        .setForceResendingToken(hethong.getmForceResendingToken())
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Executor) context, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = task.getResult().getUser();
                                            Intent intent = new Intent(context, QuenMatKhauCapLaiMatKhauActivity.class);
                                            intent.putExtra("taiKhoan", taiKhoan);
                                            intent.putExtra("soDienThoai", user.getPhoneNumber());
                                            context.startActivity(intent);
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                            }

                            @Override
                            public void onCodeSent(@NonNull String vertificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(vertificationID, forceResendingToken);
                                hethong.setVertificationID(vertificationID);
                                hethong.setmForceResendingToken(forceResendingToken);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // Đổi mật khẩu
    public void doiMatkhau(Context context, String oldPass, String newPass, String maKhachHang) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("khachhang").getChildren()) {
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    if (khachHang.getMaKhachHang().equalsIgnoreCase(maKhachHang) && khachHang.getMatKhau().equals(oldPass)) {
                        nguoiDungDatabase.child("khachhang").child(khachHang.getMaKhachHang()).child("matKhau").setValue(newPass);
                        //show dialog
                        AlertDialog alertDialogW = new AlertDialog.Builder(context).create();
                        alertDialogW.setTitle("THÔNG BÁO");
                        alertDialogW.setMessage("Mật khẩu của bạn đã được thay đổi");
                        alertDialogW.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ((DoiMatKhauActivity) context).finish();
                            }
                        });
                        alertDialogW.show();
                        return;
                    }
                }
                //show dialog
                AlertDialog alertDialogW = new AlertDialog.Builder(context).create();
                alertDialogW.setTitle("CẢNH BÁO");
                alertDialogW.setMessage("Mật khẩu cũ bạn nhập không chính xác");
                alertDialogW.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogW.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Màn hình lịch sử mua hàng
    public void hienThiLichSuMuaHang(String maKhachHang, ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs, LichSuMuaHangDonHangRecyclerViewAdapter adapter) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        donHangDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DonHang> donHangKHs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    if (donHang.getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
                        donHangKHs.add(donHang);
                    }
                }

                trangThaiDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<DonHang> donHangs = new ArrayList<>();
                        for (DonHang donHang : donHangKHs) {
                            TrangThaiDonHang trangThaiDonHang = snapshot.child(donHang.getMaDonHang()).getValue(TrangThaiDonHang.class);
                            if (trangThaiDonHang.getTrangThaiDon().equalsIgnoreCase("Thành công")) {
                                donHangs.add(donHang);
                            }
                        }

                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<ChiTietTheoDoiDonHang_SanPham> sanPhams = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                                    Sach sach = dataSnapshot.getValue(Sach.class);
                                    sanPhams.add(new ChiTietTheoDoiDonHang_SanPham(sach.getMaSach(), sach.getTenSach(), sach.getGiaTien(), sach.getKhuyenMai(), sach.getHinhSach()));
                                }
                                for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                                    VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                                    sanPhams.add(new ChiTietTheoDoiDonHang_SanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), vanPhongPham.getGiaTien(), vanPhongPham.getKhuyenMai(), vanPhongPham.getHinhVanPhongPham()));
                                }

                                xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        lichSuMuaHang_donHangs.clear();
                                        for (DonHang donHang : donHangs) {
                                            LichSuMuaHang_DonHang lichSuMuaHang_donHang = new LichSuMuaHang_DonHang();
                                            ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                            for (DataSnapshot dataSnapshot : snapshot.child(donHang.getMaDonHang()).getChildren()) {
                                                XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                xuatKhos.add(xuatKho);
                                            }
                                            int sum = 0;
                                            for (XuatKho xuatKho : xuatKhos) {
                                                for (ChiTietTheoDoiDonHang_SanPham sanPham : sanPhams) {
                                                    if (sanPham.getMaSanPham().equalsIgnoreCase(xuatKho.getMaSanPham())) {
                                                        LichSuMuaHang_SanPham lichSuMuaHang_sanPham = new LichSuMuaHang_SanPham(
                                                                sanPham.getMaSanPham(),
                                                                sanPham.getTenSanPham(),
                                                                sanPham.getHinhSanPham(),
                                                                Integer.valueOf(sanPham.getGiaTien()),
                                                                Integer.valueOf(sanPham.getKhuyenMai()),
                                                                Integer.valueOf(xuatKho.getSoLuongXuat()));
                                                        sum += lichSuMuaHang_sanPham.getTongTien();
                                                        lichSuMuaHang_donHang.getLichSuMuaHang_sanPhams().add(lichSuMuaHang_sanPham);
                                                        break;
                                                    }
                                                }
                                            }
                                            lichSuMuaHang_donHang.setMaDonHang(donHang.getMaDonHang());
                                            lichSuMuaHang_donHang.setNgayGiaoHang(donHang.getThoiGianGiao());
                                            lichSuMuaHang_donHang.setTrangThai("Thành công");
                                            lichSuMuaHang_donHang.setTongTien(sum);
                                            lichSuMuaHang_donHangs.add(lichSuMuaHang_donHang);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void muaLaiDonHang(String maKhachHang, ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference gioHangDatabase = firebaseDatabase.getReference("GIOHANG");
        gioHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (LichSuMuaHang_SanPham lichSuMuaHang_sanPham : lichSuMuaHang_sanPhams) {
                    GioHang gioHang = new GioHang(
                            lichSuMuaHang_sanPham.getMaSanPham(),
                            maKhachHang,
                            String.valueOf(lichSuMuaHang_sanPham.getSoLuongMua()));
                    gioHangDatabase.child(gioHang.getMaKhachHang()).child(gioHang.getMaSanPham()).setValue(gioHang);
                }
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Thêm vào giỏ hàng thành công!");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Màn hình đánh giá sản phẩm
    public void hienThiItemDanhGiaSanPham(String maDonHang, ArrayList<DanhGiaSanPham> danhGiaSanPhams, DanhGiaSanPhamRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference sanphamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference phanHoiDatabase = firebaseDatabase.getReference("PHANHOI");
        xuatKhoDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.child(maDonHang).getChildren()) {
                    XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                    xuatKhos.add(xuatKho);
                }

                sanphamDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<DanhGiaSanPham_SanPham> sanPhams = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                            Sach sach = dataSnapshot.getValue(Sach.class);
                            sanPhams.add(new DanhGiaSanPham_SanPham(sach.getMaSach(), sach.getTenSach(), sach.getHinhSach()));
                        }
                        for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                            VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                            sanPhams.add(new DanhGiaSanPham_SanPham(vanPhongPham.getMaVanPhongPham(), vanPhongPham.getTenVanPhongPham(), vanPhongPham.getHinhVanPhongPham()));
                        }

                        phanHoiDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                danhGiaSanPhams.clear();
                                for (XuatKho xuatKho : xuatKhos) {
                                    for (DanhGiaSanPham_SanPham sanPham : sanPhams) {
                                        if (xuatKho.getMaSanPham().equalsIgnoreCase(sanPham.getMaSanPham())) {
                                            PhanHoi phanHoi = snapshot.child(maDonHang).child(sanPham.getMaSanPham()).getValue(PhanHoi.class);
                                            DanhGiaSanPham danhGiaSanPham = null;
                                            if (phanHoi != null) {
                                                danhGiaSanPham = new DanhGiaSanPham(
                                                        sanPham.getMaSanPham(),
                                                        sanPham.getTenSanPham(),
                                                        sanPham.getHinhSanPham(),
                                                        phanHoi.getBinhLuan(),
                                                        Integer.valueOf(phanHoi.getDanhGia()));
                                            } else {
                                                danhGiaSanPham = new DanhGiaSanPham(
                                                        sanPham.getMaSanPham(),
                                                        sanPham.getTenSanPham(),
                                                        sanPham.getHinhSanPham(),
                                                        "",
                                                        0);
                                            }
                                            danhGiaSanPhams.add(danhGiaSanPham);
                                            break;
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void phanHoiCuaKhachHang(PhanHoi phanHoi, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("PHANHOI");
        donHangDatabase.child(phanHoi.getMaDonHang()).child(phanHoi.getMaSanPham()).setValue(phanHoi, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                ((DanhGiaSanPhamActivity) context).capNhat();
            }
        });
    }

    // Màn hình chính nhân viên
    public void hienThiThongTinNhanVien(String maNhanVien, NhanVien nhanVien, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        nguoiDungDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NhanVien nv = snapshot.child("nhanvien").child(maNhanVien).getValue(NhanVien.class);
                nhanVien.setTenNhanVien(nv.getTenNhanVien());
                nhanVien.setMaNhanVien(nv.getMaNhanVien());
                nhanVien.setHinhNhanVien(nv.getHinhNhanVien());
                ((ManHinhChinhNhanVienActivity) context).hienThiManHinhChinhNhanVien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Màn hình danh mục sản phẩm
    public void hienThiManHinhDanhMucSanPham(ArrayList<DanhMucSanPham> danhMucSanPhams, DanhMucSanPhamRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        sanPhamDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                danhMucSanPhams.clear();
                for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                    Sach sach = dataSnapshot.getValue(Sach.class);
                    danhMucSanPhams.add(new DanhMucSanPham(
                            sach.getTenSach(),
                            sach.getMaSach(),
                            sach.getHinhSach(),
                            Integer.valueOf(sach.getGiaTien()),
                            Integer.valueOf(sach.getKhuyenMai()),
                            Integer.valueOf(sach.getSoLuongKho())));
                }
                for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                    VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                    danhMucSanPhams.add(new DanhMucSanPham(
                            vanPhongPham.getTenVanPhongPham(),
                            vanPhongPham.getMaVanPhongPham(),
                            vanPhongPham.getHinhVanPhongPham(),
                            Integer.valueOf(vanPhongPham.getGiaTien()),
                            Integer.valueOf(vanPhongPham.getKhuyenMai()),
                            Integer.valueOf(vanPhongPham.getSoLuongKho())));
                }
                adapter.notifyDataSetChanged();
                ((DanhMucSanPhamActivity) context).suKien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Màn hình quản lý đơn hàng
    public void hienThiQuanLyDonHang(ArrayList<QuanLyDonHang_DonHang> quanLyDonHang_donHangs, QuanLyDonHangRecyclerViewAdapter adapter, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference donHangDatabase = firebaseDatabase.getReference("DONHANG");
        DatabaseReference trangThaiDatabase = firebaseDatabase.getReference("TRANGTHAIDONHANG");
        DatabaseReference sanPhamDatabase = firebaseDatabase.getReference("SANPHAM");
        DatabaseReference xuatKhoDatabase = firebaseDatabase.getReference("XUATKHO");
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG");
        DatabaseReference giamGiaDatabase = firebaseDatabase.getReference("GIAMGIA");

        trangThaiDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TrangThaiDonHang> trangThaiDonHangs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TrangThaiDonHang trangThaiDonHang = dataSnapshot.getValue(TrangThaiDonHang.class);
                    trangThaiDonHangs.add(trangThaiDonHang);
                }

                donHangDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<DonHang> donHangs = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            DonHang donHang = dataSnapshot.getValue(DonHang.class);
                            donHangs.add(donHang);
                        }

                        sanPhamDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<QuanLyDonHang> sanPhams = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.child("SACH").getChildren()) {
                                    Sach sach = dataSnapshot.getValue(Sach.class);
                                    sanPhams.add(new QuanLyDonHang(
                                            sach.getTenSach(),
                                            sach.getMaSach(),
                                            sach.getHinhSach(),
                                            sach.getSoLuongKho(),
                                            sach.getGiaTien(),
                                            sach.getKhuyenMai()));
                                }
                                for (DataSnapshot dataSnapshot : snapshot.child("VANPHONGPHAM").getChildren()) {
                                    VanPhongPham vanPhongPham = dataSnapshot.getValue(VanPhongPham.class);
                                    sanPhams.add(new QuanLyDonHang(
                                            vanPhongPham.getTenVanPhongPham(),
                                            vanPhongPham.getMaVanPhongPham(),
                                            vanPhongPham.getHinhVanPhongPham(),
                                            vanPhongPham.getSoLuongKho(),
                                            vanPhongPham.getGiaTien(),
                                            vanPhongPham.getKhuyenMai()));
                                }

                                nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        ArrayList<KhachHang> khachHangs = new ArrayList<>();
                                        for (DataSnapshot nguoiDungSnapshot : snapshot.getChildren()) {
                                            if (nguoiDungSnapshot.getKey().equalsIgnoreCase("khachhang")) {
                                                for (DataSnapshot dataSnapshot : nguoiDungSnapshot.getChildren()) {
                                                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                                                    khachHangs.add(khachHang);
                                                }
                                            } else if (nguoiDungSnapshot.getKey().equalsIgnoreCase("nhanvien")) {
                                                for (DataSnapshot dataSnapshot : nguoiDungSnapshot.getChildren()) {
                                                    NhanVien nhanVien = dataSnapshot.getValue(NhanVien.class);
                                                    KhachHang khachHang = new KhachHang();
                                                    khachHang.setMaKhachHang(nhanVien.getMaNhanVien());
                                                    khachHang.setTenKhachHang(nhanVien.getTenNhanVien());
                                                    khachHangs.add(khachHang);
                                                }
                                            }
                                        }

                                        giamGiaDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                ArrayList<GiamGia> giamGias = new ArrayList<>();
                                                for (DataSnapshot dataSnapshot : snapshot.child("tong").getChildren()) {
                                                    GiamGia giamGia = dataSnapshot.getValue(GiamGia.class);
                                                    giamGias.add(giamGia);
                                                }

                                                xuatKhoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        quanLyDonHang_donHangs.clear();
                                                        for (DonHang donHang : donHangs) {
                                                            QuanLyDonHang_DonHang quanLyDonHang_donHang = new QuanLyDonHang_DonHang();
                                                            ArrayList<XuatKho> xuatKhos = new ArrayList<>();
                                                            for (DataSnapshot dataSnapshot : snapshot.child(donHang.getMaDonHang()).getChildren()) {
                                                                XuatKho xuatKho = dataSnapshot.getValue(XuatKho.class);
                                                                xuatKhos.add(xuatKho);
                                                            }
                                                            int sum = 0;
                                                            for (XuatKho xuatKho : xuatKhos) {
                                                                for (QuanLyDonHang sanPham : sanPhams) {
                                                                    if (sanPham.getMaSanPham().equalsIgnoreCase(xuatKho.getMaSanPham())) {
                                                                        QuanLyDonHang_SanPham quanLyDonHang_sanPham = new QuanLyDonHang_SanPham(
                                                                                sanPham.getTenSanPham(),
                                                                                sanPham.getMaSanPham(),
                                                                                sanPham.getHinhSanPham(),
                                                                                Integer.valueOf(sanPham.getSoLuongKho()),
                                                                                Integer.valueOf(xuatKho.getSoLuongXuat()),
                                                                                Integer.valueOf(sanPham.getGiaTien()),
                                                                                Integer.valueOf(sanPham.getKhuyenMai()));
                                                                        sum += quanLyDonHang_sanPham.getTongTien();
                                                                        quanLyDonHang_donHang.getQuanLyDonHang_sanPhams().add(quanLyDonHang_sanPham);
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            for (TrangThaiDonHang trangThaiDonHang : trangThaiDonHangs) {
                                                                if (trangThaiDonHang.getMaDonHang().equalsIgnoreCase(donHang.getMaDonHang())) {
                                                                    quanLyDonHang_donHang.setTrangThai(trangThaiDonHang.getTrangThaiDon());
                                                                    quanLyDonHang_donHang.setTrangThaiDuyetNV(trangThaiDonHang.getTrangThaiDuyetNV());
                                                                    quanLyDonHang_donHang.setTrangThaiGiaoHangNV(trangThaiDonHang.getTrangThaiGiaoHangNV());
                                                                    quanLyDonHang_donHang.setTrangThaiGiaoHangKH(trangThaiDonHang.getTrangThaiGiaoHangKH());
                                                                    quanLyDonHang_donHang.setLyDoHuy(trangThaiDonHang.getLyDoHuy());
                                                                    break;
                                                                }
                                                            }
                                                            if (!donHang.getMaNhanVien().equalsIgnoreCase("")) {
                                                                for (KhachHang khachHang : khachHangs) {
                                                                    if (donHang.getMaNhanVien().equalsIgnoreCase(khachHang.getMaKhachHang())) {
                                                                        quanLyDonHang_donHang.setTenNhanVien(khachHang.getTenKhachHang());
                                                                    } else if (donHang.getMaKhachHang().equalsIgnoreCase(khachHang.getMaKhachHang())) {
                                                                        quanLyDonHang_donHang.setTenKhachHang(khachHang.getTenKhachHang());
                                                                        quanLyDonHang_donHang.setEmail(khachHang.getEmail());
                                                                        quanLyDonHang_donHang.setSoDienThoai(khachHang.getSoDienThoai());
                                                                    }
                                                                }
                                                            } else {
                                                                quanLyDonHang_donHang.setTenNhanVien("Đang xử lý");
                                                            }

                                                            if (!donHang.getMaGiamGia().equalsIgnoreCase("")) {
                                                                for (GiamGia giamGia : giamGias) {
                                                                    if (giamGia.getMaGiamGia().equalsIgnoreCase(donHang.getMaGiamGia())) {
                                                                        quanLyDonHang_donHang.setMaDonHang(giamGia.getMaGiamGia());
                                                                        quanLyDonHang_donHang.setGiamGia(Integer.valueOf(giamGia.getTienGiamGia()));
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                quanLyDonHang_donHang.setMaGiamGia("");
                                                                quanLyDonHang_donHang.setGiamGia(0);
                                                            }

                                                            quanLyDonHang_donHang.setDiaChi(donHang.getDiaChiGiao());
                                                            quanLyDonHang_donHang.setMaDonHang(donHang.getMaDonHang());
                                                            quanLyDonHang_donHang.setNgayLapHoaDon(donHang.getThoiGianLap());
                                                            quanLyDonHang_donHang.setNgayDuKienGiao(donHang.getThoiGianGiao());
                                                            quanLyDonHang_donHang.setPhiVanChuyen(Integer.valueOf(donHang.getPhiVanChuyen()));
                                                            sum = sum + quanLyDonHang_donHang.getPhiVanChuyen() - quanLyDonHang_donHang.getGiamGia();
                                                            quanLyDonHang_donHang.setTongTien(sum);
                                                            quanLyDonHang_donHangs.add(quanLyDonHang_donHang);
                                                        }
                                                        adapter.notifyDataSetChanged();
                                                        ((QuanLyDonHangActivity) context).suKien();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    // Hien thi thong tin van phong pham
    public void hienThiVanPhongPham(String maSanPham, VanPhongPham vanPhongPham, Context context) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sachDatabase = firebaseDatabase.getReference("SANPHAM");
        sachDatabase.child("VANPHONGPHAM").child(maSanPham).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("test", snapshot.getValue() + " ");
                VanPhongPham vpp = snapshot.getValue(VanPhongPham.class);
                vanPhongPham.setHinhVanPhongPham(vpp.getHinhVanPhongPham());
                vanPhongPham.setMaVanPhongPham(vpp.getMaVanPhongPham());
                vanPhongPham.setGiaTien(vpp.getGiaTien());
                vanPhongPham.setKhuyenMai(vpp.getKhuyenMai());
                vanPhongPham.setTenVanPhongPham(vpp.getTenVanPhongPham());
                vanPhongPham.setDonVi(vpp.getDonVi());
                vanPhongPham.setNhaPhanPhoi(vpp.getNhaPhanPhoi());
                vanPhongPham.setSoLuongKho(vpp.getSoLuongKho());
                vanPhongPham.setXuatXu(vpp.getXuatXu());
                ((SuaVanPhongPhamActivity) context).thongTinVanPhongPham();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "Lỗi!" + error.getMessage());
            }
        });
    }
    public void suaSanPhamSach(SuaSachActivity suaSachActivity, String maSach, String hinhAnh, String tenSach, String theLoai, String tacGia, String nhaXuatBan, String ngayXuatBan, String donGia, String soLuongTonKho) {


    }
}
