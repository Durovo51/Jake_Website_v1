package jakeBryantSiteV1.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final EmailService emailService;

    public MainView(EmailService emailService) {
        this.emailService = emailService;


//Background
        getStyle().set("background-color", "#4758B3");  // blue in hexadecimal

//Header
        H1 title = new H1("Jake Bryant");
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

 //Bio

        Div jakeBio = new Div();
        jakeBio.setWidth("90%");
        jakeBio.setMaxWidth("800px");
        jakeBio.getStyle().set("text-align", "center");
        jakeBio.getStyle().set("margin", "0 auto");
        jakeBio.getStyle().set("font-size", "20px");
        jakeBio.getStyle().set("color", "white");
        jakeBio.getStyle().set("font-family", "Georgia, serif");
        jakeBio.setText("As a first-year Computer Science student at the University of Wisconsin-Madison with a 4.0 GPA, I am passionate about building practical software solutions. My project experience includes developing an AI-powered movie recommendation web app using Flask and the OpenAI API , a Python web scraper with Requests and BeautifulSoup, and a Java weather application that fetches and parses real-time JSON data from a REST API. " +
                                "Currently, I am expanding my knowledge in database management and cybersecurity as a Software Developer for Insight Wisconsin and as an active CTF participant with Cybersecurity UW. These roles have allowed me to apply my skills in Python, Java, and web technologies in a collaborative, team-based environment. " +
                                "I am actively seeking a Summer 2026 software development internship where I can contribute to challenging projects and continue to learn from experienced developers.");

 //Links
        Anchor linkedIn = new Anchor("https://www.linkedin.com/in/jake-bryant-079484330/", "LinkedIn");
        linkedIn.getStyle().set("color", "white");
        linkedIn.getStyle().set("font-family", "Georgia, serif");
        linkedIn.getStyle().set("color", "white");
        linkedIn.getStyle().set("font-style", "italic");
        linkedIn.setTarget("_blank");

        Anchor gitHub = new Anchor("https://github.com/Durovo51", "GitHub");
        gitHub.getStyle().set("color", "white");
        gitHub.getStyle().set("font-family", "Georgia, serif");
        gitHub.getStyle().set("color", "white");
        gitHub.getStyle().set("font-style", "italic");
        gitHub.setTarget("_blank");

        HorizontalLayout linksSideways = new HorizontalLayout();
        linksSideways.setWidthFull();
        linksSideways.setJustifyContentMode(JustifyContentMode.CENTER);
        linksSideways.getStyle().set("text-align", "center");
        linksSideways.getStyle().set("font-size", "20px");
        linksSideways.getStyle().set("color", "white");
        linksSideways.add(linkedIn, gitHub);

 // Contact/Email Section

        VerticalLayout contactSection = new VerticalLayout();
        contactSection.setAlignItems(Alignment.CENTER);
        contactSection.getStyle().set("margin-top", "50px");
        contactSection.getStyle().set("margin-bottom", "50px");
        contactSection.getStyle().setBackgroundColor("white");

        H2 contactTitle = new H2("Get in Touch");
        contactTitle.getStyle().set("color", "black");
        contactTitle.getStyle().set("font-family", "Georgia, serif");

        TextField nameField = new TextField("Name");
        nameField.setWidth("400px");

        EmailField emailField = new EmailField("Email");
        emailField.setWidth("400px");
        emailField.setErrorMessage("Please enter a valid email address");

        TextArea commentField = new TextArea("Comment");
        commentField.setWidth("400px");
        commentField.setHeight("150px");

        Button sendButton = new Button("Send Message");
        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sendButton.setWidth("200px");
        sendButton.getStyle().set("background-color", "#4758B3");

        // 4. The Button Logic
        sendButton.addClickListener(event -> {

            if (emailField.isEmpty() || commentField.isEmpty()) {
                Notification.show("Please fill in your email and comment.", 3000, Notification.Position.MIDDLE);
            } else if (emailField.isInvalid()) {
                Notification.show("Please enter a valid email.", 3000, Notification.Position.MIDDLE);
            } else {
                try {

                    // B. SEND THE EMAIL (The new part)
                    emailService.sendEmail(emailField.getValue(), "Message from your site", commentField.getValue(), nameField.getValue());

                    // C. Success Message
                    Notification.show("Thanks " + nameField.getValue().replace(" ", "")+ "! Email sent.", 5000, Notification.Position.MIDDLE);

                    // D. Clear Form
                    nameField.clear();
                    emailField.clear();
                    commentField.clear();

                } catch (Exception e) {
                    Notification.show("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        contactSection.add(contactTitle, nameField, emailField, commentField, sendButton);

        // Add everything to layout in the EXACT order you want them to appear
        add(header, skyline, jakeBio, linksSideways, contactSection);

    }
}