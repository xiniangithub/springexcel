<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/25 0025
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        table {
            border-collapse:collapse;
            width: 500px;
            text-align:center
        }
        table, td, th {
            border:1px solid black;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <h2>导入Excel</h2>
    <form>
        <input type="file" name="uploadFile" />
        <input type="button" id="uploadBtn" value="上传" />
    </form>

    <hr/>

    <h2>文件下载</h2>
    <table>
        <thead>
            <td width="20%">序号</td>
            <td width="60%">文件名</td>
            <td width="20%">操作</td>
        </thead>
        <c:forEach items="${filenameList}" var="filename" varStatus="status">
            <tr>
                <td>${status.index}</td>
                <td>${filename}</td>
                <td><a href="${pageContext.request.contextPath}/index/download?filename=${filename}">下载</a></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>

    <h2>导出Excel</h2>
    <input type="button" value="导出Excel" />

    <script type="text/javascript">
        $(function() {
            $('#uploadBtn').click(function() {
                var fm = new FormData($('form')[0]);
                console.log(fm);
                $.ajax({
                    url: '${pageContext.request.contextPath}/index/uploadExcel',
                    type: 'post',
                    data: fm,
                    async: true,
                    processData: false,
                    contentType: false,
                    success: function(data) {
                        var result = JSON.parse(data);
                        alert(data.message);
                    }
                });
            });
        });
    </script>
</body>
</html>
