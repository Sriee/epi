import urllib
import json
import datetime
import time

from tornado import web, httpserver, httpclient, ioloop


class IndexHandler(web.RequestHandler):
    """Synchronous Twitter tweet rate example"""

    def data_received(self, chunk):
        pass

    def get(self):
        query = self.get_argument('q')
        http_client = httpclient.HTTPClient()
        response = http_client.fetch('http://search.twitter.com/search.json?' +
                                     urllib.urlencode(
                                         {'q': query,
                                          'result_type': 'recent',
                                          'rpp': 100}))
        body = json.load(response)
        print body
        result_count = len(body['results'])
        now = datetime.datetime.now()
        raw_oldest_tweet_at = body['results'][-1]['created_at']
        oldest_tweet_at = datetime.datetime.strptime(raw_oldest_tweet_at,
                                                     '%a, %d %b %Y %H:%M:%S +0000')
        seconds_diff = time.mktime(now.timetuple()) - time.mktime(
            oldest_tweet_at.timetuple())
        tweets_per_second = float(result_count) / seconds_diff
        self.write("""
            <div style="text-align: center">
                <div style="font-size: 72px">%s</div>
                <div style="font-size: 144px">%.02f</div>
                <div style="font-size: 24px">tweets per second</div>
            </div>""" % (query, tweets_per_second))


if __name__ == '__main__':
    app = web.Application(handlers=[(r'/', IndexHandler)],
                          debug=True)
    http_server = httpserver.HTTPServer(app)
    http_server.listen(7000)
    try:
        ioloop.IOLoop.instance().start()
    except KeyboardInterrupt:
        print ''
        ioloop.IOLoop.instance().stop()
