package uz.oak.project_warehouse.payload.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiExceptionErrorResponse {

    private String timestamp;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }

    private int statusCode;
    private String message;

    public ApiExceptionErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
