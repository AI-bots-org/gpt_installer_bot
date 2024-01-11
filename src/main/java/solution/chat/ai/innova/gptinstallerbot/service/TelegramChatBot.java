package solution.chat.ai.innova.gptinstallerbot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import solution.chat.ai.innova.gptinstallerbot.exception.DataNotFoundException;
import solution.chat.ai.innova.gptinstallerbot.exception.NoResponseException;
import solution.chat.ai.innova.gptinstallerbot.handler.impl.*;
import solution.chat.ai.innova.gptinstallerbot.models.AppUser;
import solution.chat.ai.innova.gptinstallerbot.service.entity.AppUserService;

import java.util.ArrayList;
import java.util.List;


public class TelegramChatBot extends TelegramLongPollingBot {

    private final String botUserName;

    @Autowired
    private MenuStrategy menuStrategy;
    @Autowired
    private OpenAIService openAIService;
    @Autowired
    private AppUserService appUserService;

    public TelegramChatBot(String botToken, String botUserName) {
        super(botToken);
        this.botUserName = botUserName;
        initializeMenu();
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            processTextMessage(update);
        }  else {
            setUnsupportedMessageTypeView(update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        String text = "Непідтримуваний тип повідомлення. Виберіть одну з доступних команд у меню";
        SendMessage sendMessage = generateSandMessageWithText(update, text);
        sendAnswerMessage(sendMessage);
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Виникла помилка. Спробуйте, будь-ласка, пізніше.");
                sendAnswerMessage(sendMessage);
            }
        }
    }

    private SendMessage generateSandMessageWithText(Update update, String text) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        return sendMessage;
    }

    private void processTextMessage(Update update) {
        saveUserIfNotExist(update);
        try {
            SendMessage sendMessage = menuStrategy.getHandler(update.getMessage().getText()).getMenu(update);
            if ((sendMessage.getText().length() > 4009) && (sendMessage.getText().length() < 8018)) {
                String part1 = sendMessage.getText().substring(0, 4009);
                String part2 = sendMessage.getText().substring(4009);

                sendMessage.setText(part1);
                sendAnswerMessage(sendMessage);

                sendMessage.setText(part2);
                sendAnswerMessage(sendMessage);
            }
            sendAnswerMessage(sendMessage);
        } catch (DataNotFoundException e) {
            try {
                String text = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                String response = openAIService.getResponse(text, chatId);

                SendMessage sendMessage = new SendMessage(chatId.toString(), response);
                sendAnswerMessage(sendMessage);
            } catch (NoResponseException exception) {
                Long chatId = exception.getChatId();
                String response = e.getMessage();

                SendMessage sendMessage = new SendMessage(chatId.toString(), response);
                sendAnswerMessage(sendMessage);
            }
        }
    }

    private void saveUserIfNotExist(Update update) {
        AppUser appUser = appUserService.findByChatId(update.getMessage().getChatId())
                .orElse(buildAppUserByUpdate(update));
        appUserService.save(appUser);
    }

    private AppUser buildAppUserByUpdate(Update update) {
        User user = update.getMessage().getFrom();
        return AppUser.builder()
                .chatId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUserName())
                .build();
    }

    private void initializeMenu() {
        List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/start", "Як мені встановити ваш бот?"));
        botCommands.add(new BotCommand("/guide", "Як бот може мені допомогти?"));
        botCommands.add(new BotCommand("/benefits", "Переваги бота"));
        botCommands.add(new BotCommand("/faq", "Найпоширеніші питання"));
        try {
            this.execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't execute menu", e);
        }
    }
}