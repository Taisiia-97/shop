package com.taisiia.shop.service;

import java.util.Map;

public interface MailService {

    void sendMail(Map<String, Object> variables, String receiver,String templateName);

}
