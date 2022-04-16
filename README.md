# Business-Enterprise
Project Created for ICE-2101 Business-Enteprise Semester 2 group Module.



## Adding application.properties
The Application Will not run without this file being created.

1. Navigate to `/Business-Enterprise/src/main/resources/`.
2. Create a file named `application.properties`.
3. Inside this new file we need to add the information for our database.

``` properties
spring.datasource.driver-class-name= #{The Name of your driver | ex:'com.mysql.cj.jdbc.Driver'}
spring.datasource.password= #{The password for your datasource}
spring.datasource.url= #{The url for your datasource | ex:'jdbc:mysql://localhost/academi_cymraeg'}
spring.datasource.username= #{The username for your datasource}
spring.jpa.hibernate.ddl-auto=update #{The Method for our table creation. Needs to be set to 'validate' for production env.}
```

## Running the Application

1. Navigate to `/Business-Enterprise/src/main/java/uk/ac/bangor/csee/group3/spring/academigymraeg/AcademiGymraegApplication.java`
2. right click and select `run as > Java Application` OR use <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>X</kbd>, <kbd>J</kbd>


## Accessing Secured Routes in development.

For the sake of ease the application will generate our first user, which will be given the highest level of access. Since this currently is only intended for local use, this is plenty secure.

The details for admin are:
Username - `admin` <br>
Password - `admin`



