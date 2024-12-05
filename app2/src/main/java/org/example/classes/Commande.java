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

    // Membre qui est le client
    @ManyToOne
    @JoinColumn(name = "membre_client_id", nullable = false)
    private Membre membreClient;

    // Membre actif (par exemple, vendeur ou gestionnaire associé à la commande)
    @ManyToOne
    @JoinColumn(name = "membre_actif_id", nullable = false)
    private Membre membreVendeur;

    // Date de la commande
    private LocalDateTime dateCommande;

    // Liste des matériels commandés
    @ManyToMany
    @JoinTable(
            name = "commande_materiels",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "materiel_id")
    )
    private List<Materiel> materiels;

    // Prix total de la commande
    private Double prixTotal;
}