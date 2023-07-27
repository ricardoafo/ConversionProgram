import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyApi {

    private String currencies;
    private String baseCurrency = "ARS";

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public double currencyApiRequest() {
        String url = "https://api.currencyapi.com/v3/latest";
        String apiKey = "cur_live_7qb93AMPMfpQlYqc6LIvu1Fnfs7GnWK698IsakVs";

        double valueDouble = 0;
        try {
            URL requestUrl = new URL(url + "?apikey=" + apiKey + "&currencies=" + currencies + "&base_currency=" + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            // Set the request method (GET in this case)
            connection.setRequestMethod("GET");

            // Get the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Print the response
                System.out.println("Response: " + response);

                // Regular expression pattern to match the value
                Pattern pattern = Pattern.compile("\"value\":\\s*([\\d.]+)");

                // Match the pattern against the input string
                Matcher matcher = pattern.matcher(response);

                // Check if the pattern is found and extract the value
                if (matcher.find()) {
                    String matchedValue = matcher.group(1);
                    valueDouble = Double.parseDouble(matchedValue);
                    System.out.println(valueDouble);
                } else {
                    System.out.println("Value not found.");
                }
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valueDouble;
    }
}
