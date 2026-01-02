package dao;

import dto.Diem;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiemDAO {

    // Lấy bảng điểm
    public List<Diem> getBangDiemBySV(String maSV) {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT * FROM tblDiem WHERE MaSV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSV);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Diem d = new Diem();
                    d.setMaMon(rs.getString("MaMon"));
                    d.setMaSV(rs.getString("MaSV"));
                    d.setDiem(rs.getDouble("Diem"));
                    list.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lưu điểm (Upsert)
    public boolean saveDiem(Diem diem) {
        // Kiểm tra tồn tại
        boolean exist = checkExist(diem.getMaSV(), diem.getMaMon());
        String sql;

        if (exist) {
            sql = "UPDATE tblDiem SET Diem = ? WHERE MaSV = ? AND MaMon = ?";
        } else {
            sql = "INSERT INTO tblDiem (Diem, MaSV, MaMon) VALUES (?, ?, ?)";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, diem.getDiem());
            ps.setString(2, diem.getMaSV());
            ps.setString(3, diem.getMaMon());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm phụ: Kiểm tra
    private boolean checkExist(String maSV, String maMon) {
        String sql = "SELECT 1 FROM tblDiem WHERE MaSV = ? AND MaMon = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ps.setString(2, maMon);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}