package com.project.sneakermarket.common.config;

import com.project.sneakermarket.calendar.entity.CalendarEvent;
import com.project.sneakermarket.calendar.repository.CalendarEventRepository;
import com.project.sneakermarket.member.entity.Member;
import com.project.sneakermarket.member.repository.MemberRepository;
import com.project.sneakermarket.notification.entity.Notification;
import com.project.sneakermarket.notification.repository.NotificationRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/** Seeds fictional, repeatable portfolio data in the in-memory database. */
@Component
@ConditionalOnProperty(name = "app.demo-mode", havingValue = "true")
public class DemoDataInitializer implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final CalendarEventRepository eventRepository;
    private final NotificationRepository notificationRepository;

    public DemoDataInitializer(
            MemberRepository memberRepository,
            CalendarEventRepository eventRepository,
            NotificationRepository notificationRepository
    ) {
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (memberRepository.count() == 0) {
            memberRepository.save(Member.builder()
                    .email("reviewer@example.com")
                    .role("ADMIN")
                    .build());
        }

        if (eventRepository.count() == 0) {
            LocalDateTime base = LocalDateTime.now()
                    .plusDays(2)
                    .truncatedTo(ChronoUnit.HOURS)
                    .withHour(14);

            eventRepository.save(event(
                    "RELEASE", "秋季限定球鞋發售", "新品發售與尺寸試穿活動",
                    "松山文創園區", "台北市信義區光復南路133號",
                    "25.043750", "121.560640", base, 2
            ));
            eventRepository.save(event(
                    "EVENT", "球鞋文化交流日", "收藏家交流、鞋款故事與保養分享",
                    "華山1914文化創意產業園區", "台北市中正區八德路一段1號",
                    "25.044130", "121.529400", base.plusDays(5), 3
            ));
            eventRepository.save(event(
                    "SALE", "週末會員限定優惠", "指定鞋款限時優惠，數量有限",
                    "信義門市", "台北市信義區松壽路12號",
                    "25.035780", "121.566150", base.plusDays(12), 8
            ));
        }

        if (notificationRepository.count() == 0) {
            notificationRepository.save(templateNotification(
                    "EVENT_REMINDER", "活動行前提醒",
                    "你追蹤的活動即將開始，記得預留交通時間。", false
            ));
            notificationRepository.save(templateNotification(
                    "SYSTEM", "服務公告",
                    "EventFlow 展示環境已完成例行更新。", true
            ));
            notificationRepository.save(notification(
                    "EVENT_REMINDER", "活動提醒",
                    "你追蹤的「秋季限定球鞋發售」即將開始。", false
            ));
            notificationRepository.save(notification(
                    "PRODUCT_RELEASE", "新品通知",
                    "你關注的新品資訊已更新。", true
            ));
        }
    }

    private CalendarEvent event(
            String type,
            String title,
            String description,
            String venue,
            String address,
            String latitude,
            String longitude,
            LocalDateTime start,
            int durationHours
    ) {
        CalendarEvent event = new CalendarEvent();
        event.setCreatedBy(1L);
        event.setEventType(type);
        event.setTitle(title);
        event.setDescription(description);
        event.setVenueName(venue);
        event.setAddress(address);
        event.setLatitude(new BigDecimal(latitude));
        event.setLongitude(new BigDecimal(longitude));
        event.setStartAt(start);
        event.setEndAt(start.plusHours(durationHours));
        event.setSourceType("PORTFOLIO_DEMO");
        event.setStatus("ACTIVE");
        return event;
    }

    private Notification notification(
            String type,
            String title,
            String content,
            boolean read
    ) {
        Notification notification = new Notification();
        notification.setMemberId(1L);
        notification.setNotificationType(type);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setTargetType("CALENDAR_EVENT");
        notification.setTargetUrl("/calendar");
        notification.setIsRead(read);
        notification.setIsSent(true);
        notification.setInAppVisible(true);
        notification.setSentAt(LocalDateTime.now());
        if (read) {
            notification.setReadAt(LocalDateTime.now());
        }
        return notification;
    }

    private Notification templateNotification(
            String type,
            String title,
            String content,
            boolean sent
    ) {
        Notification notification = new Notification();
        notification.setMemberId(null);
        notification.setNotificationType(type);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setTargetType("CALENDAR_EVENT");
        notification.setTargetUrl("/calendar");
        notification.setIsRead(false);
        notification.setIsSent(sent);
        notification.setInAppVisible(true);
        if (sent) {
            notification.setSentAt(LocalDateTime.now());
        }
        return notification;
    }
}
