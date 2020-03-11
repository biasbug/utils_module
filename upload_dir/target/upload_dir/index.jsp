<%--
  Created by IntelliJ IDEA.
  User: xuhongda
  Date: 2020/3/11
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<!-- isELIgnored属性用于指定是否忽略EL表达式 -->
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件、文件夹上传</title>
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/fileupload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="fileupload">请选择上传的文件</label>
            <input type="file" name="fileupload" id="fileupload" >
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
    <hr>
    <form action="${pageContext.request.contextPath}/dirupload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="dirupload">请选择上传的文件夹</label>
            <input type="file" name="dirupload" id="dirupload" webkitdirectory mozdirectory>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>

</body>
</html>
