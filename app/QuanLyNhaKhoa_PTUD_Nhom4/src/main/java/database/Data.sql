USE QuanLyNhaKhoa;
GO

INSERT INTO ChiNhanh (maChiNhanh, tenChiNhanh, diaChi, dienThoai) VALUES
(N'CN1', N'Quang Thanh', N'28 Lê Lai, TP.HCM', N'0908.030.910');

INSERT INTO NhomKhachHang (maNhom, tenNhom) VALUES
(N'NK01', N'PTUD'), (N'NK02', N'VIP'), (N'NK03', N'Thân thiết');

INSERT INTO NhomDichVu (maNhom, tenNhomDichVu, moTa) VALUES
(N'ND01', N'ĐIỀU TRỊ NHA CHU', NULL),
(N'ND02', N'PHỤC HÌNH RĂNG SỨ', NULL),
(N'ND03', N'NHA KHOA TRẺ EM', NULL),
(N'ND04', N'PHỤC HÌNH THÁO LẮP', NULL),
(N'ND05', N'CHUẨN ĐOÁN HÌNH ẢNH', NULL),
(N'ND06', N'NHA KHOA THẨM MỸ', NULL),
(N'ND07', N'CHỈNH NHA', NULL),
(N'ND08', N'PHỤC HÌNH CỐ ĐỊNH', NULL),
(N'ND09', N'PHẪU THUẬT', NULL),
(N'ND10', N'IMPLANT', NULL);

INSERT INTO LoaiRang (maLoaiRang, tenLoaiRang) VALUES
(N'LR01', N'Hàm trên'), (N'LR02', N'Hàm dưới'), (N'LR03', N'Răng sữa');

INSERT INTO DieuTri (maDieuTri, tenDieuTri) VALUES
(N'DT01', N'Răng'), (N'DT02', N'Nướu'), (N'DT03', N'Khớp thái dương hàm');

INSERT INTO DonVi (maDonVi, tenDonVi) VALUES
(N'DV01', N'Viên'), (N'DV02', N'Hộp'), (N'DV03', N'Cái'), (N'DV04', N'Tuýp');

INSERT INTO LoaiSanPham (maLoai, tenLoaiSanPham) VALUES
(N'LSP01', N'Thuốc'), (N'LSP02', N'Vật liệu tiêu hao'), (N'LSP03', N'Thiết bị nha khoa');

INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, dienThoai, diaChi) VALUES
(N'NCC01', N'STRAUMANN', N'0281234567', N'TP.HCM');

INSERT INTO NhanVien (maNhanVien, chucDanh, hoTen, email, soDienThoai, vaiTro, kichHoat, maChiNhanh) VALUES
(N'NV01', N'Ts', N'Nguyễn Khắc Duy', N'duy@gmail.com', N'0123456789', N'Lễ tân', 1, N'CN1'),
(N'NV02', N'Bs', N'Hồ Văn Khánh', N'thuan08022006@gmail.com', N'0933942776', N'Bác sĩ', 1, N'CN1'),
(N'NV03', N'Bs', N'Lê Trần Quang Thanh', N'thanhqt@gmail.com', N'0908030910', N'Bác sĩ', 1, N'CN1');

INSERT INTO TaiKhoan (tenDangNhap, matKhau, email, vaiTro, maNhanVien, kichHoat) VALUES
(N'thuan080', N'123456', N'thuan08022006@gmail.com', N'Bác sĩ', N'NV02', 1),
(N'thanhqt', N'123456', N'thanhqt@gmail.com', N'Bác sĩ', N'NV03', 1),
(N'duy', N'123456', N'duy@gmail.com', N'Lễ tân', N'NV01', 1);

INSERT INTO DichVu (maDichVu, maNhomDichVu, tenDichVu, gia, trangThai) VALUES
(N'DV01', N'ND07', N'Niềng răng mắc cài tự buộc', 27500000, N'Hoạt động'),
(N'DV02', N'ND07', N'Niềng răng mắc cài kim loại tự buộc', 21500000, N'Hoạt động'),
(N'DV03', N'ND07', N'Niềng răng mắc cài kim loại tiêu chuẩn', 18000000, N'Hoạt động'),
(N'DV04', N'ND07', N'Niềng răng mắc cài sứ tiêu chuẩn', 25000000, N'Hoạt động'),
(N'DV05', N'ND07', N'Niềng răng mắc cài Sapphire', 20000000, N'Hoạt động'),
(N'DV06', N'ND07', N'Niềng răng invisalign', 60000000, N'Hoạt động'),
(N'DV07', N'ND07', N'Niềng răng mắc cài mặt lưỡi (Mặt trong)', 60000000, N'Hoạt động'),
(N'DV08', N'ND05', N'Chụp CT', 200000, N'Hoạt động'),
(N'DV09', N'ND05', N'Chụp X-quang', 200000, N'Hoạt động'),
(N'DV10', N'ND01', N'Điều trị viêm nha chu', 200000, N'Hoạt động'),
(N'DV11', N'ND02', N'Răng sứ titan', 2500000, N'Hoạt động'),
(N'DV12', N'ND10', N'Cấy ghép implant', 18000000, N'Hoạt động'),
(N'DV13', N'ND06', N'Thăm khám - Tư vấn', 200000, N'Hoạt động'),
(N'DV14', N'ND01', N'Cạo vôi - Đánh bóng', 700000, N'Hoạt động');

INSERT INTO Rang (maRang, tenRang, maLoaiRang) VALUES
(N'RHT', N'Hàm trên', N'LR01'), (N'RHD', N'Hàm dưới', N'LR02');

INSERT INTO Rang (maRang, tenRang, maLoaiRang)
SELECT N'R' + CAST(h AS NVARCHAR(1)) + CAST(i AS NVARCHAR(1)),
       N'R' + CAST(h AS NVARCHAR(1)) + CAST(i AS NVARCHAR(1)),
       CASE WHEN h IN (1, 2) THEN N'LR01' ELSE N'LR02' END
FROM (VALUES (1),(2),(3),(4)) H(h)
CROSS JOIN (VALUES (1),(2),(3),(4),(5),(6),(7),(8)) I(i);

INSERT INTO KhachHang (maKH, hoTen, gioiTinh, ngaySinh, soDienThoai, email, diaChi, cccd, ghiChu, maNhom, maChiNhanh, lyDoKham, tienSuBenh, diUng, maBacSi, maTuVan, soLanKham, lanKhamGanNhat, ngayTao)
VALUES
(N'0001', N'Lê Trần Quang Thanh', N'Nam', '2006-08-27', N'03xxxx2808', N'thanhqt@gmail.com', N'28 Lê Lai', NULL, NULL, N'NK01', N'CN1', N'Đau răng', N'Béo phì', NULL, N'NV03', NULL, 1, '2026-09-27', '2026-09-27'),
(N'0002', N'Nguyễn Minh Lý', N'Nam', NULL, N'09xxxx8352', N'minh@maysoft.vn', N'29 Nội Khu Mỹ Toàn - Q7 - TP.HCM', NULL, NULL, N'NK01', N'CN1', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2026-09-27'),
(N'0003', N'Nguyễn Minh Thuận', N'Nam', NULL, NULL, NULL, NULL, NULL, NULL, N'NK01', N'CN1', NULL, NULL, NULL, N'NV03', NULL, 0, NULL, '2026-09-27');

INSERT INTO LichHen (maLichHen, maKH, batDau, thoiLuongPhut, maBacSi, maPhuTa, ghiChu, trangThai) VALUES
(N'LH01', N'0001', '2026-09-28T08:00:00', 60, N'NV03', NULL, NULL, N'Đang điều trị'),
(N'LH02', N'0002', '2026-09-28T09:30:00', 30, N'NV03', N'NV01', NULL, N'Đang đợi'),
(N'LH03', N'0003', '2026-09-28T14:00:00', 30, N'NV02', NULL, NULL, N'Hoàn tất'),
(N'LH04', N'0001', '2026-09-27T10:00:00', 45, N'NV03', NULL, NULL, N'Khách ra về');

INSERT INTO KeHoachDieuTri (maKeHoach, maKH, ngayKeHoach, ghiChu, trangThai, ngayTao, nguoiTao) VALUES
(N'KH01', N'0001', '2026-09-27', N'Kế hoạch điều trị ngày 27/09/2026', N'Hoàn tất', '2026-09-27T22:11:39', N'thuan08022006@gmail.com');

INSERT INTO HoaDon (maHoaDon, ngayLap, maKH, maKeHoach, maBacSi, tongTien, chietKhau, thanhTien, daThu, conNo, trangThai, ghiChu, ngayTao, nguoiTao) VALUES
(N'0001', '2026-09-27', N'0001', N'KH01', N'NV03', 900000, 0, 900000, 0, 900000, N'Chưa thanh toán', N'Xuất từ kế hoạch điều trị', '2026-09-27T22:22:19', N'thuan08022006@gmail.com');

INSERT INTO ChiTietKeHoachDieuTri (maChiTiet, maKeHoach, ngay, maRang, maDichVu, soLuong, donGia, thanhTien, trangThai, maHoaDon) VALUES
(N'CTK01', N'KH01', '2026-09-27', NULL, N'DV13', 1, 200000, 200000, N'Hoàn tất', N'0001'),
(N'CTK02', N'KH01', '2026-09-27', NULL, N'DV14', 1, 700000, 700000, N'Hoàn tất', N'0001');

INSERT INTO ChiTietHoaDon (maChiTiet, maHoaDon, ngay, maRang, maDichVu, soLuong, donGia, thanhTien, keHoach) VALUES
(N'CTH01', N'0001', '2026-09-27', NULL, N'DV13', 1, 200000, 200000, N'Kế hoạch điều trị ngày 27/09/2026'),
(N'CTH02', N'0001', '2026-09-27', NULL, N'DV14', 1, 700000, 700000, N'Kế hoạch điều trị ngày 27/09/2026');

INSERT INTO PhieuThu (maPhieuThu, ngayThu, maKH, maHoaDon, lyDo, soTien, hinhThucThanhToan, trangThai, maBacSi, maPhuTa, nguoiGioiThieu, ngayTao, nguoiTao) VALUES
(N'0001', '2026-09-27', N'0001', N'0001', N'bệnh', 72000, N'Tiền mặt', N'Đã chi trả', NULL, NULL, NULL, '2026-09-27T22:22:19', N'thuan08022006@gmail.com'),
(N'0002', '2026-09-28', N'0001', NULL, N'3 x aptx 4869 (36000)', 36000, N'Tiền mặt', N'Đã chi trả', N'NV03', NULL, NULL, '2026-09-28T21:34:12', N'thuan08022006@gmail.com');

INSERT INTO PhieuChi (maPhieuChi, maNhaCungCap, loaiChiTieu, lyDoChi, soTien, ngayChi, hinhThucThanhToan, trangThai, ngayTao, nguoiTao) VALUES
(N'PC01', N'NCC01', N'Vật tư', N'Mua găng tay', 150000, '2026-09-28', N'Tiền mặt', N'Đã chi', '2026-09-28T20:00:00', N'thuan08022006@gmail.com');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, gia, cachDung) VALUES
(N'0001', N'aptx 4869', N'DV01', 36000, N'Sáng 1 - Trưa 1 - Chiều 1'),
(N'0002', N'Paracetamol 500mg', N'DV01', 2000, N'Sau ăn'),
(N'0003', N'Amoxicillin 500mg', N'DV01', 5000, N'Sáng tối');

INSERT INTO ToaThuoc (maToaThuoc, maKH, maChanDoan, maBacSi, hinhThucThanhToan, nhanTaiPhongKham, ghiChu, thanhTien, ngayTao) VALUES
(N'TT01', N'0001', N'CD01', N'NV03', N'Tiền mặt', 1, NULL, 108000, '2026-09-28T21:34:12');

INSERT INTO ChiTietToaThuoc (maChiTiet, maToaThuoc, maThuoc, soNgay, cachSuDung, tongSo, donGia, thanhTien) VALUES
(N'CTT01', N'TT01', N'0001', 1, N'Sáng 1 - Trưa 1 - Chiều 1', 3, 36000, 108000);

INSERT INTO SanPham (maSanPham, tenSanPham, maLoai, soLuong, donGia) VALUES
(N'SP01', N'Găng tay', N'LSP02', 100, 1500),
(N'SP02', N'Khẩu trang', N'LSP02', 200, 800),
(N'SP03', N'Mũi khoan', N'LSP03', 10, 250000);

INSERT INTO PhieuNhapKho (maPhieuNhap, ngayNhap, loaiVatTu, maNhaCungCap, lyDo, tongTien, fileExcel, ngayTao, nguoiTao) VALUES
(N'PN01', '2026-09-28', N'Vật liệu tiêu hao', N'NCC01', N'Nhập vật tư tháng 9', 75000, NULL, '2026-09-28T09:00:00', N'thuan08022006@gmail.com');

INSERT INTO ChiTietNhapKho (maChiTiet, maPhieuNhap, maSanPham, soLuong, donGia, thanhTien) VALUES
(N'CTN01', N'PN01', N'SP01', 50, 1500, 75000);

INSERT INTO PhieuXuatKho (maPhieuXuat, ngayXuat, loaiVatTu, maNhaCungCap, lyDo, tongTien, ngayTao, nguoiTao) VALUES
(N'PX01', '2026-09-28', N'Vật liệu tiêu hao', N'NCC01', N'Xuất dùng phòng khám', 15000, '2026-09-28T16:00:00', N'thuan08022006@gmail.com');

INSERT INTO ChiTietXuatKho (maChiTiet, maPhieuXuat, maSanPham, soLuong, donGia, thanhTien) VALUES
(N'CTX01', N'PX01', N'SP01', 10, 1500, 15000);

INSERT INTO ThoiGianLamViec (maLich, ngay, gioBatDau, gioKetThuc, maBacSi, maChiNhanh) VALUES
(N'TL01', N'Mo, Tu, We, Th, Fr', '08:00', '17:00', N'NV03', N'CN1');
GO
