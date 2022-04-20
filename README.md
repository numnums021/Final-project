## :atm: Приложение, иммитирующее работу банкомата 

### :pencil: Техническое описание 
Приложение иммитирущее работу банкомата написано на языке программирования ***Java***, представляет из себя 2 ***Spring Boot*** приложения: банкомат (клиент) и сервер. Взаимодействие приложений реализовано с использованием ***REST***. В качестве системы сборки используется ***Maven***. Для хранения информации о клиенте используется СУБД ***PostgreSQL***.

#### :file_folder: Компоненты программы: 
- Client - модуль, реализующий работу банкомата, отвечает за денежные запросы к серверу, проверяет правильность введённых данных.
- Server - модуль, реализующий работу сервера, принимает запросы от клиента, реализует взаимодействие с БД для удовлетворения требования и возвращает необходимый результат.
- Common - модуль, отвечающий за общие данные, используемые при общении модулей Client и Server.

#### :floppy_disk: Хранилище данных 
При организации хранилища данных, использовались следующие утверждения:
1) Каждая карта уникальна.
2) Каждый клиент уникален.
3) У одного клиента может быть несколько карт.
4) У карты может быть только один владелец (клиент).
5) У одного пользователя может быть много ролей.
6) Много ролей могут принадлежать множеству пользователей.

По данным утверждениям было организовано следующее хранилище данных:
![image](https://user-images.githubusercontent.com/59023791/164175613-126d45e4-b39e-4d92-b556-c6d2a61bfa45.png)

**SQL скрипты создания карты, клиента и ролей соответственно:**
```
CREATE TABLE Cards(
                      id_card SERIAL,
                      pin INT,
                      balance NUMERIC,
                      id_client INT NOT NULL,
                      FOREIGN KEY (id_client) REFERENCES Clients(id_client),
                      PRIMARY KEY(id_card)
);
```

```
CREATE TABLE Clients(
                        id_client SERIAL,
                        name VARCHAR(15),
                        surname VARCHAR(20),
                        patronymic VARCHAR(20),
                        date_of_birth DATE,
                        PRIMARY KEY(id_client)
);
```
```
CREATE TABLE users_roles(
	user_id BIGINT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES cards(id_card),
	FOREIGN KEY (role_id) REFERENCES roles(id)
);
```
```
CREATE TABLE roles(
	id SERIAL,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);
```
### :computer: Пример использования
Перед началом работы приложения в настройках необходимо ввести номер карты и пинкод клиента.
В качестве примера воспользуемся ***[Postman](https://www.postman.com/company/about-postman/).***
   
На данном этапе клиент может выполнить 3 вида операций по своей карте:
1) ***Узнать баланс***           
>  
>Для выполнения данного действия необходимо ввести следующий запрос в Postman:
>```
>http://localhost:1704/atms/balance
>``` 
>> __В случае успешного выполнения отобразятся следующие данные:(bold)__
>>   
>> `Ваш баланс = [Денежные средства] руб.`
>>
2) ***Снять денежные средства с карты***   
>   
>Для выполнения данного действия необходимо ввести следующий запрос в Postman:
>```
>http://localhost:1704/atms/deposit/1000
>```
>>   
>>`1000` - 1000 отвечает за количество денежных средств для снятия с карты.
>>  
>>> __В случае успешного выполнения отобразятся следующие данные:__
>>>   
>>>`Вы внесли денежные средства на карту. Ваш баланс составляет = [Денежные средства] руб.`
>>>   
3) ***Положить денежные средства на карту***
>   
> Для выполнения данного действия необходимо ввести следующий запрос в Postman:
>```
>http://localhost:1704/atms/withdraw/10000 
>```
>>`1000` - 1000 отвечает за количество денежных средств для пополнения карты.
>>   
>>> __В случае успешного выполнения отобразятся следующие данные: __
>>>   
>>>`Вы сняли денежные средства с карты. 
>>>   Ваш баланс составляет = [Денежные средства] руб.`
4) ***Аутентификация***   
>   
>Для выполнения данного действия необходимо ввести следующий запрос в Postman:
>```
>http://localhost:1704/atms/auth
>```
>> __В случае успешного выполнения отобразятся следующие данные:__
>>   
>>`Вы успешно аутентифицировались!`
>>   
5) ***Выход из системы***   
>   
>Для выполнения данного действия необходимо ввести следующий запрос в Postman:
>```
>http://localhost:1704/atms/logout
>```
>>__В случае успешного выполнения отобразятся следующие данные:__
>>   
>>`Вы вышли из системы`
>>    
### :x: Обработка ошибок
   
В следующей таблице приведены основные возможные ошибки при использовании приложения с соответствующими сообщениями при их возникновении, а также возможный вариант их решения.
   
   | ОШИБКА | СООБЩЕНИЕ | РЕШЕНИЕ |
|----------------|:---------:|----------------:|
| Направильный формат ввода номера карты | "Проверьте правильность Вашего запроса." | Номер карты должен быть положительным целым числом |
| Направильный формат ввода PIN | "Проверьте правильность Вашего запроса." | PIN код должен быть положительным целым числом |
| Неверный логин или PIN | "Ошибка аутентификации" | Необходимо проверить правильность введённых учётных данных |
| Введенна недоступная для снятия денежная сумма | "На Вашем счёте недостаточно средств." | Проверьте остаток средств на счёте и повторите операцию с доступной суммой |
   
   
## :grey_exclamation: Безопасность   
Перед началом работы с приложением клиенту необходимо пройти процедуру аутентификации, после чего ему будет доступны основные функции программы.
При аутентификации клиент получает свой собственный JWT, который в последствии будет прикреплятся к заголовкам запросов для будущей проверки их на сервере.
   
В случае, если пользовательне пройдёт аутентификацию, при попытке сделать запрос к ресурсам карты - ему будет отказано.
  
Время жизни специального ключа - токена, составляет 1 минуту.

## :speech_balloon: В разработке
В данный момент планируется реализовать авторизацию с помощью неиспользуемого раннее функционала - информации о пользователях.
А также добавить брокер сообщений Apache Kafka.

![image](https://user-images.githubusercontent.com/59023791/162626773-e95b9cda-928e-4474-8b9d-e8770f2d9346.png)
