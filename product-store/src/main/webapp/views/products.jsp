<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<meta charset="utf-8"/>
<link href="/twitter-bootstrap/twitter-bootstrap-v2/docs/assets/css/bootstrap2.2.css" rel="stylesheet">  
	<link rel="stylesheet" href="demo.css">
	<link rel="stylesheet" href="footer-distributed.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee Manager</title>
</head>
<style>
	#add{
    float:right;
    margin-right:250px;
}
</style>
<body>
<div>
	<h4><a href="/mvc/home">Home</a></h4>
</div> 
<div align="center">
    <h1>Product List</h1>
    <div align="center">
    	<c:choose>
		<c:when test="${role == 'ROLE_ADMIN'}">
			<h4><a href="/logout">logout</a></h4>
		</c:when>
	</c:choose>
        </div> 
    <br/><br/>
    
     <!-- ============== Table with population of data ================= -->    
 <div class="container">  
 	<form action="viewproductwidimg.jsp" method="get">
       <table id="myTable" class="table table-striped" >  
        <thead>  
          <tr> 
          	<th style="visibility:hidden;">ID</th>
            <th>Product Code</th>  
            <th>Product Name</th>  
            <th>Price</th> 
            <th>Category</th>
            <c:choose>
			    <c:when test="${role == 'ROLE_ADMIN'}">
			        <th>Actions</th>
			    </c:when>
			</c:choose> 
          </tr>  
        </thead>  
        <tbody>  
        <c:forEach items="${listProducts}" var="element">
          <tr> 
          	<td style="visibility:hidden;">${element.id}</td> 
            <td>${element.productcode}</td>  
            <td>${element.productname}</td>  
            <td>${element.price}</td> 
            <td>${element.category}</td> 
            <c:choose>
			    <c:when test="${role == 'ROLE_ADMIN'}">
			        <td>
                    	<a href="/mvc/admin/edit/${element.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/mvc/admin/delete/${element.id}">Delete</a>
            		</td> 
			    </c:when>
			</c:choose>  
          </tr>  
 	</c:forEach>
        </tbody>  
      </table>  
     </form>
 </div>
</div>  
<div id="add" class="w3-container">
	 <c:choose>
		<c:when test="${role == 'ROLE_ADMIN'}">
			<a href="/mvc/admin/add">
		     	<button class="w3-button w3-circle w3-teal w3-card-4">Add</button>
		     </a> 
		</c:when>
	</c:choose>
 </div> 
</body>
</html>