##This readme file is in progress..

## Important Notes

- From Chisel2 tt Chisel3, the toolchain has changed the simulation from C++ to verilator. "Verilator offers the advantages of having industry support and permitting cosimulation of Chisel and existing verilog.  Given that it is based on translation of Verilog to C++ it will still allow you to write your own C++ code." "Chisel3 relies on verilator to generate the C++ simulation from the Verilog output of firrtl." Verilator should be install apart from the Chisel-toolchain.

- The PeekPokeTester supports two separate backends. The Firrtl Interpreter: a lightweight scala based low firrtl execution engine, with rapid spinup but slower overall speed. A verilator backend: which builds a c++ compiled circuit emulation. Faster execution, but with a longer spinup.

- Debugging with REPL (see link)


## Definitions
SBT
Chisel
Firrtl
Verilator


###Interesting Links
https://chisel.eecs.berkeley.edu/blog/?p=156
https://github.com/freechipsproject/chisel3/wiki/chisel-toolchain
https://github.com/freechipsproject/firrtl
https://github.com/freechipsproject/chisel-testers
https://github.com/freechipsproject/chisel3/wiki/Chisel3-vs-Chisel2
https://github.com/freechipsproject/chisel3/wiki/Running-Stuff
https://github.com/freechipsproject/chisel3/wiki/frequently-asked-questions
https://github.com/freechipsproject/chisel3/wiki/Debugging-with-the-Interpreter-REPL-1
https://github.com/ccelio/chisel-style-guide



###A compilation of infos for later reference

For example when running a test, the tester is invoked, which then invokes chisel3 to generate the circuit, which invokes firrtl to compile the circuit into low firrtl. After low firrtl has been generated, the firrtl-interpreter is invoked in order to execute the test on the device under test.

