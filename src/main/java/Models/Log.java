package Models;


import java.util.Date;
import java.util.Objects;

public class Log {
    private String message;
    private int level;
    private Labels label;
    private Date date;

    public Labels getLabel() {
        return label;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private void setLabel(String message) {
        if (message.startsWith("ERROR")) {
            label = Labels.ERROR;
        }
        if (message.startsWith("WARNING")) {
            label = Labels.WARNING;
        }
        if (message.startsWith("INFO")) {
            label = Labels.INFO;
        }
        if (message.startsWith("TRACEBACK")) {
            label = Labels.TRACEBACK;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Log(String message) {
        this.message = message;
        setLabel(message);
    }

    public Log(String message, Date date) {
        this.message = message;
        setLabel(message);
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return getLevel() == log.getLevel() && Objects.equals(getMessage(), log.getMessage()) && getLabel() == log.getLabel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getLevel(), getLabel());
    }
}
