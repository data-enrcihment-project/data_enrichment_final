var CallConnection = function (psType)
{
	debugger;
	
	$.ajax({
		url : 'dataEnrichment_db',
		type: "GET",
		//dataType:'json',
		//data : {type: psType},
		success : function(data) {
			//debugger;
		
			//method for mapping product details
			DisplayProductDetails($.parseJSON(data));
		}
	});
	
};

//divProductDetailSelect
var OnChangeProdCombo = function(obj)
{
	//debugger;
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
	//debugger;
	$("#divProductDetails").jsGrid({
        width: "100%",
        height: "400px",
 
        //inserting: true,
        //editing: true,
        sorting: true,
        paging: true,
 
        data: details,
 
        fields: [
            {title:"Company", name: "company", type: "text", width: 150 },
            { title: "Description" ,name: "description", type: "text", width: 200 },
            { title: "Shop Code", name: "shop_code", type: "text", width: 200 },
            { title: "Enrich Data Using", type: "control", width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                  
                    /*return $("<a>").attr("href", "AmazonItemDescription.jsp?psDescr="+data.description+"&shop_code="+data.shop_code+"&id="+data.id)
                    .attr("target", "_blank")
                    .text("Get Amazon Details");*/
                    debugger;
                    var self = this;
                    var $customButton = $("<button>")
                            .text("Get Selected Amazon Items")
                            .click(function (e) {
                            	GetDetailsforItems("AmazonItemDescription","GET",data.id,data.shop_code,data.description,"Amazon");
                                e.stopPropagation();
                            });
                    return $customButton;
                                      
              
                }
            },
            { title: "Enrich Data Using", type: "control", width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                    //var customHref = $("<a>").attr("href","itemDescription.jsp").text("Go To Item")                  
                   // debugger;
                   /*return $("<a>").on("click", GetDetailsforItems("ItemDescription","POST",data.id,data.shop_code,data.description,"Ebay"))
                   .attr("target", "_blank")
                   .text("Get Ebay Details");
                  */
                   
                   /*return $("<a>").attr("href",  function(e){
                	debugger;
                	   GetDetailsforItems("dataEnrichment_db","POST",data.id,data.shop_code,data.description,"Ebay");
                	   
                   	})
                   .attr("target", "_blank")
                   .text("Get Ebay Details");*/
                   //  "ItemDescription.jsp?psDescr="+data.Ebay Item Description")
                    
                    var self = this;
                    var $customButton = $("<button>")
                            .text("Get Selected Ebay Items")
                            .click(function (e) {
                            	GetDetailsforItems("ItemDescription","GET",data.id,data.shop_code,data.description,"Ebay");//item_no
                                e.stopPropagation();
                            });
                    return $customButton;
                    
                   
                }
            },
            //onclick='GetEbayDetailsforItems(id,code);'
            
            //{ title: "Check for product details", type: "text" , template: "<a type='button' href='ItemDescription?psDescr=' text='Get Ebay Details' ></a>", width: 200 }
            //{ name: "Married", type: "checkbox", title: "Is Married", sorting: false },
            //{ type: "control" }
        ]
    });
};
///////

var GetEbayDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and itemdescription
	alert('asdsdCall');
	debugger;
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	//debugger;
	$.ajax({
		url : 'dataEnrichment_db?psDescr='+psDescr,//'ItemDescription.jsp?psDescr='+psDescr,//
		type: "POST",
		success : function(data) {
			//debugger;
			MapItemDescription((data));
		}
	});
};


////////
var GetDetailsforItems = function(psURL,urlType,psId,pscode,psDescr,type)
{
	////Post method have to be changed to get and itemdescription	
	
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	debugger;
	$.ajax({
		url : psURL,//'ItemDescription.jsp?psDescr='+psDescr,//
		//data: JSON.stringify({ psDescr: psDescr}) ,
		type: urlType,
		data: {"psDescr" : psDescr},
		success : function(data) {
			//debugger;
			
					if(type=="Amazon")
					{
						MapAmazonItemDescription(data);
					}else{
						
						MapItemDescription(data);							
					}
			
			alert(data+"asdsa");	
		}
	});
};


var GetAmazonDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and itemdescription
	alert('asdsdCall');
	//debugger;
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	//debugger;
	$.ajax({
		url : 'AmazonItemDescription?psDescr='+psDescr,//'ItemDescription.jsp?psDescr='+psDescr,//
		type: "GET",
		success : function(data) {
			//debugger;
			
					
					alert('asd');
					
			//$("#divProductItemDetails").append("<a type='button' href='ItemDescription?psDescr=1212' text='Get Ebay Details' ></a>")
//			document.navigate.action="/ItemDescriptipn.jsp?psDescr="+psDescr;
//			document.navigate.submit();
			
					MapAmazonItemDescription((data));
			alert(data+"asdsa");	
		}
	});
};

var MapAmazonItemDescription = function(objData)
{
	$("#divProductItemDetails").html("");
	//debugger;
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
			//+"<td><embed src='"+obj[i].customerReviews.iframeURL+"' width='400' height='200'  /></td>"+
			+"</table>";
		//$("#ulItemDetails").append("<li>").append(
		$("#divProductItemDetails").append(sti);//.append("</li>");
		
		
		i++;
	}while(i<=count);
	
};

var MapItemDescription = function(objData)
{
	//debugger;
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
		//$("#ulItemDetails").append("<li>").append(
		$("#divProductItemDetails").append(sti);//.append("</li>");
		
		
		i++;
	}while(i<=count);
	
};
var CallDataEnrichmentMethod = function(psItemID,pstype,psURL,psEnrichedfrom)
{
	debugger;
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
	debugger;
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
		dataType:'json',
        data:{"psJsonString":psJsonString,"dataID":$("#ebayJsonDataID")[0].value,"dataDescr":$("#ebayJsonDataDescr")[0].value,"dataCode":$("#ebayJsonDataCode")[0].value,"categoryName":psCategoryName,"images_URL":psImages_URL},//
       
		success : function(data) {
			//debugger;if success Done
			alert(data+"asdsa");	
			
			//method for mapping product details
			//DisplayProductDetails($.parseJSON(data));
		}
	});
	
};


