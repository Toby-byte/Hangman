import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hangman_logic {

    static void hangmanDrawingsInitialize(int count) {
        if (count==1) {
            System.out.println(
                    "  _____\n" +
                            " | * * |\n" +
                            " |  _  |\n" +
                            "  \\___/\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "     ");
        }else if (count==2) {
            System.out.println(
                    "  _____\n" +
                            " | * * |\n" +
                            " |  _  |\n" +
                            "  \\___/\n" +
                            "    |\n" +
                            "    |\n" +
                            "    |\n" +
                            "\n" +
                            "     ");
        }else if (count==3) {
            System.out.println(
                    "  _____\n" +
                            " | * * |\n" +
                            " |  _  |\n" +
                            "  \\___/\n" +
                            "    |\n" +
                            "    |\n" +
                            "    |\n" +
                            "   /\n" +
                            "  /    ");
        }else if (count==4) {
            System.out.println(
                    "  _____\n" +
                            " | * * |\n" +
                            " |  _  |\n" +
                            "  \\___/\n" +
                            "    |\n" +
                            "    |\n" +
                            "    |\n" +
                            "   / \\\n" +
                            "  /   \\");
        }else if (count==5) {
            System.out.println(
                    "  _____\n" +
                            " | * * |\n" +
                            " |  _  |\n" +
                            "  \\___/\n" +
                            "   /|\n" +
                            "  / |\n" +
                            "    |\n" +
                            "   / \\\n" +
                            "  /   \\");

        }else if (count==6) {
            System.out.println("  _____\n" +
                    " | * * |\n" +
                    " |  _  |\n" +
                    "  \\___/\n" +
                    "   /|\\\n" +
                    "  / | \\\n" +
                    "    |\n" +
                    "   / \\\n" +
                    "  /   \\");
        } else if (count==7) {
            System.out.println("  _____\n" +
                    " | * * |\n" +
                    " |  _  |\n" +
                    "  \\___/\n" +
                    "   /|\\\n" +
                    "  / | \\\n" +
                    "    |\n" +
                    "   / \\\n" +
                    "  /   \\");
            System.out.println("Stickman is dead");
        }
    }

    static void makeNameArrayList(String HangmanName,ArrayList<String> word ) {
        for (int i = 0; i < HangmanName.length(); i++) {
            char character = HangmanName.charAt(i);
            String letter = String.valueOf(character);
            word.add(letter);
        }
    }

    public static int findSameLetterInWord(String word,String LetterToCompare) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            String wordToCompare = String.valueOf(word.charAt(i));
            if (wordToCompare.equals(LetterToCompare)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            // først finder man sin fil ved at bruge "file klassen"
            File HangmanNames = new File("ressources/HangmanNames.txt");
            // derefter laver man en scanner som scanner i HangmanNames identifier
            Scanner scanner = new Scanner(HangmanNames);
            // så længe scanner har næste linje, så printer den
            String hangmanName = null;
            while (scanner.hasNextLine()){
                hangmanName = scanner.nextLine();
            }

            ArrayList<String> word = new ArrayList<>();
            // Take letters from hangmanName and puts them into the ArrayList with the word: word
            makeNameArrayList(hangmanName,word);

            // is here to check the word in arraylist is correct
            System.out.println("The word is "+hangmanName.length()+" letters");

            ArrayList<String> blank = new ArrayList<>();

            for (int i = 0; i < word.size(); i++) {
                blank.add("_");
            }
            // Laver blank om til en string
            // denne er brugt som reference https://newbedev.com/python-convert-list-string-to-string-java-code-example
            System.out.println(blank.stream().map(Object::toString)
                    .collect(Collectors.joining("")));
            
            // denne er brugt som reference https://newbedev.com/python-convert-list-string-to-string-java-code-example
            String newWord = word.stream().map(Object::toString)
                    .collect(Collectors.joining(""));

            int count = 0;
            while (true) {
                if (!blank.contains("_")) {
                    System.out.println("you won the game!");
                    return;
                } else if (count==7) {
                    System.out.println("you lost the game");
                    return;
                }
                try {
                    String userGuess = sc.nextLine();
                    // hvis brugers input er ligmed hvad der er inde i ord
                    if (userGuess.contains(word.get(word.indexOf(userGuess)))) {
                        int newIndex = 0;
                        // dette forloop finder mægnden af bogstaver i ordet
                        // og køre det antal gange som der er bogstaver i ordet
                        for (int i = 0 ; i < findSameLetterInWord(newWord,userGuess); i++) {
                            // loopet køre først en gang og setter det første bogstav i blank
                            // Et nyt index bliver sat og det andet bogstav bliver fundet og sat
                            for (int j = newIndex; j < word.size(); j++) {
                                if (String.valueOf(newWord.charAt(j)).equals(userGuess)) {
                                    blank.set(j, word.get(word.indexOf(userGuess)));
                                    newIndex=j+1;
                                    break;
                                }
                            }
                        }
                        // derefter bruges denne til at lave ArrayList om til String
                        // denne er brugt som reference https://newbedev.com/python-convert-list-string-to-string-java-code-example
                        System.out.println(blank.stream().map(Object::toString)
                                .collect(Collectors.joining("")));
                    }

                    else {
                        System.out.println("sorry but that's wrong");
                        count++;
                        System.out.println("you have "+count+" mistakes, you can have 6 in total");
                        hangmanDrawingsInitialize(count);
                        System.out.println(blank);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That is not correct");
                    count++;
                    System.out.println("you have "+count+" mistakes, you can have 6 in total");
                    hangmanDrawingsInitialize(count);
                    System.out.println(blank);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("file could not be found!");
            e.printStackTrace();
        }
    }

}
