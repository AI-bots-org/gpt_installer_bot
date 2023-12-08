package solution.chat.ai.innova.gptinstallerbot.service.entity;

import solution.chat.ai.innova.gptinstallerbot.models.AppUser;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> findByChatId(Long chatId);

    AppUser save(AppUser appUser);
}
