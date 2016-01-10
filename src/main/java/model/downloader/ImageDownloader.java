package model.downloader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Created by mayun8 on 21.12.2015.
 */
public class ImageDownloader extends AbstractDownloader {

    public static final List<String > imageFormats = Collections.unmodifiableList(
            Arrays.asList(".apt", ".bmp", ".dds", ".djvu", ".dng", ".gbr", ".gif", ".gz", ".iff", ".iso",
                    ".jpeg", ".jpg", ".kdc", ".mng", ".php", ".png", ".pot", ".psd", ".pspimage", ".scr", ".tga",
                    ".thm", ".tif", ".tiff", ".vst", ".xcf",".gif"));


    @Override
    public Map<String ,String> parse(String url) throws IOException {
        Elements links = getElementsFromUrl(url);
        Map<String,String> map = new TreeMap<>();
        for (Element link : links) {
            String linkAttr = "href";
            String href = link.attr(linkAttr);
            String name = link.text();
            imageFormats.stream().filter(name::contains) //if formats.contains(name)->lambda
                    .forEach(musicFormat -> map.put(href, name));
        }
        return map;
    }
}
