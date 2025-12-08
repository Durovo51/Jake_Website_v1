My personal website.

This project is a personal portfolio website built entirely in Java using the Vaadin Flow framework. It serves as a digital resume and a demonstration of full-stack Java capabilities, eliminating the need for separate HTML/CSS files by handling UI logic server-side.

Key Features
Pure Java UI: Utilizes Vaadin components (VerticalLayout, HorizontalLayout, H1, Div) to construct the frontend programmatically.

Custom Styling: Implements CSS styling directly within Java for a cohesive blue and white theme (#4758B3), utilizing custom fonts and responsive layouts.

Interactive Contact Form:

Includes validation logic for email formatting and empty fields.

File I/O Persistence: Upon submission, user messages (Name, Email, Comment) are captured and appended to a local log file (messages.txt) using java.io.FileWriter.

Provides real-time user feedback via Vaadin Notification components.

Content: Displays a dynamic biography, a graphical header (Madison Skyline), and external navigation links.

Technologies Used

Language: Java

Framework: Vaadin Flow (Spring Boot compatible)

Utilities: Java IO, Java Time (LocalDateTime)
