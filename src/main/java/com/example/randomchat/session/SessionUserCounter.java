package com.example.randomchat.session;


import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUserCounter implements HttpSessionListener {

    private static Logger logger = LoggerFactory.getLogger(SessionUserCounter.class);

    // 총 접속자 수
    public static int count;

    public static int getCount() {
        return count;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        count++;
        logger.error("\\n\\tSESSION CREATED : {}, TOTAL ACCESSER : {}\", session.getId(), count");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        count--;
        if(count < 0) count = 0;

        HttpSession session = event.getSession();
        logger.error("\n\tSESSION DESTROYED : {}, TOTAL ACCESSER : {}", session.getId(), count);
    }
}
