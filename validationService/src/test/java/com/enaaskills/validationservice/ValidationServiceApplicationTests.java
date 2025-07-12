package com.enaaskills.validationservice;

import com.enaaskills.validationservice.model.ValidationDTO;
import com.enaaskills.validationservice.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import com.enaaskills.validationservice.repository.ValidationRepository;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ValidationServiceApplicationTests {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ValidationRepository validationRepository;

    @BeforeEach
    void cleanDb() {
        validationRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testNoDuplicateValidation() {
        ValidationDTO dto = new ValidationDTO();
        dto.setStatut("VALIDE");
        dto.setApprenantId(100L);
        dto.setCompetenceId(200L);
        dto.setBriefId(300L);
        dto.setRenduId(400L);

        // Première création doit passer
        assertDoesNotThrow(() -> validationService.createValidation(dto));
        // Deuxième création avec mêmes valeurs doit lever une exception
        Exception exception = assertThrows(RuntimeException.class, () -> validationService.createValidation(dto));
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testCreateValidationWithoutExternalChecks() {
        ValidationDTO dto = new ValidationDTO();
        dto.setStatut("VALIDE");
        dto.setApprenantId(999L); // ID qui n'existe pas dans d'autres services
        dto.setCompetenceId(888L);
        dto.setBriefId(777L);
        dto.setRenduId(666L);

        // Première création doit passer
        assertDoesNotThrow(() -> validationService.createValidation(dto));
        // Deuxième création avec mêmes valeurs doit lever une exception (duplication)
        Exception exception = assertThrows(RuntimeException.class, () -> validationService.createValidation(dto));
        assertTrue(exception.getMessage().contains("already exists"));
    }
}
