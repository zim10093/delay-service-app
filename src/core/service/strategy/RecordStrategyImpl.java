package core.service.strategy;

import core.service.strategy.handler.RecordHandler;
import java.util.Map;

public class RecordStrategyImpl implements RecordStrategy {
    private final Map<Character, RecordHandler> recordHandlerMap;

    public RecordStrategyImpl(Map<Character, RecordHandler> recordHandlerMap) {
        this.recordHandlerMap = recordHandlerMap;
    }

    @Override
    public void process(String line) {
        recordHandlerMap.get(line.charAt(0)).process(line);
    }
}
