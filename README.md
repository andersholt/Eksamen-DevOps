# DevOps med gode intensjoner

Lenkene i svarene er lenket til siste versjon av filene, jeg legger derfor ved kodeblokker som viser hvilke endringer som er blitt gjort underveis.

## Del 1 DevOps-prinsipper

Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse
disse? Hvilke DevOps prinsipper blir brutt?

Shoppifly har følgende problemer/utfordringer:
  *	De deployer kode 4 ganger i året.
  *	Å deploye ofte har tidligere ført til ustabilitet:

Dette fører til at de må ansette flere testere.
Senker frekvensen av leveransene.
Utviklingslederne må gå godkjenne alle leveranser.
* Ved deployment feiler koden ofte, som gjør at de må rulle tilbake til forrige versjon.
* Leveransen skjer ikke automatisk. 
* En egen avdeling installerer leveransen i produksjon.

### Hvilke DevOps-prinsipper blir brutt?
Shoppifly bryter Flyt-prinsippet og tilbakemeldings-prinsippet.

Shoppifly har en god del «waste» i verdikjeden sin. Siden deployment skjer 4 ganger i året, og vi får en opphoping av oppgaver som må gjennomgås av utviklingslederne før eventuelle feil kan rettes opp av utviklingsavdelingen. Utviklerne må da kanskje jobbe med kode de skrev for 3 måneder siden, dette fører til at de må bryte det de jobbet med fra før for å løse den oppståtte feilen. Driftsavdelingen må vente lenge på nye deployments, og får mange store oppgaver samtidig.f
De har også en unødvendig overlevering mellom utviklingsavdelingen og driftsavdelingen. Denne kan vi løse ved å ikke dele opp i to større avdelinger, men heller i DevOps-team. Med dette et DevOps-team vil ofte løse oppstående problemer raskere enn
Bedriften har egne testere, dette gjør at utviklerne kan lene seg litt på at de skal finne feilen i koden deres, utviklerne leverer da «dårligere» kode. De bruker også tilsynelatende manuelle tester for å teste koden. De har heller ikke en fungerende CI/CD-pipeline.

Å deploye sjeldent vil ofte føre til en større opphoping av oppgaver enn å deploye ofte. Disse oppgavene kan ofte bli store og vanskelig å gjøre. Når deployment skjer fire ganger i året, kan problemer for kode som ble skrevet 3 måneder tidligere by på problemer, disse problemene kan være komplekse og vanskelig for drift- eller utviklingsavdelingen å rette opp. Utviklerne som skrev koden, kan ha glemt viktige elementer av funksjonaliteten i leveransen, som gjør at utviklerne må bruke tid på å sette seg inn i kode som de skrev flere måneder tilbake.

Det er åpenbart at prosessen de har hatt gjør at Shoppifly må bruke mer ressurser på testere, siden koden ikke er av tilstrekkelig kvalitet.

Å senke deploymentfrekvensen vil igjen føre til større leveranser som kan være krevende å håndtere.

Utviklingslederne må bruke tid og ressurser på å gå gjennom leveransene. Ved å gi så mange og store oppgaver til utviklingslederne vil vi få en flaskehals i utviklingsprosessen. I tillegg, kommer disse oppgavene i store bolker fire ganger i året. Dette gjør at leveransene blir enda mer forsinket, siden utviklingslederne må sette seg inn i og kvalitetssikre store mengder kode av gangen, dette fører til tapt tid og muligvis skroting av funksjonalitet.

Koden feiler ofte ved deployment, det kan være feil i selve konfigureringen av produksjonsmiljøet. Dette fører også til tapt tid og funksjonalitet.

Å dele opp i en utviklingsavdeling og en driftsavdeling gjør at prosessen krever flere overleveringer. Feil i produksjon og deployment, vil ligge på driftsavdelingen, som har mindre forståelse for hvordan koden virker enn de som har skrevet den.

Shoppifly blir med dette mindre konkurransedyktige, i form av ressurstap, funksjonalitet og sikkerhet.

Løsning:

Ved å bruke kontinuerlig leveranse og implementasjon, vil vi ikke få en like stor opphoping av oppgaver, vi vil få færre problemer i produksjon samtidig, og problemene vil ofte være mer nærliggende hos utviklerne som nettopp har skrevet koden, dette vil føre til en mer effektiv feilretting. I tillegg til dette vil utviklerne ofte ta mer ansvar for at koden som leveres fungerer, da dette ikke blir et problem de må løse om opp til 3 måneder.
Vi bør ha et DevOps-team som står for både utvikling og deployment. Problemer som oppstår i produksjon, vil være lettere for et DevOps-team å håndtere enn for to oppdelte avdelinger.
Ved å deploye så fort funksjonalitet er ferdig utviklet vil feilene i koden oppstå raskere, det vil ikke være fire perioder i året hvor det er mange feil samtidig, men heller små feil av og til, som blir enkelt for DevOps-teamet å løse.
Vi ønsker heller ikke at utviklingslederne må gå gjennom all kode som deployes. Automatisering kan gjøre denne delen av arbeidet mer effektivt, da utviklerne kan få respons på om koden faktisk virker før den deployes. Det anbefales at Shoppifly setter opp en CI- og CD-pipeline som gjør mye av denne repetitive kvalitetssikringen før deployment skjer raskere og likt hver gang. Dette vil også minske arbeidet for utviklingslederne, da de slipper å gå gjennom kode som ikke består testene og driftsavdelingen slipper å bruke tid på leveransen.
Et DevOps-team, vil sitte på mer ansvar for egen kode, og de vil trolig gjøre mer for å sikre seg at koden fungerer før de pusher.
Ved å bruke DevOps-prinsippene, vil Shoppifly få mer funksjonalitet, raskere feilretting og trolig høyere kvalitet på applikasjonen

---
* En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?

Ut ifra et DevOps-perspektiv, ønsker vi å ha gjøre dette ofte. Et DevOps-team fokuserer på å forstå kundebehovet, og med dette ofte at funksjonalitet er ønskelig så raskt som mulig.

Det er vanskelig å release funksjonalitet sjeldnere og legge inn mer kontroller og kvalitetssikring samtidig. Sjelden release fører ofte til at mye kode endres samtidig, og med dette at større og flere feil oppstår samtidig.
Vi ønsker heller å ha en høyere releasefrekvens. Med høy releasefrekvens vil feilene kanskje oppstå hyppigere, men feilene bør med dette være mindre og lettere å håndtere.
Vi kan også vurdere å la noen av brukerne få tilgang til den nye funksjonaliteten, slik at vi får tilbakemelding fra de om noe går galt, da minsker vi konsekvensen av en feil i produksjon.

---
* Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?

Den største utfordringen ved å dele opp i en utviklings-avdeling og en drift-avdeling, er at drift-avdelingen ikke nødvendigvis forstår koden og virkemåten bak funksjonaliteten som er levert. Dette kan føre til tapt tid og ressurser da driftsavdelingen må håndtere denne feilen. Samtidig har utviklerteamet hands on erfaring med koden, og forstår kanskje hva som har gått galt i løsningen, og hvordan det skal rettes. Det gir også enda et overleveringsledd som utviklerne og drift må håndtere.

Utviklerteamet vil trolig også finne frem den beste løsningen for feilen i koden, samtidig som dev-teamet kanskje vil finne den raskeste løsningen for å løse feilen som har oppstått

Feilrettingen kan være svak, noe som gjør at løsningen kan feile enda en gang på samme sted.
Dette fører til tapt tid, i og med at driftsavdelingen må sette seg inn i kode som utviklings-avdelingen allerede har kjennskap til.
Noen av gevinstene ved å kombinere disse avdelingen er:
- 	Sikrere drift: Dersom feil oppstår vil et kombinert team ha kunnskap med hvordan
systemet fungerer, og feilrettingen vil derfor i de fleste tilfeller gå raskere,
og det kombinerte teamet vil mest sannsynlig også komme med en god måte å rette opp feilen (ved å foreksempel ikke legge in exceptions på funksjonalitet som er nødvendig).
- Raskere feilretting.
- 	Vi slipper endring av kontekst for de ulike teamene. Hva taper man ved å kombinere team:
- 	Færre overleveringer.



---
* Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere
  eller fjerne risiko ved hyppige leveraner.

Om vi har har en CI/CD pipeline som kjører alle tester ved deployment, og godkjenning av pull/merge-requests inn til main/master branchen i koden. For å kunne release kode ofte, er det viktig å ha en robust automatisert pipeline med gode tester for å kvalitetssikre koden.

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
      - main
  pull_request:
```
Om jeg misforsto oppgaven over til at det skal være pull_request til main og push til main så må vi legge inn "branches: - main" på neste linje.

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

I [docker.yml](.github/workflows/docker.yml) er det lagt inn to variabler: ```username``` og ```password```. 
Disse linker til github sine secrets-variabler.
Jeg går derfor inn på dockerhub og oppretter en ny token, og oppretter to nye repository secrets i github i settings, 
med brukernavnet mitt og det genererte tokenet (DOCKER_HUB_USERNAME og DOCKER_HUB_TOKEN).

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

Endrer også [docker.yml](.github/workflows/docker.yml) til slik at det er i dockerimaget vi bygger applikasjonen:
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

Endrer [docker.yml](.github/workflows/docker.yml), og kopierer over ferdig-genererte push commands fra ECR, men endrer endingen på lenkene til:
```1053:$rev``` for å få commit hashen fra github som ending.

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

Problemet oppstår fordi vi forsøker å opprette en ny bucket, siden vi ikke har definert en state-fil. Hvis vi ser på hva som skjer før feilmeldingen:
````
Plan: 5 to add, 0 to change, 0 to destroy.
aws_cloudwatch_dashboard.main: Creating...
aws_sns_topic.alarms: Creating...
aws_s3_bucket.analyticsbucket: Creating...
aws_cloudwatch_dashboard.main: Creation complete after 1s [id=1053]
aws_sns_topic.alarms: Creation complete after 4s [id=arn:aws:sns:eu-west-1:244530008913:alarm-topic-1053]
aws_sns_topic_subscription.user_updates_sqs_target: Creating...
aws_cloudwatch_metric_alarm.Too_many_checkouts: Creating...
aws_sns_topic_subscription.user_updates_sqs_target: Creation complete after 1s [id=arn:aws:sns:eu-west-1:244530008913:alarm-topic-1053:172f95e4-5336-41c4-b4e6-57e3579cd179]
aws_cloudwatch_metric_alarm.Too_many_checkouts: Creation complete after 1s [id=too_many_checkouts]
````

Vi ser at vi forsøker å opprette ny bucket, når denne allerede eksisterer. Grunnen til a dette skjer, er at vi ikke har definert en terraform state.
Dersom vi definerer hvor state-filen er, kan terraform lese av terraform-staten og modifisere state.

Endrer slik at vi har en definert state: [provider.tf](infra/provider.tf)

Da får vi:
````
aws_s3_bucket.analyticsbucket: Refreshing state... [id=analytics-1053]
aws_cloudwatch_dashboard.main: Refreshing state... [id=1053]
aws_sns_topic.alarms: Refreshing state... [id=arn:aws:sns:eu-west-1:244530008913:alarm-topic-1053]
aws_sns_topic_subscription.user_updates_sqs_target: Refreshing state... [id=arn:aws:sns:eu-west-1:244530008913:alarm-topic-1053:172f95e4-5336-41c4-b4e6-57e3579cd179]
aws_cloudwatch_metric_alarm.Too_many_checkouts: Refreshing state... [id=too_many_checkouts]
````
Og vi ser at denne "refresher" state i stedet.

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
Bruker genererte metrics-parametere fra GUIet til cloudwatch for å velge korrekt datapunkt for checkout_latency.

### Alarmer

<details><summary>Oppgavetekst</summary>

Lag Terraform-kode som oppretter

* En CloudWatch Alarm  som løses ut dersom antall handlekurver over tre repeternde perioder,på fem minutter, overstiger verdien 5
* Alarmen skal sendes som e-post til en addresse som gis i workflow filen ```cloudwatch_dashboard.yml``` 
</details>

Jeg oppretter en alarm i [sns_topic_for_alarm.tf](infra/sns_topic_for_alarm.tf).
For at cloudwatch skal ha muligheten til å sende meg en mail, må jeg registrere mailen min på eventet i SNS.

