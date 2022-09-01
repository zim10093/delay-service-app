package core.service.impl;

import core.dto.QueryRecord;
import core.service.RecordsParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QueryRecordParserImpl implements RecordsParser<QueryRecord> {
    private static final int INDEX_OF_SERVICE = 1;
    private static final int INDEX_OF_TYPE = 2;
    private static final int INDEX_OF_ANSWER_TYPE = 3;
    private static final int INDEX_OF_DATES = 4;

    @Override
    public QueryRecord parse(String line) {
        String[] splitQuery = line.split(" ");
        String[] splitService = splitQuery[INDEX_OF_SERVICE].split("\\.");
        String[] splitType = splitQuery[INDEX_OF_TYPE].split("\\.");
        String[] splitData = splitQuery[INDEX_OF_DATES].split("-");

        int answerType = splitQuery[INDEX_OF_ANSWER_TYPE].equals("N") ? 0 : 1;
        int serviceId = splitService[0].equals("*") ? 0 : Integer.parseInt(splitService[0]);
        int typeId =  splitType[0].equals("*") ? 0 : Integer.parseInt(splitType[0]);

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(splitData[0], formatter);
        LocalDate toDate;
        if (splitData.length == 2) {
            toDate = LocalDate.parse(splitData[1], formatter);
        } else {
            toDate = fromDate;
        }

        QueryRecord queryRecord = new QueryRecord();
        queryRecord.setFromDate(fromDate.minusDays(1));
        queryRecord.setToDate(toDate.plusDays(1));
        queryRecord.setAnswerTypeId(answerType);
        queryRecord.setServiceId(serviceId - 1);
        queryRecord.setVariationId(variation);
        queryRecord.setQuestionTypeId(typeId - 1);
        queryRecord.setQuestionCategoriesId(category);
        queryRecord.setQuestionSubCategoriesId(subCategory);
        return queryRecord;
    }
}
