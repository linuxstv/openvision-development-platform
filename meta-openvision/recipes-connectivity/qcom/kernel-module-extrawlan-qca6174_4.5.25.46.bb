DESCRIPTION = "qcacld-2.0 module.bbclass mechanism."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://Android.mk;md5=235cc8d87e0fb1c956be4af0d07074fb"
CAF_MIRROR = "https://www.codeaurora.org/cgit/external/wlan"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit module machine_kernel_pr

SRC_URI = "${CAF_MIRROR}/qcacld-2.0/snapshot/qcacld-2.0-${PV}.tar.gz \
	file://qcacld-2.0-support.patch \
	${@bb.utils.contains_any("MACHINE", "spycat4k spycat4kcombo spycat4kmini", "file://qcacld-2.0-support-xc7439.patch", "", d)} \
	"

SRC_URI[md5sum] = "90a83aacaffc48e6a3eefbe70ef7ebd1"
SRC_URI[sha256sum] = "b7ed5214c7be2229b69c8925de415437d07796ec9d7c9a15b85337da6e023dc0"

#EXTRA_OEMAKE_append = " ${@bb.utils.contains_any("MACHINE", "spycat4k spycat4kcombo spycat4kmini", " CONFIG_CLD_HL_SDIO_CORE=y", "", d)}"

S = "${WORKDIR}/qcacld-2.0-${PV}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0644 ${S}/wlan.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
}

python do_package_prepend() {
    d.appendVar('PKGV', '-')
    d.appendVar('PKGV', d.getVar("KERNEL_VERSION", True).split("-")[0])
}
