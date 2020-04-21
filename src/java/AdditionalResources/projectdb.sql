-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2020 at 11:01 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `userId` int(11) NOT NULL,
  `postID` int(11) NOT NULL,
  `commentID` int(11) NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `commentDate` date DEFAULT current_timestamp(),
  `active` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`userId`, `postID`, `commentID`, `content`, `commentDate`, `active`) VALUES
(28, 5, 5, 'This is a comment!', '2020-03-06', 1),
(25, 5, 6, 'Hello world!', '2020-03-07', 1),
(25, 7, 7, 'I crave chedder', '2020-03-07', 1),
(25, 5, 8, 'Test content!', '2020-03-08', 1),
(25, 5, 12, 'I made a comment!', '2020-03-08', 1),
(25, 5, 13, 'Another comment', '2020-03-08', 1),
(25, 11, 14, 'A comment', '2020-03-08', 1),
(25, 13, 15, 'A comment', '2020-03-08', 1),
(25, 13, 16, 'This is a comment', '2020-03-08', 1),
(25, 14, 17, 'First!', '2020-03-08', 1),
(25, 14, 18, 'Secind!', '2020-03-08', 1),
(25, 14, 19, 'asldjsidjosj', '2020-03-08', 1),
(25, 15, 20, 'Comment', '2020-03-09', 1),
(25, 15, 21, 'Anotehrt comment', '2020-03-09', 1),
(25, 15, 22, 'dofhwfhwdsfh', '2020-03-09', 1),
(25, 16, 23, 'Make a post!\n', '2020-03-09', 1),
(31, 16, 24, '[deleted]', '2020-03-10', 0),
(31, 16, 25, '[deleted]', '2020-03-10', 0),
(31, 16, 26, '[deleted]', '2020-03-11', 0),
(31, 16, 27, '[deleted]', '2020-03-11', 0);

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `postID` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `postHeader` varchar(100) DEFAULT NULL,
  `postContent` varchar(1000) DEFAULT NULL,
  `postDate` date DEFAULT NULL,
  `media` varchar(500) DEFAULT NULL,
  `active` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`postID`, `userId`, `postHeader`, `postContent`, `postDate`, `media`, `active`) VALUES
(5, 25, 'First Post!', 'I made the first post!', '2020-03-06', NULL, 1),
(6, 25, 'I also made the second post!', 'This is very hard :(', '2020-03-06', NULL, 1),
(7, 27, 'I like cheese', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2020-03-06', NULL, 1),
(8, 26, 'Cool video!', 'https://www.youtube.com/watch?v=Yw6u6YkTgQ4', '2020-03-06', NULL, 1),
(9, 25, 'I posted this', 'From the form', '2020-03-07', '1', 1),
(10, 25, 'sfdsdfsd', 'sdfsdf', '2020-03-07', '1', 1),
(11, 25, 'I made another Post', 'This is getting somewhere', '2020-03-07', '1', 1),
(12, 25, 'Test Post', 'Another Test', '2020-03-08', '1', 1),
(13, 25, 'One more test!', 'HIIHIHIHIHIHIH', '2020-03-08', '1', 1),
(14, 25, 'Test post123123123123', 'Hi world!', '2020-03-08', '1', 1),
(15, 25, 'dfsdgsdgsd', 'Post', '2020-03-09', '1', 1),
(16, 25, 'wqeqwe', 'Test', '2020-03-09', '1', 1),
(17, 29, 'ewer', 'dssdfsdf', '2020-03-09', '1', 0),
(18, 31, '[deleted]', '[deleted]', '2020-03-10', '1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `postID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `selectedRating` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userprofile`
--

CREATE TABLE `userprofile` (
  `userId` int(11) NOT NULL,
  `profileId` int(11) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `userType` tinyint(4) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `dob` varchar(100) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userprofile`
--

INSERT INTO `userprofile` (`userId`, `profileId`, `fname`, `lname`, `userType`, `address`, `dob`, `active`) VALUES
(25, 23, NULL, NULL, 1, NULL, NULL, 1),
(26, 24, NULL, NULL, 1, NULL, NULL, 1),
(27, 25, NULL, NULL, 1, NULL, NULL, 1),
(28, 26, NULL, NULL, 1, NULL, NULL, 1),
(29, 27, NULL, NULL, 1, NULL, NULL, 1),
(30, 28, NULL, NULL, 1, NULL, NULL, 1),
(31, 29, NULL, NULL, 1, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userName`, `email`, `password`) VALUES
(25, 'tjohora', 'test@gmail.com', '$2a$13$yWMuwes6MM8RVLe0QYJo6uRLHnCllPuItU7A8XHfgz61OAKTjO/zu'),
(26, 'qweqwe', 'qweqwe@example.com', '$2a$13$mENFs/aEX/VKgPK0ogqjCORDo72y8w98iLv1TjSfBmFLAX16qN0rO'),
(27, 'johnjoe123', 'johnJoe@gmail.com', '$2a$13$e6jDchzhnxX412t2.u3l6eZ0aQmrLgeJ9WP7ApAeYe3EX2hMc/D6e'),
(28, 'UserTJ', 'test2@gmail.com', '$2a$13$vJQGAoCARoiV8ZOxvMCfeOGKdxqSKshksv2LW.mqS1vIcjdn7QiMq'),
(29, 'FunnyName', 'Funny@example.com', '$2a$13$HREM2WVZOZmnPJX6SWTCx.oR0MajP4v2ZVN0/snaPpIvqqJos.nUG'),
(30, 'JohnJoe111', 'Test123123@example.com', '$2a$13$Kpj7lZEUIq6YOuW6Z4iSLOi0HneUkzZLm8JVBR5IcXMb0Ord8FGt.'),
(31, 'Auser', 'test123123123@gmail.com', '$2a$13$e5VzlYxFkTEdaihhuZIJ5eAX4N6i/COlfSduWz.yCZJotxMJhITHa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`commentID`),
  ADD KEY `userId` (`userId`),
  ADD KEY `postID` (`postID`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`friendId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`postID`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`postID`,`userID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD PRIMARY KEY (`profileId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `userName` (`userName`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `commentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `postID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `profileId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`postID`) REFERENCES `posts` (`postID`);

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`postID`) REFERENCES `posts` (`postID`);

--
-- Constraints for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD CONSTRAINT `userprofile_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
