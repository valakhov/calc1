import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static java.lang.String String;

    public static void main(String[] args){
        calc(String);
    }

    public static String calc(String input) {

        Converter converter = new Converter();
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        String[] splitString = string.split(" ");

        if (splitString.length != 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Пользователь ввел недопустимое количество символов");
            }
        }

        if (converter.isRoman(splitString[0]) == converter.isRoman(splitString[2])) {


            int a, b;
            String c = splitString[1];
            char d = c.charAt(0);
            boolean isRoman = converter.isRoman(splitString[0]);

            if (isRoman) {
                a = converter.romanToInt(splitString[0]);
                b = converter.romanToInt(splitString[2]);
                if (a < 1 || b < 1 || a > 10 || b > 10) {
                    System.out.println("Пользователь ввел некорректные цифры");
                }
            } else {
                a = Integer.parseInt(splitString[0]);
                b = Integer.parseInt(splitString[2]);
                if ((a < 1) || (a > 10) || (b < 1) || (b > 10)) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Пользователь ввел некорректные цифры");
                    }
                }
            }

            int result = 0;
            switch (d) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;
                default:
                    System.out.println("Вы ввели недопустимое значение");
                    break;
            }
            System.out.println(result);
            return input;


        } else {
            System.out.println("Числа должны быть в одном формате");
        }
        return string;
    }
}

class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
    }

    String intToRoman(int number){
        String roman = "";
        int arabianKey;
        do{
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return  roman;
    }

    int romanToInt(String s){
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >=0; i--){
            arabian = romanKeyMap.get(arr[i]);
            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;}
            else {
                result += arabian;
            }
        }
        return result;
    }

    boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }
}