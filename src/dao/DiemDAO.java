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
                     diem.setMaMon(rs.getString("MaMonHoc"));
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
}
