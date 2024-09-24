package com.Offre_Emploi.Back.Repository;

import com.Offre_Emploi.Back.Entity.TestNiveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TestNiveauRepository extends JpaRepository<TestNiveau,Long> {
    @Query("SELECT tn FROM TestNiveau tn JOIN tn.user u WHERE u.id = :userId")
    Optional<List<TestNiveau>> findTestNiveauxByUserId(@Param("userId") Long userId);

    TestNiveau getTestNiveauByScoreTests_Id(Long id);
}
