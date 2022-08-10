package deque;

import java.util.Comparator;

public class MaxArrayDeque<LochNess> extends ArrayDeque<LochNess>{
    //通过给定的comparator创建MAD
    Comparator comparator;
    public MaxArrayDeque(Comparator<LochNess> c){
        //把创建时传入的comparator存起来
        comparator = c;
    }
    public MaxArrayDeque(){

    }
    /**
     * 返回双端队列中的最大元素
     * 如果MAD为空返回null
     * 使用创建时给定的comparator
     * */
    public LochNess max() {
        if (size() == 0) {
            return null;
        }
        LochNess maxT = get(0);
        for (int i = 1; i < size(); i += 1) {
            LochNess getI = get(i);
            if (comparator.compare(maxT, getI) < 0) {
                maxT = getI;
            }
        }
        return maxT;
    }


    /**
     * 返回双端队列中的最大元素
     * 如果MAD为空返回null
     * 需要指定comparator
     * */
    public LochNess max(Comparator<LochNess> c){
        //重设comparator
        comparator = c;
        //没必要再去重复代码，直接再调用max（）
        return max();
    }



    public static void main(String[] args) {
        MaxArrayDeque<Integer> A = new MaxArrayDeque<Integer>();
        A.addFirst(3);
        A.addLast(4);
        A.addFirst(12);
        A.addFirst(34);
        A.addFirst(15);
        A.addFirst(3);
        A.addLast(4);
        A.addFirst(12);
        A.addFirst(34);
        A.printDeque();
        A.removeLast();


        A.removeLast();




        A.printDeque();
    }
}
