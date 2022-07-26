package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      correct.addLast(5);
      correct.addLast(10);
      correct.addLast(15);

      broken.addLast(5);
      broken.addLast(10);
      broken.addLast(15);

      assertEquals(correct.size(), broken.size());

      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
  }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
//                System.out.println("sizeL: " + sizeL + " sizeB: " + sizeB);
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2) {
                if(L.size()!=0 && B.size()!=0){
                // getLast
//                System.out.println("getLastL: " + L.getLast()+" getLastB: " + B.getLast());
                assertEquals(L.getLast(), B.getLast());
                }
            } else if (operationNumber == 3) {
                if(L.size()!=0 && B.size()!=0) {
                    // removeLast
                    int removeLastL = L.removeLast();
                    int removeLastB = B.removeLast();
//                    System.out.println("removeLastL: " + removeLastL+" removeLastB: " + removeLastB);
                    assertEquals(removeLastL, removeLastB);
                }
            }
        }
    }
}
