package sillynaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Silvio Wangler
 */
@Controller("/")
public class PersonResource {

    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Get("/")
    public List<Person> readAll() {
        return personRepository.findAll();
    }

    @Get("/{id}")
    public Person read(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Post("/")
    public void create() {
        Person p = new Person();
        p.setFirstName("Silvio");
        p.setSurname("Naut");
        personRepository.save(p);
    }

    @Delete("/{id}")
    public void delete(@NotNull Long id) {

        personRepository.delete(id);

    }
}
