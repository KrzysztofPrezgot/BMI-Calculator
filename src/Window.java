import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener    {

    JButton countButton, resetButton, exitButton;
    JLabel weightLabel, heightLabel, bmiLabel;
    JTextField weightField, heightField, bmiField;

    double height, weight, bmi, bmiRounded;

    public Window() {
        setSize(400,400);
        setTitle("BMI Calculator");
        setLayout(null);

        countButton = new JButton("Oblicz");
        countButton.setBounds(250, 50, 100, 80);
        add(countButton);
        countButton.addActionListener(this);

        resetButton = new JButton("Wyzeruj");
        resetButton.setBounds(250, 150, 100, 80);
        add(resetButton);
        resetButton.addActionListener(this);

        exitButton = new JButton("Wyjście");
        exitButton.setBounds(250, 250, 100, 80);
        add(exitButton);
        exitButton.addActionListener(this);

        weightLabel = new JLabel("kg");
        weightLabel.setBounds(160, 50, 150,80);
        add(weightLabel);

        heightLabel = new JLabel("cm");
        heightLabel.setBounds(160, 150, 150,80);
        add(heightLabel);

        bmiLabel = new JLabel("BMI:");
        bmiLabel.setBounds(40, 250, 50,80);
        add(bmiLabel);

        weightField = new JTextField();
        weightField.setBounds(70,50,80,80);
        add(weightField);

        heightField = new JTextField();
        heightField.setBounds(70,150,80,80);
        add(heightField);

        bmiField = new JTextField();
        bmiField.setBounds(70, 250, 80,80);
        add(bmiField);

    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == countButton) {
            height = Double.parseDouble(heightField.getText());
            weight = Double.parseDouble(weightField.getText());
            bmi = 10000 * weight/ (height * height);
            bmiRounded = round(bmi,1);

            bmiField.setText(String.valueOf(bmiRounded));
        }
        else if (source == resetButton) {
            weightField.setText("");
            heightField.setText("");
            bmiField.setText("");
        }
        else if (source == exitButton) {
            dispose();
        }

    }

}