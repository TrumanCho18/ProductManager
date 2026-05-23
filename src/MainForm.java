import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

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
    private JButton viewItemsButton;
    private JPanel ListScreen;
    private JScrollPane ItemScrollPanel;
    private JPanel ItemGrid;

    public MainForm() {

        setContentPane(MainWindow);
        HeaderText.setBorder(new EmptyBorder(30, 30, 30, 30));

        CardLayout c = new CardLayout();
        MainWindow.setLayout(c);

        ArrayList<Product> ProductList = new ArrayList<>();

        ItemScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ItemScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ItemScrollPanel.getViewport().setBackground(new Color(0, 11, 22));
        ItemScrollPanel.getVerticalScrollBar().setBackground(new Color(95, 142, 195));

        addProductButton.setBorderPainted(false);
        addProductButton.setFocusPainted(false);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.next(MainWindow);
                Status.setText(" ");
            }
        });

        viewItemsButton.setBorderPainted(false);
        viewItemsButton.setFocusPainted(false);
        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.next(MainWindow);
                c.next(MainWindow);

                JPanel ScrollContent = new JPanel();
                ScrollContent.setBackground(new Color(0, 11, 22));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                Dimension minSize = new Dimension(800, 600);
                Dimension prefSize = new Dimension(800, 600);
                Dimension maxSize = new Dimension(800, Integer.MAX_VALUE);
                ScrollContent.setMinimumSize(minSize);
                ScrollContent.setPreferredSize(prefSize);
                ScrollContent.setMaximumSize(maxSize);


                for (Product p : ProductList) {
                    ScrollContent.add(new ItemTile(p), gbc);
                }

                ItemScrollPanel.setViewportView(ScrollContent);
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

                boolean valid = true;

                String temp;
                double CostVal = 0;
                double SellVal = 0;
                int M = 0;
                int D = 0;
                int Y = 0;

                temp = Cost.getText();
                try  {
                    CostVal = Double.parseDouble(temp);
                } catch (NumberFormatException x){
                    valid = false;
                }

                temp = Sold.getText();
                try  {
                    SellVal = Double.parseDouble(temp);
                } catch (NumberFormatException x){
                    valid = false;
                }

                temp = Date.getText();
                if (temp.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    M = Integer.parseInt(temp.split("/")[0]);
                    D = Integer.parseInt(temp.split("/")[1]);
                    Y = Integer.parseInt(temp.split("/")[2]);
                } else {
                    valid = false;
                }

                int tempQty = (int) qty.getValue();
                if (tempQty < 1) {
                    valid = false;
                }

                if (valid) {
                    Product p = new Product(Name.getText(), CostVal, SellVal, M, D, Y);
                    p.AddStock(tempQty - 1);
                    ProductList.add(p);

                    Status.setForeground(Color.GREEN);
                    Status.setText("Product Added Successfully!");

                    System.out.println(ProductList.getLast());

                } else {
                    Status.setForeground(Color.RED);
                    Status.setText("Invalid fields!");
                }
            }
        });

        Xbutton.setBorderPainted(false);
        Xbutton.setFocusPainted(false);
        Xbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.first(MainWindow);
            }
        });

        Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Status.setText("");
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
                Status.setText("");
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
                Status.setText("");
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
                Status.setText("");
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
