
## Build and run the example queries

mvn clean install

mvn exec:java -Dexec.mainClass="co.uk.dagsoft.exercise.addressbook.Main"


## Comments

I have created the model and stream capabilities that allows to query easily the stream. 
There was no requirement to create a "criteria" Api so I have focused on the model and creating the stream that 
can be easily filtered to get the required values. This part is not automatically tested because of the lack of criteria API.
