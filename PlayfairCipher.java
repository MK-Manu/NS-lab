import java.util.*;

public class PlayfairCipher {

    static char[][] m = new char[5][5];
    static Map<Character, int[]> map = new HashMap<>();

    static void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        Set<Character> set = new LinkedHashSet<>();

        for (char c : (key + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toCharArray())
            set.add(c);

        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                m[i][j] = it.next();
                map.put(m[i][j], new int[]{i, j});
            }
    }

    static String prepare(String t) {
        t = t.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < t.length(); i++) {
            s.append(t.charAt(i));
            if (i + 1 < t.length() && t.charAt(i) == t.charAt(i + 1))
                s.append('X');
        }
        if (s.length() % 2 != 0) s.append('X');
        return s.toString();
    }

    static String process(String text, boolean enc) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] a = map.get(text.charAt(i));
            int[] b = map.get(text.charAt(i + 1));

            if (a[0] == b[0]) { // row
                res.append(m[a[0]][(a[1] + (enc ? 1 : 4)) % 5]);
                res.append(m[b[0]][(b[1] + (enc ? 1 : 4)) % 5]);
            } else if (a[1] == b[1]) { // column
                res.append(m[(a[0] + (enc ? 1 : 4)) % 5][a[1]]);
                res.append(m[(b[0] + (enc ? 1 : 4)) % 5][b[1]]);
            } else { // rectangle
                res.append(m[a[0]][b[1]]);
                res.append(m[b[0]][a[1]]);
            }
        }
        return res.toString();
    }

    static void printMatrix() {
        System.out.println("\nPlayfair Matrix:");
        for (char[] row : m) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        generateMatrix(key);
        printMatrix();

        System.out.print("\nEnter Plain Text: ");
        String text = sc.nextLine();

        String p = prepare(text), e = process(p, true);
        System.out.println("Encrypted Text: " + e);
        System.out.println("Decrypted Text: " + process(e, false));
    }
}