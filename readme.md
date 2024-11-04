# M06-A01 Implementing the List-Detail Pattern 
The following details the implementation of the requested capabilities from the canvas rubric


### *A controller object instantiates and shows the list view containing a JTable of some model objects*
<hr>

[ApplicationController.java](src/main/java/controller/ApplicationController.java) 
is the meta controller that instantiates all other controllers.

[FileBrowserJPanelController.java](src/main/java/controller/filebrowser/FileBrowserJPanelController.java) 
is the controller that manages the list as a JTree as defined by [UserFileJTree.java](src/main/java/view/filebrowser/UserFileJTree.java).<br>
The JTable of details is created by [FileStatisticsJPanelController.java](src/main/java/controller/filedata/FileStatisticsJPanelController.java).



### *The user can select a model object from the list and view its details in a separate detail view*
<hr>

When a model object is clicked on in the JTree [FileStatisticsJPanelController.java](src/main/java/controller/filedata/FileStatisticsJPanelController.java)
is notified and updates the JTable with the details of the selected model object.
At the same time [FileDisplayJPanelController.java](src/main/java/controller/filedisplay/FileDisplayJPanelController.java) is notified and displays ALL images within the directory of the selected image (this may change later).

### *The user can add new items to the list using the detail view as the input data form*
<hr>

[ApplicationJMenuBar.java](src/main/java/view/ApplicationJMenuBar.java) has a text field that allows a user to enter in a new directory. All images found in said directory are added to the file tree.
A button above the file tree allows a user to add an individual image to the file tree.

### *The user can change the data for a particular item using the detail view as the input data form*
<hr>

[FileStatisticsJPanelController.java](src/main/java/controller/filedata/FileStatisticsJPanelController.java) 
manages multiple buttons to the left of the data table that allows a user to change the data of that model object given they input reasonably valid data.

### *The user can delete items from the list*
<hr>

[FileBrowserJPanelController.java](src/main/java/controller/filebrowser/FileBrowserJPanelController.java)
manages a button above the file tree that allows a user to remove a model object from the program.
It does not delete the file from their system, it simply tells the program to stop tracking that item. Directories are not permitted to be removed (currently).