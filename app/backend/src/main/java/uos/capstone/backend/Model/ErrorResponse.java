package uos.capstone.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ErrorResponse {
    private Integer code;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
