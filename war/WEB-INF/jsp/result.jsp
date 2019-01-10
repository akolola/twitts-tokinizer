<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>Your result</title>
<style>
   .shadowtext {
    text-shadow: black 1px 1px 1px, white 0 0 15px; /* Параметры тени */
    color: #809080;
   }
</style>
<style type="text/css">
	.buttontext {
    text-shadow: #707070 1px 2px 2px, black 1em 1em 1em; /* Параметры тени */
    color: #607060;
    font-size: 17pt;
   	}
</style>
<style type="text/css">
	.tagtext {
    text-shadow: #707070 0.07em 0.07em 0.1em, white 0 0 0.5em; /* Параметры тени */
    color: #607060;
    font-size: 17pt;
   	}
</style>
</head>
<body bgcolor="CCCCCC" text="505050">
	<br>
	<table align="center">
		<tr>
			<td align="center">
				<span class="shadowtext" style="font-size: 40px">Your words</span>
			</td>
		</tr>
		<tr>
			<td align="center"><form action="index.action">
							   <input type="submit" value="          Try again! =)          " class="buttontext">
							   </form></td>
		</tr>
	</table>
	<table align="center">
		<s:iterator value="listWF" var="wf">
			<tr>
				<td align="center"><span style="font-size: ${wf.font}pt" class="tagtext">${wf.word }</span></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>