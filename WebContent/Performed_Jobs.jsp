
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>

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
<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/sb-admin-2.min.css" />
<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.min.css" />
<script src="scripts/html5shiv.js"></script>
<script src="scripts/respond.min.js"></script>

<script src="scripts/Performed_jobs.js"></script>

<script  type="text/javascript">
$(document).ready(function(){
	
	CallPerformedJobs('');
});

</script>
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
            
                       
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Performed Jobs</h1>
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
						
                            <div class="table-responsive">
							
							<div id="divPerformedJobs">
							</div>
		
                            </div>
                            <!-- <p>Grid classes apply to devices with screen widths greater than or equal to the breakpoint sizes, and override grid classes targeted at smaller devices. Therefore, applying any
                                <code>.col-md-</code> class to an element will not only affect its styling on medium devices but also on large devices if a
                                <code>.col-lg-</code> class is not present.</p> -->
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			
			<!--
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3>Generated Graph</h3>
                            <p> Below is the generated graph based on the <b>Pricing module</b>.</p>
                           
							<!--
						   <table class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>
												Record No.
											</th>
											<th>
                                                Item imagine
                                                
                                            </th>
                                            <th>
                                                Item title
                                                
                                            </th>
                                            <th>
                                                Item description
                                                
                                            </th>
                                            <th>
                                                Item price
                                                
                                            </th>
                                            <th>
                                                Item URL
                                                
                                            </th>
											
											<th>
                                                Item reviews							
												
                                            </th>
											
											<th>
                                                Check record
											
												
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<td>None (auto)</td>
                                            <td>750px</td>
                                            <td>970px</td>
                                            <td>1170px</td>
											 <td>970px</td>
                                            <td>hahah</td>
											<td>hahah</td>
											<td> 
											
											<form action="/action_page.php" method="get">
											<input type="checkbox" ><br>
												<!-- name="vehicle" value="Bike" -->
											</form>
											
											</td>
                                       <!--  
                                        <tr>
                                            <th>Max container width</th>
                                            <td>None (auto)</td>
                                            <td>750px</td>
                                            <td>970px</td>
                                            <td>1170px</td>
                                        </tr>
                                        <tr>
                                            <th>Class prefix</th>
                                            <td>
                                                <code>.col-xs-</code>
                                            </td>
                                            <td>
                                                <code>.col-sm-</code>
                                            </td>
                                            <td>
                                                <code>.col-md-</code>
                                            </td>
                                            <td>
                                                <code>.col-lg-</code>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th># of columns</th>
                                            <td colspan="4">12</td>
                                        </tr>
                                        <tr>
                                            <th>Max column width</th>
                                            <td class="text-muted">Auto</td>
                                            <td>60px</td>
                                            <td>78px</td>
                                            <td>95px</td>
                                        </tr>
                                        <tr>
                                            <th>Gutter width</th>
                                            <td colspan="4">30px (15px on each side of a column)</td>
                                        </tr>
                                        <tr>
                                            <th>Nestable</th>
                                            <td colspan="4">Yes</td>
                                        </tr>
                                        <tr>
                                            <th>Offsets</th>
                                            <td colspan="4">Yes</td>
                                        </tr>
                                        <tr>
                                            <th>Column ordering</th>
                                            <td colspan="4">Yes</td>
                                        </tr> -->
                                    </tbody>
									
                                </table>
                                <!-- <button type="button" onclick="alert('Hello world!')">Refresh result</button> <button type="button" onclick="alert('Hello world!')">Clear</button>
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

<script src="scripts/Performed_jobs.js"></script>

</body>
</html>