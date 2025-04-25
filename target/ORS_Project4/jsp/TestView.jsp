<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">

		<h1>Add Test</h1>

		<table align="center">
			<tr>
				<th align="left">TestName</th>
				<td><input type="text" name="testName" placeholder="Enter Test"
					size="25" value=""></td>
			</tr>

			<tr>
				<th style="padding: 3px"></th>
			</tr>

			<tr>
				<th align="left">Description<span style="color: red">*</span> :
				</th>
				<td><input type="text" name="description" maxlength="3"
					placeholder="Enter description" style="width: 208px" value="">
				</td>

				<td style="position: fixed;"><font color="red"></font></td>
			</tr>

			<tr>
				<th style="padding: 3px"></th>
			</tr>

			<tr>
				<th align="left">No. of Question<span style="color: red">*</span>
					:
				</th>
				<td><input type="number" name="noOfQuestion" maxlength="3"
					placeholder="Enter No. of question" style="width: 208px" value="">
				</td>

				<td style="position: fixed;"><font color="red"></font></td>
			</tr>

			<tr>
				<th style="padding: 3px"></th>
			</tr>

		</table>

	</div>
</body>
</html>