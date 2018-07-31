var CallConnection = function (psType)
{
	$.ajax({
		url : 'dataEnrichment_db',
		type: "GET",
		//dataType:'json',
		//data : {type: psType},
		success : function(data) {
			
			//method for mapping product details
			DisplayProductDetails($.parseJSON(data));
		}
	});
	
};

//divProductDetailSelect
var OnChangeProdCombo = function(obj)
{
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
	$("#divProductDetails").jsGrid({
        width: "100%",
        height: "400px",
 
        sorting: true,
        paging: true,
 
        data: details,
 
        fields: [
            {title:"Company", name: "company", type: "text", width: 150 },
            { title: "Description" ,name: "description", type: "text", width: 200 },
            { title: "Shop Code", name: "shop_code", type: "text", width: 200 },
            { title: "Enrich Data Using",  width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                
                    var self = this;
                    var $customButton = $("<button>")
                            .text("Get Selected Amazon Items")
                            .click(function (e) {
                            	GetDetailsforItems("AmazonItemDescription","GET",data.item_no,data.shop_code,data.description,"Amazon");
                                e.stopPropagation();
                            });
                    return $customButton;
                                      
              
                }
            },
            { title: "Enrich Data Using",  width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                    var self = this;
                    var $customButton = $("<button>")
                            .text("Get Selected Ebay Items")
                            .click(function (e) {
                            	GetDetailsforItems("ItemDescription","GET",data.item_no,data.shop_code,data.description,"Ebay");//item_no
                                e.stopPropagation();
                            });
                    return $customButton;
                    
                   
                }
            },
        ]
    });
};
///////

var GetEbayDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and item description
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	
	$.ajax({
		url : 'dataEnrichment_db?psDescr='+psDescr,
		type: "POST",
		success : function(data) {
			
			MapItemDescription((data));
		}
	});
};


////////
var GetDetailsforItems = function(psURL,urlType,psId,pscode,psDescr,type)
{
	////Post method have to be changed to get and item description	
	
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	$.ajax({
		url : psURL,
		type: urlType,
		data: {"psDescr" : psDescr},
		success : function(data) {
				if(type=="Amazon")
					{
						MapAmazonItemDescription(data);
					}else{
						
						MapItemDescription(data);							
					}
		}
	});
};


var GetAmazonDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and itemdescription
	
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	$.ajax({
		url : 'AmazonItemDescription?psDescr='+psDescr,
		type: "GET",
		success : function(data) {
			
			MapAmazonItemDescription((data));
			
		}
	});
};

var MapAmazonItemDescription = function(objData)
{
	$("#divProductItemDetails").html("");
	
	$("#ebayJsonData").val(objData);
	var obj= $.parseJSON(objData)
	var count = obj[0];
	i=1;
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
			
			"<tr><td><input type='button' onclick=CallDataEnrichmentMethod('"+obj[i].asin+"','obj[i].asin','AmazonItemDescription','Amazon'); value='Product View URL' /></td></tr>"
			
			+"</table>";
		
		$("#divProductItemDetails").append(sti);
		
		
		i++;
	}while(i<=count);
	
};

var MapItemDescription = function(objData)
{
	
	$("#divProductItemDetails").html("");
	$("#ebayJsonData").val(objData);
	var obj= $.parseJSON(objData)
	var count = obj[0];
	i=1;
	do{
		if(count==0)
			break;
		
		if(obj[i].sellingStatus==undefined || obj[i].sellingStatus==null)
			break;
		
		var price = obj[i].sellingStatus.currentPrice.value;
		var shipLoc = obj[i].shippingInfo.shipToLocations[0];
		var paymethod = obj[i].paymentMethod[0];
		
		var sti = "<table class='table table-bordered table-striped'><tr><td><h1>Title :"+ obj[i].title+"</h1><br/></td></tr>" +
		"<tr><td><h1>Condition : " + obj[i].condition.conditionDisplayName +"</td></tr>"+
		"<tr><td></h1><br/> Item Image: <img src='"+ obj[i].galleryURL +"' /><br/></td></tr>" +
				"<tr><td><label text= "+obj[i].location+"></label><br/></td></tr>"+
			"<tr><td>Payment Method: "+ obj[i].paymentMethod[0]+"<br/></td></tr>"+

			"<tr><td></br> Selling Price :"+ price+"<br/></td></tr>"+
			"<tr><td>Ship To Location : " +shipLoc+"<br/></td></tr>"+
			"<tr><td>payment Method :"+paymethod+"<br/></td></tr>"+
			"<tr><td><a href= '"+obj[i].viewItemURL+"' >View Item Url</a><br/></td></tr>"+
			
			"<tr><td><input type='button' text='Enrich Data' onclick=CallDataEnrichmentMethod('"+obj[i].itemId+"','obj[i].itemId','ItemDescription','Ebay'); value='Enrich Item' /></td></tr>" +
			
			"</table>";
		$("#divProductItemDetails").append(sti);
		
		
		i++;
	}while(i<=count);
	
};
var CallDataEnrichmentMethod = function(psItemID,pstype,psURL,psEnrichedfrom)
{
	
	var jsonData = $("#ebayJsonData").val();
	var obj= $.parseJSON(jsonData);
	var count = obj[0];
	var enrichDataArr = new Array();
	i=1;
	do{
		if(eval(pstype) == psItemID)
		{
			enrichDataArr.push(obj[i]);
			break;
		}
		
		i++;
	}while(i<=count);
	
	var categoryName ="";
	var images_URL ="";
	if(psEnrichedfrom=="Ebay")
	{
		var jsonArray = $.parseJSON(enrichDataArr[0].subtitle);
		if(jsonArray.Item!=undefined && jsonArray!=null)
		{
			$("#ebayJsonDataDescr")[0].value = jsonArray.Item.Description;
			categoryName =jsonArray.Item.PrimaryCategoryName;
			images_URL =JSON.stringify($.parseJSON(enrichDataArr[0].subtitle).Item.PictureURL);
		}
	}else
	{
		categoryName ="";
		images_URL ="";
	}	
	
	var jsonString = JSON.stringify(enrichDataArr[0]);
	
	SaveEnrichData(jsonString,psURL,categoryName,images_URL);
	
};

var SaveEnrichData = function (psJsonString,psURL,psCategoryName,psImages_URL)
{	
	$.ajax({
		url : psURL ,
		type: "POST",		
		//dataType:'json',
        data:{"psJsonString":psJsonString,"dataID":$("#ebayJsonDataID")[0].value,"dataDescr":$("#ebayJsonDataDescr")[0].value,"dataCode":$("#ebayJsonDataCode")[0].value,"categoryName":psCategoryName,"images_URL":psImages_URL},//
       
		success : function(data) {
			
			alert(data);
		}
	});
	
};

var SaveEnrichDataDetail = function (itemId)
{	
	$.ajax({
		url : "http://open.api.ebay.com/shopping?callname=GetSingleItem&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&version=517&responseencoding=JSON&itemid="+ itemId +"&IncludeSelector=Details,Description,Variations,Compatibility" ,
		type: "GET",
       
		success : function(data) {
			alert('Done Saving');
		}
	});
	
};

//

