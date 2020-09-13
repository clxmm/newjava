package org.clxmm.util;

import java.io.File;
import java.io.IOException;

/**
 * file 过滤 遍历
 * @author clx
 * @date 2020-09-07 20:39
 */
public class FileUtil {


    /**
     * 遍历 指定目录下 （包括其子目录下）的所有文件
     * @param dir
     * @throws IOException
     */
    public static void listDirectory(File dir) throws IOException {

        if(!dir.exists()) {
            throw new IllegalArgumentException("目录: "+dir+" 不存在 ");
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("目录: "+dir+" 不是目录 ");
        }
        // .list() 列出当前目录下的子目录和文件
//        String[] list = dir.list();
//        for (String s : list) {
//            System.out.println(s);
//        }

        File[] files = dir.listFiles();

        if (files!=null && files.length>0) {
            for (File file : files) {
                if (file != null) {
                    if (file.isDirectory()) {
                        //
                        listDirectory(file);
                    } else {
                        System.out.println(file);
                    }
                }
            }
        }




    }


    public static void main(String[] args) throws IOException {
        File file = new File("D:\\resourceStudy\\imooc\\test\\");
        listDirectory(file);
    }


}
