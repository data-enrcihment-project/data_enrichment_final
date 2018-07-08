var CallCanvasChart = function (psType)
{
	$.ajax({
		url : 'PricingChart',
		type: "GET",
		//dataType:'json',
		data : {selectTypeNo: psType},
		success : function(data) {
			
			data = $.parseJSON(data);
			if(psType=="")
			{
				FillCombo(data);
			}else{
				//method for mapping product details
				MapPricingChart(data);
			}
			
		}
	});
};

var FillCombo = function(poData)
{
	
	var options="";
	$.each(poData, function(index, item) {
        options += '<option value="' + item.item_no + '">' + item.description + '</option>';
	});
	
	$('#cboPricingChartCat').html(options);
};
var cboPricingChartCat = function(obj)
{
	CallCanvasChart(obj.value);
};

var MapPricingChart = function(obj)
{
	var array= new Array();
	var i=0;
	do{
		array.push({y: obj[i].base_price,label:obj[i].description +" -- discount price "+obj[i].discount_price +" with Item No"+ obj[i].item_no});
		
		i++;
	}while(i<obj.length);
	
	
	
	
	var options = {
			title: {
				text: "Pricing Ratio"
			},
			subtitles: [{
				text: "Pricing Ratio Comparison"
			}],
			animationEnabled: true,
			data: [{
				type: "pie",
				startAngle: 40,
				toolTipContent: "<b>{label}</b>: {y}%",
				showInLegend: "true",
				legendText: "{label}",
				indexLabelFontSize: 16,
				indexLabel: "{label} - {y}%",
				dataPoints: array 
			}]
		};
		$("#chartContainer").CanvasJSChart(options);	
}