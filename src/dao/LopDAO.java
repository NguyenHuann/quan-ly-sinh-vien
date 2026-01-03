package dao;

import dto.Lop;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopDAO {

    /**
     * 1. Lấy toàn bộ danh sách lớp
     */
    public List<Lop> getAll() {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM tblLop ORDER BY TenLop";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getString("MaLop"));
                lop.setMaKhoa(rs.getString("MaKhoa"));
                lop.setTenLop(rs.getString("TenLop"));
                lop.setCVHT(rs.getString("CVHT")); // Cột mới: Cố vấn học tập

                list.add(lop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 2. Lấy danh sách lớp theo Khoa
     * (Phục vụ chức năng lọc: Chọn Khoa -> Hiện các lớp của khoa đó)
     */
    public List<Lop> getByKhoa(String maKhoa) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM tblLop WHERE MaKhoa = ? ORDER BY TenLop";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maKhoa);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Lop lop = new Lop();
                    lop.setMaLop(rs.getString("MaLop"));
                    lop.setMaKhoa(rs.getString("MaKhoa"));
                    lop.setTenLop(rs.getString("TenLop"));
                    lop.setCVHT(rs.getString("CVHT"));
                    list.add(lop);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 3. Thêm lớp mới
     */
    public boolean insert(Lop lop) {
        String sql = "INSERT INTO tblLop (MaLop, MaKhoa, TenLop, CVHT) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lop.getMaLop());
            ps.setString(2, lop.getMaKhoa());
            ps.setString(3, lop.getTenLop());
            ps.setString(4, lop.getCVHT());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 4. Cập nhật thông tin lớp
     */
    public boolean update(Lop lop) {
        // Cập nhật tên lớp, CVHT và cả Mã Khoa (phòng trường hợp lớp chuyển khoa)
        String sql = "UPDATE tblLop SET MaKhoa=?, TenLop=?, CVHT=? WHERE MaLop=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lop.getMaKhoa());
            ps.setString(2, lop.getTenLop());
            ps.setString(3, lop.getCVHT());
            // Điều kiện WHERE
            ps.setString(4, lop.getMaLop());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 5. Xóa lớp
     * Lưu ý: Nếu lớp đã có sinh viên, SQL sẽ chặn xóa (FK_SV_Lop)
     */
    public boolean delete(String maLop) {
        String sql = "DELETE FROM tblLop WHERE MaLop=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maLop);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("Không thể xóa lớp này (đang có sinh viên hoặc dữ liệu liên quan).");
        }
        return false;
    }

    /**
     * 6. Kiểm tra trùng mã lớp
     */
    public boolean checkExist(String maLop) {
        String sql = "SELECT 1 FROM tblLop WHERE MaLop=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maLop);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}