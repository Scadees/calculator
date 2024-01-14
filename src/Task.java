import java.util.Scanner;


public class Task {

    public static void main(String[] args) throws Exception {
        String initialLine;
        String initialLine1;
        Scanner keyboard = new Scanner(System.in);

        initialLine1 = keyboard.nextLine(); // input с клавиатуры
        initialLine = initialLine1.toUpperCase();
        String[] splitLine = initialLine.split(" "); // разделение строки на массив

        if (splitLine.length > 3) {  // ошибка с завершением программы, если длина массива > 3
            throw new Exception("//т.к. формат математической операции " +
                    "не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        } else if (splitLine.length < 3) {  // ошибка с завершением программы, если длина массива < 3
            throw new Exception("throws Exception //т.к. строка не является математической операцией");

        } else if ((splitLine[0].contains(",") || (splitLine[0].contains(".")) || // ошибка с завершением программы,
                // если какое-либо из чисел не целое
                splitLine[2].contains(",")) || (splitLine[2].contains("."))) {
            throw new Exception("//вводите только целые числа");
        }

        Calculator task = new Calculator();
        Roman nums = new Roman();
        int result = 0;
        int firstNumber;
        int secondNumber;

        if (!nums.iSRoman(splitLine[0]) && nums.iSRoman(splitLine[2]) || // ошибка с завершение программы,
                // если используются разные системы счисления
                !nums.iSRoman(splitLine[2]) && nums.iSRoman(splitLine[0])) {
            throw new Exception("//т.к. используются одновременно разные системы счисления");
        }

        if (nums.iSRoman(splitLine[0])) { // преобразование римского числа в арабское
            firstNumber = task.romanToArabic(splitLine[0]);
            secondNumber = task.romanToArabic(splitLine[2]);
        } else {
            firstNumber = Integer.parseInt(splitLine[0]);
            secondNumber = Integer.parseInt(splitLine[2]);
        }

        if (firstNumber < 0 || firstNumber > 10 || secondNumber > 10){ // ошибка с завершением программы,
            // если какое-либо из чисел < 1 или > 10
            throw new Exception("//т.к. значения не могут быть меньше 1 или больше 10");
        }

        switch (splitLine[1]) {

            case "+":
                result = task.add(firstNumber, secondNumber);
                break;

            case "-":
                if (nums.iSRoman(splitLine[0])) {
                    if (secondNumber > firstNumber) { // ошибка с завершением программы, если
                        // римское число отрицательное
                        throw new Exception("//т.к. в римской системе нет отрицательных чисел");
                    }
                } else {
                    result = task.subtraction(firstNumber, secondNumber);
                    break;
                }

            case "*":
                result = task.multiplication(firstNumber, secondNumber);
                break;

            case "/":
                result = task.division(firstNumber, secondNumber);
                break;
        }

        if (nums.iSRoman(splitLine[0])) { // вывод результата
            if (result == 0) {
                System.out.println(result);
            } else {
                System.out.println(task.arabicToRoman(result));
            }
        } else {
            System.out.println(result);
        }
    }
}


class Roman { // проверка числа на принадлежность к римским цифрам
    static String[] romanNums = new String[]{"0", "I", "V", "X", "L", "C", "D", "M"};

    boolean iSRoman (String userInput) {

        for (int i = 0; i < romanNums.length; i++) {

            if (userInput.length() > 1) {
                String[] userSplitLine = userInput.split("");
                if (userSplitLine[0].equals(romanNums[i])) {
                    return true;
                }
            }

            if (userInput.equals(romanNums[i])) {
                return true;
                    }
                }

        return false;
    }
}

class Calculator {  // метод для вычисления значений
    int firstNumber;
    int secondNumber;

    int add(int userNumber1, int userNumber2) {
        firstNumber = userNumber1;
        secondNumber = userNumber2;
        return firstNumber + secondNumber;
    }

    int subtraction(int userNumber1, int userNumber2) {
        firstNumber = userNumber1;
        secondNumber = userNumber2;
        return firstNumber - secondNumber;
    }

    int multiplication(int userNumber1, int userNumber2) {
        firstNumber = userNumber1;
        secondNumber = userNumber2;
        return firstNumber * secondNumber;
    }

    int division(int userNumber1, int userNumber2) {
        firstNumber = userNumber1;
        secondNumber = userNumber2;
        return firstNumber / secondNumber;
    }

    int romanToArabic(String userNumber) { // преобразование римского числа в арабское
        int convertedNumber = 0;
        int[] arabic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String romanNumber = userNumber;

        for (int i = 0; i < arabic.length; i++ ) {
            while (romanNumber.indexOf(roman[i]) == 0) {
                convertedNumber += arabic[i];
                romanNumber = romanNumber.substring(roman[i].length());
            }
        }
        return convertedNumber;
    }

    String arabicToRoman(int resultNumber){ // преобразование арабского числа в римское
//        String convertedNumber = ""; - методом конкатенации
        StringBuilder convertedNumber = new StringBuilder();
        int[] arabic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int arabicNumber = resultNumber;

        for (int i = 0; i < roman.length; i++) {

            while (arabicNumber >= arabic[i]) {
                int x = arabicNumber / arabic[i];
                arabicNumber = arabicNumber % arabic[i];

                for (int k = 0; k < x; k++){
//                    convertedNumber += roman[i]; - конкатенация
                    convertedNumber.append(roman[i]);
                }
            resultNumber++;
            }

        }
        return convertedNumber.toString();
    }
}
