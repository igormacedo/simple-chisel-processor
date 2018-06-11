package components

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}

class FullAdderTester(c: FullAdder) extends PeekPokeTester(c) {

    private val adder = c
    val truncateToNbits = (x: Int, n: Int) => Integer.parseInt(x.toBinaryString.takeRight(n),2) 
    val getBit = (x: Int, n: Int) => Integer.parseInt(x.toBinaryString.reverse.padTo(2,"0").reverse.mkString.take(n+1),2)
    
    for (a <- 0 until 2){
        for (b <- 0 until 2){
            for (cin <- 0 until 2){
                poke(adder.io.inputA, a)
                poke(adder.io.inputB, b)
                poke(adder.io.cin, cin)
                step(1)
                printf("a: %d, b: %d, cin: %d\n",a,b,cin)
                printf("expected sum: %d, cout: %d\n\n", truncateToNbits(a + b + cin, 1), getBit(a+b+cin,0))
                expect(adder.io.output, truncateToNbits(a + b + cin, 1))
                expect(adder.io.cout, getBit(a+b+cin,0))
            }
        }
    }
}

object FullAdderMain extends App {
  iotesters.Driver.execute(args, () => new FullAdder){
      c => new FullAdderTester(c)
  }
}

object FullAdderRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new FullAdder)
}