package components

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AdderUnitTester(c: Adder) extends PeekPokeTester(c) {
    private val adder = c
    poke(adder.io.inputA, 1)
    poke(adder.io.inputB, 2)
    poke(adder.io.Cin,    1)

    step(1)

    expect(adder.io.Sum,  3)
    expect(adder.io.Cout, 0)

}

class AdderTester extends ChiselFlatSpec {
    "Basic test using Driver.execute" should "be used as an alternative way to run specification" in {
        iotesters.Driver.execute(Array(), () => new Adder(2)) {
        c => new AdderUnitTester(c)
        } should be (true)
    }
}