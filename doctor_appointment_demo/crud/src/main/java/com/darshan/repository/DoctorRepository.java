package com.darshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darshan.bean.Doctor;

/**
 * This <code> AppointmentRepository <code> interface holds data operation methods for <b>Doctor</b>.
 * 
 * @author darshan.dalwadi
 *
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
