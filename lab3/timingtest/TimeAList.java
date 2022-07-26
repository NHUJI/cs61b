package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        //Dog时间、大小、操作次数记录
        AList<Double> DogTime = new AList();
        AList<Integer> DogSize= new AList();
        AList<Integer> DogOp= new AList();
        //不断平方增加addLast操作的次数i
        for(int i = 1000;i<= 128000;i = i*2){
            //创建测试数组
            AList<Integer> Dog = new AList();
            Stopwatch sw = new Stopwatch();
            //进行i次addLast
            for(int j = 1;j<= i;j++){
                Dog.addLast(1);
            }
            DogTime.addLast(sw.elapsedTime());
            DogSize.addLast(Dog.size());
            DogOp.addLast(i);
        }
        printTimingTable(DogSize,DogTime,DogOp);
    }
}
