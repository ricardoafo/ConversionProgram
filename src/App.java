import javax.swing.*;

public class App {
    static Money_Exchange moneyExchange = new Money_Exchange();
    public static void main(String[] args) {

        // Create a showInputDialog() method to show two options for the user to choose from a dropdown list.
        String[] options = {"Money Exchange", "Temperature Conversion"};
        String input = (String) JOptionPane.showInputDialog(null, "Select a conversion type",
                "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // Create an if statement to check if the user has selected the first option.
        if (input.equals("Money Exchange")) {

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

                    amount = JOptionPane.showInputDialog("Enter the amount of money to be converted, you have " +
                            (tries - counter) + " tries left");
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

            // User selects the conversion type
            String[] conversionOptions = {"Argentinian Pesos to Dolar", "Argentinian Pesos to Euro",
                    "Argentinian Pesos to Libra", "Argentinian Pesos to Yuan", "Argentinian Pesos to Corean Won",
                    "Dolar to Argentinian Pesos", "Euro to Argentinian Pesos", "Libra to Argentinian Pesos",
                    "Yuan to Argentinian Pesos", "Corean Won to Argentinian Pesos"};
            String conversionType = (String) JOptionPane.showInputDialog(null, "Select a conversion type",
                    "Menu", JOptionPane.PLAIN_MESSAGE, null, conversionOptions, conversionOptions[0]);

            switch (conversionType) {
                case "Argentinian Pesos to Dolar" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Dolar is: " +
                            moneyExchange.arsToDolar(amountDouble));
                    break;
                case "Argentinian Pesos to Euro" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Euro is: " +
                            moneyExchange.arsToEuro(amountDouble));
                    break;
                case "Argentinian Pesos to Libra" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Libra is: " +
                            moneyExchange.arsToLibra(amountDouble));
                    break;
                case "Argentinian Pesos to Yuan" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Yuan is: " +
                            moneyExchange.arsToYuan(amountDouble));
                    break;
                case "Argentinian Pesos to Corean Won" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Corean Won is: " +
                            moneyExchange.arsToCoreanWon(amountDouble));
                    break;
                case "Dolar to Argentinian Pesos" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Argentinian Pesos is: " +
                            moneyExchange.dolarToArs(amountDouble));
                    break;
                case "Euro to Argentinian Pesos" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Argentinian Pesos is: " +
                            moneyExchange.euroToArs(amountDouble));
                    break;
                case "Libra to Argentinian Pesos" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Argentinian Pesos is: " +
                            moneyExchange.libraToArs(amountDouble));
                    break;
                case "Yuan to Argentinian Pesos" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Argentinian Pesos is: " +
                            moneyExchange.yuanToArs(amountDouble));
                    break;
                    case "Corean Won to Argentinian Pesos" :
                    JOptionPane.showMessageDialog(null, "The amount of money in Argentinian Pesos is: " +
                            moneyExchange.coreanWonToArs(amountDouble));
                default:
                    throw new IllegalStateException("Unexpected value: " + conversionType);
            }
        }
    }
}
