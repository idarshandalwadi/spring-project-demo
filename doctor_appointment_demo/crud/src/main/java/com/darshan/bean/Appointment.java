package com.darshan.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Please enter name of the patient.")
	private String patientName;

	@NotEmpty(message = "Please enter email addresss.")
	@Email(message = "Please enter valid Email address.")
	private String emailId;

	@NotBlank(message = "Please enter mobile.")
	private String mobile;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Please select birth date.")
	private LocalDate birthDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Please select aapointment date.")
	private LocalDate appointmentDate;

	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctorId;

	@Transient
	@Nullable
	private List<Doctor> doctorList;

}
