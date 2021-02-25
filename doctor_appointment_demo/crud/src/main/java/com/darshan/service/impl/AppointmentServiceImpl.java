package com.darshan.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darshan.bean.Appointment;
import com.darshan.bean.Doctor;
import com.darshan.repository.AppointmentRepository;
import com.darshan.repository.DoctorRepository;
import com.darshan.service.AppointmentService;

/**
 * <code> AppointmentServiceImpl </code> contains business method implementation
 * related to <b>Appointment</b>.
 * 
 * @author darshan.dalwadi
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Override
	public void deleteById(Integer id) {

		LOGGER.info(" ==> Method ==> deleteById ==> called");

		appointmentRepository.deleteById(id);
	}

	@Override
	public List<Appointment> findAll() {

		LOGGER.info(" ==> Method ==> findAll ==> called");

		return appointmentRepository.findAll();
	}

	@Override
	public Appointment findById(Integer id) {

		LOGGER.info(" ==> Method ==> findById ==> called");

		Optional<Appointment> appointment = appointmentRepository.findById(id);
		return appointment.isPresent() ? appointment.get() : null;
	}

	@Override
	public Appointment saveORupdate(Appointment appointment) {

		LOGGER.info(" ==> Method ==> saveORupdate ==> Enter");

		try {

			if (appointment != null) {

				Optional<Doctor> optDoctor = doctorRepository.findById(appointment.getDoctorId().getId());
				if (optDoctor.isPresent()) {
					Doctor doctor = optDoctor.get();

					if (appointment.getAppointmentDate().compareTo(doctor.getAvailableFrom()) >= 0
							&& appointment.getAppointmentDate().compareTo(doctor.getAvailableTo()) <= 0) {

						Optional<Appointment> optAppointment = appointmentRepository.findById(appointment.getId());
						if (optAppointment.isPresent()) {
							Appointment existingRecord = optAppointment.get();
							BeanUtils.copyProperties(appointment, existingRecord, "id");
							return appointmentRepository.save(existingRecord);
						}

					} else {
						LOGGER.info(" ==> Method ==> saveORupdate ==> Appointment Date is not in range.");
						return null;
					}
				} else {
					LOGGER.info(" ==> Method ==> saveORupdate ==> Doctor not Found.!");
					return null;
				}
			}

		} catch (Exception exception) {
			LOGGER.info(" ==> Method ==> saveORupdate ==> Excpetion");
			exception.printStackTrace();
		}

		LOGGER.info(" ==> Method ==> saveORupdate ==> Exit");

		return appointmentRepository.save(appointment);
	}

}
