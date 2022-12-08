import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.*;

public class bmiCalculator extends JPanel implements PropertyChangeListener    {


    private JLabel weightLabel, heightLabel, bmiLabel;
    private JFormattedTextField weightField, heightField, bmiField;
    private NumberFormat weightFormat, heightFormat, bmiFormat;
    private double height = 0;
    private double weight = 0;
    private double bmi = 0;
    private static String weightString = "Waga:";
    private static String heightString = "Wzrost:";
    private static String bmiString = "BMI:";

    PropertyChangeEvent e;

    private void setUpFormats ()    {
        weightFormat = NumberFormat.getNumberInstance();
        heightFormat = NumberFormat.getNumberInstance();
        bmiFormat = NumberFormat.getNumberInstance();
    }
    double computeBmi(double weight, double height) {
        bmi = weight * 10000 / (height * height);
        return bmi;
    }

    public bmiCalculator()  {
        super(new BorderLayout());
        setUpFormats();
        double bmi = computeBmi(weight, height);

        weightLabel = new JLabel(weightString);
        heightLabel = new JLabel(heightString);
        bmiLabel = new JLabel(bmiString);

        weightField = new JFormattedTextField(weightFormat);
        weightField.setValue(new Double(weight));
        weightField.setColumns(4);
        weightField.addPropertyChangeListener("value", this);

        heightField = new JFormattedTextField(heightFormat);
        heightField.setValue(new Double(height));
        heightField.setColumns(4);
        heightField.addPropertyChangeListener("value", this);

        bmiField = new JFormattedTextField(bmiFormat);
        bmiField.setValue(new Double(bmi));
        bmiField.setColumns(3);
        bmiField.setEditable(false);

        weightLabel.setLabelFor(weightField);
        heightLabel.setLabelFor(heightField);
        bmiLabel.setLabelFor(bmiField);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(weightLabel);
        labelPane.add(heightLabel);
        labelPane.add(bmiLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(weightField);
        fieldPane.add(heightField);
        fieldPane.add(bmiField);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    public void propertyChange (PropertyChangeEvent e) {
        Object source = e.getSource();

        if (source == weightField) {
            weight = ((Number) weightField.getValue()).doubleValue();
        }
        else if (source == heightField) {
            height = ((Number) heightField.getValue()).doubleValue();
        }

        double bmi = computeBmi (weight, height);
        bmiField.setValue(new Double(bmi));
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new bmiCalculator());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main (String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
                UIManager.put("swing.boldMetal",Boolean.FALSE);
                createAndShowGUI();
           }
       });
    }


}