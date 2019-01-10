<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>Your Stat application</title>
<style type="text/css">
	.colortext {
    background-color: #DDD;
    text-shadow: black 1px 1px 1px, white 0 0 2px; /* Параметры тени */
    color: #809080;
    font-size: 13pt;
   	}
</style>
<style>
   .shadowtext {
    text-shadow: black 1px 1px 1px, white 0 0 15px; /* Параметры тени */
    color: #809080;
   }
</style>
<style type="text/css">
	.buttontext {
    text-shadow: #707070 1px 1px 1px, black 0.7em 0.7em 1em; /* Параметры тени */
    color: #607060;
    font-size: 11pt;
   	}
</style>
</head>
<body bgcolor="CCCCCC">
	<br><br><br><br><br>
	<table align="center">
		<tr>
			<td align="center">
				<span class="shadowtext" style="font-size: 32px">Input your Nick on twitter.com<br>
																 and number of twits for computing</span>
			</td>
		</tr>
	</table>
	<br>
	<form action="result.action">
		<table align="center">
			<tr>
				<td align="center"><input type="text" align="middle" name="nick" value="Your nick" class="colortext">
				<input type="text" align="middle" name="numTwits" value="Number of twits" class="colortext"></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Get your statistic" class="buttontext"></td>
			</tr>
			<tr>
				<td align="center"><s:fielderror name="emptyNick" cssClass="buttontext"></s:fielderror></td>
			</tr>
			<tr>
				<!-- <td align="center"><s:fielderror name="numTwits" cssClass="buttontext"></s:fielderror></td> -->
			</tr>
			<tr>
				<!-- <td align="center"><s:fielderror name="error" cssClass="buttontext"></s:fielderror></td> -->
			</tr>
		</table>
	</form>
</body>
</html>