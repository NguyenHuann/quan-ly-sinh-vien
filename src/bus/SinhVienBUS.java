package bus;

import dao.SinhVienDAO;
import dto.SinhVien;

public class SinhVienBUS {
    SinhVienDAO dao = new SinhVienDAO();
    public String themSinhVien(SinhVien sv) {
        if (dao.existById(sv.getMaSV())) {
            return "Mã sinh viên đã tồn tại";
        }
        if (sv.getTen().isEmpty()) {
            return "Tên không được để trống!";
        }
        if (dao.insert(sv)) {
            return "Thêm thành công!";
        }else {
            return "Lỗi khi thêm vào CSDL";
        }
    }
}
