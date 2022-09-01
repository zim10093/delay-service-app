package core.service;

import java.nio.file.Path;
import java.util.List;

public interface QueryResultWriter {
    void writeToFile(Path path, List<String> lines);
}
