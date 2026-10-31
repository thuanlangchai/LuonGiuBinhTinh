IF DB_ID(N'QuanLyNhaKhoa') IS NOT NULL
BEGIN
    ALTER DATABASE QuanLyNhaKhoa SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QuanLyNhaKhoa;
END;
GO
CREATE DATABASE QuanLyNhaKhoa;
GO
USE QuanLyNhaKhoa;
GO

CREATE TABLE ChiNhanh (
    maChiNhanh    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenChiNhanh   NVARCHAR(100) NOT NULL,
    diaChi        NVARCHAR(255) NULL,
    dienThoai     NVARCHAR(20)  NULL
);

CREATE TABLE NhomKhachHang (
    maNhom   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenNhom  NVARCHAR(50) NOT NULL
);

CREATE TABLE NhomDichVu (
    maNhom          NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenNhomDichVu   NVARCHAR(80)  NOT NULL,
    moTa            NVARCHAR(255) NULL
);

CREATE TABLE LoaiRang (
    maLoaiRang   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLoaiRang  NVARCHAR(50) NOT NULL
);

CREATE TABLE DieuTri (
    maDieuTri   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenDieuTri  NVARCHAR(80) NOT NULL
);

CREATE TABLE DonVi (
    maDonVi   NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenDonVi  NVARCHAR(40) NOT NULL
);

CREATE TABLE LoaiSanPham (
    maLoai          NVARCHAR(10) NOT NULL PRIMARY KEY,
    tenLoaiSanPham  NVARCHAR(80) NOT NULL
);

CREATE TABLE NhaCungCap (
    maNhaCungCap   NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenNhaCungCap  NVARCHAR(100) NOT NULL,
    dienThoai      NVARCHAR(20)  NULL,
    diaChi         NVARCHAR(255) NULL
);

CREATE TABLE NhanVien (
    maNhanVien    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    chucDanh      NVARCHAR(20)  NULL,
    hoTen         NVARCHAR(100) NOT NULL,
    email         NVARCHAR(120) NULL,
    soDienThoai   NVARCHAR(20)  NULL,
    vaiTro        NVARCHAR(40)  NOT NULL,
    kichHoat      BIT           NOT NULL DEFAULT 1,
    maChiNhanh    NVARCHAR(10)  NULL,
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
    soDienThoai      NVARCHAR(20)  NULL,
    email            NVARCHAR(120) NULL,
    diaChi           NVARCHAR(255) NULL,
    cccd             NVARCHAR(20)  NULL,
    ghiChu           NVARCHAR(255) NULL,
    maNhom           NVARCHAR(10)  NULL,
    maChiNhanh       NVARCHAR(10)  NULL,
    lyDoKham         NVARCHAR(100) NULL,
    tienSuBenh       NVARCHAR(100) NULL,
    diUng            NVARCHAR(100) NULL,
    maBacSi          NVARCHAR(10)  NULL,
    maTuVan          NVARCHAR(10)  NULL,
    soLanKham        INT           NOT NULL DEFAULT 0,
    lanKhamGanNhat   DATE          NULL,
    ngayTao          DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_KH_Nhom FOREIGN KEY (maNhom) REFERENCES NhomKhachHang(maNhom),
    CONSTRAINT FK_KH_CN FOREIGN KEY (maChiNhanh) REFERENCES ChiNhanh(maChiNhanh),
    CONSTRAINT FK_KH_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien),
    CONSTRAINT FK_KH_TV FOREIGN KEY (maTuVan) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE LichHen (
    maLichHen       NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH            NVARCHAR(10)  NOT NULL,
    batDau          DATETIME2     NOT NULL,
    thoiLuongPhut   INT           NOT NULL DEFAULT 30,
    maBacSi         NVARCHAR(10)  NULL,
    maPhuTa         NVARCHAR(10)  NULL,
    ghiChu          NVARCHAR(255) NULL,
    trangThai       NVARCHAR(30)  NOT NULL DEFAULT N'Mới',
    CONSTRAINT FK_LH_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    CONSTRAINT FK_LH_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien),
    CONSTRAINT FK_LH_PT FOREIGN KEY (maPhuTa) REFERENCES NhanVien(maNhanVien)
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
    CONSTRAINT FK_CTHD_Rang FOREIGN KEY (maRang) REFERENCES Rang(maRang),
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
    CONSTRAINT FK_PT_HD FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    CONSTRAINT FK_PT_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien),
    CONSTRAINT FK_PT_PT FOREIGN KEY (maPhuTa) REFERENCES NhanVien(maNhanVien)
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
    maThuoc   NVARCHAR(10)  NOT NULL PRIMARY KEY,
    tenThuoc  NVARCHAR(120) NOT NULL,
    maDonVi   NVARCHAR(10)  NULL,
    gia       BIGINT        NOT NULL,
    cachDung  NVARCHAR(120) NULL,
    CONSTRAINT FK_Thuoc_DV FOREIGN KEY (maDonVi) REFERENCES DonVi(maDonVi)
);

CREATE TABLE ToaThuoc (
    maToaThuoc         NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maKH               NVARCHAR(10)  NOT NULL,
    maChanDoan         NVARCHAR(10)  NULL,
    maBacSi            NVARCHAR(10)  NULL,
    hinhThucThanhToan  NVARCHAR(40)  NULL,
    nhanTaiPhongKham   BIT           NOT NULL DEFAULT 1,
    ghiChu             NVARCHAR(255) NULL,
    thanhTien          BIGINT        NOT NULL DEFAULT 0,
    ngayTao            DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT FK_TT_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    CONSTRAINT FK_TT_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE ChiTietToaThuoc (
    maChiTiet    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    maToaThuoc   NVARCHAR(10)  NOT NULL,
    maThuoc      NVARCHAR(10)  NOT NULL,
    soNgay       INT           NOT NULL DEFAULT 1,
    cachSuDung   NVARCHAR(255) NULL,
    tongSo       INT           NOT NULL DEFAULT 1,
    donGia       BIGINT        NOT NULL,
    thanhTien    BIGINT        NOT NULL,
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
    maPhieuNhap    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayNhap       DATE          NOT NULL,
    loaiVatTu      NVARCHAR(80)  NULL,
    maNhaCungCap   NVARCHAR(10)  NULL,
    lyDo           NVARCHAR(255) NOT NULL,
    tongTien       BIGINT        NOT NULL DEFAULT 0,
    fileExcel      NVARCHAR(255) NULL,
    ngayTao        DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao       NVARCHAR(120) NULL,
    CONSTRAINT FK_PN_NCC FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);

CREATE TABLE ChiTietNhapKho (
    maChiTiet    NVARCHAR(10) NOT NULL PRIMARY KEY,
    maPhieuNhap  NVARCHAR(10) NOT NULL,
    maSanPham    NVARCHAR(10) NOT NULL,
    soLuong      INT          NOT NULL,
    donGia       BIGINT       NOT NULL,
    thanhTien    BIGINT       NOT NULL,
    CONSTRAINT FK_CTN_PN FOREIGN KEY (maPhieuNhap) REFERENCES PhieuNhapKho(maPhieuNhap),
    CONSTRAINT FK_CTN_SP FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);

CREATE TABLE PhieuXuatKho (
    maPhieuXuat    NVARCHAR(10)  NOT NULL PRIMARY KEY,
    ngayXuat       DATE          NOT NULL,
    loaiVatTu      NVARCHAR(80)  NULL,
    maNhaCungCap   NVARCHAR(10)  NULL,
    lyDo           NVARCHAR(255) NOT NULL,
    tongTien       BIGINT        NOT NULL DEFAULT 0,
    ngayTao        DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    nguoiTao       NVARCHAR(120) NULL,
    CONSTRAINT FK_PX_NCC FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);

CREATE TABLE ChiTietXuatKho (
    maChiTiet    NVARCHAR(10) NOT NULL PRIMARY KEY,
    maPhieuXuat  NVARCHAR(10) NOT NULL,
    maSanPham    NVARCHAR(10) NOT NULL,
    soLuong      INT          NOT NULL,
    donGia       BIGINT       NOT NULL,
    thanhTien    BIGINT       NOT NULL,
    CONSTRAINT FK_CTX_PX FOREIGN KEY (maPhieuXuat) REFERENCES PhieuXuatKho(maPhieuXuat),
    CONSTRAINT FK_CTX_SP FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);

CREATE TABLE ThoiGianLamViec (
    maLich      NVARCHAR(10) NOT NULL PRIMARY KEY,
    ngay        NVARCHAR(20) NULL,
    gioBatDau   TIME         NULL,
    gioKetThuc  TIME         NULL,
    maBacSi     NVARCHAR(10) NULL,
    maChiNhanh  NVARCHAR(10) NULL,
    CONSTRAINT FK_TGLV_BS FOREIGN KEY (maBacSi) REFERENCES NhanVien(maNhanVien),
    CONSTRAINT FK_TGLV_CN FOREIGN KEY (maChiNhanh) REFERENCES ChiNhanh(maChiNhanh)
);
GO

