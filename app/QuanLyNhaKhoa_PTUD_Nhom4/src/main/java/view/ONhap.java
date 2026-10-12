package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class ONhap extends JTextField {

    private String goiY;

    public ONhap(String goiY, int cot) {
        super(cot);
        this.goiY = goiY == null ? "" : goiY;
        ViewTheme.vienNhap(this);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    public void datGoiY(String goiY) {
        this.goiY = goiY == null ? "" : goiY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!getText().isEmpty() || isFocusOwner() || goiY.isEmpty()) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(getFont());
        g2.setColor(new Color(160, 167, 173));
        FontMetrics fm = g2.getFontMetrics();
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(goiY, 8, y);
        g2.dispose();
    }
}
