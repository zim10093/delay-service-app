package core.dto;

import java.time.LocalDate;


public class DelayRecord extends AbstractRecord {
    private int delay;
    private LocalDate date;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
