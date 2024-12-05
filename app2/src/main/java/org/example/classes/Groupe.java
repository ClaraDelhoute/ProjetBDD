package org.example.classes;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupe {
    @Id
    private String numero;
    private String nom;
    private String ville;
    private Integer codePostal;

    @OneToMany(mappedBy = "groupe")
    private List<Membre> membres;

    @OneToOne
    private Materiel materiel;

}
