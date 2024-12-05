package org.example.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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

