CREATE TABLE ChiNhanh (
    maChiNhanh    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenChiNhanh   NVARCHAR(100) NOT NULL,
    diaChi        NVARCHAR(255) NULL,
    dienThoai     NVARCHAR(20)  NULL
);

CREATE TABLE NhomKhachHang (
    maNhom   NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenNhom  NVARCHAR(50)  NOT NULL
);

CREATE TABLE NguonKhach (
    maNguon   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenNguon  NVARCHAR(50) NOT NULL
);

CREATE TABLE NgheNghiep (
    maNghe   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenNghe  NVARCHAR(80) NOT NULL
);

CREATE TABLE LyDoKham (
    maLyDo   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLyDo  NVARCHAR(100) NOT NULL
);

CREATE TABLE TienSuBenh (
    maTienSu   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenTienSu  NVARCHAR(100) NOT NULL
);

CREATE TABLE DiUng (
    maDiUng   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenDiUng  NVARCHAR(100) NOT NULL
);

CREATE TABLE ChanDoan (
    maChanDoan   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenChanDoan  NVARCHAR(100) NOT NULL
);

CREATE TABLE LoaiChiTieu (
    maLoai          NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLoaiChiTieu  NVARCHAR(80) NOT NULL
);

CREATE TABLE DonVi (
    maDonVi   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenDonVi  NVARCHAR(40) NOT NULL
);

CREATE TABLE CachDungThuoc (
    maCachDung   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenCachDung  NVARCHAR(80) NOT NULL
);

CREATE TABLE ThuocGoc (
    maThuocGoc   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenThuocGoc  NVARCHAR(80) NOT NULL
);

CREATE TABLE DonThuocMau (
    maMau   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenMau  NVARCHAR(100) NOT NULL
);

CREATE TABLE NhomDichVu (
    maNhom           NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenNhomDichVu    NVARCHAR(80)  NOT NULL,
    iconDichVu       NVARCHAR(50)  NULL,
    lichChamSoc      NVARCHAR(100) NULL,
    moTa             NVARCHAR(255) NULL
);

CREATE TABLE LoaiRang (
    maLoaiRang    NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLoaiRang   NVARCHAR(50) NOT NULL,
    baoHanh       NVARCHAR(30) NULL
);

CREATE TABLE DieuTri (
    maDieuTri   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenDieuTri  NVARCHAR(80) NOT NULL
);

CREATE TABLE LoaiSanPham (
    maLoai           NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLoaiSanPham   NVARCHAR(80) NOT NULL,
    loaiKho          NVARCHAR(80) NOT NULL
);

CREATE TABLE NhaCungCap (
    maNhaCungCap    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenNhaCungCap   NVARCHAR(100) NOT NULL,
    dienThoai       NVARCHAR(20)  NULL,
    diaChi          NVARCHAR(255) NULL
);

CREATE TABLE NhanVien (
    maNhanVien     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    chucDanh       NVARCHAR(20)  NULL,
    hoTen          NVARCHAR(100) NOT NULL,
    vietTat        NVARCHAR(20)  NULL,
    hoaHongChinh   FLOAT         NOT NULL DEFAULT 0,
    hoaHongPhu     FLOAT         NOT NULL DEFAULT 0,
    hoaHongTuVan   FLOAT         NOT NULL DEFAULT 0,
    hoaHongSale    FLOAT         NOT NULL DEFAULT 0,
    email          NVARCHAR(120) NULL,
    soDienThoai    NVARCHAR(20)  NULL,
    vaiTro         NVARCHAR(40)  NOT NULL,
    kichHoat       BIT           NOT NULL DEFAULT 1,
    maChiNhanh     NVARCHAR(10)  NULL,
    CONSTRAINT FK_NhanVien_ChiNhanh FOREIGN KEY (maChiNhanh) REFERENCES ChiNhanh(maChiNhanh)
);

CREATE TABLE TaiKhoan (
    tenDangNhap  NVARCHAR(50)  NOT NULL PRIMARY KEY,
    matKhau      NVARCHAR(100) NOT NULL,
    email        NVARCHAR(120) NULL,
    vaiTro       NVARCHAR(40)  NOT NULL,
    maNhanVien   NVARCHAR(10)  NULL,
    kichHoat     BIT           NOT NULL DEFAULT 1,
    ngayTao      DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE DichVu (
    maDichVu        NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maNhomDichVu    NVARCHAR(10)  NOT NULL,
    tenDichVu       NVARCHAR(150) NOT NULL,
    gia             BIGINT        NOT NULL,
    tyLeHoaHong     FLOAT         NOT NULL DEFAULT 100,
    thue            FLOAT         NOT NULL DEFAULT 8,
    canDatLabo      BIT           NOT NULL DEFAULT 0,
    trangThai       NVARCHAR(20)  NOT NULL DEFAULT N'Hoạt động',
    CONSTRAINT FK_DichVu_Nhom FOREIGN KEY (maNhomDichVu) REFERENCES NhomDichVu(maNhom)
);

CREATE TABLE Rang (
    maRang      NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenRang     NVARCHAR(40) NOT NULL,
    maLoaiRang  NVARCHAR(10) NULL,
    CONSTRAINT FK_Rang_Loai FOREIGN KEY (maLoaiRang) REFERENCES LoaiRang(maLoaiRang)
);

CREATE TABLE KhachHang (
    maKH             NVARCHAR(10)  NOT NULL PRIMARY KEY,
    hoTen            NVARCHAR(120) NOT NULL,
    gioiTinh         NVARCHAR(10)  NULL,
    ngaySinh         DATE          NULL,
    namSinh          INT           NULL,
    soDienThoai      NVARCHAR(20)  NULL,
    email            NVARCHAR(120) NULL,
    diaChi           NVARCHAR(255) NULL,
    cccd             NVARCHAR(20)  NULL,
    ngheNghiep       NVARCHAR(80)  NULL,
    ghiChu           NVARCHAR(255) NULL,
    danhGia          NVARCHAR(50)  NULL,
    maNhom           NVARCHAR(10)  NULL,
    nhan             NVARCHAR(80)  NULL,
    maGiaDinh        NVARCHAR(20)  NULL,
    maNguon          NVARCHAR(10)  NULL,
    maChiNhanh       NVARCHAR(10)  NULL,
    lyDoKham         NVARCHAR(100) NULL,
    tienSuBenh       NVARCHAR(100) NULL,
    diUng            NVARCHAR(100) NULL,
    maBacSi          NVARCHAR(10)  NULL,
    maTuVan          NVARCHAR(10)  NULL,
    maSale           NVARCHAR(10)  NULL,
    maCSKH           NVARCHAR(10)  NULL,
    nguoiGioiThieu   NVARCHAR(120) NULL,
    soLanKham        INT           NOT NULL DEFAULT 0,
    lanKhamGanNhat   DATE          NULL,
    ngayTao          DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_KH_Nhom FOREIGN KEY (maNhom) REFERENCES NhomKhachHang(maNhom),
    CONSTRAINT FK_KH_Nguon FOREIGN KEY (maNguon) REFERENCES NguonKhach(maNguon),
    CONSTRAINT FK_KH_CN FOREIGN KEY (maChiNhanh) REFERENCES ChiNhanh(maChiNhanh),
    CONSTRAINT FK_KH_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE CongViec (
    maCongViec      NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenCongViec     NVARCHAR(80)  NOT NULL,
    thoiLuongPhut   INT           NOT NULL DEFAULT 30,
    maDichVu        NVARCHAR(10)  NULL,
    ngayTao         DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_CV_DV FOREIGN KEY (maDichVu) REFERENCES DichVu(maDichVu)
);

CREATE TABLE LichHen (
    maLichHen       NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maCongViec      NVARCHAR(10)  NULL,
    maKH            NVARCHAR(10)  NOT NULL,
    batDau          DATETIME2     NOT NULL,
    thoiLuongPhut   INT           NOT NULL DEFAULT 30,
    maBacSi         NVARCHAR(10)  NULL,
    maPhuTa         NVARCHAR(10)  NULL,
    ghiChu          NVARCHAR(255) NULL,
    trangThai       NVARCHAR(30)  NOT NULL DEFAULT N'Mới',
    CONSTRAINT FK_LH_CV FOREIGN KEY (maCongViec) REFERENCES CongViec(maCongViec),
    CONSTRAINT FK_LH_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    CONSTRAINT FK_LH_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE KeHoachDieuTri (
    maKeHoach     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH          NVARCHAR(10)  NOT NULL,
    ngayKeHoach   DATE          NOT NULL,
    ghiChu        NVARCHAR(255) NULL,
    trangThai     NVARCHAR(30)  NOT NULL DEFAULT N'Mới',
    ngayTao       DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao      NVARCHAR(120) NULL,
    CONSTRAINT FK_KHDT_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE ChiTietKeHoachDieuTri (
    maChiTiet    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKeHoach    NVARCHAR(10)  NOT NULL,
    ngay         DATE          NOT NULL,
    maRang       NVARCHAR(10)  NULL,
    maDichVu     NVARCHAR(10)  NOT NULL,
    soLuong      INT           NOT NULL DEFAULT 1,
    donGia       BIGINT        NOT NULL,
    thanhTien    BIGINT        NOT NULL,
    trangThai    NVARCHAR(30)  NOT NULL DEFAULT N'Mới',
    maHoaDon     NVARCHAR(10)  NULL,
    CONSTRAINT FK_CTKH_KH FOREIGN KEY (maKeHoach) REFERENCES KeHoachDieuTri(maKeHoach),
    CONSTRAINT FK_CTKH_Rang FOREIGN KEY (maRang) REFERENCES Rang(maRang),
    CONSTRAINT FK_CTKH_DV FOREIGN KEY (maDichVu) REFERENCES DichVu(maDichVu)
);

CREATE TABLE HoaDon (
    maHoaDon     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayLap      DATE          NOT NULL,
    maKH         NVARCHAR(10)  NOT NULL,
    maKeHoach    NVARCHAR(10)  NULL,
    maBacSi      NVARCHAR(10)  NULL,
    tongTien     BIGINT        NOT NULL DEFAULT 0,
    chietKhau    BIGINT        NOT NULL DEFAULT 0,
    thanhTien    BIGINT        NOT NULL DEFAULT 0,
    daThu        BIGINT        NOT NULL DEFAULT 0,
    conNo        BIGINT        NOT NULL DEFAULT 0,
    trangThai    NVARCHAR(40)  NOT NULL DEFAULT N'Chưa thanh toán',
    ghiChu       NVARCHAR(255) NULL,
    ngayTao      DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao     NVARCHAR(120) NULL,
    CONSTRAINT FK_HD_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    CONSTRAINT FK_HD_KHDT FOREIGN KEY (maKeHoach) REFERENCES KeHoachDieuTri(maKeHoach),
    CONSTRAINT FK_HD_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE ChiTietHoaDon (
    maChiTiet    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maHoaDon     NVARCHAR(10)  NOT NULL,
    ngay         DATE          NULL,
    maRang       NVARCHAR(10)  NULL,
    maDichVu     NVARCHAR(10)  NOT NULL,
    soLuong      INT           NOT NULL DEFAULT 1,
    donGia       BIGINT        NOT NULL,
    thanhTien    BIGINT        NOT NULL,
    keHoach      NVARCHAR(120) NULL,
    CONSTRAINT FK_CTHD_HD FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    CONSTRAINT FK_CTHD_DV FOREIGN KEY (maDichVu) REFERENCES DichVu(maDichVu)
);

CREATE TABLE PhieuThu (
    maPhieuThu           NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayThu              DATE          NOT NULL,
    maKH                 NVARCHAR(10)  NOT NULL,
    maHoaDon             NVARCHAR(10)  NULL,
    lyDo                 NVARCHAR(255) NULL,
    soTien               BIGINT        NOT NULL,
    hinhThucThanhToan    NVARCHAR(40)  NOT NULL DEFAULT N'Tiền mặt',
    trangThai            NVARCHAR(30)  NOT NULL DEFAULT N'Đã chi trả',
    maBacSi              NVARCHAR(10)  NULL,
    maPhuTa              NVARCHAR(10)  NULL,
    nguoiGioiThieu       NVARCHAR(120) NULL,
    ngayTao              DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao             NVARCHAR(120) NULL,
    CONSTRAINT FK_PT_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    CONSTRAINT FK_PT_HD FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon)
);

CREATE TABLE PhieuChi (
    maPhieuChi           NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maNhaCungCap         NVARCHAR(10)  NULL,
    loaiChiTieu          NVARCHAR(40)  NULL,
    lyDoChi              NVARCHAR(255) NULL,
    soTien               BIGINT        NOT NULL,
    ngayChi              DATE          NOT NULL,
    hinhThucThanhToan    NVARCHAR(40)  NULL,
    trangThai            NVARCHAR(30)  NOT NULL DEFAULT N'Đã chi',
    ngayTao              DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao             NVARCHAR(120) NULL,
    CONSTRAINT FK_PC_NCC FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);

CREATE TABLE Thuoc (
    maThuoc      NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenThuoc     NVARCHAR(120) NOT NULL,
    maDonVi      NVARCHAR(10)  NULL,
    gia          BIGINT        NOT NULL,
    cachDung     NVARCHAR(120) NULL,
    maThuocGoc   NVARCHAR(10)  NULL,
    CONSTRAINT FK_Thuoc_DV FOREIGN KEY (maDonVi) REFERENCES DonVi(maDonVi),
    CONSTRAINT FK_Thuoc_Goc FOREIGN KEY (maThuocGoc) REFERENCES ThuocGoc(maThuocGoc)
);

CREATE TABLE ToaThuoc (
    maToaThuoc            NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH                  NVARCHAR(10)  NOT NULL,
    maChanDoan            NVARCHAR(10)  NULL,
    maBacSi               NVARCHAR(10)  NULL,
    hinhThucThanhToan     NVARCHAR(40)  NULL,
    nhanTaiPhongKham      BIT           NOT NULL DEFAULT 1,
    ghiChu                NVARCHAR(255) NULL,
    thanhTien             BIGINT        NOT NULL DEFAULT 0,
    ngayTao               DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_TT_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE ChiTietToaThuoc (
    maChiTiet     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maToaThuoc    NVARCHAR(10)  NOT NULL,
    maThuoc       NVARCHAR(10)  NOT NULL,
    soNgay        INT           NOT NULL DEFAULT 1,
    cachSuDung    NVARCHAR(255) NULL,
    tongSo        INT           NOT NULL DEFAULT 1,
    donGia        BIGINT        NOT NULL,
    thanhTien     BIGINT        NOT NULL,
    CONSTRAINT FK_CTTT_TT FOREIGN KEY (maToaThuoc) REFERENCES ToaThuoc(maToaThuoc),
    CONSTRAINT FK_CTTT_Thuoc FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc)
);

CREATE TABLE SanPham (
    maSanPham   NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenSanPham  NVARCHAR(120) NOT NULL,
    maLoai      NVARCHAR(10)  NULL,
    soLuong     INT           NOT NULL DEFAULT 0,
    donGia      BIGINT        NOT NULL DEFAULT 0,
    CONSTRAINT FK_SP_Loai FOREIGN KEY (maLoai) REFERENCES LoaiSanPham(maLoai)
);

CREATE TABLE PhieuNhapKho (
    maPhieuNhap     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayNhap        DATE          NOT NULL,
    loaiVatTu       NVARCHAR(80)  NULL,
    maNhaCungCap    NVARCHAR(10)  NULL,
    lyDo            NVARCHAR(255) NOT NULL,
    tongTien        BIGINT        NOT NULL DEFAULT 0,
    fileExcel       NVARCHAR(255) NULL,
    ngayTao         DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao        NVARCHAR(120) NULL,
    CONSTRAINT FK_PN_NCC FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);

CREATE TABLE ChiTietNhapKho (
    maChiTiet     NVARCHAR(10) NOT NULL PRIMARY KEY,
    maPhieuNhap   NVARCHAR(10) NOT NULL,
    maSanPham     NVARCHAR(10) NOT NULL,
    soLuong       INT          NOT NULL,
    donGia        BIGINT       NOT NULL,
    thanhTien     BIGINT       NOT NULL,
    CONSTRAINT FK_CTN_PN FOREIGN KEY (maPhieuNhap) REFERENCES PhieuNhapKho(maPhieuNhap),
    CONSTRAINT FK_CTN_SP FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);

CREATE TABLE PhieuXuatKho (
    maPhieuXuat     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayXuat        DATE          NOT NULL,
    loaiVatTu       NVARCHAR(80)  NULL,
    maNhaCungCap    NVARCHAR(10)  NULL,
    lyDo            NVARCHAR(255) NOT NULL,
    tongTien        BIGINT        NOT NULL DEFAULT 0,
    ngayTao         DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao        NVARCHAR(120) NULL
);

CREATE TABLE ChiTietXuatKho (
    maChiTiet     NVARCHAR(10) NOT NULL PRIMARY KEY,
    maPhieuXuat   NVARCHAR(10) NOT NULL,
    maSanPham     NVARCHAR(10) NOT NULL,
    soLuong       INT          NOT NULL,
    donGia        BIGINT       NOT NULL,
    thanhTien     BIGINT       NOT NULL,
    CONSTRAINT FK_CTX_PX FOREIGN KEY (maPhieuXuat) REFERENCES PhieuXuatKho(maPhieuXuat),
    CONSTRAINT FK_CTX_SP FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);

CREATE TABLE KiemKho (
    maKiemKho    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    lyDo         NVARCHAR(255) NULL,
    duyet        NVARCHAR(30)  NULL,
    nguoiDuyet   NVARCHAR(120) NULL,
    ngayTao      DATETIME2     NOT NULL DEFAULT SYSDATETIME()
);

CREATE TABLE PhieuLabo (
    maPhieuLabo     NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maNhaCungCap    NVARCHAR(10)  NULL,
    maBacSi         NVARCHAR(10)  NULL,
    maKH            NVARCHAR(10)  NULL,
    chiTiet         NVARCHAR(255) NULL,
    tinhTrang       NVARCHAR(30)  NULL,
    ngayGui         DATE          NULL,
    ngayNhan        DATE          NULL,
    ngayHoanThanh   DATE          NULL
);

CREATE TABLE PhieuImplant (
    maPhieuImplant  NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayGui         DATE          NULL,
    maKH            NVARCHAR(10)  NULL,
    maBacSi         NVARCHAR(10)  NULL,
    tinhTrang       NVARCHAR(30)  NULL,
    ngayNhan        DATETIME2     NULL,
    ngayHoanThanh   DATETIME2     NULL,
    maNhaCungCap    NVARCHAR(10)  NULL
);

CREATE TABLE ChiTietPhieuImplant (
    maChiTiet         NVARCHAR(10) NOT NULL PRIMARY KEY,
    maPhieuImplant    NVARCHAR(10) NOT NULL,
    vatTu             NVARCHAR(80) NULL,
    nhaSanXuat        NVARCHAR(80) NULL,
    baoHanh           NVARCHAR(30) NULL,
    soLuong           INT          NOT NULL DEFAULT 1,
    CONSTRAINT FK_CTI_PI FOREIGN KEY (maPhieuImplant) REFERENCES PhieuImplant(maPhieuImplant)
);

CREATE TABLE Voucher (
    maVoucher    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenVoucher   NVARCHAR(120) NOT NULL,
    phanTram     FLOAT         NOT NULL DEFAULT 0,
    tuNgay       DATE          NULL,
    denNgay      DATE          NULL,
    trangThai    NVARCHAR(20)  NULL,
    ngayTao      DATETIME2     NOT NULL DEFAULT SYSDATETIME()
);

CREATE TABLE ThoiGianLamViec (
    maLich        NVARCHAR(10) NOT NULL PRIMARY KEY,
    ngay          NVARCHAR(20) NULL,
    gioBatDau     TIME         NULL,
    gioKetThuc    TIME         NULL,
    maBacSi       NVARCHAR(10) NULL,
    maChiNhanh    NVARCHAR(10) NULL
);

CREATE TABLE ChamSocKhachHang (
    maCSKH      NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH        NVARCHAR(10)  NOT NULL,
    noiDung     NVARCHAR(255) NULL,
    kenh        NVARCHAR(40)  NULL,
    ngay        DATE          NULL,
    trangThai   NVARCHAR(30)  NULL,
    CONSTRAINT FK_CSKH_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE LichChamSoc (
    maLichChamSoc      NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH               NVARCHAR(10)  NOT NULL,
    noiDung            NVARCHAR(255) NULL,
    ngayHen            DATE          NULL,
    maNguoiPhuTrach    NVARCHAR(10)  NULL,
    trangThai          NVARCHAR(30)  NULL,
    CONSTRAINT FK_LCS_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
GO

/* ========== DỮ LIỆU MẪU ========== */
INSERT INTO ChiNhanh VALUES (N'CN1', N'Quang Thanh', N'28 Lê Lai, TP.HCM', N'0908.030.910');

INSERT INTO NhomKhachHang VALUES (N'NK01', N'PTUD'), (N'NK02', N'VIP'), (N'NK03', N'Thân thiết');
INSERT INTO NguonKhach VALUES (N'NG01', N'Cư dân'), (N'NG02', N'Facebook'), (N'NG03', N'Giới thiệu'), (N'NG04', N'Website');
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
