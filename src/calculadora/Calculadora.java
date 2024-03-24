
package calculadora;

/**
Nombre completo: Hugo Ivan Marin Galicia
Fecha de elaboración: 12 de Marzo de 2023
Nombre del Módulo: Topicos Avanzados de Programación
Nombre del Asesor: Margarita Márquez Tirso
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
        setSize(400, 600);

        // Inicialización de componentes
        textField = new JTextField();
        initializeTextField();

        JPanel buttonPanel = createButtonPanel();

        // Diseño del panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(textField, BorderLayout.NORTH);
        panelPrincipal.add(buttonPanel, BorderLayout.CENTER);

        // Añadir el panel principal a la ventana
        add(panelPrincipal);
        setVisible(true);
    }

    private void initializeTextField() {
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(400, 50));
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));

        numberButtons = createNumberButtons();
        operationButtons = createOperationButtons();
        equalsButton = createButton("=", 80, 80);
        clearButton = createButton("C", 80, 80);

        addButtonsToPanel(buttonPanel);

        return buttonPanel;
    }

    private JButton[] createNumberButtons() {
        JButton[] buttons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            buttons[i] = createButton(String.valueOf(i), 80, 80);
            buttons[i].addActionListener(this);
        }
        return buttons;
    }

    private JButton[] createOperationButtons() {
        JButton[] buttons = new JButton[4];
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            buttons[i] = createButton(operations[i], 80, 80);
            buttons[i].addActionListener(this);
        }
        return buttons;
    }

    private JButton createButton(String label, int width, int height) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(this);
        return button;
    }

    private void addButtonsToPanel(JPanel buttonPanel) {
        for (int i = 1; i < 10; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(clearButton);
        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(operationButtons[1]);
        buttonPanel.add(operationButtons[2]);
        buttonPanel.add(operationButtons[3]);
        buttonPanel.add(equalsButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                handleNumberButtonClick(i);
                break;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == operationButtons[i]) {
                handleOperationButtonClick(i);
                break;
            }
        }

        if (e.getSource() == equalsButton) {
            handleEqualsButtonClick();
            return;
        }

        if (e.getSource() == clearButton) {
            handleClearButtonClick();
        }
    }

    private void handleNumberButtonClick(int number) {
    String currentText = textField.getText();
            textField.setText(String.valueOf(number));
         
    }

    private void handleOperationButtonClick(int index) {
        performOperation(index);
    }

    private void handleEqualsButtonClick() {
        num2 = Double.parseDouble(textField.getText());
        calculateResult();
    }

    private void handleClearButtonClick() {
        clearCalculator();
    }

    private void performOperation(int index) {
        num1 = Double.parseDouble(textField.getText());
        operator = indexToOperator(index);
        textField.setText("");
    }

    private char indexToOperator(int index) {
        char[] operators = {'+', '-', '*', '/'};
        return operators[index];
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


