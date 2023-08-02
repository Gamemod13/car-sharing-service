package mate.academy.car.sharing.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import mate.academy.car.sharing.exception.TelegramNotificationException;
import mate.academy.car.sharing.service.TelegramNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScope;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Getter
@Service
@PropertySource("classpath:application.properties")
public class TelegramNotificationServiceImpl extends TelegramNotificationService {
    private static final String STARTED = "Started";
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.chat.id}")
    private String adminChatId;

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
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            if (messageText.equals("/start")) {
                sendMessage(chatId, STARTED);
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

    private void setCommandScopes(){
        List<BotCommand> commands = List.of(
                new BotCommand("",""),
                new BotCommand("",""),
                new BotCommand("",""),
                new BotCommand("","")
        );
    }
}
