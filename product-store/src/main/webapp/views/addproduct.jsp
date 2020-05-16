<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file = "../views/common/commonheader.jsp" %>   
  <div align="center">
        <h1>Add New Product</h1> 
       <form:form method="post" action="save">    
        <table > 
        <tr style="visibility:hidden;">    
          <td>ID : </td>   
          <td><form:input path="id"  /></td>  
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
          <td>Product Price :</td>    
          <td><form:input path="price" /></td>  
         </tr>   
         <tr>    
          <td>Category :</td>    
          <td><form:input path="category" /></td>  
         </tr>   
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form> 
     </div>   
     <%@ include file = "../views/common/commonfooter.jsp" %>