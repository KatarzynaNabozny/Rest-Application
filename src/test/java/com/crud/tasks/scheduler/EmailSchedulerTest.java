package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.security.auth.Subject;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    @InjectMocks
    EmailScheduler emailScheduler;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    TaskRepository taskRepository;
    @Mock
    AdminConfig adminConfig;

    @Test
    void sendInformationEmail() {

        //Given
        emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
        given(taskRepository.count()).willReturn(1l);
        Mail mail = Mail.builder()
                .mailTo(null)
                .subject("Tasks: Once a day email")
                .message("Currently in database you got: 1 task")
                .toCc(null)
                .build();

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService,times(1)).send(mail);
    }
}