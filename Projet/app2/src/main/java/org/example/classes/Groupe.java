package org.example.classes;


import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "groupe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupe {
    @Id
    private String numero;
    private String nom;
    private String ville;
    private Integer codePostal;

    @DBRef
    private List<Membre> membres;

    @DBRef
    private Materiel materiel;

}
