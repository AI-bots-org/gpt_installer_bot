package solution.chat.ai.innova.gptinstallerbot.handler.impl;


import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import solution.chat.ai.innova.gptinstallerbot.handler.MenuHandler;

import static solution.chat.ai.innova.gptinstallerbot.enums.ServiceCommands.INSTALLATION;

@RequiredArgsConstructor
@Component
public class StartHandler implements MenuHandler {
    private static final String INSTALLATION_COMMAND = "Як мені встановити ваш бот?";

    @Override
    public SendMessage getMenu(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = """
                Доброго дня, ми раді, що ви зацікавились новим надпотужним ботом на основі штучного інтелекту, та можемо запропонувати для вас встановлення у ваш телеграм чат. \uD83E\uDD16\uD83D\uDCAC
                Переваги приватного бота:
                • Ви маєте надпотужний у світі штучний інтелект в своєму Telegram 24/7 \uD83D\uDD25.
                • Повна приватність (чат належить вам і ви додаєте учасників та керуєте чатом самостійно) \uD83D\uDEE1\uFE0F.
                • Ви можете бачити, про що спілкуються ваші обрані користувачі у чаті з штучним інтелектом \uD83D\uDC41\uFE0F\u200D\uD83D\uDDE8\uFE0F.
                • Ви можете додати його до 5 чатів, в які забажаєте \uD83C\uDF10.
                Особливість: це ChatGPT в Telegram з вашими працівниками, підключаємо до 5 чатів, в які ви самостійно можете додавати або видаляти учасників.
                Що нового є в боті:
                • Найпотужніша версія, яка існує ⚡.
                • База знань ChatGPT про все, що знає людство та все, що відбувалось до 2023 року \uD83C\uDF0D.
                • Генерація текстових відповідей на будь-які запити, використовуючи останню технологію ChatGPT4 Turbo \uD83D\uDCAC.
                • Спілкування голосовими повідомленнями з штучним інтелектом та отримання відповідей теж голосом (підтримка до 80 мов) \uD83D\uDDE3\uFE0F.
                • Генерація картинок від нейромережі за допомогою текстового запиту будь-якою мовою \uD83C\uDFA8.
                • Аналіз файлів (завантаження файлів в систему та отримання інформації з файлу, його аналізу, тощо) \uD83D\uDCCA.
                • Підтримка та оновлення всіх останніх функцій в бот по замовчуванню \uD83D\uDD04.
                Одноразова вартість встановлення: 24 USD.
                Щомісячна підписка: 14 USD
                В подарунок надаємо маркетингову енциклопедію по роботі з ChatGPT.
                Якщо цікавить. Все, що потрібно - захотіти. \uD83C\uDF81
                Підключити легко:
                •   Оплата тут: @romko164
                •   Інстуркцію про встановлення ви отримаєте одразу після оплати
                
                Виникли питання? Ми готові вам допомогти – просто пишіть нам у чат \uD83D\uDE0A
                """;

        SendMessage message = new SendMessage(chatId, text);

        ReplyKeyboardMarkup keyboardMarkup = getStartReplyKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    @NotNull
    public static ReplyKeyboardMarkup getStartReplyKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Як мені встановити ваш бот?"));
        keyboard.add(firstRow);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    @Override
    public boolean isApplicable(String message) {
        return INSTALLATION.toString().equals(message) || INSTALLATION_COMMAND.equals(message);
    }

    @Override
    public SendMessage getMessage(Update update) {
        return getMenu(update);
    }
}

