import argparse
import time
import helper.adb as adb

available_apps = [
    {
        "name": "chrome",
        "implicit": True,
        "data": "https://contactapp.stefanhuber.at"
    },
    {
        "name": "native",
        "implicit": False,
        "pkg": "at.stefanhuber.contactappnative",
        "activity": "MainActivity"
    },
    {
        "name": "react",
        "implicit": False,
        "pkg": "com.contactappreactnative",
        "activity": "MainActivity"
    },
    {
        "name": "capacitor",
        "implicit": False,
        "pkg": "at.stefanhuber.contactappcapacitor",
        "activity": "MainActivity"
    },
    {
        "name": "flutter",
        "implicit": False,
        "pkg": "com.thomasdorfer.contactappflutter",
        "activity": "MainActivity"
    }
]
apps = []

parser = argparse.ArgumentParser(description='Android Battery Test')
parser.add_argument('-i', '--ip', type=str, help='IP-Address of adb-connected Android device', default="")
parser.add_argument('-p', '--port', type=str, help='Port of adb-connected Android device', default="7777")
parser.add_argument('-n', '--count', type=int, help='Number of executions of a test per app', default=1)
parser.add_argument('-a', '--apps', type=str, help='Comma separated list of apps to execute', default='chrome,native,capacitor,react,flutter')
parser.add_argument('-s', '--start', type=int, help='Start index of test', default=1)

args = parser.parse_args()
device = adb.get_connected_device(args.ip, args.port)
count = args.count
app_names = args.apps.split(",")
start = args.start

for app_name in app_names:
    for app in available_apps:
        if app_name == app["name"]:
            apps.append(app)
            break

# interaction: interaction class name, energy metering True/False

interactions = [
    ["OpenCloseDrawerInteractionTest", True],
    ["ScrollDownListInteractionTest", True],
    ["ScrollUpListInteractionTest", True],
    ["SwitchScreensInteractionTest", True],
    ["TopRightMenuInteractionTest", False],
    ["EnterFormDataInteractionTest", True],
    ["BackMenuInteractionTest", False],
]

for n in range(start, start + count):
    print("start run: {} of {}".format(n, (start + count - 1)))

    for app in apps:
        adb.kill_all()

        if app["implicit"]:
            adb.start_app_implicit(app["data"])
        else:
            adb.start_app(app["pkg"], app["activity"])

        time.sleep(15)

        for interaction in interactions:

            if interaction[1]:
                adb.clear_batterystats()

            adb.start_instrumentation("at.stefanhuber.instrumentation", interaction[0])

            if interaction[1]:
                adb.dump_batterystats()
                adb.pull_data("/data/local/tmp/battery.txt", "./data/battery_{}_{}_{}_{}.txt".format(device, app["name"], interaction[0], n))

        adb.start_instrumentation("at.stefanhuber.instrumentation", "CloseAllAppsInteractionTest")