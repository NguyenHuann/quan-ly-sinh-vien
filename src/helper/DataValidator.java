package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    // Hàm kiểm tra định dạng Email chuẩn
    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    // Hàm kiểm tra số điện thoại
    public static boolean isPhoneValid(String phone) {
        if (phone == null || phone.isEmpty()) return false;
        // Regex: Chỉ chứa số, độ dài 10
        return phone.matches("\\d{10}");
    }
}
