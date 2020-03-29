package com.yair.guessthecode;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int computerNumber = generateComputerNumber();
        int numbersNotExist = getNumbersNotExist(computerNumber);
        int oneNumberCorrectInRightLocation = getOneNumberCorrectInRightLocation(computerNumber);

        // test
        System.out.println(computerNumber);
        System.out.println(numbersNotExist);
        System.out.println(oneNumberCorrectInRightLocation);
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


//        int tempNum = computerNumber;
//        int[] digitsTempNum = new int[10];
//
//        while (tempNum != 0) {
//            int digit = tempNum % 10;
//            digitsTempNum[digit]++;
//            tempNum /= 10;
//        }
//
//        int[] digitsOneNumberCorrectInRightLocation = new int[10];
//
//        while (oneNumberCorrectInRightLocation != 0) {
//            int digit = oneNumberCorrectInRightLocation % 10;
//            digitsOneNumberCorrectInRightLocation[digit]++;
//            oneNumberCorrectInRightLocation /= 10;
//        }
//        int checkLocation = 0;
//        while (checkLocation != 1) {
//            for (int i = 0; i < digitsTempNum.length; i++) {
//                if (digitsTempNum[i] == digitsOneNumberCorrectInRightLocation[i]) {
//                    checkLocation++;
//                }
//            }
//            if (checkLocation != 1) {
//
//            }
//        }
//
//        return oneNumberCorrectInRightLocation;
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
            if (digits[randomNumber] == false) {
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

            if (digits[digit] == true) {
                return true;
            }
            digits[digit] = true;
            computerNumber /= 10;
        }

        return false;
    }
}
