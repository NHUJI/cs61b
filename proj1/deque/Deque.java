package deque;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    default  boolean isEmpty() {
        //为了单一出口考虑设置一个变量
        boolean isEmpty = false;
        if(size() == 0){
            isEmpty = true;
        }
        return isEmpty;
    }

}
