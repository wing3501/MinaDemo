<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>

<form id="itemForm" action="${pageContext.request.contextPath }/av/upload" method="post" enctype="multipart/form-data">
测试文件上传：
<table width="100%" border=1>
<tr>
	<td>文件</td>
	<td>
		<input type="file"  name="appFile"/> 
	</td>
</tr>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
	<td>appVersion</td>
	<td><input type="text" name="appVersion"/></td>
</tr>
<tr>
	<td>isReleased</td>
	<td><input type="text" name="isReleased"/></td>
</tr>
<tr>
	<td>isForced</td>
	<td><input type="text" name="isForced"/></td>
</tr>
<tr>
	<td>displayVersion</td>
	<td><input type="text" name="displayVersion"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>




<form id="getAppKeyForm" action="${pageContext.request.contextPath }/av/getAppVersion" method="post">
测试获取最新版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>

<form id="setupReleasedForm" action="${pageContext.request.contextPath }/av/setupReleased" method="post">
测试发布版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
	<td>appVersion</td>
	<td><input type="text" name="appVersion"/></td>
</tr>
<tr>
	<td>isReleased</td>
	<td><input type="text" name="isReleased"/></td>
</tr>
<tr>
	<td>isForced</td>
	<td><input type="text" name="isForced"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>


<form id="setupLatestForm" action="${pageContext.request.contextPath }/av/setupLatest" method="post">
测试设置最新版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
	<td>appVersion</td>
	<td><input type="text" name="appVersion"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>


<form id="getAllVersionByKeyForm" action="${pageContext.request.contextPath }/av/getAllVersionByName" method="post">
测试历史版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>
</form>


<form id="getAllAppForm" action="${pageContext.request.contextPath }/av/getAllApp" method="post">
测试获取所有版本：
<table width="100%" border=1>

<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>
</form>


<form id="saveAppVersionForm" action="${pageContext.request.contextPath }/av/uploadVersion" method="post" enctype="multipart/form-data">
测试上传ipa\apk：
<table width="100%" border=1>
<tr>
	<td>文件</td>
	<td>
		<input type="file"  name="appFile"/> 
	</td>
</tr>

<tr>
	<td>isReleased</td>
	<td><input type="text" name="isReleased"/></td>
</tr>
<tr>
	<td>isForced</td>
	<td><input type="text" name="isForced"/></td>
</tr>
<tr>
	<td>displayVersion</td>
	<td><input type="text" name="displayVersion"/></td>
</tr>
<tr>
	<td>description</td>
	<td><input type="text" name="description"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>
</form>


<form id="getFileVersion" action="${pageContext.request.contextPath }/av/getFileVersion" method="post">
测试获取最新的ipa/apk信息：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>

<!-- 
<form id="setupFileReleasedForm" action="${pageContext.request.contextPath }/av/setupFileReleased" method="post">
测试ipa/apk发布版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
	<td>appVersion</td>
	<td><input type="text" name="appVersion"/></td>
</tr>
<tr>
	<td>isReleased</td>
	<td><input type="text" name="isReleased"/></td>
</tr>
<tr>
	<td>isForced</td>
	<td><input type="text" name="isForced"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>


<form id="setupFileLatestForm" action="${pageContext.request.contextPath }/av/setupFileLatest" method="post">
测试设置ipa/apk最新版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
	<td>appVersion</td>
	<td><input type="text" name="appVersion"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
 -->


<form id="getAllFileVersionByName" action="${pageContext.request.contextPath }/av/getAllFileVersionByName" method="post">
测试ipa/apk历史版本：
<table width="100%" border=1>
<tr>
	<td>appName</td>
	<td><input type="text" name="appName"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>
</form>





</body>
</html>