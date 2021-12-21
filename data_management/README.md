# Why?

Here we've got the data_preparation_scripts, that get the data after Part II and parse them into JSON documents.
Then we populate mongodb with the JSONs

# How to run it

You need to have python3.6 and run it in terminal

```
$ python3.6 prepare_database.py
```

Then you need to move the script mongo.sh from the "datasets" folder change the connection string to your own MongoDb server and then run it 

```
$ ./mongo.sh
```
