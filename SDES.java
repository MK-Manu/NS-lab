import java.util.*;

public class SDES {

    static int[] P10={3,5,2,7,4,10,1,9,8,6}, P8={6,3,7,4,8,5,10,9},
    P4={2,4,3,1}, IP={2,6,3,1,4,8,5,7}, IP1={4,1,3,5,7,2,8,6},
    EP={4,1,2,3,2,3,4,1};
 
    static int[][] S0={{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}},
                     S1={{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};

    static String p(String s,int[] t){String r="";for(int i:t)r+=s.charAt(i-1);return r;}
    static String ls(String s,int n){return s.substring(n)+s.substring(0,n);}
    static String x(String a,String b){String r="";for(int i=0;i<a.length();i++)r+=(a.charAt(i)^b.charAt(i));return r;}

    static String s(String in,int[][] box){
        int r=Integer.parseInt(""+in.charAt(0)+in.charAt(3),2);
        int c=Integer.parseInt(""+in.charAt(1)+in.charAt(2),2);
        return String.format("%2s",Integer.toBinaryString(box[r][c])).replace(' ','0');
    }

    static String[] keys(String k){
        k=p(k,P10);
        String l=ls(k.substring(0,5),1), r=ls(k.substring(5),1);
        String k1=p(l+r,P8);
        l=ls(l,2); r=ls(r,2);
        String k2=p(l+r,P8);
        return new String[]{k1,k2};
    }

    static String fk(String in,String k){
        String l=in.substring(0,4), r=in.substring(4);
        String t=x(p(r,EP),k);
        String s=p(s(t.substring(0,4),S0)+s(t.substring(4),S1),P4);
        return x(l,s)+r;
    }

    static String sw(String s){return s.substring(4)+s.substring(0,4);}

    static String enc(String pt,String k){
        String[] key=keys(k);
        String r=fk(p(pt,IP),key[0]);
        return p(fk(sw(r),key[1]),IP1);
    }

    static String dec(String ct,String k){
        String[] key=keys(k);
        String r=fk(p(ct,IP),key[1]);
        return p(fk(sw(r),key[0]),IP1);
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter 10-bit key: ");
        String k=sc.next();

        System.out.print("Enter 8-bit plaintext: ");
        String pt=sc.next();

        String ct=enc(pt,k);
        System.out.println("Encrypted: "+ct);
        System.out.println("Decrypted: "+dec(ct,k));

        sc.close();
    }
}
