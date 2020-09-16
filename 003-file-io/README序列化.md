### 对象的序列化，反序列化
* 对象序列化，就是将Object转换成byte序列，反之，叫对象的反序列化
* 序列化流（ObjectOutputStream），是过滤流 ————》writeObject
    反序列化（ObjectInputStream）----》 readObject
    
* 序列化接口 （Serializable）
    对象必须实现序列化接口，才能进行序列化，否则将出现异常，
    这个接口，没有任何方法，只是一个标准
    
* transient 修饰的不会进行jvm的被序列化 也可以自己完成这个元素的序列化

参照 ArrayList 
```java
public class Student implements Serializable {

    private String stuno;

    private String stuname;

    // transient 修饰的不会进行jvm的被序列化 也可以自己完成这个元素的序列化
    private transient String stuage;


    // 序列化
     private void writeObject(java.io.ObjectOutputStream s) throws IOException, ClassNotFoundException {
         s.defaultWriteObject();  // 把jvm能默认序列化的元素进行序列化操作
         s.writeObject(stuage);  // 自己完成stuage 的序列化
     }

     // 反序列化
    private void readObject(java.io.ObjectInputStream s) throws  IOException,ClassNotFoundException {
         s.defaultReadObject(); // 将jvm能默认反序列化的元素进行反序列化操作
        Object i = s.readObject();
        this.stuage = String.valueOf(i); //完成自己stuage的反序列化操作
    }
}
```

* 序列化的子父构造函数的问题

序列化中子类和父类的调用问题
1. 一个类实现了序列化接口，其子类一定可以实现序列化


