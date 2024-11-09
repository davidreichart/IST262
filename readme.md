# M07-A02 Persistent Data Implementation<hr>
The following details what objects are being serialized, and how that serialization is occurring.

### A test folder of images in <code>serTest</code> can be used to test data persistence by adding its absolute file path in the menu bar text  field. Only the images found in <code>src/test/resources</code> are auto loaded by the application.<hr>


These two classes are currently placed in the model package, but may easily be refactored to the controller package if more appropraite:

- [Serializer.java](src/main/java/model/Serializer.java) - Instantiated at exit in [App.java](src/main/java/App.java) briefly so it has access to all model/view/controller objects. The following objects are the only ones written to the state.ser bytecode file:
  - [ApplicationContext.java](src/main/java/model/ApplicationContext.java) - Holds all currently tracked directories, files, and file details.
  - [UserFileJTree.java](src/main/java/view/filebrowser/UserFileJTree.java) - Holds the node's and their structure that makes up the file browser tree in the GUI. 

- [Deserializer.java](src/main/java/model/Deserializer.java) - Static class that has individual methods for loading each serialized object whenever needed when the program launches and a new frame is being created.
  - All serialized objects are stored in one file with the following object order:
    - ApplicationContext.java
    - UserFileJTree.java

<code>state.ser</code> should be located @ src/main/resources
<hr>

The following is a complete list of all objects implementing <code>Serializable</code> and which directly serialized object they are found in:<br>

<code>model</code><br>
- [ApplicationContext.java](src/main/java/model/ApplicationContext.java) <code>ApplicationContext</code>
- [FileTag.java](src/main/java/model/data/FileTag.java) <code>ApplicationContext</code>
- [SystemDirectoryList](src/main/java/model/data/filetypes/SystemDirectoryList.java) <code>ApplicationContext</code>
- [SystemDirectory](src/main/java/model/data/filetypes/SystemDirectory.java) <code>ApplicationContext</code>
- [ImageFile.java](src/main/java/model/data/filetypes/ImageFile.java) <code>ApplicationContext</code>
- [SystemFile.java](src/main/java/model/data/filetypes/SystemFile.java) <code>ApplicationContext</code>

<code>view</code><br>
- [UserFileJTree.java](src/main/java/view/filebrowser/UserFileJTree.java) <code>UserFileJTree</code>

<code>controller</code><br>
- *none*

