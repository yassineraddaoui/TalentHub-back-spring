package com.Offre_Emploi.Back.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recruteur extends User{

    private int num_tel;
    private String adresse;
    private String date_naissance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private File image;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ro_id", referencedColumnName = "id")
    private Set<Offres> offres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<TestNiveau> testNiveaus ;

    public String toString() {
        return "recruteur";
    }
}

