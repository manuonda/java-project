CREATE TABLE IF NOT EXISTS users(
   id int not null auto_increment primary key,
   name varchar(100),
   username varchar(100),
   email varchar(100),
   password varchar(100)
);

CREATE TABLE IF NOT EXISTS posts(
    id int not null auto_increment primary key,
    title varchar(255),
    description varchar(255),
    content TEXT
);

CREATE TABLE IF NOT EXISTS comments(
   id int not null auto_increment primary key,
   name varchar(100),
   email varchar(100),
   body text, 
   post_id int
);

CREATE TABLE IF NOT EXISTS roles(
   id int not null auto_increment primary key,
   name varchar(100)
);


CREATE TABLE IF NOT EXISTS user_roles(
    id int not null auto_increment primary key,
    user_id int ,
    role_id int
);
