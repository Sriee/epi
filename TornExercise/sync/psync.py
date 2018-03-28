from P4 import P4, P4Exception
from collections import namedtuple
import logging.config
import os

logger = logging.getLogger(__name__)
WorkSpace = namedtuple('WorkSpace', 'client user location')

# Perforce work space configuration goes here
work_spaces = [WorkSpace('sriee_ws', 'sriee_sathiiss', '//depot/Global_Performance_Unit/Tools/Automation/...'),
               WorkSpace('performance', 'sriee_sathiiss', '//depot/Global_Performance_Unit/...')
               ]
perforce_server = 'ssl:p4cc.ges.symantec.com:1666'


class Perforce(object):
    """
    Service which interacts with Perforce server and syncs local work spaces
    """
    def __init__(self, user, port, client):
        """
        Initialize Perforce client environment

        Assumes that the user has logged in and has a valid session. Can't login using run_login() method since
        Perforce server is configured to enter SYMC password and VIP token to login
        :param user: User name
        :param port: Perforce server address and port number
        :param client: local work space
        """
        if not user or not port or not client:
            raise ValueError('Missing required credentials')

        self._p4 = P4()
        self._p4.user, self._p4.port, self._p4.client = user, port, client

    def execute(self, workspace_location):
        """
        Implements p4 sync /path/to/location

        :param workspace_location: local workspace location
        """
        try:
            self._p4.connect()  # connect to perforce server

            if not self.is_connected():
                logger.error('Failed to establish connection to the server')
                return

            logger.debug(self._p4)
            # Run p4 sync on workspace location. Return info captures in P4Exception
            self._p4.run_sync(workspace_location)
            logger.info(self._p4.client + ' File(s) synced.')
        except P4Exception:
            for e in self._p4.warnings:
                logger.info(self._p4.client + ' ' + e)
            for e in self._p4.errors:
                logger.error(self._p4.client + ' ' + e)
        finally:
            if self.is_connected():
                self._p4.disconnect()  # disconnect to perforce server

    def is_connected(self):
        """
        Utility function to check whether connection with the server is established

        :return: True if connection is established; False otherwise
        """
        return self._p4.connected()


def main():
    logger.debug('Start...')
    for ws in work_spaces:
        logger.debug(ws)
        perf = Perforce(user=ws.user, port=perforce_server, client=ws.client)
        perf.execute(ws.location)
    logger.debug('Stop!...')


if __name__ == '__main__':
    # Setup log configuration
    logging.config.dictConfig({
        "version": 1,
        "disable_existing_loggers": False,
        "formatters": {
            "timestamp": {
                "format": "%(asctime)s:%(levelname)s: %(message)s",
                "datefmt": "[%m-%d-%Y][%H:%M]"
            }
        },
        "handlers": {
            "file": {
                "class": "logging.handlers.RotatingFileHandler",
                "formatter": "timestamp",
                "filename": os.path.abspath(os.path.join(os.path.dirname(__file__), 'psync.log')),
                "maxBytes": 10485760,
                "backupCount": 20,
                "encoding": "utf8"
            }
        },
        "root": {
            "level": "INFO",
            "handlers": ["file"]
        }
    })
    main()
