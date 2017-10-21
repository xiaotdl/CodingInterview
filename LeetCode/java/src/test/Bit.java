package test;

/**
 * Created by Xiaotian on 10/20/17.
 */
public class Bit {

    public static void printBits(int x) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.insert(0, x >> i & 1);
            if (i != 31 && (i + 1) % 8 == 0) sb.insert(0, ' ');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println("== Integer.MAX_VALUE ==");
        System.out.println(Integer.MAX_VALUE);
        printBits(Integer.MAX_VALUE);

        System.out.println("== Integer.MIN_VALUE ==");
        System.out.println(Integer.MIN_VALUE);
        printBits(Integer.MIN_VALUE);

        System.out.println("== Integer.MIN_VALUE-1 ==");
        System.out.println(Integer.MIN_VALUE-1);
        printBits(Integer.MIN_VALUE-1);
    }
}
