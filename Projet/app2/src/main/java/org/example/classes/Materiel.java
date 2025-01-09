package org.example.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String numeroSerie;
    private String marque;
    private String modele;
    private Type type;
    private Double prix;

    @OneToOne(mappedBy = "materiel")
    private Groupe groupe;

    @ManyToMany
    private List<Commande> commandes;
}

