SET QUOTED_IDENTIFIER ON;
GO
USE QuanLyNhaKhoa;
GO

IF COL_LENGTH(N'LichHen', N'maKeHoach') IS NULL
    ALTER TABLE LichHen ADD maKeHoach NVARCHAR(10) NULL;
GO

UPDATE LichHen
SET maKeHoach = SUBSTRING(
        ghiChu,
        CHARINDEX(N'[KHDT:', ghiChu) + 6,
        CHARINDEX(N']', ghiChu, CHARINDEX(N'[KHDT:', ghiChu)) - CHARINDEX(N'[KHDT:', ghiChu) - 6)
WHERE ghiChu LIKE N'%[[]KHDT:%'
  AND maKeHoach IS NULL
  AND CHARINDEX(N']', ghiChu, CHARINDEX(N'[KHDT:', ghiChu)) > CHARINDEX(N'[KHDT:', ghiChu) + 6;
GO

UPDATE LichHen
SET maKeHoach = NULL
WHERE maKeHoach IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM KeHoachDieuTri k WHERE k.maKeHoach = LichHen.maKeHoach);
GO

IF NOT EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = N'FK_LH_KHDT')
    ALTER TABLE LichHen ADD CONSTRAINT FK_LH_KHDT FOREIGN KEY (maKeHoach) REFERENCES KeHoachDieuTri(maKeHoach);
GO

UPDATE ChiTietKeHoachDieuTri
SET maHoaDon = NULL
WHERE maHoaDon IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM HoaDon h WHERE h.maHoaDon = ChiTietKeHoachDieuTri.maHoaDon);
GO

IF NOT EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = N'FK_CTKH_HD')
    ALTER TABLE ChiTietKeHoachDieuTri ADD CONSTRAINT FK_CTKH_HD FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_LichHen_maKH_batDau' AND object_id = OBJECT_ID(N'LichHen'))
    CREATE INDEX IX_LichHen_maKH_batDau ON LichHen(maKH, batDau);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_LichHen_maBacSi_batDau' AND object_id = OBJECT_ID(N'LichHen'))
    CREATE INDEX IX_LichHen_maBacSi_batDau ON LichHen(maBacSi, batDau) WHERE maBacSi IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_LichHen_maPhuTa_batDau' AND object_id = OBJECT_ID(N'LichHen'))
    CREATE INDEX IX_LichHen_maPhuTa_batDau ON LichHen(maPhuTa, batDau) WHERE maPhuTa IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_LichHen_batDau' AND object_id = OBJECT_ID(N'LichHen'))
    CREATE INDEX IX_LichHen_batDau ON LichHen(batDau);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_LichHen_maKeHoach' AND object_id = OBJECT_ID(N'LichHen'))
    CREATE INDEX IX_LichHen_maKeHoach ON LichHen(maKeHoach) WHERE maKeHoach IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_KeHoachDieuTri_maKH' AND object_id = OBJECT_ID(N'KeHoachDieuTri'))
    CREATE INDEX IX_KeHoachDieuTri_maKH ON KeHoachDieuTri(maKH);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTKH_maKeHoach' AND object_id = OBJECT_ID(N'ChiTietKeHoachDieuTri'))
    CREATE INDEX IX_CTKH_maKeHoach ON ChiTietKeHoachDieuTri(maKeHoach);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTKH_maHoaDon' AND object_id = OBJECT_ID(N'ChiTietKeHoachDieuTri'))
    CREATE INDEX IX_CTKH_maHoaDon ON ChiTietKeHoachDieuTri(maHoaDon) WHERE maHoaDon IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_HoaDon_maKH' AND object_id = OBJECT_ID(N'HoaDon'))
    CREATE INDEX IX_HoaDon_maKH ON HoaDon(maKH);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_HoaDon_maKeHoach' AND object_id = OBJECT_ID(N'HoaDon'))
    CREATE INDEX IX_HoaDon_maKeHoach ON HoaDon(maKeHoach) WHERE maKeHoach IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTHD_maHoaDon' AND object_id = OBJECT_ID(N'ChiTietHoaDon'))
    CREATE INDEX IX_CTHD_maHoaDon ON ChiTietHoaDon(maHoaDon);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_PhieuThu_maKH' AND object_id = OBJECT_ID(N'PhieuThu'))
    CREATE INDEX IX_PhieuThu_maKH ON PhieuThu(maKH);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_PhieuThu_maHoaDon' AND object_id = OBJECT_ID(N'PhieuThu'))
    CREATE INDEX IX_PhieuThu_maHoaDon ON PhieuThu(maHoaDon) WHERE maHoaDon IS NOT NULL;
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTTT_maToaThuoc' AND object_id = OBJECT_ID(N'ChiTietToaThuoc'))
    CREATE INDEX IX_CTTT_maToaThuoc ON ChiTietToaThuoc(maToaThuoc);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTN_maPhieuNhap' AND object_id = OBJECT_ID(N'ChiTietNhapKho'))
    CREATE INDEX IX_CTN_maPhieuNhap ON ChiTietNhapKho(maPhieuNhap);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_CTX_maPhieuXuat' AND object_id = OBJECT_ID(N'ChiTietXuatKho'))
    CREATE INDEX IX_CTX_maPhieuXuat ON ChiTietXuatKho(maPhieuXuat);
GO
