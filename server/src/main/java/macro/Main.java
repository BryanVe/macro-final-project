package macro;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

class SparkStreamingApp {
    public static void main(String[] args) throws StreamingQueryException, TimeoutException {
        // Crear una instancia de SparkSession
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark Streaming App")
                .master("spark://localhost:7077")
                .getOrCreate();

        // Crear un DataFrame representando los datos de streaming
        Dataset<Row> dataStream = spark
                .readStream()
                .format("socket")
                .option("host", "localhost")
                .option("port", 9999)
                .load();

        // Realizar operaciones de transformación y acción en los datos de streaming
        Dataset<Row> transformedData = dataStream.selectExpr("value AS message");

        // Escribir los resultados en la consola
        StreamingQuery query = transformedData.writeStream()
                .format("console")
                .outputMode("append")
                .start();

        // Iniciar la ejecución del streaming
        query.awaitTermination();
    }
}
