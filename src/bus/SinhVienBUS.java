package bus;

import dao.SinhVienDAO;
import dto.SinhVien;
import helper.DataValidator;
import java.util.List;

public class SinhVienBUS {

    private SinhVienDAO sinhVienDAO = new SinhVienDAO();

//  lấy toàn bộ danh sách sinh viên
    public List<SinhVien> getAllSinhVien() {
        return sinhVienDAO.getAll();
    }

//  lấy danh sách sinh viên theo lớp
    public List<SinhVien> getSinhVienByLop(String maLop) {
        return sinhVienDAO.getByLop(maLop); // Giả sử bạn đã viết hàm này trong DAO
    }

//  tìm kiếm sinh viên
    public List<SinhVien> searchSinhVien(String keyword) {
        return sinhVienDAO.search(keyword); // Giả sử bạn đã viết hàm này trong DAO
    }

//  thêm sinh viên
    public String addSinhVien(SinhVien sv) {
        // 1. Kiểm tra các trường bắt buộc
        if (sv.getMaSV().isEmpty()) {
            return "Mã sinh viên không được để trống!";
        }
        if (sv.getHoSV().isEmpty() || sv.getTenSV().isEmpty()) {
            return "Họ và Tên sinh viên không được để trống!";
        }
        if (sv.getMaLop().isEmpty()) {
            return "Vui lòng chọn Lớp quản lý!";
        }

        // 2. Kiểm tra định dạng Email
        if (sv.getEmail() != null && !sv.getEmail().isEmpty()) {
            if (!DataValidator.isEmailValid(sv.getEmail())) {
                return "Email không đúng định dạng!";
            }
        }

        // 3. Kiểm tra số điện thoại
        if (sv.getDienThoai() != null && !sv.getDienThoai().isEmpty()) {
            if (!DataValidator.isPhoneValid(sv.getDienThoai())) {
                return "Số điện thoại phải là 10 chữ số!";
            }
        }

        // 4. Kiểm tra trùng mã
        if (sinhVienDAO.existById(sv.getMaSV())) {
            return "Mã sinh viên " + sv.getMaSV() + " đã tồn tại trong hệ thống!";
        }

        // 5. Gọi DAO để thêm vào DB
        if (sinhVienDAO.insert(sv)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại! Vui lòng kiểm tra lại kết nối.";
        }
    }

//  cập nhật thông tin sinh viên
    public String updateSinhVien(SinhVien sv) {
        // Kiểm tra dữ liệu hợp lệ trước khi sửa
        if (sv.getHoSV().isEmpty() || sv.getTenSV().isEmpty()) {
            return "Họ và Tên không được để trống!";
        }

        // Validate Email
        if (sv.getEmail() != null && !sv.getEmail().isEmpty()) {
            if (!DataValidator.isEmailValid(sv.getEmail())) {
                return "Email không đúng định dạng!";
            }
        }

        // Validate Phone
        if (sv.getDienThoai() != null && !sv.getDienThoai().isEmpty()) {
            if (!DataValidator.isPhoneValid(sv.getDienThoai())) {
                return "Số điện thoại phải là 10 chữ số!";
            }
        }

        if (sinhVienDAO.update(sv)) {
            return "Cập nhật thành công!";
        } else {
            return "Cập nhật thất bại!";
        }
    }

//  xóa sinh viên
    public String deleteSinhVien(String maSV) {

        if (sinhVienDAO.delete(maSV)) {
            return "Xóa thành công!";
        } else {
            return "Xóa thất bại! (Có thể sinh viên đang có điểm hoặc dữ liệu liên quan)";
        }
    }
}