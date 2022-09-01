package core.service.impl;

import core.dto.DelayRecord;
import core.service.RecordsParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DelayRecordsParserImpl implements RecordsParser<DelayRecord> {
    private static final int INDEX_OF_SERVICE = 1;
    private static final int INDEX_OF_TYPE = 2;
    private static final int INDEX_OF_ANSWER_TYPE = 3;
    private static final int INDEX_OF_DATES = 4;
    private static final int INDEX_OF_DELAY = 5;



    @Override
    public DelayRecord parse(String line) {
        String[] splitLine = line.split(" ");
        String[] splitService = splitLine[INDEX_OF_SERVICE].split("\\.");
        String[] splitType = splitLine[INDEX_OF_TYPE].split("\\.");

        int answerType = splitLine[INDEX_OF_ANSWER_TYPE].equals("N") ? 0 : 1;
        int serviceId = Integer.parseInt(splitService[0]);
        int typeId = Integer.parseInt(splitType[0]);

        int variation = 0;
        if (splitService.length == 2) {
            variation = Integer.parseInt(splitService[1]);
        }

        int category = 0;
        int subCategory = 0;
        if (splitType.length > 1) {
            category = Integer.parseInt(splitType[1]);
            if (splitType.length > 2) {
                subCategory = Integer.parseInt(splitType[2]);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(splitLine[INDEX_OF_DATES], formatter);

        int delay = Integer.parseInt(splitLine[INDEX_OF_DELAY]);

        DelayRecord delayRecord = new DelayRecord();
        delayRecord.setDelay(delay);
        delayRecord.setDate(date);
        delayRecord.setAnswerTypeId(answerType);
        delayRecord.setServiceId(serviceId - 1);
        delayRecord.setVariationId(variation);
        delayRecord.setQuestionTypeId(typeId - 1);
        delayRecord.setQuestionCategoriesId(category);
        delayRecord.setQuestionSubCategoriesId(subCategory);
        return delayRecord;
    }
}
