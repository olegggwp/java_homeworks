import java.util.HashMap;
import java.util.Scanner;

public class ProblemM {
    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int j = n-1; j >= 1; j--){
            for (int i = 0; i < j; i++) {
                Integer cntVal = cnt.get(2*arr[j]-arr[i]);
                if (cntVal != null){
                    ans += cntVal;
                }
            }
            Integer cntj = cnt.get(arr[j]);
            if (cntj == null){
                cntj = 0;
            }
            cnt.put(arr[j], cntj+1);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            solve(sc);
        }
    }
}
