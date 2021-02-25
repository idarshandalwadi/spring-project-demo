<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="header.jsp"></jsp:include>
<title>Appointments</title>
</head>
<body>

<hr/>
   <c:choose>         
        <c:when test = "${appointment.id != 0 }">
           <b class="ml-3">Update appointment.</b>
        </c:when>         
        <c:otherwise>
        	<b class="ml-3">Take an appointment.</b>
        </c:otherwise>
   </c:choose>
<a href="/">
	<button class="btn btn-primary float-right mr-5">
		All appointments
	</button>
</a>
<hr/>

<div class="col-md-8">
<c:if test="${not empty success}">
	<p class="h5 text-success alert alert-success">
		<c:out value="${success}" />
	</p>
</c:if>

<c:if test="${not empty error}">
   	<p class="h5 text-danger alert alert-danger">
		<c:out value="${error}" />
	</p>
</c:if>

<form:form action="/create" method="post" modelAttribute="appointment">

  <c:set var="formErrors">
		<form:errors path="*"></form:errors>
  </c:set>
  
  <c:if test="${not empty formErrors}">
	<div class="alert alert-danger">
		<p class="h5">
			<b><form:errors path="patientName"></form:errors></b>
		</p>
		<p class="h5">
			<b><form:errors path="emailId"></form:errors></b>
		</p>
		<p class="h5">
			<b><form:errors path="mobile"></form:errors></b>
		</p>
		<p class="h5">
			<b><form:errors path="birthDate"></form:errors></b>
		</p>
		<p class="h5">
			<b><form:errors path="appointmentDate"></form:errors></b>
		</p>
	</div>
  </c:if>

  <form:input type="hidden" path="id" class="form-control" value="${appointment.id}" id="id" />
  
  <div class="form-group">
    <label for="patientName">Patient Full Name:</label>
    <form:input type="text" path="patientName" class="form-control" value="${appointment.patientName}" id="patientName"  placeholder="Enter full Name" required="required"/>
  </div>

  <div class="form-group">
    <label for="email">Email address:</label>
    <form:input type="email" path="emailId" value="${appointment.emailId}" class="form-control" id="email" placeholder="Enter email address" required="required"/>
  </div>

  <div class="form-group">
    <label for="mobile">Mobile:</label>
    <form:input type="tel" path="mobile" value="${appointment.mobile}" pattern="(\\+){0,1}([0-9 \\-()]){10,15}" class="form-control" id="mobile" placeholder="Enter Mobile Number" required="required"/>
  	<small>sample: 123456789</small>
  </div>
  
  <div class="form-group">
    <label for="birthDate">Birth Date:</label>
    <form:input type="date" path="birthDate" value="${appointment.birthDate}" pattern="dd/MM/yyyy" class="form-control" required="required"/>
  </div>

  <div class="form-group">
    <label for="appointmentDate">Appointment Date:</label>
    <form:input type="date" path="appointmentDate" value="${appointment.appointmentDate}" class="form-control" pattern="dd/MM/yyyy" required="required"/>
  </div>

  <input type="hidden" value="${appointment.doctorId.id}" id="selectedDoctor"/>
	
  <div class="form-group">
    <label for="doctors">Select Doctor:</label>
    <form:select class="form-control" path="doctorId" id="doctors">
      <option value="Select" label=" - Select Doctor - " selected disabled="disabled"></option>
      <c:forEach var="doctor" items="${appointment.doctorList}">
      	<form:option value="${doctor.id}" label="${doctor.fullName}"/>
      </c:forEach>
    </form:select>
  </div>
  
   <div class="form-grup">
		<button type="submit" class="btn btn-default btn-primary" id="submit">Submit </button>
   </div>
</form:form>
</div>
  <script>
  	jQuery(document).ready(function(){
  	  if(jQuery('#selectedDoctor').val()!=""){
		  jQuery("#doctors").val(jQuery('#selectedDoctor').val());
  	  }
	});
	// Set minimum date for appointment date
  	appointmentDate.min = new Date().toISOString().split("T")[0];

 	// Set maximum date for birthdate
  	birthDate.max = new Date().toISOString().split("T")[0];
  </script>
</body>
</html>