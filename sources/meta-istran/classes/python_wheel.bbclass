# Install a prebuilt Python wheel into site-packages (no pip).
inherit python3native

# Each recipe must set the exact wheel filename:
#   PY_WHEEL_FILE = "package-<ver>-<tags>.whl"
PY_WHEEL_FILE ??= ""

python do_install() {
    import os, zipfile, shutil, stat

    dget = d.getVar
    whl_file = dget("PY_WHEEL_FILE")
    if not whl_file:
        bb.fatal("PY_WHEEL_FILE not set")

    workdir  = dget("WORKDIR")
    destdir  = dget("D")
    site_rel = dget("PYTHON_SITEPACKAGES_DIR").lstrip("/")
    bin_rel  = dget("bindir").lstrip("/")

    whl_path = os.path.join(workdir, whl_file)
    site = os.path.join(destdir, site_rel)
    bindir = os.path.join(destdir, bin_rel)
    tmp = os.path.join(workdir, "_whl_unpack")

    os.makedirs(site, exist_ok=True)
    os.makedirs(bindir, exist_ok=True)
    if os.path.isdir(tmp):
        shutil.rmtree(tmp)
    os.makedirs(tmp, exist_ok=True)

    with zipfile.ZipFile(whl_path) as z:
        z.extractall(tmp)

    # Move top-level payload (excluding .data) into site-packages
    entries = sorted(os.listdir(tmp))
    data_dirname = None
    for name in entries:
        src = os.path.join(tmp, name)
        if name.endswith(".data"):
            data_dirname = name
            continue
        shutil.move(src, os.path.join(site, name))

    # Handle .data contents per PEP 427
    if data_dirname:
        data_dir = os.path.join(tmp, data_dirname)
        purelib = os.path.join(data_dir, "purelib")
        platlib = os.path.join(data_dir, "platlib")
        scripts = os.path.join(data_dir, "scripts")

        for part in (purelib, platlib):
            if os.path.isdir(part):
                for name in os.listdir(part):
                    shutil.move(os.path.join(part, name), os.path.join(site, name))

        if os.path.isdir(scripts):
            for name in os.listdir(scripts):
                src = os.path.join(scripts, name)
                dst = os.path.join(bindir, name)
                shutil.move(src, dst)
                os.chmod(dst, os.stat(dst).st_mode | stat.S_IXUSR | stat.S_IXGRP | stat.S_IXOTH)
}

FILES:${PN} += " ${PYTHON_SITEPACKAGES_DIR} ${bindir} "
RDEPENDS:${PN} += "python3-core"

# Prebuilt wheels can trip QA; relax common nags
INSANE_SKIP:${PN} += "already-stripped ldflags textrel dev-so"
