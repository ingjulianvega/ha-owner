package ingjulianvega.ximic.haowner.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    private LocalDateTime timestamp;
    private String api;
    private String apiCode;
    private String error;
    private String message;
    private String solution;
}
