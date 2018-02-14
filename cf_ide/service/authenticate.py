import requests
import time
import logging.config
import urllib3

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

BEARER_TOKEN = 'M3zcxzBDvR4ikJx63eGN'
LOGGER = logging.getLogger('cf_ide')


class Token(object):

    def __init__(self, func):
        self.func = func

    @property
    def bearer(self):
        global BEARER_TOKEN
        if not BEARER_TOKEN:
            LOGGER.info('Creating new bearer token.')
            # Generate new bearer token
            response = requests.post('https://10.141.40.163/api/v2/token',
                                     {
                                         'email': 'admin@spirent.com',
                                         'password': 'spirent'
                                     },
                                     verify=False)
            self.bearer = response.json()['token']
            LOGGER.debug('Bearer token {} created'.format(self.bearer))
        return BEARER_TOKEN

    @bearer.setter
    def bearer(self, value):
        global BEARER_TOKEN
        BEARER_TOKEN = value

    def __call__(self, *args, **kwargs):
        retries = 1
        while retries <= 3:
            try:
                res = requests.get('https://10.141.40.163//api/v2/system/version',
                                   headers={'Content-Type': 'application/json',
                                            'Authorization': 'Bearer ' + self.bearer},
                                   verify=False)
                if res.status_code == 401:
                    LOGGER.info('Bearer Token {} expired'.format(self.bearer))
                    # Generate new bearer token
                    response = requests.post('https://10.141.40.163/api/v2/token',
                                             {
                                                 'email': 'admin@spirent.com',
                                                 'password': 'spirent'
                                             },
                                             verify=False)
                    self.bearer = response.json()['token']
                    LOGGER.debug('Bearer token {} created'.format(self.bearer))
                    retries += 1
                    time.sleep(2)
                else:
                    LOGGER.debug('Token valid')
                    break
            except requests.exceptions.SSLError:
                retries += 1
                time.sleep(2)
                continue

        self.func(*args, **kwargs)
