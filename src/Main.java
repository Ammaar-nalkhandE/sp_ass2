import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
            File file_location = new File("D:\\VU sem5\\SPL\\Ass2\\asm_code.txt");
            MotTable.AllTables();
            try{
                BufferedReader asm = new BufferedReader(new FileReader(file_location));
                String st;
                while ((st = asm.readLine()) != null)

                    // Print the string
                    System.out.println(st);
            }
            catch (Exception e){

                System.out.println(e);
            }

    }
}