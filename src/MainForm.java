import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainForm extends JFrame {

    private JPanel MainWindow;
    private JLabel HeaderText;
    private JPanel Home;
    private JPanel AddProductScreen;
    private JButton addProductButton;
    private JLabel AddProduct;
    private JTextField Name;
    private JTextField Cost;
    private JTextField Sold;
    private JButton addButton;
    private JLabel Status;
    private JSpinner qty;
    private JTextField Date;
    private JButton Xbutton;

    public MainForm() {

        setContentPane(MainWindow);
        HeaderText.setBorder(new EmptyBorder(30, 30, 30, 30));

        CardLayout c = new CardLayout();
        MainWindow.setLayout(c);

        addProductButton.setBorderPainted(false);
        addProductButton.setFocusPainted(false);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.next(MainWindow);
                Status.setText(" ");
            }
        });

        Name.setBorder(null);
        Sold.setBorder(null);
        Cost.setBorder(null);
        Date.setBorder(null);

        qty.setBorder(null);
        JComponent editor = qty.getEditor();
        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
        qty.addChangeListener(e -> {
            int val = (int) qty.getValue();
            if (val < 1) {
                textField.setBackground(Color.RED);
            } else {
                textField.setBackground(Color.GRAY);
            }
        });
        qty.setValue(1);

        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Status.setForeground(Color.GREEN);
                Status.setText("Product Added Successfully!");
            }
        });

        Xbutton.setBorderPainted(false);
        Xbutton.setFocusPainted(false);

        Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Name.getText().equals("Name")) {
                    Name.setText("");
                    Name.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (Name.getText().isEmpty()) {
                    Name.setForeground(Color.LIGHT_GRAY);
                    Name.setText("Name");
                }
            }
        });

        Sold.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Sold.getText().equals("Sell Price")) {
                    Sold.setText("");
                    Sold.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (Sold.getText().isEmpty()) {
                    Sold.setForeground(Color.LIGHT_GRAY);
                    Sold.setText("Sell Price");
                }
            }
        });

        Cost.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Cost.getText().equals("Cost")) {
                    Cost.setText("");
                    Cost.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (Cost.getText().isEmpty()) {
                    Cost.setForeground(Color.LIGHT_GRAY);
                    Cost.setText("Cost");
                }
            }
        });

        Date.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Date.getText().equals("Date Logged")) {
                    Date.setText("");
                    Date.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (Date.getText().isEmpty()) {
                    Date.setForeground(Color.LIGHT_GRAY);
                    Date.setText("Date Logged");
                }
            }
        });

        setTitle("ProductManager(v.beta)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new MainForm();
        CardLayout c = new CardLayout();
    }

}
