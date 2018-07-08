var OnChangesimilarItemCombo = function (obj)
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
			MapSimilarItemGrid($.parseJSON(data));
		}
	});
};

var MapSimilarItemGrid = function(data)
{
	debugger;
///grid method including click event	
	$("#divPerformedSimilarItems").jsGrid({
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
            { title: "Reviews", width: 100,
            	itemTemplate: function(ret, data) {
            		debugger;
            		var psCallFrom = "EbaySimilaritems";
            		if(data.Type_ID==2)
            		{
            			psCallFrom = "AmazonSimilarItems";
            		}            		
            		
            		var self = this;
                    var $customButton = $("<button>")
                            .text("Get Similar Items")
                            .click(function (e) {
                            	onItemselectedSimilarItem(data.Item_no,data.Type_ID,psCallFrom,'getSimilarItems');
                                e.stopPropagation();
                            });
                    return $customButton;
                }            
            },
            { title: "Reviews", width: 100,
            	itemTemplate: function(ret, data) {
            		var psCallFrom = "EbaySimilaritems";
            		if(data.Type_ID==2)
            		{
            			psCallFrom = "AmazonSimilarItems";
            		}
            		
            		var self = this;
                    var $customButton = $("<button>")
                            .text("Get related category Items")
                            .click(function (e) {
                            	onItemselectedSimilarItem(data.Item_no,data.Type_ID,psCallFrom,'getRelatedCategoryItems');
                                e.stopPropagation();
                            });
                    return $customButton;
            		
                }            
            }
        ]
    });
};

var onItemselectedSimilarItem = function(psItemId,psItemType,psURL,psTypeFunction)
{
	debugger;
	$.ajax({
		url : psURL,//URl
		type: "GET",
		//dataType:'json',
		data : {item_Id: psItemId,type_Id:psItemType,typefunction:psTypeFunction},
		success : function(data) {
			debugger;
			alert(data+"asdsa");	
			$("#divSimilarItemsDetail").html("");
			//method for mapping product details
			MapSimilarItemdetails($.parseJSON(data),psItemType);
		}
	});
};

var MapSimilarItemdetails = function(psObjdata,psItemType,psTypeFunction)
{
	debugger;
	$("#divSimilarItemsDetail").html("");
	if(psItemType==1)
	{
		var count = 0;
		//EbayItems
		do{
			if(count>0)
				{
				
				var price =0;
				if(psTypeFunction=="getSimilarItems")
				{
					price = psObjdata.itemRecommendations[count].item.currentPrice;
				}else if(psTypeFunction=="getSimilarItems")
				{
					price = psObjdata.itemRecommendations.item.buyItNowPrice;
				}
				 
				
				var sti = "<table class='table table-bordered table-striped'><tr><td><h1>Title :"+ psObjdata.itemRecommendations[count].item.title+"</h1><br/></td></tr>" +
				
				"</tr></td> Item Image: <img src='"+ psObjdata.itemRecommendations[count].item.imageURL+"' /><br/></label><br/></td></tr>"+
					
					"<tr><td></br> Selling Price :"+ price +"<br/></td></tr>"+
					 
					
					"<tr><td><a href= '"+psObjdata.itemRecommendations[count].item.viewItemURL+"' >View Item Url</a><br/></td></tr>"+
					
					"</table>";
				}
			count++;
		}while(count<psObjdata.itemRecommendations.length);
		
		//$("#ulItemDetails").append("<li>").append(
		$("#divSimilarItemsDetail").append(sti);
		
	}else if(psItemType==2){
		//AmazonItems
		debugger;
		if(psObjdata[0]==0)
			return;
		
		var obj= psObjdata[1].item;
		var count = obj.length;
		i=0;
		do{
			if(count==0)
				break;
			
			if(obj[i].offerSummary==undefined || obj[i].offerSummary==null)
				break;
			
			var price = obj[i].offerSummary.lowestNewPrice.formattedPrice;
			var shipLoc = obj[i].offers.offer[0].offerListing[0].availability;
			
			var sti = "<table><tr><td><h1>Title :"+ obj[i].itemAttributes.title +"</h1><br/></td></tr>" +
			"<tr><td><h1>Condition : " + obj[i].offers.offer[0].offerAttributes.condition +"</td></tr>"+
			"<tr><td> Item Image: <img src='"+ obj[i].imageSets[0].imageSet[0].mediumImage.url +"' /><br/>" +
					"<tr><td><label text= "+obj[i].location+"></label><br/></td></tr>"+
				

				"<tr><td></br> Selling Price :'"+ price+"'<br/></td></tr>"+
				"<tr><td>Ship To Location : " +shipLoc+"<br/></td></tr>"+
				"<tr><td><a href= '"+obj[i].detailPageURL+"' >View Item Url</a><br/></td></tr>"+
				
				//"<tr><td><input type='button' onclick=CallDataEnrichmentMethod('"+obj[i].asin+"','obj[i].asin','AmazonItemDescription','Amazon'); value='Product View URL' /></td></tr>"
				//+"<td><embed src='"+obj[i].customerReviews.iframeURL+"' width='400' height='200'  /></td>"+
				+"</table>";
			//$("#ulItemDetails").append("<li>").append(
			$("#divSimilarItemsDetail").append(sti);//.append("</li>");
			
			
			i++;
		}while(i<=count);
		
	}
};