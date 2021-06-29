package com.retheviper.springbootsample.api.v1.util;

import com.retheviper.springbootsample.common.constant.type.MimeType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.Map;

import static java.util.Map.entry;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    private static final Map<String, MimeType> MIME_TYPE = Map.ofEntries(
            entry("csv", MimeType.CSV)
    );

    /**
     * Create file download response by filename and it's data.
     *
     * @param filename Name of file (contains suffix)
     * @param data     File data
     * @return Streaming file download response
     */
    public static ResponseEntity<Resource> createByInputStream(final String filename, final InputStream data) {
        final Resource resource = new InputStreamResource(data);
        final String suffix = filename.substring(filename.indexOf(".") + 1).toLowerCase();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MIME_TYPE.get(suffix).getValue()))
                .header("Content-Disposition", String.format("attachment; filename=%s", filename))
                .body(resource);
    }
}
