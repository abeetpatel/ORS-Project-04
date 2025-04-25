<%@page import="com.rays.pro4.Exception.RecordNotFoundException"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Model.MarksheetModel"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.rays.pro4.Bean.MarksheetBean"%>
<%@page import="com.rays.pro4.controller.MarksheetListCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Marksheet List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
		<%@include file="Header.jsp"%>

		<%
			List l = (List) request.getAttribute("rollNo");

			int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
		%>

		<center>
			<div align="center">
				<h1>Marksheet List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h2>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h2>
			</div>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				/*  	System.out.println("list aayi");*/
				Iterator<MarksheetBean> it = list.iterator();

				if (list.size() != 0) {
			%>


			<table width="100%" align="center">
				<tr>
					<td align="center"><label> Student Name :</font></label> <input
						type="text" name="name" placeholder="Enter Student Name"
						value="<%=ServletUtility.getParameter("name", request)%>">

						&emsp; <label> Result :</font></label> <input type="text" name="result"
						placeholder="Enter pass fail"
						value="<%=DataUtility.getString(request.getParameter("result") == null ? ""
						: DataUtility.getString(request.getParameter("result")))%>">
						&emsp; <label>RollNo :</label> <%-- <input type="text" name="rollNo" placeholder="Enter Roll Number" value="<%=ServletUtility.getParameter("rollNo", request)%>">
                    --%> <%=HTMLUtility.getList("rollNo123", String.valueOf(bean.getId()), l)%>
						&nbsp;<input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_SEARCH%>"> &nbsp;<input
						type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>




			<table border="1" width="100%" align="center" cellpadding=6px
				id="demo" cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">
						Select All.</th>
					<th>S.No.</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage</th>
					<th>Result</th>
					<th>Edit</th>
				</tr>
				<%
					String result = DataUtility.getString(request.getParameter("result"));
						if (result != null && result.length() > 0) {
							Iterator<MarksheetBean> itr = list.iterator();

							/* System.out.println(request.getParameter("tot"));
							System.out.println("ifchla");
							*/ while (itr.hasNext()) {
								bean = itr.next();

								int phy = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
								int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
								int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
								int total = (phy + chem + math);
								float perc = total / 3;
								/*  if(total!=t){
								count=1;
								}
								 */
								if (phy >= 33 && chem >= 33 && math >= 33 && result.equalsIgnoreCase("pass")) {
				%>

				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
					<td><%=total%></td>
					<td><%=((perc) + "%")%></td>

					<td>
						<%
							if (phy >= 33 && chem >= 33 && math >= 33) {
						%> <span style="color: green"> Pass</span> <%
 	} else {
 %> <span style="color: red"> Fail</span> <%
 	}
 %>
					</td>
					<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>

				</tr>
				<%
					}
								if (result.equalsIgnoreCase("fail")) {
									if (phy < 33 || chem < 33 || math < 33) {
				%>

				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
					<td><%=total%></td>
					<td><%=((perc) + "%")%></td>

					<td>
						<%
							if (phy >= 33 && chem >= 33 && math >= 33) {
						%> <span style="color: green"> Pass</span> <%
 	} else {
 %> <span style="color: red"> Fail</span> <%
 	}
 %>
					</td>
					<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>

				</tr>
				<%
					}
								}
							}
				%>
				<%
					} else {
							/* System.out.println("else cla");*/
							while (it.hasNext()) {
								bean = it.next();

								int phy = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
								int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
								int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
								int total = (phy + chem + math);
								float perc = total / 3;
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
					<td><%=total%></td>
					<td><%=((perc) + "%")%></td>

					<td>
						<%
							if (phy >= 33 && chem >= 33 && math >= 33) {
						%> <span style="color: green"> Pass</span> <%
 	} else {
 %> <span style="color: red"> Fail</span> <%
 	}
 %>
					</td>
					<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>

				</tr>

				<%
					}
				%>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>


					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>



					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>

				</tr>
			</table>


			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=MarksheetListCtl.OP_BACK%>"></td>
			<%
				}
			%>


			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</center>


	<%@include file="Footer.jsp"%>
</body>
</html>



