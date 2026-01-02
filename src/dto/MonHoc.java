package dto;

public class MonHoc {
    private String maMon;    // SQL: MaMon
    private String tenMon;   // SQL: TenMon
    private String kyHoc;    // SQL: KyHoc
    private int soTinChi;    // SQL: SoTinChi

    public MonHoc() {}

    // Getter & Setter
    public String getMaMon() { return maMon; }
    public void setMaMon(String maMon) { this.maMon = maMon; }

    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }

    public String getKyHoc() { return kyHoc; }
    public void setKyHoc(String kyHoc) { this.kyHoc = kyHoc; }

    public int getSoTinChi() { return soTinChi; }
    public void setSoTinChi(int soTinChi) { this.soTinChi = soTinChi; }

    @Override
    public String toString() { return tenMon; }
}