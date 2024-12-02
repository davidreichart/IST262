# M08-A03: Implement a Collection I

Class : [ApplicationContext.java](src/main/java/model/ApplicationContext.java)<br>
Collection : ArrayList<[SystemFile](src/main/java/model/data/filetypes/SystemFile.java)>

The ArrayList "systemFiles" represents a collection of files on the user's system that are rendered in the GUI.
An ArrayList seemed apt given that theoretically, there may be multiple instances of an identical image file located at different locations on the user's system.
Given this, comparing the two images would cause a Set to presume that they are the same file, and thus only add one of the instances.

## The controller creates an instance of the class encapsulating your collection.<hr>

[App.java](src/main/java/App.java) loads an instance of the ApplicationContext class containing this collection upon app startup.
The method (loadApplicationContext) attempts to locate a .ser file to load a preexisting instance of this class from.

## The constructor for this class should populate collection with a set of test data objects<hr>

[App.java](src/main/java/App.java) contains code in the <i>run</i> method (which is the main app driver method) that populates this collection with test data from "src/test/resources".

## The view class allows the user to search for objects in the collection<hr>

[FileBrowserJPanel](src/main/java/view/filebrowser/FileBrowserJPanel.java) provides the user with a file browser to select which individual file to view.
[FileDisplayJPanel](src/main/java/view/filedisplay/FileDisplayJPanel.java) renders all images in the parent directory of the currently selected file in the file browser (or a singular file if in the non-default GUI view).


## The view class allows the user to get and display the data for a single object<hr>

[FileDisplayJPanel](src/main/java/view/filedisplay/FileDisplayJPanel.java) displays a table of data for the currently selected file in the file browser.

## The view class allows the user to add objects to the collection<hr>

[FileBrowserJPanel](src/main/java/view/filebrowser/FileBrowserJPanel.java) creates a JButton "addNewFileButton" that allows the user to add a singular file.<br>
[ApplicationJMenuBar](src/main/java/view/ApplicationJMenuBar.java) Contains a text field and button that allows the user to add an entire directory to be tracked by the application.
The application scans the entire folder for all image files and creates SystemFile objects to represent each.

## The view class allows the user to remove (delete) objects in the collection<hr>

[FileBrowserJPanel](src/main/java/view/filebrowser/FileBrowserJPanel.java) creates a JButton "deleteSelectedFileButton" that allows the user to remove the selected file from being tracked by the application.
This does not delete the file from the user's system.

## The selected collection class, test data, and implemented functionality contribute to the performance of your project application<hr>

The use of an ArrayList allows for quick random access to collection elements. 
Given that the user is allowed to select objects in any order (regardless of comparator) this property of ArrayLists allows for quick lookup of the exact SystemFile object instance that must be read from to display all relevant data to the user in the GUI.