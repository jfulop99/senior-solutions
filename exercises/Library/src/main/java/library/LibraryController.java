package library;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class LibraryController {

    private LibraryService libraryService;

    @GetMapping
    public List<AuthorDto> getAuthors(){
        return libraryService.getAuthors();
    }

    @PostMapping
    public AuthorDto addAuthor(@RequestBody createAuthorCommand command){
        return libraryService.addAuthor(command);
    }

    @PostMapping("/{id}/books")
    public AuthorDto addBook(@PathVariable Long id, @RequestBody createBookCommand command){
        return libraryService.addBook(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        libraryService.deleteAuthor(id);
    }
}
