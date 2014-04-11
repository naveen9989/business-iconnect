<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BusinessInformation</title>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

	<form:form method="post" modelAttribute="registration">
		<form:errors path="*" cssClass="errorblock" element="div" />

		<font color="red"><c:out value="${url}"></c:out></font>
		<table>
			<tr>
				<td>CompanyName</td>
				<td><form:input path="companyName" /></td>
				<td><form:errors path="companyName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Established Year</td>
				<td><form:input path="establishedYear" /></td>
				<td><form:errors path="establishedYear" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Industry</td>
				<td><form:input path="industry" /></td>
				<td><form:errors path="industry" cssClass="error" /></td>
			</tr>
			<tr>
				<td>RevenueSize</td>
				<td><form:input path="revenueSize" /></td>
				<td><form:errors path="revenueSize" cssClass="error" /></td>
			</tr>
			<tr>
				<td>EmployeeSize</td>
				<td><form:input path="employeeSize" /></td>
				<td><form:errors path="employeeSize" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>

			</tr>
			<tr>
				<td>ConfirmEmail</td>
				<td><form:input path="confirmEmail" /></td>
				<td><form:errors path="confirmEmail" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Fax</td>
				<td><form:input path="fax" /></td>
				<td><form:errors path="fax" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><form:input path="city" /></td>
				<td><form:errors path="city" cssClass="error" /></td>
			</tr>
			<tr>
				<td>State</td>
				<td><form:input path="state" /></td>
				<td><form:errors path="state" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Country</td>
				<td><form:input path="country" /></td>
				<td><form:errors path="country" cssClass="error" /></td>
			</tr>
			<tr>
				<td><input type="hidden" value="1" name="_page"></td>
				<td><input type="submit" value="Back" name="_target0"></td>
				<td><input type="submit" value="Finish" name="_finish"></td>

			</tr>
		</table>
	</form:form>
</body>
</html>