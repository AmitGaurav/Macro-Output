<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Macro System</title>
</head>
<body>
<center>
<div class="panel panel-primary">
     
      <div class="panel-heading"><h2>Macro Comparison Tool</h2></div>
    </div>
	 <form method="post" action="CompareServlet">
	  <input type="text" name="fname1"><br>
	  <br>
	  <input type="text" name="fname2"><br>
	  <br>
	  <button type="submit" class="btn btn-success">Compare and Download</button>
	 </form>
</center>
</body>
<script type="text/javascript">
</script>
</html>