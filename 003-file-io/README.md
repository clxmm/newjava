### file 类
* java.io.File 类用于表示文件/目录
File 类只用于表示文件（目录）的信息（名称，大小），不能用于文件内容的访问


#### RandomAccessFile
* java 提供的对文件内容的访问，既可以读取文件，也可以写文件。
RandomAccessFile 支持随机访问文件，可以访问文件的任意位置


### java 文件模型
* 在硬盘上的文件是 byte byte 存储的，是数据的集合
* 打开文件

*  有两种模式 “rw” 读写 “r”只读
  RandomAccessFile raf = new RandomAccessFile(file,"rw")
  文件指针,打开文件时指针在开头 pointer = 0

* 写方法
    
    raf.write(int)  --> 只写一个字节(后8位)，同时指针指向下一个位置，
准备再次写入

* 读方法
   int b = raf.read()   --> 读一个字节
   
* 文件读写完成后一定要关闭   




### io流（输入流，输出流）
* 字节流和字符流
1. 字节流 InputStream OutputStream
 InputStream 抽象了应用程序读取数据的方式，
 OutputStream 写出数据的方式
 
2. eof = end 读到-1 就读到了结尾

3. 输入流  基本的方法
    int b = in.read() ;读取一个字节无符号填充到int低八位，-1是eof
    in.read(byte[] buf) : 读取数据填充的字节数组buf
    in.read(byte[] buf,int start,int end)
 
4. 输出流
    out.write(int b) : 写出一个byte到流，b的低8位
    out.write(byte[] buf) : 将buf字节数组写入到流
    out.write(byte[] buf,int start,int end)
    
5. FileInputStream  --> 具体实现了在文件上读取数据

6. FleOutputStream -> 实现了向文件写出byte数据的方法    

7. DataOutputStream /DataInputStream
 对“流”的扩展，可以更加方便的读取int long ,字符等类型数据
 
 * DataOutputStream
    writeInt/writeDouble(double v)/writeUTF(String str) 
    
8. BufferedInputStream 和 BufferedOutputStream
 
    这两个流类为Io提供了带缓冲区的操作，一般打开文件进行写入或读取操作时，都会加上缓冲，
    这种流模式提高了Io的性能
    从应用程序中把输入放入文件
    fileOutputStream  -> write() 一滴滴把水转移过去
    DataOutputStream -> writeXxx()  一瓢一瓢的把水转移
    BufferedOutputStream -> write()  先一瓢一瓢放入桶里，在放入缸中
    
    
    
## 字符流
### 1. 编码问题
### 2，认识文本和文本文件
java的文本（char） 是16位无符号整数，是字符unicode编码（双字节编码），
文件是 byte byte ···的数据序列
文本文件是文本（char） 序列，按照某种编码方案（utf-8，gbk序列化为byte的存储结构）
### 3，字符流 （Reader，Writer）
字符的处理，一次处理一个字符
字符的底层依然是基本的字节序列
inputStreamReader 完成byte流解析为char流，按照编码解析
outputStreamWrite 提供char流到byte流，按照编码处理


FileReader/FileWrite


#### 字符流的过滤器
bufferedReader ----> readerLine 
bufferedWrite/printWrite -----> 

 
 


 