import java.util.*;

public class TranspositionCipher {

    // Get column order based on key
    static Integer[] getOrder(String key) {
        Integer[] order = new Integer[key.length()];

        for (int i = 0; i < key.length(); i++)
            order[i] = i;

        Arrays.sort(order, Comparator.comparingInt(key::charAt));
        return order;
    }

    // Main process method (like other ciphers)
    static String process(String text, String key, boolean encrypt) {
        StringBuilder res = new StringBuilder();

        text = text.toUpperCase().replaceAll(" ", "");
        key = key.toUpperCase();

        int cols = key.length();
        int rows = (text.length() + cols - 1) / cols;

        char[][] matrix = new char[rows][cols];
        Integer[] order = getOrder(key);

        if (encrypt) {

            // Fill matrix row-wise
            int k = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = (k < text.length()) ? text.charAt(k++) : 'X';
                }
            }

            // Read column-wise based on key order
            for (int i : order)
                for (int j = 0; j < rows; j++)
                    res.append(matrix[j][i]);

        } else {

            // Fill column-wise based on key order
            int k = 0;
            for (int i = 0; i < cols; i++)
                for (int j = 0; j < rows; j++)
                    matrix[j][order[i]] = text.charAt(k++);

            // Read row-wise
            for (char[] row : matrix)
                for (char ch : row)
                    res.append(ch);
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
        String dec = process(enc, key, false);

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + dec);
    }
}
