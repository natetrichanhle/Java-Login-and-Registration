<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Read Share</title>
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

        <body class="text-white">
            <div class="container mx-auto p-5">
                <h1>${book.title}</h1>
                <div class="d-flex m-4 justify-content-center">
                    <a href="/books">back to the shelves</a>
                </div>
                <div class="card mx-auto p-5 bg-dark border-secondary">
                    <p>
                        <c:choose>
                            <c:when test="${book.user.id eq thisLoggedInUser.id}">
                                You
                            </c:when>
                            <c:otherwise>
                                ${book.user.userName} 
                            </c:otherwise>
                        </c:choose>
                    read ${book.title} by ${book.author}.</p>
                    <p>Here are 
                        <c:choose>
                            <c:when test="${book.user.id eq thisLoggedInUser.id}">
                                your
                            </c:when>
                            <c:otherwise>
                                ${book.user.userName}'s
                            </c:otherwise>
                        </c:choose>
                        thoughts:</p>
                    <span class="d-flex align-items-center justify-content-between">
                        <p>${book.thoughts}</p>
                        <c:if test="${book.user.id eq thisLoggedInUser.id}">
                            <span class="d-flex align-items-center btn-group">
                                <a href="/books/${book.id}/edit" class="link-warning">Edit</a>
                            </span>
                        </c:if>
                    </span>
                </div>
            </div>
        </body>

        </html>