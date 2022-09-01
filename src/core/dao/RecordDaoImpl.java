package core.dao;

import core.db.Storage;
import core.dto.DelayRecord;
import core.dto.QueryRecord;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RecordDaoImpl implements RecordDao {
    private static final int MAX_NUMBERS_OF_SERVICES = 9;
    private static final int MAX_NUMBERS_OF_VARIATIONS = 3;
    private static final int MAX_NUMBERS_OF_TYPES = 9;
    private static final int MAX_NUMBERS_OF_CATEGORIES = 20;
    private static final int MAX_NUMBERS_OF_SUB_CATEGORIES = 5;
    private static final String EMPTY_RESULT = "-";
    @Override
    public String findAvgDelayByCategoryAndDate(QueryRecord queryRecord) {
        int answerType = queryRecord.getAnswerTypeId();

        int fromServiceId = 0;
        int toServiceId = MAX_NUMBERS_OF_SERVICES;
        if (queryRecord.getServiceId() != -1) {
            fromServiceId = queryRecord.getServiceId();
            toServiceId = fromServiceId;
        }

        int fromVariation = queryRecord.getVariationId();
        int toVariation = MAX_NUMBERS_OF_VARIATIONS;
        if (fromVariation != 0) {
            toVariation = fromVariation;
        }

        int fromQuestionTypeId = 0;
        int toQuestionTypeId = MAX_NUMBERS_OF_TYPES;
        if (queryRecord.getQuestionTypeId() != -1) {
            fromQuestionTypeId = queryRecord.getQuestionTypeId();
            toQuestionTypeId = fromQuestionTypeId;
        }


        int fromCategory = queryRecord.getQuestionCategoriesId();
        int toCategory = MAX_NUMBERS_OF_CATEGORIES;
        if (fromCategory != 0) {
            toCategory = fromCategory;
        }
        int fromSubCategory = queryRecord.getQuestionSubCategoriesId();
        int toSubCategory = MAX_NUMBERS_OF_SUB_CATEGORIES;
        if (fromSubCategory != 0) {
            toSubCategory = fromSubCategory;
        }

        AtomicLong sumDelay = new AtomicLong(0);
        AtomicInteger countDelay = new AtomicInteger(0);
        for (int j = fromServiceId; j <= toServiceId; j++) {
            for (int k = fromVariation; k <= toVariation; k++) {
                for (int l = fromQuestionTypeId; l <= toQuestionTypeId; l++) {
                    for (int m = fromCategory; m <= toCategory; m++) {
                        for (int n = fromSubCategory; n <= toSubCategory; n++) {
                            Storage.records[answerType][j][k][l][m][n].entrySet()
                                    .stream()
                                    .filter(e -> e.getKey().isAfter(queryRecord.getFromDate())
                                            && e.getKey().isBefore(queryRecord.getToDate()))
                                    .forEach(e -> {
                                        sumDelay.addAndGet(e.getValue());
                                        countDelay.getAndIncrement();
                                    });
                        }
                    }
                }
            }
        }
        return sumDelay.get() == 0 ?
                EMPTY_RESULT : String.valueOf(sumDelay.get() / countDelay.get());
    }

    public void saveDelayRecord(DelayRecord delayRecord) {
        Storage.records[delayRecord.getAnswerTypeId()]
                [delayRecord.getServiceId()]
                [delayRecord.getVariationId()]
                [delayRecord.getQuestionTypeId()]
                [delayRecord.getQuestionCategoriesId()]
                [delayRecord.getQuestionSubCategoriesId()]
                .put(delayRecord.getDate(), delayRecord.getDelay());
    }
}
