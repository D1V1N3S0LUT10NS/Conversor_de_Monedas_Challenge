package conversion;

import com.google.gson.annotations.SerializedName;

    /*
       Clase que Representa la Respuesta de la API de ExchangeRate-API
       Proporciona Métodos para Acceder a los Datos de la Conversión
    */

public class ConversionResponse {

    /* El Código de la Moneda Base en la Conversión
       Índices de la API de ExchangeRate-API:
       "base_code"
       "target_code"
       "conversion_rate"
       "conversion_result"
    */

    // Código de la Moneda Origen en la Conversión
    @SerializedName("base_code")
    private String monedaOrigen;

    // Código de la Moneda Destino en la Conversión
    @SerializedName("target_code")
    private String monedaDestino;

    // Tasa de Conversión entre la Moneda Base y la Moneda Destino
    @SerializedName("conversion_rate")
    private double conversionRate;

    public ConversionResponse(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    // Obtiene el Código de la Moneda Base
    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    // Obtiene el Código de la Moneda Destino
    public String getMonedaDestino() {
        return monedaDestino;
    }

    // Obtiene la Tasa de Conversión
    public double getConversionRate() {
        return conversionRate;
    }

}
