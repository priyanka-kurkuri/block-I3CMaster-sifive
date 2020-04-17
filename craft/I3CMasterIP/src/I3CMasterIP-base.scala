// Generated Code
// Please DO NOT EDIT


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



class I3CMasterIPBlackBoxIO(

) extends Bundle {
  val CLK = Input(Bool())
  val RESET = Input(Bool())
  val SDA = Output(Bool())
  val SCL = Output(Bool())
}

class I3CMasterIP(

) extends BlackBox(Map(

)) with HasBlackBoxResource {
  val io = IO(new I3CMasterIPBlackBoxIO(

  ))
// setResource("top.v")
}

case class I3CMasterIPParams(
  cacheBlockBytes: Int
)



class LI3CMasterIPBase(c: I3CMasterIPParams)(implicit p: Parameters) extends LazyModule {

  def extraResources(resources: ResourceBindings) = Map[String, Seq[ResourceValue]]()

  val device = new SimpleDevice("I3CMasterIP", Seq("sifive,I3CMasterIP-0.1.0")) {
    override def describe(resources: ResourceBindings): Description = {
      val Description(name, mapping) = super.describe(resources)
      Description(name, mapping ++ extraResources(resources))
    }
  }





  val ioBridgeSource = BundleBridgeSource(() => new I3CMasterIPBlackBoxIO(

  ))

  class LI3CMasterIPBaseImp extends LazyRawModuleImp(this) {
    val blackbox = Module(new I3CMasterIP(

    ))
    // interface wiring 2

    // port wiring
    blackbox.io.CLK := ioBridgeSource.bundle.CLK
    blackbox.io.RESET := ioBridgeSource.bundle.RESET
    ioBridgeSource.bundle.SDA := blackbox.io.SDA
    ioBridgeSource.bundle.SCL := blackbox.io.SCL
    // interface alias

    // interface wiring

  }
  lazy val module = new LI3CMasterIPBaseImp
}



case class NI3CMasterIPTopParams(
  blackbox: I3CMasterIPParams
) {
  def setBurstBytes(x: Int): NI3CMasterIPTopParams = this.copy()
}

object NI3CMasterIPTopParams {
  def defaults(
    cacheBlockBytes: Int
  ) = NI3CMasterIPTopParams(
    blackbox = I3CMasterIPParams(
      cacheBlockBytes = cacheBlockBytes
    )
  )
}


class NI3CMasterIPTopLogicalTreeNode(component: NI3CMasterIPTopBase) extends LogicalTreeNode(() => Some(component.imp.device)) {
  override def getOMComponents(resourceBindings: ResourceBindings, components: Seq[OMComponent]): Seq[OMComponent] = {
    val name = component.imp.device.describe(resourceBindings).name
    val omDevice = new scala.collection.mutable.LinkedHashMap[String, Any] with OMDevice {
      val memoryRegions = component.getOMMemoryRegions(resourceBindings)
      val interrupts = component.getOMInterrupts(resourceBindings)
      val _types: Seq[String] = Seq("OMI3CMasterIP", "OMDevice", "OMComponent", "OMCompoundType")
    }
    val userOM = component.userOM
    val values = userOM.productIterator
    if (values.nonEmpty) {
      val pairs = (userOM.getClass.getDeclaredFields.map { field =>
        assert(field.getName != "memoryRegions", "user Object Model must not define \"memoryRegions\"")
        assert(field.getName != "interrupts", "user Object Model must not define \"interrupts\"")
        assert(field.getName != "_types", "user Object Model must not define \"_types\"")

        field.getName -> values.next
      }).toSeq
      omDevice ++= pairs
    }
    omDevice("memoryRegions") = omDevice.memoryRegions
    omDevice("interrupts") = omDevice.interrupts
    omDevice("_types") = omDevice._types
    Seq(omDevice)
  }
}

class NI3CMasterIPTopBase(val c: NI3CMasterIPTopParams)(implicit p: Parameters)
 extends SimpleLazyModule
 with BindingScope
 with HasLogicalTreeNode {
  val imp = LazyModule(new LI3CMasterIP(c.blackbox))

  ResourceBinding { Resource(imp.device, "exists").bind(ResourceString("yes")) }

  def userOM: Product with Serializable = Nil

  private def prettyPrintField(field: RegField, bitOffset: Int): String = {
    val nameOpt = field.desc.map(_.name)
    nameOpt.map(_ + " ").getOrElse("") + s"at offset $bitOffset"
  }
  private def padFields(fields: (Int, RegField)*) = {
    var previousOffset = 0
    var previousFieldOpt: Option[RegField] = None

    fields.sortBy({ case (offset, _) => offset }).flatMap { case (fieldOffset, field) =>
      val padWidth = fieldOffset - (previousOffset + previousFieldOpt.map(_.width).getOrElse(0))
      val prettyField = prettyPrintField(field, fieldOffset)
      require(
        padWidth >= 0,
        previousFieldOpt.map(previousField => {
          val prettyPrevField = prettyPrintField(previousField, previousOffset)
          s"register fields $prettyPrevField and $prettyField are overlapping"
        }).getOrElse(
          s"register field $prettyField has a negative offset"
        )
      )

      previousOffset = fieldOffset
      previousFieldOpt = Some(field)

      if (padWidth > 0) {
        Seq(RegField(padWidth), field)
      } else {
        Seq(field)
      }
    }
  }

  def omRegisterMaps = Seq(
)

  def getOMMemoryRegions(resourceBindings: ResourceBindings): Seq[OMMemoryRegion] = {
    val name = imp.device.describe(resourceBindings).name
    DiplomaticObjectModelAddressing.getOMMemoryRegions(name, resourceBindings, None)
  }

  def getOMInterrupts(resourceBindings: ResourceBindings): Seq[OMInterrupt] = {
    val name = imp.device.describe(resourceBindings).name
    DiplomaticObjectModelAddressing.describeGlobalInterrupts(name, resourceBindings)
  }

  def logicalTreeNode: LogicalTreeNode = new NI3CMasterIPTopLogicalTreeNode(this)


// no channel node

}

object NI3CMasterIPTopBase {
  def attach(c: NI3CMasterIPTopParams)(bap: BlockAttachParams): NI3CMasterIPTop = {
    implicit val p: Parameters = bap.p
    val I3CMasterIP_top = LazyModule(new NI3CMasterIPTop(c))
    // no channel attachment

    LogicalModuleTree.add(bap.parentNode, I3CMasterIP_top.logicalTreeNode)
    I3CMasterIP_top
  }
}

class WithI3CMasterIPTopBase (

) extends Config((site, here, up) => {
  case BlockDescriptorKey =>
    BlockDescriptor(
      name = "I3CMasterIP",
      place = NI3CMasterIPTop.attach(NI3CMasterIPTopParams.defaults(
        cacheBlockBytes = site(CacheBlockBytes)
      ))
    ) +: up(BlockDescriptorKey, site)
})
