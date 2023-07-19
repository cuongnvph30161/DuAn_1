package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Uhelper {

    public static boolean checkNull(String text) {
        return text.trim().equals("");
    }

    public static boolean checkErrDouble(String text) {
        try {
            return Double.parseDouble(text) < 0;

        } catch (Exception e) {
            return true;
        }
    }

    public static boolean checkErrInt(String text) {
        try {
            return Integer.parseInt(text) < 0;

        } catch (Exception e) {
            return true;
        }
    }

    public static boolean checkErrDate(String text) {
        SimpleDateFormat spd = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return spd.parse(text).before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean checkErrNumberPhone(String text) {
        return Pattern.matches("(84|0)[0-9]{8}", text);
    }

    public static void main(String[] args) {

    }

    public static boolean checkNullText(JTextField c, String mss) {
        if (c.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, mss);
            c.requestFocus();
            return true;
        } else {
            return false;
        }
    }

}
