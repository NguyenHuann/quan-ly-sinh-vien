package ui;

import bus.LopBUS;
import bus.SinhVienBUS;
import dto.Lop;
import dto.SinhVien;
import helper.DataValidator;
import helper.MessageDialogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SinhVienPanel extends JPanel {

    private JTextField txtMaSV, txtHoSV, txtTenSV, txtNgaySinh, txtNoiSinh, txtDiaChi, txtDienThoai, txtEmail;
    private JRadioButton rdoNam, rdoNu;
    private ButtonGroup grpGioiTinh;
    private JComboBox<Lop> cboLop; // ComboBox chứa Object Lop
    private JTable tblSinhVien;
    private DefaultTableModel tblModel;

    private SinhVienBUS svBUS = new SinhVienBUS();
    private LopBUS lopBUS = new LopBUS();

    public SinhVienPanel() {
        initComponents();
        loadDataToComboBox(); // 1. Load lớp
        loadDataToTable();    // 2. Load sinh viên
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // --- PHẦN 1: FORM NHẬP LIỆU (NORTH) ---
        JPanel pnlInput = new JPanel(new GridLayout(6, 4, 10, 10)); // Grid 6 hàng 4 cột
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        pnlInput.setPreferredSize(new Dimension(800, 200));

        // Khởi tạo các components
        txtMaSV = new JTextField();
        txtHoSV = new JTextField();
        txtTenSV = new JTextField();
        txtNgaySinh = new JTextField(); // Nhập dạng dd/MM/yyyy
        txtNoiSinh = new JTextField();
        txtDiaChi = new JTextField();
        txtDienThoai = new JTextField();
        txtEmail = new JTextField();

        // Radio Button Giới tính
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        grpGioiTinh = new ButtonGroup();
        grpGioiTinh.add(rdoNam);
        grpGioiTinh.add(rdoNu);
        rdoNam.setSelected(true); // Mặc định chọn Nam
        JPanel pnlGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlGioiTinh.add(rdoNam);
        pnlGioiTinh.add(rdoNu);

        // ComboBox Lớp
        cboLop = new JComboBox<>();

        // Thêm vào Panel Input (Thứ tự quan trọng trong GridLayout)
        pnlInput.add(new JLabel("Mã SV:")); pnlInput.add(txtMaSV);
        pnlInput.add(new JLabel("Lớp:"));   pnlInput.add(cboLop);

        pnlInput.add(new JLabel("Họ đệm:")); pnlInput.add(txtHoSV);
        pnlInput.add(new JLabel("Tên:"));    pnlInput.add(txtTenSV);

        pnlInput.add(new JLabel("Ngày sinh (dd/MM/yyyy):")); pnlInput.add(txtNgaySinh);
        pnlInput.add(new JLabel("Giới tính:")); pnlInput.add(pnlGioiTinh);

        pnlInput.add(new JLabel("Nơi sinh:")); pnlInput.add(txtNoiSinh);
        pnlInput.add(new JLabel("Địa chỉ:"));  pnlInput.add(txtDiaChi);

        pnlInput.add(new JLabel("Điện thoại:")); pnlInput.add(txtDienThoai);
        pnlInput.add(new JLabel("Email:"));      pnlInput.add(txtEmail);

        // --- PHẦN 2: CÁC NÚT CHỨC NĂNG (CENTER - TOP) ---
        JPanel pnlButtons = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Thêm mới");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnNew = new JButton("Làm mới");

        pnlButtons.add(btnAdd);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnNew);

        // Gom Input và Button vào một Panel phía trên
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        // --- PHẦN 3: BẢNG DỮ LIỆU (CENTER) ---
        String[] columns = {"Mã SV", "Họ đệm", "Tên", "Giới tính", "Ngày sinh", "Lớp", "Email"};
        tblModel = new DefaultTableModel(columns, 0);
        tblSinhVien = new JTable(tblModel);
        add(new JScrollPane(tblSinhVien), BorderLayout.CENTER);

        // --- XỬ LÝ SỰ KIỆN (CONTROLLER) ---

        // Sự kiện Click bảng -> Đổ dữ liệu lên Form
        tblSinhVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSinhVien.getSelectedRow();
                if (row >= 0) {
                    txtMaSV.setText(tblSinhVien.getValueAt(row, 0).toString());
                    txtHoSV.setText(tblSinhVien.getValueAt(row, 1).toString());
                    txtTenSV.setText(tblSinhVien.getValueAt(row, 2).toString());

                    String gt = tblSinhVien.getValueAt(row, 3).toString();
                    if(gt.equals("Nam")) rdoNam.setSelected(true);
                    else rdoNu.setSelected(true);

                    txtNgaySinh.setText(tblSinhVien.getValueAt(row, 4).toString());

                    // Chọn lại lớp trong ComboBox
                    String tenLop = tblSinhVien.getValueAt(row, 5).toString();
                    for (int i = 0; i < cboLop.getItemCount(); i++) {
                        if (cboLop.getItemAt(i).toString().equals(tenLop)) {
                            cboLop.setSelectedIndex(i);
                            break;
                        }
                    }

                    txtEmail.setText(tblSinhVien.getValueAt(row, 6).toString());
                    // Các trường khác (Địa chỉ, SĐT...) bạn tự lấy thêm từ DB nếu cần chi tiết
                    // hoặc hiển thị hết ra bảng.
                }
            }
        });

        // Sự kiện Thêm
        btnAdd.addActionListener((ActionEvent e) -> {
            insertSinhVien();
        });

        // Sự kiện Xóa
        btnDelete.addActionListener((ActionEvent e) -> {
            deleteSinhVien();
        });

        // Sự kiện Làm mới
        btnNew.addActionListener((ActionEvent e) -> {
            clearForm();
        });

        // Sự kiện Cập nhật (Bạn tự viết tương tự Thêm)
        btnUpdate.addActionListener((ActionEvent e) -> {
            updateSinhVien();
        });
    }

    // --- CÁC HÀM XỬ LÝ LOGIC ---

    private void loadDataToComboBox() {
        cboLop.removeAllItems();
        List<Lop> listLop = lopBUS.getAllLop();
        for (Lop lop : listLop) {
            cboLop.addItem(lop); // add Object, nó sẽ tự gọi toString() để hiển thị tên
        }
    }

    private void loadDataToTable() {
        tblModel.setRowCount(0); // Xóa trắng bảng
        List<SinhVien> listSV = svBUS.getAllSinhVien();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (SinhVien sv : listSV) {
            // Cần lấy Tên Lớp thay vì Mã Lớp để hiển thị cho đẹp
            // (Thực tế nên Join bảng trong SQL, ở đây tạm hiển thị Mã Lớp hoặc xử lý sau)
            String tenLop = sv.getMaLop();

            tblModel.addRow(new Object[]{
                    sv.getMaSV(), sv.getHoSV(), sv.getTenSV(),
                    sv.isGioiTinh() ? "Nam" : "Nữ",
                    sdf.format(sv.getNgaySinh()),
                    tenLop,
                    sv.getEmail()
            });
        }
    }

    private void insertSinhVien() {
        try {
            // 1. Thu thập dữ liệu
            SinhVien sv = new SinhVien();
            sv.setMaSV(txtMaSV.getText());
            sv.setHoSV(txtHoSV.getText());
            sv.setTenSV(txtTenSV.getText());
            sv.setGioiTinh(rdoNam.isSelected()); // True=Nam

            // Xử lý ngày sinh
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sv.setNgaySinh(sdf.parse(txtNgaySinh.getText()));

            // Lấy Mã lớp từ ComboBox (Lấy Object đang chọn -> ép kiểu về Lop -> lấy Mã)
            Lop selectedLop = (Lop) cboLop.getSelectedItem();
            if (selectedLop != null) {
                sv.setMaLop(selectedLop.getMaLop());
            }

            sv.setNoiSinh(txtNoiSinh.getText());
            sv.setDiaChi(txtDiaChi.getText());
            sv.setDienThoai(txtDienThoai.getText());
            sv.setEmail(txtEmail.getText());

            // 2. Gọi BUS
            String result = svBUS.addSinhVien(sv);

            // 3. Thông báo
            if (result.equals("Thêm thành công!")) {
                MessageDialogHelper.showMessageDialog(this, result, "Thông báo");
                loadDataToTable(); // Nạp lại bảng
                clearForm();
            } else {
                MessageDialogHelper.showErrorDialog(this, result, "Lỗi");
            }
        } catch (Exception ex) {
            MessageDialogHelper.showErrorDialog(this, "Lỗi nhập liệu: " + ex.getMessage(), "Lỗi");
            ex.printStackTrace();
        }
    }

    private void updateSinhVien() {
        // Tương tự insert, chỉ khác là gọi svBUS.updateSinhVien(sv)
        // Lưu ý: Không cho sửa Mã SV
        try {
            SinhVien sv = new SinhVien();
            sv.setMaSV(txtMaSV.getText()); // Mã để tìm và update
            sv.setHoSV(txtHoSV.getText());
            sv.setTenSV(txtTenSV.getText());
            sv.setGioiTinh(rdoNam.isSelected());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sv.setNgaySinh(sdf.parse(txtNgaySinh.getText()));

            Lop selectedLop = (Lop) cboLop.getSelectedItem();
            if (selectedLop != null) sv.setMaLop(selectedLop.getMaLop());

            sv.setNoiSinh(txtNoiSinh.getText());
            sv.setDiaChi(txtDiaChi.getText());
            sv.setDienThoai(txtDienThoai.getText());
            sv.setEmail(txtEmail.getText());

            String result = svBUS.updateSinhVien(sv);

            if (result.equals("Cập nhật thành công!")) {
                MessageDialogHelper.showMessageDialog(this, result, "Thông báo");
                loadDataToTable();
            } else {
                MessageDialogHelper.showErrorDialog(this, result, "Lỗi");
            }
        } catch (Exception ex) {
            MessageDialogHelper.showErrorDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi");
        }
    }

    private void deleteSinhVien() {
        if (txtMaSV.getText().isEmpty()) {
            MessageDialogHelper.showErrorDialog(this, "Vui lòng chọn sinh viên cần xóa", "Lỗi");
            return;
        }

        if (MessageDialogHelper.showConfirmDialog(this, "Bạn có chắc muốn xóa SV này?", "Hỏi") == JOptionPane.YES_OPTION) {
            String result = svBUS.deleteSinhVien(txtMaSV.getText());
            if (result.equals("Xóa thành công!")) {
                MessageDialogHelper.showMessageDialog(this, result, "Thông báo");
                loadDataToTable();
                clearForm();
            } else {
                MessageDialogHelper.showErrorDialog(this, result, "Lỗi");
            }
        }
    }

    private void clearForm() {
        txtMaSV.setText("");
        txtHoSV.setText("");
        txtTenSV.setText("");
        txtNgaySinh.setText("");
        txtNoiSinh.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        rdoNam.setSelected(true);
        if (cboLop.getItemCount() > 0) cboLop.setSelectedIndex(0);
    }
}