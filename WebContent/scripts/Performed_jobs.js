var CallPerformedJobs = function (psDuration)
{
	debugger;
	$.ajax({
		url : 'Performed_Jobs',
		type: "GET",
		data : {duration : psDuration},
		success : function(data) {
			debugger;
		
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
            { title: "Type", type: "Type_ID", width: 100,
            	itemTemplate: function(ret, data) {
            		
            		var type = "Amazon";
            		if(data.Type_ID==1)
            		{
            			type = "Ebay"
            		}
            		return type;
                }            
            },
            { title: "Remove Item",  width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                    var self = this;
                    var $customButton = $("<button>")
                            .text("Delete Selected Items")
                            .click(function (e) {
                            	DeleteSelectedJobs(data.E_ID);
                                e.stopPropagation();
                            });
                    return $customButton;
                }
            }
        ]
    });
};

var DeleteSelectedJobs = function (psItemId)
{
	$.ajax({
		url : 'Performed_Jobs?E_ID='+psItemId,
		type: "DELETE",
		data : {E_ID : psItemId},
		success : function(data) {
			CallPerformedJobs();
		}
	});
	
};


