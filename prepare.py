import helper.adb as adb
import os
import re

pkgs = [
    "at.stefanhuber.instrumentation",
    "at.stefanhuber.contactappnative",
    "at.stefanhuber.contactappcapacitor",
    "com.contactappreactnative",
    "com.thomasdorfer.contactappflutter"
]

for pkg in pkgs:
    try:
        adb.uninstall_apk(pkg)
    except:
        print("cannot uninstall pkg: {}".format(pkg))

files = os.listdir("./apks")
for file in files:
    if re.match("^.*?\.apk", file):
        adb.install_apk("./apks/{}".format(file))
