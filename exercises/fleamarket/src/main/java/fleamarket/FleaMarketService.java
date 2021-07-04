package fleamarket;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class FleaMarketService {

    private ModelMapper modelMapper;

    private AtomicLong id = new AtomicLong();

    private List<Advertisement> advertisements = new ArrayList<>();

    public FleaMarketService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void deleteAll() {
        advertisements.clear();

        id = new AtomicLong();
    }

    public List<AdvertisementDTO> getAdvertisements(Optional<String> category, Optional<String> word) {
        return advertisements.stream()
                .filter(a -> category.isEmpty() || a.getLumberCategory().equals(LumberCategory.valueOf(category.get().toUpperCase())))
                .filter(a -> word.isEmpty() || a.getText().toLowerCase().contains(word.get().toLowerCase()))
                .sorted(Comparator.comparing(Advertisement::getTimeStamp).reversed())
                .map(advertisement -> modelMapper.map(advertisement, AdvertisementDTO.class))
                .collect(Collectors.toList());
    }

    public AdvertisementDTO createAdvertisement(CreateAdvertisementCommand command) {
        Advertisement advertisement = new Advertisement(id.incrementAndGet(), command.getLumberCategory(), command.getText(), LocalDateTime.now());
        advertisements.add(advertisement);
        return modelMapper.map(advertisement, AdvertisementDTO.class);
    }

    public AdvertisementDTO updateAdvertisement(long id, UpdateAdvertisementCommand command) {
        Advertisement advertisement = findAdvertisementById(id);
        advertisement.setText(command.getText());
        advertisement.setTimeStamp(LocalDateTime.now());
        return modelMapper.map(advertisement, AdvertisementDTO.class);
    }

    private Advertisement findAdvertisementById(long id){
        return advertisements.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find advertisement"));
    }

    public AdvertisementDTO getAdvertisementById(long id) {
        return modelMapper.map(findAdvertisementById(id), AdvertisementDTO.class);
    }

    public void deleteById(long id) {
        advertisements.remove(findAdvertisementById(id));
    }

    public void deleteOldestByCategory(Optional<String> category) {
        if (category.isPresent()){
            advertisements.remove(advertisements.stream()
                    .filter(a -> a.getLumberCategory().equals(LumberCategory.valueOf(category.get().toUpperCase())))
                    .min(Comparator.comparing(Advertisement::getTimeStamp))
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find advertisement")));
        }
        else{
            for (LumberCategory cat:LumberCategory.values()) {
                advertisements.remove(advertisements.stream()
                        .filter(a -> a.getLumberCategory().equals(cat))
                        .min(Comparator.comparing(Advertisement::getTimeStamp))
                        .orElse(null));
            }
        }
    }
}
