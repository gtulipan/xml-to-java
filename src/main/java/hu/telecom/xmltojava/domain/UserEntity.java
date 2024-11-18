package hu.telecom.xmltojava.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 1, max = 255)
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false)
    private String address;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleEntity> roles;
}
