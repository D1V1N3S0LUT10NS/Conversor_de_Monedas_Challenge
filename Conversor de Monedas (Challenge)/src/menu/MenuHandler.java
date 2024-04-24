package menu;

import conversion.Conversion;
import conversion.RegistroConversion;

import java.util.Scanner;

public class MenuHandler {

    // Método para Mostrar el Menú de Opciones
    public static void mostrarMenu() {
        System.out.println("************************************************************");
        System.out.println("""
           
           1) Dólar a Peso Argentino
           2) Peso Argentino a Dólar
           3) Dólar a Real Brasileño
           4) Real Brasileño a Dólar
           5) Dólar a Peso Colombiano
           6) Peso Colombiano a Dólar
           7) Elegir Otras Monedas para Convertir
           8) Salir
           
           Elija una Opción Válida""");
    }

    // Método para Ejecutar la Opción Seleccionada por el Usuario
    public static void ejecutarOpcion(int opcion, Conversion conversion, Scanner lectura) {

        switch (opcion) {
            case 1:
                convertirMoneda("USD", "ARS", conversion, lectura);
                // Convertir de USD a ARS
                break;
            case 2:
                convertirMoneda("ARS", "USD", conversion, lectura);
                // Convertir de ARS a USD
                break;
            case 3:
                convertirMoneda("USD", "BRL", conversion, lectura);
                // Convertir de USD a BRL
                break;
            case 4:
                convertirMoneda("BRL", "USD", conversion, lectura);
                // Convertir de BRL a USD
                break;
            case 5:
                convertirMoneda("USD", "COP", conversion, lectura);
                // Convertir de USD a COP
                break;
            case 6:
                convertirMoneda("COP", "USD", conversion, lectura);
                // Convertir de COP a USD
                break;
            case 7:
                elegirOtrasMonedas(conversion, lectura);
                // Llama al Método para Elegir Otras Monedas a Convertir
                break;
            case 8:
                System.out.println("¡Gracias por Usar el Convertidor de Monedas!");
                // Mensaje de Cierre
                System.exit(0);
                // Finaliza el Programa
            default:
                System.out.println("Opción Inválida, por favor Seleccione una Opción Válida del Menú.");
                // Mensaje de Opción Inválida
                break;
        }
    }

    // Método Privado para Realizar la Conversión de Moneda
    private static void convertirMoneda(String monedaBase, String monedaDestino, Conversion c, Scanner lectura) {
        System.out.println("Ingrese el Monto a Convertir:");
        // Solicita al Usuario Ingresar el Monto a Convertir

        double monto = lectura.nextDouble();
        // Lee el Monto Ingresado por el Usuario

        int montoEntero = (int) monto;
        // Convierte el Monto a Entero

        RegistroConversion registro = c.convertir(monedaBase, monedaDestino, montoEntero);
        // Realiza la Conversión
        mostrarResultado(registro);
    }

    // Método para Elegir Otras Monedas a Convertir
    private static void elegirOtrasMonedas(Conversion conversion, Scanner lectura) {
        try {
            System.out.println("""
            Esta Lista contiene 100 Códigos de Moneda y sus Países de Origen para Facilitar la Conversión:
        
            | 1.  **AED**: United Arab Emirates      | 2.  **AFN**: Afghanistan          | 3.  **ALL**: Albania
            | 4.  **AMD**: Armenia                   | 5.  **ANG**: Netherlands Antilles | 6.  **AOA**: Angola
            | 7.  **ARS**: Argentina                 | 8.  **AUD**: Australia            | 9.  **AWG**: Aruba
            | 10. **AZN**: Azerbaijan                | 11. **BBD**: Barbados             | 12. **BGN**: Bulgaria
            | 13. **BHD**: Bahrain                   | 14. **BRL**: Brazil               | 15. **BSD**: Bahamas
            | 16. **BTN**: Bhutan                    | 17. **BWP**: Botswana             | 18. **BZD**: Belize
            | 19. **CAD**: Canada                    | 20. **CHF**: Switzerland          | 21. **CLP**: Chile
            | 22. **CNY**: China                     | 23. **COP**: Colombia             | 24. **CRC**: Costa Rica
            | 25. **CUP**: Cuba                      | 26. **CVE**: Cape Verde           | 27. **CZK**: Czech Republic
            | 28. **DKK**: Denmark                   | 29. **DOP**: Dominican Republic   | 30. **DZD**: Algeria
            | 31. **EGP**: Egypt                     | 32. **ERN**: Eritrea              | 33. **ETB**: Ethiopia
            | 34. **EUR**: European Union            | 35. **FJD**: Fiji                 | 36. **FKP**: Falkland Islands
            | 37. **GBP**: United Kingdom            | 38. **GEL**: Georgia              | 39. **GHS**: Ghana
            | 40. **GIP**: Gibraltar                 | 41. **GMD**: The Gambia           | 42. **GNF**: Guinea
            | 43. **GTQ**: Guatemala                 | 44. **GYD**: Guyana               | 45. **HKD**: Hong Kong
            | 46. **HNL**: Honduras                  | 47. **HRK**: Croatia              | 48. **HTG**: Haiti
            | 49. **HUF**: Hungary                   | 50. **IDR**: Indonesia            | 51. **ILS**: Israel
            | 52. **IMP**: Isle of Man               | 53. **INR**: India                | 54. **IQD**: Iraq
            | 55. **IRR**: Iran                      | 56. **ISK**: Iceland              | 57. **JMD**: Jamaica
            | 58. **JOD**: Jordan                    | 59. **JPY**: Japan                | 60. **KES**: Kenya
            | 61. **KGS**: Kyrgyzstan                | 62. **KHR**: Cambodia             | 63. **KID**: Kiribati
            | 64. **KMF**: Comoros                   | 65. **KRW**: South Korea          | 66. **KWD**: Kuwait
            | 67. **KYD**: Cayman Islands            | 68. **KZT**: Kazakhstan           | 69. **LAK**: Laos
            | 70. **LBP**: Lebanon                   | 71. **LKR**: Sri Lanka            | 72. **LRD**: Liberia
            | 73. **LSL**: Lesotho                   | 74. **LYD**: Libya                | 75. **MAD**: Morocco
            | 76. **MDL**: Moldova                   | 77. **MGA**: Madagascar           | 78. **MKD**: North Macedonia
            | 79. **MMK**: Myanmar                   | 80. **MNT**: Mongolia             | 81. **MOP**: Macau
            | 82. **MUR**: Mauritius                 | 83. **MVR**: Maldives             | 84. **MWK**: Malawi
            | 85. **MXN**: Mexico                    | 86. **MYR**: Malaysia             | 87. **MZN**: Mozambique
            | 88. **NAD**: Namibia                   | 89. **NGN**: Nigeria              | 90. **NIO**: Nicaragua
            | 91. **NOK**: Norway                    | 92. **NPR**: Nepal                | 93. **NZD**: New Zealand
            | 94. **OMR**: Oman                      | 95. **PAB**: Panama               | 96. **PEN**: Peru
            | 97. **PGK**: Papua New Guinea          | 98. **PHP**: Philippines          | 99. **PKR**: Pakistan
            | 100. **PLN**: Poland
            Ingrese el Código de Moneda Base:""");

            String monedaBase = lectura.next().toUpperCase();
            // Lee el Código de Moneda Base Ingresado por el Usuario

            System.out.println("Ingrese el Código de Moneda Destino:");
            String monedaDestino = lectura.next().toUpperCase();
            // Lee el Código de Moneda Destino Ingresado por el Usuario

            convertirMoneda(monedaBase, monedaDestino, conversion, lectura);
            // Realiza la Conversión

        } catch (Exception e) {
            System.out.println("Error: Ingrese un Código de Moneda Válido.");
        }
    }

    // Método Privado para Mostrar el Resultado de la Conversión
    private static void mostrarResultado(RegistroConversion registro) {
        if (registro != null) {
            Conversion conversion = registro.getConversion();
            if (conversion != null) {
                double monto = conversion.getMonto();
                double resultado = conversion.getResultado();
                double conversionRate = conversion.getConversionRate();

                // Redondea los Valores para Mostrar solo 2 Decimales
                monto = Math.round(monto * 10000.0) / 10000.0;
                resultado = Math.round(resultado * 10000.0) / 10000.0;
                conversionRate = Math.round(conversionRate * 10000.0) / 10000.0;

                System.out.println("************************************************************");
                System.out.println("Resultado de la Conversión:");
                System.out.println("---------------------------------");
                System.out.println("Moneda Base: " + conversion.getMonedaOrigen() + ": " + String.format("%.0f", monto));
                System.out.println("Moneda Destino: " + conversion.getMonedaDestino() + ": " + String.format("%.2f", resultado));
                System.out.println("Monto: " + String.format("%.1f", monto));
                System.out.println("Tasa de Conversión: " + String.format("%.5f", conversionRate));
                System.out.println("Resultado: " + String.format("%.2f", resultado));
                System.out.println("************************************************************");

            } else {
                System.out.println("Ha ocurrido un Error al Obtener la Conversión");
            }
        } else {
            System.out.println("Ha ocurrido un Error al Convertir la Moneda");
        }
    }
}
