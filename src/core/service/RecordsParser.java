package core.service;

public interface RecordsParser<T> {
    T parse(String line);
}
