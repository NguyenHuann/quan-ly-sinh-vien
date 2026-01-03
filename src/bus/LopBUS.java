package bus;

import dao.LopDAO;
import dto.Lop;
import java.util.List;

public class LopBUS {

    private LopDAO lopDAO = new LopDAO();

    /**
     * Lấy danh sách tất cả các lớp
     */
    public List<Lop> getAllLop() {
        return lopDAO.getAll();
    }

    /**
     * Lấy danh sách lớp theo Khoa
     * (Dùng khi chọn Khoa trên giao diện -> Load lại combobox Lớp)
     */
    public List<Lop> getLopByKhoa(String maKhoa) {
        return lopDAO.getByKhoa(maKhoa);
    }

    /**
     * Thêm lớp mới
     */
    public String addLop(Lop lop) {
        // 1. Validate dữ liệu
        if (lop.getMaLop().isEmpty() || lop.getTenLop().isEmpty()) {
            return "Mã lớp và Tên lớp không được để trống!";
        }

        if (lop.getMaKhoa().isEmpty()) {
            return "Vui lòng chọn Khoa trực thuộc!";
        }

        if (lop.getCVHT().isEmpty()) {
            return "Vui lòng nhập tên Cố vấn học tập (CVHT)!";
        }

        // 2. Kiểm tra trùng mã
        if (lopDAO.checkExist(lop.getMaLop())) {
            return "Mã lớp " + lop.getMaLop() + " đã tồn tại!";
        }

        // 3. Gọi DAO
        if (lopDAO.insert(lop)) {
            return "Thêm lớp thành công!";
        }
        return "Thêm thất bại!";
    }

    /**
     * Cập nhật thông tin lớp
     */
    public String updateLop(Lop lop) {
        if (lop.getTenLop().isEmpty()) {
            return "Tên lớp không được để trống!";
        }
        if (lop.getCVHT().isEmpty()) {
            return "Vui lòng nhập tên Cố vấn học tập (CVHT)!";
        }

        if (lopDAO.update(lop)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    /**
     * Xóa lớp
     */
    public String deleteLop(String maLop) {
        // Lưu ý: Nếu lớp đã có sinh viên, SQL sẽ chặn xóa (Ràng buộc khóa ngoại FK_SV_Lop)
        if (lopDAO.delete(maLop)) {
            return "Xóa thành công!";
        }
        return "Xóa thất bại! (Lớp đang có sinh viên theo học hoặc lỗi hệ thống)";
    }
}
