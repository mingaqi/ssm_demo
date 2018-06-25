<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<html>
<body>
<h2>Hello World!</h2>
<form action="${ctxPath }/add" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input name="name" size="15" value="${randomName }"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input name="pwd" size="15"></td>
        </tr>
        <tr>
            <td><button type="submit">提交</button></td>
            <td><button type="reset">重置</button></td>
        </tr>
    </table>
</form>
<br>
<br><br><br>


数据列表：
<table width="40%" border=1>
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>PWD</td>
    </tr>
    <c:forEach items="${userList }" var="data">
        <tr>
            <td>${data.id }</td>
            <td>${data.name }</td>
            <td>${data.pwd }</td>
            <%--<td><a href="${pageContext.request.contextPath }/data/editDatas.action?num=${data.num}">修改</a></td>--%>

        </tr>
    </c:forEach>

</table>
</body>
</html>
