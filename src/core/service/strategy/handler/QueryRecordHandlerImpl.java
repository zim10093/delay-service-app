package core.service.strategy.handler;

import core.dao.RecordDao;
import core.dto.QueryRecord;
import core.service.RecordsParser;
import java.util.List;

public class QueryRecordHandlerImpl implements RecordHandler {
    private final RecordDao recordDao;
    private final RecordsParser<QueryRecord> recordsParser;
    private final List<String> tempStorage;

    public QueryRecordHandlerImpl(RecordDao recordDao, RecordsParser<QueryRecord> recordsParser,
                                  List<String> tempStorage) {
        this.recordDao = recordDao;
        this.recordsParser = recordsParser;
        this.tempStorage = tempStorage;
    }

    @Override
    public void process(String line) {
        tempStorage.add(recordDao.findAvgDelayByCategoryAndDate(recordsParser.parse(line)));
    }
}
