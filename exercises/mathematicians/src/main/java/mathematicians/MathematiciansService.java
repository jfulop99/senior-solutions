package mathematicians;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MathematiciansService {

    private final MathematiciansRepository mathematiciansRepository;

    private final ModelMapper modelMapper;

//    @Transactional
    public List<MathematicianDto> getMathematicians() {
        return mathematiciansRepository.findAll()
                .stream()
                .map(math -> modelMapper.map(math, MathematicianDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public MathematicianDto addMathematicians(createMathematicianCommand command) {
        Mathematician mathematician = new Mathematician(command.getName(),
                command.getBirthDay(),
                command.getFavoriteTopics(),
                command.getFavoritePrime());
        mathematiciansRepository.save(mathematician);
        return modelMapper.map(mathematician, MathematicianDto.class);
    }
}
