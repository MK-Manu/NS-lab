import java.util.*;

public class TranspositionCipher {

    static Integer[] getOrder(String key) {
        Integer[] o = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) o[i] = i;
        Arrays.sort(o, Comparator.comparingInt(key::charAt));
        return o;
    }

    static String process(String text, String key, boolean enc) {
        int c = key.length(), r = text.length() / c + (text.length() % c == 0 ? 0 : 1);
        char[][] m = new char[r][c];
        Integer[] o = getOrder(key);
        StringBuilder res = new StringBuilder();

        if (enc) {
            for (int i = 0, k = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    m[i][j] = k < text.length() ? text.charAt(k++) : 'X';

            for (int i : o)
                for (int j = 0; j < r; j++)
                    res.append(m[j][i]);
        } else {
            for (int i = 0, k = 0; i < o.length; i++)
                for (int j = 0; j < r; j++)
                    m[j][o[i]] = text.charAt(k++);

            for (char[] row : m)
                for (char ch : row)
                    res.append(ch);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Transposition Cipher Program ===");
        System.out.println("1. Single Encryption and Decryption");
        System.out.println("2. Double Encryption and Decryption");
        System.out.print("Enter your choice: ");
        int ch = sc.nextInt(); sc.nextLine();

        System.out.print("Enter text: ");
        String t = sc.nextLine().toUpperCase().replaceAll(" ", "");

        if (ch == 1) {
            System.out.print("Enter key: ");
            String k = sc.nextLine().toUpperCase();
            String e = process(t, k, true);
            System.out.println("Encrypted: " + e);
            System.out.println("Decrypted: " + process(e, k, false));
        } else if (ch == 2) {
            System.out.print("Enter first key: ");
            String k1 = sc.nextLine().toUpperCase();
            System.out.print("Enter second key: ");
            String k2 = sc.nextLine().toUpperCase();

            String e = process(process(t, k1, true), k2, true);
            System.out.println("Encrypted: " + e);
            System.out.println("Decrypted: " + process(process(e, k2, false), k1, false));
        } else System.out.println("Invalid choice!");
    }
}