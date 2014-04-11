<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Information</title>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
.errorblock {
	color: #0000;
	background-color: #ffEEEE;
	border: 2px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<form:form method="post" modelAttribute="registration">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>First name</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>LastName</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><form:radiobutton path="gender" value="male" />Male <form:radiobutton
						path="gender" value="female" />Female</td>
				<td><form:errors path="gender" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><form:input path="age" /></td>
				<td><form:errors path="age" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><form:input path="dob" /></td>
				<td><form:errors path="dob" cssClass="error"
						placeholder="MM/DD/YYYY" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td>ConfirmPassword</td>
				<td><form:password path="confirmPassword" /></td>
				<td><form:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<td><input type="hidden" value="0" name="_page"></td>
				<td><input type="submit" name="_target1" value="procced"></td>
			</tr>

		</table>
	</form:form>
</body>
</html>