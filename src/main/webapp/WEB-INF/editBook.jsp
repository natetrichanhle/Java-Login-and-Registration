<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Share</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <!-- For any Bootstrap that uses JS or jQuery-->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <!-- YOUR own local JS -->
    <script type="text/javascript" src="/js/app.js"></script>
</head>
<body>
    <!-- Enter body here -->
    <div class="card mx-auto p-5">
        <h1>Change your Entry</h1>
        <a href="/books">back to the shelves</a>
        <form:form action="/books/${id}" method="POST" modelAttribute="book">
            <input type="hidden" name="_method" value="put">
            <div class="mb-3">
                <form:label path="title" for="formInput" class="form-label">Title</form:label>
                <form:input type="text" class="form-control" id="formInput" path="title" />
                <form:errors path="title" class="text-danger" />
            </div>
            <div class="mb-3">
                <form:label path="author" for="formInput" class="form-label">Author
                </form:label>
                <form:input type="text" class="form-control" id="formInput" path="author" placeholder="" />
                <form:errors path="author" class="text-danger" />
            </div>
            <div class="mb-3">
                <form:label path="thoughts" for="formInput" class="form-label">Thoughts:
                </form:label>
                <form:input type="text" class="form-control" id="formInput" path="thoughts" placeholder="" />
                <form:errors path="thoughts" class="text-danger" />
            </div>
            <div class="d-flex justify-content-end">
                <input type="submit" value="Submit" class="btn submit-btn">
            </div>
        </form:form>
    </div>
</body>
</html>