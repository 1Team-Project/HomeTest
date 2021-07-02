<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/vendor/bootstrap/css/login.css" />
</head>
<body>
<form class="form-signin" name="loginform" action="/login" method="post">
  <h1 class="h3 mb-3 font-weight-normal">로그인</h1>
  

    <label for="userid" class="sr-only">로그인 아이디</label>
    <input type="text" id="username" name="username" class="form-control"
    placeholder="아이디를 입력해 주세요" required autofocus>

    <label for="password" class="sr-only">비밀번호</label>
    <input type="password" id="password" name="password" class="form-control"
    placeholder="비밀번호를 입력해 주세요" required>


  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" name="remember-me"> 로그인 기억하기
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <div class="error-message">
  	<span>${loginError}</span>
  </div>
</form>
</body>
</html>