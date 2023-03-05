## SpringMVCFilmCrud

# Description

This Film C.R.U.D full-stack application is a dynamic web project that utilizes Java, the RDMS MySQL, JDBC API, gradle dependency management, and SpringMVC Framework to create an interface that allows users to search, create update, and delete films from a relational database. This project is a continuation of the previous film query console application with the addition of a DAO pattern interacting with a controller, HTML files, JSP files, and a dispatcher servlet to deploy as a full-stack application. 


# Gradle 

Gradle was used in this project to manage the four dependencies required for the different technologies in our project to interact. Gradle utilizes the Groovy scripting language to dynamically compile Java bytecode and executes that code in the JRE. The four dependencies managed by Gradle in this project are...
1. <strong>org.springframework</strong> - The spring framework allows for the use of Java POJOs as Spring beans throughout the project through the component scan. This scans the classpath for annotated classes that will be automatically created as Spring beans. These Spring beans allow for Autowiring which instantiates the objects needed by the class automatically through the process of dependency injection. 
2. <strong>mysql-connector</strong> - This dependency allows for the connection to the relational databases in MySQL and usage of the SQL language with the JDBC API. 
3. <strong>Javax Servlet</strong> - The javax servlet API allows for the usage of classes like HTTPservletRequest and Response. Although these methods are not explicitly defined in the project they work behind the scenes allowing us to use JSPs to display information on the webpage.
4. <strong>JUNIT</strong> - JUNIT can be used to test methods to ensure they return the correct information. Unfortunately, we did not have time to implement JUINT in this project. 

# WEB.XML and Spring Configuration file

##### web.xml

The web.xml still holds the welcome file for this project, but it is much less involved than when using only servlets to pass information. This is because the spring framework handles client requests through a dispatcher servlet. The only two requirements for the current web.xml file are that the servlet-name matches the dispatcher servlet as (servlet-name)-servlet.xml, and the servlet-class reads as org.springframework.web.servlet.DispatcherServlet.

##### Spring Configuration file (dispatcher servlet)

As mentioned the dispatcher servlet's purpose is to handle client requests and ensure they are directed to the correct file path. When the landing page of our application opens, three links are displayed to either find a film by id, create a new film, or find a film by keyword. When one of those links is clicked, the user is taken to a static HTML page where they are asked for information depending on which link is chosen through an HTML form. When the user submits that form, whichever action is specified by the .do is carried out by the controller. The reason this is possible is because of the dispatcher servlet. Using the base package in the component scan, the dispatcher servlet searches the provided file path for anything that has a Spring annotation. Once it finds a matching file, it creates the objects needed for that file by dependency injection and the file, in this case the film controller, carries out the correct classpath. Also in this file is the viewResolver bean. This bean allows to set a prefix and suffix for view names carried out by the ModelAndView object or that are created as a String. It's most important purpose is for code maintainability, allowing for easy switching of front-end view types. 

# Controller

When a form is sent from an HTML page and that form's action matches the RequestMapping of one of the methods in the controller, the request will be carried out. For example, the HTML page filmIdForm actions the URL-pattern GetFilmDataById.do. When this request is sent in and re-directed by the dispatcher servlet, it is passed to the annotated controller where is it "caught" by the request mapping of one of the controller's methods. In this case the getFilmById method with the parameter of int filmId. Once the request mapping determines the URL-path is correct, it then looks to ensure that the RequestMethod type is correct. In our case a get method. Once this is ensured the method can begin. A ModelAndView object is created to be able to store any objects and views for the information that will be returned from the DAO. In this method, a film is returned using the parameter filmId. This parameter comes from the form that was submitted by the HTML page. It is important that either the parameter matches the name of the form perfectly or an annotation RequestParam is used with the exact spelling and a new local variable name. Once the DAO returns the film it is stored by the ModelAndView's addObject method and the View name is set for the JSP that the object information will be sent to. 

##### redirected methods

Methods in our controller including deleteFilm, updateFilm, and makeNewFilm are Post/Redirect/Get (PRG) methods. These specific methods carry out post requests that enter data that persists in our MySQL database. It is good practice to ensure that methods like these are redirected after initial interaction with the DAO so that the information they add or delete does not occur multiple times. In these methods, the object normally added by the addObject method is instead redirected using the RedirectAttributes class's addFlashAttribute method. The ModelAndView object's view is then set to the redirect methods URL path and the JSP view is launched as a GET request instead of a POST request. Not allowing the user to enter their information more than once. 

# DAO

When the controller calls a DAO method it is using the Interface version of that object, the correct DAO is not instantiated and used like in previous projects. With the SpringMVC framework, we use the component path to search for annotated components of that Interface object. In our case, it finds the FilmDaoJdbcImpl class. The method is then called from this class and the appropriate value is returned. As opposed to last week where we only used select statements to retrieve data, this week's application involved creating, updating, and deleting data in the database using Data Manipulation language. 
For example, in our createFilm method, we are using an Insert query to add a film to the database which will be auto-incremented by the database. It is imperative in these methods to start a transaction before beginning the query. Starting a transaction allows the transaction to be rolled-back if something occurs accidentally or a non-desirable outcome occurs. This transaction is started by setting AutoCommit to false. MySQL by default runs with auto-commit enabled. This means that any query that occurs outside of a transaction is automatically committed to the database. This would be undesirable if we by accident deleted an entire table of information. 

After the transaction begins, the SQL query language is used to create an insert statement. This is done using the keywords INSERT INTO, selecting the table, the values that will be changed, and then setting the values to bind variables. These bind variables are then further set using the prepared statement to add the information to the database. The prepared statement is then used to call its executeUpdate method which returns an int of the number of rows affected by the creation. In this instance, it returns 1 since only one row is changed. When the subsequent if statement is determined to be true the prepared statement then calls its generateKeys method which returns any auto-generated keys by the database which is assigned to the result set. In this case, the key is the film's id number. Once it is determined that the result set table has a next value the auto-generated key is assigned to the int newFilmId by calling the result sets getInt method. This newFilmid is then set to the film as the film's ID. If at any point during this transaction, an error occurs the transaction is automatically rolled back in the catch statement and an exception is thrown. If the film is determined to be null for any reason, a null film is returned. Otherwise, the created film is returned.

This created film is then sent back to the controller whereas a command object it is set into the ModelAndView and sent to be displayed in the JSP. It is important in the case of command objects that each name in the HTML form actioning it matches exactly to the setters of that object. This is the only way that the command object's fields can be set. This is done by removing the word set from the setter method and the first letter of the following is set to lowercase.

# JSP 

Depending on which controller method is called, determines which JSP will be displayed to the user. In the case of creating a film, the user is sent to the viewFilm.jsp. Here the user is displayed all of the film's information using JSP expression language(EL). EL allows for the object name to be used to call its methods similar to how they are called by command objects. In viewFilm.jsp as long as the film is not considered to be empty, The film's information is displayed in an unordered list. Beneath that, a link to the home page is provided as well as a link to update the film and a submit button to delete the film. 

If the user chooses the home link they will be sent back to the landing page. If they choose the update link, they will be sent to the newUpdate.jsp where they will be asked to enter the film's information with the current information pre-populated. In newUpdate.jsp the fields that require numbers are set to only accept numbers in their text boxes, while the language and ratings boxes use a drop-down menu to ensure only correct information is provided. If at any point during this process, a user fails to update their film they are sent to the updateError.jsp. If they are successful in updating their film they are sent UpdateSuccessful.jsp displaying a message saying their update was successful.

If the user selects to delete the film and it is a film that does not have any child dependencies, that film will be deleted and they will be re-routed to DeleteSuccessful.jsp where a successful message is displayed. If the user attempts to delete a film that is linked to foreign keys in the table, they are sent to deleteError.jsp where an error message is displayed. 
An almost exact process occurs if the user elects to search for a film by keyword. However, in that case, they are prompted to input the id of the film they would like to delete or update. If that film cannot be deleted because of key constraints they are sent to the error page. If they elect to update the film the same process as above occurs.   


# Technologies Used

* Java
* MySQL
* Gradle
* SpringMVC
* JDBC
* JSP
* Spring Tool Suite


# Lessons Learned

One lesson learned throughout this project is how to read others' pre-written code. This was new to David and me, and using Sebastian's already-written code was quite different from other weeks where we were only writing our own. 

Another lesson learned this week was how to work as a team and use the git repository system. The splitting of workload was a welcomed difference from other projects, but the use of git hub did not come without issues.

A third lesson learned from this project was the workflow of using SpringMVC. As opposed to using a different servlet for each URL-mapping SpringMVC allows for the use of a dispatcher servlet along with inversion of control for the creation of needed objects. 

A final lesson learned is using JSPs to display webpage information. The use of expression language as well as sending that information to other pages was a useful tool when developing this application.