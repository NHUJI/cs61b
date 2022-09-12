package bstmap;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable, V>  implements Map61B<K, V>  {
    //用于存储Map的大小以供快速返回
    int size = 0;
    //BST的root 暂时不知道什么用
    private Node root;             // root of BST
    //BST的一个节点，整个BSTMap其实就是将真正的BST排序的key当作存储内容，val当作map对应的值，应该把整个BSTMap当成BST来实现
    private class Node {
        /** Stores the key of the key-value pair of this node in the BST. */
        private K key;
        /** Stores the value of the key-value pair of this node in the BSY. */
        private V val;
        /** Stores the left, right node in the BST. */
        private Node left, right;//节点的左右节点
        // 注意BST本身就会不平衡和lab7不用做删除功能，因此只考虑添加时断开和连接就行了

        public Node(K key, V val) {
            //将值存储到本node
            this.key = key;
            this.val = val;
        }
    }



    /**重置整个BSTMap*/
    @Override
    public void clear() {
        size = 0;
        root = null;

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        //如果找不到对应的key就不能使用equals，所以进行验证
        if(getKey(root,key)!=null){
            return getKey(root,key).equals(key);
        }else return false;

    }

    @Override
    public V get(K key) {
        return get(root, key);
    }
    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }
    /**BSTMap中是否有该key的辅助函数*/
    private K getKey(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return getKey(x.left, key);
        else if (cmp > 0) return getKey(x.right, key);
        else              return x.key;
    }

    @Override
    public int size() {
        return size;
    }


    //放入第一个值使用的put
    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(key, val,root);
        size = size + 1;
    }


    public Node put(K key, V val, Node x) {
        //如果x节点左/右没有更多节点就放下当前添加的节点
        if (x == null) return new Node(key, val);
        //比较大小以决定放置位置，小放左边、大放右边
        //由于K extends String了所以也具有比较的方法
        int cmp = key.compareTo(x.key);
        //如果小于当前的x节点就把新的值放在
        if      (cmp < 0) x.left  = put(key, val, x.left);
        else if (cmp > 0) x.right = put(key, val, x.right);
        else {
                x.val   = val;
                size = size + 1;
        }


        return x;

    }

    /**将值放入BSTMap
     * */
    //假装已经完成了BST部分，而编写好put方法

    //不需要在Lab里实现所以抛出不支持
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
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
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] args) {
        int i = 0;
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi" + 0, 1+0);
        b.put("a" , 231);
        b.put("bi" , 42);
        b.put("c" , 1242);
        b.put("b" ,13442 );
        b.put("apple" , 114514);
        b.put("zoo" , 21344);

        b.containsKey("hi" + i);
        System.out.println(b.containsKey("hi" + i));

    }

}
