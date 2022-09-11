package bstmap;

import java.util.Iterator;
import java.util.Set;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Key operations are get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key.
 */
public class ULLMap<K, V>  implements Map61B<K, V> {

    int size = 0;

    /** Returns the value corresponding to KEY or null if no such value exists. */
    /* 代码解释：双*是文档，所以不使用，双重验证了list和要找的key本身是不是空的然后返回key对应的值*/
    public V get(K key) {
        if (list == null) {
            return null;
        }
        Entry lookup = list.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }

    /** Removes all of the mappings from this map. */
    /*清空了list以重置map数据*/
    @Override
    public void clear() {
        size = 0;
        list = null;
    }

    /** Inserts the key-value pair of KEY and VALUE into this dictionary,
     *  replacing the previous value associated to KEY, if any. */
    public void put(K key, V val) {
        //list不为空时查看key是否已存在
        if (list != null) {
            Entry lookup = list.get(key);
            if (lookup == null) {
                list = new Entry(key, val, list);
                //修改了size增加的逻辑
                size = size + 1;
            } else {
                lookup.val = val;
            }
        } else {
            list = new Entry(key, val, list);
            size = size + 1;
        }
    }

    /** Returns true if and only if this dictionary contains KEY as the
     *  key of some key-value pair. */
    public boolean containsKey(K key) {
        if (list == null) {
            return false;
        }
        return list.get(key) != null;
    }

    @Override
    public Iterator<K> iterator() {
        return new ULLMapIter();
    }

    /** Keys and values are stored in a linked list of Entry objects.
     *  This variable stores the first pair in this linked list. */
    /*存放linked list存放的类型为Entry 一个Entry包含键值和他的下一个地址
    * 另外注意这个list是自己实现的并不是Java内置的
    * 只是Entry对象的一个实例名字而已
    *
    *
    * */
    private Entry list;

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    /*Entry代表linked list的一个node，Entry一个接一个组成LL*/
    private class Entry {

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        Entry(K k, V v, Entry n) {
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        Entry get(K k) {
            //使用递归来找到k对应的值
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            //修改了错误的逻辑以正常返回get的值
            return next.get(k);
        }

        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        Entry next;

    }

    /** An iterator that iterates over the keys of the dictionary. */
    private class ULLMapIter implements Iterator<K> {

        /** Create a new ULLMapIter by setting cur to the first node in the
         *  linked list that stores the key-value pairs. */
        public ULLMapIter() {
            cur = list;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            K ret = cur.key;
            cur = cur.next;
            return ret;
        }

        /** Stores the current key-value pair. */
        private Entry cur;

    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        ULLMap test = new ULLMap();
        test.put(1,"a");
        test.put(4,"d");
        test.put(2,"b");
        test.put(3,"c");

        test.get(1);
        System.out.println(test.get(1));

    }
}
