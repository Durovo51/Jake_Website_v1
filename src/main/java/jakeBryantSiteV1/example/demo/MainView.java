package jakeBryantSiteV1.example.demo;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;



@Route("")
public class MainView extends VerticalLayout {

    public MainView() {


//Background
        getStyle().set("background-color", "#4758B3");  // blue in hexadecimal

//Header
        H1 title = new H1("Jake Bryant's Site V1");
        title.getStyle().set("color", "white");

        HorizontalLayout header = new HorizontalLayout();
        header.add(title);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.getStyle().set("font-family", "Georgia, serif");




//Skyline image

        Image skyline = new Image("WisconsinSkylineV2.jpg", "Madison Skyline");
        skyline.setWidthFull();
        skyline.setHeight("600px");
        add(skyline);

 //Bio

        Div jakeBio = new Div();
        jakeBio.setWidth("1300px");
        jakeBio.setHeight("300px");
        jakeBio.getStyle().set("text-align", "center");
        jakeBio.getStyle().set("margin", "0 auto");
        jakeBio.getStyle().set("font-size", "20px");
        jakeBio.getStyle().set("color", "white");


        jakeBio.getStyle().set("font-family", "Georgia, serif");
        jakeBio.setText("As a first-year Computer Science student at the University of Wisconsin-Madison with a 4.0 GPA, I am passionate about building practical software solutions. My project experience includes developing an AI-powered movie recommendation web app using Flask and the OpenAI API , a Python web scraper with Requests and BeautifulSoup, and a Java weather application that fetches and parses real-time JSON data from a REST API. " +
                                "Currently, I am expanding my knowledge in database management and cybersecurity as a Software Developer for Insight Wisconsin and as an active CTF participant with Cybersecurity UW. These roles have allowed me to apply my skills in Python, Java, and web technologies in a collaborative, team-based environment. " +
                                "I am actively seeking a Summer 2026 software development internship where I can contribute to challenging projects and continue to learn from experienced developers.");
        add(header, jakeBio);


 //Links
        Anchor linkedIn = new Anchor("https://www.linkedin.com/in/jake-bryant-079484330/", "Visit my LinkedIn");
        linkedIn.getStyle().set("color", "white");
        linkedIn.getStyle().set("font-family", "Georgia, serif");
        linkedIn.getStyle().set("color", "white");
        linkedIn.getStyle().set("font-style", "italic");


        HorizontalLayout linksSideways = new HorizontalLayout();
        linksSideways.setWidthFull();
        linksSideways.setJustifyContentMode(JustifyContentMode.CENTER);
        linksSideways.getStyle().set("text-align", "center");
        linksSideways.getStyle().set("font-size", "20px");
        linksSideways.getStyle().set("color", "white");
        linksSideways.add(linkedIn);

 // Contact/Email Section

 // 1. The Container
        VerticalLayout contactSection = new VerticalLayout();
        contactSection.setAlignItems(Alignment.CENTER);
        contactSection.getStyle().set("margin-top", "50px"); // Add space above it
        contactSection.getStyle().set("margin-bottom", "50px"); // Add space below it
        contactSection.getStyle().setBackgroundColor("white");

// 2. Title
        H2 contactTitle = new H2("Get in Touch");
        contactTitle.getStyle().set("color", "white");
        contactTitle.getStyle().set("font-family", "Georgia, serif");

// 3. Form Fields
        TextField nameField = new TextField("Name");
        nameField.setWidth("400px");
        nameField.getStyle().set("color", "white");
        nameField.getStyle().setColor("#4758B3");
// Style the label color to be white (a bit tricky in Vaadin, but this helps visibility)
        nameField.getStyle().set("--vaadin-input-field-value-color", "white");

        EmailField emailField = new EmailField("Email");
        emailField.setWidth("400px");
        emailField.setErrorMessage("Please enter a valid email address");
        emailField.setClearButtonVisible(true);
        emailField.getStyle().set("color", "white");
        emailField.getStyle().setColor("#4758B3");

        TextArea commentField = new TextArea("Comment");
        commentField.setWidth("400px");
        commentField.setHeight("150px"); // Make it tall enough for a message
        commentField.getStyle().set("color", "white");
        commentField.getStyle().setColor("#4758B3");

// 4. Send Button
        Button sendButton = new Button("Send Message");
        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY); // Makes it blue/highlighted
        sendButton.setWidth("200px");

// 5. Button Logic (What happens when you click) and getting messages
        // REPLACES BOTH PREVIOUS LISTENERS
        sendButton.addClickListener(event -> {
            // 1. Validate
            if (emailField.isEmpty() || commentField.isEmpty()) {
                Notification.show("Please fill in your email and comment.", 3000, Notification.Position.MIDDLE);
            } else if (emailField.isInvalid()) {
                Notification.show("Please enter a valid email.", 3000, Notification.Position.MIDDLE);
            } else {
                // 2. Logic is valid: Try to save to file
                try {

                    FileWriter fileWriter = new FileWriter("/Users/jakebryant/Desktop/Website Messages/messages.txt", true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    printWriter.println("--- New Message [" + LocalDateTime.now() + "] ---");
                    printWriter.println("Name: " + nameField.getValue());
                    printWriter.println("Email: " + emailField.getValue());
                    printWriter.println("Comment: " + commentField.getValue());
                    printWriter.println("");
                    printWriter.close();

                    // 3. Show Success Message
                    Notification.show("Thanks " + nameField.getValue() + "! Your message has been saved.",
                            5000, Notification.Position.MIDDLE);

                    // 4. Clear the form (MUST happen last)
                    nameField.clear();
                    emailField.clear();
                    commentField.clear();

                } catch (IOException e) {
                    Notification.show("Error saving message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });


        contactSection.add(contactTitle, nameField, emailField, commentField, sendButton);

        add(
                header,
                skyline,
                jakeBio,
                linksSideways,
                contactSection

        );


    }
}





