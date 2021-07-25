package therapy;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TherapyService {

    private TherapyRepository therapyRepository;

    private ModelMapper modelMapper;

    public TherapyDTO addParticipant(Long id, CreateParticipantCommand command) {

        Therapy therapy = findTherapyById(id);
        therapy.getParticipants().add(command.getName());
        Therapy otherTherapy = therapyRepository.save(therapy);
        return modelMapper.map(otherTherapy, TherapyDTO.class);
    }

    public List<TherapyDTO> getTherapies() {
        return therapyRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TherapyDTO.class))
                .collect(Collectors.toList());
    }

    public TherapyDTO createTherapy(CreateTherapyCommand command) {
        Therapy therapy = therapyRepository.save(new Therapy(command.getTherapist(), command.getLocation(), command.getTime()));
        return modelMapper.map(therapy, TherapyDTO.class);
    }

    public TherapyDTO getTherapyById(Long id) {
        return modelMapper.map(findTherapyById(id), TherapyDTO.class);
    }

    private Therapy findTherapyById(Long id){
        return therapyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Therapy not found"));
    }

    public void deleteParticipant(Long id, String name) {
        Therapy therapy = findTherapyById(id);
        if (!therapy.getParticipants().remove(name)){
            throw new IllegalArgumentException("Name is not in participants");
        }
        therapyRepository.save(therapy);
    }
}
