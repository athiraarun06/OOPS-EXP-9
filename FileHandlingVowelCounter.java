import java.io.*;
import java.util.*;

public class FileHandlingVowelCounter {

    // ========== CHARACTER STREAM ==========
    public static void countVowelsCharacterStream(String filename) {
        int[] vowels = new int[5]; // a, e, i, o, u
        int total = 0;

        try (FileReader fr = new FileReader(filename)) {
            int ch;
            while ((ch = fr.read()) != -1) {
                char c = Character.toLowerCase((char) ch);
                switch (c) {
                    case 'a': vowels[0]++; total++; break;
                    case 'e': vowels[1]++; total++; break;
                    case 'i': vowels[2]++; total++; break;
                    case 'o': vowels[3]++; total++; break;
                    case 'u': vowels[4]++; total++; break;
                }
            }

            displayVowelResult(vowels, total, "Character Stream");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // ========== BYTE STREAM ==========
    public static void countVowelsByteStream(String filename) {
        int[] vowels = new int[5];
        int total = 0;

        try (FileInputStream fis = new FileInputStream(filename)) {
            int b;
            while ((b = fis.read()) != -1) {
                char c = Character.toLowerCase((char) b);
                switch (c) {
                    case 'a': vowels[0]++; total++; break;
                    case 'e': vowels[1]++; total++; break;
                    case 'i': vowels[2]++; total++; break;
                    case 'o': vowels[3]++; total++; break;
                    case 'u': vowels[4]++; total++; break;
                }
            }

            displayVowelResult(vowels, total, "Byte Stream");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // ========== DISPLAY RESULT ==========
    public static void displayVowelResult(int[] vowels, int total, String method) {
        System.out.println("\n--- Vowel Count (" + method + ") ---");
        System.out.println("Total vowels: " + total);
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < 5; i++) {
            System.out.println(chars[i] + ": " + vowels[i]);
        }

        int maxIndex = 0;
        for (int i = 1; i < 5; i++) {
            if (vowels[i] > vowels[maxIndex]) maxIndex = i;
        }
        System.out.println("Most frequent vowel: " + chars[maxIndex]);
    }

    // ========== FILE OPERATIONS ==========
    public static void createFile(String name) {
        try {
            File f = new File(name);
            if (f.createNewFile()) System.out.println("File created: " + f.getName());
            else System.out.println("File already exists.");
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    public static void renameFile(String oldName, String newName) {
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        if (oldFile.renameTo(newFile))
            System.out.println("File renamed successfully.");
        else
            System.out.println("Rename failed.");
    }

    public static void deleteFile(String name) {
        File f = new File(name);
        if (f.delete())
            System.out.println("File deleted successfully.");
        else
            System.out.println("File not found or cannot be deleted.");
    }

    public static void createDirectory(String dirName) {
        File dir = new File(dirName);
        if (dir.mkdir())
            System.out.println("Directory created successfully.");
        else
            System.out.println("Directory already exists or failed.");
    }

    public static void findAbsolutePath(String name) {
        File f = new File(name);
        System.out.println("Absolute Path: " + f.getAbsolutePath());
    }

    public static void listFilesInDirectory(String dirName) {
        File dir = new File(dirName);
        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();
            System.out.println("\nFiles in " + dirName + ":");
            for (String file : files)
                System.out.println(file);
        } else {
            System.out.println("Invalid directory.");
        }
    }

    // ========== MAIN MENU ==========
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n FILE HANDLING MENU ");
            System.out.println("1. Create a new file");
            System.out.println("2. Rename a file");
            System.out.println("3. Delete a file");
            System.out.println("4. Create a directory");
            System.out.println("5. Find absolute path of a file");
            System.out.println("6. Display all file names in a directory");
            System.out.println("7. Count vowels (Character Stream)");
            System.out.println("8. Count vowels (Byte Stream)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    createFile(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter old file name: ");
                    String oldName = sc.nextLine();
                    System.out.print("Enter new file name: ");
                    renameFile(oldName, sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter file name to delete: ");
                    deleteFile(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter directory name: ");
                    createDirectory(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter file name: ");
                    findAbsolutePath(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter directory name: ");
                    listFilesInDirectory(sc.nextLine());
                    break;
                case 7:
                    countVowelsCharacterStream("notes.txt");
                    break;
                case 8:
                    countVowelsByteStream("notes.txt");
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (choice != 9);

        sc.close();
    }
}
