package com.voxloud.provisioning.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private int status;

    public ErrorDetails(String message, int status){
        this.timestamp = Date.from(Instant.now());
        this.message = message;
        this.status = status;
    }
}
