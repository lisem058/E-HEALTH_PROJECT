# E-HEALTH_PROJECT

This README explains how the directories work and what you could find if you check every directory step by step

 * under data management folder you could see the data science notebooks and the data preparation scripts, and also local README.md
 * under datasets folder is the datasets kept
 * under analytics folder there are jupyter notebooks for Part I and Part II (analysis part)
 * under backend folder you can find the Spring boot project and also local README.md
 * under web-frontend folder there is React application.

To access the web site you should use the following link:

```
https://hidden-ocean-15108.herokuapp.com/
```

Since it is heroku you should wait a little while it is starting the front end server and back end server since we've chosen the free mode and they're "sleeping" in free mode.

If you want to run everything step by step you need to go under the folders in the following order:
1. If you want to run the analytics from scratch go to the folder "analytics"
2. If you want to populate the MongoDb database you should go to the folder "data management"
3. If you want to run the backend server go to the folder "backend"
4. If you want to run the frontend server go to the "web-frontend"
