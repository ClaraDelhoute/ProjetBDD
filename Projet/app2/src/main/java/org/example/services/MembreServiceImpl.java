package org.example.services;

import org.example.classes.Membre;
import org.example.classes.TypeMembre;
import org.example.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembreServiceImpl implements MembreService {

    @Autowired
    private MembreRepository membreRepository;

    @Override
    public void saveMembre(Membre membre) {
        membreRepository.save(membre);
    }

    @Override
    public void updateMembre(Membre membre) {
        membreRepository.save(membre);
    }

    @Override
    public void deleteMembre(Membre membre) {
        membreRepository.delete(membre);
    }

    @Override
    public List<Membre> getAllMembre() {
        return membreRepository.findAll();
    }

    @Override
    public Membre getMembreByIdMembre(Long id) {
         return membreRepository.getMemberByIdMembre(id);
    }

    @Override
    public Membre getMembreByNom(String nom) {
        return  membreRepository.getMembreByNom(nom);
    }

    @Override
    public Membre getMembreByNomAndPassword(String nom,String password){
        return membreRepository.getMembreByNomAndPassword(nom,password);
    }

    @Override
    public List<Membre> getMembresClients() {
        List<Membre> membresClients = new ArrayList<>();
        membresClients = (List<Membre>) membreRepository.getMembreByTypeMembre(TypeMembre.CLIENT);
        if(!CollectionUtils.isEmpty(membresClients)){
            return membresClients;
        }
        return null;
    }

    @Override
    public List<Membre> getMembresVendeur() {
        List<Membre> membresClients = new ArrayList<>();
        membresClients = (List<Membre>) membreRepository.getMembreByTypeMembre(TypeMembre.ACTIF);
        if(!CollectionUtils.isEmpty(membresClients)){
            return membresClients;
        }
        return null;
    }


}
