package components

import chisel3._
import chisel3.Driver

// This class is created only to help the AdderNbits class
class FullAdder extends Module {
    val io = IO( new Bundle {
        val inputA = Input(UInt(1.W))
        val inputB = Input(UInt(1.W))
        val cin    = Input(UInt(1.W))
        val output = Output(UInt(1.W))
        val cout   = Output(UInt(1.W))
    })

    val ha1 = Module (new HalfAdder)
    ha1.io.inputA := io.inputA
    ha1.io.inputB := io.inputB

    val ha2 = Module (new HalfAdder)
    ha2.io.inputA := ha1.io.output
    ha2.io.inputB := io.cin

    io.cout := ha1.io.cout | ha2.io.cout
    io.output := ha2.io.output
}

object FullAdder {
    def main(args: Array[String]): Unit = {
        chisel3.Driver.execute(args, () => new FullAdder)
    }
}