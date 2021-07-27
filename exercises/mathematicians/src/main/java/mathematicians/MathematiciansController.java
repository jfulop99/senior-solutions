package mathematicians;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mathematicians")
@AllArgsConstructor
public class MathematiciansController {

    private final MathematiciansService mathematiciansService;


    @GetMapping
    public List<MathematicianDto> getMathematicians(){
        return mathematiciansService.getMathematicians();
    }

    @PostMapping
    public MathematicianDto addMathematicians(@Valid @RequestBody createMathematicianCommand command){
        return mathematiciansService.addMathematicians(command);
    }



}
