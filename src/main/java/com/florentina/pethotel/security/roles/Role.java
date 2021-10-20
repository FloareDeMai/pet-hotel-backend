package com.florentina.pethotel.security.roles;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
