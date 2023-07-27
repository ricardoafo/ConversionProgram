import javax.swing.*;

public class App {
    private static final Money_Exchange moneyExchange = new Money_Exchange();
    private static final CurrencyApi currencyApi = new CurrencyApi();

    private static final TemperatureConversion temperatureConversion = new TemperatureConversion();

    public static void main(String[] args) {
        moneyOrTemp();
    }

    public static void moneyOrTemp() {
        String[] options = {"Money Exchange", "Temperature Conversion"};
        String input = (String) JOptionPane.showInputDialog(null, "Select a conversion type",
                "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // Create an if statement to check if the user has selected the first option.
        if (input.equals("Money Exchange")) {
            // User enters the amount to change
            double amountDouble = amountOfMoney();

            // User selects the conversion type
            String conversionType = whatToChangeFromAndToMoney();
            System.out.println(conversionType);
            currencyApi.setCurrencies(conversionType);

            // API request
            double conversionRate = currencyApi.currencyApiRequest();

            // Money is converted and display
            moneyConversion(conversionType, amountDouble, conversionRate);

            // Ask user if he wants to do another conversion
            askToContinue();

        } else if (input.equals("Temperature Conversion")) {
            // User enters the amount to change
            double amountDouble = amountOfTemp();

            // User selects the conversion type
            String conversionType = whatToChangeFromAndToTemp();

            // Amount is converted and display
            tempConversion(conversionType, amountDouble);

            // Ask user if he wants to do another conversion
            askToContinue();
        }
    }

    public static double amountOfMoney() {
        // Create a showInputDialog() method for the user to enter the amount of money to be converted.
        String amount = JOptionPane.showInputDialog("Enter the amount of money to be converted");

        // Replace the comma with a period.
        amount = amount.replaceAll(",", ".");

        // Create a try-catch block to check if the user has entered a number, with a counter so the user can try up to three times.
        double amountDouble = 0;
        int counter = 0;
        int tries = 3;
        while (counter < 3) {
            try {
                amountDouble = Double.parseDouble(amount);

                // Validate if it's a positive number.
                if (amountDouble < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number");
                } else if (amountDouble == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a number greater than 0");
                } else {
                    break;
                }

                amount = JOptionPane.showInputDialog("Enter the amount of money to be converted, you have " + (tries - counter) + " tries left");
                counter++;
            } catch (NumberFormatException e) {
                amount = JOptionPane.showInputDialog("Please enter a valid number, you have " + (tries - counter) + " tries left");
                counter++;
            }
        }

        if (counter == 3) {
            JOptionPane.showMessageDialog(null, "You have exceeded the number of tries");
            System.exit(0);
        }

        System.out.println(amountDouble);

        return amountDouble;
    }

    public static double amountOfTemp() {
        // Create a showInputDialog() method for the user to enter the amount of money to be converted.
        String amount = JOptionPane.showInputDialog("Enter the temperature number to be converted");

        // Replace the comma with a period.
        amount = amount.replaceAll(",", ".");

        // Create a try-catch block to check if the user has entered a number, with a counter so the user can try up to three times.
        double amountDouble = 0;
        int counter = 0;
        int tries = 3;
        while (counter < 3) {
            try {
                amountDouble = Double.parseDouble(amount);
                counter++;
                break;
            } catch (NumberFormatException e) {
                amount = JOptionPane.showInputDialog("Please enter a valid number, you have " + (tries - counter) + " tries left");
                counter++;
            }
        }

        if (counter == 3) {
            JOptionPane.showMessageDialog(null, "You have exceeded the number of tries");
            System.exit(0);
        }

        System.out.println(amountDouble);

        return amountDouble;
    }

    public static String whatToChangeFromAndToMoney() {
        String[] conversionOptions = {"Argentinian Pesos to Dolar", "Argentinian Pesos to Euro", "Argentinian Pesos to Libra", "Argentinian Pesos to Yuan", "Argentinian Pesos to Corean Won", "Dolar to Argentinian Pesos", "Euro to Argentinian Pesos", "Libra to Argentinian Pesos", "Yuan to Argentinian Pesos", "Corean Won to Argentinian Pesos"};


        String conversionType = (String) JOptionPane.showInputDialog(null, "Select a conversion type", "Menu", JOptionPane.PLAIN_MESSAGE, null, conversionOptions, conversionOptions[0]);

        // Swicth que pase del string largo a un acronimo
        switch (conversionType) {
            case "Argentinian Pesos to Dolar":
                conversionType = "USD";
                break;
            case "Argentinian Pesos to Euro":
                conversionType = "EUR";
                break;
            case "Argentinian Pesos to Libra":
                conversionType = "GBP";
                break;
            case "Argentinian Pesos to Yuan":
                conversionType = "CNY";
                break;
            case "Argentinian Pesos to Corean Won":
                conversionType = "KRW";
                break;
            case "Dolar to Argentinian Pesos":
                conversionType = "ARS";
                currencyApi.setBaseCurrency("USD");
                break;
            case "Euro to Argentinian Pesos":
                conversionType = "ARS";
                currencyApi.setBaseCurrency("EUR");
                break;
            case "Libra to Argentinian Pesos":
                conversionType = "ARS";
                currencyApi.setBaseCurrency("GBP");
                break;
            case "Yuan to Argentinian Pesos":
                conversionType = "ARS";
                currencyApi.setBaseCurrency("CNY");
                break;
            case "Corean Won to Argentinian Pesos":
                conversionType = "ARS";
                currencyApi.setBaseCurrency("KRW");
            default:
                throw new IllegalStateException("Unexpected value: " + conversionType);
        }

        return conversionType;
    }

    public static String whatToChangeFromAndToTemp() {
        String[] conversionOptions = {"Celsius (C) to Fahrenheit (F)", "Fahrenheit (F) to Celsius (C)"};


        String conversionType = (String) JOptionPane.showInputDialog(null, "Select a conversion type", "Menu", JOptionPane.PLAIN_MESSAGE, null, conversionOptions, conversionOptions[0]);

        // Swicth que pase del string largo a un acronimo
        switch (conversionType) {
            case "Celsius (C) to Fahrenheit (F)":
                conversionType = "CtoF";
                break;
            case "Fahrenheit (F) to Celsius (C)":
                conversionType = "FtoC";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + conversionType);
        }

        return conversionType;
    }

    public static void moneyConversion(String conversionType, double amountDouble, double conversionRate) {
        moneyExchange.setConversionRate(conversionRate);

        if (conversionType.equals("ARS")) {
            JOptionPane.showMessageDialog(null, "The amount of money in " + conversionType +
                    " is: " + moneyExchange.toArs(amountDouble));
        } else {
            JOptionPane.showMessageDialog(null, "The amount of money in " + conversionType +
                    " is: " + moneyExchange.arsTo(amountDouble));
        }
    }

    public static void tempConversion(String conversionType, double amountDouble) {
        if (conversionType.equals("CtoF")) {
            JOptionPane.showMessageDialog(null, "The temperature in Fahrenheit is: " +
                    temperatureConversion.celsiusToFahrenheit(amountDouble) + "F°");
        } else {
            JOptionPane.showMessageDialog(null, "The temperature in Celsius is: " +
                    temperatureConversion.fahrenheitToCelsius(amountDouble) + "C°");
        }
    }

    public static void askToContinue() {
        int input = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Select an Option", JOptionPane.YES_NO_CANCEL_OPTION);

        if (input == 0) {
            moneyOrTemp();
        } else if (input == 1) {
            JOptionPane.showMessageDialog(null, "Program close");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Program close");
            System.exit(0);
        }
    }
}
