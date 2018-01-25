import tornado.httpserver
import tornado.options
import tornado.ioloop
import tornado.web

from tornado.options import define, options
define('port', default=7000, type=int, help='Run on the given port')


class IndexHandler(tornado.web.RequestHandler):

    def get(self):
        greet = self.get_argument('greeting', 'Hello')
        self.write(greet + ' , user!\n')

    def write_error(self, status_code, **kwargs):
        self.write('Oops! Something went wrong.')


if __name__ == '__main__':
    tornado.options.parse_command_line()
    app = tornado.web.Application(handlers = [(r'/', IndexHandler)])
    http_server = tornado.httpserver.HTTPServer(app)
    http_server.listen(options.port)
    try:
        tornado.ioloop.IOLoop.instance().start()
    except KeyboardInterrupt:
        tornado.ioloop.IOLoop.instance().stop()
