package com.Hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
    private ArrayList<Word> availableWords = new ArrayList<Word>();
    private ArrayList<User> users = new ArrayList<User>();
    private int tries = 0;
    private ArrayList<Word> words = new ArrayList<Word>();
    private String word = "", line = "", newWord = "", temp = "";
    private boolean isAnswered = false, isFirstTry = true; // In development
    Scanner scanner = new Scanner(System.in);

    public void generateAvailableWords() {
        if (words.isEmpty()) {
            System.out.println("There are no available words.");
        } else {
            for (int i = 0; i < words.size(); i++) {
                availableWords.add(words.get(i));
            } // Only call this if words is not empty
        }
    }

    public void generateRandomWord() {
        int index = (int) (Math.random()*availableWords.size());
        word = availableWords.get(index).getWord();

    }

    public void addWord() {
        System.out.println("Type in a word to add:");
        String w = scanner.next();
        System.out.println("Type in a simple description that does not give it away:");
        String d = scanner.next();
        d = scanner.nextLine();
        System.out.println("Type in a fun fact about it:");
        String f = scanner.next();
        f = scanner.nextLine();

        if (checkDuplicate(new Word(w, d, f))) {
            System.out.println("This word already exists.");
        }
        else {
            words.add(new Word(w, d, f));
            System.out.println("Added to words list!");
        }
    }

    public boolean checkDuplicate(Word w) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(w)) {
                return true;
            }
        }
        return false;
    }

    public void removeWord() {
        boolean isRemoved = false;
        System.out.println("Type in the word you would like to remove:");
        String w = scanner.nextLine();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().equals(w)) {
                words.remove(i);
                isRemoved = true;
                System.out.println(w + " has been removed from the words list!");
                i--;
            }
        }
        if (!isRemoved) {
            System.out.println(w + " was not in the words list.");
        }
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
        System.out.println("Commands:");
        System.out.println("1 - Quits the game");
        System.out.println("2 - Gives a hint");
        System.out.println("3 - Reveals the answer");
        System.out.println("Guess a letter of the word: ");
        if (isFirstTry) {
            checkNumChars(word);
            System.out.println(line);
        } else {
            System.out.println(newWord);
        }
        String letter = scanner.next();
        if (letter.equalsIgnoreCase("1")) {
            System.out.println("Game Over!");
        } // In development
        if (letter.equalsIgnoreCase("2")) {
            giveHint(word);
            setGuess();
        }
        if (letter.equalsIgnoreCase("3")) {
            revealAnswer(word);
            giveFunFact(word);
            endMenu();
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
        isFirstTry = false;
        System.out.println(newWord);
        if (isWordSame()) {
            giveFunFact(word);
            endMenu();
        } else {
            setGuess();
        }
    }

    public boolean isWordSame() {
        if (newWord.equals(word)) {
            isAnswered = true;
        }
        return isAnswered;
    }

    public void resetHighScores() {
        for (int i = 0; i < users.size(); i++) {
            users.remove(i);
            i--;
        }
        if (users.isEmpty()) {
            System.out.println("High scores list has been reset!");
        }
    }

    public void startGame() {
        System.out.println("Would you like to put your own words or use existing ones?");
        System.out.println("1. Your own words");
        System.out.println("2. Use existing ones");
        System.out.println("3. Exit to main menu");
        int s = scanner.nextInt();
        switch(s) {
            case 1:
                System.out.println("How many words would you like to add?");
                int num = scanner.nextInt();
                for (int i = 0; i < num; i++) {
                    addWord();
                }
                generateAvailableWords();
                setGuess();
                break;
            
            case 2:
                addDefaultWords();
                generateAvailableWords();
                generateRandomWord();
                setGuess();
                break;

            case 3:
                showMenu();

            default:
                System.out.println("Invalid option. Please enter again.");
                startGame();
        }

    }

    public void addDefaultWords() {
        words.add(new Word("Catastrophe", "That's a lot of damage!", "COVID-19 is Catastrophic!"));
        words.add(new Word("Bespectacled", "An adjective that describes a useful tool that is carried on a person.", "If you have glasses, you are bespectacled!"));
        words.add(new Word("AP Computer Science A", "A class that emphasizes on algorithms", "AP Computer Science A is one of the easiest APs in all of College Board. If you like computers and are good at math, take it when you can!"));
        words.add(new Word("AP Calculus BC", "The highest level of math you can reach in high school.", "The next level of math after AP Calculus BC is Multivariable Calculus!"));
        words.add(new Word("Prospect High School", "A school in Saratoga, California that has colors gold and blue.", "PHS has a very diverse community!"));
    }

    public void giveHint(String word) {
        System.out.print("Hint:");
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().equals(word)) {
                System.out.println(words.get(i).getHint());
            }
        }
    }

    public void giveFunFact(String word) {
        System.out.print("Fun fact: ");
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().equals(word)) {
                System.out.println(words.get(i).getFunFact());
            }
        }
    }

    public void printUserList() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        if (users.isEmpty()) {
            System.out.println("Either no one has played the game yet or high scores have been cleared.\n");
        }
        System.out.println("Would you like to:");
        System.out.println("1. Return to Main Menu");
        System.out.println("2. Reset high scores");
        int choice = scanner.nextInt();
        if (choice == 1) {
            showMenu();
        }
        else if (choice == 2) {
            resetHighScores();
            printUserList();
        }
        else {
            System.out.println("Invalid option. Please enter again.");
            printUserList();
        }
    }

    public void aboutMe() {
        System.out.println("Hi! I am Clement Boiteux. I am currently a sophomore in high school. My most favorite subject is math, but I also enjoy any subject that uses a lot of math. I plan to pursue engineering, programming, or finance/economics in the future.");
        System.out.println("Programming Experience: I took AP Computer Science A in my sophomore year of high school and joined Programming Club. I participated in two hackathons so far and programmed in HTML/CSS for both. I actively program in Java, C++, HTML/CSS, and JavaScript. I have a little bit of experience with Python but didn't learn it in depth.\n");
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
        System.out.println("1. New Game              _____"); // In development
        System.out.println("2. Credits               |   |");
        System.out.println("3. How to Play           o   |");
        System.out.println("4. About the developer  ---  |");
        System.out.println("5. High scores          | |  |");
        System.out.println("6. Exit");
        String choice = scanner.next();

        if (choice.equals("1")) {
            startGame();
        }
        else if (choice.equals("2")) {
            showCredits();
        }
        else if (choice.equals("3")) {
            tutorial();
        }
        else if (choice.equals("4")) {
            aboutMe();
        }
        else if (choice.equals("5")) {
            printUserList();
        }
        else if (choice.equals("6")) {
            System.out.println("Have a nice day! Thank you for playing!");
        }
        else {
            System.out.println("Invalid option. Enter again.");
            showMenu();
        }
    }


    public void endMenu() {
        System.out.println("Good job! You have discovered the secret word! Would you like to: ");
        System.out.println("1. Be featured on the high scores list");
        System.out.println("2. Quit game");
        String choice = scanner.next();
        if (choice.equals("1")) {
            System.out.println("First Name:");
            String name = scanner.next();
            System.out.println("Date completed ([month]/[day]/[year] format)");
            String date = scanner.next();
            users.add(new User(name, date, tries));
        }
        else if (choice.equals("2")) {
            System.out.println("Have a nice day! Thank you for playing!");
        }
    }

    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.showMenu();
    }
}
