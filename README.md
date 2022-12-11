# DevOps med gode intensjoner

Lenkene i svarene er lenket til siste versjon av filene, jeg legger derfor ved kodeblokker som viser hvilke endringer som er blitt gjort underveis.

## Del 1 DevOps-prinsipper

Beskriv med egne ord;

* Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse
  disse? Hvilke DevOps prinsipper blir brutt?

De deployer kode første mandag i kvartalet.

Svar:
Problemet her er at det da vil være mye kode å gå gjennom for hver leveranse. Utviklingslederne må fortsatt bruke mye 
tid på å gå gjennom alle leveransene, og mye funksjonalitet må derfor bli skrotet.

De pleide å deploye oftere før- men dette førte til ustabilitet. Selskapet ansatte flere testere, 
og startet en prosess der utviklingsledere måtte se over og godkjenne alle leveranser. 
De senket samtidig frekvensen på leveransene sine for å få bedre stabilitet.

Svar:
Det virker som om selve koden som ble deployet var av hørere kvalitet tidligere, men at de fikk en
flaskehals i og med at utviklingslederne måtte bruke tid på å se over og godkjenne leveransene. Her
kunne de fått en bedre flyt ved at flere går over koden og sørger for at alle leveransene går gjennom.

Når de deployer, feiler det fortsatt ofte. Da ruller de tilbake til forrige versjon, og ny
funksjonalitet blir derfor ofte forsinket i månedsvis

Svar:


Leveransen skjer ved at Utviklingsteamet bruker FTP til å overføre en Spring boot JAR sammen med 
dokumentasjon i en ZIP. En egen avdeling tar i mot disse filene og installerer i AWS / Produksjon.

Svar:

Her kunne de ha opprettet en pipeline i github, som deployer for de automatisk.





* En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?
* Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?

Den største utfordringen ved å dele opp i en utviklings-avdeling og en drift-avdeling, er at drift-avdelingen ikke nødvendigvis forstår koden og virkemåten bak funksjonaliteten som er levert. Dette kan føre til tapt tid og ressurser da driftsavdelingen må håndtere denne feilen. Samtidig har utviklerteamet hands on erfaring med koden, og forstår kanskje hva som har gått galt i løsningen, og hvordan det skal rettes.

Utviklerteamet vil trolig også finne frem den beste løsningen for feilen i koden, 
samtidig som ops-teamet kanskje vil finne den raskeste løsningen for å løse feilen 
som har oppstått -> Feilrettingen kan være svak, noe som gjør at løsningen kan feile 
enda en gang på samme sted.

Dette fører til tapt tid, i og med at driftsavdelingen må sette seg inn i kode som 
utviklings-avdelingen allerede har kjennskap til.

Noen av gevinstene ved å kombinere disse avdelingen er:
-	Sikrere drift: Dersom feil oppstår vil et kombinert team ha kunnskap med hvordan 
- systemet fungerer, og feilrettingen vil derfor i de fleste tilfeller gå raskere, 
- og det kombinerte teamet vil mest sannsynlig også komme med en god måte å rette opp 
- feilen (ved å foreksempel ikke legge in exceptions på funksjonalitet som er nødvendig).
-	Raskere feilretting.
-	Vi slipper endring av kontekst for de ulike teamene.
     Hva taper man ved å kombinere team:
-	Man får kanskje tregere utvikling, siden man teamet vil ha et større
     ansvarsområde.
     Vi ønsker ferrest mulig overleveringer.


* Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere
  eller fjerne risiko ved hyppige leveraner.

Automatisering: Vi har har en CI/CD pipeline som kjører alle tester ved deployment, og godkjenning av pull/merge-requests 
inn til main/master branchen i koden. Vi bør også legge inn en sperre for å pushe kode direkte til main/master.

## Del 2 - CI

### Oppgave 1 

* Start med å få workflowen til å kjøre når det lages en pull request, og på hver push til main branch:
---
I [ci.yml](.github/workflows/ci.yml) ser vi at workflowen kun skal kjøre ved workflow_dispatch, som dukker opp som et valg i github actions.
Vi endrer kriteriene for at workflowen skal kjøre, her vil den kjøre på push til main, og alle pull requests:
```yml
on:
  push:
    branches:
      - master
  pull_request:
```

### Oppgave 2 
<details><summary>Oppgavetekst</summary>

* Få først ```ci.yml```workflow til å feile fordi enhetstesten feiler.

* Rett deretter enhetstesten og se at pipeline kjører "ok".

* Workflowen skal kompilere javakoden og kjøre enhetstester på hver eneste push, *uavhengig av branch*
</details>

---

* Gjør slik at testene kjører endre Build with Maven slik at den bygger med testene:
```yml
- name: Build with Maven
  run: mvn -B package --file pom.xml
```

* Enhetstesten forventer at antall handlekurver skal være 100 etter at vi har lagt til og sjekket ut en handlekurv.
Jeg endrer enhetstesten slik at den forventer 0 istedet, og testen kjører grønt.

* Her må vi endre på [ci.yml](.github/workflows/ci.yml) med nye krav for at workflowen skal kjøre:
```yml
on: [push, pull_request]
```

### Oppgave 3 
<details><summary>Oppgavetekst</summary>
Branch protection og status sjekker - Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte
at

* Ingen kan pushe kode direkte på main branch
* Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning
* Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.
</details>

----

Fra roten av repoet/forken:

-> Trykk på "Settings"

-> Trykk på "Branches"

-> Trykk på "Add branch protection rule"

-> Skriv "main" i Branch name pattern

-> Huk av "Require pull request before merging"

-> Huk av "Require status checks to pass before merging" (i feltet som dukker opp må du skrive og velge ```build```)

-> Huk av "Do not allow bypassing the above settings", slik at vi er helt sikre på at reglene som er satt ikke blir overstyrt.

Ferdig!

Du kan sjekke om dette virker ved å forsøke å pushe direkte til main. Du vil da få en feilmeling som denne:
```
remote: error: GH006: Protected branch update failed for refs/heads/main.
remote: error: Required status check "build" is expected. At least 1 approving review is required by reviewers with write access.
To https://github.com/<DINFORK>/Eksamen-DevOps.git
```
## Del 3 - Docker

### Oppgave 1
<details><summary>Oppgavetekst</summary>
Beskriv hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?
</details>

---

I [docker.yml](.github/workflows/docker.yml) er det lagt inn to variabler: ```username``` og ```password```. Disse linker til github sine secrets-variabler.
Jeg går derfor inn på dockerhub og oppretter en ny token, og oppretter to nye repository secrets i settings, med brukernavnet mitt og det genererte tokenet (DOCKER_HUB_USERNAME og DOCKER_HUB_TOKEN).

Docker build skal nå kjøre grønt.

### Oppgave 2
<details><summary>Oppgavetekst</summary>

Når du har fikset problemet i oppgave 1, og du forøker å kjøre applikasjonen fra Docker hub med for eksempel; 
```docker run <dockerhub brukeravn>/shopifly```

Får du en feilmelding 
```text
Exception in thread "main" java.lang.UnsupportedClassVersionError: no/shoppifly/CddemoApplication has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
        at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
        at java.net.URLClassLoader.defineClass(URLClassLoader.java:473)
        at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
```

De annsatte i Gaffel consulting tenkte at Maven-bygget kunne kjøres i GitHub Actions, med  ```mvn ...```, og at JAR filen kunne kopieres inn i Container Image docker under bygging.  

Men så bestemte en av utviklerene seg for å oppgradere Javaversjonen i pom.xml, og workflow filen, til Java 11. 
Alt stoppet å fungere! Som dere ser av Dockerfilen, kjører Spring boot på Java 8...

```
FROM adoptopenjdk/openjdk8
```

Konsulentene ga opp, og hoppet som vanlig over på en annen oppgave. Så for øyeblikket har ikke Shopifly en fungerende applikasjon. 

Vi kan få bedre kontroll på byggeprosessen ved også å gjøre maven bygget i en container. For å unngå lignende problemer i fremtiden ønsker vi derfor å bruke Docker til kompilere- og bygge koden.

* Skriv om Dockerfilen. til bruke en *Multi Stage Build*. 
* Du må også rydde opp i ```docker.yml``` workflow filen... Fjern ønødvendige "steps".
</details>

---

Feilmeldingen oppstår fordi Dockercontaineren bruker java8, samtidig som applikasjonen ble kompilert i java 11.
[Dockerfile](Dockerfile) endres derfor til: da kompilerer vi og bygger i Dockerfila.
````Dockerfile
FROM maven:3.6-jdk-11 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM adoptopenjdk/openjdk11
COPY --from=build app/target/onlinestore-0.0.1-SNAPSHOT.jar /app/application.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/application.jar"]
````

Endrer også [docker.yml](.github/workflows/docker.yml) til:
```yml
name: Docker build
on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Login to Docker Hub
        uses: docker/login-action@v2
        with:
          username: ${{ secrets.DOCKER_HUB_USERNAME }}
          password: ${{ secrets.DOCKER_HUB_TOKEN }}
      - name: Build and push
        uses: docker/build-push-action@v3
        with:
          context: .
          file: ./Dockerfile
          push: true
          tags: ${{ secrets.DOCKER_HUB_USERNAME }}/shopifly:latest
```

### Oppave 3
<details><summary>Oppgavetekst</summary>

Gaffel consulting var ikke klar over at det var en dårlig idè å ha sitt container image i et offentlig Docker hub repository - og Shopifly har allerede sett at flere hundre har lastet ned deres container image.
Et privat ECR repository i AWS er en bedre løsning.

* Lag dit eget ECR repository med kandidatnummer som navn, enten ved hjelp av UI - eller ved hjelp av CLI.
* Endre ```docker.yml```, workflow til å pushe docker container til Amazon ECR, istedet for docker hub
* Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.
* Docker workflow skal pushe et container image med en tag som er lik GitHub commit hash (id); for eksempel ```244530008913.dkr.ecr.eu-west-1.amazonaws.com/glenn_exam_practice:8234efc```
</details>

--- 
Oppretter ECR repository 1053 -> Dette gjør jeg via UI.

Endrer [docker.yml](.github/workflows/docker.yml), og kopierer over ferdig-genererte fra push commands fra ECR, men endrer endingen på lenkene til:
```1053:$rev```

## Del 4 - Metrics, overvåkning og alarmer

Cloud9 er ikke verdens beste IDE. Det anbefales å gjøre den følgende oppgaven på lokal PC. Husk å kjøre  
```aws configure``` 
;-) 

### Oppgave 1
<details><summary>Oppgavetekst</summary>

Gjør nødvendige endringer i ```pom.xml``` - og koden, slik at applikasjonen kan levere Metrics til CloudWatch ved hjelp av Spring Boot Micrometer.
Konfigurer applikasjonen til å bruke ditt eget ClodWatch Metrics Namespace - ditt Kandidatnummer. 

*OBS!* Når dere innfører Micrometer i koden deres, vil enhetstesten slutte å fungere. Dere får lov til å slette 
enhetstesten når dere starter å jobbe med denne oppgaven. I "virkeligheten" ville vi brukt et rammeverk som feks Mockito  
til å "mocke" micrometer fra enhetstestene, men det er ikke ønskelig at dere skal bruke tid på dette under eksamen!
</details>

Kommenterer ut testen.
Legger inn Micrometer og AWS-sdk i pom-fila:
```xml
...
<properties>
  <java.version>11</java.version>
  <aws.sdk.version>2.17.292</aws.sdk.version>
</properties>
<dependenies>
    ...
  <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-cloudwatch2</artifactId>
  </dependency>
  <dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>utils</artifactId>
    <version>${aws.sdk.version}</version>
  </dependency>
  <dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>aws-sdk-java</artifactId>
    <version>${aws.sdk.version}</version>
    <scope>provided</scope>
  </dependency>
</dependenies>
...
```
Oppretter [MetricsConfig.java](src/main/java/no/shoppifly/MetricsConfig.java), med mitt kandidatnummer i configuration.

### Oppgave 2 
<details><summary>Oppgavetekst</summary>

Endre Javakoden slik at den rapporterer følgende Metrics til CloudWatch

* "carts" -  Antall handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.  
* "cartsvalue" - Total sum med penger i handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
* "checkouts" - Totalt antall  handlevogner er blitt sjekket ut
* "checkout_latency" - Gjennomsnittlig responstid for Checkout metoden i Controller-klassen.
</details>

Se: [ShoppingCartController.java](src/main/java/no/shoppifly/ShoppingCartController.java).
Også lagt inn: 
```java
float total();
```
i [CartService.java](src/main/java/no/shoppifly/CartService.java).

## Del 5 - Terraform og CloudWatch Dashboards
<details><summary>Oppgavetekst</summary>

Konsulentene i Gaffel consulting hadde ambisiøse planer om å få Terraform-koden i dette repoet til å kjøre
i GitHub Actions. Workflowen kjørte bra første gang, men nå feiler den hver gang, og klager over at en bucket med samme navn allerede eksisterer.
Shopifly har tenkt på bruke denne bucketen til data-analyse.

```text
Error: creating Amazon S3 (Simple Storage) Bucket (analytics-jim): BucketAlreadyOwnedByYou: 
Your previous request to create the named bucket succeeded and you already own it.
```

De kommenterte derfor bare ut S3 bucket koden, og gikk videre til neste oppgave. 
</details>

### Oppgave 1 
<details><summary>Oppgavetekst</summary>

Se på ```provider.tf filen```. 

* Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer? 
* Gjør nødvendige Endre slik denne slik at Terraform kan kjøres flere ganger uten å forsøke å opprette ressurser hver gang den kjører.
* Fjern kommentarene fra ```databacket.tf``` slik at Terraform-koden  også lager en S3 bucket. 

</details>

Endrer [provider.tf](infra/provider.tf)

Fjerner kommentarer fra [databucket.tf](infra/databucket.tf)

### Oppgave 2
<details><summary>Oppgavetekst</summary>

Et annet problem er at "terraform apply" bare blir kjørt hver gang noen lager en Pull request. Vi ønsker bare å kjøre apply når
noen gjør en push mot main branch. 

Fullfør workflow filen ```cloudwatch_dashboard.yml``` filen slik at apply bare bli kjørt på push mot main branch, og terraform plan   
på når det lages en Pull request 
</details>

Gjør nødvendige endringer i [cloudwatch_dashboard.yml](.github/workflows/cloudwatch_dashboard.yml)

Bruker github.event_name for å sjekke for pull_request eller push og github.ref for å sjekke branch.
### Oppgave 3
<details><summary>Oppgavetekst</summary>

* Fullfør cloudwatch_dashboard.tf slik at koden lager et CloudWatch Dashboard med *fire widgets*. Disse skal vise metrikkene fra oppgave 2, Del 4. 
* Antall handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
* Total sum med penger i handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
* Totalt antall  handlevogner er blitt "sjekket ut" per time
* Gjennomsnittlig responstid for Checkout metoden i Controller-klassen.
</details>

Gjør nødvendige endringer i [dashboards.tf](infra/dashboards.tf).

### Alarmer

<details><summary>Oppgavetekst</summary>

Lag Terraform-kode som oppretter

* En CloudWatch Alarm  som løses ut dersom antall handlekurver over tre repeternde perioder,på fem minutter, overstiger verdien 5
* Alarmen skal sendes som e-post til en addresse som gis i workflow filen ```cloudwatch_dashboard.yml``` 
</details>

For at cloudwatch skal ha muligheten til å sende meg en mail, må jeg registrere mailen min på eventet i SNS.

