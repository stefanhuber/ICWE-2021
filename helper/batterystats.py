import re
import os
import statistics


def findUid(app, result):
    if app == "chrome" and result["top_pkg"] == "com.android.chrome":
        return result["top_uid"]
    elif app == "firefox" and result["top_pkg"] == "org.mozilla.firefox":
        return result["top_uid"]
    elif app == "native" and result["top_pkg"] == "at.stefanhuber.contactappnative":
        return result["top_uid"]
    elif app == "capacitor" and result["top_pkg"] == "at.stefanhuber.contactappcapacitor":
        return result["top_uid"]
    elif app == "react" and result["top_pkg"] == "com.contactappreactnative":
        return result["top_uid"]
    elif app == "flutter" and result["top_pkg"] == "com.thomasdorfer.contactappflutter":
        return result["top_uid"]
    else:
        return None


def append_alternative_result(device, app, interaction, result, data):
    if device not in data.keys():
        data[device] = {}
    if app not in data[device].keys():
        data[device][app] = {}
    if interaction not in data[device][app].keys():
        data[device][app][interaction] = {"app": [], "app_joule": []}

    value = result['total_drain'] # - result['system_processes']['Screen']["mAh"]

    value_Wh = float(value) * float(result["volt"]) / 1000000.0
    value_joule = value_Wh * 3600.0

    data[device][app][interaction]["app"].append(
        value
    )
    data[device][app][interaction]["app_joule"].append(
        value_joule
    )



def append_result(device, app, interaction, result, data):
    uid = findUid(app, result)

    if uid:
        value = float(result["processes"][uid]["mAh"]) # value in mAh

        if value > 0.001:
            if device not in data.keys():
                data[device] = {}
            if app not in data[device].keys():
                data[device][app] = {}
            if interaction not in data[device][app].keys():
                data[device][app][interaction] = {"test": [], "app": [], "test_joule": [], "app_joule": []}


            value_Wh = float(value) * float(result["volt"]) / 1000000.0
            value_joule = value_Wh * 3600.0

            data[device][app][interaction]["app"].append(
                value
            )
            data[device][app][interaction]["app_joule"].append(
                value_joule
            )

            test_value = float(result["processes"][result["mapping"]["at.stefanhuber.instrumentation"]]["mAh"])
            test_value_Wh = float(test_value) * float(result["volt"]) / 1000000.0
            test_value_joule = test_value_Wh * 3600.0

            data[device][app][interaction]["test"].append(
                test_value
            )
            data[device][app][interaction]["test_joule"].append(
                test_value_joule
            )


def reformat_data(result):
    data = {}
    relevant_interactions = ["OpenCloseDrawerInteractionTest", "ScrollDownListInteractionTest",
                             "SwitchScreensInteractionTest", "EnterFormDataInteractionTest"]
    interaction_names = {
        "OpenCloseDrawerInteractionTest": "Open/close drawer (i1)",
        "ScrollDownListInteractionTest": "List scrolling (i2)",
        "SwitchScreensInteractionTest": "Switch screens (i3)",
        "EnterFormDataInteractionTest": "Enter form data (i4)",
    }
    app_names = {
        "firefox": "PWA (firefox)",
        "chrome": "PWA (chrome)",
        "react": "React Native",
        "flutter": "Flutter",
        "native": "Android native",
        "capacitor": "Capacitor"
    }

    for device, apps in result.items():
        for app, interactions in apps.items():
            for interaction, values in interactions.items():
                if interaction not in relevant_interactions:
                    continue

                if "{}_{}".format(device, interaction) not in data.keys():
                    data["{}_{}".format(device, interaction)] = {
                        "data": [],
                        "app": [],
                        "interaction": interaction_names[interaction],
                        "device": device
                    }
                data["{}_{}".format(device, interaction)]["data"].append(values["app"])
                data["{}_{}".format(device, interaction)]["app"].append(app_names[app])

    return data


def parse_files(dir="./data"):
    data = {}
    files = os.listdir(dir)

    for file in files:
        if re.match("^battery_", file):
            m = re.match("^battery_([A-Za-z0-9\-]+)_([a-z]+)_([A-Za-z]+)_(\d+)\.txt", file)
            res = parse_file(dir + "/" + file)
            append_result(m.group(1), m.group(2), m.group(3), res, data)

    return data


def parse_file(f):
    result = {
        "processes": {},
        "system_processes": {},
        "total_drain": 0,
        "mapping": {},
        "top_uid": "",
        "top_pkg": "",
        "volt": []
    }

    # print("parse file: " + f)

    with open(f, "rt") as file:
        state = "i"
        pid = ""

        for line in file:
            if state == "i" and re.match("^$", line):
                state = ""
                continue
            elif state == "i":
                m = re.match('^ +[0-9+msh]+ \([0-9]+\) [0-9]{3,} top=([a-z0-9]+):"([a-zA-Z0-9.]+)"', line)
                if m:
                    result["top_uid"] = m.group(1)
                    result["top_pkg"] = m.group(2)

                m = re.match('^ +[0-9+msh]+ \([0-9]+\) [0-9]{3,} .*?volt=(\d+)', line)
                if m:
                    result["volt"].append(float(m.group(1)))

                continue
            elif state == "e" and re.match("^$", line):
                state = "p"
                continue
            elif line.find("Estimated power use (mAh):") > 0:
                state = "e"
                continue

            if state == "e": # estimaed power use
                m = re.match("^ +.*?drain: ([0-9.]+),.*", line)
                if m:
                    result['total_drain'] = float(m.group(1))

                m = re.match("^ +Uid ([a-z0-9]+): ([0-9.]+).*", line)
                if m:
                    result["processes"][m.group(1)] = {
                        "mAh": float(m.group(2)),
                    }

                m = re.match("^ +([a-zA-Z ]+): ([0-9.]+).*", line)
                if m:
                    result["system_processes"][m.group(1)] = {
                        "mAh": float(m.group(2)),
                    }


            if state == "p": # processes
                m = re.match("^  ([a-z0-9]+):$", line)
                p = re.match("^ +Proc (.*?):", line)
                if m:
                    pid = m.group(1)
                elif p:
                    result["mapping"][p.group(1)] = pid

        result["volt"] = statistics.mean(result["volt"])

    return result




