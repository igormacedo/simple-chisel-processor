# This Makefile was created based on the makefile presented at https://github.com/schoeberl/chisel-examples

#
# Building Chisel examples without too much sbt/scala/... stuff
#
# sbt looks for default into a folder ./project and . for build.sdt and Build.scala
# sbt creates per default a ./target folder

SBT = sbt
TEST=0
REPL=0

## STARTER EXAMPLE
# Generate Verilog code

# creates firrtl and verilog file and saves to output/$@
Zero:
	$(SBT) "runMain components.$@ -td=output/$@"

# Generate the C++ simulation and run the tests
ZeroMain:
	$(SBT) "test:runMain components.$@"

# Start REPL firrtl simulator
ZeroRepl:
	$(SBT) "test:runMain components.$@"



# General RULE
%::
ifeq ($(TEST), 1)
	$(SBT) "test:runMain components.$@Main"
endif

ifeq ($(REPL), 1)
	$(SBT) "test:runMain components.$@Repl"
endif

ifeq ($(TEST)$(REPL), 00)
	$(SBT) "runMain components.$@ -td=output/$@"
endif