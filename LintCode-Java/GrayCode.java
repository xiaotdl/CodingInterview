import java.util.ArrayList;

public class GrayCode {
    public static ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (n == 0) {
            ret.add(0);
            return ret;
        }

        ret = grayCode(n - 1);

        for (int i = ret.size() - 1; i >= 0; i--) {
            int num = ret.get(i);
            num += 1 << (n - 1);
            ret.add(num);
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(grayCode(2));
    }
}