package com.Offre_Emploi.Back.payload;

import com.Offre_Emploi.Back.Entity.Offres;
import com.Offre_Emploi.Back.Entity.Question;
import com.Offre_Emploi.Back.Entity.ScoreTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestNiveauAddRequest {
    private Long id;
    private int score_min;
    private int nb_questions;
    private String duree;
    private String titre;
    private String user_id;
    private List<Question> questions;
    private List<ScoreTest> scoreTests ;
    private List<Offres> offres ;

}
