package singlecycle

import chisel3._
import chisel3.util._

class regfile extends Module {
  val io = IO (new Bundle {
	val RegWrite = Input(Bool())
	val rs1 = Input(UInt(5.W))
	val rs2 = Input(UInt(5.W))
	val rd = Input(UInt(5.W))
	val WriteData = Input(SInt(32.W))
	val rdata1 = Output(SInt(32.W))
	val rdata2 = Output(SInt(32.W))
  })
	val register = Reg(Vec(32,SInt(32.W)))
	register(0) := 0.S
	io.rdata1 := register(io.rs1)
	io.rdata2 := register(io.rs2)
	when(io.RegWrite === 1.B){
		when(io.rd =/= "b00000".U){register(io.rd) := io.WriteData}
		.otherwise {register(io.rd) := 0.S}
	}
}