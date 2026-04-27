import java.util.*;

public class LCG {

    public static void main(String[] a){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter seed (X0): ");
        int x=sc.nextInt();

        System.out.print("Enter multiplier (a): ");
        int m=sc.nextInt();

        System.out.print("Enter increment (c): ");
        int c=sc.nextInt();

        System.out.print("Enter modulus (m): ");
        int mod=sc.nextInt();

        System.out.print("Enter number of random numbers to generate: ");
        int n=sc.nextInt();

        System.out.println("\nGenerated Pseudo Random Numbers:");

        for(int i=0;i<n;i++)
            System.out.println("X"+(i+1)+" = "+(x=(m*x+c)%mod));

        sc.close();
    }
}
