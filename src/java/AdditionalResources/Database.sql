-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2020 at 01:04 PM
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
  `commentDate` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(1, 8, 'First!', 'This is my first post!', '2020-02-26', NULL, 1),
(2, 11, 'Second post', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vel turpis nunc eget lorem dolor sed viverra ipsum. Dignissim convallis aenean et tortor at risus viverra adipiscing. Volutpat lacus laoreet non curabitur gravida arcu ac tortor. Vel risus commodo viverra maecenas accumsan lacus vel. Ornare suspendisse sed nisi lacus sed. Elementum curabitur vitae nunc sed velit dignissim sodales. Purus semper eget duis at tellus at. Lectus nulla at volutpat diam ut. Montes nascetur ridiculus mus mauris.', '2020-02-26', NULL, 1),
(3, 11, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vel turpis nunc eget lorem dolor sed viverra ipsum. Dignissim convallis aenean et tortor at risus viverra adipiscing. Volutpat lacus laoreet non curabitur gravida arcu ac tortor. Vel risus commodo viverra maecenas accumsan lacus vel. Ornare suspendisse sed nisi lacus sed. Elementum curabitur vitae nunc sed velit dignissim sodales. Purus semper eget duis at tellus at. Lectus nulla at volutpat diam ut. Montes nascetur ridiculus mus mauris.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vel turpis nunc eget lorem dolor sed viverra ipsum. Dignissim convallis aenean et tortor at risus viverra adipiscing. Volutpat lacus laoreet non curabitur gravida arcu ac tortor. Vel risus commodo viverra maecenas accumsan lacus vel. Ornare suspendisse sed nisi lacus sed. Elementum curabitur vitae nunc sed vel', '2020-02-23', NULL, 1),
(4, 8, 'Ipsum', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lobortis elementum nibh tellus molestie nunc. Tincidunt arcu non sodales neque sodales. Vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt. Morbi tincidunt ornare massa eget. Erat velit scelerisque in dictum non consectetur a erat. Id eu nisl nunc mi ipsum faucibus vitae. Vel pretium lectus quam id leo in vitae turpis. Accumsan lacus vel facilisis volutpat. Posuere lorem ipsum dolor sit amet consectetur adipiscing. Commodo viverra maecenas accumsan lacus vel facilisis volutpat. Erat pellentesque adipiscing commodo elit. Donec massa sapien faucibus et molestie ac. Ultrices eros in cursus turpis massa tincidunt dui ut ornare. Gravida neque convallis a cras semper. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue. Vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt.', '2020-02-29', NULL, 1);

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
(8, 8, NULL, NULL, 1, NULL, NULL, 1),
(11, 9, NULL, NULL, 1, NULL, NULL, 1),
(17, 15, NULL, NULL, 1, NULL, NULL, 1);

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
(8, 'johnjoe2', 'john@gmail.com', '$2a$13$byiQQWn4346ujXfV0WbyAOtW.hjkTTlye85S0qvl.JDLeYercKxAq'),
(11, 'johnjoe23', 'john23@gmail.com', '$2a$13$CM5jAcXx1/zOKksgSMdB6.vx7Yftrhw9lEAUsUrFuZQFpflBUo.o.'),
(17, 'qwerty', 'johnqwe3@gmail.com', '$2a$13$/qgyC90xhOJW1XZd8e3J/us/70DAPMkfUYCEajXPXZPiYXRoivZ1W');

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
  MODIFY `commentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `postID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `profileId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
