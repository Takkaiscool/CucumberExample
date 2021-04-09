package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    FileReader fileReader;
    public static Properties properties;
    public PropertiesFileReader(String path){
        try {
            fileReader=new FileReader(path);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {
            properties=new Properties();
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
