package model;


import java.io.IOException;
import java.util.Map;

public interface Downloader {

    void download(String path, Map<String, String> map) throws IOException;
    Map<String,String > parse(String url) throws IOException;
}
