package com.sonal.journalApp.scheduler;

import com.sonal.journalApp.entity.JournalEntry;
import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.repository.UserRepositoryImpl;
import com.sonal.journalApp.service.EmailService;
import com.sonal.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0 0 ? * SUN *")
    public void fetchUsersAndSendSaMail(){
        List<User> usersForSA = userRepository.getUsersforSA();
        for(User user : usersForSA){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }

    }
}
