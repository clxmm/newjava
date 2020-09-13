package org.clxmm;

import java.io.UnsupportedEncodingException;

/**
 * @author clx
 * @date 2020-09-06 21:42
 */
public class Test1Encode {


    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = "楚留香abc";

        byte[] bytes = s.getBytes();  // 用的是项目的默认编码
        // utf -8 中文3个 英文1个
        for (byte aByte : bytes) {
            // 把字节（转为int）以16进制的方式显示
            System.out.print(Integer.toHexString(aByte & 0xff) + " ");
            // e6 a5 9a e7 95 99 e9 a6 99 61 62 63
        }

        System.out.println();
        // gbk 中文占2个字符 英文1个
        bytes = s.getBytes("gbk");
        for (byte aByte : bytes) {
            // 把字节（转为int）以16进制的方式显示
            System.out.print(Integer.toHexString(aByte & 0xff) + " ");
            // b3 fe c1 f4 cf e3 61 62 63
        }

        System.out.println();
        // java 是双字节编码  utf-16be
        // utf-16be 中文 英文 2个字节
        bytes = s.getBytes("utf-16be");
        for (byte aByte : bytes) {
            // 把字节（转为int）以16进制的方式显示
            System.out.print(Integer.toHexString(aByte & 0xff) + " ");
            // 69 5a 75 59 99 99 0 61 0 62 0 63
        }

        /**
         *   把字节转为字符串时，也需要用对应的编码格式
         */

        String s1 = new String(bytes,"utf-16be");

        System.out.println();
        System.out.println(s1);


    }
    /**
     * 文本文件也是字节序列，可以是任意编码的字节序列 
     */



}
