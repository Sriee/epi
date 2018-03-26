from P4 import P4, P4Exception
from collections import namedtuple
import logging.config

logger = logging.getLogger(__name__)
WorkSpace = namedtuple('WorkSpace', 'client user location')

# Configure perforce work space configuration here
work_spaces = [WorkSpace('sriee_ws', 'sriee_sathiiss', '//depot/Global_Performance_Unit/Tools/Automation/...'),
               WorkSpace('performance', 'sriee_sathiiss', '//depot/Global_Performance_Unit/...')
               ]
perforce_server = 'ssl:p4cc.ges.symantec.com:1666'


class Perforce(object):

    def __init__(self, user, port, client):
        if not user or not port or not client:
            raise ValueError('Missing required credentials')

        self._p4 = P4()
        self._p4.user, self._p4.port, self._p4.client = user, port, client

    def execute(self, workspace_location):
        try:
            self._p4.connect()  # connect to perforce server

            if not self.is_connected():
                logger.debug('Failed to establish connection to the server')
                return

            logger.debug(self._p4)
            # Run p4 sync on workspace location. Return info captures in P4Exception
            self._p4.run_sync(workspace_location)
        except P4Exception:
            for e in self._p4.warnings:
                logger.info(e)
            for e in self._p4.errors:
                logger.error(e)
        finally:
            self._p4.disconnect()  # disconnect to perforce server

    def is_connected(self):
        return self._p4.connected()


def main():
    logger.info('Start...')
    # TODO: comments
    for ws in work_spaces:
        logger.debug(ws)
        perf = Perforce(user=ws.user, port=perforce_server, client=ws.client)
        perf.execute(ws.location)
    logger.info('Stop!...')


if __name__ == '__main__':
    # Setup log configuration
    logging.config.dictConfig({
        "version": 1,
        "disable_existing_loggers": False,
        "formatters": {
            "timestamp": {
                "format": "%(asctime)s:%(levelname)s: %(message)s",
                "datefmt": "[%m-%d-%Y][%I:%M]"
            }
        },
        "handlers": {
            "file": {
                "class": "logging.handlers.RotatingFileHandler",
                "level": "DEBUG",
                "formatter": "timestamp",
                "filename": "psync.log",
                "maxBytes": 10485760,
                "backupCount": 20,
                "encoding": "utf8"
            }
        },
        "root": {
            "level": "DEBUG",
            "handlers": ["file"]
        }
    })
    main()
