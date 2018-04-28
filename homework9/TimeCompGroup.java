package hw9;

import javax.swing.*;
import java.awt.*;

public class TimeCompGroup extends JFrame {



    public TimeCompGroup(TimeComplexityTable table, XYChart chart) throws HeadlessException {
        super("Time Complexity Table");
        setSize(1200, 1200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));
        add(table);
        add(chart);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
