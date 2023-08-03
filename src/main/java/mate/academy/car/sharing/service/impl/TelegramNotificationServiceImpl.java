package mate.academy.car.sharing.service.impl;

import javax.annotation.PostConstruct;
import mate.academy.car.sharing.exception.TelegramNotificationException;
import mate.academy.car.sharing.service.TelegramNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
@PropertySource("classpath:application.properties")
public class TelegramNotificationServiceImpl extends TelegramNotificationService {
    private static final String ADMIN_CHAT_ID = "-1001600483081";
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new TelegramNotificationException("Can't send message={" + message + "} to "
                    + "chat, with id={" + chatId + "}", e);
        }
    }

    @Override
    public void sendMessageToAdminChat(String message) {
        sendMessage(ADMIN_CHAT_ID, message);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            if (messageText.equals("/start")) {
                System.out.println(chatId);
                sendMessage(chatId, "Hi, " + update.getMessage().getFrom().getFirstName()
                        + ", bot "
                        + "has "
                        + "been started");
            }
        }
    }

    @PostConstruct
    @Override
    public void registerBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            throw new TelegramNotificationException("Can't register bot");
        }
    }
}
