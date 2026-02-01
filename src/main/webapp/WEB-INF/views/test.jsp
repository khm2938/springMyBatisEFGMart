<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>EFG Mart DB Test</title>
</head>
<body>
  <h1>✅ JSP 렌더링 OK</h1>

  <p><b>현재 시간:</b> ${now}</p>
  <p><b>Oracle DB 테스트 쿼리 결과 (SELECT 1 FROM DUAL):</b> ${dbResult}</p>

  <hr/>
  <p>dbResult가 1이면 DB 연결까지 성공입니다.</p>
</body>
</html>
