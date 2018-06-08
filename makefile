# This Makefile was created based on the makefile presented at https://github.com/schoeberl/chisel-examples

#
# Building Chisel examples without too much sbt/scala/... stuff
#
# sbt looks for default into a folder ./project and . for build.sdt and Build.scala
# sbt creates per default a ./target folder

SBT = sbt


# Generate Verilog code

adder:
	$(SBT) "runMain components.Adder -td=output/adder"
	# creates firrtl and verilog file and saves to output/adder


# Generate the C++ simulation and run the tests

adder-test:
	$(SBT) "test:runMain components.AdderTester"


