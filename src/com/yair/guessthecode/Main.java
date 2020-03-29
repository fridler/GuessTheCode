package com.yair.guessthecode;

import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int computerNumber = generateComputerNumber();
        int numbersNotExist = getNumbersNotExist(computerNumber);
        int oneNumberCorrectInRightLocation1 = getOneNumberCorrectInRightLocation(computerNumber);
        int oneNumberCorrectInRightLocation2 = getOneNumberCorrectInRightLocation(computerNumber);
        while (oneNumberCorrectInRightLocation1 == oneNumberCorrectInRightLocation2){
            oneNumberCorrectInRightLocation2 = getOneNumberCorrectInRightLocation(computerNumber);
        }
        int oneNumberCorrectAtWrongLocation = getOneNumberCorrectAtWrongLocation(computerNumber);
        int twoNumberCorrectAtWrongLocation = getTwoNumberCorrectAtWrongLocation(computerNumber);

        System.out.println("The number generated: " + computerNumber);

        boolean isUserWon = false;
        int attemptAmount = 0;

        while (!isUserWon) {
            String text = JOptionPane.showInputDialog(null, "Please try to guess the code:" +
                    "\nOnly one number is correct and in the right location: " + oneNumberCorrectInRightLocation1 +
                    "\nOnly one number is correct and in wrong location: " + oneNumberCorrectAtWrongLocation +
                    "\nOnly two number is correct and in wrong location: " + twoNumberCorrectAtWrongLocation +
                    "\nNumbers are not Exist: " + numbersNotExist +
                    "\nOnly one number is correct and in the right location: " + oneNumberCorrectInRightLocation2,
                    "Created by Fridler", JOptionPane.QUESTION_MESSAGE);

            int userGuess = Integer.parseInt(text);
            if (userGuess == computerNumber) {
                isUserWon = true;
            }
            attemptAmount++;
        }

        JOptionPane.showMessageDialog(null,"You succeeded after " + attemptAmount + " attempts","Created by Fridler", JOptionPane.INFORMATION_MESSAGE);

        // test
        System.out.println("The number generated: " + computerNumber);
        System.out.println("No number exists: " + numbersNotExist);
        System.out.println("One number correct and in the right location: " + oneNumberCorrectInRightLocation1);
        System.out.println("One number correct and in the right location: " + oneNumberCorrectInRightLocation2);
        System.out.println("One number correct and wrong location: " + oneNumberCorrectAtWrongLocation);
        System.out.println("Two number correct and wrong location: " + twoNumberCorrectAtWrongLocation);
    }

    private static int getTwoNumberCorrectAtWrongLocation(int computerNumber) {
        int twoLocationWrong = 0;
        while (twoLocationWrong != 2) {

            twoLocationWrong = 0;
            int twoNumberCorrectWrongLocation = generateComputerNumber();
            int tempComputerNumber = computerNumber;


            int[] twoNumberCorrectWrongLocationArray = new int[3];
            int[] tempComputerNumberArray = new int[3];

            for (int i = 0; i < twoNumberCorrectWrongLocationArray.length; i++) {
                int digit = twoNumberCorrectWrongLocation % 10;
                twoNumberCorrectWrongLocationArray[i] = digit;
                twoNumberCorrectWrongLocation /= 10;
            }

            for (int i = 0; i < tempComputerNumberArray.length; i++) {
                int digit = tempComputerNumber % 10;
                tempComputerNumberArray[i] = digit;
                tempComputerNumber /= 10;
            }

            for (int i = 0; i < twoNumberCorrectWrongLocationArray.length; i++) {
                for (int j = 0; j < tempComputerNumberArray.length; j++) {
                    if (twoNumberCorrectWrongLocationArray[i] == tempComputerNumberArray[j] && i != j) {
                        twoLocationWrong++;
                    }
                }

            }
            if (twoLocationWrong == 2) {
                for (int i = twoNumberCorrectWrongLocationArray.length; i > 0; i--) {
                    int digit = twoNumberCorrectWrongLocationArray[i - 1];
                    twoNumberCorrectWrongLocation = twoNumberCorrectWrongLocation * 10 + digit;
                }
                return twoNumberCorrectWrongLocation;
            }
        }
        return 0;
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
