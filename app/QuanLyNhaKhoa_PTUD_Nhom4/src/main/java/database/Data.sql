INSERT INTO ChiNhanh VALUES (N'CN1', N'Quang Thanh', N'28 Lê Lai, TP.HCM', N'0908.030.910');

INSERT INTO NhomKhachHang VALUES (N'NK01', N'PTUD'), (N'NK02', N'VIP'), (N'NK03', N'Thân thiết');
INSERT INTO NgheNghiep VALUES (N'NN01', N'Sinh viên'), (N'NN02', N'Nhân viên văn phòng'), (N'NN03', N'Kinh doanh');
INSERT INTO LyDoKham VALUES (N'LD01', N'Đau răng'), (N'LD02', N'Kiểm tra định kỳ'), (N'LD03', N'Chỉnh nha'), (N'LD04', N'Implant');
INSERT INTO TienSuBenh VALUES (N'TS01', N'Béo phì'), (N'TS02', N'Tiểu đường'), (N'TS03', N'Cao huyết áp');
INSERT INTO DiUng VALUES (N'DU01', N'Penicillin'), (N'DU02', N'Hải sản'), (N'DU03', N'Không');
INSERT INTO ChanDoan VALUES (N'CD01', N'Sâu răng'), (N'CD02', N'Viêm tủy'), (N'CD03', N'Viêm nha chu'), (N'CD04', N'Răng khôn lệch');
INSERT INTO LoaiChiTieu VALUES (N'LC01', N'Vật tư'), (N'LC02', N'Lương'), (N'LC03', N'Mặt bằng'), (N'LC04', N'Labo');
INSERT INTO DonVi VALUES (N'DV01', N'Viên'), (N'DV02', N'Hộp'), (N'DV03', N'Cái'), (N'DV04', N'Tuýp');
INSERT INTO CachDungThuoc VALUES (N'CDU01', N'Sáng 1 viên'), (N'CDU02', N'Trưa 1 viên'), (N'CDU03', N'Chiều 1 viên'), (N'CDU04', N'Sau ăn');
INSERT INTO ThuocGoc VALUES (N'TG01', N'Paracetamol'), (N'TG02', N'Amoxicillin'), (N'TG03', N'Ibuprofen');
INSERT INTO DonThuocMau VALUES (N'DTM01', N'Đơn sau nhổ răng'), (N'DTM02', N'Đơn viêm nha chu');

INSERT INTO NhomDichVu (maNhom, tenNhomDichVu) VALUES
(N'ND01', N'ĐIỀU TRỊ NHA CHU'), (N'ND02', N'PHỤC HÌNH RĂNG SỨ'), (N'ND03', N'NHA KHOA TRẺ EM'),
(N'ND04', N'PHỤC HÌNH THÁO LẮP'), (N'ND05', N'CHUẨN ĐOÁN HÌNH ẢNH'), (N'ND06', N'NHA KHOA THẨM MỸ'),
(N'ND07', N'CHỈNH NHA'), (N'ND08', N'PHỤC HÌNH CỐ ĐỊNH'), (N'ND09', N'PHẪU THUẬT'), (N'ND10', N'IMPLANT');

INSERT INTO LoaiRang VALUES (N'LR01', N'Hàm trên', N'6 tháng'), (N'LR02', N'Hàm dưới', N'12 tháng'), (N'LR03', N'Răng sữa', N'Không');
INSERT INTO DieuTri VALUES (N'DT01', N'Răng'), (N'DT02', N'Nướu'), (N'DT03', N'Khớp thái dương hàm');
INSERT INTO LoaiSanPham VALUES (N'LSP01', N'Thuốc', N'Thuốc'), (N'LSP02', N'Vật liệu tiêu hao', N'Vật liệu tiêu hao'), (N'LSP03', N'Thiết bị nha khoa', N'Thiết bị nha khoa');
INSERT INTO NhaCungCap VALUES (N'NCC01', N'STRAUMANN', N'0281234567', N'TP.HCM');

INSERT INTO NhanVien VALUES
(N'NV01', N'Ts', N'Nguyễn Khắc Duy', NULL, 0, 0, 0, 0, N'duy@gmail.com', N'0123456789', N'Lễ tân', 1, N'CN1'),
(N'NV02', N'Bs', N'Hồ Văn Khánh', NULL, 0, 0, 0, 0, N'thuan08022006@gmail.com', N'0933942776', N'Bác sĩ', 1, N'CN1'),
(N'NV03', N'Bs', N'Lê Trần Quang Thanh', NULL, 0, 0, 0, 0, N'thanhqt@gmail.com', N'0908030910', N'Bác sĩ', 1, N'CN1');

INSERT INTO TaiKhoan (tenDangNhap, matKhau, email, vaiTro, maNhanVien, kichHoat) VALUES
(N'thuan080', N'123456', N'thuan08022006@gmail.com', N'Bác sĩ', N'NV02', 1),
(N'thanhqt', N'123456', N'thanhqt@gmail.com', N'Bác sĩ', N'NV03', 1),
(N'duy', N'123456', N'duy@gmail.com', N'Lễ tân', N'NV01', 1);

INSERT INTO DichVu VALUES
(N'DV01', N'ND07', N'Niềng răng mắc cài tự buộc', 27500000, 100, 8, 0, N'Hoạt động'),
(N'DV02', N'ND07', N'Niềng răng mắc cài kim loại tự buộc', 21500000, 100, 8, 0, N'Hoạt động'),
(N'DV03', N'ND07', N'Niềng răng mắc cài kim loại tiêu chuẩn', 18000000, 100, 8, 0, N'Hoạt động'),
(N'DV04', N'ND07', N'Niềng răng mắc cài sứ tiêu chuẩn', 25000000, 100, 8, 0, N'Hoạt động'),
(N'DV05', N'ND07', N'Niềng răng mắc cài Sapphire', 20000000, 100, 8, 0, N'Hoạt động'),
(N'DV06', N'ND07', N'Niềng răng invisalign', 60000000, 100, 8, 0, N'Hoạt động'),
(N'DV07', N'ND07', N'Niềng răng mắc cài mặt lưỡi (Mặt trong)', 60000000, 100, 8, 0, N'Hoạt động'),
(N'DV08', N'ND05', N'Chụp CT', 200000, 100, 8, 0, N'Hoạt động'),
(N'DV09', N'ND05', N'Chụp X-quang', 200000, 100, 8, 0, N'Hoạt động'),
(N'DV10', N'ND01', N'Điều trị viêm nha chu', 200000, 100, 8, 0, N'Hoạt động'),
(N'DV11', N'ND02', N'Răng sứ titan', 2500000, 100, 8, 1, N'Hoạt động'),
(N'DV12', N'ND10', N'Cấy ghép implant', 18000000, 100, 8, 1, N'Hoạt động'),
(N'DV13', N'ND06', N'Thăm khám - Tư vấn', 200000, 100, 8, 0, N'Hoạt động'),
(N'DV14', N'ND01', N'Cạo vôi - Đánh bóng', 700000, 100, 8, 0, N'Hoạt động');

INSERT INTO Rang VALUES (N'RHT', N'Hàm trên', N'LR01'), (N'RHD', N'Hàm dưới', N'LR02');
INSERT INTO Rang (maRang, tenRang, maLoaiRang)
SELECT N'R' + CAST(h AS NVARCHAR(1)) + CAST(i AS NVARCHAR(1)),
       N'R' + CAST(h AS NVARCHAR(1)) + CAST(i AS NVARCHAR(1)),
       CASE WHEN h IN (1, 2) THEN N'LR01' ELSE N'LR02' END
FROM (VALUES (1),(2),(3),(4)) H(h)
CROSS JOIN (VALUES (1),(2),(3),(4),(5),(6),(7),(8)) I(i);

INSERT INTO KhachHang (maKH, hoTen, gioiTinh, namSinh, soDienThoai, email, diaChi, maNhom, nhan, maNguon, maChiNhanh, lyDoKham, tienSuBenh, maBacSi, soLanKham, lanKhamGanNhat, ngayTao)
VALUES
(N'0001', N'Lê Trần Quang Thanh', N'Nam', 2006, N'03xxxx2808', N'thanhqt@gmail.com', N'28 Lê Lai', N'NK01', N'Nhổ răng khôn', N'NG01', N'CN1', N'Đau răng', N'Béo phì', N'NV03', 1, '2026-09-27', '2026-09-27'),
(N'0002', N'Nguyễn Minh Lý', N'Nam', NULL, N'09xxxx8352', N'minh@maysoft.vn', N'29 Nội Khu Mỹ Toàn - Q7 - TP.HCM', N'NK01', NULL, NULL, N'CN1', NULL, NULL, NULL, 0, NULL, '2026-09-27'),
(N'0003', N'Nguyễn Minh Thuận', N'Nam', NULL, NULL, NULL, NULL, N'NK01', NULL, N'NG01', N'CN1', NULL, NULL, N'NV03', 0, NULL, '2026-09-27');

INSERT INTO CongViec VALUES
(N'CV01', N'Kiểm tra định kỳ', 30, NULL, '2026-09-27T22:11:39'),
(N'CV02', N'Cạo vôi răng', 30, N'DV14', '2026-09-27T22:11:39'),
(N'CV03', N'Chỉnh nha', 60, N'DV01', '2026-09-27T22:11:39'),
(N'CV04', N'Tái khám', 60, N'DV13', '2026-09-27T22:11:39');

INSERT INTO LichHen VALUES
(N'LH01', N'CV03', N'0001', '2026-09-28T08:00:00', 60, N'NV03', NULL, NULL, N'Đang điều trị'),
(N'LH02', N'CV02', N'0002', '2026-09-28T09:30:00', 30, N'NV03', N'NV01', NULL, N'Đang đợi'),
(N'LH03', N'CV04', N'0003', '2026-09-28T14:00:00', 30, N'NV02', NULL, NULL, N'Hoàn tất'),
(N'LH04', N'CV04', N'0001', '2026-09-27T10:00:00', 45, N'NV03', NULL, NULL, N'Khách ra về');

INSERT INTO KeHoachDieuTri VALUES
(N'KH01', N'0001', '2026-09-27', N'Kế hoạch điều trị ngày 27/09/2026', N'Hoàn tất', '2026-09-27T22:11:39', N'thuan08022006@gmail.com');

INSERT INTO ChiTietKeHoachDieuTri VALUES
(N'CTK01', N'KH01', '2026-09-27', NULL, N'DV13', 1, 200000, 200000, N'Hoàn tất', N'0001'),
(N'CTK02', N'KH01', '2026-09-27', NULL, N'DV14', 1, 700000, 700000, N'Hoàn tất', N'0001');

INSERT INTO HoaDon VALUES
(N'0001', '2026-09-27', N'0001', N'KH01', N'NV03', 900000, 0, 900000, 0, 900000, N'Chưa thanh toán', N'Xuất từ kế hoạch điều trị', '2026-09-27T22:22:19', N'thuan08022006@gmail.com');

INSERT INTO ChiTietHoaDon VALUES
(N'CTH01', N'0001', '2026-09-27', NULL, N'DV13', 1, 200000, 200000, N'Kế hoạch điều trị ngày 27/09/2026'),
(N'CTH02', N'0001', '2026-09-27', NULL, N'DV14', 1, 700000, 700000, N'Kế hoạch điều trị ngày 27/09/2026');

INSERT INTO PhieuThu VALUES
(N'0001', '2026-09-27', N'0001', N'0001', N'bệnh', 72000, N'Tiền mặt', N'Đã chi trả', NULL, NULL, NULL, '2026-09-27T22:22:19', N'thuan08022006@gmail.com'),
(N'0002', '2026-09-28', N'0001', NULL, N'3 x aptx 4869 (36000)', 36000, N'Tiền mặt', N'Đã chi trả', N'NV03', NULL, NULL, '2026-09-28T21:34:12', N'thuan08022006@gmail.com');

INSERT INTO Thuoc VALUES
(N'0001', N'aptx 4869', N'DV01', 36000, N'Sáng 1 - Trưa 1 - Chiều 1', NULL),
(N'0002', N'Paracetamol 500mg', N'DV01', 2000, N'Sau ăn', N'TG01'),
(N'0003', N'Amoxicillin 500mg', N'DV01', 5000, N'Sáng tối', N'TG02');

INSERT INTO ToaThuoc VALUES
(N'TT01', N'0001', N'CD01', N'NV03', N'Tiền mặt', 1, NULL, 36000, '2026-09-28T21:34:12');
INSERT INTO ChiTietToaThuoc VALUES
(N'CTT01', N'TT01', N'0001', 1, N'Sáng 1 (36000) - Trưa 1 (36000) - Chiều 1 (36000)', 3, 36000, 36000);

INSERT INTO SanPham VALUES
(N'SP01', N'Găng tay', N'LSP02', 100, 1500),
(N'SP02', N'Khẩu trang', N'LSP02', 200, 800),
(N'SP03', N'Mũi khoan', N'LSP03', 10, 250000);

INSERT INTO KiemKho VALUES (N'000001', N'Kiểm Kho', N'Đã duyệt', N'thuan08022006@gmail.com', '2026-09-28T20:45:49');

INSERT INTO PhieuLabo VALUES
(N'0001', N'NCC01', N'NV03', N'0002', N'Mão sứ răng 11', N'Mới', '2026-09-28', NULL, NULL);

INSERT INTO ChamSocKhachHang VALUES
(N'CS01', N'0001', N'Nhắc tái khám', N'Điện thoại', '2026-09-28', N'Đã gọi');

INSERT INTO LichChamSoc VALUES
(N'LCS01', N'0002', N'Chăm sóc sau implant', '2026-10-05', N'NV03', N'Chờ gọi');
GO
