var OnChangeReviewCombo = function (obj)
{
//ajax and map grid
	
	debugger;
	
	$.ajax({
		url : 'Performed_Jobs',
		type: "GET",
		//dataType:'json',
		data : {type_Id: cboProdReview.selectedIndex},
		success : function(data) {
			debugger;
			alert(data+"asdsa");	
			
			//method for mapping product details
			MapItemReviewGrid($.parseJSON(data));
		}
	});
};

var MapItemReviewGrid = function(data)
{
	debugger;
///grid method including click event	
	$("#divPerformedJobsReview").jsGrid({
        width: "100%",
        height: "400px",
 
        //inserting: true,
        //editing: true,
        sorting: true,
        paging: true,
 
        data: data,
        
        fields: [
            {title:"Item No", name: "Item_ID", type: "text", width: 150 },
            { title: "Title" ,name: "Item_title", type: "text", width: 200 },
            { title: "Item Price", name: "Item_Price", type: "text", width: 200 },
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
            { title: "Reviews", type: "Item_Reviews", width: 100,
            	itemTemplate: function(ret, data) {
            		
            		data.Item_Reviews
            		return $("<a>").on("click", onItemReviewSelected(data.Item_ID,data.Type_ID,data.Item_Reviews))
                    .attr("target", "_blank")
                    .text("Show Reviews");
                }            
            }
        ]
    });
};


var onItemReviewSelected = function(psItem_No,ps_Type,psEmbedReviewUrl)
{
	debugger;
	$("embedReviewUrl")[0].scr = "";
	$("embedReviewUrl")[0].scr = psEmbedReviewUrl;//showing reviews
}