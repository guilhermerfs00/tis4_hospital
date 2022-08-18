//package br.com.pucminas.hospital.data.entity;
//
//import java.io.Serializable;
//import java.security.Permission;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Table(name = "users")
//@Entity
//@Getter
//@Setter
//public class User implements UserDetails, Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "user_name", unique = true)
//    private String userName;
//
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "account_non_expired")
//    private Boolean accountNonExpired;
//
//    @Column(name = "account_non_locked")
//    private Boolean accountNonLocked;
//
//    @Column(name = "credentials_non_expired")
//    private Boolean credentialsNonExpired;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")}, inverseJoinColumns = {@JoinColumn(name = "id_permission")})
//    private List<Permission> permissions;
//
//    public List<String> getRoles() {
//        List<String> roles = new ArrayList<>();
//        for (Permission permission : this.permissions) {
//            roles.add(permission.getName());
//        }
//        return roles;
//    }
//
//    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return (Collection<? extends GrantedAuthority>) this.permissions;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
//
