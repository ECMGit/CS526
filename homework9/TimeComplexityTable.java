package hw9;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class TimeComplexityTable extends JComponent {
    // default table model
    private DefaultTableModel model = null;
    private JTable table = null;

    /**
     * @param data   each sort method running time
     * @param titles each column title,including the input size
     */
    public TimeComplexityTable(String[][] data, String[] titles) {


        model = new DefaultTableModel(data, titles);
        table = new JTable(model);
        setLayout(new BorderLayout());
        add("North",new JScrollPane(table));
//        add(new X2FunctionPanel());
        //set window's size
        setSize(1100, 400);
        setVisible(true);

        //draw time complexity graph of each sort method
    }



}


