package org.example.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "materiel")
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

    @DBRef
    private Groupe groupe;

    @DBRef
    private List<Commande> commandes;
}

