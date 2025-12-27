package dao;

import dto.SinhVien;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SinhVienDAO {
//    them sinh vien
    public boolean insert(SinhVien sv) {
        String sql = "insert into SinhVien "
                + "(MaSinhVien, MaLop, Ho, Ten, GioiTinh, NgaySinh, NoiSinh, DiaChi, SoDienThoai, GhiChu) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try (Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getMaLop());
            ps.setString(3, sv.getHo());
            ps.setString(4,sv.getTen());
            ps.setBoolean(5,sv.isGioiTinh());
            ps.setDate(6, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setString(7, sv.getNoiSinh());
            ps.setString(8, sv.getDiaChi());
            ps.setString(9, sv.getSoDienThoai());
            ps.setString(10, sv.getGhiChu());

            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    kiem tra trung ma
    public boolean existById(String maSV) {
        String sql = "select 1 from SinhVien where MaSinhVien = ?";
        try (Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
