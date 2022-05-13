# Compilers
JC := javac
JVM := java
JDOC := javadoc
JAR := jar

# Directories
SRC := src
OUT := classes
DOCS := docs
TEST := tests

CP := jar/json-simple-1.1.1.jar
JUNIT := jar/junit-1.8.2.jar
JUNIT_FLAGS := --details=summary --disable-banner

# Variables containing files
SOURCES := $(shell find $(SRC) -name '*.java') # retrieve all .java files in src/
TESTS := $(shell find $(TEST) -name '*.java') # same in test/
SRC_CLASSES := $(SOURCES:%.java=$(OUT)/%.class) # transform all source files paths into class files paths
TEST_CLASSES := $(TESTS:%.java=$(OUT)/%.class) # same for tests sources files

# Other variables
MAIN := game.Main # Starting class of program
JARFILE := jeu.jar # Name of the jar archive

##########

# Define rules that are not name of files, but instead recipes to be executed
.PHONY: cls clean doc tests test run play

# Compile all files
cls : $(SRC_CLASSES) $(TEST_CLASSES)

# Compile all source classes ; each must match the $(OUT)/%.class pattern ; %.java is the prerequisite
# See 'static pattern rules'
$(SRC_CLASSES): $(OUT)/%.class: %.java
	$(JC) -sourcepath $(SRC) -d $(OUT)/$(SRC) -classpath $(CP) $<

# Compile all test classes
$(TEST_CLASSES): $(OUT)/%.class: %.java
	$(JC) -sourcepath $(SRC):$(TEST) -d $(OUT)/$(TEST) -classpath $(OUT)/$(SRC):$(CP):$(JUNIT) $<

# Create .jar for the program
$(JARFILE): $(CLASSES)
	@mkdir jar -p
	cd $(OUT)/$(SRC) && $(JAR) xvf ../../jar/json-simple-1.1.1.jar
	$(JAR) cvfe jar/$(JARFILE) $(MAIN) -C $(OUT)/$(SRC) .

# Generate documentation
doc:
	$(JDOC) -sourcepath $(SRC) -d $(DOCS) -classpath $(CP) -subpackages game

# Run all test files
tests: cls
	$(JVM) -jar $(JUNIT) -classpath $(OUT)/$(SRC):$(OUT)/$(TEST):$(CP):$(TEST) --select-package game $(JUNIT_FLAGS)

# Run the specified test with variable 'class' when calling rule (ex: make test class=game.TestGame)
test: cls
	$(JVM) -jar $(JUNIT) -classpath $(OUT)/$(SRC):$(OUT)/$(TEST):$(CP):$(TEST) --select-class $(class) $(JUNIT_FLAGS)

# Compile and run the program
run: $(SRC_CLASSES)
	$(JVM) -classpath $(OUT)/$(SRC):$(CP) $(MAIN) $(algo)

# Start the program from jar archive
play: $(JARFILE)
	$(JVM) -jar jar/$(JARFILE)  $(algo)

# Remove all .class files and generated docs
clean:
	rm -rf $(DOCS)
	rm -rf $(OUT)
	find . -name *.class -type f -delete
