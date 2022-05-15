$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatepaymentForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "paymentsAPI", 
 type : type, 
 data : $("#formpayment").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onpaymentSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onpaymentSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divpaymentsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidpaymentIDSave").val(""); 
$("#formpayment")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidpaymentIDSave").val($(this).data("paymentid")); 
		 $("#paymentCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#paymentName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#paymentPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#paymentDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "paymentsAPI", 
		 type : "DELETE", 
		 data : "paymentID=" + $(this).data("paymentid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onpaymentDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onpaymentDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divpaymentsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validatepaymentForm()
{
	// CODE
	if ($("#paymentCode").val().trim() == "")
	{
	return "Insert payment Code.";
	}
	// NAME
	if ($("#paymentName").val().trim() == "")
	{
	return "Insert payment Name.";
}

// PRICE-------------------------------
if ($("#paymentPrice").val().trim() == ""){
	return "Insert payment Price.";
}
		// is numerical value
		var tmpPrice = $("#paymentPrice").val().trim();
		if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for payment Price.";
	}
		
// convert to decimal price
$("#paymentPrice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#paymentDesc").val().trim() == ""){
	
	return "Insert payment Description.";
}
	return true;
}