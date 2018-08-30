<%--
  Created by IntelliJ IDEA.
  User: bonree
  Date: 2018/8/29
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
</head>
<body>
    <form name="itemsForm" action="${pageContext.request.contextPath }/items/queryItem.action" method="post">
        查询条件：
        <table width="100%" border=1>
            <tr>
                <td><input type="submit" value="查询"/></td>
                <td><input type="button" value="批量删除" onclick="deleteItems()"></td>
            </tr>
        </table>
        商品列表：
        <table width="100%" border=1>
            <tr>
                <td>选项</td>
                <td>商品名称</td>
                <td>商品价格</td>
                <td>生产日期</td>
                <td>商品描述</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${itemsList }" var="item">
                <tr>
                    <td><input type="checkbox" name="delete_id" value="${item.id}"></td>
                    <td>${item.name }</td>
                    <td>${item.price }</td>
                    <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${item.detail }</td>

                    <td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a></td>

                </tr>
            </c:forEach>

        </table>
    </form>
    <%--<td>--%>
        <%--<select>--%>
            <%--<c:forEach items="${itemsType}" var="item">--%>
                <%--<option value="${item.key}">${item.value}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</td>--%>
</body>
    <script type="text/javascript">
        function deleteItems() {
            <%--var check_value = [];--%>

            <%--$("input[name='delete_id']:checked").each(function () {--%>
                <%--check_value.push($(this).val());--%>
            <%--});--%>
            <%--if (check_value.length == 0) {--%>
                <%--alert("请选择需要删除的商品");--%>
            <%--}--%>
            <%--if (check_value.length > 0) {--%>
                <%--location.href = "${pageContext.request.contextPath}/items/deleteItems.action?delete_id=" + check_value;--%>
            <%--}--%>
            //将form的action指向删除商品的地址
            document.itemsForm.action="${pageContext.request.contextPath}/items/deleteItems.action";

            //进行form提交
            document.itemsForm.submit();

        }
    </script>
</html>
