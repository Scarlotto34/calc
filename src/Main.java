import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String userInput = scan.nextLine();
        String result = null;
        try {
            result = calc(userInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }
    public static String calc(String input) throws IOException {
        Converter convert = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(input.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        if(actionIndex==-1){
            throw new IOException("Принимаются только +, -, *, /");
        }
        String[] xSplit = input.split(regexActions[actionIndex]);
        if (xSplit.length != 2){
            throw new IOException("Необходимы 2 цифры и одно действие");
        }
        String[] data = input.split(regexActions[actionIndex]);
        if(convert.isRoman(data[0]) == convert.isRoman(data[1])){
            int a,b;
            boolean isRoman = convert.isRoman(data[0]);
            if(isRoman){
                a = convert.toArabic(data[0]);
                b = convert.toArabic(data[1]);
            }else{
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            if(isRoman){
                input = (convert.toRoman(result));
            }
            else{
                input = String.valueOf(result);
            }
        }else{
            throw new IOException("Числа должны быть в одном формате");
        }
        return input;
    }
}