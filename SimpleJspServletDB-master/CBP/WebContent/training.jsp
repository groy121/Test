<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=stDate]').datepicker();
            $('input[name=edDate]').datepicker();
        });
    </script>

    <form method="POST" action='TrainingController' name="frmAddTraining">
        Title : <input
            type="text" name="title"
            value="<c:out value="${training.title}" />" /> <br /> 
        Trainer Name : <input
            type="text" name="trainer"
            value="<c:out value="${training.trainer}" />" /> <br /> 
        Start Date : <input
            type="text" name="stDate"
            value="<fmt:formatDate pattern="MM/dd/yyyy" value="${training.stDate}" />" /> <br /> 
        End Date : <input
            type="text" name="edDate"
            value="<fmt:formatDate pattern="MM/dd/yyyy" value="${training.edDate}" />" /> <br /> 
            <input
            type="submit" value="Submit" />
    </form>
</body>
</html>