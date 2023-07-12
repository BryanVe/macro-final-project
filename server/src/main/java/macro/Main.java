package macro;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SimpleSparkApp {
    public static void main(String[] args) {
        // Configurar la aplicación de Spark
        SparkConf conf = new SparkConf()
                .setAppName("Simple Spark App")
                .setMaster("spark://localhost:7077"); // Especifica la URL del maestro del clúster

        // Crear el contexto de Spark
        JavaSparkContext sc = new JavaSparkContext(conf);

        System.out.println("SparkContext creado exitosamente!");

        // Detener el contexto de Spark
        sc.stop();

        System.out.println("SparkContext detenido exitosamente!");
    }
}
