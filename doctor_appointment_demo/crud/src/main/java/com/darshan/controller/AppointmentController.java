package com.darshan.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.darshan.bean.Appointment;
import com.darshan.bean.Doctor;
import com.darshan.service.AppointmentService;
import com.darshan.service.DoctorService;
import com.darshan.util.ApplicationConstants;

import lombok.extern.log4j.Log4j2;

/**
 * <code> AppointmentController </code> Controller class responsible to handle
 * user request-response for <b>Appointment</b>.
 * 
 * @author darshan.dalwadi
 */
@RestController
@Log4j2
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorService doctorService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

	/**
	 * This <code>getAll</code> method used to get list of appointments.
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView getAll() {

		LOGGER.info(" ==> Method ==> getAll ==> Enter");

		List<Appointment> appointmentList = appointmentService.findAll();

		LOGGER.info(" ==> Method ==> getAll ==> Exit");

		return new ModelAndView("list_appointment", "appointmentList", appointmentList);
	}

	/**
	 * This <code>getForm</code> method used to load appointment form.
	 * 
	 * @return
	 */
	@GetMapping({ "/add", "/update/{id}" })
	public ModelAndView getForm(@PathVariable(required = false) final Integer id) {

		LOGGER.info(" ==> Method ==> getForm ==> Enter");

		Appointment appointment = new Appointment();

		try {
			if (!Objects.isNull(id)) {
				// Load page to update existing record
				Appointment existedAppointment = appointmentService.findById(id);
				appointment = existedAppointment;
			}

			// Set list of doctors
			List<Doctor> doctorList = doctorService.findAll();
			appointment.setDoctorList(doctorList);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(" ==> Method ==> getForm ==> Exception");
		}

		LOGGER.info(" ==> Method ==> getForm ==> Exit");

		return new ModelAndView("take_appointment", "appointment", appointment);
	}

	/**
	 * This <code>add</code> method used to add an appointments.
	 * 
	 * @param appointment
	 * @param bindingResult
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/create")
	public ModelAndView add(@Valid @ModelAttribute("appointment") final Appointment appointment,
			final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

		LOGGER.info(" ==> Method ==> add ==> Enter");

		ModelAndView modelAndView = new ModelAndView("redirect:/add");

		try {
			
			if (bindingResult.hasErrors()) {

				return modelAndView;
			} else {

				if (appointmentService.saveORupdate(appointment) != null) {
					modelAndView.setViewName("redirect:/");
					redirectAttributes.addFlashAttribute(ApplicationConstants.SUCCESS_MSG,
							"Appointment saved successfully.!");
				} else {
					redirectAttributes.addFlashAttribute(ApplicationConstants.ERROR_MSG,"Error in scheduling appointment.");
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			LOGGER.info(ApplicationConstants.METHOD_EXCEPTION_LABEL);
		}

		LOGGER.info(" ==> Method ==> add ==> Exit");
		return modelAndView;
	}

	/**
	 * This <code>delete</code> method used to delete an appointment.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(required = false) final Integer id,
			final RedirectAttributes redirectAttributes) {

		LOGGER.info(" ==> Method ==> delete ==> Enter");

		try {

			if (Objects.isNull(id)) {
				redirectAttributes.addFlashAttribute(ApplicationConstants.ERROR_MSG,
						"No identification, can not remove.");
				return new ModelAndView("redirect:/");
			} else {
				appointmentService.deleteById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(" ==> Method ==> delete ==> Exception");
		}

		LOGGER.info(" ==> Method ==> delete ==> Exit");

		redirectAttributes.addFlashAttribute(ApplicationConstants.SUCCESS_MSG, "Appointment removed.!");
		return new ModelAndView("redirect:/");
	}
}
