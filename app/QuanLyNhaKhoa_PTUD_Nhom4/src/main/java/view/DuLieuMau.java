package view;

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
                new String[]{"Nhóm dịch vụ", "Tên dịch vụ", "Giá", "Tỷ lệ hoa hồng", "Thuế", "Trạng thái"},
                new Object[][]{
                    {"CHỈNH NHA", "Niềng răng mắc cài tự buộc", "27,500,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng mắc cài kim loại tự buộc", "21,500,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng mắc cài kim loại tiêu chuẩn", "18,000,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng mắc cài sứ tiêu chuẩn", "25,000,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng mắc cài Sapphire", "20,000,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng invisalign", "60,000,000", "100", "8", "Hoạt động"},
                    {"CHỈNH NHA", "Niềng răng mắc cài mặt lưỡi (Mặt trong)", "60,000,000", "100", "8", "Không", "Hoạt động"},
                    {"CHUẨN ĐOÁN HÌNH ẢNH", "Chụp CT", "200,000", "100", "8", "Hoạt động"},
                    {"CHUẨN ĐOÁN HÌNH ẢNH", "Chụp X-quang", "200,000", "100", "8", "Hoạt động"},
                    {"ĐIỀU TRỊ NHA CHU", "Điều trị viêm nha chu", "200,000", "100", "8", "Hoạt động"},
                    {"PHỤC HÌNH RĂNG SỨ", "Răng sứ titan", "2,500,000", "100", "8", "Hoạt động"},
                    {"IMPLANT", "Cấy ghép implant", "18,000,000", "100", "8", "Hoạt động"}
                }, null));

        dat(new CauHinhTrang("nhomdichvu", "Nhóm dịch vụ", "Tìm nhóm dịch vụ...", "Thêm nhóm dịch vụ",
                new String[]{"Tên nhóm dịch vụ", "Icon dịch vụ", "Lịch chăm sóc khách hàng", "Mô tả thêm"},
                new Object[][]{
                    {"ĐIỀU TRỊ NHA CHU", "", "", ""},
                    {"PHỤC HÌNH RĂNG SỨ", "", "", ""},
                    {"NHA KHOA TRẺ EM", "", "", ""},
                    {"PHỤC HÌNH THÁO LẮP", "", "", ""},
                    {"CHUẨN ĐOÁN HÌNH ẢNH", "", "", ""},
                    {"NHA KHOA THẨM MỸ", "", "", ""},
                    {"CHỈNH NHA", "", "", ""},
                    {"PHỤC HÌNH CỐ ĐỊNH", "", "", ""},
                    {"PHẪU THUẬT", "", "", ""},
                    {"IMPLANT", "", "", ""}
                }, null));

        dat(new CauHinhTrang("nhanvien", "Nhân viên", "Tìm nhân viên...", "Thêm nhân viên",
                new String[]{"Chức danh", "Họ và tên", "Viết tắt", "Hoa hồng làm chính", "Hoa hồng làm phụ", "Hoa hồng tư vấn", "Hoa hồng sale", "Địa chỉ email", "Số điện thoại", "Vai trò", "Kích hoạt"},
                new Object[][]{
                    {"Ts", "Nguyễn Khắc Duy", "", "0", "0", "0", "0", "duy@gmail.com", "0123456789", "Lễ tân", "Có"},
                    {"Bs", "Hồ Văn Khánh", "", "0", "0", "0", "0", "thuan08022006@gmail.com", "0933942776", "Bác sĩ", "Có"},
                    {"Bs", "Lê Trần Quang Thanh", "", "0", "0", "0", "0", "thanhqt@gmail.com", "0908030910", "Bác sĩ", "Có"}
                }, null));

        List<Object[]> rang = new ArrayList<Object[]>();
        rang.add(new Object[]{"Hàm dưới"});
        rang.add(new Object[]{"Hàm trên"});
        String[] ham = {"1", "2", "3", "4"};
        for (String h : ham) {
            for (int i = 1; i <= 8; i++) {
                rang.add(new Object[]{"R" + h + i});
            }
        }
        dat(new CauHinhTrang("rang", "Răng", "Tìm răng...", "Thêm răng",
                new String[]{"Tên răng"}, rang.toArray(new Object[0][]), null));

        dat(new CauHinhTrang("dieutri", "Điều trị", "Tìm điều trị...", "Thêm điều trị",
                new String[]{"Điều trị"},
                new Object[][]{{"Răng"}, {"Nướu"}, {"Khớp thái dương hàm"}}, null));

        dat(new CauHinhTrang("loairang", "Loại răng", "Tìm loại răng...", "Thêm loại răng",
                new String[]{"Loại răng", "Bảo hành"},
                new Object[][]{{"Hàm trên", "6 tháng"}, {"Hàm dưới", "12 tháng"}, {"Răng sữa", "Không"}}, null));

        dat(new CauHinhTrang("tiensu", "Tiền sử bệnh", "Tìm tiền sử bệnh...", "Thêm tiền sử bệnh",
                new String[]{"Tiền sử bệnh"},
                new Object[][]{{"Béo phì"}, {"Tiểu đường"}, {"Cao huyết áp"}}, null));

        dat(new CauHinhTrang("voucher", "Voucher - Khuyến mãi", "Tìm Voucher - Khuyến mãi...", "Thêm Voucher - Khuyến mãi",
                new String[]{"Tên Voucher - Khuyến mãi", "Phần trăm", "Từ ngày", "Đến ngày", "Trạng thái", "Ngày tạo"},
                new Object[][]{}, null));

        dat(new CauHinhTrang("thoigian", "Thời gian làm việc", "Tìm thời gian làm việc...", "Thêm thời gian làm việc",
                new String[]{"Ngày", "Giờ bắt đầu", "Giờ kết thúc", "Bác sĩ", "Chi nhánh"},
                new Object[][]{}, null));

        dat(new CauHinhTrang("kiemkho", "Kiểm kho", "Tìm Kiểm kho...", "Thêm Kiểm kho",
                new String[]{"Mã kiểm kho", "Lý do", "Duyệt", "Người duyệt", "Ngày tạo"},
                new Object[][]{{"000001", "Kiểm Kho", "Đã duyệt", NGUOI, "28 Sep 2026 08:45:49 PM"}}, null));

        dat(new CauHinhTrang("loaisanpham", "Loại sản phẩm", "Tìm loại sản phẩm...", "Thêm loại sản phẩm",
                new String[]{"Loại sản phẩm", "Loại kho"},
                new Object[][]{
                    {"Thuốc", "Thuốc"},
                    {"Vật liệu tiêu hao", "Vật liệu tiêu hao"},
                    {"Thiết bị nha khoa", "Thiết bị nha khoa"}
                }, null));

        dat(new CauHinhTrang("thuoc", "Thuốc", "Tìm thuốc...", "Thêm thuốc",
                new String[]{"Mã thuốc", "Tên thuốc", "Đơn vị", "Giá", "Cách dùng"},
                new Object[][]{
                    {"0001", "aptx 4869", "Viên", "36,000", "Sáng 1 - Trưa 1 - Chiều 1"},
                    {"0002", "Paracetamol 500mg", "Viên", "2,000", "Sau ăn"},
                    {"0003", "Amoxicillin 500mg", "Viên", "5,000", "Sáng tối"}
                }, null));

        dat(new CauHinhTrang("sanpham", "Sản phẩm", "Tìm sản phẩm...", "Thêm sản phẩm",
                new String[]{"Mã", "Tên sản phẩm", "Loại", "Số lượng", "Đơn giá"},
                new Object[][]{
                    {"SP01", "Găng tay", "Vật liệu tiêu hao", "100", "1,500"},
                    {"SP02", "Khẩu trang", "Vật liệu tiêu hao", "200", "800"},
                    {"SP03", "Mũi khoan", "Thiết bị nha khoa", "10", "250,000"}
                }, null));

        dat(new CauHinhTrang("chinhanh", "Chi nhánh", "Tìm chi nhánh...", "Thêm chi nhánh",
                new String[]{"Mã", "Tên chi nhánh", "Địa chỉ", "Điện thoại"},
                new Object[][]{{"CN1", "Quang Thanh", "28 Lê Lai, TP.HCM", "0908.030.910"}}, null));

        dat(new CauHinhTrang("nhacungcap", "Nhà cung cấp", "Tìm nhà cung cấp...", "Thêm nhà cung cấp",
                new String[]{"Mã", "Nhà cung cấp", "Điện thoại", "Địa chỉ"},
                new Object[][]{{"NCC01", "STRAUMANN", "0281234567", "TP.HCM"}}, null));

        dat(new CauHinhTrang("lichhen-ds", "Lịch hẹn", "Tìm lịch hẹn...", "Thêm lịch hẹn",
                new String[]{"Công việc", "Bắt đầu", "Khách hàng", "Người làm chính"},
                new Object[][]{{"Chỉnh nha", "28 Sep 2026 08:47:00 PM", "Lê Trần Quang Thanh", "Lê Trần Quang Thanh"}}, null));
        dat(don("cachdung", "Cách dùng thuốc", "Sáng 1 viên", "Trưa 1 viên", "Chiều 1 viên", "Sau ăn"));
        dat(don("thuocgoc", "Thuốc gốc", "Paracetamol", "Amoxicillin", "Ibuprofen"));
        dat(don("donthuocmau", "Đơn thuốc mẫu", "Đơn sau nhổ răng", "Đơn viêm nha chu"));
        dat(don("chandoan", "Chẩn đoán", "Sâu răng", "Viêm tủy", "Viêm nha chu", "Răng khôn lệch"));
        dat(don("diung", "Dị ứng", "Penicillin", "Hải sản", "Không"));
        dat(don("nguon", "Nguồn", "Cư dân", "Facebook", "Giới thiệu", "Website"));
        dat(don("nhomkhach", "Nhóm khách hàng", "PTUD", "VIP", "Thân thiết"));        
        dat(don("lydokham", "Lý do đến khám", "Đau răng", "Kiểm tra định kỳ", "Chỉnh nha", "Implant"));
        dat(don("donvi", "Đơn vị", "Viên", "Hộp", "Cái", "Tuýp"));
        dat(don("nguoigioithieu", "Người giới thiệu", "Nguyễn Minh Thuận", "Hồ Văn Khánh"));                       
        dat(don("danhgia", "Đánh giá", "5 sao - Lê Trần Quang Thanh"));
        dat(don("donhang", "Đơn hàng", "Đơn vật tư tháng 9"));
        dat(don("dinhmuc", "Định mức", "Găng tay tối thiểu 50"));                
        dat(don("loaihinhanh", "Loại ảnh điều trị", "Trong miệng", "Ngoài mặt", "X-quang"));
        dat(don("capbac", "Cấp bậc", "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn"));       
    }

    private DuLieuMau() {
    }

    public static CauHinhTrang lay(String ma) {
        return DS.get(ma);
    }

    private static void dat(CauHinhTrang c) {
        DS.put(c.ma, c);
    }

    private static CauHinhTrang don(String ma, String tieuDe, String... dong) {
        Object[][] data = new Object[dong.length][1];
        for (int i = 0; i < dong.length; i++) {
            data[i][0] = dong[i];
        }
        String thuong = tieuDe.toLowerCase();
        return new CauHinhTrang(ma, tieuDe, "Tìm " + thuong + "...", "Thêm " + thuong,
                new String[]{tieuDe}, data, null);
    }
}
