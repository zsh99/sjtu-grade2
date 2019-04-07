package com.example.demo;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class ladder {

    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String ladder(String source, String target) throws FileNotFoundException {
        String filepath = "dictionary.txt";
        int len = source.length();
        if (len != target.length()) return "NoT FoUnD";
        Scanner sc = new Scanner(new File(filepath));//file input
        HashSet<String> set = new HashSet<>();
        loadDictionary(len, sc, set);
        if (!(set.contains(source) && set.contains(target))) {
            return "NoT iN DiCtIoNaRy";
        }

        Stack<Stack<String>> ladders = prepareLadderStacks(source);
        Stack<String> result = wordLadder(set, ladders, target);
        return result.toString();
    }

    public static Stack<Stack<String>> prepareLadderStacks(String source) {
        Stack<String> ladder = new Stack<>();
        ladder.push(source);
        Stack<Stack<String>> ladders = new Stack<>();
        ladders.push(ladder);
        return ladders;
    }

    private static void loadDictionary(int len, Scanner sc, HashSet<String> set) {
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (word.length() == len) {
                set.add(word);
            }
        }
    }

    private static Stack<String> wordLadder(HashSet<String> set, Stack<Stack<String>> ladders, String target)  {
        if (ladders.size() == 0) {
            Stack<String> stk = new Stack<>();
            stk.push("Not found.");
            return stk;
        }
        Stack<Stack<String>> longerLadder = new Stack<>();
        int laddersSize = ladders.size();
        for (int k = 0; k < laddersSize; k++) {
            Stack<String> lad = ladders.pop();
            String top = lad.lastElement();
            for (int i = 0; i < top.length(); i++) {
                StringBuilder newWord = new StringBuilder(top);
                for (int j = 0; j < 26; j++) {
                    newWord.setCharAt(i, alphabet[j]);
                    if (newWord.toString().equals(target)) {
                        lad.push(newWord.toString());
                        return lad;
                    } else if (!newWord.toString().equals(top) && set.contains(newWord.toString())) {
                        lad.push(newWord.toString());
                        set.remove(newWord.toString());
                        longerLadder.push((Stack<String>)lad.clone());
                        lad.pop();
                    }
                }
            }
        }
        return wordLadder(set, longerLadder, target);
    }

}
