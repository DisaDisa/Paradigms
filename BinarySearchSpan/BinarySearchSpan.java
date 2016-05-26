import java.io.IOException;

/**
 * Created by Disa on 25.02.2016.
 */
public class BinarySearchSpan {
    // for all i: 1 <= i <= a.length - 1 : a[i - 1] >= a[i], 0 <= l <= r
    public static int binarySearchIterative(int x, int a[], int l, int r) {
        //let a[-1] > x, a[a.length] < x
        //inv : -1 <= l < r <= a.length, a[l] > x >= a[r]
        while (l + 1 < r) {
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

    public static int binarySearchRecursion(int x, int a[], int l, int r) {
        //let a[-1] > x, a[a.length] < x
        //inv : -1 <= l < r <= a.lenght, a[l] >= x > a[r]
        if (l + 1 == r) {
            return r;
            //res = min(i): a[i] <= x && a[i - 1] > x
        }
        int m = l + (r - l) / 2;
        if (a[m] >= x) {
            //a[l] >= x > a[r]
            return binarySearchRecursion(x, a, m, r);
        } else {
            //a[l] >= x > a[r]
            return binarySearchRecursion(x, a, l, m);
        }
    }

    public static void main(String[] args) throws IOException {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        int pos1 = binarySearchIterative(x, a, -1, a.length);
        if (pos1 == a.length || a[pos1] != x) {
            System.out.println(pos1 + " 0");
        } else {
            int pos2 = binarySearchRecursion(x, a, -1, a.length);
            System.out.println(pos1 + " " + (pos2 - pos1));
        }
    }
}
