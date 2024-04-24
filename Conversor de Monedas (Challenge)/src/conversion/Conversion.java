package conversion;

import adapters.LocalDateTimeAdapter;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

// Clase que Representa una Conversión entre Monedas

public class Conversion {

    // Código de la Moneda de Origen
    @SerializedName("Moneda_Origen")
    private String monedaOrigen;

    // Código de la Moneda de Destino
    @SerializedName("Moneda_Destino")
    private String monedaDestino;

    // Monto de Moneda a Convertir
    @SerializedName("Monto_a_Convertir")
    private double monto;

    // Resultado de la Conversión
    @SerializedName("Resultado")
    private double resultado;

    // Tasa de Conversión
    @SerializedName("Tasa_Conversion")
    private double conversionRate;

    // Constructor Vacío
    public Conversion() {}

    /*
      Constructor con Parámetros.
      @param monedaOrigen (Código de la Moneda de Origen)
      @param monedaDestino (Código de la Moneda de Destino)
      @param monto (Monto a Convertir)
     */

    public Conversion(String monedaOrigen, String monedaDestino, double monto) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.monto = monto;
    }

    // Getters y setters
    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    /*
      Realiza la Conversión de Moneda
      @param codMonOrigen (Código de la Moneda de Origen)
      @param codMonDestino (Código de la Moneda de Destino)
      @param monto (Monto a Convertir)
      @return (Registro de la Conversión)
     */

    public RegistroConversion convertir(String codMonOrigen, String codMonDestino, int monto) {

        // Construir la URI para la Solicitud API
        URI direccion_API = URI.create("https://v6.exchangerate-api.com/v6/ae1e02032d0479fefb847707/pair/"
                + codMonOrigen + "/" + codMonDestino + "/" + monto);

        // Crear Cliente y Solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion_API)
                .build();
        HttpResponse<String> response;

        try {
            // Realizar Solicitud HTTP
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Leer el JSON de la Respuesta
        try (JsonReader reader = new JsonReader(new StringReader(response.body()))) {
            reader.setLenient(true);

            // Configurar Gson para Deserializar la Respuesta
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            // Deserializar la Respuesta en un Objeto ConversionResponse
            ConversionResponse conversionResponse = gson.fromJson(reader, ConversionResponse.class);

            // Calcular el Resultado y la Tasa de Conversión
            this.conversionRate = conversionResponse.getConversionRate();
            this.resultado = monto * conversionRate;

            // Crear un Objeto Conversion con los Datos de la Respuesta
            Conversion conversion = new Conversion(conversionResponse.getMonedaOrigen(), conversionResponse.getMonedaDestino(), monto);
            conversion.setResultado(resultado);
            conversion.setConversionRate(conversionResponse.getConversionRate());

            // Crear un Registro de la Conversión
            RegistroConversion registroConversion = new RegistroConversion(conversion);

            // Leer Historial de Conversiones desde el Archivo JSON
            RegistroConversion[] historial;
            try (Reader fileReader = new FileReader("registros_data_time.json")) {
                historial = gson.fromJson(fileReader, RegistroConversion[].class);
            } catch (FileNotFoundException e) {
                historial = new RegistroConversion[0];
            }

            // Agregar el Nuevo Registro al Historial
            RegistroConversion[] nuevoHistorial = new RegistroConversion[historial.length + 1];
            System.arraycopy(historial, 0, nuevoHistorial, 0, historial.length);
            nuevoHistorial[historial.length] = registroConversion;

            // Escribir el Historial Actualizado en el Archivo JSON
            try (Writer fileWriter = new FileWriter("registros_data_time.json")) {
                gson.toJson(nuevoHistorial, fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Devolver el Registro de la Conversión
            return registroConversion;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
