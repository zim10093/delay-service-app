package core;

import core.dao.RecordDao;
import core.dao.RecordDaoImpl;
import core.dto.DelayRecord;
import core.dto.QueryRecord;
import core.service.RecordsParser;
import core.service.impl.DelayRecordsParserImpl;
import core.service.impl.QueryRecordParserImpl;
import core.service.impl.QueryResultWriterImpl;
import core.service.impl.RecordReaderImpl;
import core.service.strategy.RecordStrategy;
import core.service.strategy.RecordStrategyImpl;
import core.service.strategy.handler.DelayRecordHandlerImpl;
import core.service.strategy.handler.QueryRecordHandlerImpl;
import core.service.strategy.handler.RecordHandler;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_PATH = "resource/input.txt";
    private static final String OUTPUT_PATH = "resource/output.txt";

    public static void main(String[] args) {
        RecordDao recordsDao = new RecordDaoImpl();
        RecordsParser<DelayRecord> delayRecordParser = new DelayRecordsParserImpl();
        RecordsParser<QueryRecord> queryRecordParser = new QueryRecordParserImpl();

        List<String> tempStorage = new ArrayList<>();
        Map<Character, RecordHandler> recordHandlerMap = new HashMap<>();
        recordHandlerMap.put('C', new DelayRecordHandlerImpl(recordsDao, delayRecordParser));
        recordHandlerMap.put('D', new QueryRecordHandlerImpl(
                recordsDao, queryRecordParser, tempStorage));
        RecordStrategy recordStrategy = new RecordStrategyImpl(recordHandlerMap);

        List<String> records = new RecordReaderImpl().readRecords(Path.of(INPUT_PATH));
        records.forEach(recordStrategy::process);

        new QueryResultWriterImpl().writeToFile(Path.of(OUTPUT_PATH),
                        tempStorage.stream().map(String::valueOf).toList());
    }
}
