package view;

import view.model.CauHinhTrang;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class DuLieuMau {

    public static final String NGUOI = "thuan08022006@gmail.com";
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
                new Object[][]{
                    {"DV01", "CHỈNH NHA", "Niềng răng mắc cài tự buộc", "27,500,000", "Hoạt động"},
                    {"DV02", "CHỈNH NHA", "Niềng răng mắc cài kim loại tự buộc", "21,500,000", "Hoạt động"},
                    {"DV03", "CHỈNH NHA", "Niềng răng mắc cài kim loại tiêu chuẩn", "18,000,000", "Hoạt động"},
                    {"DV04", "CHỈNH NHA", "Niềng răng mắc cài sứ tiêu chuẩn", "25,000,000", "Hoạt động"},
                    {"DV05", "CHỈNH NHA", "Niềng răng mắc cài Sapphire", "20,000,000", "Hoạt động"},
                    {"DV06", "CHỈNH NHA", "Niềng răng invisalign", "60,000,000", "Hoạt động"},
                    {"DV07", "CHỈNH NHA", "Niềng răng mắc cài mặt lưỡi (Mặt trong)", "60,000,000", "Hoạt động"},
                    {"DV08", "CHUẨN ĐOÁN HÌNH ẢNH", "Chụp CT", "200,000", "Hoạt động"},
                    {"DV09", "CHUẨN ĐOÁN HÌNH ẢNH", "Chụp X-quang", "200,000", "Hoạt động"},
                    {"DV10", "ĐIỀU TRỊ NHA CHU", "Điều trị viêm nha chu", "200,000", "Hoạt động"},
                    {"DV11", "PHỤC HÌNH RĂNG SỨ", "Răng sứ titan", "2,500,000", "Hoạt động"},
                    {"DV12", "IMPLANT", "Cấy ghép implant", "18,000,000", "Hoạt động"}
                }, null));

        dat(new CauHinhTrang("nhomdichvu", "Nhóm dịch vụ", "Tìm nhóm dịch vụ...", "Thêm nhóm dịch vụ",
                new String[]{"Mã nhóm", "Tên nhóm dịch vụ", "Mô tả"},
                new Object[][]{
                    {"ND01", "ĐIỀU TRỊ NHA CHU", ""},
                    {"ND02", "PHỤC HÌNH RĂNG SỨ", ""},
                    {"ND03", "NHA KHOA TRẺ EM", ""},
                    {"ND04", "PHỤC HÌNH THÁO LẮP", ""},
                    {"ND05", "CHUẨN ĐOÁN HÌNH ẢNH", ""},
                    {"ND06", "NHA KHOA THẨM MỸ", ""},
                    {"ND07", "CHỈNH NHA", ""},
                    {"ND08", "PHỤC HÌNH CỐ ĐỊNH", ""},
                    {"ND09", "PHẪU THUẬT", ""},
                    {"ND10", "IMPLANT", ""}
                }, null));

        dat(new CauHinhTrang("nhanvien", "Nhân viên", "Tìm nhân viên...", "Thêm nhân viên",
                new String[]{"Mã NV", "Chức danh", "Họ và tên", "Email", "Số điện thoại", "Vai trò", "Kích hoạt", "Chi nhánh"},
                new Object[][]{
                    {"NV01", "Ts", "Nguyễn Khắc Duy", "duy@gmail.com", "0123456789", "Lễ tân", "Có", "CN1"},
                    {"NV02", "Bs", "Hồ Văn Khánh", "thuan08022006@gmail.com", "0933942776", "Bác sĩ", "Có", "CN1"},
                    {"NV03", "Bs", "Lê Trần Quang Thanh", "thanhqt@gmail.com", "0908030910", "Bác sĩ", "Có", "CN1"}
                }, null));

        List<Object[]> rang = new ArrayList<Object[]>();
        rang.add(new Object[]{"RHD", "Hàm dưới", "LR02"});
        rang.add(new Object[]{"RHT", "Hàm trên", "LR01"});
        String[] ham = {"1", "2", "3", "4"};
        for (String h : ham) {
            for (int i = 1; i <= 8; i++) {
                rang.add(new Object[]{"R" + h + i, "R" + h + i, h.equals("1") || h.equals("2") ? "LR01" : "LR02"});
            }
        }
        dat(new CauHinhTrang("rang", "Răng", "Tìm răng...", "Thêm răng",
                new String[]{"Mã răng", "Tên răng", "Loại răng"}, rang.toArray(new Object[0][]), null));

        dat(new CauHinhTrang("dieutri", "Điều trị", "Tìm điều trị...", "Thêm điều trị",
                new String[]{"Mã điều trị", "Tên điều trị"},
                new Object[][]{{"DT01", "Răng"}, {"DT02", "Nướu"}, {"DT03", "Khớp thái dương hàm"}}, null));

        dat(new CauHinhTrang("loairang", "Loại răng", "Tìm loại răng...", "Thêm loại răng",
                new String[]{"Mã loại răng", "Tên loại răng"},
                new Object[][]{{"LR01", "Hàm trên"}, {"LR02", "Hàm dưới"}, {"LR03", "Răng sữa"}}, null));

        dat(new CauHinhTrang("thoigian", "Thời gian làm việc", "Tìm thời gian làm việc...", "Thêm thời gian làm việc",
                new String[]{"Mã lịch", "Ngày", "Giờ bắt đầu", "Giờ kết thúc", "Bác sĩ", "Chi nhánh"},
                new Object[][]{}, null));

        dat(new CauHinhTrang("loaisanpham", "Loại sản phẩm", "Tìm loại sản phẩm...", "Thêm loại sản phẩm",
                new String[]{"Mã loại", "Tên loại sản phẩm"},
                new Object[][]{
                    {"LSP01", "Thuốc"},
                    {"LSP02", "Vật liệu tiêu hao"},
                    {"LSP03", "Thiết bị nha khoa"}
                }, null));

        dat(new CauHinhTrang("thuoc", "Thuốc", "Tìm thuốc...", "Thêm thuốc",
                new String[]{"Mã thuốc", "Tên thuốc", "Đơn vị", "Giá", "Cách dùng"},
                new Object[][]{
                    {"0001", "aptx 4869", "DV01", "36,000", "Sáng 1 - Trưa 1 - Chiều 1"},
                    {"0002", "Paracetamol 500mg", "DV01", "2,000", "Sau ăn"},
                    {"0003", "Amoxicillin 500mg", "DV01", "5,000", "Sáng tối"}
                }, null));

        dat(new CauHinhTrang("sanpham", "Sản phẩm", "Tìm sản phẩm...", "Thêm sản phẩm",
                new String[]{"Mã SP", "Tên sản phẩm", "Loại", "Số lượng", "Đơn giá"},
                new Object[][]{
                    {"SP01", "Găng tay", "LSP02", "100", "1,500"},
                    {"SP02", "Khẩu trang", "LSP02", "200", "800"},
                    {"SP03", "Mũi khoan", "LSP03", "10", "250,000"}
                }, null));

        dat(new CauHinhTrang("chinhanh", "Chi nhánh", "Tìm chi nhánh...", "Thêm chi nhánh",
                new String[]{"Mã chi nhánh", "Tên chi nhánh", "Địa chỉ", "Điện thoại"},
                new Object[][]{{"CN1", "Quang Thanh", "28 Lê Lai, TP.HCM", "0908.030.910"}}, null));

        dat(new CauHinhTrang("nhacungcap", "Nhà cung cấp", "Tìm nhà cung cấp...", "Thêm nhà cung cấp",
                new String[]{"Mã NCC", "Tên nhà cung cấp", "Điện thoại", "Địa chỉ"},
                new Object[][]{{"NCC01", "STRAUMANN", "0281234567", "TP.HCM"}}, null));

        dat(new CauHinhTrang("lichhen-ds", "Lịch hẹn", "Tìm lịch hẹn...", "Thêm lịch hẹn",
                new String[]{"Mã lịch hẹn", "Mã KH", "Bắt đầu", "Thời lượng (phút)", "Bác sĩ", "Phụ tá", "Ghi chú", "Trạng thái"},
                new Object[][]{{"LH01", "0001", "2026-09-28 08:00", "60", "NV03", "", "", "Đang điều trị"}}, null));

        dat(new CauHinhTrang("nhomkhach", "Nhóm khách hàng", "Tìm nhóm khách hàng...", "Thêm nhóm khách hàng",
                new String[]{"Mã nhóm", "Tên nhóm"},
                new Object[][]{{"NK01", "PTUD"}, {"NK02", "VIP"}, {"NK03", "Thân thiết"}}, null));

        dat(new CauHinhTrang("donvi", "Đơn vị", "Tìm đơn vị...", "Thêm đơn vị",
                new String[]{"Mã đơn vị", "Tên đơn vị"},
                new Object[][]{{"DV01", "Viên"}, {"DV02", "Hộp"}, {"DV03", "Cái"}, {"DV04", "Tuýp"}}, null));
    }

    private DuLieuMau() {
    }

    public static CauHinhTrang lay(String ma) {
        return DS.get(ma);
    }

    private static void dat(CauHinhTrang c) {
        DS.put(c.ma, c);
    }
}
