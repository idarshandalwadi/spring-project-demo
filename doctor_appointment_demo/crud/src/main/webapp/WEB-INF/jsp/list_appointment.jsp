<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="header.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<title>Appointments</title>
</head>
<body>

<hr/>
<b class="ml-3">All appointments.</b>
<a href="/add">
	<button class="btn btn-primary float-right mr-5">
		Add appointment
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

<table class="table">
  <thead>
    <tr>
      <th scope="col">FullName</th>
      <th scope="col">Email</th>
      <th scope="col">Mobile</th>
      <th scope="col">BirthDate</th>
      <th scope="col">App.Date</th>
      <th scope="col">Doctor</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="appointment" items="${appointmentList}">
    <tr>
      <td>${appointment.patientName}</td>
      <td>${appointment.emailId}</td>
      <td>${appointment.mobile}</td>
      <td>${appointment.birthDate}</td>
      <td>${appointment.appointmentDate}</td>
      <td>${appointment.doctorId.fullName}</td>
      <td>
      	<a href="/update/${appointment.id}"><i class="fas fa-edit fa-lg text-green" title="Update/Edit"></i></a>
      	<a href="/delete/${appointment.id}"><i class="fas fa-trash-alt fa-lg text-danger ml-2" title="Delete"></i></a>
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
</div>

</body>
</html>