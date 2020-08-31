package com.retheviper.springbootsample.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

/**
 * Utility class for store file.
 *
 * @author retheviper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileStorage {

    /**
     * Store file data and returns stored file name.
     *
     * @param path             Path to store file
     * @param originalFileName Original file name
     * @param source           source data
     * @return Stored file name
     */
    public static String storeFile(final String path, final String originalFileName, final InputStream source) {
        final String storedFileName = String.format("%s.%s",
                UUID.randomUUID(),
                originalFileName.substring(originalFileName.lastIndexOf(".")));
        try (source) {
            Files.copy(source, Paths.get(path, storedFileName), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return storedFileName;
    }

    /**
     * Get stored file as base64 string.
     *
     * @param path           Path to store file
     * @param storedFileName Stored file name
     * @return base64 encoded string
     */
    public static String getFile(final String path, final String storedFileName) {
        try {
            return Base64.getEncoder()
                    .encodeToString(Files.readAllBytes(Paths.get(path, storedFileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
