<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Comics Stash </title>
    <link rel="stylesheet" href="css/form.css">
   </head>
<body>
  <div class="wrapper">
    <h2>Login</h2>
    <form action="login" method="post">
      <div class="input-box">
        <input type="text" name="email" placeholder="Enter your email" required>
      </div>
      <div class="input-box">
        <input type="password" name="password" placeholder="Enter password" required>
      </div>
      <div class="input-box button">
        <input type="Submit" value="Log in">
      </div>
      <div class="text">
        <h3>Don't have an account? <a href="index.jsp">Create now</a></h3>
      </div>
    </form>
  </div>

  <script src="js/index.js"></script>
</body>
</html>