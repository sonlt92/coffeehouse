package projecta07.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projecta07.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private boolean enabled;

    @JsonIgnore
    private String password;
    List<GrantedAuthority> authorities = null;

    public AccountDetailsImpl(Long id, String username, String password,
                              List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    // This func help you guys get account information to AccountDetailService
    public static AccountDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole()))
                .collect(Collectors.toList());
        return new AccountDetailsImpl(
                user.getIdUser(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccountDetailsImpl account = (AccountDetailsImpl) o;
        return Objects.equals(id, account.id);
    }
}
