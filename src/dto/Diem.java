package dto;

public class Diem {
    private String maMon;  // SQL: MaMon
    private String maSV;   // SQL: MaSV
    private double diem;   // SQL: Diem (FLOAT)

    // Bỏ trường GhiChu vì trong SQL không có

    public Diem() {}

    public Diem(String maMon, String maSV, double diem) {
        this.maMon = maMon;
        this.maSV = maSV;
        this.diem = diem;
    }

    public String getMaMon() { return maMon; }
    public void setMaMon(String maMon) { this.maMon = maMon; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public double getDiem() { return diem; }
    public void setDiem(double diem) { this.diem = diem; }
}