package macro;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

class SparkApp {
    public static void main(String[] args) throws InterruptedException {
        // Configurar la aplicación de Spark
        SparkConf conf = new SparkConf()
                .setAppName("Simple Streaming Spark App")
                .setMaster("spark://localhost:7077"); // Especifica la URL del maestro del clúster

        // Crear el contexto de Spark Streaming
        try (JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(1000))) {
            // Crear un flujo de entrada a partir de un servidor de sockets
            JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);

            // Procesar cada línea de datos recibida
            JavaDStream<String> processedLines = lines.map((Function<String, String>) line -> {
                // En este ejemplo, simplemente devolvemos la misma línea
                return line;
            });

            // Imprimir los datos en la consola
            processedLines.print();

            // Comenzar el procesamiento
            jssc.start();

            // Esperar a que el procesamiento termine
            jssc.awaitTermination();
        }
    }
}
