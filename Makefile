# A simple Makefile for the Java Common KFS BSD library.


# Global Settings

.PHONY: default check clean

JUNIT_VERSION=4.8.2
JUNIT_CLASSPATH=lib/junit.jar

#### Class List ####
MAIN_SRC=\
         src/com/kfs/bsd/common/util/LinearArgDesequencer.java\
#### Class List ####
TEST_SRC=\
#### Class List ####


# Computed Global Settings

MAIN_CLASSES=$(subst src/,bin/,$(subst .java,.class,$(MAIN_SRC)))
TEST_CLASSES=$(subst src/,bin/,$(subst .java,.class,$(TEST_SRC)))
_MAIN_CLASSES=$(subst /,.,$(subst .java,,$(subst src/,,$(MAIN_SRC))))
_TEST_CLASSES=$(subst /,.,$(subst .java,,$(subst src/,,$(TEST_SRC))))


# Default Target

default:
	@echo "'make build' does not currently make sense in this library.  Try 'make check'."


# JUnit Testing

check: lib/junit.jar $(TEST_CLASSES) $(MAIN_CLASSES)
	java -classpath bin:$(JUNIT_CLASSPATH) org.junit.runner.JUnitCore $(_TEST_CLASSES)

lib/junit.jar:
	cd lib/junit && ant
	cp lib/junit/junit$(JUNIT_VERSION)/junit-$(JUNIT_VERSION).jar lib/junit.jar
	cd lib/junit && ant clean && git clean -fd


# Generic Compilation

bin/%.class: src/%.java
	@mkdir -p bin
	javac -sourcepath src -classpath $(JUNIT_CLASSPATH) -d bin src/$*.java


# Housekeeping

clean:
	rm -rf bin
