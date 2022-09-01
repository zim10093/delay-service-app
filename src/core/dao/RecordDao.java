package core.dao;

import core.dto.DelayRecord;
import core.dto.QueryRecord;

public interface RecordDao {
    String findAvgDelayByCategoryAndDate(QueryRecord queryRecord);

    void saveDelayRecord(DelayRecord delayRecord);
}
