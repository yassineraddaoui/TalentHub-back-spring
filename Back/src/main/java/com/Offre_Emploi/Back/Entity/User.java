package com.Offre_Emploi.Back.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String mdp;
    private String role;
    private LocalDate dateinscription = LocalDate.now();

    @OneToMany
    private List<TestNiveau> testNiveaux;
    @Column(unique = true)
    private String mail;
}
