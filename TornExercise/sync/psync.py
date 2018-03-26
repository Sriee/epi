from P4 import P4, P4Exception

# Change perforce settings here
work_spaces = [('sriee_sathiiss', 'sriee_ws')]  # List of (user, client) tuples
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
                print('Failed to establish connection to the server')
                return

            # Run p4 sync on workspace location. Return info captures in P4Exception
            self._p4.run_sync(workspace_location)
        except P4Exception:
            for e in self._p4.warnings:
                print(e)
            for e in self._p4.errors:
                print(e)
        finally:
            self._p4.disconnect()  # disconnect to perforce server

    def is_connected(self):
        return self._p4.connected()


def main():
    for ws in work_spaces:
        perf = Perforce(user=ws[0],
                        port=perforce_server,
                        client=ws[1]
                        )
        perf.execute('//depot/Global_Performance_Unit/Tools/Automation/...')


if __name__ == '__main__':
    main()
