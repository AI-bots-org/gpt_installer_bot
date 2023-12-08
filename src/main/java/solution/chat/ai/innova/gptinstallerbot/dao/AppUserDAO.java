package solution.chat.ai.innova.gptinstallerbot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solution.chat.ai.innova.gptinstallerbot.models.AppUser;

import java.util.Optional;

@Repository
public interface AppUserDAO extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByChatId(Long chatId);
}
