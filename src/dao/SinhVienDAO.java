package dao;

import dto.SinhVien;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
//    lấy toàn bộ danh sách sinh viên
    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM tblSinhVien ORDER BY TenSV, HoSV";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToSinhVien(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    lấy danh sách sinh viên theo lớp
    public List<SinhVien> getByLop(String maLop) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM tblSinhVien WHERE MaLop = ? ORDER BY TenSV, HoSV";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maLop);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToSinhVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    thêm sinh viên mới
    public boolean insert(SinhVien sv) {
        String sql = "INSERT INTO tblSinhVien "
                + "(MaSV, MaLop, HoSV, TenSV, GioiTinh, NgaySinh, NoiSinh, DiaChi, DienThoai, Email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getMaLop());
            ps.setString(3, sv.getHoSV());
            ps.setString(4, sv.getTenSV());
            ps.setBoolean(5, sv.isGioiTinh()); // BIT trong SQL
            ps.setDate(6, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setString(7, sv.getNoiSinh());
            ps.setString(8, sv.getDiaChi());
            ps.setString(9, sv.getDienThoai());
            ps.setString(10, sv.getEmail());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    cập nhật thông tin sinh viên
    public boolean update(SinhVien sv) {
        String sql = "UPDATE tblSinhVien SET MaLop=?, HoSV=?, TenSV=?, GioiTinh=?, NgaySinh=?, "
                + "NoiSinh=?, DiaChi=?, DienThoai=?, Email=? WHERE MaSV=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sv.getMaLop());
            ps.setString(2, sv.getHoSV());
            ps.setString(3, sv.getTenSV());
            ps.setBoolean(4, sv.isGioiTinh());
            ps.setDate(5, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setString(6, sv.getNoiSinh());
            ps.setString(7, sv.getDiaChi());
            ps.setString(8, sv.getDienThoai());
            ps.setString(9, sv.getEmail());
            // Điều kiện WHERE
            ps.setString(10, sv.getMaSV());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    xóa sinh viên
    public boolean delete(String maSV) {
        String sql = "DELETE FROM tblSinhVien WHERE MaSV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maSV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("Không thể xóa sinh viên này (có thể do đang có bảng điểm).");
        }
        return false;
    }
//    tìm kiếm sinh viên theo mã hoặc theo tên
    public List<SinhVien> search(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        // Tìm gần đúng trong MaSV, HoSV hoặc TenSV
        String sql = "SELECT * FROM tblSinhVien WHERE MaSV LIKE ? OR HoSV LIKE ? OR TenSV LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setString(3, query);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToSinhVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    kiểm tra trùng mã sinh viên
    public boolean existById(String maSV) {
        String sql = "SELECT 1 FROM tblSinhVien WHERE MaSV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSV);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//  lấy thông tin sinh viên theo id
    public SinhVien getById(String maSV) {
        String sql = "SELECT * FROM tblSinhVien WHERE MaSV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSinhVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper: Ánh xạ từ ResultSet sang Object SinhVien
     */
    private SinhVien mapResultSetToSinhVien(ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMaSV(rs.getString("MaSV"));
        sv.setMaLop(rs.getString("MaLop"));
        sv.setHoSV(rs.getString("HoSV"));
        sv.setTenSV(rs.getString("TenSV"));
        sv.setGioiTinh(rs.getBoolean("GioiTinh")); // boolean
        sv.setNgaySinh(rs.getDate("NgaySinh"));
        sv.setNoiSinh(rs.getString("NoiSinh"));
        sv.setDiaChi(rs.getString("DiaChi"));
        sv.setDienThoai(rs.getString("DienThoai"));
        sv.setEmail(rs.getString("Email"));
        return sv;
    }
}