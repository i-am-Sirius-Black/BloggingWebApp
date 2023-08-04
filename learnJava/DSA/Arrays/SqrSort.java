package DSA.Arrays;

import java.util.Scanner;

public class SqrSort {
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[]= new int[n];

        for(int i=0; i<n; i++){
            arr[i]=sc.nextInt();

        }

        int L=0; int R=n-1;
        int newArr[]=new int[n];

        int m=n-1;
        while(L<=R){
            int LSqr=arr[L]*arr[L];
            int RSqr=arr[R]*arr[R];
            if(LSqr>RSqr){
                newArr[m]=LSqr;
                L++;
            }else{
                newArr[m]=RSqr;
                R--;
            }
            m--;
        }
        for(int i=0; i<n; i++){
            System.out.print( newArr[i]+" ");
        }

    }
}
