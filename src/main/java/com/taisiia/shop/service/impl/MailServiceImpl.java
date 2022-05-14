package com.taisiia.shop.service.impl;

import com.taisiia.shop.service.MailService;
import com.taisiia.shop.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateService templateService;
    private final ITemplateEngine iTemplateEngine;

    @Override
    public void sendMail(Map<String, Object> variables, String receiver, String templateName) {
        var template = templateService.findByName(templateName);
        var body = iTemplateEngine.process(template.getBody(), new Context(Locale.getDefault(), variables));

        javaMailSender.send(mimeMessage -> {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject(template.getSubject());
            mimeMessageHelper.setText(body, true);
        });

    }
}
