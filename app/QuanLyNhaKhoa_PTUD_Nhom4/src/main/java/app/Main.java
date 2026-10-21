package app;

import javax.swing.SwingUtilities;
import view.LoginView;
import view.ViewTheme;

public class Main {

    public static void main(String[] args) {
            ViewTheme.caiDat();
            SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
