# auction
The auction app, I going to use React, Java EE, Docker, PostgreSQL

#### For the App I use only Wildfly server you can download the latest one from
https://wildfly.org/downloads/

'wildfly:start' - starts wildfly but you need to specify your server location in <wildfly.home>
'wildfly:deploy' - deploy the app
'wildfly:redeploy' - redeploy the app
'wildfly:undeploy' - undeploy the app

.gitignore config info
https://www.atlassian.com/git/tutorials/saving-changes/gitignore

WildFly plugin info
https://docs.jboss.org/wildfly/plugins/maven/latest/examples/add-resource-example.html

CMT & BMT
https://examples.javacodegeeks.com/enterprise-java/ejb3/transactions/ejb-transaction-management-example/


## Errors
if you have an exception when deploy/redeploy like :
[ERROR] Caused by: org.hibernate.exception.SQLGrammarException: Unable to build DatabaseInformation 
[ERROR] Caused by: org.h2.jdbc.JdbcSQLException: Таблица \"PG_CLASS\" не найдена 
[ERROR] Table \"PG_CLASS\" not found; SQL statement: 
[ERROR] select relname from pg_class where relkind='S' [42102-193]"}}}} 

then delete in standalone.xml and standalone-full.xml below line:
<default-bindings contextservice="java:jboss/ee/concurrency/context/default" datasource="java:jboss/datasources/ExampleDS" jms-connection factory="java:jboss/DefaultJMSConnectionFactory" managed-executor-service="java:jboss/ee/concurrency/executor/default" managed-scheduled-executor-service="java:jboss/ee/concurrency/scheduler/default" managed-thread-factory="java:jboss/ee/concurrency/factory/default"/>

## OIDC
To prove it works with a valid JWT, you can clone Bootiful React project, and run its UI:

git clone -b okta https://github.com/oktadeveloper/spring-boot-react-example.git bootiful-react
cd bootiful-react/client
npm install

after you create an account in https://developer.okta.com/
myOKTA clientId : 0oa27h0yu7SOOU0SY4x6 - when you add an app
myOKTA domain : no-dev-645009.okta.com - top right corner

const config = {
  issuer: 'https://dev-669532.oktapreview.com/oauth2/default',
  redirect_uri: window.location.origin + '/implicit/callback',
  client_id: '0oag7xss2m38BLyIh0h7'
};

follow this tip and try to understand why he redirects to okta auth page
https://www.youtube.com/watch?v=ptzPD-u7LFU&list=PLshTZo9V1-aHrNcVSheNsfHLMcs29cel0

also articles to read
https://github.com/okta/okta-sdk-java#configuration-reference - here describes watewer I need



### how to check access token
https://www.jsonwebtoken.io/
