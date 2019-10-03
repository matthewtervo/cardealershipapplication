Application Overview:
    Domain: Car Dealership
    Platform: JVM
    Frontend: React
    Interface: Web UI (SPA)
    Backend: Java
This is a Spring Boot backend with ReactJS frontend application. The backend implements filtered REST APIs
using Spring Data JPA and Specifications.


SETUP/START INSTRUCTIONS:
0. Dependencies:
    In terminal, I am using:
        Maven 3.6.2
        Npm 6.9.0

1. Database Setup:
    Create a local mySQL database schema called ‘cardealership’.
        Refer <root>\src\main\resources\application.properties
            The application assumes the local database can be accessed on port 3306.
            The application assumes database can be accessed using :
                Username:root
                password:password.
    Note: Default database car entries will be inserted via the data-mysql.sql file on startup.

2. Spring Boot Backend Setup/Start:
    In a terminal window navigate to the project’s root directory (where the pom.xml file is located):
        Compile the project using maven:
            mvn compile -f pom.xml
        Start the application using maven:
            mvn spring-boot:run
    Note1: At this point you should be able to hit the API endpoint in your browser:
        http://localhost:8080/cars?search=year>2013,hasSunroof:true
    Note2: Backend supported search operations are:
        EQUALITY ':'
        GREATER THAN '>'
        LESS THAN '<'


3. React Frontend Setup/Start:
    In a terminal window navigate to the project’s <root>\src\webapp\ directory (where the package.json file is located):
        Install react server dependencies:
            npm install
        Start react server:
            npm start
    Note: At this point you should be able to access the frontend UI in your browser:
        http://localhost:3000/

4.DONE!
    The frontend is a very simple UI where you can select checkbox options for the car search criteria. By default the
    search is exclusive, unless you check the ‘Inclusive Search’ option.

TO-DO:
- Restructure front end to support the following:
    Create new car entry in database.
    Delete existing car entry in database.
    Update existing car entry in database.
    Query specific car ID (search endpoint already does this but I want an example that doesnt use Specifications)
    Add additional search options:
        make
        color
        model
        year range
        price range
        mileage range
- Look into/implement React component testing.

- Update backend with:
    Logging via log4j.
    Proper API error handling on bad/failed requests.
    Caching? why not!
    Deploy react front end using spring boot?
        ^Fix cross domain issue?




