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

To edit Harley development machine name

`python hatms.py edit --harley harley-dev1`

To toggle database update

_set_   - `python hatms.py edit --db_update True`

_reset_ - `python hatms.py edit --db_update False`

To toggle pagination for HTML report

_set_   - `python hatms.py edit --pagination True`

_reset_ - `python hatms.py edit --pagination False`

To edit Harley user name

`python hatms.py edit --user admin`

To edit output file name

`python hatms.py edit -o status_release` or `python hatms.py edit --output status_release`

To edit output file format

_html_ - `python hatms.py edit -f html` or `python hatms.py edit --format html` 

_json_ - `python hatms.py edit -f json` or `python hatms.py edit --format json`

___

### Generate Command 

To generate protocol status for a release and **_for a test type_**

`python hatms.py generate -b master -p all -t smoke-pim -r 1.4.0`  (or)

`python hatms.py generate --branch master --protocol all --type smoke --release 1.4.0`

To generate protocol status for a release and for **_all test type_**

`python hatms.py generate -b master -p all -r 1.4.0`  (or)

`python hatms.py generate --branch master --protocol all --release 1.4.0`

---

### Run Command 

To run the job schedule

`python hatms.py run`

To input the job schedule for the runner

`python hatms.py run -i /path/to/new_schedule.json` or 

`python hatms.py run --infile /path/to/new_schedule.json`

---

### TODO 

- [x] Populate test type table by calling REST API
- [ ] Fix database update bug 
- [ ] Create gif's for the recipes
- [ ] Add correct links to each recipes, Remove the sample gif
- [ ] Add multi-threading for loading harley database

