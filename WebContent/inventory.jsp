<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Music</title>
  </head>
  <body>
    <div>
      <h1>Track List</h1>
      <h2><a href="/songs">View All</a></h2>
    </div>
    <div>
      <table border="1">
        <caption>All songs</caption>
        
        <tr>
          <td>Title</td>
          <td>Artist</td>
          <td>Album</td>
          <td>Length</td>
        </tr>
        <c:forEach var="song" items="${songs}">
          <tr>
            <td><c:out value="${song.title}" /></td>
            <td><c:out value="${song.artist}" /></td>
            <td><c:out value="${song.album}" /></td>
            <td><c:out value="${song.length}" /></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>