package com.academy.email.constant;

public interface EmailConstant {

    String APP_EMAIL_USERNAME = "APP_EMAIL_USERNAME";
    String APP_EMAIL_PASSWORD = "APP_EMAIL_PASSWORD";
    String EMAIL_HOST = "smtp.gmail.com";
    Integer EMAIL_PORT = 587;
    String TRUE = "true";
    String EMAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    String EMAIL_SMTP_AUTH = "mail.smtp.auth";
    String EMAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    String EMAIL_DEBUG = "mail.debug";
    String UTF_8 = "UTF-8";
    String HTML = "html";
    String COMMA = ",";
    String REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String EMAIL_PROTOCOL = "smtp";

    String VERIFY_EMAIL_TEMPLATE = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "  <meta charset=\"UTF-8\">" +
            "  <title>Verify Your Email</title>" +
            "  <style>" +
            "    body {" +
            "      font-family: Arial, sans-serif;" +
            "      background-color: #f4f4f4;" +
            "      padding: 20px;" +
            "      margin: 0;" +
            "    }" +
            "    .container {" +
            "      max-width: 600px;" +
            "      margin: 0 auto;" +
            "      background: white;" +
            "      padding: 30px;" +
            "      border-radius: 6px;" +
            "      box-shadow: 0 0 5px rgba(0,0,0,0.1);" +
            "    }" +
            "    h2 {" +
            "      color: #333;" +
            "    }" +
            "    p {" +
            "      font-size: 15px;" +
            "      color: #555;" +
            "    }" +
            "    .btn {" +
            "      display: inline-block;" +
            "      padding: 12px 24px;" +
            "      background-color: #28a745;" +
            "      color: #ffffff;" +
            "      text-decoration: none;" +
            "      border-radius: 4px;" +
            "      margin-top: 20px;" +
            "      font-weight: bold;" +
            "    }" +
            "    .footer {" +
            "      margin-top: 40px;" +
            "      font-size: 12px;" +
            "      color: #aaa;" +
            "      text-align: center;" +
            "    }" +
            "  </style>" +
            "</head>" +
            "<body>" +
            "  <div class=\"container\">" +
            "    <h2>Verify Your Email</h2>" +
            "    <p>Hi <strong>${username}</strong>,</p>" +
            "    <p>Welcome to Learning Academy</p>"+
            "    <p>To verify your email address, please click the button below:</p>" +
            "    <a class=\"btn\" href=\"{base_url}?token=${token}&source=email\" target=\"_blank\">" +
            "      Verify Email" +
            "    </a>" +
            "\n" +
            "    <p>If you didn’t sign up, just ignore this message.</p>" +
            "\n" +
            "    <div class=\"footer\">" +
            "      &copy; 2025 Learning Academy. All rights reserved.\n" +
            "    </div>" +
            "  </div>" +
            "</body>" +
            "</html>";
}
