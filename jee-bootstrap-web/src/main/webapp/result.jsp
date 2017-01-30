<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Jeu de Dames</h1>
	
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:18px 18px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:18px 18px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
		.tg .tg-n1xd{background-color:#efefef;color:#333333;vertical-align:top}
		.tg .tg-yzt1{background-color:#efefef;vertical-align:top}
		.tg .tg-yvo5{background-color:#656565;vertical-align:top}
	</style>
	
	<table class="tg">
	  <tr>
	    <th class="tg-n1xd"></th>
	    <th class="tg-yvo5"></th>
	    <th class="tg-yzt1"></th>
	    <th class="tg-yvo5"></th>
	    <th class="tg-yzt1"></th>
	    <th class="tg-yvo5"></th>
	    <th class="tg-yzt1"></th>
	    <th class="tg-yvo5"></th>
	    <th class="tg-yzt1"></th>
	    <th class="tg-yvo5"></th>
	  </tr>
	  <tr>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	  </tr>
	  <tr>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	  </tr>
	  <tr>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	  </tr>
	  <tr>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	  </tr>
	  <tr>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	  </tr>
	  <tr>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	  </tr>
	  <tr>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	  </tr>
	  <tr>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	  </tr>
	  <tr>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	    <td class="tg-yvo5"></td>
	    <td class="tg-yzt1"></td>
	  </tr>
	</table>
		
</body>
</html>