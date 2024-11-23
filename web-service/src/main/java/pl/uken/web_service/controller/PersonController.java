package pl.uken.web_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uken.web_service.model.Person;
import pl.uken.web_service.repository.PersonRepository;


@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("message", "wiadomość z kontrolera");
        return "index";
    }

    @GetMapping("/add")
    public String addPersonView(Model model){
        Person p = new Person();
        p.setFirstName(null);
        model.addAttribute("person", p);

        return "add_person";
    }

    @PostMapping("/add")
    public String addPersonView(Person person){
        repository.save(person);
        return "redirect:add";
    }
}
