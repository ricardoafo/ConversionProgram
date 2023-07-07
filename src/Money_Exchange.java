
public class Money_Exchange {
    private double arsValue = 0.00384109;
    private double usdValue = 1;
    private double eurValue = 1.18;
    private double gbpValue = 1.38;
    private double cnyValue = 0.16;
    private double krwValue = 0.00089;

    public double arsToDolar(double amount) {
        return (amount * arsValue) / usdValue;
    }

    public double arsToEuro(double amount) {
        return amount / eurValue;
    }

    public double arsToLibra(double amount) {
        return amount / gbpValue;
    }

    public double arsToYuan(double amount) {
        return amount / cnyValue;
    }

    public double arsToCoreanWon(double amount) {
        return amount / krwValue;
    }

    public double dolarToArs(double amount) {
        return amount / arsValue;
    }

    public double euroToArs(double amount) {
        return amount / arsValue;
    }

    public double libraToArs(double amount) {
        return amount / arsValue;
    }

    public double yuanToArs(double amount) {
        return amount / arsValue;
    }

    public double coreanWonToArs(double amount) {
        return amount / arsValue;
    }
}
