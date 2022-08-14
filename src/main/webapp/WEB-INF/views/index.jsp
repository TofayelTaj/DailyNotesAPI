
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>

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


<h2>Todo List Application.</h2>


<%

//    String recieve;
//    String buffer = null;
//    String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YWpAZ21haWwuY29tIiwiZXhwIjoxNjU5OTEzODgxLCJpYXQiOjE2NTk4OTU4ODF9.ESsndfU14CkehbBWvvAqKPL85LabLigZEaomhHeB99IINBY97k43EklNAGge0IZW2pG8bDsyXuqnE97sQQmrng";
//    URL jsonpage = new URL("https://localhost:8081/api/notes");
//    URLConnection urlcon = jsonpage.openConnection();
//    urlcon.setRequestProperty("JWT", "Bearer "+ jwt);
//    BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
//
//    while ((recieve = buffread.readLine()) != null)
//        buffer += recieve;
//    buffread.close();
//
//    System.out.println(buffer);
%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>


<script>


    $(document).ready(function (){
        var response = '';
        $.ajax({ type: "GET",
            url: "http://localhost:8081/api/notes/7",
            headers : {
            "JWT" : "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YWpAZ21haWwuY29tIiwiZXhwIjoxNjU5OTE4NzQ3LCJpYXQiOjE2NTk5MDA3NDd9.QAQRXWYdy3LkQR-Ag9GqQYE6j21PGFt7cDiYLrpXlKcYTrApOYnR3CQ9jG0kJlQVNs8_aZOwYvWEwb1k9URadQ"
            },

            success : function(text)
            {
                response = text;
                console.log(response);
            }
        });


    })
</script>

</body>
</html>