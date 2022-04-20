**Ticket Master Code Chanllenge**

When you're done, you can delete the content in this README and update the file with details for others getting started with your repository.

*We recommend that you open this README in another tab as you perform the tasks below. You can [watch our video](https://youtu.be/0ocf7u76WSo) for a full demo of all the steps in this tutorial. Open the video in a new tab to avoid leaving Bitbucket.*

---

## To run this project

You will need the following software:

1. At least JDK 11.
2. Gradle 7.4.2

---

## Libraries Used

The following libraries were used on this project.

1. Lombok annotations used to avoid generating setters and getters as well as logging.
2. MapStruct this library makes it easy to copy java bean properties.

##Running the application

1. Start as a regular SpringBoot application
2. Once is running, test the end point URi: curl -get localhost:8080/artists/27
3. Artist id has to be a positive number

##Design Considerations
1. Thought about using in memory H2 database to read json and store in tables, I may still do this in a different branch. For now I am keeping it very simple.
2. Result dto eventsList could benefit from HATEOS so link to an Event URI are available  
3. Artist id has to be a positive number if it does not exist a ArtistNotFoundException is returned with the appropiate http code
4. Added functionality could be its own microservice
    
