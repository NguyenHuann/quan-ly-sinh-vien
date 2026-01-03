package helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageDialogHelper {

    // Thông báo tin nhắn thường
    public static void showMessageDialog(Component parent, String content, String title) {
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Thông báo lỗi
    public static void showErrorDialog(Component parent, String content, String title) {
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.ERROR_MESSAGE);
    }

    // Hộp thoại xác nhận (Yes/No)
    public static int showConfirmDialog(Component parent, String content, String title) {
        int choose = JOptionPane.showConfirmDialog(parent, content, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return choose;
    }
}
