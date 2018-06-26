var CallConnection = function ()
{

	$.ajax({
		url : 'dataEnrichment_db',
		type: "GET",
		success : function(data) {
			debugger;
			alert(data+"asdsa");	
			
			//method for mapping product details
			DisplayProductDetails($.parseJSON(data));
		}
	});
	
};

var DisplayProductDetails = function(details)
{
	debugger;
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
            { title: "Description" ,name: "description", type: "text", width: 50 },
            { title: "Email Address", name: "language_code", type: "text", width: 200 },
            { title: "Shop Url", name: "shop_code", type: "text", width: 200 },
            { type: "control", width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                  
                    return $("<a>").attr("href", "AmazonItemDescription.jsp?psDescr="+data.description+"&shop_code="+data.shop_code+"&id="+data.id)
                    .attr("target", "_blank")
                    .text("Get Amazon Details");
                    
              
                }
            },
            { type: "control", width: 100,
            	itemTemplate: function(ret, data) {
            		
                    var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                   
                    //var customHref = $("<a>").attr("href","itemDescription.jsp").text("Go To Item")
                   
                    
                   return $("<a>").attr("href", "ItemDescription.jsp?psDescr="+data.description+"&shop_code="+data.shop_code+"&id="+data.id)
                    .attr("target", "_blank")
                    .text("Get Ebay Details");
                    
                  /*  
                    var $customButton = $("<button type='submit'>")
                    	.text("Click")
                    	.href(function(e) {
                    		debugger;
                    		GetEbayDetailsforItems(data.id,data.shop_code,data.description);
                            e.stopPropagation();
                        });s
                    //return $("<div>").append("<a type='submit' href=ItemDescription.jsp?psDescr="+data.description+ "text='Get Ebay Details' ></a>");

                    return $result.add($customButton);*/
                }
            },
            //onclick='GetEbayDetailsforItems(id,code);'
            
            { title: "Check for product details", type: "text" , template: "<a type='button' href='ItemDescription?psDescr=' text='Get Ebay Details' ></a>", width: 200 }
            //{ name: "Married", type: "checkbox", title: "Is Married", sorting: false },
            //{ type: "control" }
        ]
    });
};

var GetEbayDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and itemdescription
	alert('asdsdCall');
	debugger;
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	debugger;
	$.ajax({
		url : 'dataEnrichment_db?psDescr='+psDescr,//'ItemDescription.jsp?psDescr='+psDescr,//
		type: "POST",
		success : function(data) {
			debugger;
			
					
					alert('asd');
					
			//$("#divProductItemDetails").append("<a type='button' href='ItemDescription?psDescr=1212' text='Get Ebay Details' ></a>")
//			document.navigate.action="/ItemDescriptipn.jsp?psDescr="+psDescr;
//			document.navigate.submit();
			
			MapItemDescription((data));
			alert(data+"asdsa");	
		}
	});
};


var GetAmazonDetailsforItems = function(psId,pscode,psDescr)
{
	////Post method have to be changed to get and itemdescription
	alert('asdsdCall');
	debugger;
	$("#ebayJsonDataID")[0].value =psId;
	$("#ebayJsonDataDescr")[0].value =psDescr;
	$("#ebayJsonDataCode")[0].value =pscode;
	
	debugger;
	$.ajax({
		url : 'AmazonItemDescription?psDescr='+psDescr,//'ItemDescription.jsp?psDescr='+psDescr,//
		type: "GEt",
		success : function(data) {
			debugger;
			
					
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
	debugger;
	$("#ebayJsonData").val(objData);
	var obj= $.parseJSON(objData)
	var count = obj[0];
	i=1;
	do{
		if(obj[i].offerSummary==undefined || obj[i].offerSummary==null)
			break;
		
		var price = obj[i].offerSummary.lowestNewPrice.formattedPrice;
		var shipLoc = obj[i].offers.offer[0].offerListing[0].availability;
		
		var sti = "<table><td><h1>Title :"+ obj[i].itemAttributes.title +"</h1><br/>" +
		"<h1>Condition : " + obj[i].offers.offer[0].offerAttributes.condition +
		"</h1><br/> Item Image: <img src='"+ obj[i].imageSets[0].imageSet[0].mediumImage.url +"' /><br/><label text= "+obj[i].location+"></label><br/>"+
			

			"</br> Selling Price :'"+ price+"'<br/>"+
			"Ship To Location : " +shipLoc+"<br/>"+
			"<a href= '"+obj[i].detailPageURL+"' >View Item Url</a><br/>"+
			"</td>"+
			"</table>"+
			"<td><input type='button' onclick=CallDataEnrichmentMethod('"+obj[i].asin+"','asin'); value='Product View URL' /></td>";
		//$("#ulItemDetails").append("<li>").append(
		$("#divProductItemDetails").append(sti);//.append("</li>");
		
		
		i++;
	}while(i<count);
	
};

var MapItemDescription = function(objData)
{
	debugger;
	$("#divProductItemDetails").html("");
	$("#ebayJsonData").val(objData);
	var obj= $.parseJSON(objData)
	var count = obj[0];
	i=1;
	do{
		
		if(obj[i].sellingStatus==undefined || obj[i].sellingStatus==null)
			break;
		
		var price = obj[i].sellingStatus.currentPrice.value;
		var shipLoc = obj[i].shippingInfo.shipToLocations[0];
		var paymethod = obj[i].paymentMethod[0];
		
		var sti = "<table><td><h1>Title :"+ obj[i].title+"</h1><br/>" +
		"<h1>Condition : " + obj[i].condition.conditionDisplayName +
		"</h1><br/> Item Image: <img src='"+ obj[i].galleryURL +"' /><br/><label text= "+obj[i].location+"></label><br/>"+
			"Payment Method: "+ obj[i].paymentMethod[0]+"<br/>"+

			"</br> Selling Price :"+ price+"<br/>"+
			"Ship To Location : " +shipLoc+"<br/>"+
			"payment Method :"+paymethod+"<br/>"+
			"<a href= '"+obj[i].viewItemURL+"' >View Item Url</a><br/>"+
			"</td>"+
			"</table>"+
			"<td><input type='button' text='Enrich Data' onclick=CallDataEnrichmentMethod('"+obj[i].itemId+"','itemId'); value='Product View URL' /></td>";
		//$("#ulItemDetails").append("<li>").append(
		$("#divProductItemDetails").append(sti);//.append("</li>");
		
		
		i++;
	}while(i<count);
	
};
var CallDataEnrichmentMethod = function(psItemID,pstype)
{
	debugger;
	var jsonData = $("#ebayJsonData").val();
	var obj= $.parseJSON(jsonData);
	var count = obj[0];
	var ebayDataArr = new Array();
	i=1;
	do{
		if(obj[i].eval(pstype) ==psItemID)
		{
			ebayDataArr.push(obj[i]);
			break;
		}
		
		i++;
	}while(i<=count);
	
	var jsonString = JSON.stringify(ebayDataArr[0]);
	SaveEbayData(jsonString);
	
};

var SaveEbayData = function (psJsonString)
{
debugger;
	$.ajax({
		url : 'ItemDescription?psJsonString='+psJsonString,
		type: "POST",
		success : function(data) {
			debugger;
			alert(data+"asdsa");	
			
			//method for mapping product details
			//DisplayProductDetails($.parseJSON(data));
		}
	});
	
};


