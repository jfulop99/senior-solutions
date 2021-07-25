package therapy;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/therapy")
@AllArgsConstructor
public class TherapyController {

    TherapyService therapyService;

    @GetMapping
    public List<TherapyDTO> getTherapies(){

        List<TherapyDTO> therapyList = therapyService.getTherapies();

        return therapyList;

    }

    @GetMapping("/{id}")
    public TherapyDTO getTherapyById(@PathVariable Long id){

        return therapyService.getTherapyById(id);

    }

    @PostMapping
    public TherapyDTO createTherapy(@Valid @RequestBody CreateTherapyCommand command){
        return therapyService.createTherapy(command);
    }

    @PutMapping("/{id}")
    public TherapyDTO addParticipant(@Valid @RequestBody CreateParticipantCommand command, @PathVariable Long id){

        return therapyService.addParticipant(id, command);

    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id, @RequestParam String name){
        therapyService.deleteParticipant(id, name);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException e) {

        Problem problem = Problem.builder()
                .withType(URI.create("therapy-or-participant/not-found"))
                .withTitle("Not found")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(e.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
