from P4 import P4, P4Exception
import traceback

# Change perforce settings here
work_spaces = [('sriee_sathiiss', 'sriee_ws')]  # List of (user, client) tuples
port = 'ssl:p4cc.ges.symantec.com:1666'


class Perforce(object):

    def __init__(self, user, port, client):
        if not user or not port or not client:
            raise ValueError('Missing required credentials')

        self._p4 = P4()
        self._p4.user, self._p4.port, self._p4.client = user, port, client
        self._p4.exception_level = 1 # Avoid warnings being raised as exceptions

    def connect(self):
        self._p4.connect()

    def execute(self, workspace_location):
        self._p4.run('sync', workspace_location)

    def disconnect(self):
        self._p4.disconnect()

    def is_connected(self):
        return self._p4.connected()


def main():
    for ws in work_spaces:
        perf = Perforce(user=ws[0],
                        port=port,
                        client=ws[1]
            )
        try:
            # connect to perforce server
            perf.connect()

            if not perf.is_connected():
                print('Failed to establish connection to the server')
                continue

            perf.execute('//depot/Global_Performance_Unit/Tools/Automation/...')
        except P4Exception:
            for e in perf._p4.errors:
                print(e)
        finally:
            perf.disconnect()


if __name__ == '__main__':
    main()
