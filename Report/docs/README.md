# Harley Test Automation Management System 

Harley Test Automation Management System (HATMS) is a framework used to generate reports and run batch jobs using the Harley front end.

## Features

HATMS supports the following two feature

1. Generate Reports 
2. Run scheduled jobs

## Help 

#### To know the list of commands supported by HATMS
`python hatms.py -h` or `python hatms.py --help`

#### To know the options for `edit` command

`python hatms.py edit -h` or `python hatms.py edit --help`

#### To know the options for `generate` command

`python hatms.py generate -h` or `python hatms.py generate --help`

#### To know the options for `run` command 

`python hatms.py run -h` or `python hatms.py run --help`


## Documentation and Recipes

Please refer **docs** folder for detailed documentation of the framework and recipes link for using the commands

### Edit Command

[Recipe 1: To edit Harley development machine name](https://github.com/Sriee/epi/tree/master/Report/docs/sample.gif)

Recipe 2: To toggle database update

Recipe 3: To toggle pagination for HTML report

Recipe 4: To edit Harley user name

Recipe 5: To edit Harley development machine

Recipe 6: To edit output file name

Recipe 7: To edit output file format
---

### Generate Command 

Recipe 8: To generate protocol status for a release and for a test type

Recipe 9: To generate protocol status for a release and for all test type

---

### Run Command 

Recipe 10: To run the job schedule

Recipe 12: To input the job schedule for the runner


### TODO 

-[x] Populate test type table by calling REST API
-[] Fix database update bug 
-[] Add multi-threading for loading harley database

