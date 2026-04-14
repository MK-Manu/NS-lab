import java.util.Scanner;

public class PolyalphabeticCipher {

    static String process(String text, String key, boolean encrypt) {
        StringBuilder res = new StringBuilder();

        text = text.toUpperCase();
        key = key.toUpperCase();

        int j = 0; // key index

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {

                int shift = key.charAt(j % key.length()) - 'A';

                if (!encrypt) {
                    shift = 26 - shift; // reverse shift for decryption
                }

                res.append((char) ((ch - 'A' + shift) % 26 + 'A'));
                j++;

            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        String enc = process(text, key, true);

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, key, false));
    }
}
