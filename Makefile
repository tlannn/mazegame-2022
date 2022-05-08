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
CLASSES := $(SOURCES:$(SRC)/%.java=$(OUT)/%.class) # transform all source files paths into class files paths

# Other variables
MAIN := game.Main # Starting class of program
JARFILE := jeu.jar # Name of the jar archive

##########

# Define rules that are not name of files, instead recipes to be executed
.PHONY: all clean jar docs tests test run play

# Compile program
all: $(CLASSES)

cls : $(CLASSES) tests

# Compile all classes ; each must match the $(OUT)/%.class pattern ; $(SRC)/%.java is the prerequisite
# See 'static pattern rules'
$(CLASSES): $(OUT)/%.class: $(SRC)/%.java
	$(JC) -sourcepath $(SRC) -d $(OUT) -classpath $(CP) $<

# Create .jar for the program
$(JARFILE): $(CLASSES)
	@mkdir jar -p
	cd classes && $(JAR) xvf ../jar/json-simple-1.1.1.jar
	$(JAR) cvfe jar/$(JARFILE) $(MAIN) -C classes .

# Generate documentation
doc:
	$(JDOC) -sourcepath $(SRC) -d $(DOCS) -classpath $(CP) -subpackages game

# Compile all test files
tests: $(CLASSES) $(TESTS:%.java=%.class)
#$(JC) -sourcepath src -classpath test4poo.jar $(TESTS)

# Compile a single test file
$(TESTS:%.java=%.class): $(TEST)/%.class: $(TEST)/%.java
	$(JC) -sourcepath $(SRC) -classpath $(OUT):$(CP):$(JUNIT) $<

# Run the specified test with variable 'class' when calling rule (ex: make test class=game.TestGame)
# -z is to check emptiness of the string
test: $(CLASSES) $(TESTS:%.java=%.class)
	@if [ -z "$(class)" ]; then \
		$(JVM) -jar $(JUNIT) -classpath $(OUT):$(CP):$(TEST) --select-package game $(JUNIT_FLAGS); \
	else \
  		$(JVM) -jar $(JUNIT) -classpath $(OUT):$(CP):$(TEST) --select-class $(class) $(JUNIT_FLAGS); \
  	fi

# Compile and run the program
run: $(CLASSES)
	$(JVM) -classpath $(OUT):$(CP) $(MAIN)

# Start the program from jar archive
play: $(JARFILE)
	$(JVM) -jar jar/$(JARFILE)

# Remove all .class files and generated docs
clean:
	rm -rf $(DOCS)
	rm -rf $(OUT)
	find . -name *.class -type f -delete
