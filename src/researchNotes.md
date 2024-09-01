### *This file stores documentation/websites/etc that have relevance to the creation of this application. Git ignores this. Contains a lot of assumptions from my inexperienced mind.*
<hr>

# **Resources**
<hr><hr>

## pom.xml & Maven
- the pom.xml file tells the Maven build tool what dependencies this project needs to function
- just using it for JUnit in this current case

<hr>

## BufferedImage vs Image Objects (when to use what)
- Image should probably be used, and then instantiated as BufferedImage?
- Kind of like List -> LinkedList / Set -> HashSet
- [StackOverflow thread talking about Image abstract class vs Buffered Image concrete class](https://stackoverflow.com/questions/3944825/difference-between-the-image-and-bufferedimage-in-java)<br>
<br>
- Im not storing Image objects in each ImageFile object as that might be too space-resource intensive? Instead their just called upon by their File path

<hr>

## ImageIO class (making files legible by Java)
- BufferedImages can be read with ImageIO.read(File)

[Java documentation: ImageIO class](https://docs.oracle.com/javase/8/docs/api/javax/imageio/ImageIO.html)<br>

<hr>

## Serialization (data persistence)
- Converts objects to a byte steam (not legible like JSON)
- Seems like this can be used for data persistence?
- Currently thinking of using this to store some "super object" that can remember things, IST 262 course probably has a better method than this

[Java documentation: Serializable objects](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)<br>
[Youtube: Bro Code/Java serialization](https://www.youtube.com/watch?v=DfbFTVNfkeI)

<hr>

## Singleton (design pattern)
- Making an object a singleton prevents more than one instance ever being created at a time
- Apparently the best way to do this is to make a public enum that holds one "INSTANCE" variable. 

[Stack Overflow: explaining shortly how to implement a Singleton pattern using enums](https://stackoverflow.com/questions/70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java)<br>
[Effective Java: in depth explanation of singleton approach](https://drdobbs.com/jvm/creating-and-destroying-java-objects-par/208403883?pgno=3)<br>

# **Data Structures**
<hr><hr>

## Sets
- Sets automatically disallow duplicate objects
- Implement Comparable to define what makes a custom object a duplicate

[Java documentation: Set Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html)<br>

<hr>

## HashSet
- Implementation of Set
- Uses a HashTable to allow O(1) operations on the set (kinda like arrays)
- Doesn't sort the set automatically, could be done with an O(n) operation if need be, probably won't for this project?
- @Override hashCode() would allow us to define our own algorithm for generating the int that identifies each item in the HashSet (think UUID). Probably don't need to do that for this project? From my understanding, making a custom hashing algorithm would allow encoding and decoding?
- Faster than a TreeSet and should probably be the default implementation to reach for -- TreeSets only matter when ordering is very important apparently?

[Java documentation: HashSet Class](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)<br>
[Youtube: Coding with John/Set and Hashset, skipping to part about HashSets](https://youtu.be/QvHBHuuddYk?si=xd71ry5H10Fgrukb&t=990)<br>