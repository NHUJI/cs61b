package deque;



import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T>{
    //StuffNode就是具体的数节点
    private  class StuffNode {
        public T item;
        //既然是Deque，所以添加向前的点
        public StuffNode last;
        public StuffNode next;

        public StuffNode(T i, StuffNode l, StuffNode n) {
            item = i;
            //添加向前的
            last = l;
            next = n;
//            System.out.println(size);
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    //大小记录
    private int size;



    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        //sentinel节点里放什么无所谓，由于是Circular sentinel先放入null再把前后指针都放入sentinel自己
        sentinel = new StuffNode(null, null,null);
        sentinel.last = sentinel;
        sentinel.next = sentinel;

        size = 0;
    }

//给带值的初始化使用 先不处理
//   public LinkedListDeque(T x) {
//        sentinel = new StuffNode(63, null,null);
//        sentinel.next = new StuffNode(x, null,null);
//        sentinel.next.last = sentinel.last;
//        sentinel.next.next = sentinel;
//        size = 1;
//    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        //把sentinel的下一个修改为新添加的
        sentinel.next = new StuffNode(x, sentinel,sentinel.next);
        //把原本sentinel.next的last改为新添加的
        sentinel.next.next.last = sentinel.next;
        size = size + 1;
    }

    /** Returns the first item in the list. */
    public T getFirst() {
        return sentinel.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(T x) {
        size = size + 1;
        //第一项的前一个就是最后一个StuffNode
        StuffNode p = sentinel.last;
        //创建下一个
        p.next = new StuffNode(x,p,sentinel);
        //修改sentinel的上一个为最新创建的一个
        sentinel.last = p.next;
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
        StuffNode p = sentinel.next;
        for(int i=0;i< size;i++){
            System.out.print(p.item + " ");
            //不知道是我写的不规范还是什么，p有提示
            p = p.next;
        }
        System.out.println();
    }
    /** Remove x to the end of the list.
     * 删掉Deque最后一个值*/
    public T removeLast(){
        T  last = null;
        if(sentinel.next != sentinel){
        //记录要返回的值
        last = sentinel.last.item ;
        //首先把倒数第二个节点的next改为sentinel
        sentinel.last.last.next=sentinel;
        //再把sentinel的上一个修改为原本的倒数第二个
        sentinel.last = sentinel.last.last;
        size = size-1;

        }
        return last;
    }
    /** Remove x to the first of the list.
     * 删掉Deque最前面一个值*/
    public T removeFirst(){
        T  first = null;
        if(sentinel.next != sentinel){
            //记录要返回的值
            first = sentinel.next.item ;
            //首先sentinel的next改为删除节点的next
            sentinel.next = sentinel.next.next;
            //再把sentinel的下一个（原本的下下个）的last改为sentinel）
            sentinel.next.last = sentinel;
            size = size-1;

        }
        return first;
    }
    //返回对应index的值
    public T get(int index){
        StuffNode p = sentinel;
        if (index >= size){
            return null;
        }
        for (int i = 0;i<=index;i++){
            p = p.next;
        }
        return p.item;

    }
   /**使用递归实现的get方法 返回index对应的数*/
    public T getRecursive(int index){
        if(index>size){
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
    public T getRecursive(int index,StuffNode node){
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }
@Override
    /** returns an iterator (a.k.a. seer)  */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        //指针
        private int wizPos;

        public LinkedListIterator() {
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

    @Override
    public boolean equals(Object other) {
        //如果比较对象就是本身，跳过接下来的比对
        if (this == other) {
            return true;
        }
        //如果比较对象是null false
        if (other == null) {
            return false;
        }
        //如果不是LLD返回错误
        if(other instanceof LinkedListDeque){
            return false;
        }
//        if (other.getClass() != this.getClass()) {
//            return false;
//        }
       //other转换为LinkedListDeque
        LinkedListDeque<T> o = (LinkedListDeque<T>) other;
        //大小不同返回false
        if (o.size() != this.size()) {
            return false;
        }
        //迭代进行对比
        for (int i=0;i<size;i++){
            if(this.get(i)!=o.get(i)){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        LinkedListDeque<Integer> L = new LinkedListDeque<>();

        L.addLast(20);
        L.addLast(40);




        LinkedListDeque<Integer> L3 = new LinkedListDeque<>();
        L3.addLast(40);
        L3.addLast(20);


        L.printDeque();

        L3.printDeque();
        System.out.println(L.equals(L3));

    }
}
