package solution.chat.ai.innova.gptinstallerbot.service.entity.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solution.chat.ai.innova.gptinstallerbot.dao.AppUserDAO;
import solution.chat.ai.innova.gptinstallerbot.models.AppUser;
import solution.chat.ai.innova.gptinstallerbot.service.entity.AppUserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserDAO appUserDAO;

    @Override
    public Optional<AppUser> findByChatId(Long chatId) {
        return appUserDAO.findByChatId(chatId);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserDAO.save(appUser);
    }
}
