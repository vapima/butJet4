package ru.vapima.butjet4.model.db;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "email")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String hashPassword;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private State state;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Plan> plans;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
