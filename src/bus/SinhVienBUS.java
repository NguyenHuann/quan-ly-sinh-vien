package bus;

import dao.SinhVienDAO;
import dto.SinhVien;
import helper.DataValidator; // Import class kiểm tra định dạng
import java.util.List;

public class SinhVienBUS {

    private SinhVienDAO sinhVienDAO = new SinhVienDAO();

    /**
     * Lấy toàn bộ danh sách sinh viên
     */
    public List<SinhVien> getAllSinhVien() {
        return sinhVienDAO.getAll();
    }

    /**
     * Lấy danh sách sinh viên theo Lớp (Để lọc trên giao diện)
     * Lưu ý: Cần đảm bảo SinhVienDAO đã có hàm getByLop
     */
    public List<SinhVien> getSinhVienByLop(String maLop) {
        return sinhVienDAO.getByLop(maLop); // Giả sử bạn đã viết hàm này trong DAO
    }

    /**
     * Tìm kiếm sinh viên
     */
    public List<SinhVien> searchSinhVien(String keyword) {
        return sinhVienDAO.search(keyword); // Giả sử bạn đã viết hàm này trong DAO
    }

    /**
     * Thêm mới sinh viên
     */
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

        // 2. Kiểm tra định dạng Email (Nếu có nhập)
        if (sv.getEmail() != null && !sv.getEmail().isEmpty()) {
            if (!DataValidator.isEmailValid(sv.getEmail())) {
                return "Email không đúng định dạng!";
            }
        }

        // 3. Kiểm tra số điện thoại (Nếu có nhập)
        if (sv.getDienThoai() != null && !sv.getDienThoai().isEmpty()) {
            if (!DataValidator.isPhoneValid(sv.getDienThoai())) {
                return "Số điện thoại phải là 10 chữ số!";
            }
        }

        // 4. Kiểm tra trùng mã (Gọi xuống DAO)
        if (sinhVienDAO.existById(sv.getMaSV())) {
            return "Mã sinh viên " + sv.getMaSV() + " đã tồn tại trong hệ thống!";
        }

        // 5. Gọi DAO để thêm vào DB
        if (sinhVienDAO.insert(sv)) {
            return "Thêm sinh viên thành công!";
        } else {
            return "Thêm thất bại! Vui lòng kiểm tra lại kết nối.";
        }
    }

    /**
     * Cập nhật thông tin sinh viên
     */
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

    /**
     * Xóa sinh viên
     */
    public String deleteSinhVien(String maSV) {
        // Có thể thêm logic kiểm tra: Nếu sinh viên đã có điểm thi thì không cho xóa
        // Tuy nhiên, logic đó thường nằm ở Database (Foreign Key Constraint)
        // nên ở đây ta chỉ cần bắt kết quả trả về từ DAO.

        if (sinhVienDAO.delete(maSV)) {
            return "Xóa thành công!";
        } else {
            return "Xóa thất bại! (Có thể sinh viên đang có điểm hoặc dữ liệu liên quan)";
        }
    }
}