# stock-app
App specs: https://docs.google.com/document/d/1_KPhsD6GnbbCIPebblkNeL0DC3UFxIAPMhR9oybz0lk/edit?usp=sharing

####Prerequisits
Current version was tested in following environment:

Ubuntu 16.04

Java 1.8

Maven 3.3.9

Tomcat 8.5.23

At least these or higher versions of software are required

####Building
 Run `mvn clean install -P full`

 `full` profile enables documentation building.
 
Documentation can be found at `target/site/index.html`
 
####Testing 
 
Run backend: `mvn jetty:run -pl rest-app -Djetty.http.port=8080`

Run frontend: `mvn jetty:run -pl web-app -Djetty.http.port=8090`

After that you can access web application at `localhost:8090/`

####Deployment

After building copy `rest-app/target/rest-app.war` and `web-app/target/web-app.war` 
to `$CATALINA_BASE/webapps` (i.e. to `webapps` folder of your Tomcat installation)

By default `web-app` will try to access backend API at `http://localhost:8080/rest-app`.
You can change this behavior either by editing 
`web-app/src/main/resources/application.properties`
before building app or by using JNDI lookup without needing to rebuild app.
If you want to use former way, create `$CATALINA_BASE/conf/[enginename]/[hostname]/web-app.xml` file
with such content:
```
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="uriConfiguration" value="/path/to/your/properties-file" type="java.lang.String"/>
</Context>
```
File with properties specified in `value` attribute will be loaded into application.
Example properties file:
```
backend.uri.protocol=http
backend.uri.host=localhost
backend.uri.port=9000
backend.uri.prefix=/rest-app
``` 
After that `web-app` will be expecting `rest-app` is accessable at `http://localhost:9000/rest-app`
