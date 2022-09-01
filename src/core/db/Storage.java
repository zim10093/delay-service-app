package core.db;

import java.time.LocalDate;
import java.util.HashMap;

public class Storage {
    private static  final int NUMBERS_OF_ANSWER_TYPES = 2;
    private static  final int NUMBERS_OF_SERVICES = 10;
    private static  final int NUMBERS_OF_VARIATIONS = 3;
    private static  final int NUMBERS_OF_QUESTION_TYPES = 10;
    private static  final int NUMBERS_OF_CATEGORIES = 20;
    private static  final int NUMBERS_OF_SUB_CATEGORIES = 5;
    public static HashMap<LocalDate, Integer>[][][][][][] records =
            (HashMap<LocalDate, Integer>[][][][][][])
                    new HashMap[NUMBERS_OF_ANSWER_TYPES]
                    [NUMBERS_OF_SERVICES]
                    [NUMBERS_OF_VARIATIONS + 1]
                    [NUMBERS_OF_QUESTION_TYPES]
                    [NUMBERS_OF_CATEGORIES + 1]
                    [NUMBERS_OF_SUB_CATEGORIES + 1];

    static {
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[0].length; j++) {
                for (int k = 0; k < records[0][0].length; k++) {
                    for (int l = 0; l < records[0][0][0].length; l++) {
                        for (int m = 0; m < records[0][0][0][0].length; m++) {
                            for (int n = 0; n < records[0][0][0][0][0].length; n++) {
                                records[i][j][k][l][m][n] = new HashMap<>();
                            }
                        }
                    }
                }
            }
        }
    }
}
