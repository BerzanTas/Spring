package pl.uken.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.uken.web_service.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
