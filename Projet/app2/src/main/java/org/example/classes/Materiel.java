package org.example.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="materiels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {
    @Id
    private String idMateriel;
    private String numeroSerie;
    private String modele;
    private TypeMateriel type;
    private String marque;
    private Double prix;

    @DBRef
    private Groupe groupe;

    @DBRef
    private List<Commande> commandes;
}

