import java.util.Scanner;

public class CaesarCipher {

    static String process(String text, int shift) {
        StringBuilder res = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                res.append((char) ((ch - base + shift) % 26 + base));
            } else res.append(ch);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt() % 26;

        String enc = process(text, shift);
        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, 26 - shift));
    }
}