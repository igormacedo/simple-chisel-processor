package components

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ZeroUnitTester(c: Zero) extends PeekPokeTester(c) {
    private val adder = c
    poke(adder.io.inputA, 1) //Defines input to next cycle

    step(1) //Steps one clock cycle

    expect(adder.io.Cout, 0) //Test if the output is correct

}

/** 
class ZeroTester extends ChiselFlatSpec {
    "Basic test using Driver.execute" should "be used as an alternative way to run specification" in {
        iotesters.Driver.execute(Array(), () => new Zero(2)) {
        c => new ZeroUnitTester(c)
        } should be (true)
    }
}
**/

/**
  * This provides an alternate way to run tests, by executing then as a main
  * From sbt (Note: the test: prefix is because this main is under the test package hierarchy):
  * {{{
  * test:runMain gcd.GCDMain
  * }}}
  * To see all command line options use:
  * {{{
  * test:runMain gcd.GCDMain --help
  * }}}
  * To run with verilator:
  * {{{
  * test:runMain gcd.GCDMain --backend-name verilator
  * }}}
  * To run with verilator from your terminal shell use:
  * {{{
  * sbt 'test:runMain gcd.GCDMain --backend-name verilator'
  * }}}
  */
object ZeroMain extends App {
  iotesters.Driver.execute(args, () => new Zero(2)) {
    c => new ZeroUnitTester(c)
  }
}

/**
  * This provides a way to run the firrtl-interpreter REPL (or shell)
  * on the lowered firrtl generated by your circuit. You will be placed
  * in an interactive shell. This can be very helpful as a debugging
  * technique. Type help to see a list of commands.
  *
  * To run from sbt
  * {{{
  * test:runMain gcd.GCDRepl
  * }}}
  * To run from sbt and see the half a zillion options try
  * {{{
  * test:runMain gcd.GCDRepl --help
  * }}}
  */
object ZeroRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new Zero(2))
}