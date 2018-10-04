package sillynaut;

import java.util.List;
import java.util.Optional;

/**
 * @author Silvio Wangler
 */
public interface PersonRepository {

    Optional<Person> findById(Long id);

    List<Person> findAll();

    Person save(Person person);

    void delete(Long id);
}
