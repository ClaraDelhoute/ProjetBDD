package org.example.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.MembreService;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    private Long id;
    private Date date;
    private Double prix;

    @ManyToOne
    @JoinColumn(name="membre_vendeur_id")
    private Membre membreVendeur;

    @ManyToOne
    @JoinColumn(name="membre_client_id")
    private Membre membreClient;



    @ManyToMany(mappedBy = "commandes")
    private List<Materiel> materials;


}
