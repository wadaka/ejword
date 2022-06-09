<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
EJWord ejw = (EJWord)request.getAttribute("ejw");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EJWord</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<style>
.container{
min-height:calc(100vh - 70px);
}
form{
margin:20px auto;
}
input,select{
margin-right:5px;
}
.pager{
text-align:left;
}
.paginationBox{
text-align:center;
}
footer{
height:40px;
background:#347ab7;
color:white;
text-align:center;
line-height:40px;
margin-top:30px;
}
</style>
</head>
<body>
<div class="container">
<form action="/ejword/main" method="get" class="form-inline">
<input type="text" name="searchWord" value="<%=ejw.getSearchWord()%>" class="form-control" placeholder="検索語を入力" required>
<select name="mode" class="form-control">
<option value="startsWith" <%if(ejw.getMode().equals("startsWith")) out.print(" selected"); %>>で始まる</option>
<option value="contains" <%if(ejw.getMode().equals("contains")) out.print(" selected"); %>>含む</option>
<option value="endsWith" <%if(ejw.getMode().equals("endsWith")) out.print(" selected"); %>>で終わる</option>
<option value="match" <%if(ejw.getMode().equals("match")) out.print(" selected"); %>>一致する</option>
</select>
<button type="submit" class="btn btn-primary">検索</button>
</form>
<% if(ejw.getList() !=null && ejw.getList().size() > 0){ %>
<%-- 件数表示部分作成 --%>

<% if(ejw.getTotal() <=ejw.getLimit()){ %>
<p>全<%=ejw.getTotal() %>件</p>
<%}else{ %>
<%--ページ番号を利用して何件から何件を表示しているのかを表示する --%>
		<p>全<%=ejw.getTotal() %>件中  <%= (ejw.getPageNo()-1)*ejw.getLimit()+1 %>～<%=ejw.getPageNo()*ejw.getLimit() >ejw.getTotal()? ejw.getTotal():ejw.getPageNo()*ejw.getLimit() %>件を表示</p>
		<ul class="pager">
		<%if(ejw.getPageNo()>1){ %>
			<li><a href="/ejword/main?searchWord=<%=ejw.getSearchWord() %>&mode=<%=ejw.getMode() %>&page=<%=ejw.getPageNo()-1 %>">←前へ</a></li>
		<%} %>
		<%if(ejw.getPageNo()*ejw.getLimit() < ejw.getTotal()){ %>
			<li><a href="/ejword/main?searchWord=<%=ejw.getSearchWord() %>&mode=<%=ejw.getMode() %>&page=<%=ejw.getPageNo()+1 %>">次へ→</a></li>
		<%} %>
		</ul>
<%} %>

<table border="1" class="table table-bordered table-striped">
<% for(Word w:ejw.getList()){ %>
<tr><th><%=w.getTitle() %></th><td><%=w.getBody() %></td></tr>
<%} %>
</table>
<%} %>
<%if(ejw.getPager() != null){ %>
<div class='paginationBox'>
	<ul class='pagination'>
	<% for(String[] row:ejw.getPager()) {%>
		<li class="<%=row[0]%>">
		<a href="/ejword/main?searchWord=<%=ejw.getSearchWord() %>&mode=<%=ejw.getMode() %>&page=<%=row[1] %>"><%=row[2] %></a>
		</li>
	<%} %>
	</ul>
</div>
<%} %>
</div><!--  container -->
<footer>
&copy; 2022 Wadakas.net
</footer>
</body>
</html>