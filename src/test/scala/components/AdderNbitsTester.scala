package components

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}
import math.{pow}

class AdderNbitsTester(c: AdderNbits) extends PeekPokeTester(c) {
    private val adder = c
    val truncateToNbits = (x: Int, n: Int) => Integer.parseInt(x.toBinaryString.takeRight(n),2) 
    val getBit = (x: Int, n: Int) => Integer.parseInt(x.toBinaryString.reverse.padTo(adder.n_bit+1,"0").reverse.mkString.take(n+1),2)

    val maxNum = pow(2,adder.n_bit).toInt
    for (a <- 0 until maxNum){
        for (b <- 0 until maxNum){
            for (cin <- 0 until 2){
                poke(adder.io.inputA, a)
                poke(adder.io.inputB, b)
                poke(adder.io.cin, cin)
                step(1)
                
                //printf("a: %d, b: %d, cin: %d\n", a, b, cin)
                //printf("expected sum: %d, cout: %d\n\n", truncateToNbits(a + b + cin, adder.n_bit), getBit(a + b + cin,0))

                expect(adder.io.output, truncateToNbits(a + b + cin, adder.n_bit))
                expect(adder.io.cout, getBit(a + b + cin,0))
            }
        }
    }
}

object AdderNbitsMain extends App {
  iotesters.Driver.execute(args, () => new AdderNbits(4)){
      c => new AdderNbitsTester(c)
  }
}

object AdderNbitsRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new AdderNbits(16))
}