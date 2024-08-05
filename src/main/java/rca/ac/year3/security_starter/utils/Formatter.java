package rca.ac.year3.security_starter.utils;

import org.springframework.http.ResponseEntity;
import rca.ac.year3.security_starter.payload.ApiResponse;

public class Formatter {

    public static ResponseEntity<ApiResponse> ok(Object body) {
        return ResponseEntity.ok(ApiResponse.success(body));
    }

    public static ResponseEntity<ApiResponse> ok(String message) {
        return ResponseEntity.ok(ApiResponse.success(message));
    }

    public static ResponseEntity<ApiResponse> done(){
        return ResponseEntity.ok(ApiResponse.success("Done"));
    }
}
