package model;

import model.downloader.AbstractDownloader;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by mayun8 on 21.12.2015.
 */
public class MakeDownload implements Runnable {
    Map<String,String> map;

    public MakeDownload( Map<String,String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            download(AbstractDownloader.directory, map);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    protected void download(String directory, Map<String,String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String urlRoot = "http://www.ex.ua";
            byte[]arr = IOUtils.getByteArr(new URL(urlRoot + entry.getKey()).openStream());
            IOUtils.writeBytes(arr, directory + entry.getValue());
        }
    }
}
