import os
import sys
import re
import sqlite3


class DatabaseLoader(object):

    def __init__(self):
        print('Connecting to Database.')
        self.db_path = os.path.join('database', 'test.db')

        try:
            self.connection = sqlite3.connect(self.db_path)
            self.cursor = self.connection.cursor()
        except sqlite3.OperationalError:
            print('Failed connecting to the Database.')
            sys.exit(-1)

    def load_database(self):
        regex = r"^(?:DROP|CREATE)(?:[^;']|(?:'[^']+'   ))+;\s*$"

        with open('loader.sql', 'r') as fp:
            test_str = fp.read()

        matches = re.finditer(regex, test_str, re.MULTILINE | re.IGNORECASE)

        for match in matches:
            self.execute(match.group())

    def execute(self, statement):
        self.cursor.execute(statement)
        self.connection.commit()

    def finish(self):
        self.cursor.close()
        self.connection.close()
