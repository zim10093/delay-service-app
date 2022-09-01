package core.service.strategy.handler;

import core.dao.RecordDao;
import core.dto.DelayRecord;
import core.service.RecordsParser;

public class DelayRecordHandlerImpl implements RecordHandler {
    private final RecordDao recordDao;
    private final RecordsParser<DelayRecord> delayRecordsParser;

    public DelayRecordHandlerImpl(RecordDao recordDao, RecordsParser<DelayRecord> delayRecordsParser) {
        this.recordDao = recordDao;
        this.delayRecordsParser = delayRecordsParser;
    }

    @Override
    public void process(String line) {
        recordDao.saveDelayRecord(delayRecordsParser.parse(line));
    }
}
