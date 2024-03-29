package gh2;

import deque.Deque;
import deque.ArrayDeque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private static  int get= -1;
    /* Buffer for storing sound data. */
     private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer array with zeros.
        int capacity =(int) Math.round(SR/frequency) ;
        buffer=new ArrayDeque<>();
        for(int i = 0;i<capacity;i++ ) {
            buffer.addLast((double)0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i = 0;i<buffer.size();i++ ){
            double r = Math.random() - 0.5;
            buffer.addLast( r);
            buffer.removeFirst();
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double A = buffer.removeFirst();
        double B = buffer.get(0);
        buffer.addLast(DECAY*0.5*(A+B));

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }

    public static void main(String[] args) {
//        GuitarString A= new GuitarString(100);
//        A.pluck();
//        A.tic();
//        System.out.println("ok" );
    }

}