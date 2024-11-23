package pl.uken.rest_service.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Null;
import pl.uken.rest_service.model.Person;
import pl.uken.rest_service.repository.RestPersonRepository;

@RestController
@RequestMapping("api/v1/")
public class RestPersonController {
    @Autowired
    RestPersonRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPerson(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id){
        Person p = repository.findById(id).orElse(null);
        return ResponseEntity.ok().body(p);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPerson(@RequestBody Person p, 
                                            @RequestHeader("api-key") String apiKey){
        if (repository.findPersonByApiKey(apiKey) != null){
            p.setCreated_at(LocalDateTime.now());
            p.setUpdated_at(LocalDateTime.now());
            Person savePerson = repository.save(p);
            if(savePerson != null){
                return ResponseEntity.ok().body("Dodano osobę do bazy.");
            }else {
                return ResponseEntity.ok().body("Nie udało się dodać osobę do bazy!");
            }
        }else{
            return ResponseEntity.ok().body("Brak dostępu");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delPerson(@PathVariable("id") Long id){
        Person p = repository.findById(id).orElse(null);

        if(p == null){
            return ResponseEntity.ok().body("Brak osoby o danym ideksie");
        }else{
            repository.deleteById(id);
            return ResponseEntity.ok().body("Użytkownik "+ p.getId()+"."+ p.getFirstName() + " " +p.getLastName()+" został usunięty z bazy danych");
        }
    }
}
