import java.io.IOException;

/**
 * Created by Disa on 25.02.2016.
 */
public class BinarySearchSpan {
    // for all i: 1 <= i <= a.length - 1 : a[i - 1] >= a[i], l <= r
    public static int BinarySearchIterative(int x, int a[], int l, int r) {
        //let a[-1] >= x, a[a.length] < x
        //inv : -1 <= l < r <= a.length, a[l] > x >= a[r]
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            // l <= m < r
            if (a[m] <= x) {
                r = m;
                //a[l] > x >= a[r]
            } else {
                l = m;
                //a[l] > x >= a[r]
            }
        }
        return r;
        //res = min(i): a[i] <= x && a[i - 1] > x
    }
    public static int BinarySearchRecursion(int x, int a[], int l, int r) {
        //let a[-1] >= x, a[a.length] < x
        //inv : -1 <= l < r <= a.lenght, a[l] > x >= a[r]
        if (r - l == 1) {
            return r;
            //res = min(i): a[i] <= x && a[i - 1] > x
        }
        int m = l + (r - l) / 2;
        if (a[m] <= x) {
            //a[l] > x >= a[r]
            return BinarySearchRecursion(x, a, l, m);
        } else {
            //a[l] > x >= a[r]
            return BinarySearchRecursion(x, a, m, r);
        }
    }
    public static void main(String[] args) throws IOException {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        int ans1 = BinarySearchIterative(x, a, -1, a.length);
        int ans2 = BinarySearchIterative(x, a, -1, a.length);
        System.out.print(ans1 + " " + (ans2 - ans1));
    }
}
