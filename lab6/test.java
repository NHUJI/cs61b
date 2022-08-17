public class test {

public static void test(int N) {
    int count=0;
        for (int i = 1; i <= N; i = i * 2) {
        for (int j = 0; j < i; j += 1) {
            count++;
        System.out.println("hello");
        System.out.println("i = "+i+" ,j = "+j+" 第"+count+"次输出");
        int ZUG = 1 + 1;
        }
        }
        }

    public static int f3(int n) {
        if (n <= 1)
            return 1;
        return f3(n-1) + f3(n-1);
    }

    public static void main(String[] args) {
        System.out.println(f3(-1));
        System.out.println(f3(0));
    System.out.println(f3(1));
        System.out.println(f3(2));
        System.out.println(f3(3));
        System.out.println(f3(4));


    }


}