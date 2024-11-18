
# XML to Java szervíz

#### Előfeltétel:
Az új alkalmazás a készítés idején elérhető legfrissebb LTS verziókkal készült - Spring Boot 3.3.5 és jdk 21.
A fordításhoz Maven szükséges. A konténerizáció Dockert használ.

#### Indítás:
A project letöltése után a root mappában a `mvn clean install -U` parancsot adjuk ki.
Sikeres futtatása után a `docker-compose build --no-cache; docker-compose up -d` parancsot adjuk ki, szintén a root mappában.

#### Tesztelés:
A `http://localhost:8080/swagger-ui/index.html` oldalt használhatjuk, vagy töltsük le az xml-to-java-client alkalmazást a `https://github.com/gtulipan/xml-to-java-client` oldalról.