package sillynaut;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Silvio Wangler
 */
@Singleton
public class PersonRepositoryImpl implements PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PersonRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person as p", Person.class).getResultList();
    }

    @Override
    @Transactional
    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id).ifPresent(person -> entityManager.remove(person));
    }
}
