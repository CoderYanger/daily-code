package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//关于 Writer 的使用
public class Demo11 {
    public static void main(String[] args) {
        try(Writer writer=new FileWriter("./output.txt", true)) {
            //writer.write("hello world");
            BufferedWriter bufferedWriter=new BufferedWriter(writer);
            bufferedWriter.write("hello world");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
