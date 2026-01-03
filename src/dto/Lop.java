package dto;

public class Lop {
    private String maLop;   // SQL: MaLop (PK)
    private String maKhoa;  // SQL: MaKhoa (FK)
    private String tenLop;  // SQL: TenLop
    private String cvht;    // SQL: CVHT (Cố vấn học tập)

    // Constructor
    public Lop() {
    }

    public Lop(String maLop, String maKhoa, String tenLop, String cvht) {
        this.maLop = maLop;
        this.maKhoa = maKhoa;
        this.tenLop = tenLop;
        this.cvht = cvht;
    }

    // Getters và Setters
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getCVHT() {
        return cvht;
    }

    public void setCVHT(String cvht) {
        this.cvht = cvht;
    }

    // đưa đối tượng Lop vào ComboBox
    @Override
    public String toString() {
        return tenLop;
    }
}
