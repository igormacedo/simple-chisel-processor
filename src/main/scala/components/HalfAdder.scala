package components

import chisel3._
import chisel3.Driver

class HalfAdder extends Module {
    val io = IO( new Bundle {
        val inputA = Input(UInt(1.W))
        val inputB = Input(UInt(1.W))
        val output = Output(UInt(1.W))
        val cout   = Output(UInt(1.W))
    })

    io.output := io.inputA ^ io.inputB
    io.cout := io.inputA & io.inputB
}

object HalfAdder {
    def main(args: Array[String]): Unit = {
        chisel3.Driver.execute(args, () => new HalfAdder)
    }
}