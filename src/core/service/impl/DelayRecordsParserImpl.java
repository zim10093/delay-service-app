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
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String PART_OF_RECORD_SPLITERATOR = " ";
    private static final String SUB_TYPE_SPLITERATOR = "\\.";
    private static final String CHAR_OF_NEW_QUESTION = "N";



    @Override
    public DelayRecord parse(String line) {
        String[] splitLine = line.split(PART_OF_RECORD_SPLITERATOR);
        String[] splitService = splitLine[INDEX_OF_SERVICE].split(SUB_TYPE_SPLITERATOR);
        String[] splitType = splitLine[INDEX_OF_TYPE].split(SUB_TYPE_SPLITERATOR);

        int answerType = splitLine[INDEX_OF_ANSWER_TYPE].equals(CHAR_OF_NEW_QUESTION) ? 0 : 1;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
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
