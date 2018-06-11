package components

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}

class HalfAdderTester(c: HalfAdder) extends PeekPokeTester(c) {
    private val halfAdder = c

    for (a <- 0 until 2){
        for(b <- 0 until 2){
            poke(halfAdder.io.inputA, a)
            poke(halfAdder.io.inputB, b)
            step(1)
            expect(halfAdder.io.output, a ^ b)
            expect(halfAdder.io.cout, a & b)
        }
    }
}

object HalfAdderMain extends App {
    iotesters.Driver.execute(args, () => new HalfAdder) {
        c => new HalfAdderTester(c)
    }
}

object HalfAdderRepl extends App {
    iotesters.Driver.executeFirrtlRepl(args, () => new HalfAdder)
}