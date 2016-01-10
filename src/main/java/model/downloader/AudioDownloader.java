package model.downloader;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class AudioDownloader extends AbstractDownloader {

    private final List<String > audioFormats = Collections.unmodifiableList(
            Arrays.asList(".mp3", ".flac", ".m4p", ".ogg", ".wav", ".webm", ".aiff", ".au"));


    @Override
    public Map<String ,String> parse(String url) throws IOException {
        Elements links = getElementsFromUrl(url);
        Map<String,String> map = new TreeMap<>();
        for (Element link : links) {
            String linkAttr = "href";
            String href = link.attr(linkAttr);
            String name = link.text();
            audioFormats.stream().filter(name::contains) //if formats.contains(name)->lambda
                    .forEach(musicFormat -> map.put(href, name));
        }
        return map;
    }

}
