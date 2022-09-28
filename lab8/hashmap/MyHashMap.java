package hashmap;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Objects.hash;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author NHUJI
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /*bin的容量，也是哈希值取余的最大值*/
    private int m = 3 ;
    /*用于存放bucket的bin，本身和存储的内容都是ArrayList，里面再存储Node*/
    private ArrayList<ArrayList> bin = new ArrayList<ArrayList>();

    /**Map的键值对数量*/
    int size = 0;
    /*装载因子，达到多少百分比就扩容*/
    private double loadFactor = 0.75;


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() { }

    public MyHashMap(int initialSize) { }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) { }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return null;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        new LinkedList<>();
        return null;
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        //清空bin来清空HashMap
        bin = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        int hash = hash(key) % m;
        //找到对应的bucket
        ArrayList<Node> sites = bin.get(hash);
        //循环bucket查看是否有key与bucket里存储的node的key想同的
        for (int i = 0; i < sites.size(); i++) {
           if(sites.get(i).key.equals(key)){
               return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hash = hash(key) % m;
        ArrayList<Node> sites = bin.get(hash);
        for (int i = 0; i < sites.size(); i++) {
            if(sites.get(i).key.equals(key)){
                return sites.get(i).value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(bin.isEmpty()){
            initialBin(m);
        }
        //键值的哈希值
        int hash = hash(key) % m;

        ArrayList<Node> setArray = new ArrayList<Node>() ;
        if(bin.get(hash)!= null){
            System.out.println(hash);
            setArray = bin.get(hash);
            size=size+1;
        } else {
            System.out.println(hash);
             Node put = new Node(key,value);
            //把带有node新ArrayList放入bin
            setArray.add(put);
            size=size+1;
        }
        setArray.add(new Node(key,value));
        bin.set(hash,setArray);



    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set keySet = new HashSet();
        for(int i = 0; i < bin.size(); i++) {
            //由于要算的bin的第几个bucket的size所以使用get（i）
           for(int j = 0; j < bin.get(i).size();j++){
              Node  node =(Node) bin.get(i).get(j);

              keySet.add(node.key);

           }
        }
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public  void initialBin (int size){

        for (int i=1; i<=size; i++){
            ArrayList<Node> nothing = new ArrayList<>();
            bin.add(nothing);

        }
    }
    public static void main(String[] args) {

    MyHashMap< Integer,String> map = new MyHashMap<>();


    map.put(3,"hahaha");
    map.put(2,"haha");
    map.put(4,"hahaha");
    map.put(1,"ha");
    map.put(5,"hahaha");
//    System.out.println(map.containsKey(4));

    System.out.println(map.keySet());
    }
}
