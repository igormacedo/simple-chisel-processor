package components

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}

class Mux1bitTester(c: Mux1bit) extends PeekPokeTester(c) {
  private val mux = c
  
  for (i<- 0 until 2) {
    for (j<- 0 until 2){

      poke(mux.io.inputA, i)
      poke(mux.io.inputB, j)

      for (s<- 0 until 2){
        poke(mux.io.select, s)
        step(1)

        printf("A = %d\n", i)
        printf("B = %d\n", j)
        printf("sel = %d\n", s)
        printf("out = %d\n\n", peek(mux.io.output))
        
        expect(mux.io.output, if (s == 1) i else j)
      }
    }
  }
}

object Mux1bitMain extends App {
  iotesters.Driver.execute(args, () => new Mux1bit){
      c => new Mux1bitTester(c)
  }
}

object Mux1bitRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new Mux1bit)
}