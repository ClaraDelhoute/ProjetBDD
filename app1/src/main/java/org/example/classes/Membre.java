package org.example.classes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    @Column(length =  5)
    private Integer codePostal;
    @Column(nullable = false,unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private TypeMembre typeMembre;

    @ManyToOne
    private Groupe groupe;

    //Liste des commandes en tant que vendeur
    @OneToMany(mappedBy = "commandesVentes")
    private List<Commande> commandesVentes;

    //Liste des commandes en tant que client
    @OneToMany(mappedBy = "commandesAchats")
    private List<Commande> commandesAchats;
}
