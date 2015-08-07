# DataGathering
The Android application prototype represents a standalone Android application which
handles cognitive performance data when completing the tasks and provides
scheduling and alarm capabilities. All data collected is stored for later usage,
for example visualizations and data mining. 

The tasks are modules and were implemented as separate activities. 

This section aims to provide insight on the functionality and extensibility of
the task module developed for the project. 
The Data manipulation module serves basically as a translator between the
format in which the data is being saved and the Android object oriented programming
language. 
For developing purposes, the data is stored at the moment in the external
file system of the mobile phone. Specifically the data file is handled externally
as JSON data. In this regard the google free library GSON has been employed.
The general idea is that the data operations types are parsing from Object
to JSON and reverse, writing to file and reading from file. Most of the data
operations are self explanatory
