[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0a5332a696ea4b06aa9f43a39f3f21f0)](https://www.codacy.com/app/jelabra/Agents_i2b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_i2b&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/Agents_i2b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_i2b)
[![codecov](https://codecov.io/gh/Arquisoft/Agents_i2b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_i2b)


# Agents_i2b

Skeleton of agents module

# Current Authors (2017-2018)
- Jose Emilio Labra Gayo (@labra)
- Alejandro González Hevia (@alejgh)
- Carmen Sors González (@carmee-en)
- Alvaro Tejido Jardón (@tejido18)
- Eduardo Ulibarri Toledo (@uo251436)

# Previous Authors (2016-2017)

- Herminio García González (@herminiogg)
- Jose Emilio Labra Gayo (@labra)
- Jorge Zapatero Sánchez (@JorgeZapa)
- Damián Rubio Cuervo (@DamianRubio)
- Antonio Nicolás Rivero Gómez (@Lan5432)

# MongoDB
This project uses MongoDB as the database. You can check how to use it on
 - [MongoDB install](https://github.com/Arquisoft/participants_i2b/wiki/MongoDB)

# Jasypt
This project uses Jasypt to encrypt the passwords. You don't need to download it, but you can check it at: http://www.jasypt.org/
 
## REST requests
In order to use the user credentials to obtain your data, you can send a POST request to http://localhost:8080/user. The
data in this request can come as:
 - JSON:
```json
{"login":"yourLogin", "password":"yourpassword", "kind":"agentKind"}
```

- XML:
```xml
<data>
 <login>yourLogin</login>
 <password>yourPassword</password>
 <agent>agentKind</kind>
</data>
```

## Tests
To run the tests just use `mvn test`. No data in the database is needed to run them. But if you want to test the
agent interface manually you'll have to introduce this document:

```json
{
   "_id" : ObjectId("58a8670df086e81dc034d7fc"),
   "name" : "Paco",
   "email" : "paco@naver.co.kr",
   "location" : [
      157,
      256
   ],
   "kind" : "Person",
   "username" : "pruebas",
   "password" : "khZZwjdhWwVbMdmOvz9eqBfKR1N6A+CdFBDM9c1dQduUnGewQyPRlBxB4Q6wT7Cq"
}
```

And as data use:
 - login: pruebas
 - password: 4[[j[frVCUMJ>hU
 - kind: "Person"

 If you have the data and the database running and the application still reports a 404 Not Found when it shouldn't
 try deleting the document and adding it again.




