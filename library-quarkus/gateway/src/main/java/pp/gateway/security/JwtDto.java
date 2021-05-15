package pp.gateway.security;

import java.util.List;

public class JwtDto {
    private String access_token;
    private List<String> roles;

    public JwtDto() {
    }

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
