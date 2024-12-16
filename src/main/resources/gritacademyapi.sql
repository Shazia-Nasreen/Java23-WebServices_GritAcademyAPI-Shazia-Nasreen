-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 05:17 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gritacademyapi`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `name`, `description`) VALUES
(1, 'Introduction to Programming', 'Learn the basics of programming using Python.'),
(2, 'Advanced Java', 'Deep dive into advanced concepts of Java programming.'),
(3, 'Web Development with HTML, CSS, and JavaScript', 'Master the essential web technologies: HTML, CSS, and JavaScript.'),
(4, 'Database Management Systems', 'Learn how to design and manage relational databases using SQL.'),
(5, 'Data Science with Python', 'A hands-on course to learn data analysis and visualization using Python.'),
(6, 'Machine Learning Fundamentals', 'Introduction to machine learning algorithms and their applications.'),
(7, 'Cybersecurity Basics', 'Learn the fundamentals of network security and ethical hacking.'),
(8, 'Mobile App Development with Flutter', 'Build cross-platform mobile apps with Flutter framework.'),
(9, 'Cloud Computing with AWS', 'Learn cloud services, architecture, and management with AWS.'),
(10, 'Software Engineering Principles', 'Understand software development methodologies and best practices.'),
(11, 'Digital Marketing Strategies', 'Learn digital marketing tools and strategies for business growth.'),
(12, 'Artificial Intelligence in Business', 'Study the integration of AI technologies in business processes.'),
(13, 'Game Development with Unity', 'Create 2D and 3D games using Unity game development engine.'),
(14, 'UX/UI Design Fundamentals', 'Learn the principles of user experience and user interface design.'),
(15, 'Blockchain and Cryptocurrency', 'Understand blockchain technology and its applications in cryptocurrency.');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` bigint(20) NOT NULL,
  `fName` varchar(255) NOT NULL,
  `lName` varchar(255) NOT NULL,
  `town` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `fName`, `lName`, `town`) VALUES
(1, 'John', 'Nils', 'New York'),
(2, 'Jordan', 'Smith', 'Los Angeles'),
(3, 'Michael', 'Johnson', 'Chicago'),
(4, 'Emily', 'Williams', 'Houston'),
(5, 'Daniel', 'Brown', 'Phoenix'),
(6, 'Sarah', 'Davis', 'San Antonio'),
(7, 'David', 'Miller', 'San Diego'),
(8, 'Sophia', 'Wilson', 'Dallas'),
(9, 'James', 'Moore', 'Austin'),
(10, 'Olivia', 'Taylor', 'Jacksonville'),
(11, 'Jacob', 'Anderson', 'Fort Worth'),
(12, 'Isabella', 'Thomas', 'Columbus'),
(13, 'Mason', 'Jackson', 'Indianapolis'),
(14, 'Charlotte', 'White', 'San Francisco'),
(15, 'Amelia', 'Harris', 'Seattle'),
(16, 'Shazia', 'Nasreen', 'MalmO');

-- --------------------------------------------------------

--
-- Table structure for table `students_courses`
--

CREATE TABLE `students_courses` (
  `students_id` bigint(20) NOT NULL,
  `courses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `students_courses`
--

INSERT INTO `students_courses` (`students_id`, `courses_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students_courses`
--
ALTER TABLE `students_courses`
  ADD PRIMARY KEY (`students_id`,`courses_id`),
  ADD KEY `courses_id` (`courses_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `students_courses`
--
ALTER TABLE `students_courses`
  ADD CONSTRAINT `students_courses_ibfk_1` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `students_courses_ibfk_2` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
