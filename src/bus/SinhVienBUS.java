package bus;

import dao.SinhVienDAO;
import dto.SinhVien;

import java.util.List;

public class SinhVienBUS {
    SinhVienDAO sinhVienDAO = new SinhVienDAO();

//    lay toan bo danh sach sinh vien
    public List<SinhVien> getAllSinhVien() {
        return sinhVienDAO.getAll();
    }
//    lay sinh vien theo lop
    public List<SinhVien> getSinhVienByLop(String maLop) {
        return sinhVienDAO.getByLop(maLop);
    }
//    tim kiem sinh vien
    public List<SinhVien> searchSinhVien(String keyword) {
        return sinhVienDAO.search(keyword);
    }
    public String addSinhVien(SinhVien sv) {
        if (sv.getMaSV().isEmpty() || sv.getHo().isEmpty() || sv.getTen().isEmpty()) {
            return "Vui lòng nhập đầy đủ Mã, Họ, Tên";
        }
        if (sinhVienDAO.existById(sv.getMaSV())) {
            return "Mã sinh viên đã tồn tại";
        }
        if (sinhVienDAO.insert(sv)) {
            return "Thêm thành công!";
        }else {
            return "Lỗi khi thêm vào CSDL";
        }
    }
//    cap nhat sinh vien
    public String updateSinhVien(SinhVien sv) {
        if (sinhVienDAO.update(sv)) {
            return "Cập nhật thành công!";
        }else
            return "Cập nhật thất bại";
    }
//    xoa sinh vien
    public String deleteSinhVien(String maSV) {
        if (sinhVienDAO.delete(maSV)) {
            return "Xóa thành công!";
        }else
            return "Xóa thất bại!";
    }
}
