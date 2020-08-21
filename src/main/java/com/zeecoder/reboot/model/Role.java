package com.zeecoder.reboot.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Account> account = new HashSet<>();

    @Override
    public String getAuthority() {
        return getRole();
    }
}