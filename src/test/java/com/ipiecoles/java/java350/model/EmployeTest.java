package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNombreAnneeAncienneteNow() {
        // Given : un employé
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        // When : calcul de l'ancienneté
        Integer anciennete = employe.getNombreAnneeAnciennete();

        // Then : je récupère l'ancienneté
        Assertions.assertThat(anciennete).isEqualTo(0);
    }

    @Test
    public void testGetNombreAnneeAncienneteNMoins1() {
        // Given : un employé
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(1));

        // When : calcul de l'ancienneté
        Integer anciennete = employe.getNombreAnneeAnciennete();

        // Then : je récupère l'ancienneté
        Assertions.assertThat(anciennete).isEqualTo(1);
    }


    @Test
    public void testGetNombreAnneeAncienneteNull() {
        // Given : un employé
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        // When : calcul de l'ancienneté
        Integer anciennete = employe.getNombreAnneeAnciennete();

        // Then : je récupère l'ancienneté
        Assertions.assertThat(anciennete).isEqualTo(0);
    }

}
