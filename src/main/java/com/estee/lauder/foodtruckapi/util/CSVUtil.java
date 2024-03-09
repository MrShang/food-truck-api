package com.estee.lauder.foodtruckapi.util;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CSVUtil {

    private static final CSVParser parser = new CSVParserBuilder()
            .withSeparator(',')
            .build();

    public static List<String[]> read(URI uri) throws URISyntaxException, IOException, CsvValidationException {
        List<String[]> list = new ArrayList<>();
        Path filePath = Paths.get(uri);
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build()) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }
}
