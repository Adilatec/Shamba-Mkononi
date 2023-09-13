package tz.go.ega.shambamkononibackend.userserviceImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tz.go.ega.shambamkononibackend.model.UserAccount;

import java.util.Collection;
import java.util.HashSet;


public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String phone;
    @JsonIgnore
    private final String password;

    private final boolean accountNonLocked;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String phone, String password, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserAccount user){
        Collection<GrantedAuthority> authorities = getAuthorities(user);

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPhone(),
                user.getPassword(),
                true,
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    private static Collection<GrantedAuthority> getAuthorities(UserAccount userAccount) {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        userAccount.getRoles().stream().forEachOrdered((role) -> {
//            role.getPermissions().forEach((permission) -> {
//                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
//            });
//        });
//
//        return grantedAuthorities;
        return new HashSet<>();
    }

}
