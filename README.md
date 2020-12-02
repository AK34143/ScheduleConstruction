# ScheduleConstruction
Wayne Enterprises is developing a new city. They are constructing many buildings and plan to use software to keep track of all buildings under construction in this new city. A building record has the following fields: buildingNum: unique integer identifier for each building. executed_time: total number of days spent so far on this building. total_time: the total number of days needed to complete the construction of the building. Wayne Construction works on one building at a time. When it is time to select a building to work on, the building with the lowest executed_time (ties are broken by selecting the building with the lowest buildingNum) is selected. The selected building is worked on until complete or for 5 days, whichever happens first. If the building completes during this period its number and day of completion is output and it is removed from the data structures. Otherwise, the building’s executed_time is updated. In both cases, Wayne Construction selects the next building to work on using the selection rule. When no building remains, the completion date of the new city is output.

The program takes input from file in input directory
How to run this program: \
javac -d out src/com/*.java \
java -cp out com.RisingCity 

The output_file will be present in out directory
