package components

import chisel3._
import chisel3.Driver

class AdderNbits(val n_bit: Int) extends Module {
    val io = IO( new Bundle {
        val inputA = Input(UInt(n_bit.W))
        val inputB = Input(UInt(n_bit.W))
        val cin    = Input(UInt(  1.W  ))
        val output = Output(UInt(n_bit.W))
        val cout   = Output(UInt(  1.W  ))
    })

    val fulladders = VecInit(Seq.fill(n_bit){ Module(new FullAdder).io })
    
    val carry = Wire(Vec(n_bit+1, UInt(1.W)))
    val sum   = Wire(Vec(n_bit, Bool()))

    carry(0) := io.cin

    for (i<- 0 until n_bit){
        fulladders(i).inputA := io.inputA(i)
        fulladders(i).inputB := io.inputB(i)
        fulladders(i).cin    := carry(i)
        carry(i+1)           := fulladders(i).cout
        sum(i)               := fulladders(i).output.toBool()
    }

    io.output := sum.asUInt
    io.cout   := carry(n_bit)
}

object AdderNbits {
    def main(args: Array[String]): Unit = {
        chisel3.Driver.execute(args, () => new AdderNbits(8))
    }
}