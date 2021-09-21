<%-- 
    Document   : UserReport
    Created on : Sep 12, 2021, 5:30:38 AM
    Author     : Admin
--%>

<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>

        <!-- Highchart -->
        <script src="https://code.highcharts.com/highcharts.js" defer></script>
        <script src="https://code.highcharts.com/modules/exporting.js" defer></script>
        <script src="https://code.highcharts.com/modules/export-data.js" defer></script>
        <script src="https://code.highcharts.com/modules/accessibility.js" defer></script>
        
        <!-- Simple-data table -->
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" type="text/css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" type="text/javascript" defer></script>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
        
        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/userReport.css" rel="stylesheet" type="text/css">
        
        <!-- Specific Javascript file -->
        <script src="${pageContext.request.contextPath}/js/userReport.js" defer></script>
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        
        <div class="banner">
            <p class="title">Sale Statistics<p>
            <button class="generate-button">Generate report</button>
        </div>

        <div class="main">
            <div class="chart">
                <figure class="highcharts-figure-revenue">
                    <div id="revenue-container"></div>
                </figure>

                <figure class="highcharts-figure-category">
                    <div id="category-container"></div>
                </figure>
            </div>

            <div class="download-earning-report">
                <p>Download your sale report</p>
                <button class="generate-button">Generate report</button>
            </div>

            <div class="revenue-products">
                <p>Top selling products</p>
                <table id="revenue-products-table">
                    
                </table>
            </div>
        </div>
        
        <%@include file="components/Footer.jsp"%>
        
        <!-- Pass data to Javascript -->
        <script>
            let revenueByMonth = {date: [], revenue: []};
            <c:forEach items="${revenueByMonth}" var="revenue">
                revenueByMonth.revenue.unshift(${revenue.total});
                revenueByMonth.date.unshift("<fmt:formatDate pattern = "MMM-yyyy" value = "${revenue.date}" />");
            </c:forEach>
                
            let productsSoldByCategory = [];
            <c:forEach items="${productsSoldByCategory}" var="category">
                productsSoldByCategory.unshift({name: "${category.name}", y: ${category.totalItems}});
            </c:forEach>
                
            const productsReportHeader = ["Name", "Price", "Sold", "Remained", "Total"];
            let productsReport = [];
            <c:forEach items="${productsReport}" var="product">
                productsReport.unshift(["${product.name}", "${product.price}", "${product.sold}", "${product.remained}", "${product.total}"]);
            </c:forEach>
        </script>
    </body>
</html>
