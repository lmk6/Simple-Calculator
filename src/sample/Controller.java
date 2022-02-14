package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;
    public Button zero;

    public Button mod;
    public Button div;
    public Button mul;
    public Button neg;
    public Button eq;
    public Button sub;
    public Button sum;
    public Button dot;
    public Label result;

    public Button C;

    private boolean operatorEntered = false;
    private String var_1, var_2 = "", operator;
    Operations operations = new Operations();

    public static boolean onlyZero(String str) {
        String regex = "0";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public void getOne() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("1");
        else {
            res += "1";
            result.setText(res);
        }
    }

    public void getTwo() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("2");
        else {
            res += "2";
            result.setText(res);
        }
    }

    public void getThree() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("3");
        else {
            res += "3";
            result.setText(res);
        }
    }

    public void getFour() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("4");
        else {
            res += "4";
            result.setText(res);
        }
    }

    public void getFive() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("5");
        else {
            res += "5";
            result.setText(res);
        }
    }

    public void getSix() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("6");
        else {
            res += "6";
            result.setText(res);
        }
    }

    public void getSeven() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("7");
        else {
            res += "7";
            result.setText(res);
        }
    }

    public void getEight() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("8");
        else {
            res += "8";
            result.setText(res);
        }
    }

    public void getNine() {
        String res = result.getText();
        if (onlyZero(result.getText())) result.setText("9");
        else {
            res += "9";
            result.setText(res);
        }
    }

    public void getZero() {
        String res = result.getText();
        if (!onlyZero(result.getText())) {
            res += "0";
            result.setText(res);
        }
    }

    public void getDot() {
        String res = result.getText();
        if (!operatorEntered) {
            if (!res.isEmpty() && !res.contains(".")) res += ".";
        } else {
            if (!res.replace(var_1 + operator, "").isEmpty() &&
                    !res.replace(var_1 + operator, "").contains(".")) {
                res += ".";
            }
        }
        result.setText(res);
    }

    public void getSum() {
        result.setText(addOperator(" + "));
    }

    public void getSub() {
        result.setText(addOperator(" - "));
    }

    public void getMul() {
        result.setText(addOperator(" * "));
    }

    public void getDiv() {
        result.setText(addOperator(" / "));
    }

    public void getMod() {
        result.setText(addOperator(" % "));
    }

    public void clear() {
        operatorEntered = false;
        result.setText("0");
    }

    public void getResult() {
        result.setText(findResult());
    }

    public void negate() {
        result.setText(getNegative());
    }

    private String findResult() {
        String res = result.getText();
        if (!operatorEntered) {
            return res;
        } else {
            var_2 = res.replace(var_1 + operator, "");
            if (!var_2.isEmpty()) {
                double comp_res = 0;
                try {
                    comp_res =operations.getResult(var_1, var_2, operator);
                } catch (Exception e) {
                    try {
                        openPopUp();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                var_1 = String.valueOf(comp_res);
                if (var_1.split("\\.")[1].compareTo("0") == 0) var_1 = var_1.split("\\.")[0];
            }
            res = var_1;
        }
        operatorEntered = false;
        return res;
    }

    private String addOperator(String op) {
        String res = result.getText();
        if (!operatorEntered) {
            var_1 = res;
            operatorEntered = true;
            operator = op;
            res = var_1;
            res += op;
        } else {
            var_2 = res.replace(var_1 + operator, "");
            if (!var_2.isEmpty()) {
                double comp_res = 0;
                try {
                    comp_res = operations.getResult(var_1, var_2, operator);
                } catch (Exception e) {
                    try {
                        openPopUp();
                    } catch (IOException ioe) {
                        e.printStackTrace();
                    }
                }
                var_1 = String.valueOf(comp_res);
                if (var_1.split("\\.")[1].compareTo("0") == 0) var_1 = var_1.split("\\.")[0];
                res = var_1;
                res += op;
                operator = op;
            }
        }
        return res;
    }

    private String getNegative() {
        String res = result.getText();
        if (!operatorEntered) {
            if (res.compareTo("0") == 0 || res.compareTo("-0") == 0) return "0";
            if (res.contains("-")) var_1 = res.substring(1);
            else var_1 = "-" + res;
            res = var_1;
        } else {
            var_2 = res.replace(var_1 + operator, "");
            if (!var_2.isEmpty()) {
                if (var_2.compareTo("0") == 0 || var_2.compareTo("-0") == 0) return var_1 + operator + "0";
                if (var_2.contains("-")) var_2 = var_2.substring(1);
                else var_2 = "-" + var_2;
                res = var_1 + operator + var_2;
            }
        }
        return res;
    }

    private void openPopUp() throws IOException {
        Stage popUp = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("popup.fxml")));
        popUp.initStyle(StageStyle.UNDECORATED);
        popUp.setResizable(false);
        popUp.setScene(new Scene(root, 298, 113));
        popUp.show();
        result.setText("0");
    }
}
