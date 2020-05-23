package org.example.tm.command;

public enum CommandInfo {
    PROJECT_CLEAR_COMMAND("project-clear", "Очистить все проекты"),
    PROJECT_CREATE_COMMAND("project-create", "Создать проект"),
    PROJECT_EDIT_COMMAND("project-edit", "Редактировать проект"),
    PROJECT_LIST_COMMAND("project-list", "Открыть список проектов"),
    PROJECT_OPEN_COMMAND("project-open", "Открыть проект"),
    PROJECT_REMOVE_COMMAND("project-remove", "Удалить проект"),
    TASK_CLEAR_COMMAND("task-clear", "Очистить все задачи"),
    TASK_CREATE_COMMAND("task-create", "Создать задачу"),
    TASK_EDIT_COMMAND("task-edit", "Редактировать задачу"),
    TASK_LIST_COMMAND("task-list", "Открыть список задач"),
    TASK_OPEN_COMMAND("task-open", "Открыть задачу"),
    TASK_REMOVE_COMMAND("task-remove", "Удалить задачу"),
    USER_CREATE_COMMAND("user-create", "Создать нового пользователя"),
    USER_EDIT_COMMAND("user-edit", "Редактировать имя пользователя"),
    USER_LOGIN_COMMAND("user-login", "Авторизоваться"),
    USER_LOGOUT_COMMAND("user-logout", "Выйти из системы"),
    USER_NEWPASSWORD_COMMAND("user-newpassword", "Поменять пароль у существующего пользователя"),
    USER_OPEN_COMMAND("user-open", "Получить информацию о пользователе"),
    HELP_COMMAND("help", "Вывод всех доступных команд"),
    EXIT_COMMAND("exit", "Выход из приложения"),
    ABOUT_COMMAND("about", "Описание приложения");

    private final String name;
    private final String description;

    CommandInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static void printInfo() {
        for (CommandInfo value : values()) {
            System.out.printf("%s: %s\n", value.name, value.description);
        }
    }
}
