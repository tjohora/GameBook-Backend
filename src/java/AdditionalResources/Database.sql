CREATE DATABASE IF NOT EXISTS `projectdb`;

CREATE table users(
userId int NOT NULL AUTO_INCREMENT,
userName varchar(50),
password varchar(50),
PRIMARY KEY(userID));

CREATE table userProfile(
userId int NOT NULL,
profileId int NOT NULL,
fname varchar(50),
lname varchar(50),
userType tinyINT,
email varchar(100),
address varchar(100),
dob varchar(100),
active tinyInt,
PRIMARY KEY(profileID),
FOREIGN KEY (userId) REFERENCES users(userId));

CREATE table friends(
userId int NOT NULL,
friendId int NOT NULL,
PRIMARY KEY(friendID),
FOREIGN KEY (userId) REFERENCES users(userId));

CREATE table posts(
postID int NOT NULL AUTO_INCREMENT,
postHeader varchar(100),
postContent varchar(100),
postDate varchar(100),
media varchar(500),
active tinyInt,
PRIMARY KEY(postID));

CREATE table comments(
userId int NOT NULL,
postID int NOT NULL,
commentID int NOT NULL AUTO_INCREMENT,
test varchar(100),
commentDate varchar(50),
PRIMARY KEY(commentID),
FOREIGN KEY (userId) REFERENCES users(userId),
FOREIGN KEY (postID) REFERENCES posts(postId));

CREATE table rating(
postID int NOT NULL,
userID int NOT NULL,
thumbsUp int,
thumbsDown int,
PRIMARY KEY(postID, userID),
FOREIGN KEY (userId) REFERENCES users(userId),
FOREIGN KEY (postID) REFERENCES posts(postId));