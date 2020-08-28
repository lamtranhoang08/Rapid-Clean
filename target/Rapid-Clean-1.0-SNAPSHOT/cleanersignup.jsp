<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script>
          $(function(){
          $("#nav-placeholder").load("navigationbar.jsp");
          });
        </script>
        <title>Cleaner Sign Up</title>
    </head>
    <body style="background-color: #51abff">
        <form action="" method="POST">
            <table>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName"></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Confirm Password</td>
                    <td><input type="password" name="confirmPassword"></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type="text" name="phoneNumber"></td>
                </tr>
                <tr>
                    <td>BSB Number</td>
                    <td><input type="text" name="bsbNumber"></td>
                </tr>
                <tr>
                    <td>Account Number</td>
                    <td><input type="text" name="accountNumber"></td>
                </tr>
                <tr>
                    <td>Account Holder Name</td>
                    <td><input type="text" name="accountHolderName"></td>
                </tr>
            </table>
        </form>
        <a href="home.jsp"><button class="button button2"> Create Account </button></a>
        <a href="signup.jsp"><button class="button button2"> Go Back </button></a>
    </body>
</html>
