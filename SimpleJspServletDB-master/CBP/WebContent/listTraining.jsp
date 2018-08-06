<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Trainings</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Training Id</th>
                <th>Title</th>
                <th>Trainer Name</th>
                <th>Duration</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${trainings}" var="training">
                <tr>
                    <td><c:out value="${training.trainingId}" /></td>
                    <td><c:out value="${training.title}" /></td>
                    <td><c:out value="${training.trainer}" /></td>
                    <td><c:out value="${training.duration}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${training.stDate}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${training.edDate}" /></td>
                    <td><a href="TrainingController?action=edit&tId=<c:out value="${training.trainingId}"/>">Update</a></td>
                    <td><a href="TrainingController?action=delete&tId=<c:out value="${training.trainingId}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="TrainingController?action=insert">Add Training</a></p>
</body>
</html>