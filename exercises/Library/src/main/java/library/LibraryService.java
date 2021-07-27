package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibraryService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;


    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    public AuthorDto addAuthor(createAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Transactional
    public AuthorDto addBook(Long id, createBookCommand command) {

        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find author"));

        Book book = new Book(command.getIsbnNumber(), command.getTitle());

        author.addBook(book);

        return modelMapper.map(author, AuthorDto.class);
    }

    public void deleteAuthor(Long id) {

        authorRepository.deleteById(id);
    }
}
