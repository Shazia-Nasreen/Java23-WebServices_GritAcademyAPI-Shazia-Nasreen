<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
    <h1>GritAcademy API</h1>

    <div class="navbar">
        <button onclick="window.location.href='/api/students/all'">Show All Students</button>
        <button onclick="window.location.href='/api/courses/all'">Show All Courses</button>
        <button onclick="window.location.href='/api/students/allAssociations'">Show All Associations</button>
    </div>

    <div class="sub-navbar">
        <form id="searchStudentsByIdForm" action="/api/students/searchCoursesByStudentId" method="GET">
            <label for="studentId">Search Course by Student ID:</label>
            <input type="number" id="studentId" name="id" placeholder="Enter student ID" required>
            <button type="submit">Search</button>
        </form>

        <form id="searchStudentsFNameForm" action="/api/students/by-fname" method="GET">
            <label for="fName">Search Students by First Name:</label>
            <input type="text" id="fName" name="fName" placeholder="Enter student FName" required>
            <button type="submit">Search</button>
        </form>

        <form id="searchStudentsLNameForm" action="/api/students/by-lname" method="GET">
            <label for="lName">Search Students by Last Name:</label>
            <input type="text" id="lName" name="lName" placeholder="Enter student LName" required>
            <button type="submit">Search</button>
        </form>

        <form id="searchStudentsTownForm" action="/api/students/by-town" method="GET">
            <label for="town">Search Students by Town:</label>
            <input type="text" id="town" name="town" placeholder="Enter Town" required>
            <button type="submit">Search</button>
        </form>

        <h3>Remove Student by ID</h3>
        <form action="/api/students/deleteStudentById" method="POST">
            <input type="hidden" name="_method" value="DELETE">
            <label for="studentId">Student ID:</label>
            <input type="number" id="studentId" name="id" placeholder="Enter student ID" required>
            <button type="submit">Remove</button>
        </form>


        <form id="searchCoursesNameKeywordForm" action="/api/courses/by-name-keyword" method="GET">
            <label for="courseKeyword">Search Courses by Name Keyword:</label>
            <input type="text" id="courseKeyword" name="keyword" placeholder="Enter Course name Keyword" required>
            <button type="submit">Search</button>
        </form>

        <form id="searchCoursesDescKeywordForm" action="/api/courses/by-description-keyword" method="GET">
            <label for="courseDescKeyword">Search Courses by Description Keyword:</label>
            <input type="text" id="courseDescKeyword" name="keyword" placeholder="Enter Course Description Keyword" required>
            <button type="submit">Search</button>
        </form>

        <!-- Add Student Form -->
        <h3>Add Student</h3>
        <form id="addStudentForm" action="/api/students" method="POST">
            <div class="form-group">
                <label for="fName">First Name:</label>
                <input type="text" id="fName" name="fName" placeholder="Enter First Name" required>
            </div>
            <div class="form-group">
                <label for="lName">Last Name:</label>
                <input type="text" id="lName" name="lName" placeholder="Enter Last Name" required>
            </div>
            <div class="form-group">
                <label for="town">Town:</label>
                <input type="text" id="town" name="town" placeholder="Enter Town" required>
            </div>
            <button class="btn" type="submit">Add Student</button>
        </form>
        <h3>Add Student/Course Relationship</h3>
        <form action="/api/students/addStudentToCourse" method="POST">
            <label for="studentId">Student ID:</label>
            <input type="number" id="studentId" name="studentId" placeholder="Enter student ID" required>
            <label for="courseId">Course ID:</label>
            <input type="number" id="courseId" name="courseId" placeholder="Enter course ID" required>
            <button type="submit">Add Relationship</button>
        </form>
        <h3>Remove Student/Course Relationship</h3>
         <form action="/api/students/removeStudentFromCourse" method="POST">
            <input type="hidden" name="_method" value="DELETE">
            <label for="studentId">Student ID:</label>
            <input type="number" id="studentId" name="studentId" placeholder="Enter student ID" required>
            <label for="courseId">Course ID:</label>
            <input type="number" id="courseId" name="courseId" placeholder="Enter course ID" required>
            <button type="submit">Remove Relationship</button>
        </form>
        <h3>Search Students by Course ID</h3>
            <form action="/api/courses/searchStudentsByCourseId" method="get">
                <label for="courseId">Enter Course ID:</label>
                <input type="number" id="courseId" name="id" placeholder="Enter course ID" required>
                <button type="submit">Search</button>
            </form>
            <!-- Form for adding a course -->
            <h3>Add Course</h3>
            <form action="/api/courses" method="post">
                <label for="name">Course Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter course name" required><br>
                <label for="description">Course Description:</label>
                <textarea id="description" name="description"></textarea><br>
                <button type="submit">Add Course</button>
            </form>
            <!-- Form for deleting a course by ID -->
            <h3>Delete Course by ID</h3>
            <form action="/api/courses/deleteCourseById" method="post">
                <input type="hidden" name="_method" value="DELETE">
                <label for="courseId">Course ID:</label>
                <input type="number" id="courseId" name="id" placeholder="Enter course ID" required><br>
                <button type="submit">Delete Course</button>
            </form>
        </div>
</div>
</body>
</html>
