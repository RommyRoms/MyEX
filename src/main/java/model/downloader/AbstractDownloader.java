package model.downloader;


import model.Downloader;
import model.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractDownloader implements Downloader {

    public static final String DEFAULT_DIRECTORY = "D:/";
    public static String directory = DEFAULT_DIRECTORY;
    public static String url;
    public static final Map<String, AbstractDownloader>ALL_FORMATS;

    static {
        Map<String, AbstractDownloader> map = new HashMap<>();
        map.put("audio",new AudioDownloader());
        map.put("video",new VideoDownloader());
        map.put("images",new ImageDownloader());
        ALL_FORMATS = Collections.unmodifiableMap(map);
    }

    public static final String[] LIST_OF_FORMATS = {"audio","video","images","other"};


    protected void downLoadHelper(String directory, Map<String,String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String urlRoot = "http://www.ex.ua";
            byte[]arr = IOUtils.getByteArr(new URL(urlRoot + entry.getKey()).openStream());
            IOUtils.writeBytes(arr, directory + entry.getValue());
        }
    }

    protected Elements getElementsFromUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String hrefSelector = "a[href*=/get]";
        return document.select(hrefSelector);
    }

    @Override
    public void download(String path,Map<String, String> map) throws IOException {
        downLoadHelper(path,map);

    }

}
