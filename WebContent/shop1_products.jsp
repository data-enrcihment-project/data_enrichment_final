<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.models.pkg.DbMethods"%>
<%@ page import="com.dcommerce.database.DatabaseQuery"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/jsgrid.css" />
<link rel="stylesheet" type="text/css" href="CSS/theme.css" />
<link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/sb-admin-2.min.css" />
<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.min.css" />

<script src="scripts/jquery-1.11.1.min.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/jsgrid.min.js"></script>
<script src="scripts/jsgrid.core.js"></script>
<script src="scripts/jsgrid.load-indicator.js"></script>
<script src="scripts/jsgrid.load-strategies.js"></script>
<script src="scripts/jsgrid.sort-strategies.js"></script>
<script src="scripts/jsgrid.field.js"></script>
<script src="scripts/jsgrid.field.text.js"></script>
<script src="scripts/jsgrid.field.control.js"></script>
<script src="scripts/CallingServlets.js"></script>
<script src="scripts/html5shiv.js"></script>
<script src="scripts/respond.min.js"></script>


<title>Pricing Module</title>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
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
		
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Pricing Module</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">Existing Products</div>
						<!-- /.panel-heading -->


						<div class="panel-body">
							<div class="table-responsive">
								<form action="price_module.jsp">
									<input type="submit" class="btn btn-default"
										value="Return To Categories" />
								</form>
								<form action="smartPricing_shop1.jsp" method="GET">
									<table class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>Item No</th>
												<th>Description</th>
											</tr>
										</thead>
										<tbody>
											<%
												String line_no = request.getParameter("line_no");
											                                     
											                                      String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_item_has_category "
																						+ "ON shop_item.item_no = shop_item_has_category.item_no "
																						+ "WHERE shop_item_has_category.category_line_no = "+line_no;
											              							//category_line_no ='"+line_no+"'";
											                              		ResultSet rs = DatabaseQuery.showProductsOfSpecificCategory(Statement);
											                                     
											                                       while (rs.next()) {
											                                        	String description = rs.getString("description");
											                                        	String item_no = rs.getString("item_no");
											%>
											<tr>
												<td><input type="radio" name="item_no"
													value="<%out.print(item_no);%>" /></td>
												<td>
													<% out.print(item_no); %>
												</td>
												<td>
													<% out.print(description); %>
												</td>
											</tr>
											<%  }  %>
										</tbody>
									</table>
									<button type="submit" class="btn btn-default">Price
										Model</button>
								</form>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
			</div>
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
</body>
</html>