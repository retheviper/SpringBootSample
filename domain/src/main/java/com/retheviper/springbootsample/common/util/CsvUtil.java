package com.retheviper.springbootsample.common.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvUtil {

    private static final String CSV_SUFFIX = ".csv";

    public static <T> InputStream createCsv(final List<T> data) throws Exception {
        final Path path = Files.createTempFile(UUID.randomUUID().toString(), CSV_SUFFIX);
        try (Writer writer = new FileWriter(path.toString())) {
            final StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            beanToCsv.write(data);
        }
        return Files.newInputStream(path);
    }
}
