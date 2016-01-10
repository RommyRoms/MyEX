package model.downloader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Created by mayun8 on 21.12.2015.
 *
 */
public class VideoDownloader extends AbstractDownloader {

    private final List<String > videoFormats = Collections.unmodifiableList(
            Arrays.asList(".wmv", ".vob", ".vid", ".vcd", ".swf", ".rm", ".mpg", ".mpeg", ".mov", ".mp4", ".mod",
                    ".mkv", ".f4v", ".dat", ".avi", ".asx", ".asf", ".3gp", ".3g2"));

    @Override
    public Map<String ,String> parse(String url) throws IOException {
        Elements links = getElementsFromUrl(url);
        Map<String,String> map = new TreeMap<>();
        for (Element link : links) {
            String linkAttr = "href";
            String href = link.attr(linkAttr);
            String name = link.text();
            videoFormats.stream().filter(name::contains) //if formats.contains(name)->lambda
                    .forEach(musicFormat -> map.put(href, name));
        }
        return map;
    }
}
