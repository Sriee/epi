import os
import tornado.ioloop
from tornado import web, httpserver
from tornado.options import define, options


define('port', default=8000, help='Run on this port.', type=int)

class IndexHandler(web.RequestHandler):

    def data_received(self, chunk):
        pass

    def get(self):
        self.render('index.html')


class PoemHandler(web.RequestHandler):

    def data_received(self, chunk):
        pass

    def post(self):
        self.render('poem.html',
                    roads=self.get_argument('noun1'),
                    wood=self.get_argument('noun2'),
                    made=self.get_argument('verb'),
                    difference=self.get_argument('noun3')
                    )


if __name__ == '__main__':
    options.parse_command_line()
    app = web.Application(
        handlers=[(r'/', IndexHandler), (r'/poem', PoemHandler)],
        template_path=os.path.abspath(os.path.join(os.path.dirname(__file__),
                                                   '../templates'))
    )
    http_server = httpserver.HTTPServer(app)
    http_server.listen(options.port)

    try:
        tornado.ioloop.IOLoop.instance().start()
    except KeyboardInterrupt:
        print ''
        tornado.ioloop.IOLoop.instance().stop()
