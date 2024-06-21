package calculadoraDemo;

/**
Nombre completo: Hugo Ivan Marin Galicia
Fecha de elaboración: 08 de Junio de 2024
Nombre del Módulo: Topicos Avanzados de Programación
Nombre del Asesor: Andrés Espinal Jiménez
* 
* @author hugot
*/


import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class CalculadoraUI extends JFrame {
    private JTextField display;
    private Calculadora calculadora;

    public CalculadoraUI() {
        calculadora = new Calculadora();
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Evita que el usuario pueda redimensionar la ventana
        setLayout(new BorderLayout());

        // Crear display
        display = new JTextField();
        display.setPreferredSize(new Dimension(400, 50));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Crear botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4, 10, 10)); // Ajusta la separación entre botones
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agrega espacio alrededor del panel de botones
        add(panelBotones, BorderLayout.CENTER);

        String[] botonesTexto = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : botonesTexto) {
            JButton boton = new JButton(texto);
            boton.setPreferredSize(new Dimension(80, 80));
            boton.setFont(new Font("Arial", Font.PLAIN, 24));
            boton.setFocusPainted(false);
            boton.addActionListener(new BotonListener());
            panelBotones.add(boton);
        }
    }

    private class BotonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            try {
                if (comando.equals("C")) {
                    calculadora.reset();
                    display.setText("");
                } else if (comando.equals("=")) {
                    calculadora.setNumeroActual(Double.parseDouble(display.getText()));
                    calculadora.realizarOperacion();
                    display.setText(String.valueOf(calculadora.getResultado()));
                } else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
                    calculadora.setNumeroActual(Double.parseDouble(display.getText()));
                    calculadora.setOperacion(comando);
                    display.setText("");
                } else {
                    display.setText(display.getText() + comando);
                }
            } catch (ArithmeticException ex) {
                display.setText("Error");
            } catch (NumberFormatException ex) {
                display.setText("Número Inválido");
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Configurar el look and feel Luna
            Properties props = new Properties();
            props.put("theme", "Luna");
            McWinLookAndFeel.setTheme(props);

            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            CalculadoraUI calculadoraUI = new CalculadoraUI();
            calculadoraUI.setVisible(true);
        });
    }
}

