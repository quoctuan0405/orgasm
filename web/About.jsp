<%-- 
    Document   : About
    Created on : Sep 15, 2021, 7:30:26 AM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
        
        <!-- Common CSS file -->
        <link href="css/common.css" rel="stylesheet">
        
        <!-- Specific CSS file -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css" />
    </head>

    <style>
        body {
            font-family: 'Roboto';font-size: 22px;
        }
    </style>

    <body>
        <%@include file="components/Header.jsp"%>
        <div class="container">

            <div class="content">
                <div class="slide">
                    <p>About us</p>
                </div>
                <div class="content-above">
                    <h1>Orgasm</h1>
                    <p class="subtitle">Organic and <span class="italic">-ism</span></p>
                    <p class="o1">Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. 
                        Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, 
                        eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. 
                        Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus 
                        convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. 
                        Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. 
                        Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.
                    </p>
                </div>

                <div class="icons">
                    <div class="phone2">
                        <img src="${pageContext.request.contextPath}/assets/images/Phone.svg" class="phone" />
                        <p class="caption">Phone</p>
                        <p class="detail">+65 11.188.888</p>
                    </div>
                        
                    <div class="location2">
                        <img src="${pageContext.request.contextPath}/assets/images/Location.svg" class="location"/>
                        <p class="caption">Address</p>
                        <p class="detail">60 - 49 Road 11379 New York</p>
                    </div>

                    <div class="time2">
                        <img src="${pageContext.request.contextPath}/assets/images/Time.svg" class="time"/>
                        <p class="caption">Open Time</p>
                        <p class="detail">10:00 am to 23:00 pm</p>
                    </div>
                        
                    <div class="email2">
                        <img src="${pageContext.request.contextPath}/assets/images/Email.svg" class="email"/>
                        <p class="caption">Email</p>
                        <p class="detail">hello@colorlib.com</p>
                    </div>
                    

                </div>
            </div>               
        </div>
        <%@include file="components/Footer.jsp"%>
    </body>

</html>
