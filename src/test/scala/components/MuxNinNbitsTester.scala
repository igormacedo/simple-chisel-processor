package components

import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}

class MuxNinNbitsTester(c: MuxNinNbits) extends PeekPokeTester(c) {
    private val mux = c
    val truncateToNbits = (x: Int, n: Int) => Integer.parseInt(x.toBinaryString.takeRight(n),2)

    for (i<- 0 until mux.io.inputVec.length) {
        val input = truncateToNbits(i, mux.n_bits)
        poke(mux.io.inputVec(i),input)
    }

    for (i<- 0 until mux.io.inputVec.length) {
        poke(mux.io.select, i)
        step(1)
        val input = truncateToNbits(i, mux.n_bits)
        expect(mux.io.output, input)
    }

}

object MuxNinNbitsMain extends App {
  iotesters.Driver.execute(args, () => new MuxNinNbits(31,4)){
      c => new MuxNinNbitsTester(c)
  }
}

object MuxNinNbitsRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new MuxNinNbits(2,1))
}