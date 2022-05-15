<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>payments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>payments Management V10.1</h1>
<form id="formpayment" name="formpayment" method="post" action="payments.jsp">
 payment code: 
 <input id="paymentCode" name="paymentCode" type="text" 
 class="form-control form-control-sm">
 <br> payment name: 
 <input id="paymentName" name="paymentName" type="text" 
 class="form-control form-control-sm">
 <br> payment price: 
 <input id="paymentPrice" name="paymentPrice" type="text" 
 class="form-control form-control-sm">
 <br> payment description: 
 <input id="paymentDesc" name="paymentDesc" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidpaymentIDSave" 
 name="hidpaymentIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divpaymentsGrid">
 <%
 payment paymentObj = new payment(); 
 out.print(paymentObj.readpayments()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>