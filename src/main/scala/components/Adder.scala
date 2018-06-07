package components

import chisel3._
import chisel3.Driver

class Adder(val n:Int) extends Module {
    val io = IO(new Bundle {
        val inputA = Input(UInt(n.W))
        val inputB = Input(UInt(n.W))
        val Cin    = Input(UInt(1.W))
        val Sum    = Output(UInt(n.W))
        val Cout   = Output(UInt(1.W))
    })

    io.Cout := 0.asUInt(1.W)
    io.Sum := 0.asUInt(n.W) //io.inputA + io.inputB
}

/**
 * An object containing a main() to generate the Verilog code.
 */
object Adder {
  def main(args: Array[String]): Unit = {
    chisel3.Driver.execute(args, () => new Adder(2)) 
  }
}