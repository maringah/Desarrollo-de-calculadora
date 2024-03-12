/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

/**
 *
 * @author hugot
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton, clearButton;

    private double num1, num2, result;
    private char operator;

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new GridLayout(6, 4));

        textField = new JTextField();
        numberButtons = new JButton[10];
        operationButtons = new JButton[4];
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
        }

        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            operationButtons[i].addActionListener(this);
        }

        equalsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        equalsButton.addActionListener(this);

        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(this);

        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);

        add(textField);
        for (int i = 1; i < 10; i++) {
            add(numberButtons[i]);
        }
        add(numberButtons[0]);
        add(clearButton);
        add(operationButtons[0]);
        add(operationButtons[1]);
        add(operationButtons[2]);
        add(operationButtons[3]);
        add(equalsButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculadora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        if (e.getSource() == operationButtons[0]) {
            performOperation('+');
        } else if (e.getSource() == operationButtons[1]) {
            performOperation('-');
        } else if (e.getSource() == operationButtons[2]) {
            performOperation('*');
        } else if (e.getSource() == operationButtons[3]) {
            performOperation('/');
        }

        if (e.getSource() == equalsButton) {
            num2 = Double.parseDouble(textField.getText());
            calculateResult();
        }

        if (e.getSource() == clearButton) {
            clearCalculator();
        }
    }

    private void performOperation(char op) {
        num1 = Double.parseDouble(textField.getText());
        operator = op;
        textField.setText("");
    }

    private void calculateResult() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    textField.setText("Error");
                    return;
                }
                break;
        }

        textField.setText(String.valueOf(result));
        num1 = result;
        operator = '\0';
    }

    private void clearCalculator() {
        textField.setText("");
        num1 = num2 = result = 0;
        operator = '\0';
    }
}
