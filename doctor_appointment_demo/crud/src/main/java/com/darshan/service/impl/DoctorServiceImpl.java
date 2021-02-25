package com.darshan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darshan.bean.Doctor;
import com.darshan.repository.DoctorRepository;
import com.darshan.service.DoctorService;

/**
 * <code> DoctorServiceImpl </code> contains business method implementation
 * related to <b>Doctor</b>.
 * 
 * @author darshan.dalwadi
 */
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public void deleteById(Integer id) {

		doctorRepository.deleteById(id);
	}

	@Override
	public List<Doctor> findAll() {

		return doctorRepository.findAll();
	}

	@Override
	public Doctor findById(Integer id) {

		Optional<Doctor> doctor = doctorRepository.findById(id);
		return doctor.isPresent() ? doctor.get() : null;
	}

	@Override
	public Doctor saveORupdate(Doctor doctor) {

		return doctorRepository.save(doctor);
	}

}
