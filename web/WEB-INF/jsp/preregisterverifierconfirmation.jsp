<%--
    Document   : preregisterverifierconfirmation
    Created on : 07-Sep-2010, 23:22:09
    Author     : Miroslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <title>HafTrust Pre-Register Verifier Confirmation Page</title>
    </head>
    <body>

        <h1 align="center">Verifier Pre-Registration Confirmation</h1>
        <hr size="5">
        <p align="center">
            <b>Pre-Registration of the Verifier Candidate has been successful</b>
        </p>
        <table border="0" align="center">
            <tbody>
                <tr>
                    <td>Temporary ID : </td>
                    <td><c:out value="${prvBean.idVerifier}"></c:out> </td>
                </tr>
                <tr>
                    <td>Email : </td>
                    <td><c:out value="${prvBean.email}"></c:out> </td>
                </tr>
            </tbody>
        </table>
        <p align="center">
            <b>Please proceed to the Registration of the Verifier Candidate page or return to the Home page</b>
        </p>
        <p align="center">
            <a href="registerVerifier.htm">Register Verifier Candidate</a>
        </p>
        <p align="right">
            <a href="index.htm">Home</a>
        </p>
        <hr size="5">
    </body>
</html>
