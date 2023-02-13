package testovoe;

import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

class Calc{

    public static void main(String[] args) throws IOException {

        System.out.println("Введите выражение. Все знаки должны быть разделены пробелом");
        System.out.println("Калькулятор принимает арабские и римские числа от 1 до 10 (I-X)");


        try (Scanner sc = new Scanner(System.in)) {
            String result = new Calculator().calculate(sc.nextLine().trim().split(" "));
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Неверные значения");
        }

    }

}
class Convert {

    //конвертируем римские цифры в арабские

    private static final TreeMap<Integer, String> ARABIC = new TreeMap<>();
    static {
        ARABIC.put(100, "C");
        ARABIC.put(90, "XC");
        ARABIC.put(50, "L");
        ARABIC.put(40, "XL");
        ARABIC.put(10, "X");
        ARABIC.put(9, "IX");
        ARABIC.put(5, "V");
        ARABIC.put(4, "IV");
        ARABIC.put(1, "I");
    }

    private static final Map<String, Integer> ROMAN = new HashMap<>();
    static {
        ROMAN.put("I",1);
        ROMAN.put("II",2);
        ROMAN.put("III",3);
        ROMAN.put("IV",4);
        ROMAN.put("V",5);
        ROMAN.put("VI",6);
        ROMAN.put("VII",7);
        ROMAN.put("VIII",8);
        ROMAN.put("IX",9);
        ROMAN.put("X",10);
    }

    public String toRome(int number) {
        int n = ARABIC.floorKey(number);
        return number == n ? ARABIC.get(number) : ARABIC.get(n) + toRome(number - n);
    }

    public int toArab(String number) {
        return ROMAN.get(number);
    }

}

class Calculator {

    private final Convert converter;


    public Calculator() {
        this.converter = new Convert();
    }

    public String calculate(String [] args) {
        if (args.length!=3) throw new IllegalArgumentException();
        try {
            int result = calculate(Integer.parseInt(args[0]), Integer.parseInt(args[2]), args[1]);
            return String.valueOf(result);

            //римские или арабские

        } catch (NumberFormatException e) {
            int result = calculate (converter.toArab(args[0]), converter.toArab(args[2]), args[1]);
            return converter.toRome(result);
        }
    }

    //работа с оператором и проверка на величину цифр

    private int calculate(int firstNum, int secondNum, String op) {
        if (firstNum < 1 || firstNum > 10 || secondNum < 1 || secondNum > 10) throw new IllegalArgumentException();
        switch (op) {
            case "+": return firstNum + secondNum;
            case "/": return firstNum / secondNum;
            case "*": return firstNum * secondNum;
            case "-": return firstNum - secondNum;
            default: throw new IllegalArgumentException();
        }
    }

}
