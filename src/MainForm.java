import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainForm extends JFrame {

    private JPanel MainWindow;
    private JLabel HeaderText;
    private JButton button1;
    private JPanel TopPanel;
    private JPanel MidPanel;


    public MainForm() {

        setContentPane(MainWindow);
        HeaderText.setBorder(new EmptyBorder(30, 30, 30, 30));
        button1.setSize(50, 50);


        setTitle("ProductManager(v.beta)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new MainForm();

    }
}
