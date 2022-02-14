package sample;

public class Operations {
    public double getResult(String var1, String var2, String operator) throws Exception{
        double a = Double.parseDouble(var1);
        double b = Double.parseDouble(var2);
        return switch (operator) {
            case " + " -> sum(a, b);
            case " - " -> subtract(a, b);
            case " * " -> multiply(a, b);
            case " / " -> divide(a, b);
            case " % " -> mod(a, b);
            default -> 0;
        };
    }

    private double sum(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) throws Exception {
        if(b == 0) throw new Exception();
        return a / b;
    }

    private double mod(double a, double b) throws Exception{
        if(b == 0) throw new Exception();
        return a % b;
    }
}
