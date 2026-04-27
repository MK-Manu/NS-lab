import java.math.BigInteger;
import java.util.Scanner;

public class DH {

    static boolean isPrime(BigInteger n){
        return n.isProbablePrime(10);
    }

    public static void main(String[] a){ 

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter prime (p): ");
        BigInteger p=sc.nextBigInteger();

        if(!isPrime(p)){
            System.out.println("\nKey Exchange Failed! Shared key is Not same");
            return;
        }

        System.out.print("Enter primitive root (g): ");
        BigInteger g=sc.nextBigInteger();

        System.out.print("Enter Alice private key (a): ");
        BigInteger x=sc.nextBigInteger();

        System.out.print("Enter Bob private key (b): ");
        BigInteger y=sc.nextBigInteger();

        BigInteger A=g.modPow(x,p), B=g.modPow(y,p);

        System.out.println("\nAlice Public Key: "+A);
        System.out.println("Bob Public Key: "+B);

        BigInteger k1=B.modPow(x,p), k2=A.modPow(y,p);

        System.out.println("\nShared Key (Alice): "+k1);
        System.out.println("Shared Key (Bob): "+k2);

        if(k1.equals(k2))
            System.out.println("\nKey Exchange Successful! Shared key is same.");
        else
            System.out.println("\nKey Exchange Failed! Shared key is Not same");

        sc.close();
    }
}
