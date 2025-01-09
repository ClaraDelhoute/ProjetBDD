package org.example.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    private Long idCommande;

    @DBRef
    private Membre commandesVentes;

    @DBRef
    private Membre commandesAchats;

    private LocalDateTime dateCommande;

    private List<Materiel> liste;

    private Double prixTotal;
}