package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.beans.Pet;

/**
 * <code> PetRepository </code> responsible to perform DB related operations for Pet.
 * 
 * @author darshan.dalwadi
 *
 */
@Repository
public interface PetRepository extends MongoRepository<Pet, String> {

}