# Compilers
JC := javac
JVM := java
JDOC := javadoc
JAR := jar

# Directories
SRC := src
OUT := build
BIN := bin
DOCS := docs
TEST := tests
LIB := lib

CP := $(LIB)/json-simple-1.1.1.jar
JUNIT := $(LIB)/junit-1.8.2.jar
JUNIT_FLAGS := --details=tree --disable-banner

# Variables containing files
SOURCES := $(shell find $(SRC) -name '*.java') # retrieve all .java files in src/
TESTS := $(shell find $(TEST) -name '*.java') # same in tests/
SRC_CLASSES := $(SOURCES:%.java=$(OUT)/%.class) # transform all source files paths into class files paths
TEST_CLASSES := $(TESTS:%.java=$(OUT)/%.class) # same for tests sources files

# Other variables
MAIN := game.Main # Starting class of program
JARFILE := game.jar # Name of the jar archive

##########

# Define rules that are not name of files, but instead recipes to be executed
.PHONY: cls clean doc tests test run play

# Compile all files
cls: $(SRC_CLASSES) $(TEST_CLASSES)

# Compile all source classes ; each must match the $(OUT)/%.class pattern ; %.java is the prerequisite
# See 'static pattern rules'
$(SRC_CLASSES): $(OUT)/%.class: %.java
	$(JC) -sourcepath $(SRC) -d $(OUT)/$(SRC) -classpath $(CP) $<

# Compile all test classes
$(TEST_CLASSES): $(OUT)/%.class: %.java
	$(JC) -sourcepath $(SRC):$(TEST) -d $(OUT)/$(TEST) -classpath $(OUT)/$(SRC):$(CP):$(JUNIT) $<

# Compile and run the program
run: $(SRC_CLASSES)
	$(JVM) -classpath $(OUT)/$(SRC):$(CP) $(MAIN) $(algo)

# Run all test files
tests: cls
	$(JVM) -jar $(JUNIT) -classpath $(OUT)/$(SRC):$(OUT)/$(TEST):$(CP):$(TEST) --select-package game $(JUNIT_FLAGS)

# Run the specified test with variable 'class' when calling rule (ex: make test class=game.TestGame)
test: cls
	$(JVM) -jar $(JUNIT) -classpath $(OUT)/$(SRC):$(OUT)/$(TEST):$(CP):$(TEST) --select-class $(class) $(JUNIT_FLAGS)

# Create .jar for the program
jar: $(CLASSES)
	@mkdir $(BIN) -p
	cd $(OUT)/$(SRC) && $(JAR) xvf ../../$(LIB)/json-simple-1.1.1.jar
	$(JAR) cvfe $(BIN)/$(JARFILE) $(MAIN) -C $(OUT)/$(SRC) .

# Start the program from jar archive
play: $(BIN)/$(JARFILE)
	$(JVM) -jar $(BIN)/$(JARFILE) $(algo)

# Generate documentation
docs:
	$(JDOC) -sourcepath $(SRC) -d $(DOCS) -classpath $(CP) -subpackages game

# Remove all .class files and generated docs
clean:
	rm -rf $(DOCS) $(OUT) $(BIN)
	find . -name *.class -type f -delete
