package com.models.pkg;

import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiCall;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.CallRetry;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.SdkSoapException;
import com.ebay.sdk.call.GetFeedbackCall;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsAdvancedRequest;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.soap.eBLBaseComponents.AbstractRequestType;
import com.ebay.soap.eBLBaseComponents.AbstractResponseType;
import com.ebay.soap.eBLBaseComponents.AddItemRequestType;
import com.ebay.soap.eBLBaseComponents.AddItemResponseType;
import com.ebay.soap.eBLBaseComponents.CommentTypeCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.FeedbackDetailType;
import com.ebay.soap.eBLBaseComponents.FeedbackSummaryType;
import com.ebay.soap.eBLBaseComponents.FeedbackTypeCodeType;
import com.ebay.soap.eBLBaseComponents.GetFeedbackRequestType;
import com.ebay.soap.eBLBaseComponents.GetFeedbackResponseType;
import com.ebay.soap.eBLBaseComponents.GetItemRequestType;
import com.ebay.soap.eBLBaseComponents.GetItemResponseType;
import com.ebay.soap.eBLBaseComponents.GetSellerListRequestType;
import com.ebay.soap.eBLBaseComponents.GeteBayOfficialTimeRequestType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.PaginationResultType;
import com.ebay.soap.eBLBaseComponents.PaginationType;

public class EbayFeedback {

	 public static String GetEbayFeedback()
	 {
		 

		    ApiAccount account = new ApiAccount();
		    account.setDeveloper("23f48e38-e171-4608-8edc-e72304310ff0" );
		    account.setApplication("AmirMans-DataEnri-SBX-f2ccbdebc-dcb0ffe1" );
		    account.setCertificate("SBX-2ccbdebcf784-67ec-4c41-8ae4-da74" );

		    //String token = "AgAAAA**AQAAAA**aAAAAA**YQk9Ww**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AClIOlAJGLowSdj6x9nY+seQ**90sEAA**AAMAAA**vS97iPRq7Imu0Ynw4JYNNzXueTMhDyndA5KX1c4CNqjFrgJf+SV0zY8ok+hJr9yLQ25+07ziNrPiGZBxVRmAvSflsEU5b90bTH3uP1d1m4tOuCFIGlxiG8PGP+yfL8oRYLjTnEKC41utnFQDfMC9OVmqJZs0Vo0+gBnLAKT60Uhkk21J3l6/avPRHvt0Nn+sVO1CSZPDw1kPc2ZnKF5y6WTGVuoJ9rEtl1WD50XGgtgdzJt7vx7tr6J8wxNjqQGpKlrq+TAhgFj8HvPRMm1XtYu8caU6Peko+zgxVNiPHJVnUt94AgyLXckVGTACxjAfAHD9FpM0pnYk/8em0b0Qf916jqRDmc6c7XyNigWgqsaFyRObEzY21cQPbWwxhh7g8NO5cZZzFE2YHf0/3ZPqjFxaboq6P5Oz8OiBcVe34Lc0dwIo/EUm/4y4Ef+3jrFX8Nxjx9rR4tlFCWMULL2nPE6YBJLOM95b3FldTI9wlor0Nng4To8xPnGdClm+qlClzCfG32K1dr2cVfKDgiCoJQf3vVuNEvBeroD1yf8GjChNgldlv8luo8rgbp23IkWBlci8lRRwUi/1+IdSJtzTyD9KmpQruPHOLra+0u8RmqT8yCYgmgVcnGSyFjyunVMydSziSVTkzS5mR/CmwQ1iMdGDccn6sbSreZyiKvv7vKCLHey7w+l0Ixo+Md0Mr3DkWNK/V2NhxLxAwVfg4CXt82jatYatAD+N9cjHBeM7OKH/iJjEUlp5Q+YYZ7BpT5wp";
		    String token ="AgAAAA**AQAAAA**aAAAAA**7h89Ww**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agD5eEow+dj6x9nY+seQ**vaUEAA**AAMAAA**HStYA0BU/qdxUqP7mCOLA+VuTGI/j2r2bEtJWIHEvZKAATPIco0TqIFlz6GNp+jeTCNZ96favyHfJp99SUYK+fPyGxbW9KZQwbATke86hOw2Fq+3Dd1rUORmAZuuXwDlpLRgz51DzOY//07qoMLhP94GIfQMds2CY/+i6PIVpnSKEnuRfo5O8H6XiCw9qxOE2XuqOs+n+1RsH8YbVRTIy1wsC39OM4KQ8iLidQ40fJYFwJvXEVy3NihAI41QLVk81Wlsgmi5dDjHq3n7qI8zdyahECWDjL7/D3ZEY/sOiIIgMr6e+mxymZ0IovSywEE3J3AoNUK0wpvbIik5Xt1Y9xETJkClShGr3BvtQg6Rs3W0osQXwTCqT7WEEdo7RtTtE0eX727tBVUP2YBZK6s4GX0yBkdyvBXLSsZydudO70LLkbSn6xke91Spzy6+lrTAkVkAFOD+7BIfptKtlZrQlbOK7R184Z+7UT7qwQnUZBfkSfvhtXMUWuw9aQtqLiMu2nwRdXdAYy/jno/5m3xAwJgiT6qhTmLZdKYRP1Tn2IikUaPKcHTueGBaf8iz8vQCnStcUpvzEyFKp7YaqgU9YFPdkfOT3u9d2q3MiUbHI3n2pvF4TMK1/VYyXVvopMi5FMaPsu28B/+lqhrE/YZAmN+BLGqV15+8tRdoKAC8CadBXETL7DIP44JAvCT/faU3UPdh+sA05/YBsGeGDfb4zVZ6Ykz7BnLUzP6Wu5h/jX1EurNnkFM5CMu/EFnbkL/Z";
		    
		    // set ApiAccount and token in ApiCredential
		    ApiCredential credential = new ApiCredential();
		    credential.setApiAccount(account);
		    credential.seteBayToken(token);

		    // add ApiCredential to ApiContext
		    ApiContext context = new ApiContext();
		    context.setApiCredential(credential);
		    
		    
		    // set eBay server URL to call
		    context.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");//( "https://api.ebay.com/wsapi" );  // sandbox
		    
		    // set timeout in milliseconds - 3 minutes
		    context.setTimeout(180000);
		    
		    // set wsdl version number
		    context.setWSDLVersion("423");
		    
		    // turn on logging to standard out
		    /*ApiLogging logging = new ApiLogging();
		   
		    logging.setLogSOAPMessages(true);
		    logging.setLogExceptions(true);
		    context.setApiLogging(logging);*/
		    
		    System.out.println("Contect");
		    // create ApiCall object - we'll use it to make the call
		    ApiCall call = new ApiCall(context);
		    
		    // create soap api request and response objects
		    AbstractRequestType abs = new AbstractRequestType() {
			};
		    //abs.setDetailLevel(1,DetailLevelCodeType.RETURN_ALL);
		   
			
			
		    
		    GetFeedbackRequestType requesta = new GetFeedbackRequestType();
		    requesta.setItemID("182594333027");//"182594333027" "152535118191" "152497613530"
		    //requesta.setDetailLevel("ReturnAll");
		    GetItemRequestType req = new GetItemRequestType();
		    req.setItemID("263271955718");
		    
		    GeteBayOfficialTimeRequestType request = new GeteBayOfficialTimeRequestType();
		    
		    //AbstractResponseType response;
		    
		    GetFeedbackResponseType response;
		    //GetItemResponseType response;
		    
		    // make the call and handle the response
		    try {
		    	System.out.println("Started");
		    	response = (GetFeedbackResponseType) call.executeByApiName("GetFeedback", requesta);// call.execute(request)
		    	//response = (GetItemResponseType) call.executeByApiName("GetItem", req);// call.execute(request)
		    	ObjectMapper mapper = new ObjectMapper();
				String json = "";
				try {
				    // convert user object to json string and return it 
					
				     json =  mapper.writeValueAsString(response);
				     //System.out.println(response.getFeedbackSummary().getSellerRatingSummaryArray().getAverageRatingSummary().getAverageRatingDetails().RatingDetail);
				     System.out.println(json);
				     return json;
				     
				}catch(Exception e)
				{
					
					try {
						throw e;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	/*System.out.println(response);
		        // Get the ebay time
		        // Result inherits from AbstractResponseType
		        Calendar cal = response.getTimestamp();            // ebay time is returned in gmt 

		        // Display official ebay time in gmt
		        int year = cal.get(Calendar.YEAR);
		        int month = cal.get(Calendar.MONTH);
		        month = month + 1;   // java months go from 0-11; make human readable
		        int day = cal.get(Calendar.DAY_OF_MONTH);
		        int hour24 = cal.get(Calendar.HOUR_OF_DAY);
		        int min = cal.get(Calendar.MINUTE);
		        int sec = cal.get(Calendar.SECOND);
		        System.out.println("official ebay time in gmt is: " + year + "-" + month + "-" + day + " " + hour24 + ":" + min + ":" + sec);
		        
		        // Convert ebay time to local time and display
		        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
		        System.out.println("in your time zone " + formatter.format( cal.getTime() ));*/
		        
		    } catch (ApiException ae) {
		    	System.out.println(ae);
		    } catch (SdkSoapException sse) {
		    	System.out.println(sse);
		    } catch (SdkException se) {
		    	System.out.println(se);
		    }
			return "";
		    	
		 
	 }

	 
	 
	 
	
}
