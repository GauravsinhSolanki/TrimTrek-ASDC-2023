package com.ProjectTrial1.Projectdemo1.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthentication {
    private String email;
    private String password;
    private String token;
    private static final String SECRET_KEY = "90380238417CEDB6689086ADCE31DB8B3AEA940D7DCA1BE763D7CD3AA8C9DC4C";


    private static final long EXPIRATION_TIME = 3600000;
    public UserAuthentication(String email) {
        this.email = email;
        this.token  = this.generateToken();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    String generateToken(){
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(this.email)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    public UserAuthentication(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public static boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
