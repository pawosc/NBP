package pl.parser.nbp.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

    public URL urlBuilder(String baseUrl, String fileId, String fileType) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();

        return new URL(builder
                .append(baseUrl)
                .append(fileId)
                .append(fileType)
                .toString());
    }
}
