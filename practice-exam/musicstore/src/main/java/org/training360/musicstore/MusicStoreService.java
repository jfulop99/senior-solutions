package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private AtomicLong id = new AtomicLong();

    private List<Instrument> instruments = new ArrayList<>();

    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Integer> price) {
        return instruments.stream()
                .filter(instrument -> (brand.isEmpty() || instrument.getBrand().equals(brand.get())) && (price.isEmpty()) || instrument.getPrice() == price.get())
                .map(instrument -> modelMapper.map(instrument, InstrumentDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteAllInstruments() {
        instruments.clear();
        id = new AtomicLong();
    }

    public InstrumentDTO getInstrumentById(long id) {
        return instruments.stream()
                .filter(instrument -> instrument.getId() == id)
                .map(instrument -> modelMapper.map(instrument, InstrumentDTO.class))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find instrument"));
    }


    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {

        Instrument instrument = new Instrument(id.incrementAndGet(), command.getBrand(), command.getType(), command.getPrice(), LocalDate.now());

        instruments.add(instrument);

        return modelMapper.map(instrument, InstrumentDTO.class);

    }

    public InstrumentDTO updateInstrumentPrice(long id, UpdatePriceCommand command) {

        Instrument instrument = instruments.stream().filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find instrument"));

        if (instrument.getPrice() != command.getPrice()){
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }

        return modelMapper.map(instrument, InstrumentDTO.class);
    }
}
