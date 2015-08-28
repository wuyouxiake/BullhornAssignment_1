<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>All Post</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Post</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.html">Bull Horn</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="AddPost.html">Post</a></li>
        <li><a href="GetPost">Posted</a></li>
        <li><a href="search.html">Search</a></li>
        <li><a href="signin.html">Sign In</a></li>
        <li><a href="signup.html">Sign Up</a></li>
        <li><a href="SignOut">SignOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
<ul class="list-group">
   ${fullUser}
   ${fullList}
</ul>
</div>

<div align="left">
<form class="form-horizontal" role="form" method="get" action="SearchUserPost">
 <input type="hidden" name="userName" value="${userName}">   

<label for="target">Search for: </label><br>
<input type="text" name="target" required>



<input type="submit" name="submit" Value="Seacrh">
<br><br>
</form>

</div>

<a href="search.html">Search All</a><br>
<a href="AddPost.html">Add Post</a><br>
<a href="index.html">Home</a><br><br>
</body>
</html>