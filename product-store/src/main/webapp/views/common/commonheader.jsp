<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        <title></title>
        <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Product Store</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/logout">Home</a></li>
      <!--<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
         <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li> -->
      <!-- <li><a href="#">Page 2</a></li> -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <!-- <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
      <c:choose>
		<c:when test="${role == '[ROLE_ADMIN]'}">
			<li><a><span class="glyphicon glyphicon-user"></span> Welcome,${user}</a></li>
			<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</c:when>
		<c:otherwise>
			<li><a><span class="glyphicon glyphicon-user"></span> Welcome,Visitor</a></li>
		</c:otherwise>
	</c:choose>
      
    </ul>
  </div>
</nav>