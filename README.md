## Android Chatrooms

### Описание приложения.
Приложение разрабатывалось в академических целях в рамках курсов @mail.ru group на языке Java для платформы Android. Позволяет клиентам обмениваться текстовыми сообщениями.

        Для работы требуется развертывание локального Golang сервера (процесс развертывания описан ниже)

### Основные возможности.
1. Обмен текстовыми сообщениями внутри комнаты.
2. Создание комнат.
3. Просмотр и редактирование статуса пользователя.

### Разработчики
* Малых Дмитрий Андреевич.

Документация основных экранов приложения.

## Экран регистрации.
![alt tag](https://raw.githubusercontent.com/3XclusiVe/AndroidSimpleChat/master/Documentation/register.jpg)

## Экран входа в приложение.
![alt tag](https://raw.githubusercontent.com/3XclusiVe/AndroidSimpleChat/master/Documentation/login.jpg)

 Приложение поддерживает автоматическую реавторизацию при выходе из приложения.

## Экран комнат.
![alt tag](https://raw.githubusercontent.com/3XclusiVe/AndroidSimpleChat/master/Documentation/chats.jpg)

В данном окне есть возможность создать новую комнату нажатием кнопки + в навигационном баре, редактировать собственный профиль, либо зайти в существующую комнату.

## Окно чата.
![alt tag](https://raw.githubusercontent.com/3XclusiVe/AndroidSimpleChat/master/Documentation/chat.jpg)

При нажатии на сообщение откроется информация о пользователе.


## Развертывание локального сервера.
 1. Клонировать репозиторий [ссылка](https://github.com/3XclusiVe/AndroidChatServer).
 2. Найти и запустить файл server.go из директории AndroidChatServer/server/ (Требуется [Golang](https://golang.org/dl/)).
