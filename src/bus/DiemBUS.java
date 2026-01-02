package bus;

import dao.DiemDAO;
import dto.Diem;
import dto.MonHoc;
import java.util.List;

public class DiemBUS {

    private DiemDAO diemDAO = new DiemDAO();

//  lấy bảng điểm của một sinh viên
    public List<Diem> getBangDiem(String maSV) {
        return diemDAO.getBangDiemBySV(maSV);
    }

//  lưu điểm
    public String saveDiem(Diem diem) {
        // Kiểm tra dữ liệu đầu vào
        if (diem.getMaSV().isEmpty() || diem.getMaMon().isEmpty()) {
            return "Thiếu thông tin Sinh viên hoặc Môn học!";
        }

        // Validate điểm số (0 <= điểm <= 10)
        if (diem.getDiem() < 0 || diem.getDiem() > 10) {
            return "Điểm số không hợp lệ! Phải từ 0 đến 10.";
        }

        // Gọi DAO lưu xuống DB
        if (diemDAO.saveDiem(diem)) {
            return "Lưu điểm thành công!";
        } else {
            return "Lưu điểm thất bại!";
        }
    }
//    tính điểm trung bình
    public double tinhDiemTrungBinh(List<Diem> listDiem, List<MonHoc> listMonHoc) {
        double tongDiemTichLuy = 0;
        int tongSoTinChi = 0;

        for (Diem d : listDiem) {
            // Tìm môn học tương ứng để lấy Số tín chỉ
            for (MonHoc mh : listMonHoc) {
                if (mh.getMaMon().equals(d.getMaMon())) {
                    int tinChi = mh.getSoTinChi();

                    tongDiemTichLuy += d.getDiem() * tinChi;
                    tongSoTinChi += tinChi;
                    break; // Tìm thấy rồi thì break vòng lặp con
                }
            }
        }

        if (tongSoTinChi == 0) return 0;

        // Làm tròn 2 chữ số thập phân
        double dtb = tongDiemTichLuy / tongSoTinChi;
        return Math.round(dtb * 100.0) / 100.0;
    }
//    xếp loại học tập
    public String xepLoai(double dtb, List<Diem> listDiem) {
        // Kiểm tra có môn nào dưới 5.0 không
        boolean coMonDuoi5 = false;
        for (Diem d : listDiem) {
            if (d.getDiem() < 5.0) {
                coMonDuoi5 = true;
                break;
            }
        }

        // Logic xếp loại:
        if (dtb >= 9.0 && !coMonDuoi5) return "Xuất sắc";

        if (dtb >= 8.0) {
            if (!coMonDuoi5) return "Giỏi";
            else return "Khá"; // Rớt hạng
        }

        if (dtb >= 7.0) return "Khá";

        if (dtb >= 6.0) return "Trung bình khá";

        if (dtb >= 5.0) return "Trung bình";

        if (dtb >= 3.0) return "Yếu";

        return "Kém";
    }
//    xét lên lớp với đk dtb>= 5.0 và nợ <= 15 tín
    public String xetLenLop(double dtb, List<Diem> listDiem, List<MonHoc> listMonHoc) {
        if (dtb < 5.0) {
            return "Ở lại lớp (ĐTB < 5.0)";
        }

        // Tính tổng số tín chỉ của các môn điểm dưới 5
        int tongTinChiNo = 0;
        for (Diem d : listDiem) {
            if (d.getDiem() < 5.0) {
                for (MonHoc mh : listMonHoc) {
                    if (mh.getMaMon().equals(d.getMaMon())) {
                        tongTinChiNo += mh.getSoTinChi();
                        break;
                    }
                }
            }
        }

        if (tongTinChiNo > 15) {
            return "Ở lại lớp (Nợ " + tongTinChiNo + " tín chỉ > 15)";
        }

        return "Được lên lớp";
    }
}