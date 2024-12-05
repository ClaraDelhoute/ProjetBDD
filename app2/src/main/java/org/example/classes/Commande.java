package org.example.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCommande;

    @ManyToOne
    private Membre commandesVentes;

    @ManyToOne
    private Membre commandesAchats;

    private LocalDateTime dateCommande;

    @OneToMany(mappedBy = "groupe")
    private List<Materiel> liste;

    private Double prixTotal;
}