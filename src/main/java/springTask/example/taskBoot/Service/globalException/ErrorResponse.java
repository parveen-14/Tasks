package springTask.example.taskBoot.globalException;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ErrorResponse {

    private String message;
    private String details;
    private LocalDateTime localDateTime;

    public ErrorResponse(String message, String details, LocalDateTime localDateTime) {
        this.message = message;
        this.details = details;
        this.localDateTime = localDateTime;
    }
}
