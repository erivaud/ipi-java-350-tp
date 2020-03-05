package com.ipiecoles.java.java350.acceptance;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeAcceptanceTest {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    @Step("Soit un employé appelé <prenom> <nom> de matricule <matricule>")
    public void insertEmploye(String prenom, String nom, String matricule){
        Employe employe = new Employe(nom, prenom, matricule,
                LocalDate.now(), 15000.0,1,1.0);
        employeRepository.save(employe);
    }
    @Step("J'embauche une personne appelée <prenom> <nom> diplômée d'un <diplome> en temps que <poste> à <tauxActivite>"
    )
    public void embaucheEmploye(String prenom, String nom, String diplome, String poste, String tauxActivite) throws EmployeException {
        Double tempsPartiel = 1.0; // Plein temps
        if (tauxActivite.equals("mi-temps")) {
            tempsPartiel=0.5;
        }
        employeService.embaucheEmploye(
                nom, prenom,
                Poste.valueOf(poste.toUpperCase()),
                NiveauEtude.valueOf(diplome.toUpperCase()), tempsPartiel);
    }

    @Step("J'obtiens bien un nouvel employé appelé <prenom> <nom> portant le matricule <matricule> de performance <performance> et touchant un salaire de <salaire> €")
    public void resultEmploye(String prenom, String nom, String matricule, Integer performance, Double salaire){
        Employe employe = employeRepository.findByMatricule(matricule);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(performance);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaire);
    }
}
