package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class NutCongTac extends JToggleButton {

    public NutCongTac(boolean bat) {
        setUI(new BasicButtonUI());
        setSelected(bat);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);
        setPreferredSize(new Dimension(62, 28));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        capNhat();
        addActionListener(e -> capNhat());
    }

    public void dat(boolean bat) {
        setSelected(bat);
        capNhat();
    }

    private void capNhat() {
        if (isSelected()) {
            setText("Bật");
            setBackground(new Color(255, 159, 67));
            setForeground(Color.WHITE);
        } else {
            setText("Tắt");
            setBackground(new Color(206, 212, 218));
            setForeground(Color.WHITE);
        }
    }
}
