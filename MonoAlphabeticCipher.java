import java.util.*;

public class MonoAlphabeticCipher {

    static String process(String text, String key, boolean encrypt) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder();

        text = text.toUpperCase();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int i = encrypt ? alpha.indexOf(ch) : key.indexOf(ch);
                res.append(encrypt ? key.charAt(i) : alpha.charAt(i));
            } else res.append(ch);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 26-letter key (no duplicates): ");
        String key = sc.nextLine().toUpperCase();

        if (key.length() != 26) {
            System.out.println("Invalid key! Key must contain exactly 26 letters.");
            return;
        }

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        String enc = process(text, key, true);
        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, key, false));
    }
}