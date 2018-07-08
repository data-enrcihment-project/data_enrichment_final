<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="scripts/jquery-1.11.1.min.js"></script> 
<script src="scripts/jquery-ui.js"></script> 

<link rel="stylesheet" type="text/css" href="CSS/jsgrid.css" />
<link rel="stylesheet" type="text/css" href="CSS/theme.css" />

<script src="scripts/jsgrid.min.js"></script>

<script src="scripts/jsgrid.core.js"></script>
<script src="scripts/jsgrid.load-indicator.js"></script>
<script src="scripts/jsgrid.load-strategies.js"></script>
<script src="scripts/jsgrid.sort-strategies.js"></script>
<script src="scripts/jsgrid.field.js"></script>
<script src="scripts/jsgrid.field.text.js"></script>
<script src="scripts/jsgrid.field.control.js"></script>

<!-- <script src="scripts/CallingServlets.js"></script>  -->


<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/sb-admin-2.min.css" />
<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.min.css" />
<script src="scripts/html5shiv.js"></script>
<script src="scripts/respond.min.js"></script>


<script src="scripts/SimilaritemModule.js"></script> 

<title>Insert title here</title>
</head>
<body>





    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">DATA Enrichment Project</a>
            </div>
            <!-- /.navbar-header -->

            
            <!-- /.navbar-top-links -->

             <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">DATA Enrichment Project</a>
            </div>
           
           <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li><!-- href="MD_test_API_workaround.html" -->
                            <a ><i href="#" class="fa fa-dashboard fa-fw"></i> API Work-around <small>(Amazon and eBay)</small><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
							
							   <li>
                                    <a href="index.jsp">API Dashboard</a>
                                </li>
                               <li>
                                    <a href="SimilarItemsModule.jsp">Similar Items For Ebay and Amazon</a>
                                </li>
								
                            </ul>
                        </li>
						
						 <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Pricing Module<span class="fa arrow"></span></a>
							
							
                            <ul class="nav nav-second-level">
                                
								<li>
                                    <a href="price_module.jsp">Pricing Dashboard</a>
                                </li>
                                <li>
                                    <a href="PricingChart.jsp">Pricing Chart</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Review Module<span class="fa arrow"></span></a>
							
							
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="ReviewModule.jsp">Review Dashboard</a>
                                </li>
                                
								
                            </ul>
                            
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-table fa-fw"></i> Performed jobs<span class="fa arrow"></span></a>
							
							<ul class="nav nav-second-level">
								
								<li >
								<a href="Performed_Jobs.jsp">Enrichment Performed</a>
								</li>
								<li>
                                    <a >All</a>
								
                                </li>
                                <li>
                                    <a >Today</a>
                                </li>
                                <li>
                                    <a >This week</a>
                                </li>
								<li>
                                    <a >This month</a>								
                                </li>
								<li>
                                    <a>Last 6 months</a>
                                </li>
								
                            </ul>
							
							
                        </li>
                       
                        
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Get Similar Items Using Ebay and Amazon</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3>Master Data</h3>
                            <p>This is the data extracted from the database.</p>
						
									<select id="cboProdReview" onchange="OnChangesimilarItemCombo(this);">
										<option value="">Select combo</option>
										<option value="1">Similar Items of Ebay Performed Items</option>
										<option value="2">Similar Items of Amazon Performed Items</option>
									</select>
						
                            <div class="table-responsive">
							 
								<div id="divPerformedSimilarItems">

								</div>
								<div id="divSimilarItemsDetail">

									
								</div>
							 
                            </div>
                           
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3>Result set:</h3>
                            <p> Below is the set of result based on the <b>Enrichment operation</b> choose above.</p>
                            
                                <button type="button" onclick="alert('Hello world!')">Save to database</button> <button type="button" onclick="alert('Hello world!')">Refresh result</button> <button type="button" onclick="alert('Hello world!')">Clear</button> <button type="button" onclick="alert('Hello world!')">Download</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

           
            <!-- /.row -->

            <!-- /.row -->

           

        </div>
        <!-- /#page-wrapper -->

    </div>



  <script src="vendor/jquery/jquery.min.js"></script>

    
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    
    <script src="scripts/sb-admin-2.js"></script>
<script src="scripts/jsgrid.core.js"></script>
<script src="scripts/jsgrid.load-indicator.js"></script>
<script src="scripts/jsgrid.load-strategies.js"></script>
<script src="scripts/jsgrid.sort-strategies.js"></script>
<script src="scripts/jsgrid.field.js"></script>
<script src="scripts/jsgrid.field.text.js"></script>
<script src="scripts/jsgrid.field.control.js"></script>



<script src="scripts/SimilaritemModule.js"></script> 



</body>
</html>
