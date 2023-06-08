package org.java.pizzeria.controller.api;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PizzaApiController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		
		return new ResponseEntity<>("ciao", HttpStatus.OK);
	}
	
	@GetMapping("/pizzas")
	public ResponseEntity<List<Pizza>> getAllPizza(@RequestParam(required = false) String name){
		
		if(name != null && !name.isEmpty()) {
			
			List<Pizza> pizzaList = pizzaService.findByNameContaining(name);
			return new ResponseEntity<>(pizzaList, HttpStatus.OK);
			
		}
		
		List<Pizza> pizzaList = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzaList, HttpStatus.OK);
		
	}
	
	@GetMapping("pizzas/{id}")
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") int id){
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Pizza pizza = optPizza.get();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/pizzas/store")	
	public ResponseEntity<Pizza> storePizza(@RequestBody Pizza pizza){
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
		
	}
	
	@PutMapping("/pizzas/update")
	public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza) {
		
		Optional<Pizza> referencedPizza = pizzaService.findById(pizza.getId());
		
		if(referencedPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);	
	}
	
	@DeleteMapping("/pizzas/delete/{id}")
	public ResponseEntity<Pizza> deleteBook(@PathVariable int id) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		
		Pizza pizza = optPizza.get();
		pizzaService.deleteById(pizza.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
