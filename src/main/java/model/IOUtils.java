package model;

import java.io.*;

/**
 * Created by ro on 13.10.2015.
 */
public class IOUtils {
    public static byte[] getByteArr(InputStream is){
        ByteArrayOutputStream byos = new ByteArrayOutputStream();

        try {

            int b = 0;
            while((b = is.read()) != -1){
                byos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byos.toByteArray();
    }

    public static void writeBytes(byte[] arr, String path) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(path);
        try{
            os.write(arr);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
