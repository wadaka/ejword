<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
List<Word> list = (List<Word>)request.getAttribute("list");
String searchWord = (String)request.getAttribute("searchWord");
searchWord=(searchWord == null)?"":searchWord;
String mode = (String)request.getAttribute("mode");
mode=mode == null? "":mode;
Integer total = (Integer)request.getAttribute("total");
Integer limit = (Integer)request.getAttribute("limit");
Integer pageNo = (Integer)request.getAttribute("pageNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EJWord</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style>
form{
margin:20px auto;
}
input,select{
margin-right:5px;
}
.pager{
text-align:left;
}
</style>
</head>
<body>
<div class="container">
<form action="/ejword/main" method="get" class="form-inline">
<input type="text" name="searchWord" value="<%=searchWord%>" class="form-control" placeholder="検索語を入力" required>
<select name="mode" class="form-control">
<option value="startsWith" <%if(mode.equals("startsWith")) out.print(" selected"); %>>で始まる</option>
<option value="contains" <%if(mode.equals("contains")) out.print(" selected"); %>>含む</option>
<option value="endsWith" <%if(mode.equals("endsWith")) out.print(" selected"); %>>で終わる</option>
<option value="match" <%if(mode.equals("match")) out.print(" selected"); %>>一致する</option>
</select>
<button type="submit" class="btn btn-primary">検索</button>
</form>
<% if(list !=null && list.size() > 0){ %>
<%-- 件数表示部分作成 --%>

<% if(total <= limit){ %>
<p>全<%=total %>件</p>
<%}else{ %>
<%--ページ番号を利用して何件から何件を表示しているのかを表示する --%>
		<p>全<%=total %>件中  <%= (pageNo-1)*limit+1 %>～<%=pageNo*limit >total? total:pageNo*limit %>件を表示</p>
		<ul class="pager">
		<%if(pageNo > 1) {%>
		<li><a href="/ejword/main?searchWord=<%=searchWord %>&mode=<%=mode %>&page=<%=pageNo-1%>"><span aria-hidden="true">&larr;</span>前へ</a></li>
		<%} %>
		<%if(pageNo*limit < total) { %>
		<li><a href="/ejword/main?searchWord=<%=searchWord %>&mode=<%=mode %>&page=<%=pageNo+1%>">次へ<span aria-hidden="true">&rarr;</span></a></li>
		<%} %>
		</ul>
<%} %>

<table border="1" class="table table-bordered table-striped">
<% for(Word w:list){ %>
<tr><th><%=w.getTitle() %></th><td><%=w.getBody() %></td></tr>
<%} %>
</table>
<%} %>
</div>
</body>
</html>