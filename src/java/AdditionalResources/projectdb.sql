-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2020 at 06:19 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

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
(53, 91, 1, 'Andrews comment on post!!!', '2020-05-10', 1),
(53, 93, 2, 'Andrews comment on franks post', '2020-05-10', 1),
(55, 93, 3, 'Nice post', '2020-05-11', 1),
(53, 92, 4, 'Im commenting on my post!!!', '2020-05-11', 1),
(53, 94, 5, 'Comment', '2020-05-11', 1),
(53, 95, 6, '[deleted]', '2020-05-11', 0),
(54, 92, 7, '[deleted]', '2020-05-11', 0);

-- --------------------------------------------------------

--
-- Table structure for table `flaggedcomment`
--

CREATE TABLE `flaggedcomment` (
  `commentID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `flagComment` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flaggedcomment`
--

INSERT INTO `flaggedcomment` (`commentID`, `userID`, `flagComment`) VALUES
(4, 53, 1),
(4, 56, 1);

-- --------------------------------------------------------

--
-- Table structure for table `flaggedpost`
--

CREATE TABLE `flaggedpost` (
  `postID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `flagPost` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flaggedpost`
--

INSERT INTO `flaggedpost` (`postID`, `userID`, `flagPost`) VALUES
(92, 53, 1),
(92, 54, 1),
(92, 56, 1);

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
  `active` tinyint(4) DEFAULT 1,
  `flagged` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`postID`, `userId`, `postHeader`, `postContent`, `postDate`, `media`, `active`, `flagged`) VALUES
(91, 53, '[deleted]', '[deleted]', '2020-05-10', '1', 0, 0),
(92, 53, 'This is andrews 2nd Post!!!', '2nd Posts content', '2020-05-10', '1', 1, 0),
(93, 54, 'Franks first Post', 'Franks post content', '2020-05-10', '1', 1, 0),
(94, 53, 'Test post by andrew', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '2020-05-11', '1', 1, 0),
(95, 56, 'Simons Post', 'Simons Post content', '2020-05-11', '1', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `postID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `selectedRating` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`postID`, `userID`, `selectedRating`) VALUES
(91, 53, -1),
(91, 54, 1),
(92, 54, 1),
(93, 53, 1),
(93, 54, 1),
(93, 55, 1),
(94, 53, 1);

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
(53, 1, 'Andrew', 'Fox', 2, 'Ireland', '1990-01-01', 1),
(54, 2, 'Frank', 'Fox', 1, 'Louth', '1950-01-10', 1),
(55, 3, NULL, NULL, 1, NULL, NULL, 1),
(56, 4, NULL, NULL, 1, NULL, NULL, 1);

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
(53, 'Andrew ', 'Andrew@test.com', '$2a$13$plbnBLKM6oHa9sK8nbPOkOhDEltt0mfTCx8I.b4/RCGeHKVRehmNq'),
(54, 'Frank', 'frank@test.com', '$2a$13$yDAWsTfeSurBcY0bgbIGHeuYRipK5yLn5XlzjiVttdH1.1JfsaV.u'),
(55, 'UserTest', 'user@email.com', '$2a$13$J7xEX6KMKZlFagja/oyKVeKDoerX0eUNhng6hvwM/HXIOaFO09LXK'),
(56, 'Simon', 'simon@test.com', '$2a$13$HEX3RZhL9L35QaeGShXKFu1sfNqO.UaKfrP5uyzVenkos2w5lPc/m');

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
-- Indexes for table `flaggedcomment`
--
ALTER TABLE `flaggedcomment`
  ADD PRIMARY KEY (`commentID`,`userID`),
  ADD UNIQUE KEY `unique_index` (`commentID`,`userID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `flaggedpost`
--
ALTER TABLE `flaggedpost`
  ADD PRIMARY KEY (`postID`,`userID`),
  ADD UNIQUE KEY `unique_index` (`postID`,`userID`),
  ADD KEY `userID` (`userID`);

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
  ADD UNIQUE KEY `unique_index` (`postID`,`userID`),
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
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `commentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `postID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `profileId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

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
-- Constraints for table `flaggedcomment`
--
ALTER TABLE `flaggedcomment`
  ADD CONSTRAINT `flaggedC_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `flaggedC_ibfk_2` FOREIGN KEY (`commentID`) REFERENCES `comments` (`commentID`);

--
-- Constraints for table `flaggedpost`
--
ALTER TABLE `flaggedpost`
  ADD CONSTRAINT `flaggedP_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `flaggedP_ibfk_2` FOREIGN KEY (`postID`) REFERENCES `posts` (`postID`);

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
