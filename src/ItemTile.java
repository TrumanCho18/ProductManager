import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ItemTile extends JPanel {

    public ItemTile(Product p) {
        initComp(p);
    }

    private void initComp(Product p) {

        String name = p.getName();
        Font JBMono = new Font("JetBrains Mono", Font.BOLD, 18);
        Font JBMonoSmall = new Font("JetBrains Mono", Font.BOLD, 13);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setBackground(new Color(67, 102, 121));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel Name = new JLabel(name);
        Name.setForeground(Color.WHITE);
        Name.setFont(JBMono);

        ArrayList<JLabel> infoLabels = getJLabels(p, JBMonoSmall);

        JButton btn1 = new JButton("+");
        btn1.setFocusPainted(false);
        btn1.setBorderPainted(false);
        btn1.setBackground(new Color(95, 142, 195));
        btn1.setForeground(Color.WHITE);

        JButton btn2 = new JButton("-");
        btn2.setFocusPainted(false);
        btn2.setBorderPainted(false);
        btn2.setBackground(new Color(95, 142, 195));
        btn2.setForeground(Color.WHITE);

        JPanel SubPanel = new JPanel(new GridBagLayout());
        SubPanel.setBackground(new Color(67, 102, 121));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0, 5, 0, 5);
        SubPanel.add(btn2, gbc2);
        JLabel Qty = new JLabel("QTY:" + String.valueOf(p.getQty()));
        Qty.setFont(JBMono);
        Qty.setForeground(Color.WHITE);
        SubPanel.add(Qty);
        SubPanel.add(btn1, gbc2);

        JPanel InfoPanel = new JPanel(new GridBagLayout());
        InfoPanel.setBackground(new Color(67, 102, 121));
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(5, 0, 0, 0);
        gbc3.anchor = GridBagConstraints.NORTH;
        gbc3.gridwidth = GridBagConstraints.REMAINDER;
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        for ( JLabel j : infoLabels) {
            InfoPanel.add(j, gbc3);
        }

        add(Name, gbc);
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
}
