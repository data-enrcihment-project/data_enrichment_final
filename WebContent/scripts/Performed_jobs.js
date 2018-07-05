var CallPerformedJobs = function (psDuration)
{
	debugger;
	$.ajax({
		url : 'Performed_Jobs',
		type: "GET",
		data : {duration : psDuration},
		success : function(data) {
			debugger;
			alert(data+"asdsa");	
			
			//method for mapping product details
			DisplayProductDetails($.parseJSON(data));
		}
	});
	
};
var OnChangeProdCombo = function(obj)
{
	debugger;
	//obj.options[el.selectedIndex].value]();
	if(obj.value == "")
		return;
	
	var choices = ["one", "two"];

	function addInput(divName) {
	    var select = $("<select/>")
	    $.each(choices, function(a, b) {
	        select.append($("<option/>").attr("value", b).text(b));
	    });
	    $("#" + divName).append(select);
	}
	
	CallConnection(obj.value);
}

var DisplayProductDetails = function(details)
{
	debugger;
	$("#divPerformedJobs").html("");
	
	$("#divPerformedJobs").jsGrid({
        width: "100%",
        height: "400px",
 
        //inserting: true,
        //editing: true,
        sorting: true,
        paging: true,
 
        data: details,
 
        fields: [
            {title:"Title", name: "Item_title", type: "text", width: 150 },
            { title: "Price" ,name: "Item_Price", type: "text", width: 200 },
            { title: "Item URL", name: "Item_URL", type: "text", width: 200 },
            { title: "Enriched Time", name: "Time_Stamp", type: "text", width: 200 },
            
        ]
    });
};



