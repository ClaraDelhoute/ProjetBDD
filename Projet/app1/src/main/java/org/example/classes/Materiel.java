package org.example.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="materiel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {
    @Id
    private Integer idMateriel;
    private String numeroSerie;
    private String modele;
    private Type type;
    private String marque;
    private Double prix;

    @DBRef
    private Groupe groupe;

    @DBRef
    private List<Commande> commandes;
}

