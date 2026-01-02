package dto;
import java.util.Date;

public class SinhVien {
    private String maSV;       // SQL: MaSV
    private String maLop;      // SQL: MaLop
    private String hoSV;       // SQL: HoSV
    private String tenSV;      // SQL: TenSV
    private Boolean gioiTinh;  // SQL: GioiTinh (1=nam, 0=nu)
    private Date ngaySinh;     // SQL: NgaySinh
    private String noiSinh;    // SQL: NoiSinh
    private String diaChi;     // SQL: DiaChi
    private String dienThoai;  // SQL: DienThoai
    private String email;      // SQL: Email

    public SinhVien() {}

    // Constructor đầy đủ
    public SinhVien(String maSV, String maLop, String hoSV, String tenSV, boolean gioiTinh, Date ngaySinh, String noiSinh, String diaChi, String dienThoai, String email) {
        this.maSV = maSV;
        this.maLop = maLop;
        this.hoSV = hoSV;
        this.tenSV = tenSV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.email = email;
    }

    // Getter & Setter
    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }

    public String getHoSV() { return hoSV; }
    public void setHoSV(String hoSV) { this.hoSV = hoSV; }

    public String getTenSV() { return tenSV; }
    public void setTenSV(String tenSV) { this.tenSV = tenSV; }

    public Boolean isGioiTinh() { return gioiTinh; }
    public void setGioiTinh(boolean gioiTinh) { this.gioiTinh = gioiTinh; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getNoiSinh() { return noiSinh; }
    public void setNoiSinh(String noiSinh) { this.noiSinh = noiSinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return hoSV + " " + tenSV;
    }
}