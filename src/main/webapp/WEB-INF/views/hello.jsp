<%@ page contentType = "text/html; charset =UTF-8" language = "java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div> hello world </div>
    <div>data(EL문법) : ${myData} </div>
    <div>data(jstl문법-java코드) :
        <%
         String getData = (String) request.getAttribute("myData");
         out.print(getData);
         %>
    </div>
</body>
</html>