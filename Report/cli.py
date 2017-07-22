import os
import json
import argparse
import logging.config

consoleLogger = logging.getLogger("harley.console")


def setup_logging(default_path='config/log_config.json',
                  default_level=logging.INFO):
    """Setup logging configuration"""
    path = default_path
    if os.path.exists(path):
        with open(path, 'r') as f:
            config = json.load(f)
        logging.config.dictConfig(config)
    else:
        consoleLogger.warning('Log configuration missing. '
                              'Using default configuration')
        logging.basicConfig(level=default_level)


def setup_parsing():
    ifc = argparse.ArgumentParser(description='Harley Test Automation '
                                              'Management System')

    # Optional arguments
    ifc.add_argument('--info', action='version', version='Harley Test '
                     'Automation Management System v1.0')
    # Generate Group
    sub_parser = ifc.add_subparsers(title='Commands', dest='subparser_name')
    generate_group = sub_parser.add_parser('generate', help='Generate Report')

    # Generate Group - Optional Flags
    generate_group.add_argument('-b', '--branch', default=['master'],
                                choices=['master', 'all'],
                                help='Branch Name')
    generate_group.add_argument('-p', '--protocol', nargs='+', default=['all'],
                                help='Protocol Name')
    generate_group.add_argument('-t', '--type', default='smoke',
                                help='Test Type')
    generate_group.add_argument('-r', '--release', help='Release')
    generate_group.add_argument('-v', '--version', default='latest',
                                help='Version')

    generate_group.add_argument('-o', '--output',
                                help='File name for storing the scheduled job '
                                     'template')
    generate_group.add_argument('-f', '--format', choices=['html', 'json'],
                                help='Format of the output file')

    # Report Group
    report_group = sub_parser.add_parser('run', help='Run scheduled Jobs')
    report_group.add_argument('-i', '--infile',
                              help='Input of schedule job template')

    return ifc


def rain_check():
    db_path = os.path.join('database')
    config_path = os.path.join('config')
    report_path = os.path.join('report')
    template_path = os.path.join('template')
    temp = True

    # Checking directories
    if not os.path.isdir(db_path):
        consoleLogger.error('Database folder missing.')
        return False

    if not os.path.isdir(config_path):
        consoleLogger.error('Configuration folder missing.')
        return False

    if not os.path.isdir(report_path):
        consoleLogger.error('Reports folder missing')
        return False

    if not os.path.isdir(template_path):
        consoleLogger.warning('Template folder missing. Ignore warning if '
                              'outputting to a \'JSON\' file')
        temp = False

    # Checking configuration files
    settings_configuration = os.path.join(config_path, 'settings.json')
    if not os.path.isfile(settings_configuration):
        consoleLogger.error('Missing settings configuration.')
        return False

    template_configuration = os.path.join(config_path, 'new_job_template.json')
    if not os.path.isfile(template_configuration):
        consoleLogger.error('Missing Job Template configuration.')
        return False

    schedule_configuration = os.path.join(report_path, 'scheduled_job.json')
    if not os.path.isfile(schedule_configuration):
        consoleLogger.error('Missing Job Schedule Template configuration')
        return False

    if temp:
        if not os.path.isfile(os.path.join(template_path, 'base.html')) or \
                os.path.isfile(os.path.join(template_path, 'status.html')) or \
                os.path.isfile(
                    os.path.join(template_path, 'status_release.html')):
            consoleLogger.warning('Template files missing. Ignore warning if '
                                  'outputting to a \'JSON\' file')

    consoleLogger.info('Rain check was successful.')
    return True


if __name__ == '__main__':
    setup_logging()

    check = rain_check()

    if check is False:
        consoleLogger.error('Failed Rain check.')
    #    sys.exit(-1)

    cli = setup_parsing()

    args = cli.parse_args()

    if args.subparser_name == 'generate':
        print(args.branch)
        print type(args.branch[0])
        print('Generate command ba')
    else:
        print('Run command ba')
    logging.shutdown()
