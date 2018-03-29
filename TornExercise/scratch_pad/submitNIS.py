# Tested with Request 2.0.1
# Tested with Python 3.3

from __future__ import print_function
from __future__ import absolute_import
from __future__ import division

import requests as http_request
from bs4 import BeautifulSoup as BS
import time

import re
import sys
import os as ospackage

import json
from collections import OrderedDict

import fileinput
import getpass

import hashlib



def SwitchParameterSetFlag(flag, params):
    if flag is not None:
        print("Only one instance of commandline option is allowed: " + params[0])
        print(help_msg)
        sys.exit(2)
    else:
        return params[1].lower() == 'on'


def HandleOptionAttach(values):
    global batch_attach
    batch_attach = 1


def HandleOptionNewBatch(values):
    global batch_attach
    batch_attach = -1


def HandleOptionBatchname(values):
    global batch_name
    batch_name = OneParameterSetFlag(batch_name, values)


def HandleOptionDelete(values):
    global delete_switch
    delete_switch = True


def HandleOptionNIC(values):
    global setNIC
    global test_tags
    global enableNIC
    setNIC = True
    test_tags.append("NoNetwork")
    enableNIC = SwitchParameterSetFlag(enableNIC, values)


def HandleOptionXPerf(values):
    global enableXperf
    global status_active
    status_active = False
    enableXperf = SwitchParameterSetFlag(enableXperf, values)


def HandleOptionWPP(values):
    global enableWPP
    global status_active
    status_active = False
    enableWPP = SwitchParameterSetFlag(enableWPP, values)


def ListParameterSetFlag(flag, list, params):
    if flag:
        print("Only one instance of commandline option is allowed: " + params[0])
        print(help_msg)
        sys.exit(2)
    else:
        for i in range(1, len(params)):
            list.append(params[i])
        return True


def HandleOptionOS(values):
    global specify_os
    global os_list
    specify_os = ListParameterSetFlag(specify_os, os_list, values)
    for i in range(1, len(os_list)):
        if os_list[i] not in osType:
            print("Invalid OS option: " + os_list[i])
            print("Valid OS options are: " + str(osType)\
                    .replace("'", "").replace("[", "").replace("]", ""))
            print(help_msg)
            sys.exit(2)


def HandleOptionTests(values):
    global specify_testname
    global test_name
    global test_name_copy
    global run_on_backup
    global backup_override
    if not backup_override:
        run_on_backup = False
    specify_testname = ListParameterSetFlag(specify_testname, test_name, values)
    test_name_copy = list(test_name)


def HandleOptionMachine(values):
    global specify_machine
    global machine_list
    specify_machine = ListParameterSetFlag(specify_machine, machine_list, values)


def OneParameterSetFlag(flag, params):
    if flag is not None:
        print("Only one instance of commandline option is allowed: " + params[0])
        print(help_msg)
        sys.exit(2)
    else:
        return params[1]


def HandleOptionBat(values):
    global bat_param
    global status_active
    status_active = False
    bat_param = OneParameterSetFlag(bat_param, values)


def HandleOptionPreTestBat(values):
    global pretest_at_bats
    global pretest_adk_bats
    global status_active
    status_active = False
    temp_bat = OneParameterSetFlag(None, values)
    pretest_at_bats.append(temp_bat)
    temp_bat = OneParameterSetFlag(None, values)
    pretest_adk_bats.append(temp_bat)


def HandleOptionManualParameter(values):
    global m_param
    m_param = OneParameterSetFlag(m_param, values)


def HandleOptionUseMachine(values):
    global machine_param
    machine_param = OneParameterSetFlag(machine_param, values)


def HandleOptionManual(values):
    global manual_param
    global status_active
    status_active = False
    manual_param = OneParameterSetFlag(manual_param, values)


def HandleOptionNotes(values):
    global notes_param
    global status_active
    status_active = False
    notes_param = OneParameterSetFlag(notes_param, values)


def HandleOptionEnv(values):
    global env_param
    global status_active
    status_active = False
    env_param = OneParameterSetFlag(env_param, values)


def NoParameterSetFlag(flag, param):
    if flag:
        print("Only one instance of commandline option is allowed: " + param)
        print(help_msg)
        sys.exit(2)
    else:
        return True


def HandleOptionBypass(values):
    global bypass_switch
    bypass_switch = NoParameterSetFlag(bypass_switch, values[0])


def HandleOptionAlt(values):
    global alt_flag
    alt_flag = NoParameterSetFlag(alt_flag, values[0])


def HandleOptionSpecial(values):
    global special_flag
    global status_active
    status_active = False
    special_flag = NoParameterSetFlag(special_flag, values[0])


def HandleOptionHighPriority(values):
    global priority
    global priority_flag
    priority = 'High'
    priority_flag = NoParameterSetFlag(priority_flag, values[0])


def HandleOptionADK(values):
    global ADK_flag
    ADK_flag = NoParameterSetFlag(ADK_flag, values[0])


def HandleOptionAT(values):
    global AT_flag
    AT_flag = NoParameterSetFlag(AT_flag, values[0])


def HandleOptionForce(values):
    global force_flag
    force_flag = NoParameterSetFlag(force_flag, values[0])


def HandleOptionCreateDependencies(values):
    global create_dependencies_flag
    create_dependencies_flag = NoParameterSetFlag(create_dependencies_flag, values[0])


def HandleOptionUpdate(values):
    global update_flag
    update_flag = OneParameterSetFlag(None, values)


def HandleOptionWorkingSet(values):
    global bat_param
    global test_tags
    global pretest_at_bats
    global pretest_adk_bats
    pretest_at_bats.append("WorkingSetDisable.bat")
    pretest_adk_bats.append("WorkingSetDisable.bat")
    test_tags.append("WorkingSet")


def HandleOptionNoAddOns(values):
    global test_tags
    test_tags.append("NoAddOns")


def HandleOptionIterations(values):
    global iter_param
    iter_param = int(OneParameterSetFlag(iter_param, values))


def setAllContexts():
    global contextEnum
    global contexts
    global all_test
    global build_number
    all_test = True
    if build_number == '0.0.0.0':
        contexts = ['Baseline']
    else:
        contexts = ['N360', 'Product']


def HandleOptionContext(values):
    global contexts
    global contextEnum
    global help_msg
    contexts = [OneParameterSetFlag(contexts, values)]
    if contexts[0] not in contextEnum:
        if contexts[0].lower() == 'all':
            setAllContexts()
        else:
            print("Invalid Context. Valid Contexts are: " + \
                    str(contextEnum).replace("'", "").replace("[", "").replace("]", ""))
            print(help_msg)
            sys.exit(2)


def HandleOptionAppendBats(values):
    global append_bats
    global specify_append_bats
    global status_active
    status_active = False
    ListParameterSetFlag(specify_append_bats, append_bats, values)


def HandleOptionIU(values):
    global iu_name
    global iu_server
    global adk_requiresNIC

    adk_requiresNIC = True
    iu_name = OneParameterSetFlag(iu_name, values)
    #available_iu_defs = ospackage.listdir(iu_server)
    #available_iu_defs = list(set(map(lambda x: ".".join(str(x).split(".")[1:-1]),\
    #                        available_iu_defs)))
    #if iu_name not in available_iu_defs:
    #    print("Invalid IU test tag. Valid tags are: " + str(available_iu_defs))
    #    print(help_msg)
    #    sys.exit(2)


def HandleOptionCore(values):
    global coredefs
    coredefs = True


def HandleOptionDefender(values):
    global append_bats
    global test_tags
    global defender_flag
    global osType
    defender_flag = True
    append_bats.append('WDefenderOn.bat')
    test_tags.append("Defender")
    osType = ['Win10x64', 'Win10x86']


def HandleOptionNoOneDrive(values):
    global append_bats
    global test_tags
    append_bats.append('OneDrive.bat')
    test_tags.append("NoOneDrive")

def HandleOptionInstaller(values):
    global installer_file
    global installer_build_number
    global status_active
    status_active = False
    installer_file = OneParameterSetFlag(installer_file, values)
    file_name = installer_file.split('\\')[-1]
    installer_build_number = '.'.join(file_name.split('-')[-1].split('.')[:-1])


def HandleOptionCustom(values):
    global customInstallation
    customInstallation = True


def HandleOptionCopyCSDKAdapter(values):
    global pretest_at_bats
    global pretest_adk_bats
    global status_active
    temp_bat = OneParameterSetFlag(None, values)
    pretest_at_bats.append(temp_bat)
    temp_bat = OneParameterSetFlag(None, values)
    pretest_adk_bats.append(temp_bat)
    status_active = False
    filename = temp_bat
    major, minor, patch, build = build_number.split('.')[0:5]
    if minor == '8' and patch == '0':
        version = major + '.' + minor
    else:
        version = major + '.' + minor + '.' + patch
    for line in fileinput.input(filename, inplace=1):
        if 'set ver' in line:
            line = re.sub("set ver=.*", "set ver={0}".format(version), line)
        elif 'set build' in line:
            line = re.sub("set build=.*", "set build={0}".format(build), line)
        print(line, end="")


def HandleOptionFast(values):
    global fast_flag
    global backup_override
    global run_on_backup
    fast_flag = True
    if not backup_override:
        if str(values[0]).upper() == 'ON':
            run_on_backup = True
        else:
            run_on_backup = False
    else:
        run_on_backup = False


def HandleOptionOldServer(values):
    global database_server
    database_server = "http://cps.qalabs.symantec.com/teams/Global%20Performance%20Unit"


def HandleOptionNetSync(values):
    global net_loc
    global status_active
    status_active = False
    net_loc = OneParameterSetFlag(net_loc, values)


def HandleOptionNetSyncCred(values):
    global netsync_cred
    netsync_cred = OneParameterSetFlag(netsync_cred, values)


def HandleOptionInstallerName(values):
    global installer_name
    global status_active
    global custom_installer_name
    status_active = False
    custom_installer_name = True
    installer_name = OneParameterSetFlag(installer_name, values)


def HandleOptionCosint(values):
    global installer_name
    global env_param
    test_tags.append("cosint")
    env_param = "cosint"
    # Use cosint layout for 22.10 and above
    major, minor = build_number.split('.')[0:2]
    if (int(major) == 22 and int(minor) >= 10) or int(major) > 22:
        installer_name = "{Product}-ESD30D-CosInt-Def-{Build}.exe"


def HandleOptionSyncNorton(values):
    global sync_prod_loc
    global status_active
    status_active = False
    sync_prod_loc = OneParameterSetFlag(sync_prod_loc, values)


def HandleOptionDebug(values):
    global debug_flag
    debug_flag = True


def HandleOptionBackupOverride(values):
    global run_on_backup
    global backup_override
    if not backup_override:
        backup_override = True
        run_on_backup = True


def HandleOptionNoBackupOverride(values):
    global run_on_backup
    global backup_override
    if not backup_override:
        backup_override = True
        run_on_backup = False


def HandleOptionNCW(values):
    global test_tags
    global append_at_bats
    global append_adk_bats
    test_tags.append("NCW")
    append_at_bats.append("EnableNCW.bat")
    append_adk_bats.append("ADK_enableNCW.bat")


def HandleOptionPrep(values):
    global prepare_php
    folder = OneParameterSetFlag(None, values)
    prepare_php = '/' + folder + '/dbupdate/Prepare.php'


def HandleOptionLiveUpdate(values):
    global test_tags
    global liveupdate
    test_tags.append("LU")
    liveupdate = True


def HandleOptionESD(values):
    global installer_name
    global custom_installer_name
    installer_name = '{Product}-ESD-{Build}.exe'
    test_tags.append("ESD")


def HandleOptionTestIteration(values):
    global test_iteration
    global status_active
    status_active = False
    test_iteration = OneParameterSetFlag(test_iteration, values)


def HandleOptionWindowsUpdate(values):
    global test_tags
    global pretest_at_bats
    global pretest_adk_bats
    global wuid
    wuid = OneParameterSetFlag(wuid, values)
    batch_file = "installwu" + wuid + ".bat"
    append_at_bats.append(batch_file)
    append_adk_bats.append(batch_file)
    test_tags.append("wu" + wuid)
    os_list.append("Win10x64")


def handle_option_skip_os(values):
    global skip_os_list
    if len(values) < 2:
        print("Invalid option for /skipos\n", help_msg)
        sys.exit(2)
    skip_os_list = values[1:]


def handle_option_skip_test(values):
    global skip_tests
    if len(values) < 2:
        print("Invalid option for /skiptest\n", help_msg)
        sys.exit(2)
    skip_tests = values[1:]


def handle_option_skip_machine(values):
    global skip_machine
    if len(values) < 2:
        print("Invalid option for /skipmachine\n", help_msg)
        sys.exit(2)
    skip_machine = values[1:]


def reset_iterations():
    global test_iteration
    global status_active
    status_active, test_iteration = True, None


# Array of commandline options: array(option_name, call_method, param_num, option_info)
CommandLineOptions = \
    [\
        ["/delete", HandleOptionDelete, 1, "Delete requests for a specified user"], \
        ["/NIC", HandleOptionNIC, 1, "Switch NIC on or off"], \
        ["/xperf", HandleOptionXPerf, 1, "Switch xperf on or off"], \
        ["/wpp", HandleOptionWPP, 1, "Switch wpp on or off"], \
        ["/os", HandleOptionOS, -1, "Perform only the tests which support the " +\
                "specified os"], \
        ["/test", HandleOptionTests, -1, "Perform only the specified tests"], \
        ["/attach", HandleOptionAttach, 0, "Attach requests to existing batch" +\
                " even if more than one hour old"], \
        ["/newb", HandleOptionNewBatch, 0, "Create a new batch, do not attach " +\
                "to an existing batch"], \
        ["/bname", HandleOptionBatchname, 0, "Set the batch name (overriding " +\
                "any auto-generated name)"], \
        ["/high", HandleOptionHighPriority, 0, "Make test high priority"], \
        ["/machine", HandleOptionMachine, -1, "Perform only the tests on the " +\
                "specified machine"], \
        ["/manual", HandleOptionManual, 1, "Add manual parameter to all" +\
                " listed tests"], \
        ["/m", HandleOptionManualParameter, 1, "Specify where tests with" +\
                " manual parameter get listed"], \
        ["/usemachine", HandleOptionUseMachine, 1, "Specify a machine on " +\
                "which all listed tests will be run"], \
        ["/notes", HandleOptionNotes, 1, "Add note to all listed tests"], \
        ["/env", HandleOptionEnv, 1, "Add set env variable in note of all" +\
                " listed tests"], \
        ["/q", HandleOptionBypass, 0, "Automatically confirm queueing all tests"], \
        ["/alt", HandleOptionAlt, 0, "Perform only tests from the AltReqInfo" +\
                " list"], \
        ["/special", HandleOptionSpecial, 0, "Perform only tests from the" +\
                " SpecialReqInfo list"], \
        ["/ADK", HandleOptionADK, 0, "Perform only ADK tests"], \
        ["/AT", HandleOptionAT, 0, "Perform only AT tests"], \
        ["/force", HandleOptionForce, 0, "Force tests to be performed as " +\
                "specified in the commandline"], \
        ["/bat", HandleOptionBat, 1, "Perform tests with the specified .bat file"],\
        ["/pre", HandleOptionPreTestBat, 1, "Perform tests with the specified" +\
                " pretest .bat file"], \
        ["/create", HandleOptionCreateDependencies, 0, "Create product version " +\
                "record if it does not exist"], \
        ["/update", HandleOptionUpdate, 1, "Specify the update flag to be sent to" +\
                " Prepare.php"], \
        ["/WorkingSet", HandleOptionWorkingSet, 0, "Run test with working set" +\
                " trimming disabled"], \
        ["/NoAddOns", HandleOptionNoAddOns, 0, "Do not enable browser addons"], \
        ["/installer", HandleOptionInstaller, 1, "Specify installer file to use"], \
        ["/i", HandleOptionIterations, 1, "Iterate through all the tests"], \
        ["/context", HandleOptionContext, 1, "Specify the context for the tests"], \
        ["/append", HandleOptionAppendBats, -1, "Specify additional .bat files " +\
                "to append"], \
        ["/defender", HandleOptionDefender, 0, "Run test on Windows Defender"], \
        ["/custom", HandleOptionCustom, 0, "Create and Run custom installation" +\
                " batch"], \
        ["/copycsdk", HandleOptionCopyCSDKAdapter, 1, "Copy CSDK Adapter from " +\
                "the CPS Server"], \
        ["/iu", HandleOptionIU, 1, "Specify a tagged, standard IU package to " +\
                "install"], \
        ["/fast", HandleOptionFast, 0, "Runs all non-manual, working tests"], \
        ["/netsync", HandleOptionNetSync, 1, "Specify network location to sync the" +\
                " scripts folder with"], \
        ["/netsynccred", HandleOptionNetSyncCred, 1, "Specify login details for" +\
                " /netsync option"], \
        ["/cps", HandleOptionOldServer, 0, "Run on old cps server instead of " +\
                "ngc-perf-web"], \
        ["/installername", HandleOptionInstallerName, 1, "Specify installer name, " +\
                "e.g. {Product}-ESD30D-{Build}.exe"], \
        ["/syncprod", HandleOptionSyncNorton, 1, "Sync down files after product" +\
                " installation"], \
        ["/debug", HandleOptionDebug, 0, "Print all debug statements returned " +\
                "from Prepare.php"], \
        ["/backup", HandleOptionBackupOverride, 0, "Toggle (Override) Backup " +\
                "Option On"], \
        ["/nobackup", HandleOptionNoBackupOverride, 0, "Toggle (Override) Backup" +\
                " Option Off"], \
        ["/NoOneDrive", HandleOptionNoOneDrive, 0, "Disable OneDrive before running" +\
                " tests that are specified in RequestInfo.json"], \
        ["/core", HandleOptionCore, 0, "Specify whether to use core-defs"], \
        ["/NCW", HandleOptionNCW, 0, "Enable NCW by setting commandline switch " +\
                "during product install"], \
        ["/prep", HandleOptionPrep, 1, "Specify which subfolder to find prepare.php"], \
        ["/cosint", HandleOptionCosint, 0, "Use cosint installer"], \
        ["/lu", HandleOptionLiveUpdate, 0, "Run LiveUpdate before perf tests"], \
        ["/esd", HandleOptionESD, 0, "Use regular ESD build instead of ESD30D"], \
        ["/iter", HandleOptionTestIteration, 1, "Specify number of iterations for the test"], \
        ["/wu", HandleOptionWindowsUpdate, 1, "Specify ID of windows update package to install"],
        ["/skipos", handle_option_skip_os, -1, "Skip tests on these Operating Systems"],
        ["/skiptest", handle_option_skip_test, -1, "Skip tests from this list"],
        ["/skipmachine", handle_option_skip_machine, -1, "Won't schedule test on these machines"]
    ]

help_msg = "\nUsage: submitNIS.py <username> <build_number>\n"
for option in CommandLineOptions:
    msg = "[" + option[0] + "]: " + option[3] + '\n'
    help_msg += msg


# ------------------Variables---------------------------

# This script uses notes.txt and manual_parameter.txt of its
# current directory to add any additional notes and/or manual parameter.

# Get AP from: \\ussm-cpd.corp.symantec.com\ussm-cpd\unreleased_builds\Autoprotect\
# using the highest build number
# Latest NIS Product at \\ussm-cpd\USSM-CPD\Unreleased_Builds\NIS\Shared_Components
# Latest NIS PBA at \\ussm-cpd\USSM-CPD\Unreleased_Builds\STG_Platform\STG2012Q2-2
osType = ['Win10x64', 'Win81x64', 'Win7x64', 'Win10x86', 'Win8x64']
contextEnum = ['N360', 'Product', 'Baseline']

DEBUG = 1

driver_replace_files = ['']
pretest_at_bats = []
pretest_adk_bats = []
config = {'max_retries': 5}
# -----------------------------------------------

# ------------------------------------------------------------------------------
#   Parse commandline options
# ------------------------------------------------------------------------------
if len(sys.argv) < 2 or re.match("[A-Za-z_]+", sys.argv[1]) is None:
    print("Please enter Username")
    print(help_msg)
    sys.exit(2)
if len(sys.argv) < 3 or re.match("^[0-9]", sys.argv[2]) is None:
    print("Please enter Build Number")
    print(help_msg)
    sys.exit(2)


###############################################################################
# username/build_number option
###############################################################################
username = [sys.argv[1]]
build_number = sys.argv[2]
all_test = False
isVSDevBuild = False
isDevBuild = False
typedBuild = False
isVSBranch = False
specialCase229 = False
only229 = False
if len(build_number.split('.')) > 4:
    typedBuild = True
    if '.dev' in build_number:
        isDevBuild = True

###############################################################################
# Other parameter options
###############################################################################
# Check for all other parameter options
delete_switch = False
enableNIC = None
setNIC = False
enableXperf = None
enableWPP = None
priority = 'Normal'
priority_class = None

specify_os = False
os_list = []
specify_testname = False
specify_machine = False
test_name = []
test_name_copy = []
machine_list = []
machine_param = None
manual_param = None
notes_param = None
env_param = None
m_param = None
bat_param = None
test_tags = []
batch_type = None
batch_name = None
batch_priority = None
batch_attach = 0
bypass_switch = False
priority_flag = False
force_flag = False
ADK_flag = False
AT_flag = False
alt_flag = False
special_flag = False
status_active = True
iu_name = None
create_dependencies_flag = False
iter_param = None
reqinfo = []
m_list = {}
contexts = None
append_bats = []
specify_append_bats = False
created_files = []
open_files = []
defender_flag = False
reqinfo_file = 'RequestInfo.json'
installer_file = None
installer_build_number = None
customInstallation = False
seenRequests = set()
fast_flag = False
net_loc = None
netsync_cred = None
database_server = "http://ngc-perf-web.qalabs.symantec.com"
installer_name = None
custom_installer_name = False
sync_prod_loc = None
server_setup = False
debug_flag = False
run_on_backup = True
backup_override = False
backup_tests = []
definitions = None
# iu_server = "\\\\CUL1RFPFILPIN06.symc.symantec.com\\dropbox\\IU"
# iu_server = "\\\\culshare.symc.symantec.com\\Dropbox_cul1rfpfilpin06\\IU"
iu_server = "\\\\ngc-perf-web.qalabs.symantec.com\\Global Performance Unit\\IU"
iu_server_user = None
iu_server_password = None
prepare_php = '/dbupdate/Prepare.php'
coredefs = False
update_flag = "true"
defsloc = None
at_requiresNIC = False
adk_requiresNIC = False
liveupdate = False
test_iteration = None
append_at_bats = []
append_adk_bats = []
wuid = None
skip_os_list = []
skip_tests = []
skip_machine = []

if len(sys.argv) >= 4:
    params = []
    for i in range(3, len(sys.argv)):
        if re.match("^/", sys.argv[i]) is None:
            continue
        option_found = False
        for option in CommandLineOptions:
            if re.match(option[0] + '$', sys.argv[i]) is not None:
                option_found = True
                if option[2] == 1:
                    params = [sys.argv[i]]
                    i += 1
                    if (i < len(sys.argv)) and (re.match("^/", sys.argv[i]) is None):
                        params.append(sys.argv[i])
                    option[1](params)
                elif option[2] == 0:
                    params = [sys.argv[i]]
                    option[1](params)
                elif option[2] == -1:
                    params = [sys.argv[i]]
                    i += 1
                    while (i < len(sys.argv)) and (re.match("^/", sys.argv[i]) is None):
                        params.append(sys.argv[i])
                        i += 1
                    option[1](params)
                break
        if not option_found:
            print('Option \"{0}\" not found\n{1}\n'.format(sys.argv[i], help_msg))
            sys.exit(2)

# Determine if the build is VS 2015:
if customInstallation:
    VS_Prod_Ver = build_number
    if re.match(r".*\..*_.*\..*\..*", build_number):
        isVSDevBuild = True
        major, minor, build, revision, dev = build_number.split('.')
        VS_Prod_Ver = '{0}.{1}.{2}.{3}'.format(major, minor.split('_')[0], build, revision)
        product_version = major + '.' + minor + '.dev'
    elif re.match(r".*\..*\..*-.*\..*", build_number):
        isVSBranch = True
        specialCase229 = True
    try:
        if int(build_number.split('.')[1]) >= 9:
            specialCase229 = True
            only229 = True
    except Exception:
        pass
if "-" in build_number:
    branchSpecifier = True
else:
    branchSpecifier = False


# determine batch_type and priority
if specify_testname:
    batch_type = 'Test'
    batch_priority = 'Standard'
elif specify_machine:
    batch_type = 'Machine'
    batch_priority = 'Standard'
elif specify_os:
    batch_type = 'OSReport'
    batch_priority = 'Report'
elif fast_flag:
    batch_type = 'FastReport'
    batch_priority = 'BuildReport'
    if batch_attach == 0:
        batch_attach = -1
else:
    batch_type = 'Report'
    batch_priority = 'Report'
    if batch_attach == 0:
        batch_attach = -1

# Set default values
enableNIC = True if enableNIC is None else enableNIC
enableXperf = enableXperf if enableXperf is not None else False
enableWPP = enableWPP if enableWPP is not None else False
os_list = os_list if os_list else osType

batch_note = ''
if bat_param:
    batch_note += " (Bat)"

if pretest_at_bats or pretest_adk_bats:
    batch_note += " (PBat)"

if specify_append_bats:
    batch_note += " (ABat)"

if installer_file:
    batch_note += " (IFile)"

if customInstallation:
    batch_note += " (Custom)"

if net_loc:
    batch_note += " (NetSync)"

if sync_prod_loc:
    batch_note += " (SyncNort)"

if custom_installer_name:
    batch_note += " (IName)"

if coredefs:
    batch_note += " Core"

if test_iteration:
    batch_note += " (Iter)"

# Add test tag to the note
if len(test_tags) > 0:
    # Currently only one test tag is supported. If length of test_tags is greater
    # than 1, the test is not scheduled at all
    batch_note += " " + test_tags[0]

if iu_name is not None:
    batch_note += " " + iu_name
# Set Xperf trace if necessary
if enableXperf:
    batch_note += " XPerf ON"

# Set WPP trace if necessary
if enableWPP:
    batch_note += " WPP ON"

user_manual_parameter = ''
# User Manual parameters
if manual_param is not None:
    user_manual_parameter = "\n" + manual_param
elif ospackage.path.isfile("manual_parameter.txt"):
    with open('manual_parameter.txt', 'r') as content_file:
        user_manual_parameter = content_file.read()
        if user_manual_parameter.strip() != '':
            print("User specified Manual Parameter: " + user_manual_parameter)
            if bypass_switch:
                print("Add this as manual parameter(Y/N)?:  n")
                add_manual_parameter = 'n'
            else:
                add_manual_parameter = input("Add this as manual parameter(Y/N)?: ")
            if add_manual_parameter.lower() == 'y':
                status_active = False
                user_manual_parameter += '\n'
            else:
                user_manual_parameter = ''

# User notes
if notes_param:
    batch_note += " " + notes_param

elif ospackage.path.isfile("notes.txt"):
    with open('notes.txt', 'r') as content_file:
        user_notes = content_file.read()
        if user_notes.strip() != '':
            print("User specified Notes: " + user_notes)
            if bypass_switch:
                print("Add this as notes(Y/N)?:  n")
                add_notes = 'n'
            else:
                add_notes = input("Add this as notes(Y/N)?: ")
            if add_notes.lower() == 'y':
                status_active = False
                batch_note += " " + user_notes

if batch_note == '':
    batch_note = False
else:
    batch_note = batch_note.strip()
if batch_name is None and batch_note:
    batch_name = batch_note

# Parse the JSON Request Info
with open(reqinfo_file) as json_file:
    # Read the JSON file with its order preserved
    json_data = json.load(json_file, object_pairs_hook=OrderedDict)

if alt_flag:
    reqinfo = json_data['AltReqInfo']
elif special_flag:
    reqinfo = json_data['SpReqInfo']
else:
    reqinfo = json_data['StdReqInfo']

if contexts is None:
    setAllContexts()

# Rearrange test order according to /m parameter
if m_param is not None:
    for req_note in reqinfo:
        requests = reqinfo[req_note]
        for request in requests:
            if 'Manual Parameter' in request:
                if req_note not in m_list:
                    m_list[req_note] = [request]
                else:
                    m_list[req_note].append(request)
                reqinfo[req_note].remove(request)


def get_new_bat_file(old_name=None):
    global created_files
    timefrmt = time.strftime('%Y%m%d%H%M%S')
    if old_name is None:
        new_file_name = 'TEMP' + timefrmt + '.bat'
    else:
        new_file_name = timefrmt + old_name
    created_files.append(new_file_name)
    return new_file_name


def setPrepareData(testname, machine, os, subproduct, build_number, status_active,\
        username, note, matrix_tag, create_dependencies_flag, context, test_type_id,\
        manual_parameter, priority, notes, machine_type, test_tag, isAddOns, \
        atsScriptName, adkLocation, jobXML, testName, appendBats, installerloc, \
        defsloc):
    global isVSDevBuild
    global isDevBuild
    global typedBuild
    global product_version
    global actual_build
    global isVSBranch
    global branchSpecifier
    global batch_type
    global batch_name
    global batch_attach
    global batch_priority
    global enableNIC
    global enableXperf
    global enableWPP
    global definitions
    global iu_name
    global coredefs
    global update_flag
    global test_iteration

    arch_loc = os.index('x')
    arch = os[arch_loc:]
    os = os[:arch_loc]
    if os == "Win81":
        os = "Win8.1"
    if subproduct == "Product":
        subproduct = "NIS"
    if context == 'Baseline':
        product = 'Baseline'
    else:
        product = 'Norton'

    # Build computations
    if typedBuild:
        type = build_number.split('.')[4]
        build_number = '.'.join(build_number.split('.')[0:4])
    final_dot = build_number.rfind(".")
    build = build_number[final_dot+1:]
    prodver = build_number[:final_dot]
    while True:
        final_dot = prodver.rfind(".")
        if final_dot == -1 or final_dot == prodver.find("."):
            break
        minorver = prodver[final_dot+1:]
        if (not branchSpecifier) and (int(minorver) == 0):
            prodver = prodver[:final_dot]
        else:
            break
    if priority == "High":
        batch_priority = priority

    if typedBuild:
        prodver = '{0}.{1}'.format(prodver, type)
    if isVSDevBuild:
        prodver = product_version
    username = username[0]
    update = update_flag
    if status_active:
        status = "NORMAL"
    else:
        status = "INACTIVE"
    if create_dependencies_flag:
        create_dependencies = "true"
    else:
        create_dependencies = "false"
    prepare_data = { \
        'machine' :                machine, \
        'os':                      os,\
        'arch' :                   arch, \
        'product' :                product,
        'subproduct' :             subproduct,
        'prodver' :                prodver, \
        'testname' :               testname, \
        'batchtype' :              batch_type, \
        'batchname' :              batch_name, \
        'attach' :                 batch_attach, \
        'batchpri' :               batch_priority, \
        'build' :                  build, \
        'status' :                 status, \
        'tester':                  username, \
        'comment':                 note, \
        'test_tag':                test_tag, \
        'definitions':             definitions, \
        'matrix_tag':              matrix_tag, \
        'update':                  update, \
        'create_dependencies':     create_dependencies, \
        'test_type_id':            test_type_id, \
        'Priority':                priority, \
        }

    if (enableNIC) and (test_tag != "NoNetwork"):
        prepare_data['enableNIC'] = 'on'
    else:
        if test_type_id == 18 and adk_requiresNIC:
            print("This option cannot be used with NoNetwork")
            return None

    if enableXperf:
        prepare_data['enableXPerf'] = 'on'

    if enableWPP:
        prepare_data['enableWPP'] = 'on'

    if coredefs:
        prepare_data['definitions'] = 'Core'
    elif iu_name is not None:
        prepare_data['definitions'] = iu_name

    if manual_parameter not in [None, '']:
        prepare_data['ManualParameter'] = 'True'

    if isAddOns:
        prepare_data['isAddOns'] = 'True'

    if atsScriptName is not None:
        prepare_data['atsScriptName'] = atsScriptName
        prepare_data['testName'] = testName

    if adkLocation is not None:
        prepare_data['adkWACLocation'] = adkLocation
        prepare_data['jobXML'] = jobXML
        prepare_data['testName'] = testName

    if appendBats:
        prepare_data['appendBats'] = 'True'

    if installerloc:
        prepare_data['installerloc'] = installerloc

    if defsloc:
        prepare_data['defsloc'] = defsloc

    # Iteration support
    if test_iteration:
        prepare_data['iterations'] = test_iteration

    with open('pd_iteration.json', 'w') as wp:
        json.dump(prepare_data, wp, indent=4, sort_keys=True)

    if ospackage.path.isfile('pd_iteration.json'):
        sys.exit(2)

    return prepare_data


def get_server_setup_commands(server, user, password):
    global server_setup
    if server_setup:
        return ""
    server_setup = True
    command = '@echo off\nnet.exe use \"{0}\" /persistent:no /user:\"{1}\" \"{2}\"'\
            .format(server, user, password)
    return command


def get_installer_copy_commands(file, source, destination, input_path):
    command = 'copy \"{0}\" \"{2}\" /y\ncopy \"{2}{1}\" \"{3}\" /y'\
            .format(source, file, destination, input_path)
    command = command + '\nif \"{0}\"==\"T\" (\n  start /wait {2}{1} /qn\n)'\
            .format('{0}', file, destination)
    return command


def get_awk_contents(awk_command_path, awk_sub_commands, awk_file, attribute_file, version):
    awk_commands = '\n'.join(list(map(lambda x: '  echo {0} >> {1}'\
            .format(x, awk_file), map(lambda x: x['Command']\
                    .format(version), awk_sub_commands))))
    awk_contents = 'echo {{ > {1}\n{0}\necho }}1 >> {1}'.format(awk_commands, awk_file)
    apply_awk_command = '{0} -f \"{1}\" \"{2}\" > \"{2}.tmp\"\nmove \"{2}.tmp\" \"{2}\"'\
            .format(awk_command_path, awk_file, attribute_file)
    return awk_contents + '\n' + apply_awk_command


def get_tools_copy_commands(server, tools_list, tools_dest):
    tools = list(map(lambda x: 'copy \"{0}{1}\" \"{2}\" /y'\
            .format(server, x, tools_dest), map(lambda x: x['Source'], tools_list)))
    tools_copy_command = '\n'.join(tools)
    return tools_copy_command


def add_network_drive(config_batch):
    global installer_build_number
    global VS_Prod_Ver
    global specialCase229
    global isDevBuild
    prod_ver = installer_build_number if installer_build_number is not None else VS_Prod_Ver
    major, minor, build, revision = prod_ver.split('.')[0:5]
    with open('SetupInstallerFiles.json') as file:
        data = json.load(file)
    SERVER = data['Server']
    USER = data['User']
    PASSWORD = data['Password']
    if specialCase229:
        ver1 = '{0}.{1}.{2}'.format(major, minor, build)
        ver2 = revision
        devPath = 'DEV' if isDevBuild else 'CM'
        SERVER_PATH = data['SpecialCasePath'].format(ver1, devPath, ver2)
    else:
        SERVER_PATH = data['ServerPath'].format(major, minor, branch, revision)

    path = SERVER + SERVER_PATH

    setup_network_drive = '\n\nset server={0}\nset user={1}\nset pass={2}\nset ver={3}\n' + \
                'set build={4}\n\nnet.exe \"%server%\" /persistent:no' + \
                ' /user:\"%user%\" \"%pass%\"\n'.format(SERVER, USER, PASSWORD, prod_ver, revision)
    return setup_network_drive, path


#------------------------------------------------------------------------------
#   prepare_remote_transfer
#   Transfer the Installer file to the machine from the correct location
#   for VS2015 Build Requests, change the attributes file with the correct
#   values for environment variables.
#
#   INPUT
#       branch: Branch from which the files should be transferred
#       context: Context for the request
#       config_batch: Configuration Batch file
#       adk_test: Indicates ADK Test
#
#   OUTPUT
#       new_file_name: Newly created configuration batch file with the desired
#           commands for the remote file transfer, installation, etc.
#------------------------------------------------------------------------------
def prepare_remote_transfer(branch, context, config_batch, adk_test):
    global VS_Prod_Ver
    global installer_file
    global installer_build_number
    global specialCase229
    global only229
    global isDevBuild
    prod_ver = installer_build_number if installer_build_number is not None else VS_Prod_Ver
    with open('SetupInstallerFiles.json') as file:
        data = json.load(file)
    major, minor, build, revision = prod_ver.split('.')
    SERVER = data['Server']
    SERVER_PATH = data['ServerPath'].format(major, minor, branch, revision)
    USER = data['User']
    PASSWORD = data['Password']

    INSTALLER_DEST = data['InstallerDestination']
    INPUT_PATH = data['InputPath']
    CONTEXT_PATH = '\\Layouts\\English' if context == 'N360' else '\\Layouts\\SuperMUI'
    INSTALLER_SOURCE = CONTEXT_PATH + '\\'

    if specialCase229:
        CONTEXT_PATH = '\\Layouts\\English'
        INSTALLER_SOURCE = CONTEXT_PATH + '\\'
        ver1 = major + '.' + minor + '.' + build
        ver2 = revision
        devPath = 'DEV' if isDevBuild else 'CM'
        SERVER_PATH = data['SpecialCasePath'].format(ver1, devPath, ver2)
    if installer_file is None:
        ver_parts = []
        for ver in prod_ver.split('.')[0:4]:
            if '-' in ver:
                ver = ver.split('-')[0]
            elif '_' in ver:
                ver = ver.split('_')[0]
            ver_parts.append(ver)
        version = '.'.join(ver_parts)
        if only229:
            INSTALLER = 'NSBU-ESD30D-{0}.exe'.format(version) \
                if context == 'N360' else 'NS-ESD30D-{0}.exe'.format(version)
        else:
            INSTALLER = data['Installer'].format('NSBU', version) \
                if context == 'N360' else data['Installer'].format('NS', version)
    else:
        INSTALLER = installer_file
        version = '.'.join(prod_ver.split('\\')[-1].split('-')[-1].split('.')[0:4])

    if INSTALLER[0:2] == '\\\\':
        FILE_LOC = INSTALLER
        SERVER = INSTALLER.split('\\Layouts')[0]
        INSTALLER = INSTALLER.split('\\')[-1]
    elif '\\' in INSTALLER:
        FILE_LOC = SERVER + SERVER_PATH + INSTALLER
        INSTALLER = INSTALLER.split('\\')[-1]
    else:
        FILE_LOC = SERVER + SERVER_PATH + INSTALLER_SOURCE + INSTALLER

    tools_list = data['Tools']
    tools_dest = data['ToolsDestination']

    awk = data['AWKSubCommands']
    awk_location = data['AWKCommand']
    attribute_file = data['AttributeFile']
    awk_file = data['AWKFileTempLocation']

    SERVER_SETUP_COMMANDS = get_server_setup_commands(SERVER, USER, PASSWORD)
    FILE_COPY_COMMAND = get_installer_copy_commands(INSTALLER, FILE_LOC, \
            INSTALLER_DEST, INPUT_PATH).format(str(adk_test)[0])
    TOOLS_COPY_COMMANDS = get_tools_copy_commands(SERVER + SERVER_PATH, \
            tools_list, tools_dest).format(str(adk_test)[0])
    AWK_COMMANDS = get_awk_contents(awk_location, awk, awk_file, attribute_file, version)

    if only229:
        symcdefs = 'symcdefs.exe'
        FILE_COPY_COMMAND += '\n copy \"' + SERVER + SERVER_PATH + INSTALLER_SOURCE + \
                symcdefs + '\" \"%USERPROFILE%\\DESKTOP\\\" /y'
        FILE_COPY_COMMAND += '\n copy \"%USERPROFILE%\\DESKTOP\\' + symcdefs + \
                '\" \"D:\\Working\\Input\\\" /y'

    ALLCOMMANDS = SERVER_SETUP_COMMANDS + '\n\n' + FILE_COPY_COMMAND + '\n\n' + \
            TOOLS_COPY_COMMANDS + '\n\n' + AWK_COMMANDS + '\n\n'

    with open(config_batch) as file:
        current_contents = file.readlines()
    current_contents.insert(0, ALLCOMMANDS)
    new_file_name = get_new_bat_file(config_batch)
    with open(new_file_name, 'w') as file:
        file.write('\n'.join(current_contents))
    return new_file_name


def copy_scripts(config_batch):
    global net_loc
    global netsync_cred
    with open('SetupInstallerFiles.json') as file:
        data = json.load(file)
    if netsync_cred is None:
        user = data['User']
        password = data['Password']
    else:
        if ':' in netsync_cred:
            user, password = netsync_cred.split(':')[0:2]
        else:
            user = netsync_cred
            password = getpass.getpass("Password: ")
    copy_commands = "ROBOCOPY /E \"{0}\" \"%USERPROFILE%\\Desktop\\script\"".format(net_loc)
    commands = "set server={0}\n" +\
                "set user={1}\n" +\
                "set pass={2}\n" +\
                "%WINDIR%\\System32\\net.exe use \"%server%\" " +\
                "/persistent:no /user:\"%user%\" \"%pass%\"\n" +\
                "{3}\n\n"
    commands = commands.format(net_loc, user, password, copy_commands)
    new_file_name = get_new_bat_file(config_batch)
    with open(config_batch, 'r') as file:
        current_contents = file.readlines()
    with open(new_file_name, 'w') as file:
        file.write(commands)
        file.write('\n'.join(current_contents))
    return new_file_name


def create_iter_file(test_iteration, test_type_id):
    global created_files
    file_content = ''' :: Modify test iteration '''
    if test_type_id == 17:
        file_content += '''
        if exist "C:\\Python27\\" (
	        "C:\\Python27\\python.exe" D:\\Working\\Input\\Python\\AT_ModifyIter.py ''' + test_iteration + ''' -f %USERPROFILE%\\Desktop\\script\\AT\\PerfReplacements.atr -o %USERPROFILE%\\Desktop\\script\\AT\\PerfReplacements.atr.tmp > D:\\Working\\Output\\AT_ModifyIter.log 2>&1
            timeout 10
            move /Y "%USERPROFILE%\\Desktop\\script\\AT\\PerfReplacements.atr.tmp" "%USERPROFILE%\\Desktop\\script\\AT\\PerfReplacements.atr"
        )
        '''
    else:
        file_content += '''
        ROBOCOPY %USERPROFILE%\\Desktop\\script\\Python D:\\Working\\Input\\Python /IS

        if not exist "D:\\WAC\\results\\" mkdir D:\\WAC\\results

        if exist "C:\\Python27\\" (
            >> D:\\WAC\\results\\ADK_ModifyIter.log 2>&1 (
            "C:\\Python27\\python.exe" -m pip install --upgrade pip
            timeout 30
            "C:\\Python27\\Scripts\\pip.exe" install -U setuptools
            timeout 30
            "C:\\Python27\\Scripts\\pip.exe" install lxml
            timeout 60

            "C:\\Python27\\python.exe" D:\\Working\\Input\\Python\\ADK_ModifyIter.py ''' + test_iteration + ''' -f D:\\WAC\\Job.xml -o D:\\WAC\\Job_temp.xml
            timeout 10
            move /Y "D:\\WAC\\Job_temp.xml" "D:\\WAC\\Job.xml"
            )
        )
        '''
    file_name = 'IterFile_Temp_' + time.strftime('%Y%m%d%H%M%S') + '.bat'
    with open(file_name, 'w') as file:
        file.write(file_content)
    created_files.append(file_name)
    return file_name


def setup_sync_install(test_type_id):
    global sync_prod_loc
    global request_bats
    with open('SetupInstallerFiles.json') as file:
        data = json.load(file)
    user = data['User']
    password = data['Password']
    if test_type_id == 17:
        with open('AT_SyncPrepare.bat', 'r') as file:
            contents = file.readlines()
    else:
        with open('ADK_SyncPrepare.bat', 'r') as file:
            contents = file.readlines()
    contents[0] = contents[0].replace('\n', sync_prod_loc + '\n')
    contents[1] = contents[1].replace('\n', user + '\n')
    contents[2] = contents[2].replace('\n', password + '\n')
    new_file_name = get_new_bat_file()
    with open(new_file_name, 'w') as file:
        file.write(''.join(contents))
    request_bats.append(new_file_name)


#------------------------------------------------------------------------------
#   setData
#   Populates an array of data needed for a Request: array(submit_data, files)
#
#   INPUT
#       test_type_id:   ID of the test on the dashboard
#       machine_type:   Machine to test on
#       context:        Product to test with (Platform, Product Baseline, N360,
#                       SEP Platform, SEP Product)
#       build_number:   Build number for the context. Use 0.0.0.0 for Baseline
#       note:           notes for the Request
#       read_cache_file:
#                   Batch file for reading cache
#       driver_replace_file:
#                   Driver file to replace with
#       config_batch:   Configuration Batch. Runs during setup before the test
#
#   OUTPUT
#       Array with two items: submit_data and files
#       submit_data:    Contains all data that is not a file
#       files:          Contains names and streams of all input files
#
#------------------------------------------------------------------------------
def setData(test_type_id, machine_type, context, build_number, note, read_cache_file, \
        driver_replace_file, config_batch, post_Appendbatch, manual_parameter, priority, \
        tag, adk_test, enable_bootlogs, request_bats):
    global open_files
    global isVSDevBuild
    global installer_file
    global specialCase229
    global customInstallation
    global net_loc
    global installer_name
    global sync_prod_loc
    global liveupdate
    global test_iteration
    global pretest_at_bats
    global pretest_adk_bats
    global append_at_bats
    global append_adk_bats

    if installer_file is not None or customInstallation:
        build = '0.0.0.0'
        cntxt = 'Baseline'
    else:
        build = build_number
        cntxt = context

    submit_data = { \
        'test_type_id':                     test_type_id, \
        'Number+of+Runs' :                  '1', \
        'DefinitionsEnum':                  'STARi--v4.exe',\
        'Machine+Type' :                    machine_type, \
        'Context' :                         cntxt,
        'Build+Number' :                    build,
        'Manual+Parameters' :               manual_parameter,\
        'Notes':                            note,\
        'Misc.+Options/Send+email+ping+when+test+done':'on',\
        'Misc.+Options/Send+email+ping+when+manual+parameters+ready':'on',\
        'Automatic+Definitions+Type':       'Build Specific',\
        'Driver+ReplaceEnum':               'AP',\
        'Disk+Drive+Option':                'HDD',\
        'Product+Class':                    '3',\
        'Product+Version':                  '19.0.0.0',\
        'Xperf+Trace+Command':              'xperf.exe -on Latency+Dispatcher+' +\
                    'file_io+file_io_init+Disk_IO_Init+Filename+Network ' +\
                    '-stackwalk Profile+CSwitch+Readythread+FileCreate+' +\
                    'FileRead+FileWrite+FileFlush+DiskReadInit+DiskWriteInit+' +\
                    'DiskFlushInit+ImageLoad+ImageUnload+ThreadCreate+ThreadDelete ' +\
                    '-BufferSize 1024',\
        'Process+Idle+Tasks/Enable':        'on',\
        'ProcessIdleTasks+Timeout%28Seconds%29': '3600',\
        'priority':                         priority,\
        'User+name':                        '',\
        'Password':                         '',\
        }
    if net_loc is not None:
        config_batch = copy_scripts(config_batch)

    if test_iteration is not None:
        iter_file = create_iter_file(test_iteration, test_type_id)
        request_bats.append(iter_file)

    if sync_prod_loc is not None:
        setup_sync_install(test_type_id)

    if enableWPP and enable_bootlogs:
        request_bats.append('EnableBootLogs.bat')

    if liveupdate:
        if test_type_id == 17:
            request_bats.append("Enable_LU.bat")
        else:
            request_bats.append("ADK_RunLU.bat")

    # Finally append PostAppendBatch file
    if post_Appendbatch:
        request_bats.append(post_Appendbatch)

    # Assign pretest and append_batch files appropriately for AT and ADK tests
    if test_type_id == 17:
        pre_batch = pretest_at_bats[:]
        append_batch = append_at_bats[:]
    else:
        pre_batch = pretest_adk_bats[:]
        append_batch = append_adk_bats[:]

    if pre_batch:
        new_prebat_file = get_new_bat_file(pre_batch[0])
        if read_cache_file is not None and read_cache_file.strip() != "":
            pre_batch.append(read_cache_file)
        contents = []
        for filename in pre_batch:
            with open(filename, 'r') as file:
                contents += ["::\n"] + file.readlines() + ["\n\n"]
        with open(new_prebat_file, 'w') as file:
            for line in contents:
                file.write(line)
        read_cache_file = new_prebat_file
    if append_batch:
        request_bats[:0] = append_batch

    submit_data["appendBats"] = None
    if len(request_bats) > 0:
        temp_bat_file = get_new_bat_file()
        if config_batch is not None and config_batch.strip() != "":
            if config_batch not in request_bats:
                request_bats.insert(0, config_batch)
        contents = []
        for filename in request_bats:
            with open(filename, 'r') as file:
                contents += ["::\n"] + file.readlines() + ["\n\n"]
        with open(temp_bat_file, 'w') as file:
            for line in contents:
                file.write(line)
        if not config_batch:
            submit_data["appendBats"] = "True"
        config_batch = temp_bat_file

    elif installer_file is not None or customInstallation:
        if installer_file is not None:
            if isVSDevBuild:
                config_batch = prepare_remote_transfer('_VS2015', context, config_batch, adk_test)
            else:
                config_batch = prepare_remote_transfer('', context, config_batch, adk_test)
        # if defs_location is not None:
        #    config_batch = copy_defs(config_batch)

    files = {}
    # Add Driver Replacements if applicable
    if driver_replace_file is not None and driver_replace_file.strip() != "":
        file = open(driver_replace_file, 'rb')
        files['Driver+ReplaceFile'] = (driver_replace_file, file)
        open_files.append(file)

    # Add Definition files
    # files = {'DefinitionsFile': ('', '')}

    # Add Configuration Batch File
    if config_batch is not None and config_batch.strip() != "":
        file = open(config_batch, 'rb')
        files['Run+Configuration+Batch+File'] = (config_batch, file)
        open_files.append(file)

    # Add Pre-Test Batch File
    if read_cache_file is not None and read_cache_file.strip() != "":
        file = open(read_cache_file, 'rb')
        files['Run+Pre-Test+Batch+file'] = (read_cache_file, file)
        open_files.append(file)

    # Set Xperf trace if necessary
    if enableXperf:
        submit_data['Xperf+Trace+Gathering/Enable'] = 'on'


    # Set WPP trace if necessary
    if enableWPP:
        submit_data['WPP+Logging/Enable'] = 'on'

    return submit_data, files


#------------------------------------------------------------------------------
#   Update machine type and machine architecture
#------------------------------------------------------------------------------
def updateMachine(machine_type, os_type):
    updateMachine = machine_type
    if updateMachine is not None:
        updateMachine += "-"
        updateMachine += os_type
        if updateMachine == 'MID4-Win8x64':
            updateMachine = 'MID-4_WIN8'
    return updateMachine


s = http_request.Session()
payload = {'data[AllUser][username]': username}
#------------------------------------------------------------------------------
#   Login using username
#------------------------------------------------------------------------------
if delete_switch:
    r = s.post('http://perf.eng.symantec.com/login', data=payload)
    # Check if login was successful
    success = re.search("logout", str(r.content))
    if success:
        print('Logged In')
    else:
        print('Login FAILED. Check Username or try again later')
        print(help_msg)
        sys.exit(2)


def run_test(s, context, build_number, note, test_name, machine_type, machine, os, \
        status_active, username, matrix_tag, test_type_id, manual_parameter, priority, \
        config_batch, pre_batch, test_tag, isAddOns, atsScriptName, adkLocation, \
        jobXML, testName, installerloc, defsloc):
    global batch_attach
    global debug_flag
    global prepare_php

    # Run prepare.php, get back the request number to print out
    retry = True
    while retry:
        file_dict = {}
        if config_batch is not None:
            file_dict['config'] = open(config_batch, 'rb')
        if pre_batch is not None:
            file_dict['prebatch'] = open(pre_batch, 'rb')
        prepareData = setPrepareData(test_name, machine, os, context, \
                build_number, status_active, username, note, matrix_tag, \
                create_dependencies_flag, context, test_type_id, \
                manual_parameter, priority, submit_data['Notes'], \
                machine_type, test_tag, isAddOns, atsScriptName, \
                adkLocation, jobXML, testName, submit_data['appendBats'], \
                installerloc, defsloc)
        if prepareData is None:
            return
        if config_batch is not None:
            prepareData['batch_hash'] = hashlib.md5(open(config_batch, 'rb')\
                .read()).hexdigest()
        if pre_batch is not None:
            prepareData['prebatch_hash'] = hashlib.md5(open(pre_batch, 'rb')\
                    .read()).hexdigest()
        url = database_server + prepare_php
        r = s.post(url, data=prepareData, files=file_dict)
        rmessage = r.content
        rmsg_formatted = rmessage.decode("utf-8")
        rmessage = str(rmessage)
        if debug_flag:
            print("---DEBUG---")
            print(rmsg_formatted)
            print("-----------")
        if rmessage.find("STAR Web Services Trouble Alert") != -1:
            print("\"STAR Web Services Trouble Alert\" error. Retrying...")
            continue
        if (rmessage.find("Updates will not actually be performed") != -1):
            exit(rmsg_formatted)
        if (rmessage.find("Success") == -1) or (rmessage.find("Failure") != -1):
            print('ERROR: prepare failed')
            if not debug_flag:
                exit(rmsg_formatted)
        if batch_attach == -1:
            batch_attach = 0
        retry = False
        start_index = rmessage.find("Request Number:")
        if start_index >= 0:
            offset = rmessage[start_index:].find("\\n")
            print('  ' + rmessage[start_index : start_index+offset] + ' posted')
            print("  Sleeping for 2 seconds...")
            time.sleep(2)
        else:
            print('  Posted for future execution')


def find_test(test_type_id, note, context, context_list, os, os_list, machine_type):
    found = False
    found_name = False
    found_context = False
    found_os = False
    found_machine = False
    context_types = list(context_list)
    os_types = list(os_list)
    global machine_list

    # only display the tests which is specified in the command
    # only display the tests with the specified test type id
    if ((ADK_flag) and (test_type_id == 18)) or ((AT_flag) and (test_type_id == 17))\
            or (not (ADK_flag or AT_flag)):
        if specify_testname and test_name:
            for name in test_name:
                if note.lower() == name.lower():
                    found_name = True
                    test_name.pop(test_name.index(name))
                    break
        else:
            found_name = True
    # only display the tests which supports the specified context
    for context_name in context_types:
        if context_name.lower() == context.lower():
            found_context = True
            context_types.pop(context.index(context_name))
            break
    # only display the tests which supports the specified os
    for os_name in os_types:
        if os_name == os:
            found_os = True
            os_types.pop(os_types.index(os_name))
            break
    if specify_machine:
        for cur_machine in machine_list:
            #print(cur_machine + " and " + machine_type)
            if machine_type == cur_machine:
                found_machine = True
                break
    else:
        found_machine = True

    if machine_type in skip_machine:
        found_machine = False

    found = found_name and found_context and found_os and found_machine
    return found


def unpack_dictionary(req_dict, note, context, os, request_bats):
    global backup_tests
    global run_on_backup
    global manual_param
    test_id, mtype, bkp_mtype, config_batch, post_Appendbatch, context_types, ostypes,\
        matrix_tag, env, bootlogs, working, tag, add_test_tag, manual_parameter, isAddOns,\
        atsScriptName, adkLocation, jobXML, testName =\
         '', '', '', '', '', [], [], '', '', False, True, '', True, None, False, \
            None, None, None, note
    if 'Test ID' in req_dict:
        test_id = req_dict['Test ID']
    if 'Machine Type' in req_dict:
        mtype = req_dict['Machine Type']
    # If "Config Batch" is in the RequesetInfo, use it, else create it
    # using info from ATScriptFiles
    if 'Config Batch' in req_dict:
        config_batch = req_dict['Config Batch']
    if 'Post Append Batch' in req_dict:
        post_Appendbatch = req_dict['Post Append Batch']
    if 'Contexts' in req_dict:
        ctxt_list = req_dict['Contexts']
        for ctxt in ctxt_list:
            context_types.append(ctxt['Context'])
    if 'OS Types' in req_dict:
        oses = req_dict['OS Types']
        for ostype in oses:
            ostypes.append(ostype['OS'])
    if 'Matrix Tag' in req_dict:
        matrix_tag = req_dict['Matrix Tag']
    if manual_param is not None:
        manual_parameter = manual_param
    elif 'Manual Parameter' in req_dict:
        manual_parameter = req_dict['Manual Parameter']
    if 'Env' in req_dict:
        env = req_dict['Env']
    if 'Bootlog' in req_dict and req_dict['Bootlog'].lower() == 'true':
        bootlogs = True
    if 'Working' in req_dict and req_dict['Working'].lower() in ["no", "false"]:
        working = False
    if 'Test Tag' in req_dict:
        add_test_tag = False
        tag = req_dict['Test Tag']
    if 'IsAddOns' in req_dict:
        isAddOns = True
    if 'ATSScriptName' in req_dict:
        atsScriptName = req_dict['ATSScriptName']
    if 'ADKLocation' in req_dict:
        adkLocation = req_dict['ADKLocation']
    if 'Job.xml' in req_dict:
        jobXML = req_dict['Job.xml']
    if run_on_backup:
        if 'Backup' in req_dict:
            bkp_mtype = req_dict['Backup']
            backup_tests.append((test_id, bkp_mtype, config_batch, post_Appendbatch,\
                context_types, ostypes, matrix_tag, manual_parameter, env, bootlogs,\
                working, tag, add_test_tag, note, context, os, request_bats, isAddOns,\
                atsScriptName, adkLocation, jobXML, testName))
    return test_id, mtype, config_batch, post_Appendbatch, context_types, ostypes,\
        matrix_tag, manual_parameter, env, bootlogs, working, tag, add_test_tag,\
        isAddOns, atsScriptName, adkLocation, jobXML, testName


def create_iu_defs_file(os, iu_name, test_type_id):
    global created_files
    global iu_server
    global iu_server_user
    global iu_server_password
    if iu_server_user:
        logindata = '/user:"%user%" "%pass%"'
        username = iu_server_user
        password = iu_server_password
    else:
        logindata = ''
        username = ''
        password = ''
    if 'x86' in os:
        arch = '32'
    else:
        arch = '64'

    file_content = '''
        set server={0}
        set user={1}
        set pass={2}

        :: Delete the default symcdefs
        if exist "%USERPROFILE%\\Desktop\\symcdefs.exe" (
            del /f /q "%USERPROFILE%\\Desktop\\symcdefs.exe"
        )

        :: Outer Loop for Net use (max 3 attempts)
        set /a outerattempts = 0

        :: Outer Loop Label:
        :outerloop
        set /a outerattempts += 1
        %WINDIR%\\System32\\net.exe use "%server%" /persistent:no {5}

        :: Inner Loop for Network Copy (max 5 attempts per outer loop)
        set /a innerattempts = 0

        :: Inner Loop Label:
        :innerloop
        set /a innerattempts += 1

        set path=%server%\\symcdefs{3}.{4}.exe
        copy /y "%path%" "%USERPROFILE%\\Desktop\\symcdefs.exe"

        set exitstatus=%errorlevel%

        :: If Copy failed:
        if %exitstatus% neq 0 (
            if %innerattempts% leq 5 (
                sleep 30
                goto innerloop
            ) else (
                if %outerattempts% leq 3 (
                    :: Delete the network connection and re-create it
                    sleep 30
                    %WINDIR%\\System32\\net.exe use * /delete /y
                    goto outerloop
                )
            )
        )
    '''.format(iu_server, username, password, arch, iu_name, logindata)

    if test_type_id == 18:
        file_content += '''
    start /wait %USERPROFILE%\\Desktop\\symcdefs.exe /q

    ROBOCOPY D:\\Working\\Input\\ADK D:\\WAC /E /IS
    rem Delete controller.exe and state.txt
    del \"C:\\Users\\Admin\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\controller.exe\"
    del \"C:\\Users\\Admin\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\state.txt\"

    rem Reboot and start ADK test
    %WINDIR%\\System32\\reg.exe ADD HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\RunOnce /v RunJob /t REG_SZ /d D:\\WAC\\ExecuteADKTest.cmd /f
	shutdown /r /t 0
    '''

    file_name = 'IUDefs_Temp_' + time.strftime('%Y%m%d%H%M%S') + '.bat'
    with open(file_name, 'w') as file:
        file.write(file_content)
    created_files.append(file_name)
    return file_name


def process_request_decorator(function):
    global test_tags

    def wrapper(*args):
        if len(args) == 1:
            (test_type_id, machine, config_batch, post_Appendbatch, context_types, \
            os_types, matrix_tag, manual_parameter, env, bootlogs, working, tag, \
            add_test_tag, note, context, os, request_bats, isAddOns,\
            atsScriptName, adkLocation, jobXML, testName) = args[0]
            is_backup = True
        else:
            (test_type_id, machine, config_batch, post_Appendbatch, context_types, \
            os_types, matrix_tag, manual_parameter, env, bootlogs, working, tag, \
            add_test_tag, isAddOns, atsScriptName, adkLocation, jobXML, testName) = unpack_dictionary(*args)
            (note, context, os, request_bats) = args[1:5]
            is_backup = False
        tags = test_tags[:]
        if tag not in [None, '']:
            tags.append(tag)
        return function(test_type_id, machine, config_batch, post_Appendbatch, \
            context_types, os_types, matrix_tag, manual_parameter, env, bootlogs, \
            working, tags, add_test_tag, note, context, os, request_bats, is_backup, \
            isAddOns, atsScriptName, adkLocation, jobXML, testName)
    return wrapper


# def submit_request(request, note, context, os, request_bats):
@process_request_decorator
def submit_request(test_type_id, machine, config_batch, post_Appendbatch, context_types, os_types, matrix_tag,\
        manual_parameter, env, bootlogs, working, tags, add_test_tag, note, context, os, \
        request_bats, is_backup, isAddOns, atsScriptName, adkLocation, jobXML, testName):
    global build_number
    global help_msg
    global batch_note
    global force_flag
    global env_param
    global machine_param
    global bat_param
    global contextEnum
    global osType
    global user_manual_parameter
    global test_name
    global test_name_copy
    global specify_testname
    global submit_data
    global data
    global iu_name
    global fast_flag
    global installer_name
    global defsloc

    testname = note

    if specify_testname:
        test_name = list(test_name_copy)

    adk_test = True if test_type_id == 18 else False
    bnum = build_number
    if (context == 'Baseline') and (not force_flag) and (bnum != '0.0.0.0'):
        bnum = '0.0.0.0'
    elif (bnum == '0.0.0.0') and (context != 'Baseline'):
        print("Only Baseline can be installed when build number is '0.0.0.0'")
        print(help_msg)
        sys.exit(2)

    # Fast test ignores tests which are not working
    if fast_flag and (not working or (manual_parameter not in ['', None])):
        return

    if batch_note:
        note += " " + batch_note

    if (force_flag and env_param is not None) or (env != ''):
        note += '\n(env='
        if env_param is None:
            note += env
        else:
            note += env_param
        note += ')'

    pre_bat_files = ['']

    if iu_name is not None:
        iu_file_name = create_iu_defs_file(os, iu_name, test_type_id)
        if test_type_id == 17:
            request_bats.append(iu_file_name)
        else:
            pre_bat_files[0] = iu_file_name


    for (rci, read_cache_file) in enumerate(pre_bat_files):
        if force_flag:
            found_test = find_test(test_type_id, testname, context, contextEnum, \
                    os, osType, machine)
        else:
            found_test = find_test(test_type_id, testname, context, context_types, \
                    os, os_types, machine)

        # Get machine type value
        if machine_param is not None:
            machine = machine_param
        machine_type = updateMachine(machine, os)
        # Get bat file path
        if bat_param is not None:
            config_batch = bat_param
        elif ('NoAddOns' in tags) and (add_test_tag is True):
            config_batch = config_batch[:-4] + '.NoAddOns.bat'

        queue = None
        if manual_parameter is None:
            manual_parameter = ''
        else:
            note += ' ' + manual_parameter

        if found_test:
            message = "Queue a Request for "
            if is_backup:
                message += "(Backup) "
            if bypass_switch:
                print(message + note + " on " + machine_type + " (Y/N)? y")
                queue = 'y'
            else:
                queue = input(message + note + " on " + machine_type + " (Y/N)? ")

            if queue is not None and queue.lower() == 'y':
                if len(tags) > 1:
                    print("Multiple Test Tags aren't supported yet. Test not scheduled")
                    return
                elif len(tags) == 1:
                    tag = tags[0]
                else:
                    tag = ''
                submit_data, files = setData(test_type_id, machine_type, context, bnum, note,read_cache_file, "",
                                             config_batch, post_Appendbatch, manual_parameter + user_manual_parameter,
                                             priority, tag, adk_test, bootlogs, request_bats
                                             )

                if 'Run+Configuration+Batch+File' in files:
                    config = files['Run+Configuration+Batch+File'][0]
                else:
                    config = None
                if 'Run+Pre-Test+Batch+file' in files:
                    prebatch = files['Run+Pre-Test+Batch+file'][0]
                else:
                    prebatch = None
                run_test(s, context, bnum, note, testname, machine_type, machine, os, status_active, username,
                         matrix_tag, test_type_id, manual_parameter, priority, config, prebatch, tag, isAddOns,
                         atsScriptName, adkLocation, jobXML, testName, installer_name, defsloc
                         )
            else:
                continue


def parse_url(url):
    content = (s.get(url)).content
    page = BS(content, 'html.parser')
    tables = page.find_all('table', attrs={'id': 'requests'})
    if tables is None or len(tables) == 0:
        return None
    table = tables[0]
    rows = table.find_all('tr')
    parsed_info = {}
    for row in rows:
        td_elems = row.find_all('td')
        if len(td_elems) < 11:
            continue
        parsed_info[td_elems[0].text] = {'Priority': td_elems[6].text, \
                'Status': td_elems[7].text, 'Machine': td_elems[8].text, \
                'Context': td_elems[9].text, 'Notes': td_elems[10].text}
    return parsed_info


def get_request_ids(url, machines, tests=None, os_types=None, context_types=None):
    request_ids = {}
    reqdict = parse_url(url)
    if reqdict is not None:
        for reqid in reqdict:
            r_info = reqdict[reqid]
            status = r_info['Status']
            machine = r_info['Machine'].split('-')[0]
            os = r_info['Machine'].split('-')[1]
            test = r_info['Notes'].split(' ')[0]
            context = r_info['Context']
            if 'MID-4' in r_info['Machine']:
                machine = 'MID4'
                os = 'Win8x64'
            if machine not in machines or \
               status == 'Completed' or \
               ((tests is not None) and (test not in tests)) or \
               ((os_types is not None) and (os not in os_types)) or \
               ((context_types is not None) and (context not in context_types)):
                continue
            request_ids[reqid] = r_info
    return request_ids


def process_delete():
    global username
    global build_number
    global priority
    global machine_param
    global machine_list
    global test_name_copy
    global contexts
    global specify_os
    global os_list
    global bypass_switch
    global debug_flag
    global prepare_php
    request_url = 'http://perf.eng.symantec.com/AutomatedTests/allRequests/' +\
            '?owner={0}&build={1}'.format(username[0], build_number)
    delete_url = 'http://perf.eng.symantec.com/AutomatedTests/deleteRequest' +\
            '?request_id={0}'
    url = database_server + prepare_php
    print()
    if priority.lower() == 'high':
        request_url = request_url + '&priority=high'
    if (machine_list == None) or (len(machine_list) == 0):
        machines = ['HIGH5', 'HIGH6', 'HIGH16', 'HIGH17', 'HIGH18', 'HIGH19', 'HIGH20', \
                'HIGH21', 'MID32', 'MID33', 'MID4', 'MID5', 'As203']
    else:
        machines = machine_list[:]
    if len(test_name_copy) != 0:
        test_names = test_name_copy[:]
    else:
        test_names = None
    reqdict = get_request_ids(url=request_url, machines=machines, tests=test_names, \
            os_types=os_list, context_types=contexts)
    for reqid in reqdict:
        r_info = reqdict[reqid]
        r_status = r_info['Status']
        r_notes = r_info['Notes']
        r_machine = r_info['Machine']
        if not bypass_switch:
            del_choice = input('Delete {0} Request: {1} for {2} on {3} ? (y/N): '\
                    .format(r_status, reqid, r_notes, r_machine))
        else:
            print('Delete {0} Request: {1} for {2} on {3} ? (y/N): y'\
                    .format(r_status, reqid, r_notes, r_machine))
            del_choice = 'y'
        if del_choice.lower() == 'y':
            s.post(delete_url.format(reqid))
            prepareData = {'reqNum': reqid}
            r = s.post(url, data=prepareData)
            rmessage = str(r.content)
            if debug_flag:
                print("---DEBUG---")
                print(rmessage)
                print("-----------")
            if rmessage.find("PDO Error") != -1:
                print('ERROR: Delete failed')
                if not debug_flag:
                    exit(rmessage)
            print('Delete Succeeded\n')
    print()


#------------------------------------------------------------------------------
#   Queue all Requests based on reqinfo
#------------------------------------------------------------------------------

if fast_flag:
    os_list = ['Win10x64']
reqinfo_list = reqinfo.copy()

# Ensure at least 1 iteration through the required tests
iter_param = 1 if not iter_param else iter_param

# Remove os that should be skipped
if skip_os_list:
    os_list = set(os_list) - set(skip_os_list)

if delete_switch:
    process_delete()
else:
    # Pre process request list for /iu and /NIC
    if not force_flag and (iu_name or not enableNIC):
        for req_key in reqinfo_list.keys():
            for idx, item in enumerate(reqinfo_list[req_key]):
                if 'Test Tag' in item.keys() and item['Test Tag'] == 'NoNetwork':
                    del reqinfo_list[req_key][idx]

    # Iterate through all manual tests in a group
    if m_param is not None and m_param.lower() == 'first':
        for i in range(iter_param):
            for req_note in m_list:
                if req_note in skip_tests:
                    continue
                for request in m_list[req_note]:
                    for context in contexts:
                        for os in os_list:
                            # Skipping ADK Test if /wpp is on
                            if enableWPP and request['Test ID'] == 18:
                                continue

                            request_bats = append_bats[:]
                            submit_request(request, req_note, context, os, request_bats)

    # Iterate through all non-manual tests
    for i in range(iter_param):
        for os in os_list:
            for context in contexts:
                for req_note in reqinfo_list:
                    if req_note in skip_tests:
                        continue
                    for request in reqinfo_list[req_note]:
                        # Skipping ADK Test if /wpp is on
                        if enableWPP and request['Test ID'] == 18:
                            continue

                        cos_int_enable = False
                        if 'Test Tag' in request.keys() and request['Test Tag'].upper() == 'COSINT':
                            HandleOptionCosint(None)    # enable cosint parameters
                            batch_name, cos_int_enable = 'cosint', True
                            del request['Test Tag']

                        # Iterations handling - set parameters
                        if 'Iterations' in request.keys():
                            HandleOptionTestIteration(['/iter', request['Iterations']])
                            if type(batch_note) is bool:
                                batch_note = ''
                            batch_note += " (Iter)"

                        request_bats = append_bats[:]
                        submit_request(request, req_note, context, os, request_bats)
                        
                        if cos_int_enable:  # reset the parameters set for cosint
                            batch_name, env_param, installer_name = None, None, None
                            test_tags.remove('cosint')

                        if 'Iterations' in request.keys():  # reset iteration parameters
                            reset_iterations()
                            batch_note.replace(' (Iter)', '')

    if m_param is not None and m_param.lower() == 'last':
        for i in range(iter_param):
            for req_note in m_list:
                if req_note in skip_tests:
                    continue

                for request in m_list[req_note]:
                    for context in contexts:
                        for os in os_list:
                            # Skipping ADK Test if /wpp is on
                            if enableWPP and request['Test ID'] == 18:
                                continue

                            request_bats = append_bats[:]
                            submit_request(request, req_note, context, os, request_bats)

if run_on_backup:
    print()
    for test in backup_tests:
        if test[1] in skip_machine:
            continue

        if enableWPP and test[0] == 18:
            continue

        submit_request(test)

#--------------------------------------------------------------------
#   DONE
#--------------------------------------------------------------------
r = s.post('http://perf.eng.symantec.com/logout', data=payload)
s.close();

for file in open_files:
    file.close()

for filename in created_files:
    if ospackage.path.isfile(filename):
        ospackage.remove(filename)

print('DONE')
