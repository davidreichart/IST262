# M08-A03: Implement a Collection II

Class : [SystemDirectoryList.java](src/main/java/model/data/filetypes/SystemDirectoryList.java)<br>
Collection : HashSet<[SystemDirectory](src/main/java/model/data/filetypes/SystemDirectory.java)>

A set seemed a natural choice for this collection as there can only ever be one instance of a given directory on the 
user's computer. With the natural property of sets disallowing duplicates, this removes the need to implement such 
checks during addition and should naturally help to save memory during runtime. Most common operations (addition) should 
run in a near constant O(1) runtime, however the second most common operation (contains) does not benefit as much. 
This tradeoff was deemed acceptable to leverage the properties of sets.

## The controller creates an instance of the class encapsulating your collection.<hr>

[ApplicationContext.java](src/main/java/model/ApplicationContext.java) encapsulates the SystemDirectoryList object 
which holds the set.

## The constructor for this class should populate collection with a set of test data objects<hr>

The constructor does not handle test data, rather [App.java](src/main/java/App.java) constructs a new SystemDirectory 
object from the contents of <code>src/test/resources</code> which is added to the set.
This operation occurs on line 41 in the <code>run</code> method.

## The view class allows the user to search for objects in the collection<hr>

[UserFileJTree.java](src/main/java/view/filebrowser/UserFileJTree.java) displays all directories in the set as parent 
folder nodes. The contents of each directory become children of each respective directory node.

## The view class allows the user to get and display the data for a single object<hr>

By clicking on either a directory node in [UserFileJTree.java](src/main/java/view/filebrowser/UserFileJTree.java) or 
any of its children, a JTable is rendered in the lower statistics panel displaying data on the most relevant 
system directory from the set.

## The view class allows the user to add objects to the collection<hr>

[ApplicationJMenuBar.java](src/main/java/view/ApplicationJMenuBar.java) allows the user to input an absolute file path
leading to a system directory. If the given path is valid, a new SystemDirectory object is created and added to the set.

## The view class allows the user to remove (delete) objects in the collection<hr>

[FileStatisticsTableController.java](src/main/java/controller/filedata/FileStatisticsTableController.java) handles the 
appending of a listener to a button in the lower right of the statistics panel which allows the user to request the 
deletion of the most relevant directory. EX: an image of the test directory is selected -> the test directory would be 
removed. EX: the test directory node is selected -> the test directory would be removed.