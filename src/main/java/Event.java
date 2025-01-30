
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")); 
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }  

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}