package pp.zuul.dto;

import java.util.List;
import java.util.Set;

public class JwtDto {
    private String access_token;
    private List<String> roles;

    public JwtDto(String access_token, List<String> roles) {
        this.access_token = access_token;
        this.roles = roles;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
