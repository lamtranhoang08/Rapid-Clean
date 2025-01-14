<%@page import="com.uts.rapid.clean.model.Cleaner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% Cleaner cleaner = (Cleaner) session.getAttribute("cleaner"); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/cleaner-navigationbar.css">
        <title>Welcome to RapidClean!</title>
    </head>
    <body>
        <div id="navbar">
            <header>
                <img class="logo" src="css/rapidclean-navbar-icon.png" alt ="logo" width="158px" height="50px">
                <nav>
                    <ul class="nav-links">
                        <li class="tab"><a href="CleanerOrderServlet?cleanerId=<%=cleaner.getId()%>">Home</a></li>
                        <li class="tab"><a href="#">Earnings</a></li>
                        <li class="tab"><a href="#">Safety Checklist</a></li>
                        <li class="tab"><a href="TicketCleanerServlet">Contact Us</a></li>
                    </ul>
                </nav>
                <div class="dropdown">
                    <a href="#"><button>MY PROFILE ▼</button></a>
                    <div class="dropdown-content">
                        <a href="#">MY ACCOUNT</a>
                        <a href="#">MY PAYMENTS</a>
                        <a href="#">MY JOB HISTORY</a>
                        <a href="ViewRatingServlet">MY RATINGS</a>
                        <a href="TicketCleanerServlet">SUPPORT</a>
                        <a href="logout.jsp">LOGOUT</a>
                    </div>
                </div>
            </header>
            <hr>
        </div>
        <script>
            var navbar = document.getElementById("navbar");
            var sticky = navbar.offsetTop;
            
            window.onscroll = function() {
                if (window.pageYOffset >= sticky) {
                    navbar.classList.add("sticky");
                }
                else {
                    navbar.classList.remove("sticky");
                }
            };
        </script>
    </body>
</html>
