from P4 import P4, P4Exception
import traceback
import sys

p4 = P4()
p4.port = 'ssl:p4cc.ges.symantec.com:1666'
p4.user = 'sriee_sathiiss'
p4.client = 'sriee_ws'


class Perforce(object):

    def __init__(self, user, port, client):
        if not user or not port or not client:
            raise ValueError('Missing required credentials')

        self.p4 = P4()
        self.p4.user, self.p4.port, self.p4.client = user, port, client
        self.p4.exception_level = 1 # Avoid warnings being raised as exceptions

    def is_connected(self):
        return self.p4.connected()

    def connect(self):
        self.p4.connect()

    def disconnect(self):
        try:
            self.p4.disconnect()
        except P4Exception:
            traceback.print_exc()


def main():
    perf = Perforce()
    try:
        # connect to perforce server
        perf.connect()

        if not perf.is_connected():
            print('Failed to establish connection to the server')
            sys.exit(-1)

        result = perf.p4.run('sync', '//depot/Global_Performance_Unit/Tools/Automation/...')
        print(type(result))
    except P4Exception:
        traceback.print_exc()
    finally:
        perf.disconnect()


if __name__ == '__main__':
    main()
