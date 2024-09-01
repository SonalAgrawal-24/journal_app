package com.sonal.journalApp.scheduler;

import com.sonal.journalApp.entity.JournalEntry;
import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.enums.Sentiment;
import com.sonal.journalApp.repository.UserRepositoryImpl;
import com.sonal.journalApp.service.EmailService;
import com.sonal.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> usersForSA = userRepository.getUsersforSA();
        for(User user : usersForSA){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment!=null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendMail(user.getEmail(), "Sentiment for last 7 days ", mostFrequentSentiment.toString());
            }
        }

    }
}
