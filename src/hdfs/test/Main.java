package hdfs.test;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.mortbay.jetty.servlet.Context;

import java.io.*;



public class Main {


    public static void testNewFolder(String IP, String port) throws IOException {

        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        conf.set("fs.default.name", "hdfs://192.168.1.35:8020/user/cloudera");

        FileSystem fileSystem = FileSystem.get(conf);

        Path myDirPath = new Path("/user/cloudera/my_dir");

        if (fileSystem.exists(myDirPath)) {
            System.out.println("Dir " + myDirPath + "already exist!");
        } else {
            boolean isSuccess = fileSystem.mkdirs(myDirPath);
            if (!isSuccess) throw new RuntimeException("Can`t create folder");
        }
        fileSystem.delete(myDirPath, true);
    }

    public static void testNewFile(String IP, String port) throws IOException {
        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        conf.set("fs.default.name", "hdfs://192.168.1.35:8020/user/cloudera");

        FileSystem fileSystem = FileSystem.get(conf);

        Path myNewFile = new Path("/user/cloudera/my_dir/myNewFile.txt");
        if (fileSystem.exists(myNewFile)) {
            System.out.println("File " + myNewFile + "already exist!");
        } else {
            boolean isSuccess = fileSystem.createNewFile(myNewFile);
            if (!isSuccess) throw new RuntimeException("Can`t create file");
        }
        fileSystem.delete(myNewFile, true);
    }

    public static void testEditNewFile(String IP, String port) throws IOException {
        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        conf.set("fs.default.name", "hdfs://192.168.1.35:8020/user/cloudera");
        FileSystem fileSystem = FileSystem.get(conf);

        Path myNewFile2 = new Path("/user/cloudera/my_dir/myNewFile2.txt");
        if (fileSystem.exists(myNewFile2)) {
            System.out.println("File " + myNewFile2 + "already exist!");
        } else {
            boolean isSuccess = fileSystem.createNewFile(myNewFile2);
            if (!isSuccess) throw new RuntimeException("Can`t create file");
        }
        String text = "Never gonna give you up " + "Never gonna let you down";

        System.out.println("Write to file text: " + text);

        FSDataOutputStream out = fileSystem.append(myNewFile2);
        out.writeChars(text);
        out.flush();
        out.close();

        FSDataInputStream inputStream = fileSystem.open(myNewFile2);

        String out_text = IOUtils.toString(inputStream);
        System.out.println("Read from file text: " + out_text);

        if (text.equals(out_text)) {
            System.out.println("success");
        } else {
            System.out.println("wops!");
        }

        fileSystem.delete(myNewFile2, true);


    }
    public static void testCpyFile(String IP, String port) throws IOException {
        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        conf.set("fs.default.name", "hdfs://192.168.1.35:8020/user/cloudera");

        FileSystem fileSystem = FileSystem.get(conf);
        // создаем 2 папки
        Path myDirPath1 = new Path("/user/cloudera/my_dir1");

        if (fileSystem.exists(myDirPath1)) {
            System.out.println("Dir " + myDirPath1 + "already exist!");
        } else {
            boolean isSuccess = fileSystem.mkdirs(myDirPath1);
            if (!isSuccess) throw new RuntimeException("Can`t create folder");
        }
        Path myDirPath2 = new Path("/user/cloudera/my_dir2");

        if (fileSystem.exists(myDirPath2)) {
            System.out.println("Dir " + myDirPath2 + "already exist!");
        } else {
            boolean isSuccess = fileSystem.mkdirs(myDirPath2);
            if (!isSuccess) throw new RuntimeException("Can`t create folder");
        }

        //создаем файлик в одной из папок
        Path myNewFile1 = new Path("/user/cloudera/my_dir1/myNewFile1.txt");
        if (fileSystem.exists(myNewFile1)) {
            System.out.println("File " + myNewFile1 + "already exist!");
        } else {
            boolean isSuccess = fileSystem.createNewFile(myNewFile1);
            if (!isSuccess) throw new RuntimeException("Can`t create file");
        }


        FileUtil.copy(fileSystem,  new Path("/user/cloudera/my_dir1/myNewFile1.txt"), fileSystem, new Path("/user/cloudera/my_dir2"), false, conf);

        //проверка на то что скопировалось
        Path myNewFile2 = new Path("/user/cloudera/my_dir2/myNewFile1.txt");
        if (fileSystem.exists(myNewFile2)) {
            System.out.println("File " + myNewFile2 + " copy success!");
        } else {
            System.out.println("wops!");
        }

        fileSystem.delete(myDirPath1, true);
        fileSystem.delete(myDirPath2, true);

        System.out.println("Folders are deleted.");

    }
    public static void main(String[] args) throws IOException {
        String IP;
        String port;


        System.setProperty("HADOOP_USER_NAME", "hdfs");
        //testEditNewFile();
        // create folder
        //testNewFolder();
        //create new file
        //testCpyFile();
        // edit file

    }
}





