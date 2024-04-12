import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoSectionUI extends JFrame implements ActionListener {
    private JPanel section1, section2;
    private JButton goToSection2Button, showHidePasswordButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private boolean passwordVisible = false;

    public TwoSectionUI() {
        setTitle("Duas Seções");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        section1 = new JPanel();
        section2 = new JPanel();

        JLabel label1 = new JLabel("Seção 1");
        JLabel emailLabel = new JLabel("Digite seu Email: ");
        JLabel passwordLabel = new JLabel("Digite sua Senha: ");
        goToSection2Button = new JButton("Ir para a Seção 2");
        goToSection2Button.addActionListener(this);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);

        ImageIcon showIcon = new ImageIcon("eye_open.png");
        ImageIcon hideIcon = new ImageIcon("eye_close.png");
        showHidePasswordButton = new JButton(hideIcon);
        showHidePasswordButton.setFocusPainted(false);
        showHidePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordVisible = !passwordVisible;
                showHidePasswordButton.setIcon(passwordVisible ? showIcon : hideIcon);
                passwordField.setEchoChar(passwordVisible ? '\0' : '•');
            }
        });

        passwordField.setEchoChar('•');

        section1.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        section1.add(label1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        section1.add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        section1.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        section1.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        section1.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        section1.add(goToSection2Button, constraints);

        JLabel label2 = new JLabel("Seção 2");
        section2.add(label2);
        section2.setVisible(false);

        setLayout(new CardLayout());
        add(section1, "section1");
        add(section2, "section2");

        new NavigationController(this); // Inicia a classe NavigationController
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goToSection2Button) {
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "section2");
        }
    }

    public JPanel getSection2Panel() {
        return section2;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TwoSectionUI ui = new TwoSectionUI();
                ui.setVisible(true);
            }
        });
    }

    // Classe interna NavigationController
    class NavigationController {
        public NavigationController(TwoSectionUI ui) {
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Navegação");
            JMenuItem menuItem1 = new JMenuItem("Página 1");
            JMenuItem menuItem2 = new JMenuItem("Página 2");

            menuItem1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                    cardLayout.show(getContentPane(), "section1");
                }
            });

            menuItem2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                    cardLayout.show(getContentPane(), "section2");
                }
            });

            menu.add(menuItem1);
            menu.add(menuItem2);
            menuBar.add(menu);
            section2.add(menuBar);
        }
    }
}
