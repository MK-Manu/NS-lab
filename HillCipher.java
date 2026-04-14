import java.util.*;

public class HillCipher {

    static int[][] key = new int[2][2];
    static int[][] inv = new int[2][2];

    static void findInverse() {
        int det = (key[0][0]*key[1][1] - key[0][1]*key[1][0]) % 26;
        if (det < 0) det += 26;

        int detInv = 0;
        for (int i = 1; i < 26; i++)
            if ((det * i) % 26 == 1)
                detInv = i;

        inv[0][0] =  key[1][1] * detInv % 26;
        inv[0][1] = -key[0][1] * detInv % 26;
        inv[1][0] = -key[1][0] * detInv % 26;
        inv[1][1] =  key[0][0] * detInv % 26;

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
    }

    static String process(String text, int[][] mat) {
        StringBuilder res = new StringBuilder();

        text = text.toUpperCase();

        int[] a = new int[2];
        int[] r = new int[2];

        for (int i = 0; i < 2; i++)
            a[i] = text.charAt(i) - 'A';

        for (int i = 0; i < 2; i++) {
            r[i] = 0;
            for (int j = 0; j < 2; j++)
                r[i] += mat[i][j] * a[j];

            r[i] %= 26;
        }

        for (int i = 0; i < 2; i++)
            res.append((char)(r[i] + 'A'));

        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter key matrix (2x2):");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        findInverse();

        System.out.print("Enter text: ");
        String text = sc.next();

        String enc = process(text, key);

        System.out.println("Encrypted Text: " + enc);
        System.out.println("Decrypted Text: " + process(enc, inv));
    }
}
