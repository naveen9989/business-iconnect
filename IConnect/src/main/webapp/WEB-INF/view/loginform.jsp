<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: red;
}

.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<c:if test="${invalid !=null}">
		<div class="errorblock">${invalid}</div>
	</c:if>

	<form method="post" action="<c:url value='j_spring_security_check'/>">
		<table>
			<tbody>

				<tr>

					<td>Login:</td>
					<td><input type="text" name="j_username" id="j_username"
						size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" id="j_password"
						size="30" maxlength="32" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>