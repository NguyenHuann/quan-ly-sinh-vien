package dao;

import dto.Diem;
import dto.SinhVien;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiemDAO {
//    lay bang diem cua mot sinh vien
    public List<Diem> getBangDiemBySV(String maSV) {
        List<Diem> diemList = new ArrayList<>();
        String sql = "select * from tblDiem where MaSinhVien=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setString(1, maSV);
             try (ResultSet rs = ps.executeQuery()){
                 while (rs.next()) {
                     Diem diem = new Diem();
                     diem.setMaSV(rs.getString("MaSinhVien"));
                     diem.setMaMon(rs.getString("MaMonhoc"));
                     diem.setDiemSo(rs.getDouble("Diem"));
                     diem.setGhiChu(rs.getString("GhiChu"));

                     diemList.add(diem);
                 }
             }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return diemList;
    }
    // ham ho tro: kiem tra sinh vien da co diem mon nay hay chua
    private boolean checkExist(String maSV, String maMon) {
        String sql = "SELECT 1 FROM tblDiem WHERE MaSinhVien = ? AND MaMonhoc = ?";
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

    // luu diem
    public boolean saveDiem(Diem diem) {
        boolean isExist = checkExist(diem.getMaSV(), diem.getMaMon());

        String sql;

        if (isExist) {
            sql = "UPDATE tblDiem SET Diem = ?, Ghichu = ? WHERE MaSinhVien = ? AND MaMonhoc = ?";
        } else {
            sql = "INSERT INTO tblDiem (Diem, Ghichu, MaSinhVien, MaMonhoc) VALUES (?, ?, ?, ?)";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, diem.getDiemSo());
            ps.setString(2, diem.getGhiChu());
            ps.setString(3, diem.getMaSV());
            ps.setString(4, diem.getMaMon());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
