package deque;

import java.lang.reflect.Array;
import java.util.Iterator;

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 4

public class ArrayDeque<T> implements Iterable<T> {
    //根据T也就是T来改变ArrayDeque的储存类型
    private T[] items;
    private int size;
    private int addFirst;
    private int addLast;


   public  ArrayDeque(){
    items = (T[]) new Object[8];
    size = 0;
    //初始化指针，我选择从数组的第一位开始Deque
    addFirst = 0;
    addLast = 1;


   }
    /** Adds x to the front of the list. */
    public void addFirst(T x) {
       //把值放入addFirst
        items[addFirst]=x;
        size=size+1;
        //该变指针 如果addFirst指针已经在数组最前面就跳到最后一个去
        if(addFirst==0){
        addFirst= items.length-1;
        }else {
            addFirst = addFirst - 1 ;
        }
        reSize();
    }
    /** Adds x to the end of the list. */
    public void addLast(T x) {
        items[addLast]=x;
        size = size + 1;
        if(addLast== items.length-1){
            addLast=0;
        }
        else {
            addLast = addLast + 1 ;
        }
        reSize();
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        //为了单一出口考虑设置一个变量
        boolean isEmpty = false;
        if(size == 0){
            isEmpty = true;
        }
        return isEmpty;
    }
    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Print all item in Deque
     * 打印整个Deque的值. */
    public void printDeque(){
       //从addFirst开始print直到数组末尾
        for(int i=addFirst+1;i< items.length;i++){
            if(items[i]==null){
                break;
            }
            System.out.print(items[i] + " ");
        }
        //如果末尾的指针小于前面的指针说明数组完了但Deque还没结束
        if(addLast<=addFirst){
            for(int i=0;i< addLast;i++){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    /** Remove x to the first of the list.
     * 删掉Deque最前面一个值并返回这个值*/
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T  first = null;
        //先储存addFirst指针后的值，然后删除它，并调整指针，再调整大小
        //各种操作都需要考虑addFirst指针过界的情况
        if(addFirst==items.length-1){
            //如果值在数组第一个也就是指针在数组最后一个，就删除数组第一个值
            first = items[0];
            items[0] = null;
            addFirst = 0;
        } else {
            first = items[addFirst+1];
            items[addFirst+1]=null;
            addFirst=addFirst+1;
        }
        size=size-1;
        reSize();
        return first;
    }
    /** Remove x to the end of the list.
     * 删掉Deque最后一个值*/
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T  last = null;
        if(addLast==0){
            //如果值在数组最后一个也就是指针在数组第一个，就删除数组最后一个的值
            last = items[items.length-1];
            items[items.length-1] = null;
            addLast = items.length-1;
        } else {
            last = items[addLast-1];
            items[addLast-1]=null;
            addLast=addLast-1;
        }
        size=size-1;
        reSize();
        return last;
    }
    /** 返回对应index的值*/
    public T get(int index){
        //要返回的值在数组里的位置
        int get = addFirst+1+index;
       if(get> items.length-1){
           return items[get-items.length];
       }
        return items[get];
    }
    //每次进行Deque的改变操作后都call这个函数，然后自动根据当前的状况放大或缩小Array
    /**自动改变Array大小
     * */
    public void reSize(){
        //如果Deque大小和数组内核大小相同就扩大
        if(size == items.length){
            //创建2倍大的新数组
            T[] temp;
            temp = (T[]) new Object[items.length*2];
            //转移值到新数组
            for(int i = 0;i <size;i++){
                temp[i]=get(i);
            }
            items = temp;
            //重设指针
            addFirst = temp.length-1;
            addLast =size;
        }
        //如果利用率小于25%就减半数组大小
        if(size< items.length/4){
            //创建缩小50%的新数组
            T[] temp;
            temp = (T[]) new Object[items.length/2];
            //转移值到新数组
            for(int i = 0;i <size;i++){
                temp[i]=get(i);
            }
            items = temp;
            //重设指针
            addFirst = temp.length-1;
            addLast =size;
        }
    }








    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<T> {
        //指针
        private int wizPos;

        public arrayIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            //重写指向下一个的指针
            T returnItem =get(wizPos);
            wizPos += 1;
            return returnItem;

        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque<>();
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
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();




        A.printDeque();

    }
}
