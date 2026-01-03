package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("QUẢN LÝ SINH VIÊN - Java Swing");
        setSize(1000, 700);
        setLocationRelativeTo(null); // Ra giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo Tab Panel
        tabbedPane = new JTabbedPane();

        // Tab 1: Sinh viên
        tabbedPane.addTab("Quản lý Sinh viên", new ImageIcon(), new SinhVienPanel());

        // Tab 2: Điểm
        // tabbedPane.addTab("Quản lý Điểm", new ImageIcon(), new JPanel());

        // Tab 3: Lớp (Sẽ thêm sau)
        // tabbedPane.addTab("Quản lý Lớp", new ImageIcon(), new JPanel());

        this.add(tabbedPane);
    }

    // Hàm main để chạy thử giao diện ngay tại đây
    public static void main(String[] args) {
        try {
            // Set giao diện giống hệ điều hành (Windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
