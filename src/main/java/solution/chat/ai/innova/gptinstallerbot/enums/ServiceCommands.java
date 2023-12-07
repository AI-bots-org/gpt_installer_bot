package solution.chat.ai.innova.gptinstallerbot.enums;


public enum ServiceCommands {
    INSTALLATION("/start"),
    GUIDE("/guide"),
    BENEFITS("/benefits"),
    FAQ("/faq"),
    QUESTION("/question");


    private final String value;

    ServiceCommands(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
