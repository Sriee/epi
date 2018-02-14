import os
import sys
import logging.config



try:
    import adapter_core
except ImportError:
    adapter_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'adapter_core'))
    print 'Adding {} to sys'.format(adapter_path)
    sys.path.append(adapter_path)


def _setup_logging():

    logging.config.dictConfig({
        'version': 1,
        'disable_existing_loggers': False,
        'formatters': {
            'default': {
                'format': '%(asctime)s:%(levelname)s:%(name)s : %(message)s',
                'datefmt': '%m-%d-%Y'
            }
        },
        'handlers': {
            'file': {
                'class': 'logging.handlers.RotatingFileHandler',
                'level': 'DEBUG',
                'formatter': 'default',
                'filename': 'cf_ide.log',
                'maxBytes': 10485760,
                'backupCount': 20,
                'encoding': 'utf8'
            }
        },
        'root': {
            'level': 'DEBUG',
            'handlers': ['file']
        }
    })


LOGGER = logging.getLogger(__name__)


if __name__ == '__main__':
    _setup_logging()
    LOGGER.info('Logger is enabled')
