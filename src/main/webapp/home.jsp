<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<p> Welcome to home</p>

 <form action="save" method="post">  
            <table>  
             <tr>  
                    <td><label for="Employee Id">Employee id</label></td>  
                    <td><input type="text" name="id"></input></td>  
                </tr>
                <tr>  
                    <td><label for="EmpName">Employee Name</label></td>  
                    <td><input type="text" name="name"></input></td>  
                </tr>  
                <tr>  
                    <td><label for="supervisor">Supervisor</label></td>  
                    <td><input type="text" name="super"></input></td>  
                </tr>  
                 <tr>  
                    <td><label for="CTC">CTC</label></td>  
                    <td><input type="text" name="ctc"></input></td>  
                </tr>  
                <tr>  
                    <td></td>  
                    <td><input type="submit" value="Submit"></input></td>  
                </tr>  
            </table>  
        </form>  

</body>
</html>