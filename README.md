# harken-graphql-health-tracker-v1

###Introduction

To provide a regional diabetic heatmap, allowing users to share their health measures such as diabetes indicators anonymously.
The application allows reports and measures to be saved. This data can be viewed on a per city basis providing health summaries
to be displayed in barchart format, by date/health level indicator (cholesterol, blood sugar)   

###Details

This graphql server project is implemented with com.graphql-java api (the reference implementation), and uses a schema first approach via the special 
graphql dsl (called SDL). 
The project uses graphql-java-spring-boot-starter (3rd party api) which provides an abstraction of graphql-java and provides a high level layer 
for schema graph processing. There is no need to programmatically initialise the schemas, as gql spring boot will check the classpath resources for 
*.graphqls files.

This has ensured the schema is designed quickly and gets a server up and running quickly.
The schema definitions needs to be placed in src/resources where the GraphQL Spring Boot starter will read and configure them
with graphql-java-tools.

graphql-java uses the fundamental concept of data fetchers, from the javadoc "A data fetcher is responsible for returning a data value back for a given graphql". 
Rather than using the auto-configuration of gql spring boot library the developer can fully code the schema and fetching logic.

The com.graphql-java project is a direct Java port of the JS based graphql server. However, there are other organisations and contributors that 
have improved on this such as com.graphql-java-kickstart api. To use the com.graphql-java-kickstart uncomment the dependencies in the build file
and comment the com.graphql-java artifacts (with a few code changes you should be good to go).

A version 2 of this project is in development and again based on com.graphql-java but will take a programmatic approach.

###References
https://www.bignerdranch.com/blog/building-a-graphql-server-with-java/