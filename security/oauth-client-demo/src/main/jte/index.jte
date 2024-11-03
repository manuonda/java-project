@import com.oauth.dto.UserDTO

@param UserDTO usuario

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>User Info</title>
  <link rel="stylesheet" href="styles.css" />
  <style>
    /* General reset */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Body styling */
body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: Arial, sans-serif;
  background-color: #f3f4f6;
}

/* Container for the user info card */
.user-card {
  background-color: #ffffff;
  padding: 20px 40px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
}

/* Username and email styling */
.user-card h2 {
  font-size: 1.5rem;
  color: #333333;
  margin-bottom: 8px;
}

.user-card p {
  font-size: 1rem;
  color: #666666;
  margin-bottom: 20px;
}

/* Logout button styling */
.user-card .logout-btn {
  padding: 10px 20px;
  font-size: 1rem;
  font-weight: bold;
  color: #ffffff;
  background-color: #ff4d4d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.user-card .logout-btn:hover {
  background-color: #e60000;
}

  </style>
</head>
<body>
  <div class="user-card">
    <h2>Hello: ${usuario.userName()}</h2>
     @if(!usuario.userName().isEmpty())
      <p>Email : ${usuario.email()}</p>
     @endif
    <a href="/logout" class="logout-btn">Logout</a>
  </div>
</body>
</html>
