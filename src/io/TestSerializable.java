package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializable {

    public static void main(String[] args) {
        Student s = new Student("Nick", 1000L, "man",  "123456");
        FileOutputStream fo = null;
        ObjectOutputStream out = null;
        try {
            fo = new FileOutputStream("/home/beardnick/study/test/student");
            out = new ObjectOutputStream(fo);
            out.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fo != null){
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fi = null;
        ObjectInputStream in = null;
        Student student = null;
        try {
            fi = new FileInputStream("/home/beardnick/study/test/student");
            in = new ObjectInputStream(fi);
            student = (Student) in.readObject();
            System.out.println("student : "  + student);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if(fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
