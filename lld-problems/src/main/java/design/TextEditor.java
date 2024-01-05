package design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class TextEditor {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        Deque<String> stack = new ArrayDeque<>();

        while (scanner.hasNext()) {
            int ops = scanner.nextInt();

            switch(ops) {
                case 1:
                    // append
                    stack.push(sb.toString());
                    sb.append(scanner.next());
                    break;
                case 2:
                    // delete
                    int numberOfChars = scanner.nextInt();
                    int start = sb.length() - numberOfChars;
                    int end = sb.length();

                    stack.push(sb.toString());
                    sb.delete(start, end);
                    break;
                case 3:
                    // print
                    int index = scanner.nextInt();
                    char c = sb.charAt(index - 1);
                    System.out.println(c);
                    break;
                case 4:
                    // undo
                    sb = new StringBuilder(stack.pop());
                    break;
            }
        }

        scanner.close();

    }
}
