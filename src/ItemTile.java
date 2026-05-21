import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ItemTile extends JPanel {

    public ItemTile() {
        initComp();
    }

    private void initComp() {
        setSize(new Dimension(500, 500));
        setBackground(new Color(67, 102, 121));
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel Name = new JLabel("NAME");
        Name.setForeground(Color.WHITE);
        Name.setFont(new Font("JetBrains Mono", Font.BOLD, 18));

        JButton btn1 = new JButton("Btn");
        btn1.setFocusPainted(false);
        btn1.setBorderPainted(false);
        btn1.setBackground(new Color(95, 142, 195));
        btn1.setForeground(Color.WHITE);

        //this.add(Name);
        this.add(btn1);

    }
}
