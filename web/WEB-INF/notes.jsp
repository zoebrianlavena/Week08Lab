<%-- 
    Document   : notes
    Created on : Oct 24, 2018, 11:13:01 AM
    Author     : 743953
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>
            <table>
                <thead>
                <th>Note ID</th>
                <th>Date Created</th>
                <th>Contents</th>
                <th></th>
                <th></th>
                </thead>

                <tbody>
                    <c:forEach var="note" items="${notes}">
                        <tr>
                            <th>${note.noteId}</th>
                            <th>${note.dateCreated}</th>
                            <th>${note.contents}</th>
                            <th>
                                <form method="post" action="notes?action=delete">
                                    <input type="submit" value="Delete"/>  
                                    <input type="hidden" name="selectednote" value="${note.noteId}"/>
                                </form>
                            </th>
                            <th>
                                <form method="post" action="notes?action=edit">
                                    <input type="submit" value="edit"/>
                                    <input type="hidden" name="selectednote" value="${note.noteId}"/>
                                </form>
                            </th>
                        </tr>
                    </c:forEach>
                </tbody>                
            </table>

        <form method="post" action="notes?action=add">
            <textarea cols="40" rows="15">
            ${content}
            </textarea>
            <input type="submit" value="Add"/>
        </form>
        ${message}
    </body>
</html>
