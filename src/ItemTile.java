import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.StandardOpenOption;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemTile extends JPanel {

    private boolean Valid = true;

    public ItemTile(Product p, CardLayout c,  JPanel I, int P, ArrayList<Product> ProductList) {
        initComp(p, c, I, P, ProductList);
    }

    private void initComp(Product p, CardLayout c, JPanel ItemHolder, int Page, ArrayList<Product> ProductList) {

        Color BackGroundBlue = new Color(67, 102, 121);
        Color BtnBlue = new Color(95, 142, 195);
        Color BtnRed = new Color(200, 10, 50);

        String name = p.getName();
        Font JBMono = new Font("JetBrains Mono", Font.BOLD, 18);
        Font JBMonoSmall = new Font("JetBrains Mono", Font.BOLD, 13);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setBackground(BackGroundBlue);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel Name = new JLabel(name);
        Name.setForeground(Color.WHITE);
        Name.setFont(JBMono);

        ArrayList<JLabel> infoLabels = getJLabels(p, JBMonoSmall);
        JLabel Qty = new JLabel("QTY:" + String.valueOf(p.getQty()));

        JButton btn1 = new JButton("+");
        btn1.setFocusPainted(false);
        btn1.setBorderPainted(false);
        btn1.setBackground(BtnBlue);
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.AddStock(1);
                Qty.setText("QTY:" + p.getQty());
            }
        });

        JButton btn2 = new JButton("-");
        btn2.setFocusPainted(false);
        btn2.setBorderPainted(false);
        btn2.setBackground(BtnBlue);
        btn2.setForeground(Color.WHITE);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.AddStock(-1);
                Qty.setText("QTY:" + p.getQty());
            }
        });

        JButton Xbtn = new JButton("");
        Xbtn.setFocusPainted(false);
        Xbtn.setBorderPainted(false);
        Xbtn.setBackground(BtnRed);
        Xbtn.setPreferredSize(new Dimension(15, 15));
        Xbtn.setForeground(Color.WHITE);
        Xbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Valid) {
                    Valid = false;
                    ProductList.remove(p);
                    Name.setForeground(Color.RED);
                    Xbtn.setBackground(Color.GREEN);
                    System.out.println("REMOVED");
                } else {
                    Valid = true;
                    ProductList.add(p);
                    Name.setForeground(Color.WHITE);
                    Xbtn.setBackground(BtnRed);
                    System.out.println("ADDED BACK");
                }

            }
        });

        JPanel SubPanel = new JPanel(new GridBagLayout());
        SubPanel.setBackground(BackGroundBlue);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0, 5, 0, 5);
        SubPanel.add(btn2, gbc2);
        Qty.setFont(JBMono);
        Qty.setForeground(Color.WHITE);
        SubPanel.add(Qty);
        SubPanel.add(btn1, gbc2);

        JPanel InfoPanel = new JPanel(new GridBagLayout());
        InfoPanel.setBackground(BackGroundBlue);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(5, 0, 0, 0);
        gbc3.anchor = GridBagConstraints.NORTH;
        gbc3.gridwidth = GridBagConstraints.REMAINDER;
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        for ( JLabel j : infoLabels) {
            InfoPanel.add(j, gbc3);
        }

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.insets = new Insets(5, 0, 0, 0);
        gbc4.anchor = GridBagConstraints.WEST;
        gbc4.gridwidth = GridBagConstraints.REMAINDER;
        gbc4.fill = GridBagConstraints.WEST;

        JPanel SubPanel2 = new JPanel();
        SubPanel2.setBackground(BackGroundBlue);
        SubPanel2.add(Name, gbc4);
        SubPanel2.add(Xbtn, gbc4);
        add(SubPanel2, gbc4);
        add(InfoPanel, gbc);
        add(SubPanel, gbc);


    }

    private static ArrayList<JLabel> getJLabels(Product p, Font JBMonoSmall) {
        ArrayList<JLabel> infoLabels = new ArrayList<>();
        String[] info = {"Sell Price: " + p.getSellPrice(), "\nSubProfit: " + p.getSubProfit(), "\nDate Listed: " + p.getDateListed(), "\nAverage Efficiency: " + p.getEfficiency(), "\nAmountSold: " + p.getSoldAmt()};
        for (int i = 0; i < info.length; i++) {
            JLabel Details = new JLabel(info[i]);
            Details.setFont(JBMonoSmall);
            Details.setForeground(Color.WHITE);
            infoLabels.add(Details);
        }
        return infoLabels;
    }

    public static void loadData(ArrayList<Product> pList) {

        ArrayList<String> lines = getFileData("src/DataSave.txt");

        for (int i = 0; i < lines.size(); i++) {
            String Name = lines.get(i).split(",")[0];

            double Cost = Double.parseDouble(lines.get(i).split(",")[1]);
            double SellPrice = Double.parseDouble(lines.get(i).split(",")[2]);
            double SubProfit = Double.parseDouble(lines.get(i).split(",")[3]);

            int MonthListed = Integer.parseInt(lines.get(i).split(",")[4]);
            int DayListed = Integer.parseInt(lines.get(i).split(",")[5]);
            int YearListed = Integer.parseInt(lines.get(i).split(",")[6]);

            int MonthSold = Integer.parseInt(lines.get(i).split(",")[7]);
            int DaySold = Integer.parseInt(lines.get(i).split(",")[8]);
            int YearSold = Integer.parseInt(lines.get(i).split(",")[9]);

            int Qty = Integer.parseInt(lines.get(i).split(",")[10]);
            int SoldAmt = Integer.parseInt(lines.get(i).split(",")[11]);
            double Efficiency = Double.parseDouble(lines.get(i).split(",")[12]);

            pList.add(new Product(Name, Cost, SellPrice, SubProfit, MonthListed, DayListed, YearListed, MonthSold, DaySold, YearSold, Qty, SoldAmt, Efficiency));
        }
    }

    public static void saveData(ArrayList<Product> pList) {
        Path p = Paths.get("src/DataSave.txt");

        try {
            Files.writeString(p, "");
            for (int i = 0; i < pList.size(); i++) {
                String contentToAppend = pList.get(i).printSaveDataFormat();
                Files.writeString(p, contentToAppend, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while editing the file: " + e.getMessage());
        }
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
