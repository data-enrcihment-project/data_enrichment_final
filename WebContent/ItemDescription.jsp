<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="scripts/jquery.js"></script> 


<script src="scripts/jsgrid.min.js"></script>


<script src="CSS/jsgrid.min.css"></script> 
<script src="CSS/jsgrid-theme.min.css"></script> 
<script src="scripts/CallingServlets.js"></script> 
<title>Welcome to the jungle</title>
<%
String id = request.getParameter("id");
String ps_code = request.getParameter("shop_code");
String psDescription = request.getParameter("psDescr");
%>
<script type="text/javascript">

debugger;
$(document).ready(function(){
	
	alert('<%=ps_code%>');
	GetEbayDetailsforItems('<%=id%>','<%=ps_code%>','<%=psDescription%>');
	
});


</script>
</head>
<body>






<div id='divProductItemDetails'>

</div>
<input type="text" id="ebayJsonData" style="display:none;" />
<input type="text" id="ebayJsonDataID" style="display:none;" />
<input type="text" id="ebayJsonDataDescr" style="display:none;" />
<input type="text" id="ebayJsonDataCode" style="display:none;" />
</body>
</html>

