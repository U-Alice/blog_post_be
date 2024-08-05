package qt.rw.challenge.blog_post.utils;

import org.springframework.http.ResponseEntity;
import qt.rw.challenge.blog_post.payload.ApiResponse;

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
