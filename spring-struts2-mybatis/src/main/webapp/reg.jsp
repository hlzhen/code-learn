<%--
  Created by IntelliJ IDEA.
  User: dream
  Date: 2022/3/30
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/reg.action">
            username<input type="text" name="user.username"></br>
            password<input type="password" name="user.password"></br>
            <input type="submit" value="reg">
        </form>
    </body>
</html>
