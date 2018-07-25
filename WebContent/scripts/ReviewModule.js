var OnChangeReviewCombo = function (obj)
{
	embedReviewUrl.style.display="none";
	
	$.ajax({
		url : 'Performed_Jobs',
		type: "GET",
		//dataType:'json',
		data : {type_Id: cboProdReview.selectedIndex},
		success : function(data) {
			
			//method for mapping product details
			MapItemReviewGrid($.parseJSON(data));
		}
	});
};

var MapItemReviewGrid = function(data)
{
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
            {title:"Item No", name: "Item_no", type: "text", width: 150 },
            { title: "Title" ,name: "Item_title", type: "text", width: 200 },
            { title: "Item Price", name: "Item_Price", type: "text", width: 200 },
            { title: "Type",  width: 100,
            	itemTemplate: function(ret, data) {
            		
            		var type = "Amazon";
            		if(data.Type_ID==1)
            		{
            			type = "Ebay"
            		}
            		return type;
                }            
            },
            { title: "Reviews",  width: 100,
            	itemTemplate: function(ret, data) {
            		
            		var self = this;
                    var $customButton = $("<button>")
                            .text("Get Selected Reviews")
                            .click(function (e) {
                            	onItemReviewSelected(data.Item_no,data.Type_ID,data.Item_Reviews);
                                e.stopPropagation();
                            });
                    return $customButton;
            		
                }            
            }
        ]
    });
};


var onItemReviewSelected = function(psItem_No,ps_Type,psEmbedReviewUrl)
{
	debugger;
	
	$("#divReviewModule").html("");
	embedReviewUrl.src = "";
	if(ps_Type==2)
	{	
		embedReviewUrl.style.display="";
		embedReviewUrl.src = psEmbedReviewUrl;//showing reviews
		
	}else{
		//map to table
		//divReviewModule append
		var sellerRating = $.parseJSON(psEmbedReviewUrl);
		var topRated = "Yes";
		if(!eval(sellerRating.topRatedSeller))
		{
			topRated ="No";
		}
		
		var sellerRatingDet = "<table class='table table-bordered table-striped'>" +
				"<tr><td><h1>FeedBack Rating Star :"+ sellerRating.feedbackRatingStar+"</h1><br/></td></tr>" +
		"<tr><td><h1>Feedback Score : " + sellerRating.feedbackScore + "</td></tr>"+
			"<tr><td> Poisitice Feedback Percentage: "+ sellerRating.positiveFeedbackPercent+" % <br/></td></tr>"+

			"<tr><td></br> Seller Name :"+ sellerRating.sellerUserName +"<br/></td></tr>"+
			"<tr><td>Top rated Seller : " +topRated +"<br/></td></tr>"+			
			"</table>";
		$("#divReviewModule").append(sellerRatingDet);
	}
};
