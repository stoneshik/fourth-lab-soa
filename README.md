# Сервис-ориентированная архитектура

## Лабораторная работа № 4

### Задание

Вариант 132459

Переработать сервисы из лабораторной работы #3 следующим образом:
- Второй ("вызывающий") сервис переписать в соответствии с требованиями протокола SOAP.
- Развернуть переработанный сервис на сервере приложений по собственному выбору.
- Оставшийся сервис не модифицировать, не менять его API, протокол и используемый сервер приложений.
- Установить и сконфигурировать на сервере Helios программное обеспечение Mule ESB.
- Настроить интеграцию двух сервисов с использованием установленного программного обеспечения.
- Реализовать дополнительную REST-"прослойку", обеспечивающую возможность доступа к переработанному сервису клиентского приложения без необходимости его модификации. Никакой дополнительной логики, помимо вызовов SOAP-сервиса, разработанная REST-прослойка содержать не должна.

Вопросы к защите лабораторной работы:
1. Протокол SOAP. Особенности, отличия от REST, преимущества и недостатки.
2. Дескрипторы сервисов на SOAP. Формат WSDL.
3. Реестры сервисов. UDDI.
4. Сервисные шины. Назначение, протоколы, особенности работы. Отличия, достоинства и недостатки относительно микросервисной архитектуры и инфраструктурного ПО для неё.
5. Mule ESB. Установка, конфигурация, поддерживаемые протоколы.
6. Реализация взаимодействия веб-сервисов через Mule ESB.


### Комментарии к реализации

Для запуска тестов нужно задать значение переменной окружения `SPRING_PROFILES_ACTIVE` равное `test` - в этом профиле используется БД поднимаемая `testcontainers`

Для запуска в обычном режиме значение переменной окружения `SPRING_PROFILES_ACTIVE` равное `dev`

Файл `.env` должен находится в директории `resources`

Пример `.env` файла находится в `env.example`

Подключение к серверу:
```
ssh s33xxxx@se.ifmo.ru -p 2222
```

Проброс порта для helios:
```
ssh -L 8080:localhost:33xxxx s33xxxx@se.ifmo.ru -p 2222
```

Url подключения к БД
```
jdbc:postgresql://localhost:5432/studs
```

Генерация ключа:
```
keytool -genkeypair -alias spring -keyalg RSA -keysize 4096 \
  -validity 3650 -keystore spring.p12 \
  -storetype PKCS12 -storepass changeit -keypass changeit \
  -dname "CN=localhost, OU=Development, O=Company, L=City, ST=State, C=RU"
```

Экспорт сертификата:
```
keytool -exportcert -alias spring -keystore spring.p12 -storetype PKCS12 -storepass changeit -file spring.crt
```

Добавление сертификата в trustore:
```
keytool -importcert -alias spring -file spring.crt -keystore spring-truststore.p12 -storetype PKCS12 -storepass changeit -noprompt
```

Экспортируем сертификат и ключ для HAProxy:
```
# 1. Экспорт приватного ключа
openssl pkcs12 -in spring.p12 -nocerts -nodes -out spring.key -passin pass:changeit
# 2. Экспорт сертификата
openssl pkcs12 -in spring.p12 -clcerts -nokeys -out spring.crt -passin pass:changeit
# 3. Объединяем в один PEM для HAProxy
cat spring.key spring.crt > spring.pem
```

Сбор jar файла с пропуском тестов
```
mvn package -DskipTests
```

Запуск сервисов:
```
SOA_SERVICE_PORT=33511 java -jar soa-0.0.1-SNAPSHOT.jar
```
```
SOA_SERVICE_PORT=33521 java -jar soa-0.0.1-SNAPSHOT.jar
```

Устанавливаем Consul:
```
unzip consul_*_linux_amd64.zip
sudo mv consul /usr/local/bin/
consul --version
```

Запуск consul с заданием значений всех портов:
```
consul agent -dev -ui \
  -client=0.0.0.0 \
  -bind=127.0.0.1 \
  -http-port=33410 \
  -server-port=33411 \
  -serf-lan-port=33412 \
  -serf-wan-port=33413 \
  -dns-port=33414
```

Остановка consul:
```
consul leave -http-addr=127.0.0.1:33410
```

Открыть consul:
```
http://localhost:33410
```

Установка HAProxy:
```
sudo apt update
sudo apt install haproxy -y
```

Копируем сертификаты
```
sudo mkdir /etc/haproxy/certs
sudo cp certs/spring.pem /etc/haproxy/certs/spring.pem
sudo cp certs/wildfly.pem /etc/haproxy/certs/wildfly.pem
```

Копируем конфиг для consul-template
```
sudo cp haproxy.cfg.ctmpl /etc/haproxy/haproxy.cfg.ctmpl
```

Управление haproxy:
```
sudo systemctl start haproxy
sudo systemctl status haproxy
sudo systemctl stop haproxy
sudo journalctl -u haproxy -f
```

Открыть статистику haproxy:
```
http://localhost:33401/stats
```

Установка consul-template:
```
unzip consul-template_*_linux_amd64.zip
sudo mv consul-template /usr/local/bin/
consul-template --version
```

Запуск consul-template:
```
sudo consul-template \
  -consul-addr=localhost:33410 \
  -template="/etc/haproxy/haproxy.cfg.ctmpl:/etc/haproxy/haproxy.cfg:sudo systemctl reload haproxy"
```

**Порты которые нужно пробросить:**
- 33510 - обращение к сервисам на spring
- 33610 - обращение к сервисам на wildfly
- 33401 - статистика haproxy
- 33410 - consul

### Ссылки на репозитории лабораторной

1. Ссылка на основной вызываемый сервис реализованный на Spring Boot - https://github.com/stoneshik/third-lab-soa
2. Ссылка на второй вызывающий сервис реализованный на JAX-RS - https://github.com/stoneshik/third-lab-soa-second
3. Ссылка на фронтенд - https://github.com/stoneshik/third-lab-soa-frontend
