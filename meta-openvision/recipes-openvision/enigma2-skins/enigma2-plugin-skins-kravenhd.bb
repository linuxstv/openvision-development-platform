SUMMARY = "Kraven HD skin for Enigma2"
MAINTAINER = "Team Kraven"
SECTION = "misc"
PRIORITY = "optional"
LICENSE = "CLOSED"

RDEPENDS_${PN} += "${@bb.utils.contains("PYTHONEXACTVERSION", "python3", "python3-pillow", "python-imaging python-subprocess", d)} ${PYTHONNAMEONLY}-requests ${PYTHONNAMEONLY}-lxml enigma2-plugin-systemplugins-mphelp"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/KravenHD/KravenHD.git;protocol=git"

S = "${WORKDIR}/git"

FILES_${PN} = "${prefix}/* ${sysconfdir}/*"

require skin-data.inc
require skin-python.inc
