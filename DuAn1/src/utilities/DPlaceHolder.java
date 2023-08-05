package utilities;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DPlaceHolder {

    public static void addPlaceHolder(JTextField txt, String title) {
        txt.setMargin(new Insets(0, 10, 0, 0));

        txt.setText(title);
        txt.revalidate();
        txt.repaint();
        txt.setForeground(Color.gray);

        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals(title)) {
                    txt.setText("");
                } else {
                    txt.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().trim().equals("")) {
                    txt.setText(title);
                }

            }
        });
        // TODO Auto-generated constructor stub
    }
}
