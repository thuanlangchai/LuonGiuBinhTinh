package view;

import entity.KhachHang;
import entity.LoaiRang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.NhomDichVu;
import entity.SanPham;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import view.model.CauHinhTrang;

public final class DuLieuMau {

    public static final String NGUOI = "NV01";
    public static final String[] BAC_SI = {"Lê Trần Quang Thanh", "Hồ Văn Khánh", "Nguyễn Khắc Duy"};
    public static final String[] KHACH = {
        "0001 - Lê Trần Quang Thanh - 03xxxx2808",
        "0002 - Nguyễn Minh Lý",
        "0003 - Nguyễn Minh Thuận"
    };
    public static final String[] NHOM_DV = {
        "CHỈNH NHA", "CHUẨN ĐOÁN HÌNH ẢNH", "ĐIỀU TRỊ NHA CHU", "PHỤC HÌNH RĂNG SỨ",
        "NHA KHOA TRẺ EM", "PHỤC HÌNH THÁO LẮP", "NHA KHOA THẨM MỸ", "PHỤC HÌNH CỐ ĐỊNH",
        "PHẪU THUẬT", "IMPLANT"
    };
    public static final String[] TEN_DV = {
        "Niềng răng mắc cài tự buộc", "Cạo vôi - Đánh bóng", "Thăm khám - Tư vấn",
        "Nhổ răng khôn", "Trám răng", "Tẩy trắng răng"
    };

    private static final Map<String, CauHinhTrang> DS = new LinkedHashMap<String, CauHinhTrang>();

    static {
        dat(new CauHinhTrang("dichvu", "Dịch vụ", "Tìm dịch vụ...", "Thêm dịch vụ",
                new String[]{"Mã DV", "Nhóm dịch vụ", "Tên dịch vụ", "Giá", "Trạng thái"},
                null, null));

        dat(new CauHinhTrang("nhomdichvu", "Nhóm dịch vụ", "Tìm nhóm dịch vụ...", "Thêm nhóm dịch vụ",
                new String[]{"Mã nhóm", "Tên nhóm dịch vụ", "Mô tả"},
                null, null));

        dat(new CauHinhTrang("nhanvien", "Nhân viên", "Tìm nhân viên...", "Thêm nhân viên",
                new String[]{"Mã NV", "Chức danh", "Họ và tên", "Email", "Số điện thoại", "Vai trò", "Kích hoạt", "Chi nhánh"},
                null, null));
        
        dat(new CauHinhTrang("rang", "Răng", "Tìm răng...", "Thêm răng",
                new String[]{"Mã răng", "Tên răng", "Loại răng"}, null, null));

        dat(new CauHinhTrang("dieutri", "Điều trị", "Tìm điều trị...", "Thêm điều trị",
                new String[]{"Mã điều trị", "Tên điều trị"},
                null, null));

        dat(new CauHinhTrang("loairang", "Loại răng", "Tìm loại răng...", "Thêm loại răng",
                new String[]{"Mã loại răng", "Tên loại răng"},
                null, null));

        dat(new CauHinhTrang("thoigian", "Thời gian làm việc", "Tìm thời gian làm việc...", "Thêm thời gian làm việc",
                new String[]{"Mã lịch", "Ngày", "Giờ bắt đầu", "Giờ kết thúc", "Bác sĩ", "Chi nhánh"},
                null, null));

        dat(new CauHinhTrang("loaisanpham", "Loại sản phẩm", "Tìm loại sản phẩm...", "Thêm loại sản phẩm",
                new String[]{"Mã loại", "Tên loại sản phẩm"},
                null, null));

        dat(new CauHinhTrang("thuoc", "Thuốc", "Tìm thuốc...", "Thêm thuốc",
                new String[]{"Mã thuốc", "Tên thuốc", "Đơn vị", "Giá", "Cách dùng"},
                null, null));

        dat(new CauHinhTrang("sanpham", "Sản phẩm", "Tìm sản phẩm...", "Thêm sản phẩm",
                new String[]{"Mã SP", "Tên sản phẩm", "Loại", "Số lượng", "Đơn giá"},
                null, null));

        dat(new CauHinhTrang("chinhanh", "Chi nhánh", "Tìm chi nhánh...", "Thêm chi nhánh",
                new String[]{"Mã chi nhánh", "Tên chi nhánh", "Địa chỉ", "Điện thoại"},
                null, null));

        dat(new CauHinhTrang("nhacungcap", "Nhà cung cấp", "Tìm nhà cung cấp...", "Thêm nhà cung cấp",
                new String[]{"Mã NCC", "Tên nhà cung cấp", "Điện thoại", "Địa chỉ"},
                null, null));

        dat(new CauHinhTrang("lichhen-ds", "Lịch hẹn", "Tìm lịch hẹn...", "Thêm lịch hẹn",
                new String[]{"Mã lịch hẹn", "Mã KH", "Bắt đầu", "Thời lượng (phút)", "Bác sĩ", "Phụ tá", "Ghi chú", "Trạng thái"},
                null, null));

        dat(new CauHinhTrang("nhomkhach", "Nhóm khách hàng", "Tìm nhóm khách hàng...", "Thêm nhóm khách hàng",
                new String[]{"Mã nhóm", "Tên nhóm"},
                null, null));

        dat(new CauHinhTrang("donvi", "Đơn vị", "Tìm đơn vị...", "Thêm đơn vị",
                new String[]{"Mã đơn vị", "Tên đơn vị"},
                null, null));
    }

    private DuLieuMau() {
    }

    public static CauHinhTrang lay(String ma) {
        return DS.get(ma);
    }

    public static String[] dsKhach() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (KhachHang khachHang : new KhachHang_Service().findAllKhachHang()) {
                String sdt = khachHang.getSoDienThoai() == null || khachHang.getSoDienThoai().isBlank()
                        ? "" : " - " + khachHang.getSoDienThoai();
                ketQua.add(khachHang.getMaKH() + " - " + khachHang.getHoTen() + sdt);
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return KHACH;
    }

    public static String[] dsBacSi() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (NhanVien nhanVien : new DanhMuc_Service().findAllNhanVien()) {
                if (nhanVien.getHoTen() != null && !nhanVien.getHoTen().isBlank()) {
                    ketQua.add(nhanVien.getHoTen());
                }
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return BAC_SI;
    }

    public static String[] dsNhomDv() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (NhomDichVu nhom : new DanhMuc_Service().findAllNhomDichVu()) {
                if (nhom.getTenNhomDichVu() != null && !nhom.getTenNhomDichVu().isBlank()) {
                    ketQua.add(nhom.getTenNhomDichVu());
                }
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return NHOM_DV;
    }

    public static String[] dsSanPham() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (SanPham sanPham : new DanhMuc_Service().findAllSanPham()) {
                if (sanPham.getTenSanPham() != null && !sanPham.getTenSanPham().isBlank()) {
                    ketQua.add(sanPham.getTenSanPham());
                }
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return new String[]{"Găng tay", "Khẩu trang", "Mũi khoan", "aptx 4869"};
    }

    public static String[] dsLoaiRang() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (LoaiRang loaiRang : new DanhMuc_Service().findAllLoaiRang()) {
                ketQua.add(loaiRang.getMaLoaiRang() + " - " + loaiRang.getTenLoaiRang());
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return new String[]{"LR01 - Hàm trên", "LR02 - Hàm dưới", "LR03 - Răng sữa"};
    }

    public static String[] dsLoaiSanPham() {
        try {
            List<String> ketQua = new ArrayList<>();
            for (LoaiSanPham loaiSanPham : new DanhMuc_Service().findAllLoaiSanPham()) {
                ketQua.add(loaiSanPham.getMaLoai() + " - " + loaiSanPham.getTenLoaiSanPham());
            }
            if (!ketQua.isEmpty()) {
                return ketQua.toArray(new String[0]);
            }
        } catch (Exception ignored) {
        }
        return new String[]{"LSP01 - Vật tư tiêu hao", "LSP02 - Thuốc", "LSP03 - Thiết bị"};
    }

    public static String maBacSiTheoTen(String ten) {
        if (ten == null || ten.isBlank()) {
            return "";
        }
        try {
            for (NhanVien nhanVien : new DanhMuc_Service().findAllNhanVien()) {
                if (ten.equals(nhanVien.getHoTen())) {
                    return nhanVien.getMaNhanVien();
                }
            }
        } catch (Exception ignored) {
        }
        if ("Lê Trần Quang Thanh".equals(ten)) {
            return "NV03";
        }
        if ("Hồ Văn Khánh".equals(ten)) {
            return "NV02";
        }
        if ("Nguyễn Khắc Duy".equals(ten)) {
            return "NV01";
        }
        return "";
    }

    private static void dat(CauHinhTrang cauHinh) {
        DS.put(cauHinh.ma, cauHinh);
    }
}
