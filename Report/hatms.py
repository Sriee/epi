import os
import jinja2
import json
import logging.config


logger = logging.getLogger("hatms.file")


def render(data):
    filename = 'index.html'
    return jinja2.Environment(
        loader=jinja2.FileSystemLoader('./')
    ).get_template(filename).render(data)


def setup_logging(default_path='log_config.json', default_level=logging.INFO):
    """Setup logging configuration"""
    path = default_path
    if os.path.exists(path):
        with open(path, 'r') as f:
            config = json.load(f)

	# Removing old log file
	if os.path.exists(config['handlers']['file']['filename']:
           os.remov(config['handlers']['file']['filename'])

        logging.config.dictConfig(config)
    else:
        logging.basicConfig(level=default_level)


def main():

    logger.info("Logging is enabled")
    load = []
    with open('borrow.txt', 'r') as inp:

        for line in inp:
            l = line.split(',')
            load.append((l[2], l[3], l[4]))

    context = {
        "branch": "master",
        "harley": "harley-dev3",
        "version": "Latest Version",
        "payload": load
    }

    result = render(context)

    logger.debug("Writing to file")
    with open("result.html", 'w') as out:
        out.write(result)

    logger.debug("Wrote to file")

if __name__ == '__main__':
    setup_logging()
    main()
    logging.shutdown()
