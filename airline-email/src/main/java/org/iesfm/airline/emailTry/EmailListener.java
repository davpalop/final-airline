//package org.iesfm.airline.emailTry;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//public class EmailListener {
//    @Value("${spring.mail.host}")  String host;
//
//    private static void sendWithGmail(String to, String title, String body) {
//        String from = "iesfmpruebas@gmail.com";
//
//        Properties props = System.getProperties();
//
//
//
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.user", from);
//        props.put("mail.smtp.password", )
//
//    }
//    @Bean
//    public JavaMailSender getJavaMailSender(
//            @Value("${spring.mail.host}") String mailhost,
//            @Value("${spring.mail.port}") int mailPort,
//            @Value("${spring.mail.username}") String username,
//            @Value("${spring.mail.password}") String password) {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(mailhost);
//        mailSender.setPort(mailPort);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "true");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }
//
//}
