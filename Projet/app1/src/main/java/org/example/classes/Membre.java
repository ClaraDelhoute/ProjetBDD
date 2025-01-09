package org.example.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "membres") // Indique le nom de la collection MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre {

    @Id
    private String idMembre; // MongoDB utilise généralement String ou ObjectId pour l'ID

    private String nom;
    private String prenom;
    private String adresse;
    private String ville;

    private Integer codePostal;

    private String email;

    private TypeMembre type; // L'énumération peut être gérée sans l'annotation @Enumerated

    private String login;
    private String password;
    private String confirmPassword;

    @DBRef
    private Groupe groupe; // Utilisation de @DBRef pour une relation vers Groupe

    @DBRef
    private List<Commande> commandesVentes; // Liste des commandes en tant que vendeur

    @DBRef
    private List<Commande> commandesAchats; // Liste des commandes en tant que client
}
