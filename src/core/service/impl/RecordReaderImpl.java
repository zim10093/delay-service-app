package core.service.impl;

import core.service.RecordReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RecordReaderImpl implements RecordReader {
    public List<String> readRecords(Path path) {
        List<String> strings;
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file " + path, e);
        }
        int numberOfRecords = Integer.parseInt(strings.remove(0));
        if (strings.size() != numberOfRecords) {
            throw new RuntimeException("Invalid input data, wrong number of records. Path of file: "
                    + path);
        }
        return strings;
    }
}
