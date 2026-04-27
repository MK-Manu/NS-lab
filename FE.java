import java.math.BigInteger;
import java.util.Scanner;

public class FE {

    static BigInteger phi(BigInteger n){
        BigInteger r=n,i=BigInteger.valueOf(2),t=n;
        while(i.multiply(i).compareTo(t)<=0){
            if(t.mod(i).equals(BigInteger.ZERO)){
                while(t.mod(i).equals(BigInteger.ZERO)) t=t.divide(i);
                r=r.subtract(r.divide(i));
            }
            i=i.add(BigInteger.ONE);
        }
        if(t.compareTo(BigInteger.ONE)>0) r=r.subtract(r.divide(t));
        return r;
    }

    public static void main(String[] a){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter value of a: ");
        BigInteger x=sc.nextBigInteger();

        System.out.print("Enter prime number p (for Fermat): ");
        BigInteger p=sc.nextBigInteger();

        System.out.print("Enter number n (for Euler): ");
        BigInteger n=sc.nextBigInteger();

        if(p.isProbablePrime(10))
            System.out.println("\nFermat Result (a^(p-1) mod p): "+x.modPow(p.subtract(BigInteger.ONE),p));
        else
            System.out.println("\n❌ p is not prime. Fermat's theorem not applicable.");

        if(x.gcd(n).equals(BigInteger.ONE)){
            BigInteger ph=phi(n);
            System.out.println("\nEuler Totient φ(n): "+ph);
            System.out.println("Euler Result (a^φ(n) mod n): "+x.modPow(ph,n));
        } else
            System.out.println("\n❌ a and n are not coprime. Euler's theorem not applicable.");

        sc.close();
    }
}
