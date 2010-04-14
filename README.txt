This is the Java Common KFS BSD library.

REPOSITORY USAGE

It is intended that this repository be used as a submodule, although it is completely self-sustaining with regard to internal test cases.

The intended usage of this repository is as follows:

  My Great Project Foo
  |
  |- src/
  |  |- org/
  |     |- companyname/
  |        |- foo/
  |           |- MyGreatClass.java
  |- lib/
     |- jckb/
     |- junit/

In other words, it is expected that you keep your source tree as you would normally, and include this repository as a submodule located in the 'lib' folder in your Java tree, along with any other libraries you might have, such as JUnit.

Once this repository is added, you can use sourcepath extension to include this code in your builds:

  javac -sourcepath src:lib/jckb/src -d bin src/org/companyname/foo/MyGreatClass.java

And then run just like normal, because the compiled files from this repository are now in your bin folder:

  java -classpath bin org.companyname.foo.MyGreatClass

This repository uses the above technique to include the JUnit repository, except that because the JUnit repository can be build nicely as a JAR file, the jar file is instead included, using classpath extension rather than sourcepath extension.


INITIALIZATION

Currently, all of the code in this repository can compile without checking out any submodules.

On the other hand, if you intend to develop for this repository, then you will want the unit tests, for which you will need to checkout the submodules (currently only JUnit, but hey - things can change).

The following command should successfully add this repository as a submodule in your repository:

  git submodule add git://208.53.115.13/Users/andrew/scm/bsd/lib/java/Java_Common_KFS_BSD.git lib/jckb
