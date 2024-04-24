import conversion.Conversion;
import menu.MenuHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

// Clase Principal que Instancia el Convertidor de Monedas.

public class Principal {
    public static void main(String[] args) {
        try (Scanner lectura = new Scanner(System.in)) {
            System.out.println("\n");
            System.out.println("\n************************************************************");
            System.out.println("              Bienvenido al Conversor de Moneda");
            Conversion conversion = new Conversion();
            // Instanciar Conversion
            do {
                MenuHandler.mostrarMenu();
                int opcion = lectura.nextInt();
                MenuHandler.ejecutarOpcion(opcion, conversion, lectura); // Pasar Conversion y Scanner
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Por favor Ingrese un Número Válido");
        }
    }
}
