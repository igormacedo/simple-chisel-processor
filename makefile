# This Makefile was created based on the makefile presented at https://github.com/schoeberl/chisel-examples

#
# Building Chisel examples without too much sbt/scala/... stuff
#
# sbt looks for default into a folder ./project and . for build.sdt and Build.scala
# sbt creates per default a ./target folder

SBT = sbt

## STARTER EXAMPLE
# Generate Verilog code
zero:
	$(SBT) "runMain components.Zero -td=output/Zero"
	# creates firrtl and verilog file and saves to output/adder

# Generate the C++ simulation and run the tests
zero-test-main:
	$(SBT) "test:runMain components.ZeroMain"

zero-test-repl:
	$(SBT) "test:runMain components.ZeroRepl"


## Components
mux1bit:
	$(SBT) "runMain components.Mux1bit -td=output/Mux1bit"

mux1bit-test-main:
	$(SBT) "test:runMain components.Mux1bitMain"

mux1bit-test-repl:
	$(SBT) "test:runMain components.Mux1bitRepl"


muxNinNbits:
	$(SBT) "runMain components.MuxNinNbits -td=output/MuxNinNbits"

muxNinNbits-test-main:
	$(SBT) "test:runMain components.MuxNinNbitsMain"

muxNinNbits-test-repl:
	$(SBT) "test:runMain components.MuxNinNbitsRepl"
