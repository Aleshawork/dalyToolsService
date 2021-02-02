package com.dalyTools.dalyTools.Controller;

import com.dalyTools.dalyTools.DAO.Entity.Person;
import com.dalyTools.dalyTools.DAO.Service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class PersonController {
    private PersonServiceImpl personService;

    @Autowired
    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/api/allperson",method = RequestMethod.GET)
    public String findAll(Model model){
        List<Person> listOfPerson= personService.findAll();
        model.addAttribute("listOfPerson",listOfPerson);
        return "login";
    }

    @RequestMapping(method=RequestMethod.GET,value ="/api/personById")
    @ResponseBody
    public Person findByID(int id){
        return  personService.findById(id);
    }
}
