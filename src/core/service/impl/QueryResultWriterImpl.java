package core.service.impl;

import core.service.QueryResultWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class QueryResultWriterImpl implements QueryResultWriter {
    @Override
    public void writeToFile(Path path, List<String> lines) {
        try {
           Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file " + path, e);
        }
    }
}
