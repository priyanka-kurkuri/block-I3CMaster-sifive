// USER editable file


package sifive.blocks.I3CMasterIP

import chisel3._
// import chisel3.{withClockAndReset, _}
import chisel3.util._
import chisel3.experimental._

import freechips.rocketchip.config._
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.diplomaticobjectmodel.{DiplomaticObjectModelAddressing, HasLogicalTreeNode}
import freechips.rocketchip.diplomaticobjectmodel.logicaltree.{LogicalTreeNode, LogicalModuleTree}
import freechips.rocketchip.diplomaticobjectmodel.model._
import freechips.rocketchip.amba.axi4._
import freechips.rocketchip.amba.apb._
import freechips.rocketchip.amba.ahb._
import freechips.rocketchip.interrupts._
import freechips.rocketchip.util.{ElaborationArtefacts}
import freechips.rocketchip.tilelink._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.regmapper._

import sifive.skeleton._
import sifive.blocks.util.{NonBlockingEnqueue, NonBlockingDequeue}



class LI3CMasterIP(c: I3CMasterIPParams)(implicit p: Parameters) extends LI3CMasterIPBase(c)(p)
{

// User code here

}

class NI3CMasterIPTop(c: NI3CMasterIPTopParams)(implicit p: Parameters) extends NI3CMasterIPTopBase(c)(p)
{

// User code here

}

object NI3CMasterIPTop {
  def attach(c: NI3CMasterIPTopParams)(bap: BlockAttachParams): NI3CMasterIPTop = {
    val I3CMasterIP = NI3CMasterIPTopBase.attach(c)(bap)

    // User code here

    I3CMasterIP
  }
}

class WithI3CMasterIPTop extends Config(
  new WithI3CMasterIPTopBase(

  )

    // User code here
)
