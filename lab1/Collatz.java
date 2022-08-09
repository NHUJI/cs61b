/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Buggy implementation of nextNumber!
     * 返回下一个collatz数字
     *
     * */
    public static int nextNumber(int n) {
        int nextNumber ;
        if(n == 1){
            nextNumber = 1 ;

        } else if (n%2 == 0) {
            nextNumber = n/2;
        }else
        {
            nextNumber = 3*n+1;
        }
        return  nextNumber;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

