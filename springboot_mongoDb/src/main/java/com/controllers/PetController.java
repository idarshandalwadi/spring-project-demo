package com.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Pet;
import com.repositories.PetRepository;

/**
 * <code> PetController </code> responsible to handle request-response for the
 * Pet.
 * 
 * @author darshan.dalwadi
 */
@RestController
public class PetController {

	@Autowired
	private PetRepository petRepository;

	@GetMapping(value = "/pets")
	public List<Pet> getAllPet() {

		return petRepository.findAll();
	}

	@GetMapping(value = "pets/{id}")
	public Pet findByid(@PathVariable("id") String id) {

		Optional<Pet> pet = petRepository.findById(id);

		return pet.isPresent() ? pet.get() : null;
	}

	@PostMapping(value = "/pets")
	public ResponseEntity<String> addPet(@Valid @RequestBody Pet pet) {

		if (petRepository.save(pet) != null) {

			return new ResponseEntity<>("Record inserted.!", HttpStatus.CREATED);
		}

		return new ResponseEntity<>("Error in insertion", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "pets/{id}")
	public void updatePet(@PathVariable("id") String id, @Valid @RequestBody Pet pet) {

		Optional<Pet> optPet = petRepository.findById(id);

		if (optPet.isPresent()) {
			
			Pet existingPet = optPet.get();
			BeanUtils.copyProperties(pet, existingPet, "id");
			petRepository.save(existingPet);	
		}

	}

	@DeleteMapping(value = "pets/{id}")
	public void deletePet(@PathVariable String id) {

		petRepository.deleteById(id);
	}
}