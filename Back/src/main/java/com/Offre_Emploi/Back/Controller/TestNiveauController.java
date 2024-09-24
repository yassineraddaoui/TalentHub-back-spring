package com.Offre_Emploi.Back.Controller;

import com.Offre_Emploi.Back.Entity.ScoreTest;
import com.Offre_Emploi.Back.Entity.TestNiveau;
import com.Offre_Emploi.Back.Service.TestNiveauService;
import com.Offre_Emploi.Back.payload.TestNiveauAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestNiveauController {

    @Autowired
    private TestNiveauService testNiveauService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TestNiveau>> getTestNiveauxUser(@PathVariable("id") Long id){
        var tests = testNiveauService.getTestNiveauxUser(id);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
    @PostMapping("/add")
    public TestNiveau addTest(@RequestBody TestNiveauAddRequest testNiveau){
        return testNiveauService.addTest(testNiveau);
    }

    @GetMapping("/all")
    public List<TestNiveau> getAllTests(){
        return testNiveauService.allTests();
    }

    @GetMapping("/{idtest}/{idquestion}")
    public TestNiveau addQuestionToTest(@PathVariable("idtest") Long idtest, @PathVariable("idquestion") Long idquestion) {
        return testNiveauService.addQuestionToTest(idtest, idquestion);
    }

    @GetMapping("/{idtest}/{idscore}/{idcandidat}")
    public ScoreTest addScoreToTest(@PathVariable("idtest") Long idtest, @PathVariable("idscore") Long idscore,
                                    @PathVariable("idcandidat") Long idcandidat) {
        return testNiveauService.addScoreToTest(idtest,idscore,idcandidat);
    }

    @GetMapping("/{id}")
    public TestNiveau findById(@PathVariable("id") Long id) {
        return testNiveauService.findTestById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTest(@PathVariable("id") Long id) {
        testNiveauService.deleteTest(id);
    }

}
