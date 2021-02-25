package com.darshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darshan.bean.Appointment;

/**
 * This <code> AppointmentRepository <code> interface holds data operation methods for <b>Appointment</b>.
 * 
 * @author darshan.dalwadi
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
