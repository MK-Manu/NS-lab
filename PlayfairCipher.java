import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];
    static Map<Character, int[]> pos = new HashMap<>();

    static void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        String full = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        Set<Character> set = new LinkedHashSet<>();

        for (char c : full.toCharArray())
            set.add(c);

        Iterator<Character> it = set.iterator();

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = it.next();
                pos.put(matrix[i][j], new int[]{i, j});
            }
    }

    static String prepare(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            res.append(text.charAt(i));

            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
                res.append('X');
        }

        if (res.length() % 2 != 0)
            res.append('X');

        return res.toString();
    }

    static String process(String text, boolean encrypt) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] a = pos.get(text.charAt(i));
            int[] b = pos.get(text.charAt(i + 1));

            if (a[0] == b[0]) {
                res.append(matrix[a[0]][(a[1] + (encrypt ? 1 : 4)) % 5]);
                res.append(matrix[b[0]][(b[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (a[1] == b[1]) {
                res.append(matrix[(a[0] + (encrypt ? 1 : 4)) % 5][a[1]]);
                res.append(matrix[(b[0] + (encrypt ? 1 : 4)) % 5][b[1]]);
            } else {
                res.append(matrix[a[0]][b[1]]);
                res.append(matrix[b[0]][a[1]]);
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        generateMatrix(key);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String prepared = prepare(text);
        String enc = process(prepared, true);

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, false));
    }
}
