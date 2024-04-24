package conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase que Representa un Registro de Conversión

public class RegistroConversion {
    // Atributos

    private final Conversion conversion;
    // Representa la Conversión Realizada

    private final LocalDateTime timestamp;
    // Marca de tiempo de la conversión

    /*
      Constructor de Registro Conversion.
      @param conversion (Conversión que se está Registrando)
     */

    public RegistroConversion(Conversion conversion) {
        this.conversion = conversion;
        this.timestamp = LocalDateTime.now();
    }

    /*
      Obtiene la Conversión Asociada con este Registro.
      @return (Conversión Asociada)
    */

    // Getters y Setters
    public Conversion getConversion() {
        return conversion;
    }

    /*
      Establece la Conversión Asociada con este Registro.
      @param conversion (Conversión a Establecer)
    */

    /*
      Obtiene la Marca de Tiempo de la Conversión
      @return (Marca de Tiempo)
    */

    /*
      Establece la Marca de Tiempo de la Conversión
      @param timestamp (Marca de Tiempo a Establecer)
    */

    /*
      Genera una Representación en Cadena del Registro de Conversión
      @return (Representación en Cadena del Registro de Conversión)
    */

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Registro de Conversión: {" +
                "\n  Moneda de Origen: " + conversion.getMonedaOrigen() +
                "\n  Moneda de Destino: " + conversion.getMonedaDestino() +
                "\n  Monto: " + conversion.getMonto() +
                "\n  Conversion Rate: " + conversion.getConversionRate() +
                "\n  Resultado: " + conversion.getResultado() +
                "\n  Marca de Tiempo: " + timestamp.format(formatter) +
                "\n}";
    }
}
