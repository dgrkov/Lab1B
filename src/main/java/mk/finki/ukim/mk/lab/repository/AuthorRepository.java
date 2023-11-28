package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init(){
        authors.add(new Author(1L, "John", "Doe", "John Doe is a prolific author."));
        authors.add(new Author(2L, "Jane", "Smith", "Jane Smith is known for her best-selling novels."));
        authors.add(new Author(3L, "Alice", "Johnson", "Alice Johnson has won multiple literary awards."));
        authors.add(new Author(4L, "Robert", "Brown", "Robert Brown specializes in science fiction."));
        authors.add(new Author(5L, "Eleanor", "Adams", "Eleanor Adams is an expert in historical fiction."));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(Long id) {
        return authors.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
