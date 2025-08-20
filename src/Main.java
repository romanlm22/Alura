import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorApp {
    private static final String API_KEY = "e5c2fc7c34d81e6e725f8b4f"; // tu API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + "e5c2fc7c34d81e6e725f8b4f" + "/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // 1) Conexión a la API
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 2) Parsear JSON
            JSONObject json = new JSONObject(response.toString());
            JSONObject rates = json.getJSONObject("conversion_rates");

            // Extraer tasas necesarias
            double ars = rates.getDouble("ARS"); // Peso argentino
            double brl = rates.getDouble("BRL"); // Real brasileño
            double cop = rates.getDouble("COP"); // Peso colombiano

            // 3) Menú
            int opcion;
            do {
                System.out.println("**********************************");
                System.out.println("1) Dólar americano ==> Peso argentino");
                System.out.println("2) Peso argentino ==> Dólar americano");
                System.out.println("3) Dólar americano ==> Real brasileño");
                System.out.println("4) Real brasileño ==> Dólar americano");
                System.out.println("5) Dólar americano ==> Peso colombiano");
                System.out.println("6) Peso colombiano ==> Dólar americano");
                System.out.println("7) Salir");
                System.out.print("Elija una opción válida: ");
                opcion = scanner.nextInt();

                double cantidad, resultado;

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese cantidad en USD: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad * ars;
                        System.out.println("Equivalente en ARS: " + resultado);
                        break;
                    case 2:
                        System.out.print("Ingrese cantidad en ARS: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad / ars;
                        System.out.println("Equivalente en USD: " + resultado);
                        break;
                    case 3:
                        System.out.print("Ingrese cantidad en USD: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad * brl;
                        System.out.println("Equivalente en BRL: " + resultado);
                        break;
                    case 4:
                        System.out.print("Ingrese cantidad en BRL: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad / brl;
                        System.out.println("Equivalente en USD: " + resultado);
                        break;
                    case 5:
                        System.out.print("Ingrese cantidad en USD: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad * cop;
                        System.out.println("Equivalente en COP: " + resultado);
                        break;
                    case 6:
                        System.out.print("Ingrese cantidad en COP: ");
                        cantidad = scanner.nextDouble();
                        resultado = cantidad / cop;
                        System.out.println("Equivalente en USD: " + resultado);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } while (opcion != 7);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
