package dto;

public class Diem {
    private String maMon; // khoa ngoai
    private String maSV; // khoa ngoai
    private double diemSo;
    private String ghiChu;
//    constructor
    public Diem() {}
    public Diem(String maMon, String maSV, double diemSo, String ghiChu) {
        this.maMon = maMon;
        this.maSV = maSV;
        this.diemSo = diemSo;
        this.ghiChu = ghiChu;
    }
//    getter & setter
    public String getMaMon() {
        return maMon;
    }
    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }
    public String getMaSV() {
        return maSV;
    }
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public double getDiemSo() {
        return diemSo;
    }
    public void setDiemSo(double diemSo) {
        this.diemSo = diemSo;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
