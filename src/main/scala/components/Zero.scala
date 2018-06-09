package components

import chisel3._
import chisel3.Driver

class Zero(val n:Int) extends Module {
    val io = IO(new Bundle {
        val inputA = Input(UInt(n.W))
        val Cout   = Output(UInt(n.W))
    })

    printf("\ninput = %d\n\n", io.inputA)
    io.Cout := 0.asUInt(n.W)
}

/**
 * An object containing a main() to generate the Verilog code.
 */
object Zero {
  def main(args: Array[String]): Unit = {
    chisel3.Driver.execute(args, () => new Zero(2)) 
  }
}