package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void setup() {
        employeRepository.deleteAll();
    }

    @Test
    public void testFindLastMatricule(){
        // Given
        Employe employe = new Employe("Doe", "John", "C12345", LocalDate.now(), 1500.0, 1, 1.0);
        Employe employe2 = new Employe("Doe", "John", "T02345", LocalDate.now(), 1500.0, 1, 1.0);
        employeRepository.save(employe);
        employeRepository.save(employe2);
        // When
        String lastMatricule = employeRepository.findLastMatricule();
        // Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    @Test
    public void testFindLastMatriculeNull(){
        // Given : rien car on veut obtenir un Null

        // When
        String lastMatricule = employeRepository.findLastMatricule();
        // Then
        Assertions.assertThat(lastMatricule).isNull();
    }


}