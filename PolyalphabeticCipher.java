import java.util.*;

public class PolyalphabeticCipher {

    static String process(String text, String key, boolean enc) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                int shift = key.charAt(j++ % key.length()) - 'A';
                res.append((char) ((c - 'A' + (enc ? shift : -shift) + 26) % 26 + 'A'));
            } else res.append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String enc = process(text, key, true);
        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, key, false));
    }
}
