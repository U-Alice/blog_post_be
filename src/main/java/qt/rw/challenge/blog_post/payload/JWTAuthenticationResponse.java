package qt.rw.challenge.blog_post.payload;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Object data;

    public JWTAuthenticationResponse(String accessToken){
        this.accessToken = accessToken;
    }
}
