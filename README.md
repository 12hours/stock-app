# stock-app
App specs: https://docs.google.com/document/d/1_KPhsD6GnbbCIPebblkNeL0DC3UFxIAPMhR9oybz0lk/edit?usp=sharing

Building app:
 `mvn clean install -P full`

 `full` profile enables documentation building.
 
Documentation can be found at `target/site/index.html`
 
Run backend: `mvn jetty:run -pl rest-app -Djetty.http.port=8080`
Run frontend: `mvn jetty:run -pl web-app -Djetty.http.port=8090`

