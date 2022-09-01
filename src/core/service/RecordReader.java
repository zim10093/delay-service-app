package core.service;

import java.nio.file.Path;
import java.util.List;

public interface RecordReader {
    List<String> readRecords(Path path);
}
