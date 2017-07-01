Harley Test Automation Management System 

This system allows you to generate reports based on the 5 options. Once the report is generated you can run harley for the protocols which are
not tested for a release or the ones which failed. 

HATMS supports three commands
1. generate - For generating the report 
2. run - Execute scheduled job
3. edit - Edit settings for generating the report

Help 

Use `python hatms.py -h` or `python hatms.py --help` to know about the commands

You can detailed help for a particular command by using `python hatms.py {command} -h` or `python hatms.py {command} --help`
where commands = ['edit', generate', 'run']


Receipe 1 - To generate protocol status for a release for a single test type

python hatms.py generate --type 'smoke'

The above command will generate report for all the protocols in branch='master', for release 1.4.0 and for 'smoke' test type

Receipe 2 - To generate protocol status for a release for all test types

python hatms.py generate

Receipe 3 - To change the harley user

python hatms.py edit user admin

Receipe 4 - To change the harley machine name

python hatms.py edit harley harley-dev3

Receipe 5 - To toggle any of the configuration 

Change the values in settins.json or 

python hatms.py edit {option_name} {option_value}

where {option_name} 

1. enable_save_diag_bundle 
2. enable_hold_test_passed
3. enable_hold_test_failed
4. enable_hold_test_
5. enable_swimlane_recyclying
6. enable_send_notification_fast
7. user
9. template
10. test_type

TODO 

1. Have a test suite having a list of settings to run
2. Add multi-threading for loading harley database
