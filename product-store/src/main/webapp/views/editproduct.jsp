<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ include file = "../views/common/commonheader.jsp" %>
   <div align="center">
        <h1>Edit Product</h1> 
       <form:form method="POST" action="/mvc/admin/editsave">    
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr>  
         <tr>    
          <td>Product Code : </td>   
          <td><form:input path="productcode"  /></td>  
         </tr>  
         <tr>    
          <td>Product Name : </td>   
          <td><form:input path="productname"  /></td>  
         </tr>    
         <tr>    
          <td>Price :</td>    
          <td><form:input path="price" /></td>  
         </tr>   
         <tr>    
          <td>Category :</td>    
          <td><form:input path="category" /></td>  
         </tr>   
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>   
      </div> 
      <%@ include file = "../views/common/commonfooter.jsp" %>