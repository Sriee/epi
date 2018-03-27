from distutils.core import setup
import py2exe

setup(
    console=['psync.py'],
    options={
        'py2exe': {
            'packages': ['p4python', 'pytz']
        }
    }
)
