package org.clxmm.tree;

import com.sun.xml.internal.bind.v2.model.core.ID;
import sun.jvm.hotspot.debugger.Page;

import java.util.Comparator;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/15 8:02 下午
 */
public class TreeMap<K, V> {

    // 定义比较器
    private final Comparator<? super K> comparator;

    // 根结点
    private Entry<K, V> root;

    // 定义集合的长度
    private int size;

    // 空参构造
    public TreeMap() {
        this.comparator = null;
    }


    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    // 获取集合长度的方法
    public int getSize() {
        return size;
    }

    // get
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);

        return entry == null ? null : entry.v;
    }

    // 根据键获取entry 对象的方法
    public Entry<K, V> getEntry(Object key) {
        // 非空校验
        if (key == null) {
            throw new NullPointerException();
        }

        // 给根结点起名
        Entry<K, V> t = this.root;

        // 判断有没有比较器
        if (comparator != null) {
            K k = (K) key;

            // 循环
            while (t != null) {
                int cmp = comparator.compare(k, t.k);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t;
                }
            }

        } else {
            Comparable<K> k = (Comparable<K>) key;
            while (t != null) {
                int cmp = k.compareTo(t.k);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t;
                }
            }

        }

        // 如果找不到，
        return null;
    }

    // put
    public V put(K key, V value) {
        // 给根结点复制
        Entry<K, V> t = this.root;

        // 非空校验
        if (key == null) {
            throw new NullPointerException();
        }

        // 集合是否为空
        if (t == null) {
            // 创建新的根结点，并给根结点赋值
            Entry<K, V> entry = new Entry<>(key, value, null, null, null);
            root = entry;

            size++;

            return null;
        }

        // 集合不为空
        Entry<K, V> parent = t;  // 新增结点的父结点的位置
        int cmp = 0;

        if (comparator != null) {
            while (t != null) {

                //  给parent
                parent = t;
                // 判断键
                cmp = comparator.compare(key, t.k);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    // 说明找到了相同的值，用新的值替换旧的值，并把旧的值返回V
                    V v = t.v;
                    t.v = value;
                    return v;
                }
            }
        } else {
            Comparable<? super K> k = (Comparable<? super K>) key;
            while (t != null) {
                parent = t;
                cmp = k.compareTo(t.k);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    // 说明找到了相同的值，用新的值替换旧的值，并把旧的值返回V
                    V v = t.v;
                    t.v = value;
                    return v;
                }
            }
        }

        // 要添加的键值对键不重复，新增结点
        Entry<K, V> entry = new Entry<>(key, value, null, null, parent);

        if (cmp < 0) {
            parent.left = entry;
        } else {
            parent.right = entry;
        }

        size++;
        return null;
    }

    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        // 删除操作
        // 1， 删除的元素没有结点
        //2， 删除的元素只有左子结点
        // 3， 删除的元素只有右子结点
        //4， 删除的元素左子结点和右子节点都有

        if (entry.left == null && entry.right == null) {
            if (entry.parent.right == entry) {
                entry.parent.right = null;
            } else if (entry.parent.right == entry) {
                entry.parent.left = null;
            } else {
                root = null;
            }

        } else if (entry.left != null && entry.right == null) {
            //只有左子结点
            if (entry.parent.right == entry) {
                entry.parent.right = entry.left;
            } else if (entry.parent.left == entry) {
                entry.parent.left = entry.left;
            } else {
                root = entry.left;
            }
            // 让被删结点的子结点指向父结点
            entry.left.parent = entry.parent;

        } else if (entry.left == null && entry.right != null) {
            // 只有右子结点
            // 要删除的结点是父结点的右子结点
            if (entry.parent.right == entry) {
                entry.parent.right = entry.right;
            } else if (entry.parent.left == entry) {
                entry.parent.left = entry.right;
            } else {
                // 要删除的是根结点
                root = entry.right;
            }

            // 让被删结点的子结点指向父结点
            entry.right.parent = entry.parent;
        } else {  // 即右左子节点又有右子结点
            // 找后继结点
            Entry<K, V> target = entry.right;
            // 寻找被删除结点右子结点的最左子节点
            while (target.left != null) {
                target = target.left;
            }

            // 右子结点作为后继结点
            if (entry.right == target) {
                target.parent = entry.parent;
                if (entry.parent.right == entry) { // 要删除的结点是这个结点的右子结点
                    entry.parent.right = target;
                } else if (entry.parent.left == entry) {
                    entry.parent.left = target;
                } else {
                    root = target;
                }
                // 被删除的左子结点重新指向新的父结点
                entry.left.parent = target;
                target.left = entry.left;
            } else {
                //右子结点的最左子节点作为后继结点
                if (target.right == null) {
                    //  后继结点没有右子结点
                    target.parent.left = null;
                } else {

                    target.parent.left = target.right;
                    target.right = target.parent;

                }
                // 后继结点替换被删除结点
                if (entry.parent.right == entry) {
                    entry.parent.right = target;
                } else if (entry.parent.left == entry) {
                    entry.parent.left = target;
                } else {
                    root = target;
                }

                //被删除结点的左右子树需要指向后继结点
                entry.left.parent = target;
                entry.right.parent = target;

                target.left = entry.left;
                target.right = entry.right;

            }


        }

        size--;
        return entry.v;


    }

    public String toSting() {
        if (root == null) {
            return "{}";
        }

        String s = "{";
        String s11 = method(root);
        s = s + s11.substring(0, s11.length() - 2);
        return s;
    }

    // 1=a,2=w,
    public String method(Entry<K, V> entry) {
        String s = "";

        // 拼接左子结点

        if (entry.left != null) {
            s += method(entry.left);
        }

        //拼接中间

        s += entry.k + "=" + entry.v + ",";

        // 拼接右子结点
        if (entry.right != null) {
            s += method(entry.right);
        }


        return s;
    }

    class Entry<K, V> {
        K k; //键
        V v; // 值
        Entry<K, V> left; // 左子结点
        Entry<K, V> right; // 右子结点
        Entry<K, V> parent; // 父结点

        // 有参构造
        public Entry(K k, V v, Entry<K, V> left, Entry<K, V> right, Entry<K, V> parent) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }


}
