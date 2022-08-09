package flik;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void simpleTest(){
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                assertEquals(i,j);
            }
        }

    }
}
