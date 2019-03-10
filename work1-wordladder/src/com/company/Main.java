package com.company;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class Main {

    static char[] cha = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) throws FileNotFoundException {//I/O Interface
        Scanner input = new Scanner(System.in);
        String path = getPath(input);
        while (true) {
            System.out.print("input start word:");
            String start = input.nextLine();
            System.out.print("input end word:");
            String end = input.nextLine();
            Scanner dict = new Scanner(new File(path));//file input
            HashSet<String> set = new HashSet<>();
            Dictionary(start.length(), dict, set);
            if (!(set.contains(start) && set.contains(end)) && start.length()==end.length()) {
                System.out.println("wrong input:start word and the end word must be the same length and in the file");
                continue;
            }
            Stack<String> ladder = new Stack<>();
            ladder.push(start);
            Stack<Stack<String>> ladders = new Stack<>();
            ladders.push(ladder);
            Stack<String> result = wordLadder(set, ladders, end);
            System.out.println("ladder:");
            int len = result.size();
            for (int i = 0; i < len; i++) {
                System.out.print(result.elementAt(i) + " ");
            }
            System.out.println();
            System.out.println("press enter to continue('q' to quit):");
            String quit = input.nextLine();
            if (quit.equals("q")) break;
        }
    }


    private static void Dictionary(int len, Scanner sc, HashSet<String> set) {// Generated dictionary
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (word.length() == len) {
                set.add(word);
            }
        }
    }

    private static String getPath(Scanner scanner) {
        while (true) {
            System.out.print("input your dictionary(filename):");
            String dicPath = scanner.nextLine();
            File file = new File(dicPath);
            if (file.exists()) {
                return dicPath;
            }
            System.out.println("File not found.");
        }
    }


    private static Stack<String> wordLadder(HashSet<String> set, Stack<Stack<String>> ladders, String target) {//seek ladder
        if (ladders.size() == 0) {
            Stack<String> stk = new Stack<>();
            stk.push("Not found.");
            return stk;
        }
        Stack<Stack<String>> longerLadder = new Stack<>();
        int length = ladders.size();
        for (int k = 0; k < length; k++) {
            Stack<String> lad = ladders.pop();
            String top = lad.lastElement();
            for (int i = 0; i < top.length(); i++) {
                StringBuilder newWord = new StringBuilder(top);
                for (int j = 0; j < 26; j++) {
                    newWord.setCharAt(i, cha[j]);
                    if (newWord.toString().equals(target)) {
                        lad.push(newWord.toString());
                        return lad;
                    } else if (!newWord.toString().equals(top) && set.contains(newWord.toString())) {
                        lad.push(newWord.toString());
                        set.remove(newWord.toString());
                        longerLadder.push((Stack<String>) lad.clone());
                        lad.pop();
                    }
                }
            }
        }
        return wordLadder(set, longerLadder, target);
    }
}





