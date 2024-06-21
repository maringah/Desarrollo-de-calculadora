package calculadoraDemo;

/**
Nombre completo: Hugo Ivan Marin Galicia
Fecha de elaboración: 08 de Junio de 2024
Nombre del Módulo: Topicos Avanzados de Programación
Nombre del Asesor: Andrés Espinal Jiménez
* 
* @author hugot
*/


public class Calculadora {
    private double resultado;
    private String operacion;
    private double numeroActual;

    public Calculadora() {
        resultado = 0;
        operacion = "";
        numeroActual = 0;
    }

    public void setNumeroActual(double numero) {
        this.numeroActual = numero;
    }

    public void realizarOperacion() {
        switch (operacion) {
            case "+":
                resultado += numeroActual;
                break;
            case "-":
                resultado -= numeroActual;
                break;
            case "*":
                resultado *= numeroActual;
                break;
            case "/":
                if (numeroActual != 0) {
                    resultado /= numeroActual;
                } else {
                    throw new ArithmeticException("División por cero");
                }
                break;
        }
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
        resultado = numeroActual;
        numeroActual = 0;
    }

    public double getResultado() {
        return resultado;
    }

    public void reset() {
        resultado = 0;
        operacion = "";
        numeroActual = 0;
    }
}
