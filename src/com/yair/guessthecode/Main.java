package com.yair.guessthecode;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int computerNumber = generateComputerNumber();
        int numbersNotExist = getNumbersNotExist(computerNumber);
        int oneNumberCorrectInRightLocation = getOneNumberCorrectInRightLocation(computerNumber);
        int oneNumberCorrectAtWrongLocation = getOneNumberCorrectAtWrongLocation(computerNumber);

        // test
        System.out.println("The number generated: " + computerNumber);
        System.out.println("No number exists: " + numbersNotExist);
        System.out.println("One number correct and in the right location: " + oneNumberCorrectInRightLocation);
        System.out.println("One number correct and wrong location: " + oneNumberCorrectAtWrongLocation);
    }

    private static int getOneNumberCorrectAtWrongLocation(int computerNumber) {
        int oneLocationWrong = 0;
        while (oneLocationWrong != 1) {

            oneLocationWrong = 0;
            int oneNumberCorrectWrongLocation = generateComputerNumber();
            int tempComputerNumber = computerNumber;


            int[] oneNumberCorrectWrongLocationArray = new int[3];
            int[] tempComputerNumberArray = new int[3];

            for (int i = 0; i < oneNumberCorrectWrongLocationArray.length; i++) {
                int digit = oneNumberCorrectWrongLocation % 10;
                oneNumberCorrectWrongLocationArray[i] = digit;
                oneNumberCorrectWrongLocation /= 10;
            }

            for (int i = 0; i < tempComputerNumberArray.length; i++) {
                int digit = tempComputerNumber % 10;
                tempComputerNumberArray[i] = digit;
                tempComputerNumber /= 10;
            }

            for (int i = 0; i < oneNumberCorrectWrongLocationArray.length; i++) {
                for (int j = 0; j < tempComputerNumberArray.length; j++) {
                    if (oneNumberCorrectWrongLocationArray[i] == tempComputerNumberArray[j] && i != j) {
                        oneLocationWrong++;
                    }
                }

            }
            if (oneLocationWrong == 1) {
                for (int i = oneNumberCorrectWrongLocationArray.length; i > 0; i--) {
                    int digit = oneNumberCorrectWrongLocationArray[i - 1];
                    oneNumberCorrectWrongLocation = oneNumberCorrectWrongLocation * 10 + digit;
                }
                return oneNumberCorrectWrongLocation;
            }
        }
        return 0;
    }

    private static int getOneNumberCorrectInRightLocation(int computerNumber) {

        int oneLocationCorrect = 0;
        while (oneLocationCorrect != 1) {

            oneLocationCorrect = 0;
            int oneNumberCorrectInRightLocation = generateComputerNumber();
            int tempComputerNumber = computerNumber;


            int[] oneNumberCorrectInRightLocationArray = new int[3];
            int[] tempComputerNumberArray = new int[3];

            for (int i = 0; i < oneNumberCorrectInRightLocationArray.length; i++) {
                int digit = oneNumberCorrectInRightLocation % 10;
                oneNumberCorrectInRightLocationArray[i] = digit;
                oneNumberCorrectInRightLocation /= 10;
            }

            for (int i = 0; i < tempComputerNumberArray.length; i++) {
                int digit = tempComputerNumber % 10;
                tempComputerNumberArray[i] = digit;
                tempComputerNumber /= 10;
            }

            for (int i = 0; i < oneNumberCorrectInRightLocationArray.length; i++) {
                if (oneNumberCorrectInRightLocationArray[i] == tempComputerNumberArray[i]) {
                    oneLocationCorrect++;
                }
            }
            if (oneLocationCorrect == 1) {
                for (int i = oneNumberCorrectInRightLocationArray.length; i > 0; i--) {
                    int digit = oneNumberCorrectInRightLocationArray[i - 1];
                    oneNumberCorrectInRightLocation = oneNumberCorrectInRightLocation * 10 + digit;
                }
                return oneNumberCorrectInRightLocation;
            }
        }
        return 0;
    }

    private static int getNumbersNotExist(int computerNumber) {

        boolean[] digits = new boolean[10];

        while (computerNumber != 0) {
            int digit = computerNumber % 10;
            digits[digit] = true;
            computerNumber /= 10;
        }

        int numbersNotExist = 0;
        Random random = new Random();
        int randomNumber = random.nextInt(10);

        while (numbersNotExist < 100) {
            if (!digits[randomNumber]) {
                numbersNotExist = numbersNotExist * 10 + randomNumber;
                digits[randomNumber] = true;
            }
            randomNumber = random.nextInt(10);

        }

        return numbersNotExist;
    }

    private static int generateComputerNumber() {
        Random random = new Random();
        int computerNumber = random.nextInt(900) + 100;

        while (isDigitExistsTwice(computerNumber)) {
            computerNumber = random.nextInt(900) + 100;
        }

        return computerNumber;
    }

    private static boolean isDigitExistsTwice(int computerNumber) {

        boolean[] digits = new boolean[10];

        while (computerNumber != 0) {

            int digit = computerNumber % 10;

            if (digits[digit]) {
                return true;
            }
            digits[digit] = true;
            computerNumber /= 10;
        }

        return false;
    }
}
