import os
import jinja2
import json
import logging.config

import borrower
from context import Context

logger = logging.getLogger("hatms.file")


def render(data):
    filename = 'index.html'
    env = jinja2.Environment(loader=jinja2.FileSystemLoader('./'),
                             trim_blocks=True, lstrip_blocks=True)
    return env.get_template(filename).render(data)


def setup_logging(default_path='log_config.json', default_level=logging.INFO):
    """Setup logging configuration"""
    path = default_path
    if os.path.exists(path):
        with open(path, 'r') as f:
            config = json.load(f)

        logging.config.dictConfig(config)
    else:
        logging.basicConfig(level=default_level)


def main():

    start, end = 'Logging is', 'enabled'
    logger.info('%s%s', start, end)
    load = []

    with open('borrow.txt', 'r') as inp:

        for line in inp:
            ln = line.split(',')
            load.append(borrower.Borrower(ln[2], ln[3], ln[4]))

    context = Context('master', 'harley-dev3', 'latest', load)

    result = render(context.toDict())

    logger.debug("Writing to file")
    with open("result.html", 'w') as out:
        out.write(result)

    logger.debug("Wrote to file")


if __name__ == '__main__':
    setup_logging()
    logger.info('{0} New Run {0}'.format('*' * 20))
    logging.shutdown()
