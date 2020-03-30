package com.yair.guessthecode;

import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int computerNumber = generateComputerNumber();
        int numbersAreNotExist = getNumbersAreNotExist(computerNumber);
        int oneNumberAndPositionAreCorrect1 = getNumberAndPositionAreCorrect(computerNumber, 1);
        int oneNumberAndPositionAreCorrect2 = getNumberAndPositionAreCorrect(computerNumber, 1);
        while (oneNumberAndPositionAreCorrect1 == oneNumberAndPositionAreCorrect2) {
            oneNumberAndPositionAreCorrect2 = getNumberAndPositionAreCorrect(computerNumber, 1);
        }
        int oneNumberIsCorrectAndLocationIncorrect = getNumberIsCorrectAndLocationIncorrect(computerNumber, 1);
        int twoNumberIsCorrectAndLocationIncorrect1 = getNumberIsCorrectAndLocationIncorrect(computerNumber, 2);
        int twoNumberIsCorrectAndLocationIncorrect2 = getNumberIsCorrectAndLocationIncorrect(computerNumber, 2);
        int twoNumberAndPositionAreCorrect = getNumberAndPositionAreCorrect(computerNumber, 2);

        System.out.println("The number generated: " + computerNumber);

        boolean isUserWon = false;
        int attemptAmount = 0;
        int difficultLevel = getDifficultLevel();

        while (!isUserWon) {


            String text = "Please try to guess the code:" +
                    "\n\nOnly one number and position are correct: " + oneNumberAndPositionAreCorrect1 +
                    "\nOnly one number is correct and location incorrect: " + oneNumberIsCorrectAndLocationIncorrect +
                    "\nOnly two number is correct and location incorrect: " + twoNumberIsCorrectAndLocationIncorrect1 +
                    "\nNumbers are not Exists: " + numbersAreNotExist +
                    "\nOnly one number and position are correct: " + oneNumberAndPositionAreCorrect2 +
                    "\nOnly one number is correct and location incorrect: " + oneNumberIsCorrectAndLocationIncorrect;

            if (difficultLevel == 1) {
                text = text + "\nOnly two number and position are correct: " + twoNumberAndPositionAreCorrect +
                        "\nOnly two number is correct and location incorrect: " + twoNumberIsCorrectAndLocationIncorrect2;
            } else if (difficultLevel == 2) {
                text = text + "\nOnly two number is correct and location incorrect: " + twoNumberAndPositionAreCorrect;
            }

            String str = JOptionPane.showInputDialog(null, text,
                    "Created by Fridler", JOptionPane.QUESTION_MESSAGE);

            int userGuess = Integer.parseInt(str);

            if (userGuess == computerNumber) {
                isUserWon = true;
            }
            attemptAmount++;
        }

        JOptionPane.showMessageDialog(null, "You succeeded after " + attemptAmount + " attempts", "Created by Fridler", JOptionPane.INFORMATION_MESSAGE);

        // test
        System.out.println("The number generated: " + computerNumber);
        System.out.println("No number exists: " + numbersAreNotExist);
        System.out.println("Only one number correct and in the right location: " + oneNumberAndPositionAreCorrect1);
        System.out.println("Only one number correct and in the right location: " + oneNumberAndPositionAreCorrect2);
        System.out.println("Only one number correct and wrong location: " + oneNumberIsCorrectAndLocationIncorrect);
        System.out.println("Only two number correct and wrong location: " + twoNumberIsCorrectAndLocationIncorrect1);
    }

    private static int getDifficultLevel() {

        String strDifficultChoice = JOptionPane.showInputDialog("Please choose a difficulty level:\n\n" +
                "1. Easy \n" +
                "2. Medium \n" +
                "3. Hard");

        int difficultChoice = Integer.parseInt(strDifficultChoice);

        while (difficultChoice < 1 || difficultChoice > 3) {

            JOptionPane.showMessageDialog(null, "Invalid number entered ! ! !\n\n"
                    + "Your Choice must be between 1 and 3");

            strDifficultChoice = JOptionPane.showInputDialog("Please choose a difficulty level:\n\n" +
                    "1. Easy \n" +
                    "2. Medium \n" +
                    "3. Hard");

            difficultChoice = Integer.parseInt(strDifficultChoice);
        }
        return difficultChoice;
    }

    private static int getNumberIsCorrectAndLocationIncorrect(int computerNumber,
                                                              int amountOfNumberIsCorrectAndLocationIncorrect) {

        int locationWrong = 0;
        int locationCorrect = 0;

        while (locationWrong != amountOfNumberIsCorrectAndLocationIncorrect || locationCorrect > 0) {

            locationWrong = 0;
            locationCorrect = 0;

            int newNumber = generateComputerNumber();
            int tempComputerNumber = computerNumber;

            int[] newNumberArray = new int[3];
            int[] tempComputerNumberArray = new int[3];

            for (int i = 0; i < newNumberArray.length; i++) {
                int digit = newNumber % 10;
                newNumberArray[i] = digit;
                newNumber /= 10;
            }

            for (int i = 0; i < tempComputerNumberArray.length; i++) {
                int digit = tempComputerNumber % 10;
                tempComputerNumberArray[i] = digit;
                tempComputerNumber /= 10;
            }

            for (int i = 0; i < newNumberArray.length; i++) {
                for (int j = 0; j < tempComputerNumberArray.length; j++) {
                    if (newNumberArray[i] == tempComputerNumberArray[j] && i != j) {
                        locationWrong++;
                    }
                }

            }

            for (int i = 0; i < newNumberArray.length; i++) {
                if (newNumberArray[i] == tempComputerNumberArray[i]) {
                    locationCorrect++;
                }
            }
            if (locationWrong == amountOfNumberIsCorrectAndLocationIncorrect && locationCorrect == 0) {
                for (int i = newNumberArray.length; i > 0; i--) {
                    int digit = newNumberArray[i - 1];
                    newNumber = newNumber * 10 + digit;
                }
                return newNumber;
            }
        }
        return 0;
    }

    private static int getNumberAndPositionAreCorrect(int computerNumber,
                                                      int amountOfNumbersAndPositionsAreCorrect) {

        int locationCorrect = 0;
        int numbersOverlapInBothArrays = 0;

        while (locationCorrect != amountOfNumbersAndPositionsAreCorrect || locationCorrect != numbersOverlapInBothArrays) {

            locationCorrect = 0;
            numbersOverlapInBothArrays = 0;

            int newNumber = generateComputerNumber();
            int tempComputerNumber = computerNumber;

            int[] newNumberArray = new int[3];
            int[] tempComputerNumberArray = new int[3];

            for (int i = 0; i < newNumberArray.length; i++) {
                int digit = newNumber % 10;
                newNumberArray[i] = digit;
                newNumber /= 10;
            }

            for (int i = 0; i < tempComputerNumberArray.length; i++) {
                int digit = tempComputerNumber % 10;
                tempComputerNumberArray[i] = digit;
                tempComputerNumber /= 10;
            }

            for (int i = 0; i < newNumberArray.length; i++) {
                if (newNumberArray[i] == tempComputerNumberArray[i]) {
                    locationCorrect++;
                }
            }

            for (int i = 0; i < newNumberArray.length; i++) {
                if (newNumberArray[i] == tempComputerNumberArray[i]) {
                    numbersOverlapInBothArrays++;
                }
            }

            if (locationCorrect == amountOfNumbersAndPositionsAreCorrect && locationCorrect == numbersOverlapInBothArrays) {
                for (int i = newNumberArray.length; i > 0; i--) {
                    int digit = newNumberArray[i - 1];
                    newNumber = newNumber * 10 + digit;
                }
                return newNumber;
            }
        }
        return 0;
    }


    private static int getNumbersAreNotExist(int computerNumber) {

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

        while (isDigitsExistsTwice(computerNumber)) {
            computerNumber = random.nextInt(900) + 100;
        }

        return computerNumber;
    }

    private static boolean isDigitsExistsTwice(int computerNumber) {

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
