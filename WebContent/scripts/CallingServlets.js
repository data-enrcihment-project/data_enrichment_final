var CallConnection = function ()
{

	$.ajax({
		url : 'dataEnrichment_db',
		type: "GET",
		success : function(data) {
			debugger;
			alert(data);	
		}
	});
	
};