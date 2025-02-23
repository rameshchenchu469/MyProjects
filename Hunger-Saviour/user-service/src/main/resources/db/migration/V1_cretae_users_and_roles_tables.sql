CREATE TABLE users (
     user_id INT AUTO_INCREMENT PRIMARY KEY,
     user_name VARCHAR(200) ,
     password VARCHAR(200),
     created_at TIMESTAMP
);

CREATE TABLE user_role (
 PRIMARY KEY (user_id, roles),
 FOREIGN KEY(role_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE roles (
   role_id INT AUTO_INCREMENT PRIMARY KEY,
   role_name VARCHAR(200),

   user_id INT
   FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE
);