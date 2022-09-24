<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"  isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
 <center>
<h1>Une erreur est survenue lors du traitements des données</h1>
<h2><%=exception.getMessage() %><br/></h2>
 </center> 
</body>
</html>