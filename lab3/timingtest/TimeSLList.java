package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {

        // TODO: YOUR CODE HERE

        AList<Double> DogTime = new AList();
        AList<Integer> DogSize= new AList();
        AList<Integer> DogOp= new AList();


        int M = 10000;
        //不断平方增加addLast操作的次数i
        for(int i = 1000;i <= 128000;i = i*2){
            SLList Dog = new SLList();
            for(int j = 1;j<= i;j++){
                Dog.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int k = 0; k < M;k++){
               Dog.getLast();
           }
            DogTime.addLast(sw.elapsedTime());
            DogSize.addLast(Dog.size());
            DogOp.addLast(M);

        }
        printTimingTable(DogSize,DogTime,DogOp);
    }

}
