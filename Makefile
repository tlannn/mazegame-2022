# Compilers
JC := javac
JVM := java
JDOC := javadoc
JAR := jar

# Directories
SRC := src
OUT := classes
DOCS := docs
TEST := test

# Variables containing files
SOURCES := $(shell find $(SRC) -name '*.java') # retrieve all .java files in src/
TESTS := $(shell find $(TEST) -name '*.java') # same in test/
CLASSES := $(SOURCES:$(SRC)/%.java=$(OUT)/%.class) # transform all source files paths into class files paths

# Other variables
MAIN := game.Main # Starting class of program
JARFILE := mazegame.jar # Name of the jar archive

##########

# Define rules that are not name of files, instead recipes to be executed
.PHONY: all clean jar docs tests test run play

# Compile program
all: $(CLASSES)

# Compile all classes ; each must match the $(OUT)/%.class pattern ; $(SRC)/%.java is the prerequisite
# See 'static pattern rules'
$(CLASSES): $(OUT)/%.class: $(SRC)/%.java
	$(JC) -sourcepath $(SRC) -d $(OUT) $<

# Create .jar for the program
jar: $(CLASSES)
	$(JAR) cvfe $(JARFILE) $(MAIN) -C $(OUT) game

# Generate documentation
docs:
	$(JDOC) -sourcepath $(SRC) -d $(DOCS) -subpackages game

# Compile all test files
tests: $(CLASSES) $(TESTS:%.java=%.class)
#$(JC) -sourcepath src -classpath test4poo.jar $(TESTS)

# Compile a single test file
$(TESTS:%.java=%.class): $(TEST)/%.class: $(TEST)/%.java
	$(JC) -sourcepath src -classpath test4poo.jar $<

# Run the specified test with variable 'class' when calling rule (ex: make test class=game.TestGame)
test: $(CLASSES) $(TESTS:%.java=%.class)
	$(JVM) -jar test4poo.jar -classpath classes:test $(class)

# Compile and run the program
run: $(CLASSES)
	$(JVM) -classpath $(OUT) $(MAIN)

# Start the program from jar archive
play: jar
	$(JVM) -jar $(JARFILE)

# Remove all .class files and generated docs
clean:
	rm -rf $(DOCS)
	rm -rf $(OUT)
	rm -rf $(JARFILE)
	find . -name *.class -type f -delete
