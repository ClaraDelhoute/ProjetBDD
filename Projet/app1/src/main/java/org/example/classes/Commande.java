package org.example.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "commandes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    private String idCommande;

    @DBRef
    private Membre idMembre;

    /*@DBRef
    private Membre commandesAchatsId;*/

    private LocalDateTime date;

    private List<Materiel> liste;

    private Double prixTotal;

    private Integer codePostal;
}