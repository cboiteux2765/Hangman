package com.Hangman;

import java.io.*;
import java.util.*;

public class Hangman {
    private ArrayList<String> availableWords = new ArrayList();
    private String word = "", line = "", newWord = "", temp = "";
    private int attempts = 0;
    private boolean isAnswered = false, isFirstTry = true;
    private boolean lost = false;
    Scanner scanner = new Scanner(System.in);
    private BufferedReader br = null;

    public ArrayList<String> generateAvailableWords() {
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\ezrab\\OneDrive\\Desktop\\GitHub\\Hangman\\com\\Hangman\\HangmanWords.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                availableWords.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return availableWords;
    }

    public void generateRandomWord() {
        try {
            int index = (int) (Math.random()*availableWords.size());
            word = availableWords.get(index);
            attempts = word.length() + 5;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void endGame() {
        System.out.println("Have a nice day! Thank you for playing!");
    }

    public void revealAnswer(String word) {
        System.out.println("The word was " + word + "!\n");
    } 

    public void checkNumChars(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i+1).equals(" ")) {
                line += " ";
            }
            else {
                line += "_";
            }
        }
    }

    public void setGuess() {
        while (attempts > 0 && !isAnswered) {
            System.out.println("Commands:");
            System.out.println("1 - Quits the game");
            System.out.println("2 - Reveals the answer");
            System.out.println("Guess a letter of the word: ");
            if (isFirstTry) {
                checkNumChars(word);
                System.out.println(line);
                System.out.println("You have " + attempts + " attempts remaining.");
            } else {
                System.out.println(newWord);
                System.out.println("You have " + attempts + " attempts remaining.");
            }
            String letter = scanner.next();
            if (letter.equalsIgnoreCase("1")) {
                System.out.println("Game Over!");
            }
            if (letter.equalsIgnoreCase("2")) {
                revealAnswer(word);
            }

            for (int index = 0; index < word.length(); index++) {
                if (isFirstTry) {
                    if (letter.equalsIgnoreCase(word.substring(index, index + 1))) {
                        newWord += word.charAt(index);
                    } else if (word.substring(index, index + 1).equals(" ")) {
                        newWord += " ";
                    } else {
                        newWord += "_";
                    }
                    temp = newWord;
                } else {
                    newWord = "";
                    for (int i = 0; i < word.length(); i++) {
                        if (letter.equalsIgnoreCase(word.substring(i, i+1))) {
                            newWord += word.substring(i, i+1);
                        } else {
                            newWord += temp.substring(i, i+1);
                        }
                    }
                    temp = newWord;
                }
            }
            attempts--;
            isFirstTry = false;
            System.out.println(newWord);
            if (isWordSame()) {
                System.out.println("Congrats! You win!");
                endGame();
                isAnswered = true;
            } else if (attempts == 0) {
                lost = true;
                System.out.println("You lose!");
                revealAnswer(word);
            } else {
                setGuess();
            }
        }

    }

    public boolean isWordSame() {
        if (newWord.equals(word)) {
            isAnswered = true;
        }
        return isAnswered;
    }

    public void startGame() {
        generateAvailableWords();
        generateRandomWord();
        setGuess();
    }

    public void aboutMe() {
        System.out.println("Hi! I am Clement Boiteux. I am currently a sophomore in high school. My most favorite subject is math, but I also enjoy any subject that uses a lot of math. I plan to pursue engineering, programming, or finance/economics in the future.");
        System.out.println("Programming Experience: I took AP Computer Science A in my sophomore year of high school and joined Programming Club. I participated in two hackathons so far and programmed in HTML/CSS for both. I actively program in Java, C#, and JavaScript. I have a little bit of experience with C++ and Kotlin\n");
        redirect();
    }

    public void showCredits() {
        System.out.println("Developed by: Clement Boiteux in 2021");
        System.out.println("Language: Java");
        System.out.println("Inspired by Hangman");
        redirect();
    }

    public void tutorial() {
        System.out.println("Hangman is a game where a player comes up with a secret word or phrase and another player has to guess it.");
        System.out.println("The guessing player can only guess one letter at a time, and if the word/phrase contains the letter, the correctly guessed letters are filled in, thus revealing the word more.");
        System.out.println("However, if the player guesses a letter wrong, a part of the hangman is drawn. The more it is drawn, the less chances a player has. Wrong letters are noted down.");
        System.out.println("If the player has lost all their chances and guessed a letter wrong, they lose and thus the word is revealed.");
        System.out.println("However, if a player believes there are enough letters in the word/phrase that can be guessed, they can go for it, but a wrong guess will not cost the player anything.");
        redirect();
    }

    public void redirect() {
        System.out.println("Type and enter any key to return main menu.");
        scanner.next();
        showMenu();
    }

    public void showMenu() {
        System.out.println("Welcome to Clement's Hangman Game!");
        System.out.println("1. New Game              _____");
        System.out.println("2. Credits               |   |");
        System.out.println("3. How to Play           o   |");
        System.out.println("4. About the developer  ---  |");
        System.out.println("5. Exit                 / \\  |");
        int choice = scanner.nextInt();

        if (choice == 1) {
            startGame();
        }
        else if (choice == 2) {
            showCredits();
        }
        else if (choice == 3) {
            tutorial();
        }
        else if (choice == 4) {
            aboutMe();
        }
        else if (choice == 5) {
            endGame();
        }
        else {
            System.out.println("Invalid option. Enter again.");
            showMenu();
        }
    }


    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.showMenu();
    }
}
