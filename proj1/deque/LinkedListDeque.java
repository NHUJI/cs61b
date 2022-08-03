package deque;

public class LinkedListDeque<LochNess> {
    //StuffNode就是具体的数节点
    private  class StuffNode {
        public LochNess item;
        //既然是Deque，所以添加向前的点
        public StuffNode last;
        public StuffNode next;

        public StuffNode(LochNess i, StuffNode l, StuffNode n) {
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
//   public LinkedListDeque(LochNess x) {
//        sentinel = new StuffNode(63, null,null);
//        sentinel.next = new StuffNode(x, null,null);
//        sentinel.next.last = sentinel.last;
//        sentinel.next.next = sentinel;
//        size = 1;
//    }

    /** Adds x to the front of the list.先不处理 */
    public void addFirst(LochNess x) {
        //把sentinel的下一个修改为新添加的
        sentinel.next = new StuffNode(x, sentinel,sentinel.next);
        //把原本sentinel.next的last改为新添加的
        sentinel.next.next.last = sentinel.next;
        size = size + 1;
    }

    /** Returns the first item in the list. */
    public LochNess getFirst() {
        return sentinel.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(LochNess x) {
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
    public LochNess removeLast(){
        LochNess  last = null;
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
     * 删掉Deque最后一个值*/
    public LochNess removeFirst(){
        LochNess  first = null;
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
    public LochNess get(int index){
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
    public LochNess getRecursive(int index){
        if(index>size){
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
    public LochNess getRecursive(int index,StuffNode node){
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }



    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        LinkedListDeque<Integer> L = new LinkedListDeque<>();

        L.addLast(20);
        L.addLast(40);
        L.addLast(33);
        L.addLast(34);
        L.addLast(39);
        L.addLast(28);

        L.printDeque();

        System.out.println(L.getRecursive(5));
        L.printDeque();

    }
}
