package DSA.Arrays;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClosestRefuge {
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();

        int n = sc.nextInt();
        // int arr[]= new int[n];
        boolean found = false;
        for(int i=0; i<n; i++){
            set.add(sc.nextInt());
        }


        for(int i=1; i<=n; i++){
            if(!set.contains(i)){
                System.out.print(i);
                found=true;
                break;
            }
        }
        if(!found){
            System.out.print(n+1);
        }
    }
}
