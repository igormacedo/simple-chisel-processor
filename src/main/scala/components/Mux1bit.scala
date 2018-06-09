package components

import chisel3._
import chisel3.Driver

class Mux1bit extends Module {
    val io = IO(new Bundle {
        val inputA = Input(UInt(1.W))
        val inputB = Input(UInt(1.W))
        val select = Input(UInt(1.W))
        val output = Output(UInt(1.W))
    })

    when (io.select === 1.asUInt(1.W)) {
        io.output := io.inputA 
    } .otherwise {
        io.output := io.inputB
    }
}

object Mux1bit {
    def main(args: Array[String]): Unit = {
        chisel3.Driver.execute(args, () => new Mux1bit)
    }
}