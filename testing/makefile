# Makefile for compiling and running serial program

# Directories
pSRC_DIR = src/parallelAbelianSandpile
pBIN_DIR = bin/parallelAbelianSandpile

sSRC_DIR = src/serialAbelianSandpile
sBIN_DIR = bin/serialAbelianSandpile

# Source files
pJAVA_FILES = $(wildcard $(pSRC_DIR)/*.java)
sJAVA_FILES = $(wildcard $(sSRC_DIR)/*.java)


# Compiled class files
pCLASS_FILES = $(patsubst $(pSRC_DIR)/%.java, $(pBIN_DIR)/%.class, $(pJAVA_FILES))
sCLASS_FILES = $(patsubst $(sSRC_DIR)/%.java, $(sBIN_DIR)/%.class, $(sJAVA_FILES))


# Compilation flags
JAVAC_FLAGS = -d bin -sourcepath src

# Main class
pMAIN_CLASS = parallelAbelianSandpile.AutomatonSimulation
sMAIN_CLASS = serialAbelianSandpile.AutomatonSimulation

# Default arguments (update these if needed)
ARGS ?= input/5_by_5_all_4.csv output/output.png  # Replace 'default_arguments' with your specific default arguments, if any

# Targets
.PHONY: all clean run directories

all: directories $(sCLASS_FILES) $(pCLASS_FILES)
directories:
	@mkdir -p $(sBIN_DIR)
	@mkdir -p $(pBIN_DIR)


$(sBIN_DIR)/%.class: $(sSRC_DIR)/%.java
	javac $(JAVAC_FLAGS) $<

$(pBIN_DIR)/%.class: $(pSRC_DIR)/%.java
	javac $(JAVAC_FLAGS) $<

clean:
	rm -rf bin/*

run: all
	java -classpath bin $(sMAIN_CLASS) input/5_by_5_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/5_by_5_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/9_by_9_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/9_by_9_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/17_by_17_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/17_by_17_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/33_by_33_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/33_by_33_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/65_by_65_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/65_by_65_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/129_by_129_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/129_by_129_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/257_by_257_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/257_by_257_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/513_by_513_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/513_by_513_all_4.csv output/output.png
	java -classpath bin $(sMAIN_CLASS) input/1025_by_1025_all_4.csv output/output.png
	java -classpath bin $(pMAIN_CLASS) input/1025_by_1025_all_4.csv output/output.png


