package hu.telecom.xmltojava.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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

    @CreationTimestamp
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "lastModifiedDate", nullable = false)
    private Timestamp lastModifiedDate;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleEntity> roles;
}
