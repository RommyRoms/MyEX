package model;

import model.downloader.AbstractDownloader;

public class DownloadFabric {
    private static AbstractDownloader result = null;

    private DownloadFabric(){}


    public static AbstractDownloader getDownloader(String format){
        AbstractDownloader.ALL_FORMATS.entrySet().stream()
                .filter(entry -> entry.getKey().equals(format))
                .forEach(entry ->
                                result = entry.getValue()
                );
        return result;
    }
}
