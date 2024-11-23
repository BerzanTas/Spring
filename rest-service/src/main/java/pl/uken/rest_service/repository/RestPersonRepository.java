package pl.uken.rest_service.repository;
import pl.uken.rest_service.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestPersonRepository extends JpaRepository<Person, Long>{
    Person findPersonByApiKey(String apiKey);
}
