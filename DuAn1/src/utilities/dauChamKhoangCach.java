/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class dauChamKhoangCach {

    public static void displayNumberWithCommas(int number, JTextField textField) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        String formattedNumber = numberFormat.format(number);
        textField.setText(formattedNumber);
    }

    public static void displayBigDecimalWithCommas(BigDecimal number, JTextField textField) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        String formattedNumber = numberFormat.format(number);
        textField.setText(formattedNumber);
    }

}
