package uos.capstone.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Result {
    private ErrorResponse error;
    private Object payload;
}
