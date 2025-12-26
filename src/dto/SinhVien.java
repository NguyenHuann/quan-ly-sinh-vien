package dto;

import java.util.Date;

public class SinhVien {
    private String maSV;
    private String maLop;
    private String hoTen;
    private Date ngaySinh;
    private String soDienThoai;
    private boolean gioiTinh; // true = nam, false = nu
    private String noiSinh;
    private String diaChi;
    private String ghiChu;
//  contructor
    public SinhVien() {}
    public SinhVien(String maSV, String maLop, String hoTen, Date ngaySinh, String soDienThoai, boolean gioiTinh, String noiSinh, String diaChi, String ghiChu) {
        this.maSV = maSV;
        this.maLop = maLop;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.noiSinh = noiSinh;
        this.diaChi = diaChi;
        this.ghiChu = ghiChu;
    }
//  getter & setter
    public String getMaSV() {
        return maSV;
    }
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public String getMaLop() {
        return maLop;
    }
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public Date getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setSoDienTHoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public boolean isGioiTinh() {
        return gioiTinh;
    }
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public String getNoiSinh() {
        return noiSinh;
    }
    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
