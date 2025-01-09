package org.example.services;

import org.example.classes.Groupe;
import org.example.classes.Membre;
import org.example.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeServiceImpl implements GroupeService {

    @Autowired
    GroupeRepository groupeRepository;

    @Override
    public void addGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void updateGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void deleteGroupe(Groupe groupe) {groupeRepository.delete(groupe);}

    @Override
    public Optional<Groupe> getGroupeById(String id) {
       return groupeRepository.findById(id);
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeByMembre(Membre membre) {
        return groupeRepository.findGroupeByMembres(membre);
    }

}
